# 트랜잭션과 잠금

- 잠금과 트랜잭션
- 트랜잭션의 격리 수준에 대해 알아본다.
- 트랜잭션은 논리적인 작업 셋을 완벽하게 처리하거나, 처리하지 못할 때에는 partial update 가 되지 않게 보장
- lock 은 동시성을 제어
- 트랜잭션은 데이터의 정합성을 보장

## 트랜잭션

- MyISAM 이나 MEMORY 같이 트랜잭션을 제어하지 않는 스토리지 엔진의 테이블이 더 많은 고민거리를 만들어 낸다.

### MySQL 에서의 트랜잭션

- 쿼리가 몇 개가 있든 작업 셋 자체가 100% 적용되거나 아무것도 적용되지 않음을 보장해야 한다.

```sql
-- 이미 각 테이블에는 3이 값으로 있다는 가정

SET autocommit=ON;

INSERT INTO TAB_MYISAM (fdpk) values(1), (2), (3);
INSERT INTO TAB_INNODB (fdpk) values(1), (2), (3);

-- 결과는 myisam 에는 1, 2, 3
-- innodb 는 3 만 들어있다.
```

- 두 테이블 모두 pk 중복 오유로 쿼리 실패
- MyISAM 은 1, 2 가 insert 된 상태로 남아 있다.
    - 1, 2 를 저장하고 3을 저장하려는 순간 중복 키 오류가 발생
    - MyISAM 은 transaction 을 지원하지 않아 그냥 종료
    - MEMORY 도 동일
    - 이런 현상을 partial update 라고 한다.

### 주의사항

- 트랜잭션 또한 DBMS 의 커넥션과 동일하게 꼭 필요한 최소한의 코드에만 적용하는 것이 좋다.
- 프로그램 코드에서 트랜잭션 범위를 최소화 해야한다.

```sql
1. 처리 시작
	=> 데이터베이스 커넥션 생성
	=> 트랜잭션 시작
2. 사용자의 로그인 여부 확인
3. 사용자의 글쓰기 내용의 오류 여부 확인
4. 첨부로 업로드된 파일 확인 및 저장
5. 사용자의 입력 내용을 DBMS 에 저장
6. 첨부 파일 정보를 DBMS 에 저장
7. 저장된 내용 또는 키타 정보를 DBMS 에서 조회
8. 게시물 등록에 대한 알림 메일 발송
9. 알림 메일 발송 이력을 DBMS 에 저장
	<= 트랜잭션 종료
	<= 데이터베이스 커넥션 반납
10. 처리 완료
```

- 트랜잭션 처리에 좋지 않은 영향을 미치는 부분
    - 실제로 5번부터 저장하는 작업이 진행된다.
    - 그래서 2, 3, 4 는 트랜잭션에 포함시킬 이유가 없다.
    - DB 커넥션은 개수가 제한적이어서 각 단위 프로그램이 커넥션을 소유하는 시간이 길어질수록 사용 가능한 커넥션의 개수는 줄어들 수 있다.
    - 어느 순간 각 단위 프로그램에서 커넥션을 가져가기 위해 기다려야 하는 상황이 발생할 수도 있다.
    - 더 큰 위험은 8번
        - 메일 전송, FTP 파일 전송 작업 또는 네트워크를 통해 원격 서버와 통신하는 작업은 트랜잭션 내에서 제거하는 것이 좋다.
        - 메일 서버와 통신할 수 없는 상황이 발생하면 웹 서버뿐 아니라 DBMS 서버까지 위험해지는 상황 발생
    - 5, 6 번은 하나의 트랜잭션으로 묶여야 함
    - 7번은 트랜잭션 필요 없음
    - 9번은 성격이 다르기 때문에 5, 6과 묶일 필요 없음 → 별도의 트랜잭션

```sql
1. 처리 시작
2. 사용자의 로그인 여부 확인
3. 사용자의 글쓰기 내용의 오류 여부 확인
4. 첨부로 업로드된 파일 확인 및 저장
	=> 데이터베이스 커넥션 생성 (또는 커넥션 풀에서 가져오기)
	=> 트랜잭션 시작
5. 사용자의 입력 내용을 DBMS 에 저장
6. 첨부 파일 정보를 DBMS 에 저장
	<= 트랜잭션 종료 (commit)
7. 저장된 내용 또는 키타 정보를 DBMS 에서 조회
8. 게시물 등록에 대한 알림 메일 발송
	=> 트랜잭션 시작
9. 알림 메일 발송 이력을 DBMS 에 저장
	<= 트랜잭션 종료
	<= 데이터베이스 커넥션 종료 (또는 커넥션 풀에 반납)
10. 처리 완료
```

