# 쿼리 작성 및 최적화

- SQL 은 어떠한 데이터를 요청하기 위한 언어이지 어떻게 데이터를 읽을지를 표현하는 언어는 아니다.
- C, JAVA 와 같은 언어와 비교했을 때 제한적으로 느껴질 수 있다.
- 따라서 쿼리가 빠르게 수행되려면 어떻게 요청을 처리할지 예측해야 한다.

## 11.1 쿼리 작성과 연관된 시스템 변수

- MySQL 서버의 시스템 설정이 쿼리에 어떤 영향을 주는지

## 11.1.1 SQL 모드

- sql_mode 라는 시스템 설정에는 여러 개의 값이 동시에 설정될 수 있다.
- STRICT_ALLTABLES & STRICT_TRANS_TABLES
    - 서버에서 insert, update 문장으로 데이터를 변경하는 경우 칼럼의 타입과 저장되는 값의 타입이 다를 때 자동으로 타입 변경을 수행
    - 이때 타입이 변환되기 어렵거나, 값이 없거나 길이가 큰 경우 이 옵션을 통해 에러를 발생시킬지 결정
- ANSI_QUOTES
    - 리터럴을 홑따옴표와 쌍따옴표를 동시에 쓸 수 있다.
    - 오라클에서는 홑따옴표 → 문자열 값, 쌍따옴표 → 컬럼명, 테이블명과 같은 식별자(identifier)
    - 이 옵션을 설정하면 오라클과 같이 설정된다.
- ONLY_FULL_GROUP_BY
    - MySQL 에서는 group by 절에 포함되지 않은 컬럼이라도 집합 함수의 사용 없이 그대로 select, having에 쓸 수 있다.
        - 다른 DBMS 에서는 안됨
    - 8.0 부터는 이 옵션 설정돼 있어서 MySQL 에서도 안됨
- PIPE_AS_CONCAT
    - || 를 concat 으로 사용
- PAD_CHAR_TO_FULL_LENGTH
    - 유효 문자열 뒤의 공백을 제거하지 않고 반환한다.
- NO+BACKSLASH_ESCAPE
    - \ ≠ escape, \ 를 다른 문자와 동일하게 취급
- IGNORE_SPACE
    - 함수 뒤에 공백이 있으면 스토어드 함수가 없다는 에러가 출력될 수도 있는데, 이 옵션을 키면 무시됨
- REAL_AS_FLOAT
    - FLOAT, DOUBLE
    - default 는 real == double
    - 이 옵션을 키면 real == float
- NO_ZERO_IN_DATE & NO_ZERO_DATE
    - datetime 칼럼에 “0000-00-00” 과 같은 잘못된 날짜 저장 불가
- ANSI
    - SQL 표준에 맞게 구성
    - real_as_float + pipes_as_concat + ansi_quotes + ignore_apce + only_full_group_by
- TRADITIONAL
    - 아주 엄격하게 SQL 작동 제어
    - STRICT_TRANS_TABLES + STRICT_ALL_TABLES + NO_AERO_IN_DATE + NO_ZERO_DATE …

## 11.1.2 영문 대소문자 구분

- MySQL 은 설치된 운영체제에 따라 테이블명의 대소문자를 구분한다.
- 윈도우는 대소문자를 구분하지 않지만, 유닉스 계열은 구분한다.
- lower_case_table_names 를 설정하면 모두 소문자로 저장되고 대소문자 구분하지 않는다.

## 11.1.3 MySQL 예약어

- 예약어와 같은 키워드는 역따옴표나 쌍따옴표로 감싸야한다.

## 11.2 매뉴얼의 SQL 문법 표기를 읽는 방법

- p.6 참조

## 11.3 MySQL 연산자와 내장 함수

- MySQL 에서만 사용되는 연산자나 표기법이 있다.
    - ANSI 표준이 아닌 연산자

## 11.3.1 리터럴 표기법 문자열

## 11.3.1.1 문자열

- 홑따옴표를 사용하는 게 표준
- 하지만 MySQL 에서는 쌍따옴표를 사용해 문자열을 표기할 수도 있다.

```sql
SELECT * FROM departments WHERE dept_no 'd''001';
SELECT * FROM departments WHERE dept_no 'd"001';
SELECT * FROM departments WHERE dept_no "d'001";
SELECT * FROM departments WHERE dept_no "d""001";
```

- 3, 4 번째는 MySQL 에서만 지원하는 표기방법
- 또한 ansi 표준에서는 역따옴표로 예약어와 충돌을 피하는데 mysql 에서는 쌍따옴표를 사용해야 함

## 11.3.1.2 숫자

- 문자열 형태를 사용하더라도 칼럼 타입이 숫자면 숫자로 자동으로 변환한다.

```sql
SELECT * FROM tab_test WHERE number_column = '10001';
SELECT * FROM tab_test WHERE string_column = 10001;
```

- 첫 쿼리는 숫자로 자동 변환하지만
- 두번째는 string_column 칼럼의 모든 문자열 값을 숫자로 변환해서 비교함
    - 따라서 string_column 에 index 가 걸려 있더라도 활용하지 못한다.
    - 또한 string_column 에 알파벳이 포함된 경우 쿼리 자체가 실패한다.

## 11.3.1.3 날짜

- 정해진 형태의 날짜 포맷으로 표기하면 서버가 자동으로 변환하기 때문에 str_to_date 같은 함수를 이용하지 않아도 된다.

## 11.3.1.4 불리언

- bool, bollean 은 tinyint 타입에 대한 동의어
- true, false 는 그냥 1, 0 으로 비교

## 11.3.2 MySQL 연산자

## 11.3.2.1 동등(Equal) 비교(=, ≤>)

- = 기호를 사용해 비교
- MySQL에서는 동등 비교를 위해 “<=>” 도 지원
    - = 과 같다
    - 부가적으로 NULL 에 대한 비교도 수행
    - NULL <=> NULL 에 대해 1을 반환한다.
    - 혹은 1 <=> NULL 에 대해 0 을 반환한다.
    - NULL 인지 아닌지 판단 → NULL SAFE

## 11.3.2.2 부정(Not-Equal) 비교 (<>, ≠)

- 같지 않다

## 11.3.2.3 NOT 연산자(!)

- C/C++ 의 ! 과 같은 목적으로 사용한 수 있다.

## 11.3.2.4 AND(&&)와 OR(||) 연산자

