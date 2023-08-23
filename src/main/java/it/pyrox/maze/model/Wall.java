package it.pyrox.maze.model;

public class Wall extends Cell {
	
	private Floor father;
	
	public Wall(int row, int col) {
		super(row, col);
	}

	public Floor getFather() {
		return father;
	}

	public void setFather(Floor father) {
		this.father = father;
	}
	
	public boolean isBorder(int height, int width) {
		if (row > 0 && row < height - 1 && col > 0 && col < width - 1) {
			return false;
		}
		else {
			return true;
		}
	}
}
