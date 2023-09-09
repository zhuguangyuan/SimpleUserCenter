# user authentication service
`a simple user/role manage and auth center. Also can add roles for user. Write in Java.`

## features
- create/delete users 
- create/delete roles
- user authentication/invalidate token
- grant roles to users, or check if use have specific role

## core design
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

## requirement
- java 8+
- gradle
- IDEA

## unit test

## usage
- clone the repo
- build project locally
- run `AuthApplication`
- goto [swagger page try the API]()
