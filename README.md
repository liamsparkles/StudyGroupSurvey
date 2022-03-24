# StudyGroupSurvey

This project was accomplished using Angular, Java with SpringBoot, and Atlas MongoDB. In the code above, StudyGroupSurvey contains the Angular code, and surveymanager contains the java code.

The figure below describes the general layout of the project.

![MVCDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/MVCDiagram.png "Model View Controller Diagram")

Here are some additional details regarding the database structure. The embedded tag means that the table is embedded in the other table. To clarify, there are two main Documents, Survey and Question. Employee, SurveyResponse, and QuestionResponse are all embedded in their respective parent documents. Finally, the 'id' for the Survey document is actually using the name in the embedded Employee table to create unique entries.

![MongoDBDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/StudyGroupSurveyDBDiagram.png "MongoDB Diagram")


## Setup and Execution

mvn version: 3.8.5   
java 11   
angular cli version: 13.3.0   
Node version: 16.14.2   
Node package manager: 8.5.0


```Bash
cd StudyGroupSurvey/surveymanager
mvn compile
mvn package
java -jar target/gs-maven-0.1.0.jar

cd StudyGroupSurvey/StudyGroupSurvey
npm install
ng serve
```
