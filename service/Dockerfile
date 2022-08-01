# Use the OpenJDK 11 image as the base image
FROM openjdk:11

# Create a new app directory for my tasker application files
RUN mkdir /app

# Copy the jar file from host machine(jar location - target/tasker-1.0-SNAPSHOT.jar) to image filesystem(/app)
COPY build/libs/taskerAppGradle-1.0-all.jar /app

# Copy the configuration file from host machine(file location - config.yaml) to image filesystem(/app)
COPY config.yaml /app

# Set directory for future execution
WORKDIR /app

# Run the application
CMD java -jar taskerAppGradle-1.0-all.jar server config.yaml
