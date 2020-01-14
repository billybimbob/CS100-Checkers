# CS100-Checkers

This is a repository to help me synchronize changes that I make to the CS 100 Checkers project.
The **Using the Terminal** section only applies to a version of this project that has all the
class files and documentation in a `playcheckers.jar` file. Most of the other topics below can
apply to this version, but the classpath specified at the terminal and as well in the
`.classpath` file are different.

Below is some information on how the checkers program is intended to run.
Words surrounded with * are meant to be relative to person using, and not the actual text.

## Program Locations

The locations listed are the different programs to run, and one should be specified when calling
the `java` command.

### Tournament

The Tournament will run multiple Evaluator submissions in a round robin tournament, where all
submissions will face against each other. The winner is the Evaluator with the most amount of
wins in the end.

`checkers.Tournament`

The program arguements are specified when running the program, but there are
some extra requirements on the submission file in order to run correctly:

1. A text file that contains all Evaluators participating in the tournament
    * The text file should include the name of the Evaluator class names (case sensitive)
    * Each class name should be on its own line
2. Submission class files listed in the text file
    * The class files must be located in the directory `bin/submissions`

### Student Play

The Student Play is the program intended for the students to run. The students will run this
program to test a signle Evaluator implementation against predefined Evaluators.

`checkers.Play`

## Setting Up/Running

Regardless of the program being intended to run, the set up process is the same. Below are
steps to compile and run in either Eclipse or with a terminal.

## Using Eclipse

Assuming the files are unzipped, to load the project to Eclipse:

1. Open Eclipse, and click on the 'File' option at the top left
2. Click the 'Open Projects from File System...' option near the top
3. Near the top right of the dialog box, click the 'Directory' option
4. Navigate to the folder location where the unzipped files are
5. Click 'Finish' at the bottom right of the window

### Compiling

The classpath file should be set up to already have all the libraries and documentation included,
so compiling should be automatically set up when opening the project in Eclipse

### Running

#### To run, a new run configuration must be added

1. At the top, click the small down arrow to the right of the green run button
2. Near the top left, click the 'New launch configuration'
3. In the 'Main' tab, enter in one of the Program Locations listed above in the 'Main class' text box
4. In the 'Arguments' tab, enter in the program arguments in the 'Program arguments' tab
    * The exact arguements are specified in the programs, or can be shown by adding the `-h` argument
    * These arguments can be changed any time afterwards by just going through the same steps as before
    * Some of the program arguements are optional while there are also some that are required to run
5. Click either 'Run' or 'Apply' and 'Close'
6. Now to run, the green run button can just be used

## Using the Terminal

1. After unzipping the files to a location, change the directory to that unzipped location
2. Enter to commands below to compile/run
    * The program location is one of locatiosn listed above in the **Program Locations** section
    * The exact program arguements are specified in the programs, or can be shown by adding the `-h` argument

### Compile Command

`javac -d bin -cp "src:lib/playcheckers.jar" src/submissions/*YourEval*.java`

### Run Command

`java -cp "bin:lib/playcheckers.jar" *Program Location* *program args*`

### On Windows

Replace the colon characters `:` with semicolons `;`  e.g.

`java -cp "bin;lib/playcheckers.jar" *Program Location* *program args*`
