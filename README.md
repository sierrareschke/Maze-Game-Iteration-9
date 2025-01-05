## Custom Strategy Project

Team Members: Sierra Reschke, Nolan Brady, Grace Ohlsen

Java Version: 21


Notes:
The given terminal commands were run to create the jar file. 
Removed build folder from .gitignore so the .jar file is present in the repository as well as the uploaded file.


### Implemented Capabilities

* An implementation of the StrategyProvider Interface 
* An implementation of the Strategy Interface
* A properly structured JAR file with the above code and the SPI Interface file in the src/main/resources/META-INF/services directory
* The JAR must be loadable by the Professor's server code 
* The Strategy must be instantiatable and run without errors in the Professor's server code
* 100% LINE coverage of your getCommand() method

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
 inflated: polymorphia/strategy/GraceNolanSierraStrategyProvider.class
 inflated: polymorphia/strategy/GraceNolanSierraStrategy.class
  created: META-INF/services/
 inflated: META-INF/services/csci.ooad.polymorphia.intf.StrategyProvider
```

The structure should look like the above output.
