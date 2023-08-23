package it.pyrox.maze.model;

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
		
	public Cell[][] getMatrix() {
		return matrix;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setFloor(int row, int col) {
		if (!(matrix[row][col] instanceof Floor)) {
			matrix[row][col] = new Floor(row, col);
		}
	}
	
	public void setWall(int row, int col) {
		if (!(matrix[row][col] instanceof Wall)) {
			matrix[row][col] = new Wall(row, col);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] instanceof Floor) {
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
				if (matrix[i][j] instanceof Floor)
					result[i][j] = false;
				else
					result[i][j] = true;
			}
		}
		
		return result;
	}
	
}
