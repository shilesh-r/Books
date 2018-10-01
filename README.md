# Books

Amazon - Books Project

Project Description:
  Automation framework to go to Amazon.com and search for 'Data Catalog' in books category. In results page, capture information for a specific record in result.
Project is developed using the below:
1. Serenity BDD - v1.5.11 (Open source wrapper library written based on JBehave and Selenium)
2. Junit - 4.11
3. Maven


Test Framework:
Serenity BDD: Serenity is an open source library that helps to write higher quality automated acceptance tests faster. 

Website: http://www.thucydides.info/

Reason to select serenity bdd:
1. Easy to setup and write acceptance tests
2. Use of JBehave bdd enables non-technical testers to extend and write new tests with minimal knowledge
3. Powerful reporting library
4. Easy to scale in terms of tests

Programming Language: Java - jdk1.8

Browser: Firefox v57

Geckodriver: v0.19 64bit

Design:
1. Create BDD - gherkin style tests in .story files
2. Create Step definitions to call step implementations for earch gherkin step
3. Create step implementations
4. Create Page object class to maintain page elements and action methods
5. Include Junit assertions in Steps class where necessary


Execution instruction:

Command line: mvn -f "pom file path"\pom.xml clean integration-test verify

--or--

Run Configuration:
1. Right click project and click Run as
2. Select Maven Build
3. Enter goals - clean integration-test verify 
4. Click Run

Report location: index.html under 'target\serenity\serenity-reports' folder in project








