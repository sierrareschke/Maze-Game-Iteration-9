# OOAD Homework 10:
## Custom Strategy Project
#### (40 points + extra credit for top three teams)

NOTE: Expect some minor edits/updates

Introduction
Team Members:
Java Version: 21
Comments/Assumptions: None

IMPORTANT: You will submit this assignment via a link to your GIT repository AND your JAR file.

## Grading Rubric:

### Deductions

    NOTE: for this assignment you do NOT need to worry about method coverage and you do NOT need to submit a screenshot of the coverage

* Meaningful names for everything: variables, methods, classes, interfaces, etc. (1% off for each bad name, up to 10% total)
* No "magic" numbers or strings (1% off for each one, up to 10%)
* No System.out.println() calls anywhere in your main code – replace with logging (see below) or eliminate outright. 1% off for each System.out.println statement in src/main/java code.

### Method Construction Possible Deductions (max is listed under Required Capabilities)

Methods should be:
* "short" -- with very few exceptions all methods should fit on a screen using a readable font.
* well named (duh).
* properly denoted as instance methods vs. static methods (static methods don't reference the _this_ pointer).
* limited complexity (level of indentation due to control structures).
* not have comments that could be turned into just as readable code.

All of this can be achieved through functional decomposition of more complicated methods (see lecture on October 2nd).

### Required Capabilities

* An implementation of the StrategyProvider Interface (5 points)
* An implementation of the Strategy Interface (10 points)
* A properly structured JAR file with the above code and the SPI Interface file in the src/main/resources/META-INF/services directory (5 points)
* The JAR must be loadable by the Professor's server code (5 points)
* The Strategy must be instantiatable and run without errors in the Professor's server code (5 points)
* 100% LINE coverage of your getCommand() method (10 points)

## Building the JAR file

To build the JAR file, you just need to run the gradle task 'jar'. You can do this in the 
IDE, but selecting the task in the Gradle view on the right side of the IDE, or you can run
it from the command line:

```shell
> ./gradlew clean jar

BUILD SUCCESSFUL in 438ms
3 actionable tasks: 3 up-to-date

> ls -ltr build/libs
total 16
-rw-r--r--@ 1 billwright  staff  1907 Dec  5 15:54 CSCI-4448-Homework-10-1.0.0.jar
```

As you can see, this puts the JAR file in the build/libs directory of your project.
You should verify that the contents of the JAR is correct by running the `jar` command:

```shell
> jar xvf build/libs/CSCI-4448-Homework-10-1.0.0.jar
  created: META-INF/
 inflated: META-INF/MANIFEST.MF
  created: polymorphia/
  created: polymorphia/strategy/
 inflated: polymorphia/strategy/TeamNameStrategyProvider.class
 inflated: polymorphia/strategy/TeamNameStrategy.class
  created: META-INF/services/
 inflated: META-INF/services/csci.ooad.polymorphia.intf.StrategyProvider
```

The structure should look like the above output.
