# StudyGroupSurvey

This project was accomplished using Angular, Java with SpringBoot, and Atlas MongoDB. In the code above, StudyGroupSurvey contains the Angular code, and surveymanager contains the java code.

The figure below describes the general layout of the project.

![MVCDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/MVCDiagram.png "Model View Controller Diagram")

Here are some additional details regarding the database structure. The embedded tag means that the table is embedded in the other table. To clarify, there are two main Documents, Survey and Question. Employee, SurveyResponse, and QuestionResponse are all embedded in their respective parent documents. Finally, the 'id' for the Survey document is actually using the name in the embedded Employee table to create unique entries.

![MongoDBDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/StudyGroupSurveyDBDiagram.png "MongoDB Diagram")


| API Route | Request/Method | Functionality | Status Text |
| --------- | --------- | --------- | --------- |
| /api/survey | POST | Add survey | CREATED |
| /api/survey | PUT  | Update survy | OK |
| /api/survey | GET  | Get all surveys | OK |
| /api/survey/\<firstName\>/\<lastName\> | GET | Get survey with name | OK |
| /api/survey/exists/\<firstName\>/\<lastName\> | GET  | Get whether survey with name exists | OK|
| /api/survey/results/\<firstName\>/\<lastName\> | GET  | Get survey results as float | OK |
| /api/survey/\<firstName\>/\<lastName\> | DELETE  | Delete survey with name | NO_CONTENT |
| /api/question | POST | Add question | CREATED |
| /api/question | PUT | Update question | OK |
| /api/question | GET | Get all questions | OK |
| /api/question/\<id\> | GET | Get question with id| OK |
| /api/question/\<id\> | DELETE | Delete question with id | NO_CONTENT |

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
A better informative response will replace this, for example a 404.

Update submission buttons to activate on 'enter' keypress.

Add an additional parameter to the form to support people with the same name. This could either be an email field, or an user number.

Add management for the questions for administrators that don't understand the backend. This would allow them to add and modify questions using the front-end.

Modify DB design to remove the answer field from being directly included with the question. With this modified design, the front-end would never get access to the answers. By preventing users from getting access to the answers, they couldn't cheat by using postman and knowing the HTTP calls.

Adding to security, being able to make direct calls to the controller through REST is already a security risk, as any user can access the Question and/or Survey database. Allowing them to possibly cheat or gain additional time should we add a timer to the application. This can be solved by adding an API-key or some other security measure. Additional research would need to be done to figure out the best method, in addition to making sure no MITM attacks could steal the key. 
We also need to switch to HTTPS instead of HTTP.

