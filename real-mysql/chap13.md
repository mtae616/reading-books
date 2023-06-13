# partition

- 논리적으로는 하나의 테이블이지만 물리적으로는 여러 개의 테이블로 분리해서 관리
- 대용량의 테이블을 물리적으로 여러 개의 소규모 테이블로 분산하는 목적으로 사용

## 13.1 개요

- 파티션이 SQL 문장을 수행하는 데 어떻게 영향을 미치는지, 파티션으로 기대할 수 있는 장점이 무엇인지 알아본다.

## 13.1.1 파티션을 사용하는 이유

- 데이터 많다고 무조건 쓰는 것은 아님
- 용례
    - 인덱스의 크기가 물리적인 메모리보다 훨신 클 때
    - 데이터 특성상 주기적인 삭제 작업이 필요한 경우

## 13.1.1.1 단일 INSERT 와 단일 또는 범위 SELECT 의 빠른 처리

- UPDATE 나 DELETE 를 위해 index 를 사용하기도 함
    - 대상 레코드를 검색하려면
- index 가 커지면 select, insert, update 다 느려짐
- 테이블의 데이터는 실질적인 물리 메로리보다 큰 것이 일반적
    - 인덱스의 워킹 셋이 실질적인 물리 메모리보다 크다면 쿼리 처리가 상당히 느려질 것
- 파티션은 데이터와 인덱스를 조각화해서 물리적 메모리를 효율적으로 사용할 수 있게 만들어준다.

## 13.1.1.2 데이터의 물리적인 저장소를 분리

- 데이터 파일, 인덱스 파일이 시스템에서 차지하는 공간이 크면 백업이나 관리 작업 어려움

## 13.1.1.3 이력 데이터의 효율적인 관리

- 로그
    - 대량으로 누적됨, 일정 기간이 지나면 쓸모 없음
    - 별도로 아카이빙 or 백업 후 삭제
    - 데이터를 백업하거나 삭제하는 작업은 일반 테이블에서는 상당히 고부하의 작업
    - 로그 테이블을 파티션으로 관리한다면 단순히 파티션을 추가하거나 삭제하는 방식으로 간단하고 빠르게 해결

## 13.1.2 MySQL 파티션의 내부 처리

```sql
CREATE TABLE tb_article(
	article_id int not null,
	reg_date datetime not null,
	primary key(article_id, reg_date)
) partition by range(year(reg_date)) (
	partition p2009 values less then (2010),
	partition p2009 values less then (2011),
	partition p2009 values less then (2012),
	partition p2009 values less then MAXVALUE,
)
```

- reg_date 에서 연도 부분을 파티션 키로
    - 해당 레코드가 어느 파티션에서 저장될지를 결정

## 13.1.2.1 파티션 테이블의 레코드 INSERT

- reg_date 칼럼의 값을 이용해 파티션 표현식을 평가하고 레코드가 저장될 적절한 파티션 결정
- 새로 insert 되는 레코드를 위한 파티션이 결정되면 나머지 과정은 파티션되지 않은 테이블과 동일

## 13.1.2.2 파티션 테이블의 UPDATE

- update 하려면 어느 파티션에 저장돼 있는지 찾아야 함
- 이때 update 쿼리의 where 조건에 파티션 키 칼럼이 조건으로 존재하면 그 값을 이용해 레코드가 저장된 파티션에서 빠르게 대상 레코드를 검색할 수 있다.
    - 파티션 키 없으면 테이블의 모든 파티션을 검색
    - 변경 절차는 update 쿼리가 어떤 컬럼의 값을 변경하느냐에 따라 큰 차이가 생긴다.
        - 파티션 키 이외
            - 파티션이 적용되지 않은 일반 테이블과 같다.
        - 파티션 키
            - 파티션 키 컬럼이 변경될 때에는 기존 레코드를 파티션에서 삭제하고
            - 파티션 키 컬럼의 표현식을 평가하고
            - 새로운 파티션을 결정해서 새로 저장한다.

## 13.1.2.3 파티션 테이블의 검색

- 파티션 테이블을 검색할 때 성능에 크게 영향을 미치는 조건
    - where 조건으로 검색해야 할 파티션을 선택할 수 있는가?
    - where 조건이 인덱스를 효율적으로 사용(인덱스 레인지 스캔)할 수 있는가?
