package telran.additional._03_matrixMultiplication.tests;

import org.junit.jupiter.api.Test;
import telran.additional._03_matrixMultiplication.Main;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{
	@Test
	public void testMultiplying()
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

		int[][] resultMatrix = {
			  {39, 42, 45, 72},
			  {89, 96, 103, 166},
			  {139, 150, 161, 260},
			  {189, 204, 219, 354},
			  {239, 258, 277, 448},
		};

		assertArrayEquals(resultMatrix, Main.matrixMultiplication(firstMatrix, secondMatrix));
	}
}