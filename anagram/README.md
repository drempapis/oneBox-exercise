Anagram exercise
=================


## Design - Assumptions
Inversion of Control principle and dependency Injection pattern have been applied using Spring Framework version 4.
All Object beans by default are created as singletons.
 
Poem is provided as a text file with each verse in a new line
When inserted, the text is filtered, removing whitespaces, accents, non-alphabetic characters.
The implementation works for different languages than the [a-z] latin alphabet. 

The algorithm to compare two lines for anagrams, counts the number of each characters in the first line-array, 
matching with the number of characters in the second one. That makes the complexity to O(1), even better than
sorting and comparing the arrays O(nlogn).

## Requirements

jdk 7 and maven 3+  must be installed in order to run the project locally.

## Unzip and compile code

1) Unzip the file `anagram.rar` and navigate to anagram folder
2) Execute the command : `mvn clean install`

## Run the program

Run the program providing the absolute file path of the sample poem. 
(In windows should be src\main\resources\anagrammatic-poem.txt)

`java -jar target/anagram-0.0.1-SNAPSHOT-jar-with-dependencies.jar src/main/resources/anagrammatic-poem.txt`

`java -jar target/anagram-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/non-anagrammatic-poem.txt`

`java -jar target/anagram-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/greek-poem.txt`

## Output

Anagram: True - Your poem was detected as anagrammatic

Anagram: False - Your poem was detected as no-anagrammatic

