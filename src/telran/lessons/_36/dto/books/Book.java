package telran.lessons._36.dto.books;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable
{
	private static final long serialVersionUID = 1L;
	long isbn;

	String title;
	String author;
	CoverType cover;
	int pages;
	String publisher;
	LocalDate publishDate;

	public Book() {}

	public Book(long isbn, String title, String author, CoverType cover, int pages, String publisher, LocalDate publishDate)
	{
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.cover = cover;
		this.pages = pages;
		this.publisher = publisher;
		this.publishDate = publishDate;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getIsbn()
	{
		return isbn;
	}

	public void setIsbn(long isbn)
	{
		this.isbn = isbn;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public CoverType getCover()
	{
		return cover;
	}

	public void setCover(CoverType cover)
	{
		this.cover = cover;
	}

	public int getPages()
	{
		return pages;
	}

	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public LocalDate getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate)
	{
		this.publishDate = publishDate;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return isbn == book.isbn;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(isbn);
	}

	public void copy(Book book)
	{
		isbn = book.isbn;
		title = book.title;
		author = book.author;
		cover = book.cover;
		pages = book.pages;
		publisher = book.publisher;
		publishDate = book.publishDate;
	}
}
