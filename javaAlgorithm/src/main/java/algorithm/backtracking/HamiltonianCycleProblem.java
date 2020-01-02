package algorithm.backtracking;

import java.util.Arrays;

/**
 * 
 * given a undirected graph, find a path to traverse the graph, and traverse every point once and only once.
 * 
 * @author linjingfu
 *
 */
public class HamiltonianCycleProblem {
	private static final int LINK = 1;
	
	public static void main(String[] args) {
		int graph[][] = {
				{0, 1, 0, 1, 0}, 
	            {1, 0, 1, 1, 1}, 
	            {0, 1, 0, 0, 1}, 
	            {1, 1, 0, 0, 1}, 
	            {0, 1, 1, 1, 0}, 
	        }; 
		HamiltonianCycleProblem problem = new HamiltonianCycleProblem();
		int[] path = problem.solve(graph);
		System.out.println(Arrays.toString(path));
	}

	public int[] solve(int[][] graph) {
		int[] path = new int[graph.length + 1];
		boolean[] visited = new boolean[graph.length];
		
		//try start from every point
		for (int i = 0; i < graph.length; i++) {
			if (trySolve(graph, path, visited, i, 0)) {
				//check if cycle, maybe the last point don't link to first point, then retry
				if (graph[path[0]][path[path.length - 2]] == LINK) {
					path[path.length - 1] = path[0];
					return path;
				} else {
					//reset visited
					Arrays.fill(visited, false);
					continue;
				}
			}
		}
		
		throw new IllegalStateException("no solution");
	}
	
	private boolean trySolve(int[][] graph, int[] path, boolean[] visited, int pointIndex, int step) {
		path[step] = pointIndex;
		visited[pointIndex] = true;
		if (step == path.length - 2) {
			return true;
		}
		for (int i = 0; i < graph.length; i++) {
			if (graph[pointIndex][i] == LINK && !visited[i]) {
				if (trySolve(graph, path, visited, i, step + 1)) {
					return true;
				}
			}
		}
		//backtracking
		visited[pointIndex] = false;
		return false;
	}
	
}
