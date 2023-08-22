package it.pyrox.maze.enums;

import java.util.Random;

public enum Position {
	
	UP, RIGHT, DOWN, LEFT, TOTAL;
	
	public static Position getPosition(int value) {
		switch(value) {
			case 0:
				return UP;
			case 1:
				return RIGHT;
			case 2:
				return DOWN;
			case 3:
				return LEFT;
			default:
				return TOTAL;
		}
	}
	
	public int getInt() {
		switch(this) {
			case UP:
				return 0;
			case RIGHT:
				return 1;
			case DOWN:
				return 2;
			case LEFT:
				return 3;
			case TOTAL:
				return 4;
			default:
				return -1;
		}
	}
	
	public static Position[] getRandomPositions(int iterations) {
		
		Random randomGenerator = new Random();
		Position[] array = new Position[TOTAL.getInt()];
		
		for (int i = 0; i < TOTAL.getInt(); i++)
			array[i] = getPosition(i);
		
		for (int i = 0; i < iterations; i++) {
			int a = randomGenerator.nextInt(TOTAL.getInt());
			int b = randomGenerator.nextInt(TOTAL.getInt());
			
			Position tmp = array[a];
			array[a] = array[b];
			array[b] = tmp;
		}
		
		return array;
	}
}
