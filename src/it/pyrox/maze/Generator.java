package it.pyrox.maze;

public abstract class Generator {
	
	private Maze maze;
	
	public abstract Maze generate(int width, int height);
}
