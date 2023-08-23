package it.pyrox.maze.controller;

import it.pyrox.maze.enums.Position;
import it.pyrox.maze.model.Cell;
import it.pyrox.maze.model.Floor;
import it.pyrox.maze.model.Maze;
import it.pyrox.maze.model.Wall;

public class MazeController {
	
	public Cell getCellAt(Maze maze, int row, int col) {
		return maze.getMatrix()[row][col];
	}
	
	public Cell getCellAtPosition(Maze maze, Cell cell, Position position, int step) {		
		if (cell == null) {
			return null;
		}
		
		int col = cell.getCol();
		int row = cell.getRow();
		int width = maze.getWidth();
		int height = maze.getHeight();
		
		Cell toReturn = null;
		
		switch (position) {
		
			case LEFT:
				col -= step;
				if (col >= 0) {
					toReturn = getCellAt(maze, row, col);
				}
				break;
				
			case UP:
				row -= step;
				if (row >= 0) {
					toReturn = getCellAt(maze, row, col);
				}
				break;
				
			case RIGHT:
				col += step;
				if (col < width) {
					toReturn = getCellAt(maze, row, col);
				}
				break;
				
			case DOWN:
				row += step;
				if (row < height) {
					toReturn = getCellAt(maze, row, col);
				}
				break;
				
			case TOTAL:
				break;
		}
		
		return toReturn;
	}
	
	public void initAllWall(Maze maze) {		
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				maze.setWall(i, j);				
			}
		}
	}
	
	public void initAllFloor(Maze maze) {
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				maze.setFloor(i, j);
			}
		}
	}
	
	public void initSingleFloors(Maze maze) {
		initAllWall(maze);
		for (int i = 1; i < maze.getHeight() - 1; i += 2) {
			for (int j = 1; j < maze.getWidth() - 1; j += 2) {
				maze.setFloor(i, j);
			}
		}
	}
	
	public void breakWall(Maze maze, Floor floor1, Floor floor2) {		
		int col1 = floor1.getCol();
		int col2 = floor2.getCol();
		int row1 = floor1.getRow();
		int row2 = floor2.getRow();
		
		// only works with adjacent cells
		if (col1 == col2) {
			maze.setFloor(Math.abs((row2 + row1) / 2), col1);	
		}
		else if (row1 == row2) {
			maze.setFloor(row1, Math.abs((col2 + col1) / 2));			
		}
	}
	
	public Floor breakWall(Maze maze, Wall wall) {		
		Floor father = wall.getFather();
		Floor newFloor = null;
		int colW = wall.getCol();
		int colF = father.getCol();
		int rowW = wall.getRow();
		int rowF = father.getRow();
		
		if (colW == colF) {
			if (rowF < rowW) {
				newFloor = new Floor(rowW + 1, colW);
			}
			else if (rowF > rowW) {
				newFloor = new Floor(rowW - 1, colW);				
			}
		}
		else if (rowW == rowF){
			if (colF < colW) {
				newFloor = new Floor(rowW, colW + 1);
			}
			else {
				newFloor = new Floor(rowW, colW - 1);
			}
		}
		
		if (newFloor != null) {
			maze.setFloor(newFloor.getRow(), newFloor.getCol());
		}
		
		maze.setFloor(rowW, colW);
		
		return newFloor;
	}
}
