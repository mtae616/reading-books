# 옵티마이저와 힌트

- 최소한의 비용으로 최적의 실행 계획
    - 테이블의 데이터가 어떤 분포로 저장돼 있는지 통계 정보 참조
    - 최적의 실행 계획 수립

## 개요

### 쿼리 실행 절차

1. 사용자로부터 요청된 SQL 문장을 잘게 쪼개서 MySQL 서버가 이해할 수 있는 수준으로 분리(파스 트리)한다.
    1. SQL 파싱 (SQL 파서 라는 모듈로 처리한다.)
    2. sql 문장이 문법적으로 잘못됐다면 여기서 걸러진다.
    3. 이 단계에서 만들어지는 파스 트리를 가지고 MySQL 서버는 쿼리를 실행한다.
2. SQL 의 파싱 정보(파스 트리)를 확인하면서 어떤 테이블부터 읽고 어떤 인덱스를 이용해 테이블을 읽을지 선택
    1. 불필요한 조건 제거 및 복잡한 연산의 단순화
    2. 여러 테이블의 조인이 있는 경우 어떤 순서로 테이블을 읽을지 결정
    3. 각 테이블에 사용된 조건과 인덱스 통계 정보를 이용해 사용할 인덱스를 결정
    4. 가져온 레코드들을 임시 테이블에 넣고 다시 한번 가공해야 하는지 결정
3. 두 번째 단계에서 결정된 테이블의 읽기 순서나 선택된 인덱스를 이용해 스토리지 엔진으로부터 데이터를 가져온다.

### 옵티마이저의 종류

- DB 서버에서 두뇌와 같은 역할을 담당한다.
- 비용 기반 최적화(CBO, Cost-based optimizer)
    - 현재 사용
    - 각 단위 작업의 비용  정보와 대상 테이블의 예측된 통계 정보를 이용해 실행 계획별 비용을 산출
    - 최소로 소요되는 처리 방식을 선택해 최종적으로 쿼리 실행
- 규칙 기반 최적화 방법(RBO, Rule-based optimizer)
    - 예전에 사용
    - 통계 정보(레코드 건수, 컬럼값의 분포도)를 활용하지 않고 내부적으로 사용하는 우선순위에 따라 실행 계획 수립

## 기본 데이터 처리

- 데이터 정렬, 그루핑 등 기본 데이터 가공 기능

### 풀 테이블 스캔과 풀 인덱스 스캔

- 인덱스를 사용하지 않고 테이블의 데이터를 처음부터 끝까지 읽음
- 다음과 같은 조건에서 풀 테이블 스캔
    - 테이블의 레코드 건수가 너무 작아서 인덱스를 통해 읽는 것 보다 풀 테이블 스캔이 더 빠를 때(일반적으로 테이블 페이지가 1개로 구성된 경우)
    - WHERE 절이나 ON 절에 인덱스를 이용할 수 있는 적절한 조건이 없는 경우
    - 인덱스 레인지 스캔을 사용할 수 있는 쿼리라도 옵티마이저가 판단한 조건 일치 레코드 건수가 너무 많은 경우
- InnoDB 스토리지 엔진은 특정 테이블의 연속된 데이터 페이지가 읽히면 백그라운드 스레드에 의해 리드 어헤드 작업이 자동으로 시작
    - read ahead 는 어떤 영역의 데이터가 앞으로 필요해질 것을 예측해 미리 디스크에서 읽어 버퍼 풀에 가져다 두는 것
    - 풀 테이블 스캔이 실행되면 처음 몇 개의 데이터 페이지는 포그라운드 스레드가 페이지 읽기를 실행하지만
        - 특정 시점부터는 읽기 작업을 백그라운드 스레드로 넘긴다.
        - 백그라운드 스레드가 읽기를 넘겨받는 시점부터 4 ~ 8개 씩 페이지를 읽으면서 수를 증가시킨다.
        - 최대 64개까지
    - innodb_read_ahead_threshold 로 언제 리드 어헤드를 시작할지 임계값을 설정할 수 있음
        - 설정된 값만큼 포그라운드 스레드가 읽으면 백그라운드 스레드를 이용해 버퍼풀로 적재
- 풀 인덱스 스캔도 마찬가지
    - `select count(*) from employees;`
        - 인덱스로만 읽는다.

### 병렬 처리

- 한 쿼리를 여러 스레드에서 처리
    - 당연히 여러개의 쿼리를 여러 스레드에서 처리는 8.0 이전에도 됐음
    - innnodb_parallel_read_thread 설정
- where 조건 없이 단순히 테이블전체 건수를 가져오는 쿼리만 병렬로 처리 ( count(*) )

### ORDER BY 처리 (Using filesort)

- 정렬을 처리하는 방법
    - 인덱스를 이용
    - filesort 라는 별도의 처리를 이용

|  | 장점 | 단점 |
| --- | --- | --- |
| 인덱스 이용 | INSERT, UPDATE, DELETE 쿼리가 실행될 때 이미 인덱스가 정렬돼 있어서 순서대로 읽기만 하면 되므로 매우 빠르다 | INSERT, UPDATE, DELETE 작업 시 부가적인 인덱스 추가/삭제 작업이 필요하므로 느리다.
인덱스 때문에 디스크 공간이 더 많이 필요하다.
인덱스의 개수가 늘어날수록 InnoDB 버퍼 풀을 위한 메모리가 많이 필요하다. |
| filsesort 이용 | 인덱스를 생성하지 않아도 되므로 인덱스를 이용할 때의 단점이 장점으로 바뀐다.
정렬해야 할 레코드가 많지 않으면 메모리에서 Filesort가 처리되므로 충분히 빠르다. | 정렬 작업이 쿼리 실행 시 처리되므로 레코드 대상건수가 많아질수록 응답 속도가 느리다. |
- 무조건 Filsort 정렬을 해야하는 것은 아니다.
- 하지만 다음과 같은 이유로 모든 정렬을 인덱스를 이용하도록 튜닝하기란 거의 불가능하다.
    - 정렬 기준이 너무 많아서 요건별로 모두 인덱스를 생성하는 것이 불가능
    - GROUP BY의 결과 또는 DISTINCT 같은 처리의 결과를 정렬
    - UNION 결과와 같이 임시 테이블의 결과를 다시 정렬
    - 랜덤하게 결과 레코드를 가져와야 하는 경우
