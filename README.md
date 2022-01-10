# boot-JPA_blog-project

## stacks: -

> Spring security

> Springboot

>JPA

>JAVA

>CSS3

>HTML5

>Bootstrap

>Java script

>AJAX

>J query

## DATABASE 

>MySQL

### 로직
>로직
1. request 시 컨트롤러가 요청을 받고 영속성컨텍스트 시작, 요청을 분기시켜 그에 맞는 서비스를 호출시킨다.
2. 이때 JDBC 커넥션 및 트렌젝션이 시작된다.
3. JPA는 영속성 컨텍스트에 맞는 데이터 값을 저장
4. 필요 서비스를 실행, 컨트롤러로 돌아오고 직전에 트렌젝션 및 디비 연결 세션이 종료 됌.
5. 컨트롤러가 리스폰스 하기 직전 트렌젝션이 종료되었기 때문에 변경을 감지하게 된다.
6. 영속화된 객채의 데이터가 변경되었기 때문에  데이터베이스에 flush 시킨다.
7. 프로그램이 종료되고 서비스가 정상적으로 수행된다.
