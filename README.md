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
java -jar target/surveymanager-0.0.1-SNAPSHOT.jar

cd StudyGroupSurvey/StudyGroupSurvey
npm install
ng serve
```

## Improvements

Currently the backend is setup to return a 500: Internal Server Error message whenever a query fails because the item is not in the database.
Although it functions fine, I do not like this functionality. It is uninformative and doesn't share enough about what happened. 
A better informative response will replace this.
