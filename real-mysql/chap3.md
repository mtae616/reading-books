# 사용자 및 권한

사용자 계정을 생성, 권한 설정하는 방법이 다른 DBMS 와 조금 다름

## 사용자 식별

- 사용자의 계정뿐 아니라 사용자의 접속 지점(호스트 명이나 도메인 또는 IP) 또한 계정의 일부이다.
- MySQL 계정을 언급할 때는 항상 아이디와 호스트를 함께 명시해야 한다.

```bash
`svc_id`@'127.0.0.1'
```

- 위의 예시는 로컬호스트로만 접근 가능
- 만약 모든 IP 에서 접근하고 싶을 때에는 ‘%’ 를사용하면 된다.

```bash
'svc_id'@'192.168.0.10' # 비밀번호 123
'svc_id'@'%' # 비밀번호 abc
```

- 위처럼 동일한 계정이 2개가 있다면 MySQL은 범위가 가장 작은 것을 항상 먼저 선택한다.
- 사용자가 192.168.0.10 인 PC 에서 svc_id 와 비밀번호 abc 로 접근한다면 접속이 거절된다.
    - IP 할당된(범위가 좁은 것이) 192.168.0.10 이기 때문에

## 사용자 계정 관리

- MySQL 8.0 부터 계정은 SYSTEM_USER 권한을 갖고 있느냐에 따라 시스템 계정(System Account) 와 일반 계정 (Regular Account) 로 구분된다.
- 시스템 계정은 MySQL 서버 내부적으로 실행되는 백그라운드 스레드와는 무관하며 데이터베이스 서버 관리자 계정을 나타낸다. (DBA)
- 기능
    - 계정 관리 (계정 생성 및 삭제, 계정의 권한 부여 및 제거)
    - 다른 세션(Connection) 또는 그 세션에서 실행 중인 쿼리를 강제 종료
    - stored program 생성 시 definer 를 타 사용자로 설정
- 내장 객체
    - `‘mysql.sys’@’localhost’`
        - 8.0 기본 내장된 sys 스키마의 객체(뷰나 함수, 프로시저)들의 DEFINER 로 사용되는 계정
    - `‘mysql.session’@’localhost’`
        - MySQL 플러그인이 서버로 접근할 때 사용되는 계정
    - `‘mysql.infoschema’@’localshot’`
        - information_schema에 정의된 뷰의 DEFINER로 사용되는 계정
    - 위의 3 계정은 처음부터 잠겨있으므로 보안 걱정은 하지 않아도 된다.

## 계정 생성

- 8.0 부터는 생성은 CREATE USER, 권한 부여는 GRANT 로 따로 줘야 함
- 계정 생성 옵션
    - 계정의 인증 방식과 비밀번호
    - 비밀번호 관련 옵션
    - 기본 역할
    - SSL 옵션
    - 계정 잠근 여부
- IDENTIFIED WITH
    - IDENTIFIED WITH 뒤에는 반드시 인증 방식 (인증 플러그인의 이름) 을 명시
    - 기본은 password
    - 다음 4가지가 가장 대표적이다.
        - Native Pluggable Authentication
            - 단순히 비밀번호에 대한 해시 값을 저장해두고 클라이언트가 보낸 값과 해시값이 일치하는지 비교하는 인증 방식
        - Caching SHA-2 Pluggable Authetication
            - Salt + Key Stretching 를 사용하는 SHA-2 hash
            - 해시 결과값을 메모리에 캐시해서 사용한다.
            - 이 방식을 사용하려면 SSL/TLS 또는 RSA 키페어를 반드시 사용해야 한다.
        - PAM Pluggable Authentication
            - 유닉스나 리눅스 패스워드 또는 LDAP 같은 외부 인증을 사용하게 한다.
        - LDAP Pluggable Authentication
            - LDAP 를 이용한 외부 인증을 사용하게 한다.
    - 8.0 에서 기본은 Caching SHA-2 Authentication 이 기본이므로, Native 로 사용하려면 my.cnf 에 셋팅해야 한다.
- REQUIRE
    - SSL/TLS 채널을 사용할지
    - Caching SHA-2 Authentication 을 사용하면 암호화된 채널 만으로 접근한다.
- PASSWORD EXPIRE
    - 비밀번호 유효기간 명시
    - 설정하지 않으면 `default_password_lifetime` 시스템 변수에 저장된 기간으로 설정된다.
- PASSWORD HISTORY
    - 한 번 사용했던 비밀번호는 재사용하지 못하게 하는 옵션
- PASSWORD REUSE INTERVAL
    - 한 번 사용했던 비밀번호의 재사용 금지 기간 설정
- PASSWORD REQUIRE
    - 비밀번호가 만료되어 새로운 비밀번호로 변경할 때 현재 비밀번호를 필요로 할지 말지 결정
- ACCOUNT LOCK / UNLOCK
    - 계정 생성 시 또는 ALTER USER 명령을 사용해 계정 정보를 변경할 때 계정을 사용하지 못하게 잠글지 여부

## 비밀번호 관리

- 고수준 비밀번호
    - 비밀번호 유효기간, 이력 관리를 통한 재사용 금지, 비밀번호를 쉽게 유추할 수 있는 단어들이 사용되지 않게 글자의 조합을 강제하는 금칙어 등
    - validate_password 컴포넌트 사용
- 이중 비밀번호 Dual Password
    - 8.0 부터는 서버를 재시작하지 않고도 비밀번호 변경이 가능함
    - 계정의 비밀번호로 2개를 동시에 쓰는 것
    - primary, secondary 로 구분

## 권한

- 객체 권한 (테이블을 제어하는 데 필요한 권한)
- 글로벌 권한(DB 나 테이블 이외의 객체에 적용되는 권한)

## 역할

- 8.0 부터 권한을 묶어서 역할로 관리할 수 있다.

```bash
# Role 생성
CREATE ROLE
	role_emp_read,
	role_emp_write

# Role 에 권한 부여
GRANT SELECT ON emplyees.* TO role_emp_read;
GRANT INSERT, UPDATE, DELETE ON employees.* TO role_emp_write;

# User 에 role 부여
GRANT role_emp_read TO reader@'127.0.0.1';
GRANT role_emp_read, role_emp_write TO writer@'127.0.0.1';
```