- 위의 첫 번재 선택사항의 결과에 의해 두 번째 선택사항의 작업 내용이 달라질 수 있다.
    - 파티션 선택 가능 + 인덱스 효율적 사용
        - 가장 효율적으로 처리
        - 파티션 개수와 관계 없이 검색을 위해 꼭 필요한 파티션의 인덱스만 레인지 스캔
    - 파티션 선택 불가 + 인덱스 효율적 사용 가능
        - 모든 파티션을 대상으로 검색
        - 각 파티션에 대해서는 인덱스 레인지 스캔을 사용
        - 모든 파티션의 개수만큼 인덱스 레인지 스캔을 수행해서 검색
        - 파티션의 개수만큼의 테이블에 대해 인덱스 레인지 스캔 한 다음 병합 후 결과를 병합해서 가져오는 것과 같다.
    - 파티션 선택 가능 + 인덱스 효율적 사용 불가
        - 검색을 위해 필요한 파티션만 읽으면 된다.
        - 파티션에 대해 테이블 풀 스캔 수행
    - 파티션 선택 불가 + 인덱스 효율적 사용 불가
        - 모든 파티션 검색 + 파티션 (테이블 풀 스캔) 풀 스캔
        - 가능한 피해야 한다.

## 13.1.2.4 파티션 테이블의 인덱스 스캔과 정렬

- 파티션 테이블에서 인덱스는 전부 로컬 인덱스에 해당한다.
    - 모든 인덱스는 파티션 단위로 생성된다.
    - 파티션과 관계없이 테이블 전체 단위로 글로벌하게 하나의 통합된 인덱스는 지원하지 않는다.
- 그림에서 reg_userid 칼럼의 값은 파티션의 순서대로 정렬돼 잇지 않다.
    - 파티션되지 않은 테이블에서는 인덱스를 순서대로 읽으면 칼럼으로 정렬된 결과를 바로 얻을 수 있지만 파티션된 테이블에서는 그렇지 않다.

```sql
select *
from tb_article
where reg_userid between 'brew' and 'toto'
	and reg_date between '2009-01-01' and '2010-12-31'
order by reg_userid;
```

- 위의 쿼리의 실행 계획을 확인해보면 Using filesort 가 표시되지 않는다.
    - 간단히 생각해보면 partition_2009 와 partition_2010 으로부터 where 조건이 일치하는 레코드를 가져온 후 파티션의 결과를 병합하고 reg_userid 칼럼의 값으로 다시 정렬해야 될 것처럼 보인다.
    - 하지만 실행 계획에는 별도의 정렬을 수행했다는 메세지는 표시되지 않는다.
- 실제 MySQL 서버는 여러 파티션에 대해 인덱스 스캔을 수행할 때 각 파티션으로부터 조건에 일치하는 레코드를 정렬된 순서대로 읽으면서 우선순위 큐에 임시로 저장한다.
    - 우선순위 큐에서 다시 필요한 순서(인덱스의 정렬 순서) 대로 데이터를 가져간다.
    - 파티션에서 읽은 데이터가 이미 정렬돼 있는 상태라서 가능한 방법이다.

## 13.1.2.5 파티션 프루닝

- 옵티마이저에 의해 불필요한 파티션에 접근하지 않는 것
- 최적화 단계에서 필요한 파티션만 골라내고 불필요한 것들은 실행 계획에서 배제하는 것을 파티션 프루닝이라고 한다.
- 실행 계획을 확인해보면 옵티마이저가 어떤 파티션만 접근했는지 알 수 있다.
    - partitions 칼럼으로 나온다.

## 13.2 주의사항

- 제약사항 들에 대해 얘기함

## 13.2.1 파티션의 제약 사항

```sql
CREATE TABLE tb_article(
	article_id int not null,
	reg_date datetime not null,
	...
	primary key(article_id, reg_date)
) partition by range(year(reg_date)) (
	partition p2009 values less then (2010),
	partition p2010 values less then (2011),
	partition p2011 values less then (2012),
	partition p9999 values less then MAXVALUE,
)
```

- 파티션 테이블을 가정
- 레인지 파티션 사용, 파티션 칼럼은 reg_date, year 로 연도만 사용
    - 연도 범위별로 파티셔닝
