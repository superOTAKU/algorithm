package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

import algorithm.util.Util;

/**
 * 数独问题，那么，从左到右，从上到下，尝试每个格子，每次填一个数字，然后检查冲突
 * @author linjingfu
 *
 */
public class Sodoku {
	private static final int EMPTY = 0;
	
	public static void main(String[] args) {
		int[][] grid = {
				{3, 0, 6, 5, 0, 8, 4, 0, 0},  
                {5, 2, 0, 0, 0, 0, 0, 0, 0},  
                {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
			};
		Sodoku sodoku = new Sodoku();
		sodoku.solve(grid);
		Util.print(grid, 2);
	}
	
	public void solve(int[][] grid) {
		List<Pos> empties = findEmptyPoses(grid);
		if (!trySolve(grid, empties, 0)) {
			throw new IllegalStateException("no solution");
		}
	}
	
	private boolean trySolve(int[][] grid, List<Pos> empties, int emptyIndex) {
		if (emptyIndex == empties.size()) {
			return true;
		}
		Pos emptyPos = empties.get(emptyIndex);
		for (int number = 1; number <= 9; number++) {
			if (checkNoConflict(grid, emptyPos.i, emptyPos.j, number)) {
				grid[emptyPos.i][emptyPos.j] = number;
				if (trySolve(grid, empties, emptyIndex + 1)) {
					return true;
				} else {
					//backtracking
					grid[emptyPos.i][emptyPos.j] = EMPTY;
				}
			}
		}
		return false;
	}
	
	private boolean checkNoConflict(int[][] grid, int i, int j, int number) {
		for (int k = 0; k < 9; k++) {
			//同一行
			if (grid[i][k] == number) {
				return false;
			}
			//同一列
			if (grid[k][j] == number) {
				return false;
			}
		}
		//九宫格内
		int iStart = i / 3 * 3;
		int iEnd = iStart + 3;
		int jStart = j / 3 * 3;
		int jEnd = jStart + 3;
		for (int k = iStart; k < iEnd; k++) {
			for (int l = jStart; l < jEnd; l++) {
				if (grid[k][l] == number) {
					return false;
				}
			}
		}
		return true;
	}
	
	private List<Pos> findEmptyPoses(int[][] grid) {
		List<Pos> poses = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == EMPTY) {
					poses.add(new Pos(i, j));
				}
			}
		}
		return poses;
	}
	
	static class Pos {
		int i, j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
	
}
