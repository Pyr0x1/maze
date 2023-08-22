package it.pyrox.maze.generator;

import it.pyrox.maze.controller.MazeController;
import it.pyrox.maze.model.Maze;

public abstract class Generator {
	
	protected Maze maze;
	protected MazeController controller;
	
	public abstract Maze generate(int width, int height);
}
