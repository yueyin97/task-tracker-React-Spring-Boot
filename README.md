# Task Tracker Project

This is a task tracker project built using React and Spring Boot. The purpose of this project is to provide users with the ability to create and manage tasks.

## Features

Add task, set reminder, remove task

<img width="343" alt="tasks2" src="https://user-images.githubusercontent.com/67276343/210543831-98e929ed-4df5-42b8-a7df-d0dfedfd3241.PNG">

## Installation

To run this project locally, you'll need to follow these steps:

1. Clone this repository to your local machine.
2. Install the required dependencies by running `npm install` in the `task-tracker-client` directory and `mvn install` in the `task-tracker-server` directory.
3. Create a new MySQL database with the name `task_tracker`.
4. Open the `application.properties` file located in `task-tracker-server/src/main/resources` and configure the database connection settings.
5. Run the project using the command `mvn spring-boot:run` in the `task-tracker-server` directory and `npm start` in the `task-tracker-client` directory.
6. Access the website by visiting `http://localhost:3000/` in your web browser.

## Credits

This project was built using the following technologies:

- React
- Spring Boot
- H2
- Bootstrap

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