- 보다 나은 설계
- 위 처럼 트랜잭션 범위를 최소화해야 한다.

## MySQL 엔진의 잠금

- 스토리지 엔진 레벨
    - 스토리지 엔진 레벨의 잠금은 스토리지 엔진 간 상호 영향을 미치지 않음
- MySQL 엔진 레벨
    - MySQL 엔진은 MySQL 서버에서 스토리지 엔진을 제외한 나머지 부분
    - MySQL 엔진 레벨의 잠금은 모든 스토리지 엔진에 영향을 미침
    - 테이블 데이터 동기화를 위한 테이블 락
    - 테이블의 구조를 잠그는 메타데이터 락
    - 사용자의 필요에 맞게 사용할 수 있는 네임드 락

### 글로벌 락

- `FLUSH TABLES WITH READ LOCK` 으로 획득할 수 있다.
    - 모든 테이블을 닫고 잠금을 건다.
- 범위가 가장 크다
- 한 세션에서 글로벌 락을 획득하면
    - 다른 세션에서 SELECT 를 제외한 대부분의 DDL, DML 문장을 실행하는 경우 글로벌 락이 해제될 때까지 해당 문장이 대기 상태로 남는다.
- MySQL 서버 전체에 영향을 미친다.
- 테이블이나 데이터베이스가 다르더라도 동일하게 영향을 미친다.
- 조금 더 가벼운 글로벌 락
    - Xtrabackup
    - Enterprise Backup

```sql
LOCK INSTANCE FOR BACKUP;
-- 백업 실행
UNLOCK INSTANCE;
```

- 특정 세션에서 백업 락을 획득하면 모든 세션에서 다음과 같이 테이블의 스키마나 사용자의 인증 관련 정보를 변경할 수 없게 된다.
    - 데이터베이스 및 테이블 등 모든 객체 생성 및 변경, 삭제
    - REPAIR TABLE 과 OPTIMIZE TABLE 명령
    - 사용자 관리 및 비밀번호 변경
- 일반적인 테이블의 데이터 변경은 허용
    - 소스 서버
    - 레플리카 서버
        - 주로 백업 실행되는 서버
        - 소스 서버가 멈추면 레플리카 서버의 데이터가 최신 상태가 될 때까지 서비스를 멈춰야 할 수도 있음
        - Xtrabackup, Enterprise Backup이 백업하다가 스키마 바뀌면 다시 백업해야 됨…
        - MySQL 서버의 백업 락은 이런 목적으로 도입
        - 백업 실패를 막기 위해 존재

### 테이블 락

- 개별 테이블 단위 설정되는 잠금
- 명시적 또는 묵시적으로 특정 테이블의 락을 획득
- `LOCK TABLES table_name [ READ | WRITE ]` 획득
- `UNLOCK TABLES` 반납
- 애플리케이션에서 사용할 필요가 거의 없다.

### 네임드 락

- GET_LOCK() 함수를 이용해 임의의 문자열에 대한 잠금 설정
- 사용자가 지정한 문자열에 대해 획득하고 반납
- 자주 사용되지 않는다.
- MySQL 1대에 5대 웹 서버 접속해서 서비스 하는 상황에서 5대의 웹 서버가 어떤 정보를 동기화해야 하는 요건처럼 여러 클라이언트가 상호 동기화를 처리할 때 사용

```sql
-- 'mylock' 이라는 문자열에 대해 잠금 획득
-- 이미 잠금을 사용 중이면 2초 동안만 대기한다.
SELECT GET_LOCK('mylock', 2);

-- mylock 이라는 문자열에 대해 잠금이 설정돼 있는지 확인
SELECT IS_FREE_LOCK('mylock')

-- mylock 이라는 문자열에 대해 획득했던 잠금을 반납한다.
SELECT RELEASE_LOCK('mylock')

-- 3개 함수 모두 정상적으로 락을 획득하거나 해제한 경우에는 1을
-- 아니면 NULL 이나 0을 반환한다.
```

- 많은 레코드에 대해서 복잡한 요건으로 레코드를 변경하는 트랜잭션에 유용하게 사용
    - 배치 프로그램
    - 동일 데이터를 변경하거나 참조하는 프로그램끼리 분류해서 네임드 락을 걸고 쿼리를 실행하면 아주 간단히 해결됨

