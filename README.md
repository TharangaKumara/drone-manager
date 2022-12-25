# drone-manager
Sample project for MusalaSoft to manage drones to delivering medications

## Assumptions
1. A drone only contains medications in state of LOADING, LOADED or DELIVERING.
2. All the security implementation are handled by API Gateway and frontend, so not implementing security in this service.
3. Same medicine can come with different weight with but same code, so introduce medicine_id as primary key.
4. Drone is considered as available if and only if it is in IDLE, LOADING or RETURNING state and battery level is more than 25%.
5. All images are stored in separate server such as S3 bucket, and I have stored the image resource url in the MEDICATION database.
6. Since the Data Base is in-memory indexing is not introduced.
7. Medicine are considered as an unlimited deliverable loads and not implement and maintaining medicine inventory.
8. Medicine can be loaded to the drone only in countable integer multiplications.
9. Some medicine are heavier than 500gr.
10. You can add **N** numbers of medicine to drone by adding same medicine **N** times.
11. Database credentials are in application.yml file since this is a sample project, otherwise they should move to separate config server.

## Instructions
Please find the bellow end points and instructions to test this **_drone-manager_** service.
Even there are two environments (dev, prod), here I have used only dev environment. If you need run application in prod environment you can use command line arguments.

### Build the project
1. Clone the project using command `git clone https://github.com/TharangaKumara/drone-manager.git` and open it in your favourite editor.
2. Open the terminal and execute `mvn cleaninstall -DskipTests`
3. Or go to the project folder and run _build.sh_ using command `./build.sh` in terminal
4. Make sure that you are using Java 8, Spring Boot 2.x and Maven

### Run the Project
1. If you are using an editor use that to run project.
2. Or else go to the project folder and execute command `./run_drone_manager.sh`
3. Or go to the jar file location and execute command `java -jar drone-manager-0.0.1.jar --spring.profiles.active=dev`

### Logs and Log Files
You can find log files in bellow locations.

#### dev environment
* Server access logs : logs/dev/drone-manager-access
* Application logs : logs/drone-manager-service.log

#### prod environment
* Server access logs : logs/dev/drone-manager-access
* Application logs : logs/drone-manager-service.log

### Swagger
* http://localhost:8080/drone-manager/swagger-ui/index.html/

### Actuator
* http://localhost:8080/drone-tracker/actuator

### H2 Database Console
* http://localhost:8080/drone-manager/h2-console
* Password can be found in application.yml file