- MySQL 서버의 파티션이 가지는 제약사항
    - 스토어드 루틴, UDF, 사용자 변수 등을 파티션 표현식에 사용할 수 없다.
    - 파티션 표현식은 칼럼 그 자체 또는 내장 함수를 사용 여기서 일부 함수들은 파티션 생성은 가능하지만 파티션 프루닝을 지원하지 않을 수 있다.
    - PK 를 포함해서 모든 유니크 인덱스는 파티션 키 칼럼을 포함해야 한다.
    - 파티션된 테이블의 인덱스는 모두 로컬 인덱스이며 동일 테이블에 소속된 모든 파티션은 같은 구조의 인덱스만 가질 수 있다. 또한 파티션 개별로 인덱스를 변경하거나 추가할 수 없다.
    - 동일 테이블에 속한 모든 파티션은 동일 스토리지 엔진만 가질 수 있다.
    - 최대 (서브 파티션까지 포함해서) 8192 개의 파티션을 가질 수 있다.
    - 파티션 생성 이후 MySQL 서버의 sql_mode 시스템 변수 변경은 데이터 파티션의 일관성을 깨뜨릴 수 있다.
    - 파티션 테이블에서는 외래키를 사용할 수 없다.
    - 파티션 테이블은 전문 검색 인덱스 생성이나 전문 검색 쿼리를 사용할 수 없다.
    - 공간 데이터를 저장하는 칼럼 타입은 파티션 테이블에서 사용할 수 없다.
    - 임시 테이블(Temporary table)은 파티션 기능을 사용할 수 없다.
- 파티션 표현식에는 기본 산술 연산자인 “+”, “-”, “*” 같은 연산자가 가능하다.
    - 내장 함수들을 파티션 표현식에 사용할 수 있다고 해서 모두 파티션 프루닝을 제공하는 것은 아니다.


## 13.2.2 파티션 사용 시 주의사항

- pk 를 포함한 유니크 키에 대해서는 상당히 머리 아픈 제약 사항이 있다.
- 파티션의 목적 → 작업 범위를 좁히는 것
    - 유니크 인덱스는 중복 레코드에 대한 체크 작업 때문에 범위가 좁혀지지 않는다.
    - 파티션은 별도의 파일로 관리되는데 이와 관련해서 MySQL 서버가 조작할 수 있는 파일의 개수와 연관된 제약도 있다.

## 13.2.2.1 파티션과 유니크 키 (프라이머리 키 포함)

- 유니크 인덱스가 있으면 파티션 키는 모든 유니크 인덱스의 일부 또는 모든 칼럼을 포함해야 한다.

```sql
create table tb_partition (
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	unique key (fd1, fd2)
) partition by hash(fd3)

// => 아래처럼 하면 파티션 가능
create table tb_partition (
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	unique key (fd1, fd2, fd3)
) partition by hash(fd1)
```

- 위 쿼리는 유니크 키와 파티션 키가 전혀 연관이 없기 때문에 불가능

```sql
create table tb_partition(
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	unique key (fd1),
	unique key (fd2)
) partition by hash(fd1 + fd2)
	partitions 4;

// => 아래처럼 하면 파티션 가능
create table tb_partition(
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	unique key (fd1, fd2)
) partition by hash(fd1 + fd2)
	partitions 4;
```

- 위 쿼리는 첫 번째 유니크 키 칼럼인 fd1 만으로 파티션 결정이 되지 않는다 (fd2 컬럼값도 같이 있어야 파티션의 위치를 판단할 수 있다.), 두번째 유니크 키 또한 첫 번째와 같은 이유로 불가능하다.

```sql
create table tb_partition(
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	primary key(fd1),
	unique key(fd2, fd3)
) partition by hash(fd1 + fd2)
	partitions 4;

// => 아래처럼 하면 파티션 가능
create table tb_partition(
	fd1 int not null,
	fd2 int not null,
	fd3 int not null,
	unique key(fd1, fd2, fd3),
	unique key(fd3)
) partition by hash(fd3)
	partitions 4;
```

- pk 칼럼인 fd1 값만으로 파티션 판단이 되지 않으며 유니크 키인 fd2 와 fd3 로도 파티션 위치를 결정할 수 없다.

## 13.2.2.2 파티션과 open_files_limit 시스템 변수 설정

- 동시에 오픈할 수 있는 적절한 파일 개수 설정
- 테이블 1개 당 → 2 ~ 3
    - (파티션 개수 * 2 ~ 3)
    - 1024 개 가운 데 2개의 파티션만 접근해도 된다고 하더라도 일단 동시에 모든 파티션의 데이터 파일 오픈
    - 그래서 파티션을 많이 사용하는 경우에는 open_files_limit 시스템 변수를 적절히 높은 값으로 다시 설정해 줄 필요가 있다.

## 13.3 MySQL 파티션 종류

