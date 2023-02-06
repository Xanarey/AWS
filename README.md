Amazon Simple Storage Service (Amazon S3) is an object storage service that offers industry-leading scalability, data availability, security, and performance.

<table>
<tr>
  <th>Applied technologies:</th>
  <td>
      <a href="#java">Java 17</a> 
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

#### JSON Function Data Source
Here is an example:

```cucumber
Authorization - BasicAuth

	User:
	
GET:        http://localhost:8080/api/v1/users/
POST:       http://localhost:8080/api/v1/users/
[POSTMAN: Body, raw, JSON]
{
    "status": "ACTIVE",
    "username": "New",
    "firstName": "nosdfsdfviu",
    "lastName": "userok",
    "email": "userity.com44444444444",
    "password": "$2b$10$nVj6KAxKUPyxVtN5Yug8NOC.sHlzjWmJFBYKRvhWksbffsyncVAbq",
    "role": "USER"
}
PUT:        http://localhost:8080/api/v1/users/
{
    "id": "6",
    "username": "Old",
    "firstName": "nosdfsdfviu",
    "lastName": "user",
    "email": "userity.com44444444444",
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
    "created": "2029-01-19 17:06:56",
    "updated": "2029-01-19 17:06:56",
    "status": "ACTIVE",
    "user": 
    {
        "id": 3
    },
    "file":     
    {
        "name": "UwUwUAdmin.rar",
        "status": "ACTIVE"
    }
}
PUT:    http://localhost:8080/api/v1/events/
    {
        "id": 5,
        "created": "2022-01-19 17:06:56",
        "updated": "2023-02-06 17:06:56"
    }