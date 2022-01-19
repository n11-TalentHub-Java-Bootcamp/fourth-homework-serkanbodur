# fourth-homework
n11-TalentHub-Java-Bootcamp

### Project Description

This project is a project using Spring and Postgresql that can do basic CRUD operations such as insert,update,delete,find

### Project Structure

- Entity
- DTO
- Repository
- Service
- Controller
- Exceptions

### Basic Usage

- Run project once to create the tables and ddl operations
- All tables will create and table insert ddl operations will happen automatically as data.sql files.
- Apply the operations according to appropriate endpoints using Postman or Swagger.

[User Paths](fourth-homework/src/main/java/com/example/fourthhomework/controller/UserController.java)
| Request Method | Route                          | Request Body                                                                                                                                  | Description                            |
|----------------|--------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------|
|       GET      | /api/v1/users/                    | {  }                                                                                                                                       | Get All Users                          |
|      POST      | /api/v1/users/                    | {  "name" : "test_user", "surname" : "test_user_sur", "email" : "test@mail.com" , "phone" : "2233" , "username" : "tested"  }              | Save a User                            |
|       PUT      | /api/v1/users/{id}                | {  "name" : "updated_user" , "surname" : "updated_sur" , "email" : "updated@mail.com" , "phone" : "6060" , "username" : "updated_usrnm" }  | Update a User using id                 |
|     DELETE     | /api/v1/users/{id}                | {  }                                                                                                                                       | Delete a User using id                 |


[Debt Paths](fourth-homework/src/main/java/com/example/fourthhomework/controller/DebtController.java )

| Request Method | Route                               | Request Body                                                                                                                                                                                                                | Description                                                        |
|----------------|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
|       GET      | /api/v1/debts/createdDate           | {  }                                                                                                                                                                                                                        | Get Debts between of given startDate and endDate using createdDate |
|       GET      | /api/v1/debts/user                  | {  }                                                                                                                                                                                                                        | Get a user's debts using userId                                    |
|       GET      | /api/v1/debts//userOverdue          | {  }                                                                                                                                                                                                                        | Get a user's overdue debts using userId                            |
|       GET      | /api/v1/debts/userInstantTotal      | {  }                                                                                                                                                                                                                        | Get a user's total debts in that moment using userId               |
|       GET      | /api/v1/debts/userOverdueTotal      | {  }                                                                                                                                                                                                                        | Get a user's total overdue debts using using userId                |
|       GET      | /api/v1/debts/userInstantLateRaise  | {  }                                                                                                                                                                                                                        | Get a user's total late raise in that moment using using userId    |
|      POST      | /api/v1/debts/userInstantLateRaise  | {   "id": 1, "mainDebtAmount": 100, "realDebtAmount": 100,"createdDate": "2022-01-19T15:16:51.390Z", "expiryDate": "2022-01-19T15:16:51.390Z", "debtType": "NORMAL", "userId": 1, "mainDebtId": null, "collectionId": 1   } | Save a Debt                                                        |

[Collection Paths](fourth-homework/src/main/java/com/example/fourthhomework/controller/CollectionController.java )

| Request Method | Route                              | Request Body                                                            | Description                                                                 |
|----------------|------------------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------------|
|       GET      | api/v1/collections/collectionDate  | {  }                                                                    | Get collections between of given startDate and endDate using collectionDate |
|       GET      | api/v1/collections/user            | {  }                                                                    | Get collections belong a user using userId                                  |
|       GET      | api/v1/collections/totalLateRaise  | {  }                                                                    | Get a user's total collections using userId                                 |
|      POST      | api/v1/collections/                | {    "id": 1,"collectionDate": "2022-01-19T15:21:24.412Z","debtId": 1 } | Save a Collection                                                           |