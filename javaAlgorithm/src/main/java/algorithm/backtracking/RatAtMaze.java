package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * in a N * N maze，a rat start at (0,0)
 * 
 * at rat can only move right or move down
 * 
 * solve the maze using backtracking
 * 
 * @author linjingfu
 *
 */
public class RatAtMaze {
	//if the position can walk
	static final int OK = 1;
	static final int BAD = 0;
	
	public static void main(String[] args) {
		int[][] maze = {
			{1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
		};
		RatAtMaze ram = new RatAtMaze();
		List<Point> answer = ram.solve(maze);
		System.out.println(answer);
	}
	
	/**
	 * I choose a route list as the answer,
	 *  also, you can return int[][], where 0 represents not walk, 1 represents walked, as an answer
	 * 
	 * @return the route list from start to maze exit
	 */
	public List<Point> solve(int[][] maze) {
		List<Point> steps = new ArrayList<Point>();
		//first step
		steps.add(new Point(0, 0));
		//then using backtracking to move forward
		if (tryMove(maze, steps)) {
			return steps;
		}
		return null;
	}
	
	public boolean tryMove(int[][] maze, List<Point> steps) {
		Point prev = steps.get(steps.size() - 1);
		if (prev.x == maze.length - 1 && prev.y == maze.length - 1) {
			//already reach the exit of maze
			return true;
		}
		//try every available move
		for (MoveWay moveWay : MoveWay.values()) {
			Point next = moveWay.move(prev);
			if (check(maze, next)) {
				steps.add(next);
				if (tryMove(maze, steps)) {
					return true;
				} else {
					//backtracking
					steps.remove(steps.size() - 1);
				}
			}
		}
		//this way can't go
		return false;
	}
	
	private boolean check(int[][] maze, Point p) {
		//you can just move right or move down, so will not repeat with prev steps
		return p.x < maze.length && p.y < maze.length && maze[p.x][p.y] == OK;
	}

	static class Point {
		final int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}

	enum MoveWay {
		RIGHT {
			@Override
			Point move(Point p) {
				return new Point(p.x + 1, p.y);
			}
		},
		DOWN {
			@Override
			Point move(Point p) {
				return new Point(p.x, p.y + 1);
			}
		},
		;

		abstract Point move(Point p);
	}

}
