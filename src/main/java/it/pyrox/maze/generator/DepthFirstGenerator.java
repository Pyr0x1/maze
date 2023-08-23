package it.pyrox.maze.generator;

import it.pyrox.maze.controller.MazeController;
import it.pyrox.maze.enums.Position;
import it.pyrox.maze.model.Floor;
import it.pyrox.maze.model.Maze;

public class DepthFirstGenerator extends Generator {
	
	public DepthFirstGenerator() {
		controller = new MazeController();
	}

	public Maze generate(int width, int height) {
		maze = new Maze(width, height);
		controller.initSingleFloors(maze);
		
		Floor currentFloor = (Floor) controller.getCellAt(maze, 1, 1);
		currentFloor.setVisited(true);
		visit(currentFloor);
		
		return maze;
	}
	
	private void visit(Floor currentFloor) {
		Position[] positions = Position.getRandomPositions(100);
		
		for (int i = 0; i < Position.TOTAL.getInt(); i++) {
			Floor nextFloor = (Floor) controller.getCellAtPosition(maze, currentFloor, positions[i], 2);
			if (nextFloor != null && !nextFloor.isClosed() && !nextFloor.isVisited()) {
				nextFloor.setVisited(true);
				controller.breakWall(maze, currentFloor, nextFloor);
				currentFloor = nextFloor;
				visit(currentFloor);
			}
		}
		currentFloor.setClosed(true);
	}
}
