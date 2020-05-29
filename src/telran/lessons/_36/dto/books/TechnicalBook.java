package telran.lessons._36.dto.books;

public class TechnicalBook extends Book
{
	private static final long serialVersionUID = 100L;
	String subject;

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	@Override
	public String toString()
	{
		return "TechnicalBook{" +
			"subject='" + subject + '\'' +
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
