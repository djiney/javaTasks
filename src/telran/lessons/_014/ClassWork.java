package telran.lessons._014;

import telran.lessons._11.TreeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassWork
{
	public static void main(String[] args)
	{
		Integer[] numbers = getShuffledArray(10);
		TreeSet<Integer> set = new TreeSet<>(numbers);

		System.out.println("----PRINT ROTATED------------------------------------------");
		set.printRotated();
		System.out.println("----PRINT------------------------------------------");
		set.print();
		System.out.println("----HEIGHT------------------------------------------");
		System.out.println(set.height());
		System.out.println("----WIDTH-------------------------------------------");
		System.out.println(set.width());


		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	}

	private static Integer[] getShuffledArray(int length)
	{
		Integer[] numbers = new Integer[length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}

		List<Integer> intList = Arrays.asList(numbers);
		Collections.shuffle(intList);
		intList.toArray(numbers);

		return numbers;
	}
}
