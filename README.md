# List of Scenarios Identified & Automated:
#UI
1. Dynamic Table
2. MouseOver
3. Overlapped Element

#API
1. Add New Pet to the Store and Create an Order for the added Pet

# Run Locally:
We can execute the scenarios locally via the feature file or from the JUnit Runner file. Below are the details for the same:

1. Execute via Feature files: Open AssignmentScenarios.feature -> Right click on the editor and select option Run As -> Cucumber Feature

2. Execute via JUnit Runner file: Open TestRunner.java file located under src/test/java -> Right click on the editor and select option Run As -> JUnit Test

3. Execute via Maven Command Line: Open the terminal -> Go to Project folder -> Hit the commands mvn clean install -Dbrowser=chrome (or) mvn test -Dbrowser=chrome

4 All the screenshots for the UI scenarios can be found from {project}/screenshots folder.

# Run in a CI/CD pipeline:
In order to run in CI/CD pipeline I used jenkins as the tool. Below are the detailed steps to configure jenkins and integrage our project with jenkins.
1. Install Jenkins on to your local machine
2. Access the URL (https://localhost:8080) and configure the Admin Users for Managing Jenkins
3. Select the default plugins that comeup during installation
4. Additionally install the plugins like Maven Ingration, Github & Cucumber Reporting from Manage Jenkins option
5. Set the JAVA_HOME & MAVEN_HOME environment variables with a value of their installation folders from Configure System option
6. Create a Job (namely an Item) with any name by selecting Maven Project option
7. Now configure the Job to have GIT Repo URL along with its credentials, Build Parameters, Pre Build Steps, Maven command (mvn clean install -Dbrowser=chrome -Dcucumber.filter.tags=) & Post Build Steps.
8. At this point, the job is ready and we can start buiding the same by clicking on Build with Parameters.
9. Post execution, HTML Results along with screenshots can be found from workspace/target/cucumber-report/index.html
10. Save this file as a html file in your local system and launch the same in any browser to see the results

# Tools & Technologies Used:
1. Test Automation Tool: 
	UI: Selenium (v3.141.59)
	API: RestAssured
2. Programming Language: Java (v1.8)
3. Automation Framework: Cucumber BDD
4. Source/Version Control: GitHub
5. CI/CD Tool: Jenkins
6. Build Tool: Maven

# About Scenarios:
1. Dynamic Table: In most of the recent UI Applications, we see the table implementations which are dynamic in nature. Hence I've considered it in my automation.

2. MouseOver: Most of the traditional UI Applications are having menu items where in user should hover on these items in order to perform any actions on the underlying elements. Hence I've considered it in my automation.

3. Overlapped Element: Scroll is a most used action in all the recent applications. Hence I've considered it in my automation.

4. Add New Pet to the Store and Create an Order for the added Pet: It's a combination of POST & GET calls where in user creates a Pet, Get all the available pets with some status and places an Order for it. To make the scenario an end-to-end I've created only 1 API test case even though we can break it into 3.

# Further Steps:
Clone the repo and add it to your local Eclipse workspace. Convert the same to Cucumber Project from Eclipse and perform a Build. Your project is ready and you can start automating the scenarios.