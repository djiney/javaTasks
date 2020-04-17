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
		return "[^\\s,@]*@[a-zA-Z0-9]+(-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z])*[a-zA-Z]?){1,3}";
	}

	public static String arithmeticExpression()
	{
		return "\\s*([0-9]\\s*[\\+\\-\\*\\/]?\\s*)*;?\\s*";
	}

	public static String phone()
	{
		return "(\\+972-?|0)5[0|2-8](-?\\d){7}";
	}
}
