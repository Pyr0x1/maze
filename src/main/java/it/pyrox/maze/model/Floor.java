package it.pyrox.maze.model;

public class Floor extends Cell {
	
	private boolean visited;
	private boolean closed;
	
	public Floor(int row, int col) {
		super(row, col);
		visited = false;
		closed = false;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}	
	
}
