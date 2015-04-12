package it.pyrox.maze;

public class Cell {
	
	// TODO maybe coordinates are useless
	protected int x;
	protected int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}
}