### 메타데이터 락

- 데이터베이스 객체(테이블이나 뷰 등)의 이름이나 구조를 변경하는 경우 획득하는 잠금
- 명시적으로 획득하거나 해제할 수  있는 것이 아니다.
- `RENAME TABLE tab_a TO tab_b` 같은 상황에서 자동으로 획득
    - 원본 이름과 변경될 이름 두 개 모두 잠금 설정

```sql
-- 배치 프로그램에서 별도의 임시 테이블에 서비스용 랭킹 데이터를 생성
-- 랭킹 배치가 완료되면 현재 서비스용 랭킹 테이블을 rank_backup 으로 백업하고
-- 새로 만들어진 랭킹 테이블을 서비스용으로 대체하고 하는 경우
-- 정상적으로 작동한다.
RENAME TABLE rankt TO rank_backup, rank_new TO rank;

-- 아래의 상황에는 짧은 시간이지만 rank 테이블이 존재하지 않는 순간이 생긴다.
RENAME TABLE rank TO rank_backup;
RENAME TABLE rank_backup TO rank;

```

## InnoDB 스토리지 엔진 잠금

- 레코드 기반의 잠금 방식
    - 뛰어난 동시성 처리
- `INNODB_TRX`, `INNODB_LOCKS`, `INNODB_LOCK_WAITS`
    - 3개 조인해서 어떤 트랜잭션이 어떤 잠금을 대기하고 있고
    - 해당 잠금을 어느 트랜잭션이 가지고 있는지 확인
    - 장시간 잠금을 가지고 있는 클라이언트 찾아서 종료

### InnoDB 스토리지 엔진의 잠금

- 레코드 락이 페이지, 테이블 락으로 레벨업 되는 경우는 없다.
- 레코드 락 뿐만 아니라 레코드와 레코드 사이의 간격을 잠그는 Gap Lock 이라는 것이 존재한다.
- **레코드 락**
    - 레코드 자체만을 잠그는 것
    - InnoDB 스토리지 엔진은 인덱스의 레코드를 잠근다.
        - 레코드 자체를 잠그느냐, 인덱스를 잠그느냐는 상당히 크고 중요한 차이를 가진다.
        - InnoDB 에서 대부분 보조 인덱스를 이용한 변경 작업은 넥스트 키 락 또는 갭락을 사용해 잠그지만 프라이머리 키 또는 유니크 인덱스에 의한 변경 작업에서는 갭에 대해서 잠그지 않고 레코드 자체에 대해서만 락을 건다.
- **갭 락**
    - 레코드 자체가 아니라 레코드와 인접한 레코드 사이의 간격만을 잠그는 것
        - 레코드 사이에 새로운 레코드가 생성되는 것을 제어한다.
        - 갭 락 자체보다 넥스트 키 락의 일부로 자주 사용된다.
- **넥스트 키 락**
    - 레코드 락 + 갭 락
    - STATEMENT 포맷의 바이너리 로그를 사용하는 MySQL 서버에서는 REPEATABLE READ 격리 수준을 사용해야한다 또한 innodb_locks_unsafe_for_binlog 시스템 변수가 비활성화되면 변경을 위해 검색하는 레코드에는 넥스트 키 락 방식으로 잠금이 걸린다.
    - InnoDB 의 갭 락이나 넥스트 키 락은 바이너리 로그에 기록되는 쿼리가 레플리카 서버에서 실행될 때 소스 서버에서 만들어 낸 결과와 동일한 결과를 만들어 내도록 보장하는 것이 주목적
    - 넥스트 키 락과 갭 락으로 데드락이 발생하거나 트랜잭션을 기다리게 하는 일이 자주 발생
- **자동 증가 락**
    - 자동 증가하는 숫자 값을 추출(채번)하기 위해 AUTO_INCREMENT 라는 칼럼 속성을 제공
    - 동시에 여러 레코드가 INSERT 되어도 순서대로 증가하는 일련번호 보장
    - AUTO_INCREMENT LOCK 이라고 하는 테이블 수준의 잠금을 사용한다.
        - INSERT, REPLACE 처럼 새로운 레코드를 저장하는 쿼리에서만 필요
        - UPDATE, DELETE 에서는 걸리지 않는다.
    - AUTO_INCREMENT 락을 명시적으로 획득하고 해제하는 방법은 없다.
        - 아주 짧은 시간만 적용돼서 대부분 문제되지 않음
    - innodb_autoinc_lock_mode 변수로 자동 증가 락의 작동 방식 변경 가능
        - 0 : 모든 insert 문장에 적용
        - 1 : insert 의 레코드 건수를 정확히 예측할 수 있을 때 자동 증가 락을 사용하지 않고 래치(뮤텍스)를 이용해 처리
        - 2 : 절대 자동 증가 락을 사용하지 않고 래치(뮤텍스) 사용, 연속된 자동 증가 값을 보장하지는 않는다.

