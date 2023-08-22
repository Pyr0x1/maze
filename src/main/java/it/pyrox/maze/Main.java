package it.pyrox.maze;

import it.pyrox.maze.generator.DepthFirstGenerator;
import it.pyrox.maze.generator.PrimGenerator;
import it.pyrox.maze.model.Maze;

public class Main {

	public static void main(String[] args) {	

		DepthFirstGenerator DFGen = new DepthFirstGenerator();
		Maze maze = DFGen.generate(10, 20);
		System.out.println(maze);
		System.out.println();
		
		PrimGenerator PGen = new PrimGenerator();
		maze = PGen.generate(10, 20);
		System.out.println(maze);
	
	}

}