- `소트 버퍼`
    - 정렬을 수행하기 위한 메모리 공간
    - 정렬해야할 레코드의 크기에 따라 가변적
        - 최대 사용 가능 공간은 *sort_buffer_size* 라는 변수로 설정
    - 소트 버퍼 메모리 공간은 완료되면 즉시 시스템으로반납
    - 레코드의 건수가 소트버퍼 보다 크다면 레코드를 여러 조각으로 나눠서 처리하는데, 이 과정에서 임시 저장을 위해 디스크 사용 → swap?
    - 멀티 머지
        - 메모리의 소트 버퍼에서 정렬 수행 그 결과로 임시 디스크 기록, 다시 정렬해서 반복적으로 임시 저장
    - I/O 작업 유발 → 많으면 많을 수록 멀티 머지 반복
    - 소트 버퍼 사이즈는 256KB ~ 8MB 사이에서 최적의 성능을 보인다.
        - 필자는 56KB ~ 1MB 사이 추천
        - 소트 버퍼는 세션 메모리
        - 커넥션 마다 갖는다.
        - 케넥션이 많아서 OOME 를 유발할 수도 있음
- `정렬 알고리즘`

- **싱글 패스 single pass**
    - 레코드 전체를 소트 버퍼에 담을지
    - 정렬 키와 레코드 전체를 가져와서 정렬하는 방식, 레코드의 컬럼들은 고정 사이즈로 메모리 저장
    - 정렬 키와 레코드 전체를 가져와서 정렬하는 방식, 레코드의 컬럼들은 가변 사이즈로 메모리 저장
        - 5.7 버전부터 이 방식을 사용
    - 데이터를 읽을 때 정렬에 필요하지 않은 last_name 컬럼까지 전부 읽어서 소트 버퍼에 담고 정렬 수행
    - 더 많은 sort buffer 공간이 필요하다

- **투 패스 two pass**
    - 정렬 기준 컬럼만 소트 버퍼에 담을지
    - 정렬 키와 레코드의 로우 아이디만 가져와서 정렬하는 방식
    - 정렬 대상 컬럼과 PK만 소트 버퍼에 담아서 정렬 수행하고
    - 정렬된 순서대로 다시 PK 로 테이블을 읽어서 컬럼 값을 가져온다.
    - 싱글 패스 정렬 방식 보다 절반 정도의 메모리 크기를 요구한다.

| 정렬 처리 방법 | 실행 계획의 Extra 칼럼 내용 |
| --- | --- |
| 인덱스 사용 | 별도 표기 없음 |
| 조인에서 드라이빙 테이블만 정렬 | "Using filesort” 메시지가 표시됨 |
| 조인에서 조인 결과를 임시 테이블로 저장 후 정렬 | “Using temporary; Using filesort” 메시지가 표시됨 |

