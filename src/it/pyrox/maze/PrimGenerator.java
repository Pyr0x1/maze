package it.pyrox.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator {
	
	private Maze maze;
	private List<Wall> wallList;
	
	public Maze generate(int width, int height) {
		
		maze = new Maze(width, height);
		maze.initAllWall();
		maze.setFloor(1, 1);
		
		wallList = new ArrayList<Wall>();
		addWalls((Floor) maze.getCellAt(1, 1));
		
		while (wallList.size() > 0) {
			Random randomGenerator = new Random();
			int random = randomGenerator.nextInt(wallList.size());
			Wall wall = wallList.get(random);
			Floor newFloor = maze.breakWall(wall);
			addWalls(newFloor);
			wallList.remove(wall); // remove the wall that now has become a floor from the list
		}
		
		return maze;
	}
	
	private void addWalls(Floor floor) {
		
		for (int i = 0; i < Position.TOTAL.getInt(); i++) {
		
			Cell cell = maze.getCellAtPosition(floor, Position.getPosition(i), 1);
			Wall wall = null;
			
			if (cell instanceof Wall)
				wall = (Wall) cell;
			
			// if wall is met first time, add it to list, otherwise take it away because it is a "thin" wall
			if (wall != null && !wall.isBorder(maze.getHeight(), maze.getWidth())) {
				if (!wallList.contains(wall)) {
					wall.setFather(floor);
					wallList.add(wall);
				}
				else
					wallList.remove(wall);
			}
		}
	}
}
