package telran.lessons._20;

public class RegularExpressions
{
	public static String variableName()
	{
		return "[\\p{Alpha}$][\\w$]*|_[\\w$]+";
	}

	public static String ipV4()
	{
		return String.format("((%1$s)\\.){3}(%1$s)", numberLessThan256());
	}

	public static String numberLessThan256()
	{
		return "[01]?\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	}

	public static String email()
	{
		return "";
	}

	public static String phoneNumber()
	{
		return "";
	}
}