### 인덱스와 잠금

- 레코드가 아니라 인덱스를 잠그는 방식
- 변경해야 할 레코드를 찾기 위해 검색한 인덱스의 레코드를 모두 락을 걸어야 한다.

```sql
-- employees 테이블에는 first_name 칼럼만 멤버로 담긴 ix_firstname 이라는 인덱스 준비
-- KEY ix_firstname(first_name)
-- employees 테이블에서 first_name='Georgi' 인 사원은 전체 253명
-- first_name = 'Georgi' 이고 last_name = 'Klassen' 인 사원은 1명
-- fisrt_name='Georgi' 이고 last_name='Klassen' 인 사원의 입사일자를 오늘로 변경
UPDATE employees SET hire_date=NOW() WHERE first_name='Georgi' AND last_name='Klassen'
```

- 1건의 업데이트를 위해 몇 개의 레코드에 락을 걸어야 할까?
    - 인덱스를 활용할 수 있는 조건은 first_name
    - last_name 칼럼은 인덱스에 없기 때문에
    - first_name=’Georgi’ 인 253 건의 모든 레코드가 잠긴다.
    - 만약 인덱스가 없었다면 테이블에 있는 모든 레코드를 잠그게 된다.

### 레코드 수준의 잠금 확인 및 해제

- 레코드 각각 락이 걸리므로 오래동안 잠금이 걸려 있어도 잘 발견되지 않는다.
    - 레코드 잠금과 잠금 대기를 조회해서 확인해야 된다.
- 위의 시나리오를 가정
    - 각 트랜잭션이 어떤 잠금을 기다릴 때 어떤 잠금을 어떤 트랜잭션이 가지고 있는지 메타 정보를 통해 조회 할 수 있음
    - iinodb_lock, innodb_lock_waits 대신 `data_locks` 와 `data_lock_waits` 로 대체 됨

```sql
# 어떤 잠금이 존재하는가?
select * from information_schema.innodb_locks;
# 어떤 트랜잭션이 어떤 client 에 의해 가동되는가
select * from information_schema.innodb_trx;
# 잠금에 의한 process 간 의존관계
select * from information_schema.innodb_lock_wait;
```

```sql
select 
	r.trx_id waiting_trx_id,
	r.trx_mysql_thread_id waiting_thread,
	r.trx_query waiting_query,
	b.trx_id blocking_trx_id,
	b.trx_mysql_thread_id blocking_thread,
	b.trx_query blocking_query
	from information_schema.innodb_lock_waits w
		inner join information_schema.innodb_trx b on b.trx_id = w.blocing_trx_id
		inner join information_schema.innodb_trx r on r.trx_id = w.requestiong_trx_id;
```

- (34A7) 100 번이 99 번 을 기다린다.
- (34A7) 100 번이 18 번을 기다린다.
- (34A6) 99 번이 18번을 기다린다.
- 따라서 18 번이 lock 을 풀어야 99 → 100 순서로 나아갈 수 있음

## MySQL의 격리 수준

- 격리 수준
    - 여러 트랜잭션이 동시에 처리될 때 특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회하는 데이터를 볼 수 있게 허용할지 말지
        - READ UNCOMMITTED
            - DIRTY READ
            - 일반적으로 잘 안씀
        - READ COMMITTED
        - REPEATABLE READ
        - SERIALIZABLE
            - 동시성이 중요한 데이터베이스에서 성능 상의 문제로 잘 안씀


### READ UNCOMMITTED

- A 가 Lara 를 insert 하고, 커밋 하기 도 전에 B 는 Lara 를 읽을 수 있다.
    - 만약 A 가 롤백을 해도 B 는 여전히 로직을 처리하고 있다.
    - 이를 Dirty Read 라고 한다.

### READ COMMITTED