- AND == &&
- OR == ||
    - 오라클은 || 을 concat 으로 사용
    - mysql 도 pipe_as_concat 을 설정하면 가능

## 11.3.2.5 나누기(/, DIV)와 나머지 (%, MOD) 연산자

- C/C++ 과 같다.

## 11.3.2.6 REGEXP 연산자

- 정규표현식과 같다.
    - POSIX 표준
- RLIKE == REGEXP

## 11.3.2.7 LIKE 연산자

- 단순한 문자열 패턴 비교

## 11.3.2.8 BETWEEN 연산자

- 크거나 같다, 작거나 같다를 합친 연산자
- BEWETEEN 은 크다 작다 비교를 하나로 묶어둔 것에 가깝고
- IN 연산은 동등 비교 연산자와 비슷하다

```sql
-- dept_no, emp_no 로 pk
-- 아래의 쿼리는 전체 데이터의 33% 를 읽음 하지만 실제로 가져오는 데이터는 1건
-- explain 에 rows 가 165571
select * from dept_emp
where dept_no between 'd003' and 'd005' and emp_no = 1001;

-- 아래처럼 바꾸는 equal 비교를 여러번 수행하는 것 같은 효과
-- explain 에 rows 가 3
select * from dept_emp
where dept_no in ('d003', 'd004', 'd005')
and emp_no = 1001;

-- 위의 두 쿼리 다 index range scan 이지만 큰 차이가 있다.
-- 첫 번째는 d003 ~ d005 레코드의 전체 범위를 다 비교해야하지만
-- in 을 사용한 쿼리는 (d003, 1001), (d004, 1001), (d005, 1001) 조합인 레코드만 비교하면 되기 때문에
```

## 11.3.2.9 IN 연산자

- 여러 개의 값에 대해 동등 비교 연산을 수행하는 연산자
- 범위로 검색하는 것이 아니라 여러 번의 동등 비교로 실행되기 때문에 전반적으로 빠르게 처리된다.
- 구분해서 생각
    - 상수가 사용된 경우 IN(?, ?, ?)
    - 서브 쿼리가 사용된 경우 IN (SELECT… FROM…)
- 상수가 사용된 경우는 매우 빠르게 처리
- 8.0 이전 버전까지는 IN 절에 튜플을 사용하면 항상 테이블 풀 스캔

```sql
SELECT *
FROM dept_emp
WHERE (dept_no, emp_no) IN (('d001', 10017), ('d002', 10144), ('d003', 10054));
```

- IN 절에 튜플 사용
- 8.0 부터는 IN 절에 튜플을 그대로 나열해도 index 를 최적으로 사용
    - 세미 조인의 최적화
- NOT IN 은 인덱스 풀 스캔

## 11.3.3 MySQL 내장 함수

- 기본함수
- 사용자 정의 함수
    - C/C++ API 사용해서 추가할 수 있음
- 스토어드 프로그램, 프로시저, 함수와는 다름

## 11.3.3.1 NULL 값 비교 및 대체(IFNULL, ISNULL)

- IFNULL
    - 첫번째 인자 NULL이면 두번째 인자로 대체
- ISNULL
    - NULL 인지 아닌지 판단

## 11.3.3.2 현재 시각 조회(NOW, SYSDATE)

- 두개 다 같은 기능
- 다만 다른 동작 방식
    - 하나의 SQL 에서 모든 NOW 는 같은 값을 가지지만
    - SYSDATE 는 호출 시점에 따라 달라진다.
        - SYSDATE 가 사용된 SQL 은 레플리카 서버에서 안정적으로 복제되지 못한다.
        - SYSDATE 와 비교되는 칼럼은 인덱스를 효율적으로 사용하지 못한다.
            - 호출될 때마다 다른 값을 반환하므로 상수가 아니라서
            - 인덱스 스캔을 할 때에도 매번 비교되는 레코드마다 함수를 실행해야 함

## 11.3.3.3 날짜와 시간의 포맷(DATE_FORMAT, STR_TO_DATE)

- 원하는 포맷으로 변경할 때

## 11.3.3.4 날짜와 시간의 연산(DATE_ADD, DATE_SUB)

- 연도나 월일 또는 시간 등을 더하거나 뺄 때

## 11.3.3.5 타임스탬프 연산(UNIX_TIMESTAMP, FROM_UNIXTIME)

- UNIX_TIMESTAMP 1970-01-01 00000 부터 계산한거
- FROM_UNIXTIME 은 인자로 전달한 타임스탬프 값을 datetime 으로 바꿈

## 11.3.3.6 문자열 처리(RPAD, LPAD / RTRIM, LTRIM, TRIM)

- RPAD, LPAD 는 좌측, 우측에 문자열을 덧붙여서 지정된 길이의 문자열로 만드는 함수
- RTRIM, LTRIM 은 우측 또는 좌측에 연속된 공백 문자 제거

## 11.3.3.7 문자열 결함(CONCAT)

- 여러 개의 문자열 연결해서 하나의 문자열로 반환

## 11.3.3.8 GROUP BY 문자열 결함(GROUP_CONCAT)

- count, max, min, avg 등과 같은 그룹 함수 중 하나
- 특정 컬럼을 붙일 수 있음

## 11.3.3.9 값의 비교와 대체(CASE WHEN … THEN … END)

## 11.3.3.10 타입의 변환(CAST, CONVERT)

- SQL 에 포함된 모든 입력값을 문자열 취급
- 이럴 때 명시적으로 타입의 변환이 필요하면 CAST 를 사용한다.

## 11.3.3.11 이진값과 16진수 문자열(Hex String) 변환(HEX, UNHEX)

## 11.3.3.12 암호화 및 해시 함수(MD5, SHA, SHA2)

- 각 spec 마다 해싱

## 11.3.3.13 처리 대기(SLEEP)

- 프로그래밍 언어에서 사용하는 sleep 과 동일

## 11.3.3.14 벤치마크(BENCHMARK)

- 성능 테스트용
- 첫 번째 인자는 반복 수행할 횟수, 두번째는 실행할 표현식

## 11.3.3.15 IP 주소 변환(INET_ATON, INET_NTOA)

- ip주소를 . 으로 구분해서 저장
- 각 함수를 이용하면 IPv4 주소를 문자열이 아닌 부호 없는 정수 타입에 저장할 수 있게 제공
- INET6_ATON, INET6_NTOA 는 ipv6

## 11.3.3.16 JSON 포맷 (JSON_PRETTY)

- json 칼럼의 값을 읽기 쉬운 포맷으로 변환

