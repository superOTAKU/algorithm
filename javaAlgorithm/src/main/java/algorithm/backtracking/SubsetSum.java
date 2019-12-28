package algorithm.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * from a given set, find a subset whose sum is k
 * 
 * @author linjingfu
 *
 */
public class SubsetSum {
	
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(Arrays.asList(1, 3, 5,  7));
		System.out.println(new SubsetSum().solve(set, 10));
	}
	
	public Set<Integer> solve(Set<Integer> set, int sum) {
		Set<Integer> subset = new HashSet<Integer>();
		int[] setArr = new int[set.size()];
		int i = 0;
		for (int e : set) {
			setArr[i++] = e;
		}
		if (doSolve(setArr, subset, -1, sum)) {
			return subset;
		}
		throw new IllegalStateException("no solution found");
	}
	
	private boolean doSolve(int[] set, Set<Integer> answers, int idx, int sum) {
		if (sumOf(answers) == sum) {
			return true;
		}
		//try every element after current element
		for (int i = idx + 1; i < set.length; i++) {
			if (check(answers, set[i], i, set, sum)) {
				answers.add(set[i]);
				if (doSolve(set, answers, i, sum)) {
					return true;
				} else {
					//backtracking
					answers.remove(set[i]);
				}
			}
		}
		return false;
	}
	
	private boolean check(Set<Integer> answers, int newElement, int idx, int[] set, int sum) {
		int actual = sumOf(answers) + newElement;
		if (actual == sum) {
			return true;
		}
		if (actual < sum && idx < set.length) {
			return true;
		}
		return false;
	}
	
	private int sumOf(Set<Integer> answers) {
		return answers.stream().reduce((a, b) -> a + b).orElse(0);
	}

}
