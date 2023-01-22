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
	User:
update:
{
     "id": 12,
     "username": "Angel"
}
create:
{
    "status": "ACTIVE",
    "username": "TomEXP",
    "firstName": "Tom",
    "lastName": "Zakker",
    "email": "tomEXP90@gmail.com",
    "password": "$2b$12$QsrpXXXXXXXXXXXXXXXXn1NvlTbFNrMBeCec.n/Mh9f.esssz8q",
    "role": "ADMIN"
}
	Event:
create:
{
    "created": "2029-01-19 17:06:56",
    "updated": "2029-01-19 17:06:56",
    "user": 
    {
        "id": 3,
        "username": "Boris",
        "firstName": "Borya",
        "lastName": "Borievych",
        "email": "boris@gmail.com",
        "status": "ACTIVE",
        "roleName": "USER"
    },
    "file":     
    {
        "id": 11,
        "name": "3.1.xlsx",
        "status": "ACTIVE"
    }
}
update:
    {
        "id": 8,
        "created": "2022-01-19 17:06:56",
        "updated": "2022-01-19 17:06:56"
    }