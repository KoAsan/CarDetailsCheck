Car Details Check Automation Project
This project is an Automation Testing Framework using Selenium WebDriver, Maven, JUnit, Cucumber (BDD), and Page Object Model (POM) in Java. The goal of this project is to automate the process of car details comparison by searching vehicle details based on registration numbers and then compare with given output file.

Project Structure
The project follows a clean structure, separating different concerns for better maintainability and scalability. Below is an overview of the project structure:

bash
Copy
src/test
 ├── java
 │   ├── pages
 │   │   └── CarDetailsSearchPage.java
 │   ├── runner
 │   │   └── TestRunner.java
 │   ├── steps
 │   │   └── CarDetailsCheck.java
 │   └── utils
 │       └── Utilities.java
 ├── resources
 │   ├── Features
 │   │   └── CarDetailsCompareTest.feature
 │   └── Files
 │       ├── car_input - V6.txt
 │       └── car_output - V6.txt
 │   └── Drivers
          chromedriver

 
.gitignore
pom.xml
File Descriptions:
src/test/java/pages/CarDetailsSearchPage.java: Contains the Page Object Model for the Car Details search page. It defines methods to interact with the page elements, such as entering a registration number, clicking the search button, and extracting vehicle details like make, model, and year.

src/test/java/runner/TestRunner.java: The Cucumber Test Runner class that integrates with the Cucumber framework to execute the feature files. It configures the test execution, including the paths to feature files and step definitions.

src/test/java/steps/CarDetailsCheck.java: Contains the step definition methods for each step mentioned in the Cucumber feature file. These methods define the steps to interact with the web page and verify the expected results.

src/test/java/utils/Utilities.java: Contains utility methods, such as read car registration from an input file, Read expected outcome from given text file, compare car details from website and outputfile.

src/test/resources/Features/CarDetailsCompareTest.feature: The Cucumber feature file that defines the high-level scenarios for testing car details comparison. It contains Given/When/Then steps written in Gherkin syntax.

src/test/resources/Files/car_input - V6.txt: A text file that contains car registration numbers used for testing. The script reads registration numbers from this file to search for vehicle details.

src/test/resources/Files/car_output - V6.txt: A text file that contains the expected vehicle details for comparison. The car details retrieved from the website are compared against this file.

.gitignore: Specifies files and directories that should not be tracked by Git (e.g., compiled files, IDE configurations).

pom.xml: The Maven configuration file that manages the project dependencies, plugins, and build lifecycle.

Getting Started

Prerequisites
Before you can run this project, make sure you have the following installed:

Java 11+ (Install the latest version of Java JDK)

Maven (for managing dependencies and building the project)

Git (for version control)

WebDriver: (ChromeDriver, GeckoDriver, etc. based on your browser)

IDE: (e.g., IntelliJ IDEA, Eclipse)
