# MicroBF
Yet another Brainfuck interpreter, but in java and with some personal optional extensions.

## BUILD
1. Clone the project
2. Run with ./gradlew run --args="[src]" [args] for *nix users, ./gradlew.bat run --run args="[src]" [args] for Windows users.
3. To import, simply repeat the step 2, but running gradlew with "jar" task.

### NOTE
Due to Gradle's nature, you may not be able to use the BF input in your terminal when running the project with the "run" task. 

## Arguments
-d : Debug mode(with a memory and pointer display)
-v : Displays version text
-h : Displays a help screen
