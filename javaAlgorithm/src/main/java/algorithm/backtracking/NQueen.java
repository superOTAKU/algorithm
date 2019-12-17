package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

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
		List<Integer> steps = new ArrayList<Integer>();
		//从上往下走，第一行的每一列都去尝试走
		for (int col = 0; col < n; col++) {
			table[0][col] = VISITED;
			steps.add(col);
			if (tryMove(table, steps, 1)) {
				return table;
			} else {
				//回溯
				table[0][col] = FREE;
				steps.remove(steps.size() - 1);
			}
		}
		return table;
	}
	
	private boolean tryMove(int[][] table, List<Integer> steps, int currentRow) {
		if (currentRow == table.length) {
			return true;
		}
		for (int col = 0; col < table.length; col++) {
			if (check(table, steps, currentRow, col)) {
				table[currentRow][col] = VISITED;
				steps.add(col);
				if (tryMove(table, steps, currentRow + 1)) {
					return true;
				} else {
					table[currentRow][col] = FREE;
					steps.remove(steps.size() - 1);
				}
			} 
		}
		return false;
	}
	
	private boolean check(int[][] table, List<Integer> steps, int currentRow, int currentCol) {
		int[][] t = new int[table.length][table.length];
		for (int i = 0; i < table.length; i++) {
			t[i] = table[i].clone();
		}
		for (int row = 0; row < currentRow; row++) {
			int col = steps.get(row);
			//填充不可放置的区域
			
			//竖线
			for (int r = 0; r < table.length; r++) {
				t[r][col] = -1;
			}
			
			//从左上往右下的斜线
			int r = row, c = col;
			for (int i = 1; i > 0; i++) {
				int rt = r - i, ct = c - i;
				boolean ok = false;
				if (rt >= 0 && ct >= 0) {
					t[rt][ct] = -1;
					ok = true;
				}
				int rb = r + i, cb = c + i;
				if (rb < table.length && cb < table.length) {
					t[rb][cb] = -1;
					ok = true;
				}
				if (!ok) {
					break;
				}
			}
			
			//从右上往左下的竖线
			for (int i = 1; i > 0; i++) {
				int rt = r - i, ct = c + i;
				boolean ok = false;
				if (rt >= 0 && ct < table.length) {
					t[rt][ct] = -1;
					ok = true;
				}
				int rb = r + i, cb = c - i;
				if (rb < table.length && cb >= 0) {
					t[rb][cb] = -1;
					ok = true;
				}
				if (!ok) {
					break;
				}
			}
		}
		return t[currentRow][currentCol] != -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