## 11.3.3.17 JSON 필드 크기 (JSON_STORAGE_SIZE)

- 바이트로 계산

## 11.3.3.18 JSON 필드 추출(JSON_EXTRACT)

- json 토큐먼트에서 특정 필드의 값을 가져옴

## 11.3.3.19 JSON 오브젝트 포함 여부 확인(JSON_CONTAINS)

- json 필드를 가지고 있는지를 확인하는 함수

## 11.3.3.20 JSON 오브젝트 생성(JSON_OBJECT)

- RDBMS 칼럼의 값을 이용해 JSON 오브젝트 생성

## 11.3.3.21 JSON 칼럼으로 집계(JSON_OBJECTAGG & JSON_ARRAYAGG)

## 11.3.3.22 JSON 데이터를 테이블로 변환(JSON_TABLE)

- json 데이터의 값을 모아 RDBMS 테이블을 만들어 반환

## 11.4 SELECT

- 웹서비스에서 INSERT, DELETE 같은 작업은 거의 레코드 단위로 발생하므로 성능상 문제가 되는 경우는 별로 없다.
- 하지만 SELECT 는 여러 개의 테이블로부터 데이터를 조합해서 빠르게 가져와야 하기 때문에 여러 개의 테이블을 어떻게 읽을 것인가에 많은 주의를 기울여야 한다.

## 11.4.1 SELECT 절의 처리 순서

- 이 책에서 SELECT 문장이라고 하면 SQL 전체를 의미한다.
- 그리고 SELECT 키워드와 실제 가져올 칼럼을 명시한 부분만 언급할 때는 SELECT 절이라고 표현하곘다.

```sql
SELECT s.emp_no, COUNT(DISTINCT e.first_name) AS CNT
FROM salaries S
	INNER JOIN employees ON e.emp_no = s.emp_no
WHERE s.emp_no IN (100001, 100002)
GROUP BY s.emp_no
HAVING AVG(s.salary) > 1000
ORDER BY AVG(s.salary)
LIMIT 10;
```

- 이 쿼리를 각 절로 나타내보자
    - SELECT 절 : SELECT s.emp_no, COUNT(DISTINCT e.first_name) AS CNT
    - FROM 절 : FROM salaries S INNER JOIN employees ON e.emp_no = s.emp_no
    - WHERE 절 : WHERE s.emp_no IN (100001, 100002)
    - GROUP BY 절 : GROUP BY s.emp_no
    - HAVING 절 : HAVING AVG(s.salary) > 1000
    - ORDER BY 절 : ORDER BY AVG(s.salary)
    - LIMIT 절 : LIMIT 10

- 각 요소가 없는 경우는 가능하지만 이 순서가 바뀌어서 실행되는 형태의 쿼리는 거의 없다.
- ORDER BY 나 GROUP BY 절이 있더라도 인덱스를 이용해 처리될 때는 그 단계 자체가 불필요하므로 생략된다.
- 이 순서에서 벗어나려면 서브쿼리로 작성된 인라인 뷰를 사용해야 한다.
- 예를 들어 위의 쿼리에서 LIMIT 을 먼저 적용하고 ORDER BY 를 실행하고자 한다면 다음과 같이 인라인 뷰를 사용해야 한다.

```sql
SELECT emp_no, cnt
FROM (
	SELECT s.emp_no, COUNT(DISTINCT e.first_name) AS CNT, MAX(s.salary) AS max_salary
	FROM salaries s
		inner join employees e on e.emp_no = s.emp_no
	WHERE s.emp_no IN (100001, 100002)
	GROUP BY s.emp_no
	HAVING MAX(s.salary) > 1000
	LIMIT 10
) temp_view
ORDER BY max_salary;
```

- 위와 같이 사용한다면 임시테이블이 사용되기 때문에 주의해야 한다.

## 11.4.2 WHERE 절과 GROUP BY 절, ORDER BY 절의 인덱스 사용

## 11.4.2.1 인덱스를 사용하기 위한 기본 규칙

- WHERE, ORDER BY, GROUP BY 가 인덱스를 사용하려면 인덱스된 칼럼의 값 자체를 변환하지 않고 그대로 사용한다는 조건을 만족해야 한다.
- 인덱스는 칼럼의 값을 아무런 변환 없이 B TREE 에 정렬해서 저장한다.
- WHERE 조건이나 GROUP BY 또는 ORDER BY 에서도 원본값을 검색하거나 정렬할 때만 B TREE 에 정렬된 인덱스를 이용한다.
- 즉 인덱스는 salary 칼럼으로 만들어져 있는데 다음 예제의 where 절과 같이 salary 칼럼을 가공한 후 다른 상숫값과 비교한다면 이 쿼리는 인덱스를 적절히 이용하지 못하게 된다.

```sql
select * from salaries where salary * 10 > 150000;
```

- 복잡한 연산을 수행한다거나 MD5 함수와 같이 해시값을 만들어 비교해야 하는 경우라면 미리 계산된 값을 저장하도록 MySQL 의 가상 칼럼을 추가하고 그 칼럼에 인덱스를 생성하거나 함수 기반의 인덱스를 사용하면 된다.
- 검색하는 타입이 다를 때에도 index 를 효율적으로 사용하지 못한다.
    - 이를테면 ‘2’ 로 된 type 을 2 로 select 할 경우 모든 인덱스의 타입을 변환한 후 비교해야 하기 때문에

## 11.4.2.2 where 절의 인덱스 사용

- MySQL 8.0 부터는 다음 예제와 같이 인덱스를 구성하는 칼럼별로 정순(오름차순) 과 역순(내림차순) 정렬을 혼합해서 사용할 수 있게 됐다

```sql
ALTER TABLE ... ADD INDEX ix_col1234(col_1 ASC, col_2 DESC, col_3 ASC, col_4 ASC);
```

- OR 은 풀테이블 스캔을 사용할 수 밖에 없다
- 참고
    - GROUP BY 나 ORDER BY 와는 달리 WHERE 절의 조건 절은 순서를 변경해도 결과의 차이가 없기 때문에 조건이 명시된 순서는 중요치 않고 인덱스를 구성하는 칼럼에 대한 조건이 있는지 없는지가 중요하다

## 11.4.2.3 GROUP BY 절의 인덱스 사용

