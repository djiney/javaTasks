package telran.lessons._36.controllers.books;

import telran.lessons._36.components.BookGenerator;
import telran.lessons._36.components.Generator;
import telran.lessons._36.controllers.AbstractLoader;
import telran.lessons._36.dto.books.Book;

import java.util.List;

public class BookRestoreApplication extends AbstractLoader<Book>
{
	public static void main(String[] args)
	{
		new BookRestoreApplication().load();
	}

	@Override
	protected void processList(List<Book> list)
	{
		list.forEach(System.out::println);
	}

	@Override
	protected Generator<Object> getGenerator()
	{
		return new BookGenerator();
	}
}
