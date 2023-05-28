# Main Project

### Topic: Featured News

This project focus on testing a web application of Cognizant.
We have used java programming language and automation testing tools and frameworks in this project and made automation test script to test the given site. The testing is done using a set of various tools and frameworks like selenium, TestNG, maven etc.

### Problem Statement

Given site: https://be.cognizant.com/

- Open the link of the given site.
- Log in with your credentials.
- Scroll down to Featured News.
- Print the all three news title in console and then slide it to and then print the rest of three news.
- Click every news and print content of every featured news.

### Tools and Softwares

- Java
- Eclipse IDE 
- Selenium Framework
- TestNG Framework 
- Maven Tool
- DataDriven Framework
- Page Object Model
- Extent Report

### Installation

JDK 19 is used in this Project.

- https://www.oracle.com/in/java/technologies/downloads/

Eclipse or appropriate Java IDE should be installed.

- Eclipse IDE - https://www.eclipse.org/downloads/

Apache maven 3.90 is used while creating this project

- Apache Maven - https://maven.apache.org/download.cgi

TestNG is installed using Eclipse Marketplace.

- https://marketplace.eclipse.org/content/testng-eclipse

### Project Structure

This project is created in maven.
All the project dependencies are entered in pom.xml file.

#### src/main/java

Under this path four packages are created - Base, Config, Pages, Utils. 
Each class files under these packages do functionality for execution of script in smooth flow.

#### Base Package:

1.  Base.java - invoking browser, Taking Screenshot, navigating to url, expilict wait, closing browser.

#### Config Package:

1. config.properties - need to specify browser name in which the script has to run, need to enter Login credentials for logging in to website and specify the given site url.

#### Pages Package:

1. BeCognizant.java - confirms the given site, prints username, validates Featured News, validates slide button, validates contents of the news.

2. LoginBeCognizant.java - login using given credentails and navigates to gievn site.

#### Utils Package:

1. ExtentReportManager.java - class to create extent report for this project.

#### src/test/java
Under this path one package is created - TestSuites

#### TestSuites Package:

1. TestCases.java - call all function orderly and implements TestNG to perform test.

#### Folders

- Screenshots - Screenshots captured during execution will be saved in this folder.
- Report - Extent Report is saved in this folder.
- ExcelOutput - Output will be printed in excel file is saved in this folder.

#### testng.xml

- Run this testng.xml file to start this automation project.

### Workflow

1. User need to give their login credentials and browser preference in the config file.
2. User would need to run the testng.xml file.
2. Webdriver will open the given site.
3. User will be navigated to the login page of the website automatically.
4. Username and Password will be entered using sendKeys.
5. User will be prompted to approve the 2-step Authentication. Complete that 2-step Authentication
6. Then User will be redirected to the given site.
7. The username will be printed in console.
8. The count of the Featured News will be printed.
9. Each news will be opened and redirected back to home page.
10. The Content of the news is printed in console and also in Excel sheet.


