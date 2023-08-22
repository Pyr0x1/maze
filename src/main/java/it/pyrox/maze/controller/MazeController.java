package it.pyrox.maze.controller;

import it.pyrox.maze.model.Floor;
import it.pyrox.maze.model.Maze;
import it.pyrox.maze.model.Wall;

public class MazeController {
	
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
		int x1 = floor1.getX();
		int x2 = floor2.getX();
		int y1 = floor1.getY();
		int y2 = floor2.getY();
		
		// only works with adjacent cells
		if (x1 == x2) {
			maze.setFloor(x1, Math.abs((y2 + y1) / 2));			
		}
		else if (y1 == y2) {
			maze.setFloor(Math.abs((x2 + x1) / 2), y1);			
		}
	}
	
	public Floor breakWall(Maze maze, Wall wall) {		
		Floor father = wall.getFather();
		Floor newFloor = null;
		int xW = wall.getX();
		int xF = father.getX();
		int yW = wall.getY();
		int yF = father.getY();
		
		if (xW == xF) {
			if (yF < yW) {
				newFloor = new Floor(xW, yW + 1);
			}
			else if (yF > yW) {
				newFloor = new Floor(xW, yW - 1);				
			}
		}
		else if (yW == yF){
			if (xF < xW) {
				newFloor = new Floor(xW + 1, yW);
			}
			else {
				newFloor = new Floor(xW - 1, yW);
			}
		}
		
		if (newFloor != null) {
			maze.setFloor(newFloor.getX(), newFloor.getY());
		}
		
		maze.setFloor(xW, yW);
		
		return newFloor;
	}
}
