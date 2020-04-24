package telran.lessons._23.exceptions;

public class RuleException extends Exception
{
	int delta;

	public RuleException(int delta)
	{
		this.delta = delta;
	}

	public int getDelta()
	{
		return delta;
	}
}
