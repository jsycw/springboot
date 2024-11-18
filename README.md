# springboot-microservices

This project is an authentication and user management service built using the Spring Boot framework. It uses JWT (JSON Web Tokens) and Spring Security for secure user authentication and authorization. The service provides RESTful API endpoints to perform CRUD (Create, Read, Update, Delete) operations on user data.

## How to Run the Application

### 1. **Run the Application in Your IDE**

   - Open the project in your preferred IDE (e.g., IntelliJ IDEA).
   - Make sure you have Java 17+ installed.
   - Run the `SpringBootApplication` class or the main application file.
     

---

### 2. **Open Postman to Test the API**

   - Open Postman (you can download it from [here](https://www.postman.com/downloads/)).
   - Set the URL to `http://localhost:8080` to connect to your locally running Spring Boot application.


---

### 3. **Register a New User**

   - To register a new user, send a `POST` request to the `/api/users/register` endpoint.
   - The request body should contain the user information as defined in the **User** class (e.g., username, password, email, etc.).
   - If successful, you will receive the newly registered user object in the response.


---

### 4. **Login with Your Account**

   - To log in, send a `POST` request to the `/api/auth/login` endpoint with the username and password in the request body.
   - If the login is successful, you will receive a response with the user details and an authentication token.

---

### 5. **View User Details**

   - After logging in, you can retrieve your user details by sending a `GET` request to the `/api/users/profile` endpoint.
   - Add the token you received during login to the Authorization header of the request. The token should be prefixed with the string `Bearer `.

---

### 6. **Update User Profile**

   - To update your user profile (e.g., change email, username, etc.), send a `PUT` request to the `/api/users/profile/update` endpoint.
   - Include the token in the Authorization header, as described in step 5.
   - In the body of the request, include the fields you want to update (e.g., email, username).


---

