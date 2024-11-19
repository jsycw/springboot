# Microservices - Springboot

This project is an authentication and user management service built using the Spring Boot framework. It uses JWT (JSON Web Tokens) and Spring Security for secure user authentication and authorization. The service provides RESTful API endpoints to perform CRUD (Create, Read, Update, Delete) operations on user data.

## How to Run the Application

### 1. **Run the Application in Your IDE**

   - Open the project in your preferred IDE (e.g., IntelliJ IDEA).
   - Run the main application file.
     

---

### 2. **Open Postman to Test the API**

   - Open Postman
   - Click the symbol '+' on the top left corner.
   - Set the URL to `http://localhost:8080` to connect to your locally running Spring Boot application.
     
     ![image](https://github.com/user-attachments/assets/44c1e255-5be0-4847-bcc8-0a1f3ed101ff)
---

### 3. **Register a New User**

   - To register a new user, send a `POST` request to the `/api/users/register` endpoint.
   - The request body should contain the user information as defined in the **User** class (e.g., username, password, email, etc.).
   - If successful, you will receive the newly registered user object in the response.

     ![image](https://github.com/user-attachments/assets/a36adfc4-7a44-44a8-b4a5-959066852790)




---

### 4. **Login with Your Account**

   - To log in, send a `POST` request to the `/api/auth/login` endpoint with the username and password in the request body.
   - If the login is successful, you will receive a response with the user details and an authentication token.
     
     ![image](https://github.com/user-attachments/assets/70c9d84f-cc3d-4f1f-ab08-a9fd50c77331)

---

### 5. **View User Details**

   - After logging in, you can retrieve your user details by sending a `GET` request to the `/api/users/profile` endpoint.
   - Add the token you received during login to the Authorization header of the request. The token should be prefixed with the string `Bearer `.
     
     ![image](https://github.com/user-attachments/assets/c68ed154-8ddd-4196-98b7-488a801a162d)


---

### 6. **Update User Profile**

   - To update your user profile (e.g., change email, username, etc.), send a `PUT` request to the `/api/users/profile/update` endpoint.
   - Include the token in the Authorization header, as described in step 5.
   - In the body of the request, include the fields you want to update (e.g., email, username).
     
     ![image](https://github.com/user-attachments/assets/0843cc89-10d5-4e50-8e44-33e07870f068)

---