- `정렬 처리 방법`
    - 옵티마이저는 인덱스를 이용할 수 있는지 검토한 후 이용할 수 있다면 Filesort 과정 없이 순서대로 읽어서 반환
    - 할 수 없다면 WHERE 조건에 일치하는 레코드를 검색해 정렬 버퍼에 저장하면서 정렬을 처리
    - 정렬 대상 레코드를 최소화하기 위해 다음의 2가지 방법을 사용
        - 조인의 드라이빙 테이블만 정렬한 다음 조인을 수행
            - 가능하다면 이 방법이 효율적
        - 조인이 끝나고 일치하는 레코드를 모두 가져온 후 정렬을 수행
    - `인덱스를 이용한 정렬`
        - 반드시 ORDER BY 에 명시된 칼럼이 제일 먼저 읽는 테이블(조인이 사용된 경우 드라이빙 테이블)에 속하고 ORDER BY 순서대로 생성된 인덱스가 있어야 한다.
        - WHERE 절에 첫 번째로 읽는 테이블의 칼럼에 대한 조건이 있다면 그 조건과 ORDER BY 는 같은 인덱스를 사용할 수 있어야 한다.
        - B-Tree 계열의 인덱스가 아닌 해시 인덱스나 전문 검색 인덱스 등에서는 정렬을 사용할 수 없다.
            - R-Tree 도 사용할 수 없다.
        - 인덱스를 이용해 정렬이 처리되는 경우 이미 인덱스로 정렬이 돼 있기 때문에 순서대로 읽기만 하면 된다.
            - 실제로 MySQL 엔진에서 정렬을 위한 추가 작업을 하지 않는다.
                
                ```sql
                select *
                from employees e, salaries s
                where s.emp_no = e.emp_no
                and e.emp_no between 100002 and 100020
                order by e.emp_no;
                
                -- emp_no 칼럼으로 정렬이 필요한데, 인덱스를 사용하면서 자동으로 정렬이 된다고
                -- 일부러 order by emp_no 를 제거하는 것은 좋지 않은 선택이다.
                select *
                from employees e, salaries s
                where s.emp_no = e.emp_no
                and e.emp_no between 100002 and 100020
                ```
                
        - 부가적인 정렬을 mysql 이 하지 않지만 order by 가 있다고 해도 추가적인 정렬 작업은 하지 않는다.
            - 이미 정렬되어 있으므로
    - `조인의 드라이빙 테이블만 정렬`
        - 첫 번째 테이블의 레코드를 먼저 정렬한 다음 조인을 실행하는 것이 차선책
        - 이 방법으로 정렬이 처리되려면 조인에서 첫 번째로 읽히는 테이블(드라이빙 테이블)의 칼럼만으로 ORDER BY 절을 작성해야 한다.
        
        ```sql
        select *
        from employees e, salaries s
        where s.emp_no = e.emp_no
        and e.emp_no between 100002 and 100010
        order by e.last_name;
        ```
        
        - where 절이 다음 2가지 조건을 갖추고 있기 때문에 옵티마이저는 employees 테이블을 드라이빙 테이블로 선택할 것이다.
            - where 절의 검색 조건 (”emp_no between 100001 and 100010”) 은 employees 테이블의 프라이머리 키를 이용해 검색하면 작업량을 줄일 수 있다.
            - 드리븐 테이블(salaries) 의 조인 컬럼인 emp_no 컬럼에 인덱스가 있다.
        - order by 절에 명시된 칼럼은 pk 와 전혀 연관이 없으므로 인덱스를 이용한 정렬은 불가능하다.
        - 근데 order by 정렬 기준 칼럼이 드라이빙 테이블에 포함된 칼럼임을 알 수 있다.
        - 옵티마이저는 드라이빙 테이블만 검색해서 정렬을 먼저 수행하고 그 결과와 salaries 테이블을 조인한 것이다.
            1. 인덱스를 이용해 “emp_no between 100001 and 100010” 조건을 만족하는 9건을 검색
            2. 검색 결과를 last_name 칼럼으로 정렬을 수행(filesort)
            3. 정렬된 결과를 순서대로 읽으면서 salaries 테이블과 조인을 수행해 86건의 최종 결과를 가져옴
                
    - `임시 테이블을 이용한 정렬`
        - 2개 이상의 테이블을 조인해서 그 결과를 정렬해야 한다면 임시 테이블 필요
        - 항상 조인의 결과를 임시 테이블에 정렬하고 그 결과를 다시 정렬하기 때문에 가장 느림
        
        ```sql
        select *
        from employees e, salaries s
        where s.emp_no = e.emp_no
        and e.emp_no between 100002 and 100010
        order by s.salary;
        ```
        
        - 이번 쿼리에서는 order by 절의 정렬 기준 칼럼이 드리븐 테이블(salaries) 이다.
        - 즉 정렬이 수행되기 전에 salaries 테이블을 읽어야 하므로 이 쿼리는 조인된 데이터를 가지고 정렬할 수 밖에 없다.
            
    - `정렬 처리 성능 비교`
        - limit 은 작업량을 줄이는 역할
        - order by 나 group by 같은 작업은 where 조건을 만족하는 레코드를 limit 건수 만큼만 가져와서는 처리할 수 없다.
        - 조건을 만족하는 레코드를 모두 가져와서 정렬을 수행하거나 그루핑 작업을 실행해야만 비로소 limit 으로 건수를 제한할 수 있다.
        - where 조건이 아무리 인덱스를 잘 활용하도록 해도 order by 나 group by 때문에 쿼리가 느려지는 경우가 자주 발생한다.
            - `스트리밍 방식`
                - 서버 쪽에서 처리할 데이터가 얼마인지에 관계 없이 조건에 일치하는 레코드가 검색될 때마다 바로 클라이언트로 전송해주는 방식
                - 바로바로 전달해주기 때문에 이 쿼리에 limit 조건을 추가하면 전체적으로 가져오는 레코드 건수가 줄기 때문에 마지막 레코드를 가져오기까지의 시간을 줄일 수 있다.
                - 정렬 방법 3가지 중 인덱스를 사용한 정렬 방식이 이에 해당
            - `버퍼링 방식`
                - order by 나 group by 같은 처리는 쿼리의 결과가 스트리밍되는 것을 불가능하게 한다.
                - where 조건에 일치하는 모든 레코드를 가져온 후 정렬하거나 그루핑해서 차례대로 보내야 하기 때문이다.
                - mysql 서버에서는 모든 레코드를 검색하고 정렬 작업을 하는 동안 클라이언트는 아무것도 하지 못한다.
                    - 그렇기 떄문에 스트리밍의 반대인 버퍼링
                - 처리 결과를 다 가져온 다음에 limit 으로 레코드 건수를 줄여서 반납 → MySQL 서버가 해야 하는 작업량에는 그다지 변화가 없다.
                
                ```sql
                select *
                from tb_test t1, tb_test t2
                where t1.col1 = t2.col1
                order by t1.col2
                limit 10;
                ```
                
                - 1 이 100건 2가 1000 건 두 테이블의 전체 결과 1000 건이라는 가정
                
                | 정렬 방법 | 읽어야 할 건수 | 조인 횟수 | 정렬해야할 대상 건수 |
                | --- | --- | --- | --- |
                | 인덱스 사용 | tb_test1 : 1건
                tb_test2 : 10건 | 1번 | 0건 |
                | 조인의 드라이빙 테이블만 정렬 | tb_test1: 100건
                tb_test2 : 10건 | 1번 | 100건
                (tb_test1 테이블의 레코드 건수만큼 정렬 필요) |
                | 임시 테이블 사용 후 정렬 | tb_test1 : 100건
                tb_test2 : 1000건 | 100번
                (tb_test1 테이블의 레코드 건수만큼 조인 발생) | 1000건 |
                - tb_test1 이 드라이빙 되는 경우
                
                | 정렬 방법 | 읽어야 할 건수 | 조인 횟수 | 정렬해야할 대상 건수 |
                | --- | --- | --- | --- |
                | 인덱스 사용 | tb_test2 : 10건
                tb_test2 : 10건 | 1번 | 0건 |
                | 조인의 드라이빙 테이블만 정렬 | tb_test2: 1000건
                tb_test1 : 10건 | 10번 | 1000건
                (tb_test2 테이블의 레코드 건수만큼 정렬 필요) |
                | 임시 테이블 사용 후 정렬 | tb_test1 : 1000건
                tb_test2 : 100건 | 1000번
                (tb_test2 테이블의 레코드 건수만큼 조인 발생) | 1000건 |
                - tb_test2 이 드라이빙 되는 경우
                - 조인의 드라이빙 테이블만 정렬 방법만 사용해도 괜찮음

