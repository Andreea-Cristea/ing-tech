# Authentication

The authentication is developed as a Spring Boot 2.0 service
- is a spring boot application responsible for authenticating the user by looking at the roles the user has and acting accordingly


# Interface Design

### REST
It has the following RESTs:

|       API   |    URL                |Description                                                  |
|-------------|---------------------- |-----------------------------------------------------        |
|     GET     | /info                 | Returns the info page for the users that have the ROLE_USER                                                             |
|     GET     | api/v1/login          | The login page for the user                                 |
|     GET     | /home                 |The home page for the user. After the users logs out,the user is redirected to the home page                          |
|     POST    | api/v1/registration   | The registration page for the user                          |

The user details are validated , making sure that a valid inputs are being processed.

## Detail Design



### Functionality

The api/v1/registration endpoint is called for the registration of a user.
Possible input:
```json {

"firstName": "Jane",

"lastName": "Dow",

"email": "janeDoe@mail.com",

"password": "password"

}
```
The null and empty inputs are not allowed. The email is also validated in order to respect a valid email structure. 
The inputs are saved in a database and the password is encoded in order to enhance security.
The api/v1/login endpoint is called for login. 
Posibble input: 
```json {

{

"email": "janeDoe@gmail.com",

"password": "pass"

}
```
Appropiate messages are thrown if the email is not found in the database and if the password does not match for the email saved.

Only users with the `ROLE_USER` can access the /info endpoint. A 403 Http Status is shown otherwise.
The /home endpoint is where the user is redirected when the user logs out. 

### Security concerns

The application uses spring-security and is taking advantage of it by creating specific roles for the user in order to secure the endpoints.
The following roles are defined:


|       Type   |      Endpoint        |               
|-------------|----------------------|
|     ROLE_USER     |/info          |                                   