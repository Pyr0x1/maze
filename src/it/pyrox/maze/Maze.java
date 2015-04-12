package it.pyrox.maze;

public class Maze {
	
	private Cell[][] maze;
	private int width;
	private int height;
	
	public Maze(int height, int width) {
		
		// make width and height odd if they aren't, so no "double walls" are present
		if (width % 2 == 0)
			width++;
		
		if (height % 2 == 0)
			height++;
		
		maze = new Cell[height][width];
		this.width = width;
		this.height = height;
	}
	
	public void initAllWall() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new Wall(i, j);
			}
		}
	}
	
	public void initAllFloor() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new Floor(i, j);
			}
		}
	}
	
	public void initSingleFloors() {
		initAllWall();
		for (int i = 1; i < height - 1; i+=2) {
			for (int j = 1; j < width - 1; j+=2) {
				maze[i][j] = new Floor(i, j);
			}
		}
	}
	
	public Cell getCellAt(int x, int y) {
		return maze[x][y];
	}
	
	public Cell getCellAtPosition(Cell cell, Position position, int step) {
		
		if (cell == null)
			return null;
		
		int x = cell.getX();
		int y = cell.getY();
		
		Cell toReturn = null;
		
		switch (position) {
		
			case UP:
				x -= step;
				if (x >= 0)
					toReturn = getCellAt(x, y);				
				break;
				
			case RIGHT:
				y += step;
				if (y < width)
					toReturn = getCellAt(x, y);	
				break;
				
			case DOWN:
				x += step;
				if (x < height)
					toReturn = getCellAt(x, y);	
				break;
				
			case LEFT:
				y -= step;
				if (y >= 0)
					toReturn = getCellAt(x, y);
				break;
				
			case TOTAL:
				break;
		}
		
		return toReturn;
	}
	
	public void breakWall(Floor floor1, Floor floor2) {
		
		int x1 = floor1.getX();
		int x2 = floor2.getX();
		int y1 = floor1.getY();
		int y2 = floor2.getY();
		
		// only works with adjacent cells
		if (x1 == x2) 
			maze[x1][Math.abs((y2 + y1) / 2)] = new Floor(x1, Math.abs((y2 + y1) / 2)); // TODO useless coordinates
		else if (y1 == y2)
			maze[Math.abs((x2 + x1) / 2)][y1] = new Floor(Math.abs((x2 + x1) / 2), y1);
	}
	
	public Floor breakWall(Wall wall) {
		
		Floor father = wall.getFather();
		Floor newFloor = null;
		int xW = wall.getX();
		int xF = father.getX();
		int yW = wall.getY();
		int yF = father.getY();
		
		if (xW == xF) {
			if (yF < yW) {
				newFloor = new Floor(xW, yW + 1);
				maze[xW][yW + 1] = newFloor;
			}
			else if (yF > yW) {
				newFloor = new Floor(xW, yW - 1);
				maze[xW][yW - 1] = newFloor;
			}
		}
		else if (yW == yF){
			if (xF < xW) {
				newFloor = new Floor(xW + 1, yW);
				maze[xW + 1][yW] = newFloor;
			}
			else if (xF > xW) {
				newFloor = new Floor(xW - 1, yW);
				maze[xW - 1][yW] = newFloor;
			}
		}
		
		setFloor(xW, yW);
		return newFloor;
	}
	
	@Override
	public String toString() {
		String out = "";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (getCellAt(i, j) instanceof Floor) {
					out += " ";
					if (j < width - 1)
						out += " ";
				}
				else {
					out += "+";
					if (j < width - 1)
						out += " ";
				}
			}
			if (i < height - 1)
				out += "\n";
		}
		
		return out;
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
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setFloor(int x, int y) {
		if (!(maze[x][y] instanceof Floor))
			maze[x][y] = new Floor(x, y);
	}
	
	public void setWall(int x, int y) {
		if (!(maze[x][y] instanceof Wall))
			maze[x][y] = new Wall(x, y);
	}
}
