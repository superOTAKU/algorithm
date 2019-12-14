package algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * in a N * N maze，a rat start at (0,0)
 * 
 * at rat can only move in four directions, so you have to check if your route is repeat
 * 
 * solve the maze using backtracking
 * 
 * @author linjingfu
 *
 */
public class RatAtMazeFourDirection {
	//if the position can walk
	static final int OK = 1;
	static final int BAD = 0;
	
	public static void main(String[] args) {
		int[][] maze = {
			{1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 1},
		};
		RatAtMazeFourDirection ram = new RatAtMazeFourDirection();
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
		Set<Point> visited = new HashSet<Point>();
		//first step
		Point first = new Point(0, 0);
		steps.add(first);
		visited.add(first);
		//then using backtracking to move forward
		if (tryMove(maze, steps, visited)) {
			return steps;
		}
		return null;
	}
	
	public boolean tryMove(int[][] maze, List<Point> steps, Set<Point> visited) {
		Point prev = steps.get(steps.size() - 1);
		if (prev.x == maze.length - 1 && prev.y == maze.length - 1) {
			//already reach the exit of maze
			return true;
		}
		//try every available move
		for (MoveWay moveWay : MoveWay.values()) {
			Point next = moveWay.move(prev);
			if (check(maze, next, visited)) {
				steps.add(next);
				visited.add(next);
				if (tryMove(maze, steps, visited)) {
					return true;
				} else {
					//backtracking
					Point bad = steps.remove(steps.size() - 1);
					visited.remove(bad);
				}
			}
		}
		//this way can't go
		return false;
	}
	
	private boolean check(int[][] maze, Point p, Set<Point> visited) {
		return p.x >= 0 && p.x < maze.length 
				&& p.y >= 0 && p.y < maze.length 
				&& maze[p.x][p.y] == OK 
				&& !visited.contains(p); //check if repeat
	}

	static class Point {
		final int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return x * 31 + y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Point) {
				Point other = (Point)obj;
				return x == other.x && y == other.y; 
			}
			return false;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}

	enum MoveWay {
		UP {
			@Override
			Point move(Point p) {
				return new Point(p.x, p.y - 1);
			}
		},
		DOWN {
			@Override
			Point move(Point p) {
				return new Point(p.x, p.y + 1);
			}
		},
		LEFT {
			@Override
			Point move(Point p) {
				return new Point(p.x - 1, p.y);
			}
		},
		RIGHT {
			@Override
			Point move(Point p) {
				return new Point(p.x + 1, p.y);
			}
		},
		;

		abstract Point move(Point p);
	}

}
