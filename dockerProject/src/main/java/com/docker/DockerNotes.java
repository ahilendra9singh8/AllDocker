//Docker
//
//
//
//1.install docker
//2.install wsl
//3.check virtualization is enabled
//
//4.docker -v
//5.docker run hello-world (download from docker hub)
//6.docker images (check images)
//
////pull image 
//docker pull <imagename>
//docker pull hello-world
//docker pull openjdk
//docker pull openjdk:18
//
////search image on docker-hub
//docker search nginx
//
//==> run above images and convert in container
//docker run python  (1st tarika)
//docker ps -a  (check krta h konsa container run h konsa nhi)
//docker run --name pythonContainer -d python (2nd tarika)
//docker run --name myhello-world1 -it -d 74cc54e27dc4 (3rd tarika best)
//
//==> container ke andar jane ke liye
//docker exec -it <container ID> (or) <containerName>
//
//==> container restart
//docker restart <container ID>
//
//==> stop container 
//docker stop <containerName> (or) <container ID>
//
//==> remove container
//docker ps  (its showing stopped container)
//docker rm <container ID>  (removed stopped contaner)
//
//==> some other comands
//docker login
//docker commit
//docker push
//docker copy
//docker logs
//docker volume
//docker logout
//
//
//==> remove images 
//docker rmi openjdk
//
//
//===============> abhi tak ham images ko docker hub se pull krke use kr rhe the ab ham docker images ko create karege
//
//
//
//
//1.create springboot project
//======================================================================> for production
//2.create Dockerfile 
///////////////
//# Step 1: Use the OpenJDK 21 base image for the build stage
//FROM openjdk:21-jdk-slim as build
//
//# Step 2: Install Maven (since it's not included in the OpenJDK slim image)
//RUN apt-get update && apt-get install -y maven
//
//# Step 3: Set the working directory
//WORKDIR /app
//
//# Step 4: Copy the pom.xml file and download dependencies
//COPY pom.xml .
//
//# Step 5: Run mvn dependency:go-offline to download dependencies
//RUN mvn dependency:go-offline
//
//# Step 6: Copy the source code
//COPY src ./src
//
//# Step 7: Build the application
//RUN mvn clean package -DskipTests
//
//# Step 8: Use the OpenJDK 21 base image for the final image (runtime)
//FROM openjdk:21-jdk-slim
//
//# Step 9: Set the working directory for the final image
//WORKDIR /app
//
//# Step 10: Copy the jar file from the build stage
//COPY --from=build /app/target/*.jar app.jar
//
//# Step 11: Expose port 8080
//EXPOSE 8080
//
//# Step 12: Command to run the Spring Boot application
//ENTRYPOINT ["java", "-jar", "app.jar"]
//
///////////////
//3.Build dockerfile so create docker image
//docker build -t springboot-docker .
//4.run docker image so create or run the docker container
//docker run -p 8080:8080 springboot-docker    (here 7072 is a localhost port and 7073 is a dockercontainer port for perticular project)
//
//============================================================> download in local docker image
//1.download these image in local from docker(its save as a .tar file)
//docker save -o springboot-docker.tar springboot-docker:v1.0
//
//2.now share this image and run this image (so its showing on docker environment)
//docker load -i springboot-docker.tar
//
//3.run docker image and create container
//docker run -p 7072:7072 springboot-docker:v1.0
//
//=============================================================> Docker Hub
//1.Login dokerHub and docker environment (username - shailendra277 (or) ssinghlodhi08@gmail.com    password - Shailu@123)
//2.create repository on dockerHub
//3.Tag the Docker Image
//docker tag springboot-docker shailendra277/springboot-docker:v1.0
//4.login docker
//docker login
//5.Push the Image to Docker Hub
//docker push shailendra277/springboot-docker:v1.0
//
//6.Pull the Image from Docker Hub (on another machine)
//docker pull shailendra277/springboot-docker:v1.0
//7.And then run it with:
//docker run -p 7072:7072 shailendra277/springboot-docker:v1.0
//
//
//======================================================================> for Development environment
//1.Create Dockerfile
//////////////
//# step 1 : Use official OpenJDK 21 image
//FROM openjdk:21-jdk-slim
//
//# step 2 : Install Maven
//RUN apt-get update && apt-get install -y maven
//
//# step 3 : Set working directory
//WORKDIR /app
//
//# step 4 : Copy pom.xml and download dependencies
//COPY pom.xml ./
//RUN mvn dependency:go-offline
//
//# step 5 : Expose Spring Boot port
//EXPOSE 8080
//
//# step 6 : Run app in development mode
//CMD ["mvn", "spring-boot:run"]
//
//
///////////////
//2.Build dockerfile so create docker image
//docker build -t springboot-dev .
//
//3.run docker image (Run the Docker Container)
//docker run -it --rm -v "D:/Shailendra Singh/springbootProject/AllDocker/dockerProject:/app" -p 8081:8080 springboot-dev
//
//4.check for any issues 
//docker logs <container_id>
//
//Note ==> now you change in project so its showing live.
//
//
//============================================================================> Deploy on AWS
//1.create a EC2 instance
//2.Set Up Docker on EC2 (Ubuntu)
//# Update and install Docker
//sudo apt update
//sudo apt install -y docker.io
//# Start Docker and enable it on boot
//sudo systemctl start docker
//sudo systemctl enable docker
//# Optional: Allow current user to run Docker without sudo
//sudo usermod -aG docker ubuntu
//
//3.First way ==> pull from docker hub.
//sudo docker pull shailendra277/springboot-docker:v1.0
//4.Run the Docker Container on ec2
//sudo docker run -d -p 8080:8080 shailendra277/springboot-docker:v1.0
//5.you can change name than run  - Optional: Retag the Image (So You Can Use a Simpler Name)
//sudo docker tag shailendra277/springboot-docker:v1.0 springboot-docker:v1.0
//sudo docker run -d -p 8080:8080 springboot-docker:v1.0
//
//3.Second Way ==> Build Docker Image Locally and Transfer It
//3.1 On your local machine:
//docker build -t springboot-app .
//docker save springboot-app > springboot-app.tar
//
//3.2 Then copy it to EC2:
//scp -i "your-key.pem" springboot-app.tar ubuntu@your-ec2-public-ip:~
//
//3.3 On EC2:
//docker load < springboot-app.tar
//
//==>stop container:
//docker stop <container-id>
