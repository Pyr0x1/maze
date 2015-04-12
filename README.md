# Maze

A maze generator written in Java.

Usage
=====

Currently, two algorithms are implemented: Depth First and Prim. You can create a maze with the following lines of code (an example main is provided as well)

```java
DepthFirstGenerator DFGen = new DepthFirstGenerator();
Maze maze = DFGen.generate(10, 20);
System.out.println(maze); 
```

```java
PrimGenerator PGen = new PrimGenerator();
Maze maze = PGen.generate(10, 20);
System.out.println(maze);
```