- GROUP BY 절에 명시된 칼럼의 순서가 인덱스를 구성하는 칼럼의 순서와 같으면 GROUP BY 절은 일단 인덱스를 이용할 수 있다.
    - GROUP BY 절에 명시된 칼럼이 인덱스 칼럼의 순서와 위치가 같아야 한다.
    - 인덱스를 구성하는 칼럼 중에서 뒤쪽에 있는 칼럼은 GROUP BY 절에 명시되지 않아도 인덱스를 사용할 수 있지만 인덱스의 앞쪽에 있는 칼럼이 GROUP BY 절에 명시되지 않으면 인덱스를 사용할 수 없다.
    - WHERE 조건절과는 달리 GROUP BY 절에 명시된 칼럼이 하나라도 인덱스에 없으면 GROUP BY 절은 전혀 인덱스를 이용하지 못한다.

## 11.4.2.4 ORDER BY 절의 인덱스 사용

- MySQL 에서 GROUP BY 와 ORDER BY 처리 방법이 비슷하고, index 사용 여부 요건 또한 그렇다
- 정렬되는 각 컬럼의 오름차순 및 내림차순 옵션이 인덱스와 같거나 정반대인 경우에만 사용할 수 있다는 것이다.

## 11.4.2.5 WHERE 조건과 ORDER BY(또는 GROUP BY) 절의 인덱스 사용

- WHERE 과 ORDER BY, GROUP BY 를 함께 사용하는 경우
- WHERE 절과 ORDER BY 절이 동시에 같은 인덱스를 이용
    - WHERE 절의 비교 조건에서 사용하는 컬럼과 ORDER BY 절의 정렬 대상 칼럼이 모두 하나의 인덱스에서 연속해서 포함돼 있을 때
- WHERE 절만 인덱스를 이용
    - ORDER BY 절은 인덱스를 이용한 정렬이 불가능하며 인덱스를 통해 검색된 결과 레코드를 별도의 정렬 처리 과정(Using filesort) 를 거쳐 정렬을 수행
    - WHERE 절의 조건에 일치하는 레코드의 건수가 많지 않을 때 효율적
- ORDER BY 절만 인덱스를 이용
    - ORDER BY 절은 인덱스를 이용해 처리하지만 WHERE 절은 인덱스를 이용하지 못한다
    - 이 방식은 ORDER BY 절의 순서대로 인덱스를 읽으면서 레코드 한 건씩 WHERE 절의 조건에 일치하는지 비교하고 일치하지 않을 때는 버리는 형태로 처리

## 11.4.2.6 GROUP BY 절과 ORDER BY 절의 인덱스 사용

- GROUP BY 절에 명시된 컬럼과 ORDER BY 에 명시된 컬럼의 순서와 내용이 모두 같아야 한다.
- 둘 중 하나라도 인덱스를 사용할 수 없을 때는 둘 다 인덱스를 사용하지 못한다.

## 11.4.2.7 WHERE 조건과 ORDER BY 절 GROUP BY 절의 인덱스 사용

- WHERE 절이 인덱스를 사용할 수 있는가?
- GROUP BY 절이 인덱스를 사용할 수 있는가?
- GROUP BY 절과 ORDER BY 절이 동시에 인덱스를 사용할 수 있는가?

## 11.4.3 WHERE 절의 비교 조건 사용시 주의사항

## 11.4.3.1 NULL 비교

- MySQL 은 NULL 값이 포함된 레코드도 인덱스로 관리
    - 인덱스에서는 NULL 을 하나의 값으로 인정해서 관리한다는 것을 의미
- <=> 사용해서 NULL 인지 비교

## 11.4.3.2 문자열이나 숫자 비교

- 타입에 맞는 상숫값을 사용할 것을 권장

```sql
SELECT * FROM employees WHERE first_name = 10001;
```

- 위처럼 하면 first_name 을 모두 숫자로 변환해서 비교함

## 11.4.3.3 날짜 비교

- TIMESTAMP, DATETIME

## 11.4.3.3.1 DATE 또는 DATETIME 과 문자열 비교

- DATE 또는 DATETIME 과 문자열을 비교할 때는 문자열 값을 자동으로 DATETIME 타입의 값으로 변환해서 비교

```sql
SELECT COUNT(*)
FROM employees
WHERE hire_date > STR_TO_DATE('2011-07-23', '%Y-%m-%d');

SELECT COUNT(*)
FROM employees
WHERE hire_date > '2011-07-23';
```

- 위와 아래는 같은 결과, 2번째 처럼 해도 mysql 은 내부적으로 자동으로 변환시킴

```sql
select count(*)
from employees
where date_format(hire_date, '%Y-%m-%d') > '2011-07-23';

select count(*)
from employees
where date_add(hire_date, INTERVAL 1 YEAR) > '2011-02-23';

-- 아래처럼은 index 활용 가능
select count(*)
from employees
where hire_date > date_sub('2011-07-23', INTERVAL 1 YEAR);
```

- hire_date 에 index 걸려 있어도 변형하니까 index 활용 못함

## 11.4.3.3.2 DATE 와 DATETIME 의 비교

- datetime 에서 시간 빼면 date…

## 11.4.3.3.3 DATETIME 과 TIMESTAMP 비교

- DATE,DATETIME 과 TIMESTAMP 를 별도의 타입 변환 없이 비교하면 문제없이 작동하고 실제 실행 계획도 인덱스 레인지 스캔을 사용해서 동작하는 것처럼 보이지만 사실은 그렇지 않다.

```sql
select count(*) from employees
where hire_date > unix_timestamp('1986-01-01 00:00:00');
```

- 위처럼 셀렉하면 찾아지는 결과 없음
- UNIX_TIMESTAMP() 결과값은 단순 숫자 값에 불과할 뿐
- 상수 리터럴을 변환할 때는 타입에 맞춰야 함
    - DATETIME → FROM_UNIXTIME()
    - TIMESTAMP → UNIX_TIMESTAMP()

## 11.4.3.4 Short-Circuit Evaluation

- && 연산자에서 앞의 조건에서 false 면 뒤에거 진행안 하는거 → short-circuit evaluation

```sql
-- 전체 레코드 약 280만
select count(*) from salaries
where convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';
-- 결과는 244만

select count(*) from salaries where to_date < '1985-01-01';
-- 결과는 0

select * from salaries
where convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';
and to_date < '1985-01-01'
```

- 1번과 2번을 AND 로 연결한 쿼리의 결과는 0건

```sql
-- 1
select * from salaries
where convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';
and to_date < '1985-01-01'
-- => 0.73sec

-- 2
select * from salaries
where to_date < '1985-01-01'
and convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';
-- => 0.52sec
```

