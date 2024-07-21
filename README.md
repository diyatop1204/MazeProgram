# Maze Program

This Maze Program written in Java 8, loads a maze from a text file, visualizes it, and finds the shortest path from the starting vertex to the exiting vertex. The program animates the pathfinding process and highlights the shortest path on the graphical interface.

# SET UP & USER INSTRUCTIONS: 
1. Ensure Java 8 is downloaded on your system. (Download Java 8: https://www.oracle.com/nz/java/technologies/javase/javase8-archive-downloads.html)
2. Download 'Maze Program' folder.
3. Locate Terminal/ Command Prompt.
4. Navigate to 'Maze Program' directory
5. Run ```javac -d bin src/*.java```
6. Then, ```java -cp bin BinaryMaze``` to start the program.

### Explanation of Maze Text files:
'Maze1.txt' is being used in the current simulation - to change open BinaryMaze.java in preferred IDE and manually change to 'Maze2.txt' where indicated (```getMazeFile("Maze2.txt");```)

To create your own maze text file, the following format is required:
- Header: The first line contains the number of edges, columns, and rows.
- Vertices: Each subsequent line defines a vertex with its name, x and y positions, and names of linked vertices. "A" is used to represent no linked vertex (null).

This file will need to be saved in the 'MazeProgram' folder in order to run the program. Manually change text file name in BinaryMaze.java (follow instructions above).