## GROUP BY 처리

- group by 또한 order by 와 같이 쿼리가 스트리밍 되지 않게 하는 처리 중 하나
- having 은 group by 의 필터링 역할
- group by 에 사용된 조건은 인덱스를 사용해서 처리될 수 없으므로 having 절을 튜닝하려고 인덱스를 생성하거나 할 필요 없음
- `인덱스 스캔을 이용하는 GROUP BY(타이트 인덱스 스캔)`
    - order by 의 경우와 마찬가지로 조인의 드라이빙 테이블에 속한 칼럼만 이용해 그루핑 할 때 group by 칼럼으로 이미 인덱스가 있다면 그 인덱스를 차례대로 읽으면서 그루핑 작업을 수행하고 조인을 처리
    - 인덱스를 사용해서 처리하더라고 aggregation function 등의 그룹값을 처리해야 해서 임시 테이블이 필요할 수도 있음
- `루스 인덱스 스캔을 이용하는 group by`
    - 레코드를 건너뛰면서 필요한 부분만 가져오는 것

    ```sql
    SELECT emp_no
    FROM salaries
    where from_date='1985-03-01'
    group by emp_no;
    ```

    1. (emp_no, from_date) 인덱스를 차례대로 스캔하면서 emp_no 의 첫번째 유일한 값 “10001” 을 찾아낸다
    2. (emp_no, from_date) 인덱스에서 emp_no 가 ‘10001’인 것 중에서 from_date 값이 1985-03-01 인 애들만 가져옴
    3. (emp_no, from_date) 인덱스에서 emp_no 의 그 다음 유니크한(그룹 키)값을 가져온다.
    4. 3번 단계에서 결과가 더 없으면 처리를 종료하고 결과가 있다면 2번 과정으로 돌아가서 반복 수행
    - 루스 인덱스 스캔은 유니크한 값이 적을 수록 속도 향상
- `임시 테이블을 사용하는 GROUP BY`
    - 인덱스를 전혀 사용하지 못할 때
    - group by 절의 칼럼들로 구성된 유니크 인덱스를 가진 임시 테이블을 만들어서 중복 제거와 집함 함수 연산을 수행

## DISTINCT 처리

- 유니크한 값만 조회할 경우
- 인덱스를 사용하지 못할 때는 항상 임시 테이블이 필요하지만 Using temporary (Extra column임시 테이블 사용) 메시지가 출력되지 않는다.
- **distinct 처리**
    - `SELECT DISTINCT`
        - 유니크한 레코드만 가져올 때
        - group by 와 동일한 방식으로 처리된다.
        - 다음 두 쿼리는 같은 작업 수행
            - SELECT DISTINCT emp_no from salaries;
            - select emp_no from salaries group by emp_no;
        - 특정 칼럼이 아닌 레코드를 유니크하게 select
            - select distinct first_name, last_name from employees
                - 위의 쿼리는 (first_name, last_name) 조합 전체가 유니크한 레코드를 가져오는거다
            - sselect distinct(first_name), last_name from employess
                - 얼핏 보면 first_name 만 distinct 로 조회할 것 같지만 내부적으로는 괄호를 삭제한다.
    - `집합 합수와 함께 사용된 DISTINCT`
        - count, min, max 같은 집합 함수 내에서 distinct 키워드가 사용될 때 일반적으로 select distinct 와 다른 형태로 해석된다.
        - 집합 함수가 없는 select 쿼리에서 distinct 는 조회하는 모든 컬럼의 조합이 유니크한 것들만 가져온다.
        - 하지만 집합 함수 내에서 사용된 distinct 는 집합 함수의 인자로 전달된 칼럼값이 유니크한 것들을 가져온다.
            - select count(distinct s.salary)
              from employees e, salaries s where e.emp_no = s.emp_no
              and e.emp_no between 100001 and 100100;
                - 위 쿼리는 count(distinct s.salary) 를 처리하기 위해 임시 테이블 사용
                - 하지만 임시 테이블을 사용한다는 메시지는 표시되지 않는다.
                - employees 와 salaries 조인한 결과에서 salary 칼럼의 값만 저장하기 위한 임시 테이블을 만든다.
                - 이때 임시 컬럼의 salary 컬럼에는 유니크 인덱스가 생성되기 때문에 느려질 수 있음
            - select count(distinct s.salary), count(distinct e.last_name)
              from employees e, salaries s
              where e.emp_no = s.emp_no
              and e.emp_no between 100001 and 100100;
                - 인덱스된 컬럼에 대해 distinct 처리할 때는 인덱스 풀 스캔 하거나 레인지 스캔 하면서 임시 테이블 없이 최적화된 처리를 수행할 수 있다.