- 레인지 파티션
- 리스트 파티션
- 해시 파티션
- 키 파티션

## 13.3.1 레인지 파티션

- 파티션 키의 연속된 범위로 정의
- 가장 일반적
- MAXVALUE 라는 키워드를 이용해 명시되지 않은 범위의 키 값이 담긴 레코드를 저장하는 파티션 정의

## 13.3.1.1 레인지 파티션의 용도

- 용례
    - 날짜를 기반으로 데이터가 누적되고 연도나 월 또는 일 단위로 분석하고 삭제해야 할 때 → log
    - 범위 기반으로 데이터를 여러 파티션에 균등하게 나눌 수 있을 때
    - 파티션 키 위주로 검색이 자주 실행될 때
- 파티션의 장점
    - 큰 테이블을 작은 크기의 파티션으로 분리
    - 필요한 파티션만 접근 → 가장 주요한 장점
- 레인지 파티션은 파티션의 장점 2가지를 모두 취할 수 있다.

## 13.3.1.2 레인지 파티션 테이블 생성

```sql
create table employees (
	id int not null,
	first_name varchar(30),
	last_name varchar(30),
	hired date not null default '1970-01-01',
	...
) partition by range(year(hired)) (
	prtition p0 values less than (1991),
	prtition p1 values less than (1996),
	prtition p2 values less than (2001),
	prtition p3 values less than MAXVALUE
);
```

- partition by range 키워드로 레인지 파티션을 정의
- partition by range 뒤에 칼럼 또는 내장 함수를 이용해 파티션 키를 명시
    - 연도만을 파티션 키로 사용했다.
- values less than 으로 명시된 값보다 작은 값만 해당 파티션에 저장하게 설정한다.
    - less than 절에 명시된 값으 그 파티션에 포함되지 않는다.
- values less than maxvalue 로 명시되지 않은 레코드를 저장할 파티션을 지정한다.
    - 2001 년 부터 9999 년 사이에 입사한 사원의 정보는 p3 파티션에 저장될 것이다.
    - maxvalue 는 선택 사항
    - 만약 maxvalue 가 설정되지 않고 2011년도 레코드가 insert 될 때는 에러가 발생한다.

## 13.3.1.3 레인지 파티션의 분리와 병합

## 13.3.1.3.1 단순 파티션의 추가

```sql
alter table employees
	add partition (partition p4 values less than(2011));
```

- maxvalue 로 중첩된 범위가 있음
- 따라서 위의 쿼리는 에러를 반환

```sql
alter table employees alorithm = inplace, lock = shared,
	reoranize partition p3 into (
		partition p3 values less than (2011),
		partition p4 values less than maxvalue
)
```

- 따라서 위와 같은 모습으로 파티션을 나눠야 함
- 레인지 파티션에서 일반적으로 maxvlue 는 잘 안쓰지 않고 미리 2 ~ 3개를 추가로 만들어 둠
    - 배치를 통해 자동으로 파티션 추가하는 방법을 사용한다.
    - 근데 걍 less than maxvalue 만들어 두기도 함…

## 13.3.1.3.2 파티션 삭제

```sql
alter table employees drop partition p0;
```

- 위처럼 p0 테이블 삭제
- 가장 오래된 파티션 순서로만 삭제할 수 있음

## 13.3.1.3.3 기존 파티션의 분리

- 위에서 봤던 reorganize 를 통해 분리할 수 있음
- 기존 파티션의 레코드를 새로운 파티션으로 복사해야 하기 때문에 시간이 오래 걸릴 수도 있음
- 위의 쿼리는 online ddl 을 지원하기 위해 algorithm 과 lock 을 사용하였음

## 13.3.1.3.4 기존 파티션의 병합

```sql
alter table employees algorithm = inplace, lock = shared,
	reorganize partition p2, p3 into (
		partition p23 values less than (2011)
)
```

- 위와 같은 모습으로 할 수 있음

## 13.3.2 리스트 파티션

- 레인지 파티션과 매우 흡사
- 다른 점은 파티션 키 값 하나하나를 리스트로 나열, maxvalue 정의 할 수 없음

## 13.3.2.1 리스트 파티션의 용도

- 용도
    - 파티션 키 값이 코드 값이나 카테고리와 같이 고정적일 때
    - 키 값이 연속되지 않고 정렬 순서와 관계없이 파티션을 해야할 때
    - 파티션 키 값을 기준으로 레코드의 건수가 균일하고 조건에 파티션 키가 자주 사용될 때

