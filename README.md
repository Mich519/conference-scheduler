# Confere scheduler

## How to run

Navigate to root directory and run 

```
./mvnw spring-boot:run
```

## Usage


1. Retrieve conference plan 
```
GET localhost:8080/api/lectures 
```

2. Retrieve lectures for which the student is signed up
```
GET localhost:8080/api/users/{username}/lectures

Example: 
localhost:8080/api/users/john/lectures
```

3. Sign up existing user for a lectures.
```
PUT localhost:8080/api/users/{username}/lectures

Example:
localhost:8080/api/users/john/lectures

{
    "username": "john",
    "email": "john@newmail",
    "lectureIds": [3]
}
```

4. Cancel reservation for a user
```
DELETE localhost:8080/api/users/{username}}/lectures/{lectureId}

Example:
localhost:8080/api/users/john/lectures/1
```


5. Change user email adress
```
PATCH localhost:8080/api/users/{username}}

Example:
localhost:8080/api/users/anna

{
    "email": "anna123@mymail"
}
```

6. Retrieve all users
```
GET localhost:8080/api/users
```

7. Retrieve user attendence report for a single lecture
```
GET localhost:8080/api/lectures/attendance-report
```  

8. Retrieve user attendence report for a single topic
```
GET localhost:8080/api/lectures/topic-attendance-report
```



