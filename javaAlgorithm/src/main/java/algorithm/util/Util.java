package algorithm.util;

public class Util {
	
	public static void print(int[][] arr, int width) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.printf("%-" + width + "d", arr[i][j]);
			}
			System.out.println();
		}
	}
	
}
