# Use the official OpenJDK image as the base image
FROM openjdk:11-jdk

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the Java source code into the container
COPY SimpleHttpServer.java .

# Copy the index.html file into the container
COPY index.html .

# Compile the Java source code to generate the class file
RUN javac SimpleHttpServer.java

# Define the command to run the Java application
CMD ["java", "SimpleHttpServer"]
