package telran.additional._03_matrixMultiplication;

import java.util.Arrays;

public class Main
{
	public static void main(String[] args)
	{
		int[][] firstMatrix = {
			  {1, 2},
			  {3, 4},
			  {5, 6},
			  {7, 8},
			  {9, 10},
		};

		int[][] secondMatrix = {
			  {11, 12, 13, 22},
			  {14, 15, 16, 25}
		};

		printMatrix(firstMatrix, "firstMatrix");
		printMatrix(secondMatrix, "secondMatrix");

		printMatrix(matrixMultiplication(firstMatrix, secondMatrix), "resultMatrix");
	}

	private static void printMatrix(int[][] matrix, String label)
	{
		System.out.println("---- " + label + " ----");

		for (int[] array : matrix) {
			System.out.println(Arrays.toString(array));
		}

		System.out.println("");
	}

	public static int[][] matrixMultiplication(int[][] firstMatrix, int[][] secondMatrix)
	{
		boolean isSecondWider = firstMatrix[0].length > secondMatrix[0].length;

		int maxWidth = Math.max(firstMatrix[0].length, secondMatrix[0].length);
		int minWidth = Math.min(firstMatrix[0].length, secondMatrix[0].length);
		int maxHeight = Math.max(firstMatrix.length, secondMatrix.length);

		int[][] result = new int[maxHeight][maxWidth];

		int firstElement, secondElement;

		for (int i = 0; i < maxHeight; i++) {
			for (int j = 0; j < maxWidth; j++) {
				for (int k = 0; k < minWidth; k++) {
					firstElement = !isSecondWider ? firstMatrix[i][k] : firstMatrix[k][j];
					secondElement = !isSecondWider ? secondMatrix[k][j] : secondMatrix[i][k];

					result[i][j] += firstElement * secondElement;
				}
			}
		}

		return result;
	}
}
