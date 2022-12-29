# Chess Game

Console application developed during the  **[Java course](https://www.udemy.com/course/java-curso-completo/)** produced by instructor **[Nelio Alves](https://www.linkedin.com/in/nelio-alves)**.

![chessGame](C:\Projetos\chess-system\assets\chessGame.gif)

### Requirements

- Java version 19 or higher.
- Colored terminal like PowerShell used in the exemple.

### How to exec

Download the source or clone the repository.

![](C:\Projetos\chess-system\assets\clone.png)

If your system doesn't have configured Java environment, make the download of **[jdk](https://jdk.java.net/19/)** and set **[Java Path](https://www.geeksforgeeks.org/how-to-set-java-path-in-windows-and-linux/)** according your system.

In your terminal opened in the root path of the project, exec the command to build the projec. 

```bash
javac -sourcepath .\src\main\java\ -d bin .\src\main\java\application\Main.java
```

Run the project

```bash
java -cp .\bin\ .\src\main\java\application\Main.java
```

### How to play

- To move a piece, type the position **column** + **line**, eg: **b2** to select it, after that the game will highlight possible moves from your choice, then select the target position to finish a move.

###### Pieces symbols

| Pawn![](C:\Projetos\chess-system\assets\pieces\pawn.svg) | Rook![rook](C:\Projetos\chess-system\assets\pieces\rook.svg) | Knight![](C:\Projetos\chess-system\assets\pieces\knight.svg) | Bishop![](C:\Projetos\chess-system\assets\pieces\bishop.svg) | Queen![](C:\Projetos\chess-system\assets\pieces\queen.svg) | King![](C:\Projetos\chess-system\assets\pieces\king.svg) |
| :------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :--------------------------------------------------------: | :------------------------------------------------------: |
|                          **P**                           |                            **R**                             |                            **N**                             |                            **B**                             |                           **Q**                            |                          **K**                           |

### Diagram class

![](C:\Projetos\chess-system\assets\class-diagram.png)

### Unit Tests

The project was covered by some unit tests to help with a refactoring, they were also useful to discover some bugs.

![](C:\Projetos\chess-system\assets\unitTests.gif)

###### Code coverage

As the project's main focus wasn't unit tests, it don't cover 100%, but it reached a considerable percentage, covering the main features.

To see the complete coverage report open the **[link]()**

![](C:\Projetos\chess-system\assets\codeCoverage.png)