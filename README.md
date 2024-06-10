
## LearningNavigator - Exam Registration API Service :

### Overview
This project is a simplified RESTful API service designed to manage the exam enrollment process for a Learning Management System (LMS). The service is developed using Spring Boot and utilizes MySQL for data persistence. The API handles CRUD operations for Students, Subjects, and Exams and includes features such as thorough authentication, authorization, and error handling. An additional easter egg feature using the Numbers API is also implemented.
 
## Features

- CRUD operations for Students, Subjects, and Exams
- Foreign key relationships between entities
- Students can only register for exams after enrolling in the corresponding subject
- Graceful error handling with appropriate HTTP status codes
- Global exception handling using @ControllerAdvice and GlobalExceptionHandler
- Basic unit tests with MockMvc and Mockito
- Easter egg feature to generate random facts about numbers via the Numbers API
## Requirements :
### Students 
- Student Registration ID (Unique Identifier)
- Student Name
- List of enrolled Subjects
- List of registered Exams

### Subjects 
- Subject ID (Unique Identifier)
- Subject Name
- List of registered Students

### Exams
- Exam ID (Unique Identifier)
- Subject
- List of enrolled Students

### Endpoints:
- POST /exams/{examId}: Registers a student for the specific exam
- Easter Egg Feature: GET /hidden-feature/{number}: Generates a fact about the number passed as the path parameter
## Setup Instructions :

### Prerequisites

- Java 11 or higher
- Gradle 6.0.8 or higher
- MySQL 8.0 or higher
- Git
- Postman (optional, for API testing)


To run the application follow the following steps :

### Local Environment Setup :
#### 1. Clone the repository :

    git clone https://github.com/Omkar2363/LearningNavigator.git


### 2. Set up the MySQL database :

- Update the application.properties file with your MySQL database details.

      spring.datasource.url=jdbc:mysql://localhost:3306/learningnavigator?createDatabaseIfNotExist=true 
      spring.datasource.username=<your-userName> 
      spring.datasource.password=<your-password> 
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 
      spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

#### 2. Navigate to the project directory :

    cd LearningNavigator

#### 3. Build the project :

    gradle build 

#### 4. Run the application :

    gradle bootrun



## Running Tests :

To run the unit tests :

    gradle test
## Usage :

### API Endpoints :

#### 1. Student Endpoints

- GET /students: Retrieve all students
- GET /students/{id}: Retrieve a specific student by ID
- POST /students: Create a new student
- PUT /students/{id}: Update an existing student
- DELETE /students/{id}: Delete a student by ID

#### 2. Subject Endpoints
- GET /subjects: Retrieve all subjects
- GET /subjects/{id}: Retrieve a specific subject by ID
- POST /subjects: Create a new subject
- PUT /subjects/{id}: Update an existing 
- PUT /subjects/{subjectId}/enrollStudent/{studentId}: Update the subject student list with the student having studentId {studentId}
- DELETE /subjects/{id}: Delete a subject by ID

#### 3. Exam Endpoints
- GET /exams: Retrieve all exams
- GET /exams/{id}: Retrieve a specific exam by ID
- POST /exams: Create a new exam 
- PUT /exams/{id}: Update an existing exam
- PUT /exams/{examId}/register/{studentId}: Update the exam's studentList with the student having studentId as {studentId} 
- DELETE /exams/{id}: Delete an exam by ID

#### 4. Easter Egg Feature
- GET /hidden-feature/{number}: Generates a fact about the number passed as the path parameter
## Postman Collection

A Postman collection has been included to test the API endpoints. Import the collection into Postman and start testing the API.

[LearningNavigator Postman Collection Link](https://www.postman.com/omkar2363/workspace/learningnavigator/collection/28208818-8b7dc279-9892-4c91-841f-00ef055d64de?action=share&creator=28208818)
## Project Structure

The project follows a layered architecture approach:

- `entity` : Contains the User entity class.
- `controller`: Handles incoming HTTP requests and responses.
- `service`: Contains the business logic for managing users and leaderboards.
- `repository`: Interacts with the MongoDB database.
- `exception` : Contains the custom exceptions and exception handler classes. 



## Implemented Code

#### Controllers
- StudentController, SubjectController, ExamController, and EasterEggController contain endpoints for managing students, subjects, exams, and the Easter egg feature, respectively.

#### DTOs
- StudentDto, SubjectDto, and ExamDto are Data Transfer Objects representing student, subject, and exam entities.

#### Entities
- Student, Subject, and Exam are JPA entities representing student, subject, and exam entities, respectively.

#### Exception Handling
- GlobalExceptionHandler handles custom exceptions such as ResourceNotFoundException.

#### Repositories
- StudentRepository, SubjectRepository, and ExamRepository provide CRUD operations for student, subject, and exam entities, respectively.

#### Services
- StudentService, SubjectService, and ExamService contain business logic for managing students, subjects, and exams, respectively.

#### Utility
- RestClientConfig provides configuration for the RestTemplate.
- ModelMapperConfig provides configuration for the ModelMapper.

#### Unit Tests
- Include basic unit tests using MockMvc for controllers and Mockito for ServiceLayers.

#### Main Application
LearningNavigatorApplication is the main Spring Boot application class.

## Validation and Error Handing

- Basic validation is implemented to check student is registered for subject or not before registering for the exam of the subject.
- Common errors are handled, and appropriate HTTP status codes are returned (e.g., 404 for User not found).

## Licence

All the copy rights belongs to Omkar Yadav.