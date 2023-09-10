# user authentication service

`a simple user/role manage and auth center. Also can add roles for user. Write in Java.`

## features

- create/delete users
- create/delete roles
- user authentication/invalidate token
- grant roles to users, or check if use have specific role

## core design
- basic consideration
  - create/delete users
      - user password encrypt, use `SHA-256 + salt`
  - create/delete roles
      - can not delete role when role in use by more than one user
  - user authentication/invalidate token
      - login status maintain by a token map
      - token generate, using `UUID`
      - token expire check timely
  - grant roles to users, or check if use have specific role
      - each user has a role set
- project structure
  - controllers package provide all APIs
  - services package impl the api, contains UserService、RoleService、AuthService、UserRoleService
  - exceptions package define ErrCode and AuthException and handler
  - models define User/Role entity and web req/rsp
  - tasks define one task for login token remove after expire
  - configs define some properties config via `application.yaml`
  - utils contains CryptUtils, use for user's password encrypt 


## external libs used
- spring boot: web dev
- lombok: reduce boilerplate code
- logback: log impl
- swagger: for API-ui generate

## requirement

- java 8+
- gradle
- IDEA

## unit test
- use `Mockito`, All service methods have test coverage

## usage

- clone the repo

  ```shell
  git clone https://github.com/zhuguangyuan/SimpleUserCenter.git
  ```

- build and compile project locally(os is window as an example)
  ```shell
  .\gradlew.bat compileJava
  .\gradlew.bat compileTestJava
  .\gradlew.bat test
  ```
- run `AuthApplication`
- goto [swagger page and try the API](http://localhost:8080/swagger-ui/index.html)
