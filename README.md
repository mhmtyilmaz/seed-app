## What is Seed App?
Seed App is a project which helps developers to implement features easily. It has basic components defined which will be needed certainly.

It has mainly been implemented with Java 8, Spring Boot, Angular.

Has an in-memory database running to keep all the information and allow changing the DB technology at the same time.

The basic feature of it is, to add, update, view employees and their details so far.




## Run and Configure the Project :computer:

### Back-end
To make back-end up and running, please run below commands,
  * cd service
  * mvn install
  * java -jar target/seed-app-1.0-SNAPSHOT.jar


### Front-end
  * cd ../web  ` In order to go to UI folder `
  * npm install ` This is going to install needed dependencies `
  * http-server -o ` This will run a mock http-server `

Then, open browser and make sure you have entered
  * ` http://127.0.0.1:8081/app/ `


> It is ready! :thumbsup:

