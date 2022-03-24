# StudyGroupSurvey

The figure below describes the general layout of the project.

![MVCDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/MVCDiagram.png "Model View Controller Diagram")

Here are some additional details regarding the database structure. The embedded tag means that the table is embedded in the other table. To clarify, there are two main Documents, Survey and Question. Employee, SurveyResponse, and QuestionResponse are all embedded in their respective parent documents. Finally, the 'id' for the Survey document is actually using the name in the embedded Employee table to create unique entries.

![MongoDBDiagram](https://github.com/liamsparkles/StudyGroupSurvey/blob/main/StudyGroupSurveyDBDiagram.png "MongoDB Diagram")
