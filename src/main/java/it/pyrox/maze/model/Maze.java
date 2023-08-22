package it.pyrox.maze.model;

import it.pyrox.maze.enums.Position;

public class Maze {
	
	private Cell[][] matrix;
	private int width;
	private int height;
	
	public Maze(int height, int width) {		
		// make width and height odd if they aren't, so no "double walls" are present
		if (width % 2 == 0)
			width++;
		
		if (height % 2 == 0)
			height++;
		
		matrix = new Cell[height][width];
		this.width = width;
		this.height = height;
	}
	
	public Cell getCellAt(int x, int y) {
		return matrix[x][y];
	}
	
	public Cell getCellAtPosition(Cell cell, Position position, int step) {		
		if (cell == null) {
			return null;
		}
		
		int x = cell.getX();
		int y = cell.getY();
		
		Cell toReturn = null;
		
		switch (position) {
		
			case UP:
				x -= step;
				if (x >= 0) {
					toReturn = getCellAt(x, y);
				}
				break;
				
			case RIGHT:
				y += step;
				if (y < width) {
					toReturn = getCellAt(x, y);
				}
				break;
				
			case DOWN:
				x += step;
				if (x < height) {
					toReturn = getCellAt(x, y);
				}
				break;
				
			case LEFT:
				y -= step;
				if (y >= 0) {
					toReturn = getCellAt(x, y);
				}
				break;
				
			case TOTAL:
				break;
		}
		
		return toReturn;
	}
	
	public void setFloor(int x, int y) {
		if (!(matrix[x][y] instanceof Floor)) {
			matrix[x][y] = new Floor(x, y);
		}
	}
	
	public void setWall(int x, int y) {
		if (!(matrix[x][y] instanceof Wall)) {
			matrix[x][y] = new Wall(x, y);
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (getCellAt(i, j) instanceof Floor) {
					builder.append(" ");
					if (j < width - 1) {
						builder.append(" ");
					}
				}
				else {
					builder.append("+");
					if (j < width - 1) {
						builder.append(" ");
					}
				}
			}
			if (i < height - 1) {
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}
	
	public boolean[][] toBoolean() {		
		boolean[][] result = new boolean[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (getCellAt(i, j) instanceof Floor)
					result[i][j] = false;
				else
					result[i][j] = true;
			}
		}
		
		return result;
	}
	
}
