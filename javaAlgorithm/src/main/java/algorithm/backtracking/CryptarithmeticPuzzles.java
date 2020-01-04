package algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * please refer to {@link https://www.geeksforgeeks.org/solving-cryptarithmetic-puzzles-backtracking-8/}
 * for the details of the problem.
 * 
 * @author linjingfu
 *
 */
public class CryptarithmeticPuzzles {
	
	public static void main(String[] args) {
		CryptarithmeticPuzzles puzzle = new CryptarithmeticPuzzles();
		Map<Character, Integer> result = puzzle.solve("SEND", "MORE", "MONEY");
		System.out.println(result);
	}

	public Map<Character, Integer> solve(String op1, String op2, String result) {
		char[] op1Chs = op1.toCharArray();
		char[] op2Chs = op2.toCharArray();
		char[] resultChs = result.toCharArray();
		Set<Character> chars = new HashSet<Character>();
		for (char ch : op1Chs) {
			chars.add(ch);
		}
		for (char ch : op2Chs) {
			chars.add(ch);
		}
		for (char ch : resultChs) {
			chars.add(ch);
		}
		List<Character> charList = new ArrayList<Character>(chars);
		//char -> the number it represents
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		if (trySolve(op1Chs, op2Chs, resultChs, map, charList, 0)) {
			//print result
			System.out.println("Input : " + op1 + " + " + op2 + " = " + result);
			System.out.println("Output: " + new String(op1Chs) + " + " + new String(op2Chs) + " = " + new String(resultChs));
			return map;
		}		
		throw new IllegalStateException("no solution");
	}
	
	private boolean trySolve(char[] op1Chs, char[] op2Chs, char[] resultChs,
			Map<Character, Integer> map, List<Character> charList, int charIndex) {
		if (charIndex == charList.size()) {
			return checkSumEquals(op1Chs, op2Chs, resultChs);
		}
		char ch = charList.get(charIndex);
		for (int number = 0; number <= 9; number++) {
			//check if repeat
			if (!map.containsValue(number)) {
				map.put(ch, number);
				char numberCh = (char)('0' + number);
				for (int i = 0; i < op1Chs.length; i++) {
					if (op1Chs[i] == ch) {
						op1Chs[i] = numberCh;
					}
				}
				for (int i = 0; i < op2Chs.length; i++) {
					if (op2Chs[i] == ch) {
						op2Chs[i] = numberCh;
					}
				}
				for (int i = 0; i < resultChs.length; i++) {
					if (resultChs[i] == ch) {
						resultChs[i] = numberCh;
					}
				}

				if (trySolve(op1Chs, op2Chs, resultChs, map, charList, charIndex + 1)) {
					return true;
				} else {
					//backtracking
					map.remove(ch);
					for (int i = 0; i < op1Chs.length; i++) {
						if (op1Chs[i] == numberCh) {
							op1Chs[i] = ch;
						}
					}
					for (int i = 0; i < op2Chs.length; i++) {
						if (op2Chs[i] == numberCh) {
							op2Chs[i] = ch;
						}
					}
					for (int i = 0; i < resultChs.length; i++) {
						if (resultChs[i] == numberCh) {
							resultChs[i] = ch;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean checkSumEquals(char[] op1Chs, char[] op2Chs, char[] resultChs) {
		int op1 = Integer.parseInt(new String(op1Chs));
		int op2 = Integer.parseInt(new String(op2Chs));
		int result = Integer.parseInt(new String(resultChs));
		return op1 + op2 == result;
	}
	
}
