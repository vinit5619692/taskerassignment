
## Author
[Vinit Agarwal](https://github.com/vinit5619692)

# Tasker

This (Tasker) is web application to maintain and track tasks.
User can do Create, Read, Update and Delete on task using responsive application.
Screenshot of pages, from the application, are available [here](https://github.com/vinit5619692/taskerassignment/tree/main/screenshot).
Entire project is committed at [GitHub](https://github.com/vinit5619692/taskerassignment).


# Tiers of Application

1. Presentation tier - UI is powered using OJET 11.
2. Application tier - Endpoints are created with DropWizard framework.
3. Data tier - MySQL is used to persist data.


# How to start locally

Docker is configured to containerize all the tiers, which makes it easy to run entire application anywhere.

## Prerequisite software
To spin up this project locally, these are the prerequisite software
1. Docker.
2. Intellij (or any other IDE).
3. ojet ([how to install](https://docs.oracle.com/en/learn/jet-install-cli/index.html#task-3-verify-the-oracle-jet-command-line-interface)).
4. Postman (For executing endpoints test suit).

## Steps to spin up the application locally
To start using this project use the following steps:
1. Clone the repo in your local disk `git clone https://github.com/vinit5619692/taskerassignment.git`.
2. Build server side ([Endpoints](https://github.com/vinit5619692/taskerassignment/tree/main/service)).
    - Open Intellij (or any other IDE).
    - Import project (from service folder) as Gradle.
    - Build the project.
3. Build [UI](https://github.com/vinit5619692/taskerassignment/tree/main/ui)
    - Go to "ui" folder location.
    - Open `cmd` prompt
    - Run `ojet restore`
    - Run `ojet build`
4. Open `cmd` prompt in the parent directory of the application and run `docker compose up -d`.
5. Now application should be running on `localhost:7001`.

# Maintaining the application
There are 5 parts where maintenances could be required in the application 
1. Database - To do any changes in this `./db/Dockerfile`.
2. Service - Build the service project again, after changes, and follow steps 2 and 4 from `Steps to spin up the application locally` above.
3. UI - After changes in the file, follow steps 3 and 4 from `Steps to spin up the application locally` above.
4. TO make any changes in `Dockerfile`, visit respective services folder (db, service, ui).
5. To do changes in deployment parameters look for `docker-compose.yml` in parent directory.

# Testing 

## Endpoint testing
Postman is used for endpoint testing.
### Steps to execute endpoint test suit
To test all the endpoints.
1. Import both positive and negative collection along with environment variable [collection](https://github.com/vinit5619692/taskerassignment/tree/main/postman%20collection) into Postman application.
3. Execute `run collection`.

## UI testing
Selenide is used for component/pages testing.
### Steps to execute UI test suit
To test UI components/pages.
1. Open Intellij (or any other IDE).
2. Import project (from "UI Testing" folder) as Gradle.
3. Go to "MainSelenideTest.java" (Change APP_URL variable in the class, if needed).
4. Execute All or selected test.

