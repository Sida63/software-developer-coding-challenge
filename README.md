# Software Developer Coding Challenge

This is a coding challenge for software developer applicants applying through http://work.traderev.com/

## Goal:

#### You have been tasked with building a simple online car auction system which will allow users to bid on cars for sale and with the following funcitionalies: 

  - [ ] Fork this repo. Keep it public until we have been able to review it.
  - [ ] A simple auction bidding system
  - [ ] Record a user's bid on a car
  - [ ] Get the current winning bid for a car
  - [ ] Get a car's bidding history 

 ### Bonus:

  - [ ] Unit Tests on the above functions
  - [ ] Develop a UI on web or mobile or CLI to showcase the above functionality

## Evaluation:

 - [ ] Solution compiles. Provide a README to run/build the project and explain anything that you leave aside
 - [ ] No crashes, bugs, compiler warnings
 - [ ] App operates as intended
 - [ ] Conforms to SOLID principles
 - [ ] Code is easily understood and communicative
 - [ ] Commit history is consistent, easy to follow and understand

 My answers:
 ## How to run project
 # To run my code in IDE, please follow below instructions:

 - download intellij IDE
 - install LomBok Plugin and Spring Assistant in intellij IDE
 - use "import porject" option to import my project into intellij, choose "build will Maven" option
 - intellij may ask if you want to import changes for Maven, please chick yes
 - intellij will take some time to make index for the project
 - after above works are done, go to the upper-left corner, click "Maven Project" Option; find "Plugins" -> "spring-boot" -> "spring-boot:run"
 - click "spring-boot:run" to start run project
 - the project is running on http://localhost:8080/

 # To run my code from complied fat jar file:
 - find complied jar file at following path: software-developer-coding-challenge\target
 - please use below command to start service:
    java -jar auction_system-0.0.1-SNAPSHOT.jar

 # How this auction system works?
 - User can check all the car information at mainpage
 - Click the button "Input Car" in Mainpage will lead user to another page "Input Car", here user could add one car for auction
 - Click "car name" link will lead user to another page "Car Detail". Here user can see detailed information and find all the related bid history for the car. Also user could make one bid for the car at this page.

 ## Tech stacks:
 - Spring Boot 2.0
 - Maven
 - Git
 - Java
 - h2 Database
 - Html5
 - CSS
 - Javascript
 - Thymeleaf
 - JUnit
