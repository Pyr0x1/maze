package it.pyrox.maze.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.pyrox.maze.controller.MazeController;
import it.pyrox.maze.enums.Position;
import it.pyrox.maze.model.Cell;
import it.pyrox.maze.model.Floor;
import it.pyrox.maze.model.Maze;
import it.pyrox.maze.model.Wall;

public class PrimGenerator extends Generator {
	
	private List<Wall> wallList;
	
	public PrimGenerator() {
		controller = new MazeController();
	}

	public Maze generate(int width, int height) {		
		maze = new Maze(width, height);
		controller.initAllWall(maze);		
		maze.setFloor(1, 1);
		
		wallList = new ArrayList<>();
		addWalls((Floor) controller.getCellAt(maze, 1, 1));
		
		Random randomGenerator = new Random();

		while (!wallList.isEmpty()) {
			int random = randomGenerator.nextInt(wallList.size());
			Wall wall = wallList.get(random);
			Floor newFloor = controller.breakWall(maze, wall);			
			addWalls(newFloor);
			wallList.remove(wall); // remove the wall that now has become a floor from the list
		}
		
		return maze;
	}
	
	private void addWalls(Floor floor) {		
		for (int i = 0; i < Position.TOTAL.getInt(); i++) {
		
			Cell cell = controller.getCellAtPosition(maze, floor, Position.getPosition(i), 1);
			Wall wall = null;
			
			if (cell instanceof Wall) {
				wall = (Wall) cell;
			}
			
			// if wall is met first time, add it to list, otherwise take it away because it is a "thin" wall
			if (wall != null && !wall.isBorder(maze.getHeight(), maze.getWidth())) {
				if (!wallList.contains(wall)) {
					wall.setFather(floor);
					wallList.add(wall);
				}
				else {
					wallList.remove(wall);
				}
			}
		}
	}
}
