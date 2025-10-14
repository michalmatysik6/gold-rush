# Gold Rush resources

A repository of resources needed to complete tasks in software engineering classes.

## Table of contents
- **pdf/** — folder containing the PDFs used in class

- **gold_rush__empty_prj.zip** — an empty Gradle project;
  you can import it into any IDE or work with it directly from the command line (see below)
  
- **symbols.md** — list of token symbols


## Notes

### How to work with Gradle project (basics)

To build the application, use the gradlew command-line tool. (Note: on Windows, use `gradlew.bat` instead of `./gradlew`.)

Common operations include:

- `./gradlew` — build the project
- `./gradlew clean` — clean the project (removes the build directory)
- `./gradlew jar` — build the project and generate a JAR file

To run the application, execute the following command from the project’s root directory:

`java -cp build/libs/gold_rush-0.1.jar edu.io.Main`
