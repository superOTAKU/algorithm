package algorithm.backtracking;

/**
 * 
 * in a 8 * 8 chess table, a knight start from (0, 0), traverse every cell once and only once
 * a kight‘s move rule:
 * 	horizontal plus 1, vertical plus 2 or horizontal plus 2, vertical plus 1
 * 
 * 在8 * 8的棋盘中，初始骑士位于(0, 0)处，遍历所有格子，并且每个格子只能经过一次
 * 骑士的移动规则：
 * 	横向走1步，纵向走2步 或者 横向走2步，纵向走1步
 * 
 * @author linjingfu
 *
 */
public class KnightTour {
	//the table size
	private final static int N = 8;
	//8 ways to move from one point to another
	private final static int[] MOVE_X = {-1, -1, -2, -2,  1, 1,  2, 2};
	private final static int[] MOVE_Y = {-2,  2, -1,  1, -2, 2, -1, 1};
	private final static int FREE = -1;
	
	public int[][] solve() {
		int[][] table = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j] = FREE;
			}
		}
		//init position at (0, 0)
		table[0][0] = 0;
		if (tryMoveForward(table, 0, 0, 1)) {
			return table;
		}
		return null;
	}
	
	/**
	 * from (prevX, prevY), move currentStep, and try forward, backtracking if cannot forward
	 * @param table
	 * @param prevX
	 * @param prevY
	 * @param nextStep
	 * @return
	 */
	public boolean tryMoveForward(int[][] table, int prevX, int prevY, int currentStep) {
		if (currentStep == N * N) {
			//already solved
			return true;
		}
		//we have 8 ways to try
		for (int i = 0; i < N; i++) {
			int nextX = prevX + MOVE_X[i];
			int nextY = prevY + MOVE_Y[i];
			if (check(table, nextX, nextY)) { // first check if the move is legal
				table[nextX][nextY] = currentStep; //move currentStep
				if (tryMoveForward(table, nextX, nextY, currentStep + 1)) { //try next move step
					return true;
				} else {
					//backtracking
					table[nextX][nextY] = FREE;
				}
			}
		}
		//we can't find the answer
		return false;
	}
	
	private boolean check(int[][] table, int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N && table[x][y] == FREE;
	}
	
	public void printTable(int[][] table) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (table[i][j] < 10) {
					System.out.print(" ");
				}
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		KnightTour kt = new KnightTour();
		int[][] answer = kt.solve();
		if (answer != null) {
			System.out.println("- The KnightTour answer -");
			kt.printTable(answer);
		} else {
			System.out.println("no answer");
		}
	}
	
}
