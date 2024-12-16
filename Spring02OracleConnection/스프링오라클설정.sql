-- 연습용 User 만들기
create user spring_ex IDENTIFIED by spring_ex
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp;
-- 계정 접속 권한 부여
grant connect, dba to spring_ex;
-- 변경된 계정으로 다시 접속 => 접속 해제후 spring_ex / spring_ex로
show user;
-- 포트번호 확인
select dbms_xdb.gethttpport() from dual;
-- 8080일 경우 포트번호 변경
exec dbms_xdb.sethttpport(9080);