## 13.3.2.2 리스트 파티션 테이블 생성

```sql
create table product (
	id int not null,
	name varchar(30),
	category_id int not null,
	...
) partition by LIST(category_id) (
	partition p_applicance values in (3),
	partition p_computer values in (1, 9),
	partition p_sports values in (2, 6, 7),
	partition p_etc values in (4, 5, 8, null),
);
```

- partition by list 로 리스트 파티션 임을 명시
    - category_id 칼럼값을 그대로 파티션 키로 사용
- values in (…) 파티션 키 값의 목록을 나열
- null 또한 명시 가능
- 문자열 타입도 가능하다.

## 13.3.2.3 리스트 파티션의 분리와 병함

- values in 을 사용한다는 것 외에는 레인지 파티션의 추가 및 삭제 병합 작업이 모두 같다.

## 13.3.2.4 리스트 파티션 주의사항

- maxvalue 사용할 수 없다.
- null 을 저장하는 파티션을 별도로 생성할 수 있다.

## 13.3.3 해시 파티션

- 파티션 표현식의 결괏값을 파티션의 개수로 나눈 나머지로 저장될 파티션을 결정하는 방식
- 정사 또는 정수를 반환하는 표현식만 사용될 수 있다.

## 13.3.3.1 해시 파티션의 용도

- 레인지 파티션이나 리스트 파티션으로 데이터를 균등하게 나누는 것이 어려울 때
- 테이블의 모든 레코드가 비슷한 사용 빈도를 보이지만 테이블이 너무 커서 파티션을 적용해야 할 때
- 이를 테면 회원 테이블

## 13.3.3.2 해시 파티션 테이블 생성

```sql
create table employees (
	id int not null,
	first_name varchar(30),
	last_name varchar(30),
	hired date not null default '1970-01-01',
	...
) partition by hash(id) partitions 4;

-- // 파티션의 이름을 명시적으로 하고 싶을 때
create table employees (
	id int not null,
	first_name varchar(30),
	last_name varchar(30),
	hired date not null default '1970-01-01',
	...
) partition by hash(id)
	partitions 4 (
		partition p0 engine = innodb,
		partition p1 engine = innodb,
		partition p2 engine = innodb,
		partition p3 engine = innodb
);
```

- 해시 파티션의 파티션 키 또는 파티션 표현식은 반드시 정수 타입의 값을 반환해야 한다.
- partition 4 → 4 개의 파티션을 생성할 것인지 명시한다.
- 특정 파티션을 삭제하거나 병합하는 작업이 거의 불필요하다

## 13.3.3.3 해시 파티션의 분리와 병합

- 리스트, 레인지와 달리 대상 테이블의 모든 파티션에 저장된 레코드를 재분배하는 작업이 필요하다
    - 파티션의 분리나 병합으로 인해 파티션의 개수가 변경된다는 것은 해시 함수의 알고리즘을 변경하는 것이므로

## 13.3.3.3.1 해시 파티션 추가

```sql
-- // 파티션 1개만 추가하면서 파티션 이름을 부여하는 경우
alter table employees algorithm = inplace, lock = shared,
	add partition(partition p5 engine = innodb);

-- // 동시에 6개의 파티션을 별도의 이름 없이 추가하는 경우
alter table employees algorithm=inplace, lock=shared,
	add partition partitions 6;
```

- mod 로 연산한 결과값으로 파티션을 결정한다
- 모든 레코드가 재배치되어야 한다.
    - 파티션 알고리즘이 변하는 것이므로
    - 따라서 리빌드 작업이 필요하면 테이블에 대한 읽기 잠금이 필요하다
    - 파티션 추가, 생성에 많은 부하를 발생시키며 다른 트랜잭션에서 동일 테이블에 데이터를 변경하는 작업은 허용되지 않는다.

## 13.3.3.3.2 해시 파티션 삭제

- 해시나 키 파티션은 파티션 단위로 레코드를 삭제하는 방법이 없다
    - 레어 남…
    - 어떤 부류의 데이터인지 예측할 수 없으므로…

## 13.3.3.3.3 해시 파티션 분할

- 개수를 늘리는 것만 가능하다.

## 13.3.3.3.4 해시 파티션 병합

```sql
alter table employees algorithm=inplace, lock=shared,
	coalese partition 1;
```

- 해시나 키 파티션은 오직 개수를 줄이는 기능만 가능하다
- 원래 4개였다면 1개를 줄여 3개로 만드는 쿼리
    - 3개로 재구성하게 된다
    - 마찬가지로 다른 트랜잭션에서 데이터를 변경할 수 없다.

