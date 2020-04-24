package telran.lessons._22;

public class Main
{
	@SuppressWarnings("unused")
	public static int getAvailableMemoryBlockSize()
	{
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle = right / 2;

		byte[] array;

		while (left <= right)
		{
			try {
				array = new byte[middle];
				left = middle + 1;
			} catch (OutOfMemoryError e) {
				right = middle - 1;
			}

			middle = left / 2 + right / 2;
			array = null;
		}

		return middle;
	}
}
