package algorithm.backtracking;

import java.util.Arrays;

/**
 * 
 * in a matrix graph, with m color
 * 
 * @author linjingfu
 *
 */
public class MColorGraph {
	private static final int NO_COLOR = 0;
	private static final int LINK = 1;

	public static void main(String[] args) {
		int graph[][] = { 
				{ 0, 1, 1, 1 },
				{ 1, 0, 1, 0 },
				{ 1, 1, 0, 1 },
				{ 1, 0, 1, 0 },
			};
		MColorGraph problem = new MColorGraph();
		int[] colors = problem.solve(graph, 3);
		System.out.println(Arrays.toString(colors));
	}

	public int[] solve(int[][] graph, int colorNum) {
		int[] colors = new int[graph.length];
		if (trySolve(graph, colors, colorNum, 0)) {
			return colors;
		}
		throw new IllegalStateException("no solution");
	}
	
	public boolean trySolve(int[][] graph, int[] colors, int colorNum, int pointIndex) {
		if (pointIndex == graph.length) {
			return true;
		}
		for (int color = 1; color <= colorNum; color++) {
			if (checkNoConflict(graph, colors, pointIndex, color)) {
				colors[pointIndex] = color;
				if (trySolve(graph, colors, colorNum, pointIndex + 1)) {
					return true;
				} else {
					//backtracking
					colors[pointIndex] = NO_COLOR;
				}
			}
		}
		return false;
	}
	
	private boolean checkNoConflict(int[][] graph, int[] colors, int pointIndex, int color) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[pointIndex][i] == LINK && colors[i] == color) {
				return false;
			}
		}
		return true;
	}
	
}
