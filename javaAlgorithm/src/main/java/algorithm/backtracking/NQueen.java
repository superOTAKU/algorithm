package algorithm.backtracking;

/**
 * 
 * the famous n queen problem
 * 
 * @author linjingfu
 *
 */
public class NQueen {
	static final int VISITED = 1;
	static final int FREE = 0;
	
	public static void main(String[] args) {
		NQueen nq = new NQueen();
		int[][] table = nq.solve(8);
		nq.printTable(table);
	}
	
	private void printTable(int[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param n the size of chess table
	 * @return the route at chess table
	 */
	public int[][] solve(int n) {
		int[][] table = new int[n][n];
		if (tryMove(table, 0)) {
			return table;
		}
		throw new IllegalStateException("no solution found");
	}
	
	/**
	 * try to move from row0 to rowN, check conflict, and backtracking 
	 */
	public boolean tryMove(int[][] table, int row) {
		if (row == table.length) {
			return true;
		}
		
		for (int col = 0; col < table.length; col++) {
			if (check(table, row, col)) {
				table[row][col] = VISITED;
				if (tryMove(table, row + 1)) {
					return true;
				} else {
					//backtracking
					table[row][col] = FREE;
				}
			}
		}
		
		return false;
	}
	
	private boolean check(int[][] table, int row, int col) {
		int i,j;
		
		//check vertical repeat
		for(i = 0; i < row; i++) {
			if (table[i][col] == VISITED) {
				return false;
			}
		}
		
		//check leftTop
		for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (table[i][j] == VISITED) {
				return false;
			}
		}
		
		//check rightTop
		for (i = row - 1, j = col + 1; i >= 0 && j < table.length; i--, j++) {
			if (table[i][j] == VISITED) {
				return false;
			}
		}
			
		return true;
	}
	
}