- 1번은 conver_tz 를 280만번 실행하고 그 다음 to_date 비교 작엄이 240만번 실행돼야 한다.
- 2번은 to_date 비교 작업만 280만번 실행
- mysql 서버는 쿼리의 where 절에 나열된 조건을 순서대로 short-circuit evaluation 방식으로 평가해서 해당 레코드를 반환해야 할지 말지를 결정
- 근데 인덱스가 있으면 short_circuit evaluation 과 무관하게 그 조건을 가장 최우선으로 사용
- where 조건절에 나열된 조건의 순서가 인덱스의 사용 여부를 결정하지는 않는다.

```sql
select * from employees
where last_name = 'Aamodt'
and first_name = 'Matt';
```

- first_name 에 index 걸려있으면 first_name 부터 평가하고 last_name 은 그다음

```sql
select *
from employees e
where e.first_name = 'Matt'
and exists(select 1 from salaries s
						where s.emp_no = e.emp_no and s.to_date > '1955-01-01'
						group by s.salary having count(*) > 1)
and e.last_name = 'Aamodt';
```

- 위처럼 주면 Matt 을 다 가져오고 subquery 탔다가 last_name 평가함
- 이 방식은 레코드를 많이 읽기도 하지만 쓰기도 많이함
    - 가상 테이블을 쓰기 때문에

```sql
select *
from employees e
where e.first_name = 'Matt'
and e.last_name = 'Aamodt'
and exists(select 1 from salaries s
						where s.emp_no = e.emp_no and s.to_date > '1955-01-01'
						group by s.salary having count(*) > 1);
```

- 근데 위처럼 주면 Matt 인 애들 가져오고 Aamodt 를 비교하고 그다음 subquery 탐
- 훨씬 효율적
- 다만 where 절 조건에 index 가 걸려있다면 순서 상관하지 않아도 됨
    - 가장 먼저 평가하기 때문에

## 11.4.4 DISTINCT

- DISTINCT 를 남용하면 성능 문제, 쿼리의 결과가 의도한 것과 달라지는 문제가 있을 수 있음

## 11.4.5 LIMIT n

- 쿼리 결과에서 지정된 순서에 위치한 레코드만 가져오고자 할 때 사용한다.

```sql
select * from employees
where emp_no between 10001 and 10010
order by first_name
limit 0, 5;
```

1. employees 테이블에서 조건에 일치하는 레코드를 전부 읽어 온다
2. 읽어온 레코드를 first_name 컬럼값에 따라 정렬한다
3. 정렬된 결과 상위 5건을 반환한다.
- MySQL Limit 은 쿼리의 가장 마지막에 실행된다.

```sql
select * from employees limit 0, 10;

select * from employees group by first_name limit 0, 10;

select DISTINCT first_name from employees limit 0, 10;

select * from employees
where emp_no between 10001 and 11000
order by first_name
limit 0, 10;
```

- 1번 쿼리에서 limit 이 없으면 테이블 풀 스캔을 실행했을 것
    - 다만 그루핑, 오더링이 없고 limit 이 있기 때문에 10건만 읽고 반환
- 두번째는 group by 가 있기 때문에 group by 처리가 되고 limit 처리 수행
- 세번째는 풀 테이블 스캔 돌리면서 중복 제거 임시테이블 통해 함
    - 이 작업을 반복적으로 처리하다가 유니크한 레코드가 LIMIT 건수만큼 해워지면 그 순간 쿼리 멈춤
- 네번쨰는 where 절에 일치하는 레코드 읽은 후 first_name 컬럼 값으로 정렬
    - 정렬을 수행하면서 10건이 완성되는 순간 결과 반환
- 인덱스를 적절히 사용할 수 있다면 쿼리의 성능을 향상시킬 수 있다.

```sql
select * from salaries
order by salary limit 38864, 10;

-- 페이지 조회용 쿼리 (첫 페이지의 마지막 salary 값과 emp_no 값을 이용)
select * from salaries
where salary >= 38864 and not (salary=38864 and emp_no <= 274049)
order by salary limit 0, 10;
```

