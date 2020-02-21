package telran.additional._05_ArrayInt;

public class ArrayInt
{
	private int size;
	private int[] data;

	private boolean[] indexArray;
	private int defaultValue;

	public ArrayInt(int length)
	{
	    this.size = length;
		this.data = new int[length];

        this.indexArray = new boolean[length];
	}

	public int size()
    {
        return this.size;
    }

	public void setAll(int number)
	{
        this.indexArray = new boolean[this.size];
        this.defaultValue = number;
    }

	public void set(int index, int value)
	{
		this.checkIndex(index);

        this.data[index] = value;
        this.indexArray[index] = true;
	}

	public int get(int index)
	{
        this.checkIndex(index);

		return this.indexArray[index] ? this.data[index] : this.defaultValue;
	}

	private void checkIndex(int index) throws ArrayIndexOutOfBoundsException
	{
	    if (index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
	}
}