## 13.3.3.3.5 해시 파티션 주의사항

- 특정 파티션만 삭제할 수 없다.
- 추가는 단순히 추가하는 것이 아니라 재배치 해야 한다.
- 일반적으로 사용자에게 익숙한 파티션은 리스트나 레인지이다

## 13.3.4 키 파티션

- 해시 파티션과 특성이 거의 같다.
- 해시 파티션은 해시 값을 계산하는 방법을 파티션 키나 표현식에 사용자가 명시한다.
    - 키 파티션은 MySQL 서버가 해시 값의 계산도 수행한다.
    - 정수, 정숫값을 반환하는 표현식뿐만 아니라 대부분의 데이터 타입에 대해 파티션 키를 적용할 수 있다.
    - 파티션 키 값을 MD5 로 해시 값을 계산하고 mod 연산을 통해 각 파티션에 분배한다.

## 13.3.4.1 키 파티션의 생성

```sql
create table k1(
	id int not null,
	name varchar(20),
	primary key(30
)
-- // 괄호의 내용을 비워 두면 자동으로 프라이머리 키의 모든 칼럼이 파티션 키가 됨
-- // 그렇지 않고 프라이머리 키의 일부만 명시할 수도 있음
partition by key()
	partitions 2;

-- // 프라이머리 키나 유니크 키의 칼럼 일부를 파티션 키로 명시적 설정
create table dept_emp (
	emp_no int not null,
	dept_no char(4) not null,
	...
	primary key (dept_no, emp_no)
)
-- // 괄호의 내용에 프라이머리 키나 유니크 키를 구성하는 칼럼 중에서
-- // 일부만 선택해 파티션 키로 설정하는 것도 가능하다.
partition by key(dept_no)
	partitions2;
```

- key 칼럼을 명시적으로 지정할 수도 있고 지정하지 않으면 pk 가 자동으로 할당됨

## 13.3.4.2 키 파티션의 주의사항 및 특이사항

- 키 파티션은 md5 함수를 이용하기 때문에 정수타입이 아니어도 된다.
- 프라이머리 키나 유니크 키를 구성하는 칼럼 중 일부만으로도 파티션 할 수 있다.
- 유니크 키를 파티션 키로 사용할 대 반드시 not null 이어야 한다.
- 해시 파티션에 비해 더 균등하게 분배할 수 있다.

## 13.3.5 리니어 해시 파티션/리니어 키 파티션

- 파티션을 추가하거나 개수를 줄일 때 리빌드되는 단점을 최소화
- 각 레코드 분배를 위해 power-of-two 알고리즘을 이용
    - 다른 파티션에 미치는 영향을 최소화

## 13.3.5.1 리니어 해시 파티션/리니어 키 파티션의 추가 및 통합

- 단순히 mod 가 아니라 power of two 분배 방식을 이용하기 때문에 파티션의 추가나 통합 시 특정 파티션의 데이터에 대해서만 이동 작업을 하면 된다.
    - 나머지 파티션의 데이터는 재분배 대상이 되지 않는다.

## 13.3.6 파티션 테이블의 쿼리 성능

- 얼마나 많은 파티션 프루닝을 할 수 있는지가 관건이다.
    - 꼭 필요한 파티션만을 걸러내는 과정

```sql
create table user(
	user_id bigint not null,
	name varchar(20),
	primary key (id),
	INDEX ix_name (name)
) partition by key() partitions 1024;
```

- 별도로 파티션 되지 않았다면 b-tree 한 번만 룩업 해서 name=’toto’ 를 찾으면 됨
- 근데 내부적으로 1024 개로 쪼개져 있음
    - 따라서 name = ‘toto’ 를 1024 번 해야 됨
    - 파티션의 개수가 작다면 크게 부담되지 않을 수도 있음
    - 근데 테이블이 작아서 부담되지 않는다면 하나의 테이블로 구성해도 부담되지 않을 수 있음…
- 테이블을 10개로 파티션해서 10개 중 주로 1 ~ 3 개 정도의 파티션만 읽고 쓴다면 기능 향상에 도움 됨
- 근데 10개로 파티션하고 파티션된 10개를 균등하게 쓴다면 오히려 오버헤드만 심해진다.
- 대용량 테이블을 10개로 쪼개서 서로 다른 서버에 저장(샤딩)한다면 매우 효율적
    - MySQL 서버의 파티션은 샤딩이 아니다