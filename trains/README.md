Trains exercise
=================


## Design - Assumptions
Inversion of Control principle and dependency Injection pattern have been applied using Spring Framework version 4.
All Object beans by default are created as singletons.
 
Data structure:  
A representation of a directed graph with n vertices using an n × n matrix, where the entry at (i,j) is weight, if there 
is an edge from vertex i to vertex j; otherwise the entry is 0. 

This is the graph representation for the trains problem
      A    B   C   D   E
 A    inf  5  inf  5   7
 B    inf inf  4  inf inf 
 C    inf inf inf  8   2
 D    inf inf  8  inf  6
 E    inf  3 inf  inf inf

## Requirements

jdk 7 and maven 3+  must be installed in order to run the project locally.

## Unzip and compile code

1) Unzip the file `trains.rar` and navigate to trains folder
2) Execute the command : `mvn clean install`

## Run the program

Run the program providing the absolute file path of the sample poem. 

`java -jar target\trains-0.0.1-SNAPSHOT-jar-with-dependencies.jar src\main\resources\commands.txt src\main\resources\graph-input.txt`


## Output

For input the following set of commands

distance# A-B-C
distance# A-D
distance# A-D-C
distance# A-E-B-C-D
distance# A-E-D
number_of_routes# A-C
number_of_routes# A-E
shortest_path# A-C
shortest_path# A-E
shortest_path# B-E

We get the output

`Route: ABC, Distance 9`
`Route: AD, Distance 5`
`Route: ADC, Distance 13`
`Route: AEBDC, Distance 22`
`Route: AED, NO SUCH ROUTE`
`Number of Routes Between Nodes: A and C :4`
`Number of Routes Between Nodes: A and E :5`
`Shortest Path Between Nodes: A and C :9`
`Shortest Path Between Nodes: A and E :7`
`Shortest Path Between Nodes: B and E :6`

