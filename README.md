# 🔍 Spring Academy API Project

## Our Project
In this project, we have developed a REST API and Database Model to simulate an online academy. We have three tables in our Database: Trainers, Trainees, and Courses. 
These can be accessed through a front-end host, or using swaggerUI implementation for the back-end endpoints

## 🚀 How To Set Up The Project
### Prerequisites
- npm (>=6)
- Node.js (>=14)
- Intellij IDE
### Installation
```bash
git clone https://github.com/emilia-green/spring-academy-api-project.git
```
- To set up this project, you will need access to IntelliJ Ultimate Edition in order to run the Spring Boot features.

If using this IDE, enter into the backend folder (establish Maven project), and then run in Intellij.
This shall allow access to the `http://localhost:8091/swagger-ui/index.html#/` page.

- To run and use the frontend user-interface, after cloning the repository:
```bash
cd frontend
npm install
npm run dev
```

#### **Alternatively** - Run with Docker
- If you have docker desktop installed on your computer, the application can be run with a simple command, building and executing the front and back-ends simultaneously. After cloning the repository, just run (in the root directory of the app) :
```bash
docker-compose up --build
```

And the project will run on the same localhost ports!

## 🧩 Tech Stack
- React/JS - Front-end display of tables
- Java/Spring Boot - In-memory database formulation, services, controllers, DTOs and data loader. OpenAPI configuration with Swagger UI
- GitHub - Version control in collaborative environment
- Amazon AWS - Creation of EC2 instances for deploying both the front-end and back-end (manually) on the cloud
- Docker Desktop - Containerisation of the application, building the full application at once.

## 💡 Future Enhancements for Retrospective
- Further extensions of database tables, implementing attributes, furthering relationships between tables for more in-depth application.
- Extending the front-end interface, extending styling and routing to accomodate further changes and usability - more demonstrative of functionality currently.
- Containerisation of project, to deploy back-end and front-end together - extended to a cloud environment again.
- Use of Jenkins and/or GitHub Actions for CI/CD pipelines for the collaborative nature of the project.