- 위처럼 시작해야하는 페이지 레코드가 클 경우 where 조건으로 주는 게 더 빠름
    - 첫 번째 쿼리는 38864 + 10 까지 레코드를 읽고 order by 친 다음에 38864개를 버리 10개를 반환해야 하는데
    - 두 번째 쿼리는 범위를 제한하면서 이전 페이지 전체를 건너 뛰어 처음 페이지를 읽은 것과 동일한 성능을 낸다
    - 이를 No Offset 방식이라고 한다.
    - [참고 글](https://jojoldu.tistory.com/528)

## 11.4.6 COUNT()

- 레코드의  건수를 반환하는 함수
- 레코드 건수를 가져오는 것과는 무관한 작업을 하면 안된다.
    - order by, left join…
    - 제거하는 것이 좋음
- 인덱스를 제대로 사용하도록 튜닝되지 못한 count(*) 는 일반 쿼리보다 훨씬 느릴 수 있음

## 11.4.7 JOIN

- JOIN 이 어떻게 인덱스를 사용하는지에 대한 얘기

## 11.4.7.1 JOIN 의 순서와 인덱스

- 인덱스 레인지 스캔 : 인덱스 탐색 → 인덱스 스캔
    - 참고
        - 인덱스 탐색 → 조건에 만족하는 값이 저장된 위치를 찾는다.
        - 인덱스 스캔 → 찾은 위치부터 필요한 만큼 인덱스를 쭉 읽는다.
- 인덱스를 이용해서 쿼리하는 작업은 레코드 건수가 소량이기 때문에 인덱스 스캔 작업은 부하가 작지만 특정 인덱스 키를 찾는 인덱스 탐색은 부하가 높다.
- 조인에서 드라이빙 테이블은 인덱스 탐색은 단 한번만 수행하고 모두 스캔한다.
- 드리븐 테이블은 탐색과 스캔이 드라이빙 테이블에서 읽은 레코드 건수만큼 반복된다.
    - 1 : 1 로 매치 되더라도 드리븐 테이블을 읽는 것이 훨씬 더 큰 부하를 차지한다.

```sql
SELECT *
FROM employees e, dept_emp de
WHERE e.emp_no = de.emp_no;
```

- 모두 인덱스가 있는 경우
    - 옵티마이저가 통계 정보를 이용해 드라이빙 테이블을 결정한다.
- employees.emp_no 에만 인덱스가 있는 경우
    - 만약 dept_emp 가 드리븐이 된다면 employees 레코드 건수만큼 dept_emp 를 풀스캔 해야만한다.
    - 그래서 옵티마이저는 항상 dept_emp 를 드라이빙 테이블로, employees 를 드리븐 테이블로 선택한다.
- dept_emp.emp_np 에만 인덱스가 있는 경우
    - 마찬가지로 이번에는 반복된 풀 스캔을 피하기 위해 employees 테이블을 드라이빙 테이블로 선택한다.
- 두 칼럼 모드 인덱스가 없는 경우
    - 어차피 풀 스캔 일어나니까 옵티마이저가 알아서 한다.
    - 인덱스가 없는 경우 BNLJ → Hash Join 을 사용한다.

## 11.4.7.2 JOIN 칼럼의 데이터 타입

- 비교 대상 칼럼의 데이터 타입을 반드시 동일하게 써야 인덱스를 활용할 수 있다.

```sql
create table tb_test1 (user_id INT user_type INT, PRIMARY KEY(user_id));
create table tb_test2 (user_type char(1), type_desc varchar(10), primary key (user_type));

select *
from tb_test1 tb1, tb_test2 tb2
where tb1.user_type = tb2.user_type;
```

- 둘 다 풀 테이블 스캔을 사용한다.
- 또한 join buffer 를 이용해 해시 조인이 실행된다.
- 11.4.3.2 참고 → 인덱스 변형이 필요해서 인덱스 사용할 수 없음
    - 같은 char type 이더라도 문자 집합이나 콜레이션이 다른 경우 동일한 문제 발생
    - 같은 int 타입이더라도 부호(sign) 의 존재 여부가 다른 경우 동일한 문제 발생

## 11.4.7.3 OUTER JOIN의 성능과 주의사항

- 테이블의 데이터가 일관되지 않은 경우에만 아우터 조인이 필요하다
- 옵티마이저는 절대 아우터로 조인되는 테이블을 드라이빙 테이블로 선택하지 못한다.
- 이너 조인으로 사용해도 되는 쿼리를 아우터 조인으로 작성하면 조인 순서를 변경하면서 수행할 수 있는 최적화의 기회를 빼앗아버린다.

```sql
-- join 되는 mgr 에 조건을 걸면 아래처럼 inner 조건으로 바꿈
select * from employees e
left join dept_manager mgr on mgr.emp_no = e.emp_no
where mgr.dept_no = 'd001';

-- 따라서 아래의 쿼리의 where 절은 on 절로 옮겨야 한다.
select *
from employees e
inner join dept_manager mgr on mgr.emp_no = e.emp_no
where mgr.dept_no = 'd001';

select *
from employees e
left join dept_manager mgr on mgr.emp_no = e.emp_no and mgr.dept_no = 'd001';
```

## 11.4.7.4 JOIN 과 외래키(FOREIGN KEY)

- 외래키는 조인과 아무런 연관이 없다.
    - 외래키의 주목적은 데이터 무결성을 보장하기 위해
    - 참조 무결성

## 11.4.7.5 지연된 조인(Delayed Join)

- 데이터를 조회하는 쿼리에 GROUP BY 또는 ORDER BY 를 사용할 때 각 처리 방법에서 인덱스를 사용한다면 이미 최적으로 처리되고 있을 가능성이 높다.
- 그렇지 않다면 모든 조인을 실행하고 난 다음 GROUP BY 나 ORDER BY 를 처리할 것이다.
    - 조인은 대체로 실행되면 될수록 결과 레코드 건수가 늘어난다.
    - 따라서 조인의 결과를 GROUP BY 하거나 ORDER BY 하면 조인을 실행하기 전의 레코드에서 GROUP BY 나 ORDER BY 를 수행하는 것보다 많은 레코드를 처리해야 한다.
    - 지연된 조인이란 조인이 실행되기 이전에 GROUP BY 나 ORDER BY 를 처리하는 방식 → LIMIT 과 사용하면 큰 효과

```sql
select e.*
from salaries s, employees e
where e.emp_no = s.emp_no
and s.emp_np = between 10001 and 13000
group by s.emp_no
order by sum(s.salary) desc
limit 10;
```

- 인덱스를 사용하지 못하는 group by, order by 를 지연된 조인으로 처리
- employees 를 드라이빙 테이블로 선택해 between 조건의 레코드를 가져오고 salaries 테이블을 조인
    - 조인의 결과를 임시 테이블에 저장하고 group by 처리를 통해 3000 건으로 줄였다
    - 그리고 order by 를 처리해 상위 10건만 최종적으로 반환한다.

```sql
select e.*
from (
	select s.emp_no
	from salaries s
	where s.emp_no between 10001 and 13000
	group by s.emp_no
	order by sum(s.salary) desc
	limit 10
) x
employees e
where e.emp_no = x.emp_no;
```

- 위의 쿼리를 지연된 조인으로 변경
    - where, group by, limit 까지 수행한 다음 임시 테이블에 저장
    - 그 후 employees 테이블과 조인
    - 모든 처리를 salaries 테이블에서 수행하고 최종 10건만 employees 테이블과 조인

## 11.4.7.6 래터럴 조인(Lateral Join)

- 특정 그룹별로 서브쿼리를 실행해서 그 결과와 조인
    - 이 때 외부 테이블의 컬럼을 참조할 수 있다

## 11.4.7.7 실행 계획으로 인한 정렬 흐트러짐

- NLJ 는 드라이빙 테이블에서 읽은 레코드의 순서가 다른 테이블이 모두 조인돼도 그대로 유지된다.
    - 드라이빙 테이블을 읽은 순서대로 정렬된다.
- Hash Join 은 레코드 정렬 순서가 달라진다.
    - BNLJ 또한 달라진다.

## 11.4.8 GROUP BY

## 11.4.8.1 WITH ROLLUP

- 그루핑된 그룹별로 소계를 가져올 수 있는 rollup
- 최종 합만 가져오는 것이 아니라 group by 에 사용된 컬럼의 개수에 따라 소계의 레벨이 달라진다.
    - 각각의 컬럼에 대해 소계

## 11.4.8.2 레코드를 칼럼으로 변환해서 조회

- sum, count, case when … end

## 11.4.8.2.1 레코드를 칼럼을 변환

- p.99 쿼리들 참조, 대부분 count(*) 때려서 dept_no 가 몇개인지를 레코드로 보여주는거임

## 11.4.8.2.2 하나의 칼럼을 여러 칼럼으로 분리

- case when 으로 여러가지 칼럼으로 나누는 거

## 11.4.9 ORDER BY

- order by 절이 사용되지 않으면 select 쿼리의 결과는 어떤 순서로 정렬될까?
    - 인덱스를 사용한 select 의 경우 인덱스에 정렬된 순서대로 레코드 가져옴
    - 풀 스캔이 되는 경우 clustered key 기준으로 가져옴
    - select 쿼리가 임시 테이블을 거쳐 처리되면 레코드의 순서를 예측하기는 어렵다.
- ORDER BY 가 없다면 어떤 정렬 순서도 보장하지는 않는다
- Using filesort → 정렬 알고리즘을 수행했다 (디스크나, 메모리를 사용해서)
    - show status like ‘Sort_%’ 를 통해 얼마나 정렬했는지 확인 가능

## 11.4.9.1 ORDER BY 사용법 및 주의사항

- 순번를 사용하거나 따옴표를 사용해서 컬럼을 표시할 수 있다.

## 11.4.9.2 여러 방향으로 동시 정렬

- DESC, ASC 혼용
    - 인덱스를 혼용해서 생성

## 11.4.9.3 함수나 표현식을 이용한 정렬

- 8.6 절 참조

## 11.4.10 서브쿼리

- 서브쿼리가 사용되는 위치별로 어떻게 최적화 되는지 살펴봄

## 11.4.10.1 SELECT 절에 사용된 서브쿼리

- 임시 테이블을 만들거나 쿼리를 비효율적으로 실행하게 만들지는 않는다.
    - 적절히 인덱스를 사용할 수 있어야 한다.
- 체크 조건이 느슨하다 → 실제로 데이터가 없어서 NULL 값이어도 select 됨
- select 절의 서브쿼리는 로우 서브쿼리는 불가능하고 스칼라 서브쿼리만 가능
- 서브쿼리로 실행하는 것 보다 조인으로 처리할 때가 더 빠르다
- 서브쿼리를 여러번 쓰기보다 래터럴 조인을 권장

## 11.4.10.2 FROM 절에 사용된 서브쿼리

- 5.7 부터 옵티마이저가 from 절의 서브쿼리를 외부 쿼리로 병합하는 최적화 수행
- 다음과 같은 기능이 포함되면 FROM 절의 서브쿼리는 외부 쿼리로 병합되지 못한다.
    - 집합 함수 (SUM, MIN, MAX, COUNT)
    - DISTINCT
    - GROUP BY, HAVING
    - LIMIT
    - UNION, UNION ALL
    - SELECT 절에 서브쿼리가 사용된 경우
    - 사용자 변수 사용
- FROM 절의 서브 쿼리가 ORDER BY 절을 가진 경우 외부 쿼리가 GROUP BY 나 DISTINCT 같은 기능을 사용하지 않는다면 서브쿼리의 정렬 조건을 외부 쿼리로 병합한다.
    - 외부 쿼리에서 GROUP BY 나 DISTINCT 같은 기능이 사용되고 있다면 서브쿼리의 정렬 작업은 무의미하기 때문에 서브쿼리의 ORDER BY 절은 무시된다.

## 11.4.10.3 WHERE 절에 사용된 서브쿼리

- SELECT 절, FROM 절보다는 다양한 형태로 사용
- 3가지 구분
    - 동등 또는 크다 작다 비교
    - IN 비교
    - NOT IN 비교

## 11.4.10.3.1 동등 또는 크다 작다 비교

- 서브쿼리를 먼저 실행한 후 상수로 변환한다
- 상수값으로 서브쿼리를 대체해서 나머지 쿼리를 처리한다.
- 인덱스가 있다면 인덱스로 처리하여 상수값을 리턴

## 11.4.10.3.2 IN 비교

- 세미 조인 → IN (subquery) 형태의 조건을 조인의 한 방식인 세미 조인이라고 보는 것
    - 실제 조인은 아니지만 테이블의 레코드가 다른 테이블의 레코드를 이용한 표현식과 일치하는지를 체크하는 형태
- 세미 조인 최적화 전략
    - 테이블 풀 아웃
    - 퍼스트 매치
    - 루스 스캔
    - 구체화
    - 중복 제거
    - 9.3.1.9 세미조인 참조

## 11.4.10.3.3 NOT IN 비교

- 세미 조인과 비슷하지만 안ㅇ티 세미 조인이라고 한다.

## 11.4.11 CTE, Common Table Expression

- 임시 테이블
- 문장이 종료되면 자동으로 삭제된다.
- 재귀적 반복 기준
    - Non-recursive
    - Recursive

## 11.4.11.1 비 재귀적 CTE, Non-Recursive CTE

- from 절의 서브 쿼리(내부에서 집합함수 사용)를 사용하는 경우보다 CTE 가 더 효율적일 때가 있다.
- 연속해서 cte 를 사용하는 경우 2번째는 1번째를 참조할 수 있지만 1번째는 2번째를 참조할 수 없다.
- from 절 서브쿼리보다 효율적인 점
    - CTE 임시 테이블을 재사용이 가능
    - CTE 로 선언된 임시 테이블을 다른 CTE 쿼리에서 참조
    - CTE 임세 테이블의 생성 부분과 사용 부분의 코드를 분리할 수 있으므로 가독성이 높음

## 11.4.11.2 재귀적 CTE

```sql
with recursive cte(no) as (
	select 1
	union all
	select (no + 1) from cte where no < 5
)
select * from cte;
```

- 위 쿼리의 작동 순서
    1. CTE 쿼리의 비 재귀적 파트의 쿼리를 실행
    2. 1번의 결과를 이용해 cte 라는 이름의 임시 테이블을 생성
    3. 1 번의 결과를 Cte 라
    4. 1번 결과를 입력으로 사용해 cte 쿼리의 재귀적 파트의 쿼리를 실행
    5. 4번의 결과를 cte 라는 임시 테이블에 저장(이때 UNION 또는 UNION DISTINCT 의 경우 중복 제거를 실행)
    6. 전 단계의 결과를 입력으로 사용해 CTE 쿼리의 재귀적 파트 쿼리를 실행
    7. 6번 단계에서 쿼리 결과가 없으면 CTE 쿼리를 종료
    8. 6번의 결과를 CTE 라는 임시 테이블에 저장
    9. 6번으로 돌아가서 반복 실행

## 11.4.12 윈도우 함수 Window Function

- 집계 함수는 주어진 그룹 별로 하나의 레코드로 묶어서 출력하지만 윈도우 함수는 조건에 일치하는 레코드 건수는 변하지 않고 그대로 유지한다.

## 11.4.12.1 쿼리 각 절의 실행 순서

- from → where → group by → having → **window function** → select → order by → limit
    - group by 칼럼, where 에 사용할 수 없다.

```sql
-- 1
select emp_no, from_date, salary, avg(salary) over() as avg_salary
from salaries
where emp_no = 10001
limit 5;

-- 2
select emp_no, from_date, salary
avg(salary) over() as avg_salary
from (select * from salaries where emp_no = 10001 limit 5) s2;
```

- 1 번째거는 emp_no = 10001 전체를 가져와서 avg 계산하고 5개 반환
- 2번째 거는 5개만 가져와서 avg 계산하고 반환

## 11.4.12.2 윈도우 함수 기본 사용법

- over 절에 의해 만들어진 그룹을 파티션 또는 윈도우 라고 한다.
- 파티션 안에서 연산 대상 레코드별로 연산을 수행할 소그룹을 프레임이라 한다.

## 11.4.12.3 윈도우 함수

- p.133

## 11.4.12.4 윈도우 함수와 성능

- 아직 인덱스를 이용한 최적화가 부족한 부분도 있다.
- 가능하면 윈도우 함수를 피하는 것이 좋다.

## 11.4.13 잠금을 사용하는 select

- for share, for update
- transaction 에서만 가능

## 11.4.14.1 잠금 테이블 선택

```sql
select *
from employees e
	inner join dept_emp de on de.emp_no = e.emp_no
	inner join departments d on d.dept_no = de.dept_no
FOR UPDATE;
```

- 3개 테이블에서 읽은 레코드에 대해 모두 X Lock 걸음

```sql
select *
from employees e
	inner join dept_emp de on de.emp_no = e.emp_no
	inner join departments d on d.dept_no = de.dept_no
FOR UPDATE e;
```

- alias 걸면 e 만 걸음

## 11.4.13.2 NOWAIT & SKIP LOCKED

- NOWAIT
    - 해당 레코드가 lock 이 걸린 상태라면 에러 뱉어내고 바로 끝남
- skip locked
    - 잠겨진 상태라면 에러를 반환하지 않고 잠긴 레코드 무시하고 잠금이 걸리지 않은 레코드만 가져옴
- 쿠폰 발급 관련된 처리를 할 수 있음
    - queue
    - redis, memcached 대체 가능


## 11.5 INSERT

- 그다지 성능에 대해 고려한 부분이 많지 않다
- 문장보다 테이블 구조가 성능에 더 큰 영향을 미친다.
- 많은 경우 insert 와 seelect 성능을 동시에 빠르게 만들 수 있는 구조는 없다.

## 11.5.1 고급 옵션

- insert 문장에도 사용할 수 있는 유용한 기능들이 있다.

## 11.5.1.1 INSERT IGNORE

- pk, unique 칼럼이 이미 테이블에 존재해서 중복되는 경우 insert 를 무시하고 다른 레코드를 처리
    - 데이터 타입이 일치하지 않는 경우에도 ignore

## 11.5.1.2 INSERT … ON DUPLICATE KEY UPDATE

- upsert 임

## 11.5.2 LOAD DATA 명령 주의 사항

- 데이터를 빠르게 적재할 수 있는 방법
- 스토리지 엔진에 데이터를 직접 적재
- 단점
    - 단일 스레드로 실행
    - 단일 트랜잭션으로 실행
- 하나의 트랜잭션으로 처리
    - undo log 가 삭제되지 못하고 유지돼야 함

## 11.5.3 성능을 위한 테이블 구조

- insert 문장은 테이블 구조에 의해 많이 결정된다.

## 11.5.3.1 대량 insert 성능

- csv 로 덤프 받아서 load data 로 insert

```sql
LOAD DATA INFILE '/tmp/sorted_by_primary.csv'
INTO TABLE salaries_temp
fields terminated by ',' optionally enclosed by '"'
lines terminated by '\n';
-- => 1 min 53.11 sec

load data infile '/tmp/sorted_by_random.csv'
into table salaries_temp
fields terminated by ',' optionally enclosed by'"'
lines terminated by '\n';
-- => 4min 5.94 sec
```

- insert 하는 데이터라 pk 로 정렬되어 있지 않기 때문에 아래 게 2배 이상 차이남
- 세컨더리 인덱스가 많을수록 insert 성능 떨어짐

## 11.5.3.2 프라이머리 키 선정

- 세컨더리 인테스를 이용하는 쿼리보다 프라이머리 키를 이용하는 쿼리의 성능이 훨신 빨라지는 효과를 낸다.
- 단순히 insert 성능만을 위해 설계해서는 안된다.
- 읽기 비율의 압도적으로 높다.
    - select 쿼리를 빠르게 만드는 방향으로 선정
- 또한 select 가 많지 않고 insert 가 많은 테이블에 대해서는 인덱스의 개수 최소화

## 11.5.3.3 Auto-Increment

- insert 에 최적화된 테이블
    - 단조 증가 또는 단조 감소되는 값으로 pk 선정
    - secondary index 최소화
- pk 로 자동 clustering
    - A.I 를 이용하면 클러스터링 되지 않는 테이블의 효과를 얻을 수 있다.
    - 여기다 secondary index 도 없으면 가장 빠른 insert 성능을 낼 수 있음
- A.I 채번을 위한 잠금 → AUTO-INC

## 11.6 UPDATE 와 DELETE

- 여러 테이블을 조인해서 한 개 이상을 변경하거나 삭제하는 기능
    - join update
    - join delete

## 11.6.1 UPDATE … ORDER BY … LIMIT n

- 정렬해서 상위 몇 개만 update, delete

## 11.6.2 JOIN UPDATE

- 조인된 결과 레코드를 변경 및 삭제하는 쿼리
- 공통으로 존재하는 쿼리를 찾아서 업데이트
- 모든 테이블에 대해 읽기 참조만 되는 테이블은 읽기 잠금이 걸리고 칼럼이 변경되는 테이블은 쓰기 잠금이 걸린다.
- 데드락이 걸릴 가능성이 높으므로 빈번하게 사용하는 것은 피하자
    - 배치, 통계에서는 유용