- **내부 임시 테이블 활용**
    - MySQL 엔진이 스토리지 엔진으로부터 받아온 레코드를 정렬하거나 그루핑할 때는 내부적인 임시 테이블(Internal temporary table)을 사용한다.
        - create temporary table 명령으로 만든 임시 테이블과는 다르다
        - 이 때 만들어지는 임시 테이블은 다른 세션이나 다른 쿼리에서는 볼 수 없으며 사용하는 것도 불가능
        - 쿼리의 처리가 완료되면 자동으로 삭제된다.
    - `메모리 임시 테이블과 디스크 임시 테이블`
        - 8.0 부터 메모리는 TempTable 이라는 스토리지 엔진을 사용, 디스크는 InnoDB 스토리지 엔진
            - TempTable → 가변 길이 지원
            - InnoDB → 트랜잭션 지원
    - `임시 테이블이 필요한 쿼리`
        - 다음과 같은 패턴의 쿼리는 내부 임시 테이블을 사용하는 케이스이다
            - order by, group by 명시된 컬럼이 다른 쿼리
            - order by,  group by 명시된 칼럼이 조인의 순서상 첫 번재 테이블이 아닌 쿼리
            - distinct, order by 동시에 쿼리가 존재하는 경우 또는 distinct 가 인덱스로 처리되지 못하는 쿼리
            - union, union distinct 가 사용된 쿼리
            - 쿼리의 실행 계획에서 select_type 이 derived 인 쿼리
        - 마지막 3개는 Using temporary 가 없어도 임시 테이블 사용
    - `임시 테이블이 디스크에 생성되는 경우`
        - 다음과 같은 조건을 만족하면 메모리 임시 테이블을 사용할 수 없어 디스크 기반의 임시 테이블을 사용한다.
            - union, union all 에서 select 되는 컬럼 중 길이가 512 바이트 이상의 크기의 컬럼이 있는 경우
            - group by 나 distinct 컬럼에서 512 바이트 이상인 크기의 컬럼이 있는 경우
            - 메모리 임시 테이블의 크기가 tmp_table_size 또는 max_heap_table_size 시스템 변수보다 크거나 temptable_max_ram 시스템 변수 값보다 큰 경우
    - `임시 테이블 관련 상태 변수`
        - Using temporary
            - 한 번 표시됐다고 해서 하나만 사용한 것은 아니다.
            - 임시 테이블이 디스크에 생성됐는지 확인하려면
                - show session status like ‘created_tmp%’
                - flush status 를 통해 기존의 것을 날리고 확인하자
                - create_tmp_tables → 디스크/메모리 구분하지 않은 임시 테이블 누산
                - created_tmp_disk_table → 디스크만

  ## 고급 최적화

    - 통계 정보 + 옵티마이저 옵션 → 최적의 수행 결과
    - 옵티마이저 옵션
        - 조인 관련된 옵티마이저 옵션
            - MySQL 초기 버전부터
        - 옵티마이저 스위치
            - 5.5 부터 지원
            - 고급 최적화 기능들을 활성화할지 제어
    - **옵티마이저 스위치 옵션**

        - 옵티마이저 스위치 옵션은 시스템 변수를 이용해서 제어하는데, 시스템  변수는 여러 옵션을 세트로 묶어서 설정하는 방식으로 사용한다.
        - default, on, off
            - 기본, 활성화, 비활성화
        - `MRR과 배치 키 인덱스`
            - Multi-Range Read
            - 드라이빙 테이블의 레코드를 한 건 읽어서 드리븐 테이블의 일치하는 레코드를 찾아서 조인 수행
                - 네스티드 루프 조인이라고 한다.
            - 조인 처리는 MySQL 엔진이 하지만 실제 레코드를 검색하고 읽는 부분은 스토리지 엔진이 담당한다.
                - 드라이빙 테이블의 레코드 건별로 드리븐 테이블의 레코드를 찾으면 레코드를 찾고 읽는 스토리지 엔진에서는 아무런 최적화를 수행할 수가 없다.
            - 위와 같은 단점을 보완하기 위해 조인 대상 테이블 중 하나로부터 레코드를 읽어서 조인 버퍼에 버퍼링한다.
                - 드라이빙 테이블의 레코드를 읽어서 드리븐 테이블과의 조인을 즉시 실행하지 않고 조인 대상을 버퍼링
                - 조인 버퍼에 레코드가 가득 차면 MySQL 엔진은 버퍼링된 레코드를 스토리지 엔진으로 한 번에 요청
                - 위와 같은 절차를 통해 disk io 를 줄인다.
        - `block nested loop join`
            - MySQL 서버에서 대부분 nested loop join 으로 수행되는데 조인의 연결 조건이 되는 칼럼에 모두 인덱스가 있는 경우 사용된다.
            - 중첩 반복문 처럼 수행됨
                - 네스티드 루프 조인은 레코드를 읽어서 다른 버퍼 ㅗㄱㅇ간에 저장하지 않고 즉시 드리븐 테이블의 레코드를 찾아서 반환한다.

            ```java
            for(int i = 0; i < employees.length; i++) {
            	for(int j = 0; j < salaries.length; j++) {
            		if (condition_matched()) return...
            	}
            }
            ```

            - 네스티드 루프 조인과 블록 네스티드 루프 조인의 차이점
                - 조인 버퍼 사용 여부
                    - 조인 쿼리의 실행 계획에 Using join buffer 라는 문구가 표시되면 조인 버퍼를 사용한다는 것
                    - 2중 반복문과 같이 작동하므로 드리븐 테이블의 풀스캔이 너무 잦음
                    - 따라서 최대한 인덱스를 사용하지만 그럴 수 없을 때 트라이빙 테이블에서 읽은 레코드를 메모리에 캐시 → 조인 버퍼
                    - 조인이 완료된 후 조인 버퍼는 바로 해제된다.
                - 드라이빙 테이블과 드리븐 테이블이 어떤 순서로 조인되는지
            - MySQL 8.0.18 부터 hash join 알고리즘이 도입되면서 이 방식은 더이상 사용되지 않는다.
        - `index condition puschdown`

            ```java
            Alter table employees add index ix_lastname_firstname (last_name, first_name);
            
            select * from employees where last_name='Acton' and first_name like '%sal';
            ```

            - 위의 select 쿼리에서 index_condition_pushdown 을 off 로 설정하면 last_name 을 기준으로 인덱스를 타고 first_name like ‘%sal’ 조건은 직접 접근해서 검사
            - index_condition_pushdown 을 on 으로 설정하면 index 내에서 비교를 수행하고 비교한 대상 레코드에만 접근해서 데이터를 가져온다.
                - 실행 계획에 Using index condition 이라고 표시된다.
        - `인덱스 확장 use_index_extensions`
            - 세컨더리 인덱스에 자동으로 추가된 pk를 활용할 수 있게 할지에 대한 여부
            - InnoDB 엔진에서 세컨더리 인덱스는 데이터 레코드를 찾아가기 위해 pk 포함
            - pk 가 a 이고 세컨더리 인덱스가 b 로 되어 있을 때 실제로 세컨더리 인덱스는 (b, a) 로 작동한다.
        - `인덱스 머지 index_merge`
            - 인덱스 머지 실행 계획을 사용하면 하나의 테이블에 대해 2개 이상의 인덱스를 이용해 쿼리 처리
            - 하나의 인덱스를 사용해서 처리하는 것이 효율적이지만 각각의 조건이 다른 인덱스를 사용할 수 있고 조건을 만족하는 레코드 건수가 많을 것으로 예상될 때 인덱스 머지 실행 계획을 세움
            - 3개 의 실행 조건
                - index_merge_intersection
                - index_merge_union
                - index_merge_sort_union
        - `인덱스 머지 - 교집합 index_merge_intersection`
            - 여러 개의 인덱스를 각각 검색해서 그 결과의 교집합을 반환
            - 실행계획의 Extra 컬럼에 Using intersect 로 표시됨

            ```java
            select * from employees where first_name='George' and emp_no between 10000 and 20000;
            ```

            - 각 조건에 index 가 걸려있고, 레코드가 너무 많으면 각각 검색해서 교집합 하는 방식이 빠를 수도 있음
                - index merge 가 아니었다면
                    - Georgi 인 컬럼을 모두 가져와 조건에 일치하는 레코드를 반환
                    - emp_no 만 인덱스가 걸려있다면 emp_no 를 가져와 georgi 인 조건을 반환
                    - first_name 가 253 건이고 emp_no 10000건일 때 결과가 14건이면
                        - 256, 10000 전부 다 읽은 다음에 버려야됨
                - index merge 라면
                    - 두개 각각 일치하는 레코드 가져오고 교집합 때림
        - `인덱스 머지 - 합집합 index_merge_union`
            - 인덱스를 각각 검색해서 결과의 합집합을 반환
            - 실행 계획에 Using union 으로 표시됨

            ```java
            select * from employees where first_name='Matt' or hire_date='2022-07-13';
            
            ```

            - first_name, hire_date 가 각각 index 가 걸려있다면 using union 을 사용할 수 있다.
            - emp_no 가 세컨더리 인덱스에 포함이므로 emp_no 를 사용하여 중복 걸러낸다 (priority queue)
        - `인덱스 머지 - 정렬 후 합집합 index_merge_sort_union`
            - 인덱스 머지 작업 도중 sorting 이 필요하면 sort union 알고리즘을 사용하여 정렬
            - extra column 에 Using sort_union 문구로 표시
        - `세미 조인 semijoin`
            - 다른 테이블과 실제 조인을 하지는 않고 다른 테이블에서 조건에 일치하는 레코드가 있는지 없는지만 체크
        - `테이블 풀 아웃 table pull-out`
            - 세미 조인의 서브쿼리에 사용된 테이블을 아우터 쿼리로 끄집어낸 후에 쿼리를 조인 쿼리로 재작성하는 형태
                - In(subquery) 형태의 세미 조인이 가장 빈번하게 사용
            - 실행 계획에 아무 것도 표시되지는 않음
                - show warning 로 재작성한 형태를 살펴보면 join 으로 확인됨
        - `퍼스트 매치 first match`
            - IN(subquery) 형태의 세미 조인을 EXISTS(subquery) 형태로 튜닝한 것과 비슷한 방법
            - extra 컬럼에 firstmatch() 문구 출력됨
        - `루스 스캔 loosescan`
            - Loose index scan 과 비슷한 읽기 방식을 사용한다.
        - `구체화 Materialization`
            - materialization 최적화는 세미 조인에 사용된 서브 쿼리를 통째로 구체화해서 쿼리를 최적화한다.
                - 내부에 임시 테이블을 생성
            - optimizer_switch 시스템 변수에서 semijoin, materialization 옵션이 모두 on 인 경우에 활성화
        - `중복 제거 Duplicated Weed-out`
            - 세미 조인 서브쿼리를 일반적인 inner join 쿼리로 바꾸고 마지막에 중복된 레코드를 제거

            ```sql
            select * from employees e
            where e.emp_no in (select s.emp_no from salaries s where s.salary > 150000);
            ```

            - 아래와 같이 변환

            ```sql
            select e.*
            from employees e, salaries s
            where e.emp_no = s.emp_no and s.salary>150000
            group by e.emp_no;
            ```

        - `컨디션 팬아웃 condition_fanout_filter`
            - 여러 테이블이 조인되는 경우 가능하다면 일치하는 레코드 건수가 적은 순서대로 조인 실행
            - 실행 계획에는 쿼리 실행시 읽게 될 rows 의 갯수와 실행 결과 rows 의 비율인 filtered 컬럼이 있다
                - rows * filtered / 100 이 쿼리 실행 결과 나오게 될 rows 개수
            - 옵티마이저는 conditinon_fanout_filter 최적화 기능을 활성화하여 보다 정교한 계산 가능
                - where 조건절에 사용된 컬럼에 인덱스가 있는 경우
                - where 조건절에 사용된 컬럼에 히스토그램이 존재하는 경우
        - `파생 테이블 머지 derived_merge`
            - 파생 테이블 (from 절에 사용된 서브 쿼리)로 만들어지는 서브쿼리를 외부 쿼리와 병합해서 서브쿼리 부분을 제거하는 최적화
                - 임시 테이블의 크기가 크다면 disk io 발생 (메모리에 있다가 disk 에 써야함)
            - derived_merge 최적화 옵션을 통해 이러한 최적화를 활성화할지에 대한 여부 가림

            ```sql
            select * from (
            	select * from employees where first_name='Matt'
            ) dervied_table
            where derived_table.hire_date='2022-07-01'
            ```

            - from 절에 사용된 게 파생테이블
            - show warnings 명령으로 옵티마이저가 작성한 쿼리를 보면

            ```sql
            select *
            from employees
            where employees.hiredate = '2022-07-01' 
            and employees.first_name = 'Matt'
            ```

        - `인비저블 인덱스 use_invisible_indexes`
            - 인덱스의 가용 상태를 제어할 수 있는 기능
                - 이를 통해 인덱스를 삭제하지 않고 해당 인덱스를 사용하지 못하게 제어할 수 있음
        - `스킵 스캔 skip scan`
            - 인덱스의 순서 (a, b, c) 로 구성된 때 b, c 컬럼에 대한 조건만 가지고 있다면 인덱스 활용할 수 없다.
            - skip scan 은 b, c 만을 가지고도 인덱스 스킵 스캔을 도운다
                - 단, 선행 컬럼이 소수의 유니크한 값을 가질 때
        - `해시 조인 hash_join`
            - nested loop join 보다 항상 빠르지는 않다.

            - hash join 이 먼저 끝나긴 했음
            - A 가 쿼리 실행되며 첫 번째 레코드를 찾은 시점, B 가 마지막 레코드를 찾은 시점
                - nl join 이 첫번째 레코드를 찾는 것은 빨랐지만 마지막 레코드를 찾는 것은 느렸음
                - hash join 은 첫 번째는 느렸지만 마지막은 빨랐음
                - 즉 해시 조인은 최고 스루풋 best throughput 전략에 적합
                    - 분석과 같은 서비스
                - nl join 은 최고 응답 속도 best response-time 전략에 적합
                    - 웹 서비스
                - 단 MySQL 은 범용이므로 데이터 분석과 같은 것에 잘 쓰이지 않으니 응답 속도가 빠른 것이 나음
                - 따라서 hash join 은 nl join 이 사용되기 적합하지 않은 경우를 위한 차선책
            - 단계
                - 빌드 단계
                    - 조인 대상 테이블 중 레코드 건수가 적어 해시 테이블로 만들기에 용이한 테이블을 골라 메모리에 해시 테이블을 생성한다
                - 프로브 단계
                    - 나머지 테이블의 레코드를 읽어서 해시 테이블의 일치 레코드를 찾는다
        - `인덱스 정렬 선호 prefer_ordering_index`
            - MySQL 옵티마이저는 ORDER BY 또는 GROUP BY 를 인덱스를 사용해 처리 가능한 경우 쿼리의 실행 계획에서 이 인덱스의 가중치를 높이 설정해서 실행한다.
                - 옵티마이저의 이런 가중치 부여하지 않게 하기 위해 prefer_ordering_index 옵션이 추가되었다

