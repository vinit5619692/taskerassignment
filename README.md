# Tasker Assignment

This(Tasker) is web application to maintain and track tasks.
User can do Create, Read, Update and Delete on task using responsive application.
Screenshot of pages, from the application, are avaiable [here](https://github.com/vinit5619692/taskerassignment/tree/main/screenshot).
Entire project is committed at [github](https://github.com/vinit5619692/taskerassignment).


# Tiers of Application

1. Presentation tier - UI is powered using OJET 11.
2. Application tier - Endpoints are created with DropWizard framework.
3. Data tier - MySQL is used to persist data.


# How to start locally

Docker is configured to containerize all the tiers, which makes it easy to run entire application anywhere.

## Prerequisite softwares
1. Docker.
2. Intellij(or any other IDE).
3. ojet([how to install](https://docs.oracle.com/en/learn/jet-install-cli/index.html#task-3-verify-the-oracle-jet-command-line-interface)).

## Steps to spin up the application locally
1. Clone the repo in you local disk `git clone https://github.com/vinit5619692/taskerassignment.git`.
2. Build server side ([Endpoints](https://github.com/vinit5619692/taskerassignment/tree/main/service)).
    - Open Intellij(or any other IDE).
    - Import project as gradle.
    - Build the project.
3. Build [UI](https://github.com/vinit5619692/taskerassignment/tree/main/ui)
    - Go to "ui" folder location.
    - Open `cmd` prompt
    - Run `ojet restore`
    - Run `ojet build`




# Where to get Docker Compose

### Windows and macOS


Quick Start
-----------

Using Docker Compose is basically a three-step process:
1. Define your app's environment with a `Dockerfile` so it can be
   reproduced anywhere.
2. Define the services that make up your app in `docker-compose.yml` so
   they can be run together in an isolated environment.
3. Lastly, run `docker compose up` and Compose will start and run your entire
   app.
