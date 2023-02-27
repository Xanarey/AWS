JWT - JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.

AWS - Amazon Web Services,  S3 - Amazon Simple Storage Service (Amazon S3) is an object storage service that offers industry-leading scalability, data availability, security, and performance.


<table>
<tr>
  <th>Applied technologies:</th>
  <td>
    |  <a href="#java">Java 17</a> 
    |  <a href="#JWT">JWT</a> 
    | <a href="#maven">Maven</a>
    | <a href="#MySQL">MySQL</a>
    | <a href="#Spring">Spring  (IoC, Data, Security)</a>
    | <a href="#AWS SDK">AWS SDK</a>
    | <a href="#Docker">Docker</a>
    | <a href="#JUnit">JUnit</a>
    | <a href="#Mockito">Mockito</a>
  </td>
</tr>
</table>

Pre settings:
1) IDEA: Run, Edit Configurations, Modify options, Add VM options:
-DMYSQL_USER=root
   -DMYSQL_PASSWORD=password
   -DMYSQL_PORT=3307
2) IDEA: Run, Edit Configurations, Modify options, Environment variables:
-'aws access key'
-'aws secret key'

#### JSON Function Data Source
Here is an example:

```cucumber

Register
http://localhost:8080/api/v1/auth/register

{
    "username": "Tom",
    "firstName": "Tomas",
    "lastName": "Anderson",
    "email": "tom.dev@gmail.com",
    "password": "password",
    "role": "ADMIN"
}

{
    "username": "Tom",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCb3JpcyIsInJ
    vbGUiOlsiRklMRVNfVVBEQVRFIiwiRklMRVNfUkVBRF9BTEwiLCJFV
    kVOVFNfUkVBRF9BTEwiLCJGSUxFU19VUExPQUQiLCJGSUxFU19ERUx
    FVEUiLCJGSUxFU19ET1dOTE9BRCJdLCJpYXQiOjE2Nzc0OTc2MTMsI
    mV4cCI6MTY3NzUwMTIxM30.l2qna-Ys6RgOoBfLKrwBhXbPcmB2rqS
    Mml-BpIzNh28"
}

Set the returned token to the header
   POSTMAN
   Header
Authorization     Bearer_  <- "your token"

	User:
	
GET:        http://localhost:8080/api/v1/users/
POST:       http://localhost:8080/api/v1/users/
[POSTMAN: Body, raw, JSON]
{
    "status": "ACTIVE",
    "username": "Anna",
    "firstName": "Anna",
    "lastName": "Chikson",
    "email": "ann.hr@gmail.com",
    "password": "$2b$10$nVj6KAxKUPyxVtN5Yug8NOC.sHlzjWmJFBYKRvhWksbffsyncVAbq",
    "role": "MODERATOR"
}
PUT:        http://localhost:8080/api/v1/users/
{
    "id": "6",
    "username": "Old",
    "firstName": "OldUser",
    "lastName": "Old",
    "email": "old.user.@gmail.com",
    "password": "$2b$10$nVj6KAxKUPyxVtN5Yug8NOC.sHlzjWmJFBYKRvhWksbffsyncVAbq",
    "role": "USER"
}
PATCH:       http://localhost:8080/api/v1/users/6
DEL:      http://localhost:8080/api/v1/users/7

    Files:
    
    Get records
GET:    http://localhost:8080/api/v1/files/

    DOWNLOAD
GET:    http://localhost:8080/api/v1/files/5
    UPLOAD
POST:   http://localhost:8080/api/v1/files/
[POSTMAN: Body, form-data, VALUE: path.../, KEY: file]

DEL:    http://localhost:8080/api/v1/files/5

    Events:
    
GET:    http://localhost:8080/api/v1/events/
DEL:    http://localhost:8080/api/v1/events/5
POST:   http://localhost:8080/api/v1/events/
{
    "created": "2023-01-19 17:06:56",
    "updated": "2023-01-19 17:06:56",
    "status": "ACTIVE",
    "user": 
    {
        "id": 3
    },
    "file":     
    {
        "name": "Config.rar",
        "status": "ACTIVE"
    }
}
PUT:    http://localhost:8080/api/v1/events/
    {
        "id": 5,
        "created": "2022-01-19 17:06:56",
        "updated": "2023-02-06 17:06:56"
    }