- Oracle Default Isolation level
- commit 이 완료된 데이터만 조회
- A 가 Lara 를 Toto 로 바꿔도 undo log 에 있는 Lara 를 B 는 결과로 받는다.

- NON-REPEATABLE READ
- B 의 트랜잭션이 진행하면서 처음엔 toto 라는 이름을 select 했지만 결과가 없었다.
- 도중 A 가 Toto 를 update 하고 commit 했고
- 이후 B 의 트랜잭션 도중 다시 select 했을 때 결과를 받아 정합성에 어긋나게 된다.
- 입금된 금액의 총합을 계산하는 등의 문제에서는 큰 오류로 작동하게 된다.

### REPEATABLE READ

- InnoDB default isolation level
- NON REPEATABLE READ 발생하지 않음
- rollback 될 가능성을 염두해 undo log 에 백업해두고 실제 레코드 값을 변경한다.
    - MVCC 라고 한다.
- REPEATABLE READ 는 이 MVCC 를 위해 언두 영역에 백업된 이전 데이터를 이용해 동일 트랜잭션 내에서는 동일한 결과를 보여줄수 있게 보장한다.
- READ COMMITTED 도 MVCC 를 이용해 COMMIT 되기 전의 데이터를 보여준다.
- REPEATABLE READ 와 READ COMMITTED 의 차이는 언두 영역에 백업된 레코드의 여러 버전 가운데 몇 번째 이전 버전까지 찾아 들어가야 하느냐에 있다.
- 모든 InnoDB 트랜잭션은 고유한 trx 번호를 가지며 언두 영역에 백업된 모든 레코드에는 변경을 발생시킨 트랜잭션의 번호가 포함돼 있다.
    - 언두에 백업된 데이터는 InnoDB 가 불필요하다고 판단하는 시점에 주기적으로 삭제한다.
    - REPEATABLE READ 에서는 MVCC 를 보장하기 위해 실행 중인 트랜잭션 가운데 가장 오래된 트랜잭션 번호보다 트랜잭션 번호가 앞선 언두 영역의 데이터는 삭제할 수가 없다.
    - 정확하게는 특정 트랜잭션 번호의 구간 내에서 백업된 언두 데이터가 보존돼야 한다.

- 위의 그림을 통해 알아보자
    - B(trx : 10) 의 트랜잭션 도중
    - A(trx : 12) 가 Lara → Toto 로 update
        - undo 에는 Lara 가 저장된다.
    - A 가 커밋을 했다.
    - B 가 다시 select 해도 undo 에 있는 Lara 를 참조하게 된다.
        - trx 10 보다 작은 트랜잭션 번호에서 변경한 것만 보게 된다.
- 하나의 레코드에 백업이 하나 이상 얼마든지 존재할 수 있다.
    - 한 사용자가 트랜잭션을 시작하고 장시간 종료하지 않으면
    - 언두 영역이 백업된 데이터로 무한정 커질 수도 있다.

- REPEATABLE READ 에서도 다음과 같은 부정합이 발생할 수 있다.
- B 가 처음 select .. FOR UPDATE 을 했을 때에는 결과가 1건이었지만
- A 가 insert 한 이후 select 을 하면 2개로 반환 받게 된다.
- 이러한 것을 PHANTOM READ || PHANTOM ROW 라고 한다.
- select … FOR UPDATE 쿼리는 레코드에 쓰기 잠금을 걸어야 하는데 언두 레코드에는 잠금을 걸 수 없다.
    - SELECT…FOR UPDATE 나 SELECT LOCK IN SHARE MODE 로 조회되는 레코드는 현재 레코드의 값을 가져오게 된다.

### SERIALIZABLE

- 단순, 가장 엄격한 격리
- 성능이 가장 떨어짐
- Non-locking consistent read
- 읽기 작업도 공유 잠금을 획득해야 하며
    - 동시에 다른 트랜잭션은 레코드를 변경하지 못하게 된다.
- 한 트랜잭션에서 읽고 쓰는 레코드를 다른 트랜잭션에서는 절대 접근할 수 없다.
- InnoDB 스토리지 엔진에서는 갭 락과 넥스트 키 락 덕분에 REPEATABLE READ 에서도 PHANTOM READ 가 발생하지 않기 때문에 굳이 SERIALIZABLE 을 사용할 필요성은 없어 보인다.
    - PHANTOM READ 는 SELECT … FOR UPDATE, SELECT … FOR SHARE 에서는 발생할 수도 있지만 외의 상황에서는 발생하지 않음