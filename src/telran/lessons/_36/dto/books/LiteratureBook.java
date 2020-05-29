package telran.lessons._36.dto.books;

public class LiteratureBook extends Book
{
	private static final long serialVersionUID = 10L;
	Genre genre;

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}

	@Override
	public String toString()
	{
		return "LiteratureBook{" +
			"genre=" + genre +
			", isbn=" + isbn +
			", title='" + title + '\'' +
			", author='" + author + '\'' +
			", cover=" + cover +
			", pages=" + pages +
			", publisher='" + publisher + '\'' +
			", publishDate=" + publishDate +
			'}';
	}
}
