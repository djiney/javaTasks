package telran.lessons._36.controllers.books;

import telran.lessons._36.components.BookGenerator;
import telran.lessons._36.components.Generator;
import telran.lessons._36.controllers.AbstractCreator;

public class BookCreateApplication extends AbstractCreator
{
	public static void main(String[] args) throws Exception
	{
		new BookCreateApplication().create();
	}

	@Override
	protected Generator<Object> getGenerator()
	{
		return new BookGenerator();
	}
}