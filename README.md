
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

TO maintain the application, do the changes and execute step 2 to 4. 

# Testing 

## Endpoint testing
For endpoint testing I have used postman.
### Steps to execute test suit
To test all the endpoints.
1. Import both positive and negative collection along with environment variable [collection](https://github.com/vinit5619692/taskerassignment/tree/main/postman%20collection) into Postman application.
3. Execute `run collection`.

### Steps to execute UI 
To test UI components/pages.
1. Open Intellij (or any other IDE).
2. Import project (from "UI Testing" folder) as Gradle.
3. Go to "MainSelenideTest.java".
4. Execute All or selected test.