## 조인 최적화 알고리즘

- 다음 2가지의 조인 최적화 알고리즘이 있다.
- `Exhaustive 검색 알고리즘`
    - 5.0 이전에 사용되던 조인 최적화 기법
    - FROM 절에 명시된 모든 테이블의 조합에 대해 실행 계획의 비용 계산 → 최적의 조합 찾음
    - 테이블이 N 개라면 N! 개
- `Greedy 검색 알고리즘`
    - 5.0 부터 도입

## 쿼리 힌트

- 실행 계획을 어떻게 수립해야 할지 알려줄 수 있는 방법
- 2가지로 구분
    - **인덱스 힌트**
        - ANSI 표준을 준수하지 못한다
            - 5.6 부터는 준수하며 주석으로 해석된다.
            - 따라서 인덱스 힌트보다 옵티마이저 힌트 사용 권장
        - `STRAIGHT_JOIN`
            - 힌트인 동시에 조인 키워드
            - 어떤 테이블이 드라이빙 테이블이 되고 어느 테이블이 드리븐 테이블이 될지 알 수 없다.
            - straight_join 은 from 절에 명시된 테이블의 순서대로 조인을 수행하도록 유도
                - 임시 테이블이 드라이빙 테이블로 되는 것이 좋거나 혹은 인덱스가 있는 일반 테이블
            - 비슷한 옵티마이저 힌트
                - JOIN_FIXED_ORDER
                - JOIN_ORDER
                - JOIN_PREFIX
                - JOIN_SUFFIX
        - `USER INDEX / FORCE INDEX / IGNORE INDEX`
            - 인덱스 힌트는 사용하려는 인덱스를 가지는 테이블 뒤에 힌트를 명시
            - 3 ~ 4개 이상의 컬럼을 포함하는 비슷한 인덱스가 여러 개 존재하는 경우에 가끔 옵티마이저가 실수 할 수 있으므로 강제로 특정 인덱스를 사용하도록 한다
            - 인덱스 힌트는 3가지
                - USE INDEX
                    - 옵티마이저에게 특정 테이블의 인덱스를 사용하도록 권장하는 힌트
                - FORCE INDEX
                    - USE INDEX 와 비슷하지만 더 강하게 요구하는 힌트 (거의 사용하지 않음)
                - IGNORE INDEX
                    - 특정 인덱스를 사용하지 못하게 하는 힌트
            - 인덱스 용도 명시
                - 특정 용도로 사용할 수 있게 제한한다.
                    - USE INDEX FOR JOIN
                    - USE INDEX FOR ORDER BY
                    - USER INDEX FOR GROUP BY
        - `SQL_CALC_FOUND_ROWS`
            - MySQL LIMIT 을 사용하는 경우 조건을 조건을 만족하는 레코드가 limit 에 명시된 수 보다 더 많다고 하더라고 limit 에 명시된 수만큼 만족하는 레코드를 찾으면 즉시 검색 멈춤
            - SQL_CALC_FOUND_ROWS 를 명시하면 멈추지 않고 끝까지 검색 수행
            - 되도록이면 사용하지 않는 것을 권장
    - **옵티마이저 힌트**
        - 옵티마이저 힌트 종류
            - 인덱스
                - 특정 인덱스의 이름을 사용할 수 있는 옵티마이저 힌트
            - 테이블
                - 특정 테이블의 이름을 사용할 수 있는 옵티마이저 힌트
            - 쿼리 블록
                - 특정 쿼리 블록에 사용할 수 있는 옵티마이저 힌트
            - 글로벌
                - 전체 쿼리에 대해서 영향을 미치는 힌트
        - `MAX_EXECUTION_TIME`
            - 쿼리 실행 계획에 영향을 미치지 않음
            - 단순히 쿼리의 최대 실행 시간을 설정
                - 지정된 시간을 조과하면 ERROR 3024 발생
        - `SET_VAR`
            - 실행 계획을 바꾸는 용도뿐만 아니라 조인 버퍼나 정렬용 버퍼(소트 버퍼)의 크기를 일시적으로 증가시켜 대용량 처리 쿼리의 성능을 향상
        - `SEMIJOIN & NO_SEMIJOIN`
            - 세미 조인 최적화 힌트는 외부 쿼리가 아니라 서브 쿼리에 명시해야 한다.
            - SEMIJOIN( 전략 )
                - 전략
                    - duplicate weed-out
                    - first match
                    - loose scan
                    - materialization
                    - table pull-out → 별도로 힌트를 사용할 수 없음
        - `SUBQUERY`
            - 서브쿼리 최적화는 세미 조인 최적화가 사용되지 못할 때 사용
            - 2가지 형태
                - SUBQUERY( 전략 )
                    - 전략
                        - IN-to-EXISTS
                        - Materialization
            - 세미 조인 최적화는 주로 IN(subquery)형태의 쿼리에 사용될 수 있지만 안티 세미 조인의 최적화에는 사용될 수 없다.
            - 주로 안티 세미 조인 최적화에는 위의 2가지 최적화가 사용된다.
        - `BNL & NO_BNL & HASHJOIN & NO_HASHJOIN`
            - bnl → hash join
            - bnl 과 no bnl 힌트로 여전히 사용 가능하다
        - `JOIN_FIXED_ORDER & JOIN_ORDER & JOIN_PREFIX & JOIN_SUFFIX`
            - 순서 결정에 straight_join 힌트를 사용해왔다
                - straight_join 은 from 에 순서대로 작성해야 하는 번거로움
                - 또한 한번 사용되면 from 절에 명시된 모든 테이블의 조인 순서가 결정되기 때문에 일부는 조인 순서를 강제하고 일부는 옵티마이저에게 순서를 결정하게 맡기는 것이 불가능
            - 위의 단점을 보완
                - join_fixed_order
                    - straight join 힌트와 동일
                - join_order
                    - from 절에 사용된 테이블의 순서가 아니라 힌트에 명시된 테이블의 순서대로 조인 수행
                - join_prefix
                    - 드라이빙 테이블만 강제
                - join_suffix
                    - 드리븐 테이블만 강제
        - `MERGE & NO_MERGE`
            - from 절에 사용된 서브 쿼리를 내부 임시 테이블로 생성할지 외부 쿼리와 병합하는 최적화를 수행할지 정하는 힌트
        - `INDEX_MERGE & NO_INDEX_MERGE`
            - 하나의 테이블에 여러 개의 인덱스를 동시에 사용하는 것
            - 각각의 인덱스에 대해 교집합 또는 합집합을 사용할 하여 반환하는 것이 나을 때 사용
        - `NO_ICP`
            - index condition pushdown 은 항상 성능 향상에 도움 되므로 실행 계획 수림
                - icp 힌트는 제공되지 않는다.
            - 다만 icp 가 효율적이지 못하다는 판단이 있을 때 힌트 사용
        - `SKIP_SCAN & NO_SKIP_SCAN`
            - 인덱스 스킵 스캔은 인덱스의 선행 칼럼에 대한 조건이 없어도 옵티마이저가 해당 인덱스를 사용할 수 있도록 해주는 최적화 기능
                - 하지만 선행 컬럼이 가지는 유니크한 값의 개수가 많아지면 인덱스 스킵 스캔은 오히려 성능 저하
            - MySQL 옵티마이저가 유니크한 값의 개수를 잘 분석하지 못하거나 비효율적인 인덱스 스킵 스캔을 사용하면 이를 해결하기 위한 힌트
        - `INDEX & NO_INDEX`
            - INDEX 와 NO_INDEX 옵티마이저 힌트는 MySQL 서버에서 이전에 사용되던 인덱스 힌트를 대체하는 용도
            - 인덱스 힌트는 특정 테이블 뒤에 사용했기 때문에 별도로 힌트 내에 테이블명 없이 인덱스 이름만 나열햇지만 옵티마이저 힌트에는 테이블명과 인덱스 이름을 함께 명시해야 한다.

        ```sql
        -- // 인덱스 힌트 사용
        select *
        from employees USE INDEX(ix_firstname)
        where first_name='Matt';
        
        -- // 옵티마이저 힌트 사용
        select /*+ INDEX(employees ix_firstname) */ *
        from employees
        where first_name='Matt';
        ```