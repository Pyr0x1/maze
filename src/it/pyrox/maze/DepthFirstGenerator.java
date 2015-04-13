package it.pyrox.maze;

public class DepthFirstGenerator extends Generator {
	
	private Maze maze;
	
	public Maze generate(int width, int height) {
		maze = new Maze(width, height);
		maze.initSingleFloors();
		
		Floor currentFloor = (Floor) maze.getCellAt(1, 1);
		currentFloor.setVisited(true);
		visit(currentFloor);
		
		return maze;
	}
	
	private void visit(Floor currentFloor) {
		Position[] positions = Position.getRandomPositions(100);
		
		for (int i = 0; i < Position.TOTAL.getInt(); i++) {
			Floor nextFloor = (Floor) maze.getCellAtPosition(currentFloor, positions[i], 2);
			if (nextFloor != null && !nextFloor.isClosed() && !nextFloor.isVisited()) {
				nextFloor.setVisited(true);
				maze.breakWall(currentFloor, nextFloor);
				currentFloor = nextFloor;
				visit(currentFloor);
			}
		}
		currentFloor.setClosed(true);
	}
}
