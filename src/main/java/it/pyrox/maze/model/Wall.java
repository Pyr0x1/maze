package it.pyrox.maze.model;

public class Wall extends Cell {
	
	private Floor father;
	
	public Wall(int x, int y) {
		super(x, y);
	}

	public Floor getFather() {
		return father;
	}

	public void setFather(Floor father) {
		this.father = father;
	}
	
	public boolean isBorder(int height, int width) {
		if (x > 0 && x < height - 1 && y > 0 && y < width - 1) {
			return false;
		}
		else {
			return true;
		}
	}
}
