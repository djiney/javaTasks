package telran.lessons._36.components;

import telran.lessons._36.dto.books.*;

import java.time.LocalDate;

public class BookGenerator extends AbstractGenerator<Object>
{
	private static final long N_BOOKS = 100;
	private static final int PROB_LITERATURE_BOOK = 80;
	private static final int MAX_SUBJECTS = 7;
	private static final int N_AUTHORS = 15;
	private static final int MIN_PAGES = 50;
	private static final int MAX_PAGES = 1500;
	private static final int N_PUBLISHERS = 3;
	private static final int MIN_YEAR = 1970;
	private static final int MAX_YEAR = 2020;

	protected String getName()
	{
		return "books.data";
	}

	protected long getCount()
	{
		return N_BOOKS;
	}

	protected Book generateModel()
	{
		return getChance() <= PROB_LITERATURE_BOOK ? getLiteratureBook() : getTechnicalBook();
	}

	private Book getLiteratureBook()
	{
		LiteratureBook book = new LiteratureBook();

		book.copy(getBook());
		book.setGenre(randomEnum(Genre.class));

		return book;
	}

	private Book getTechnicalBook()
	{
		TechnicalBook book = new TechnicalBook();

		book.copy(getBook());
		book.setSubject("subject" + getRandomNumber(1, MAX_SUBJECTS));

		return book;
	}

	private Book getBook()
	{
		int isbn = getRandomNumber(100_000, 1_000_000);

		return new Book(
			isbn,
			"title" + isbn,
			"author" + getRandomNumber(1, N_AUTHORS),
			randomEnum(CoverType.class),
			getRandomNumber(MIN_PAGES, MAX_PAGES),
			"publisher" + getRandomNumber(1, N_PUBLISHERS),
			getPublisherDate()
		);
	}

	private LocalDate getPublisherDate()
	{
		int year = getRandomNumber(MIN_YEAR, MAX_YEAR);
		int dayYear = getRandomNumber(1, 365);
		return LocalDate.ofYearDay(year, dayYear);
	}
}
