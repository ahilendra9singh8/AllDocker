###############################################################################        for Production

# Step 1: Use the OpenJDK 21 base image for the build stage
FROM openjdk:21-jdk-slim as build

# Step 2: Install Maven (since it's not included in the OpenJDK slim image)
RUN apt-get update && apt-get install -y maven

# Step 3: Set the working directory
WORKDIR /app

# Step 4: Copy the pom.xml file and download dependencies
COPY pom.xml .

# Step 5: Run mvn dependency:go-offline to download dependencies
RUN mvn dependency:go-offline

# Step 6: Copy the source code
COPY src ./src

# Step 7: Build the application
RUN mvn clean package -DskipTests

# Step 8: Use the OpenJDK 21 base image for the final image (runtime)
FROM openjdk:21-jdk-slim

# Step 9: Set the working directory for the final image
WORKDIR /app

# Step 10: Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar   

# Step 11: Expose port 8080
EXPOSE 8080

# Step 12: Command to run the Spring Boot application  
ENTRYPOINT ["java", "-jar", "app.jar"]


###################################################################################            for Development
# step 1 : Use official OpenJDK 21 image
#FROM openjdk:21-jdk-slim

# step 2 : Install Maven
#RUN apt-get update && apt-get install -y maven

# step 3 : Set working directory
#WORKDIR /app

# step 4 : Copy pom.xml and download dependencies
#COPY pom.xml ./
#RUN mvn dependency:go-offline

# step 5 : Expose Spring Boot port
#EXPOSE 8080

# step 6 : Run app in development mode
#CMD ["mvn", "spring-boot:run"]

