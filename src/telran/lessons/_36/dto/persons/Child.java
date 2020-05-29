package telran.lessons._36.dto.persons;

import java.time.LocalDate;

public class Child extends Person
{
	private static final long serialVersionUID = 100L;

	String garden;

	public Child() {}

	public Child(long id, Address address, String name, LocalDate birthDate, String garden)
	{
		super(id, address, name, birthDate);
		this.garden = garden;
	}

	public String getGarden()
	{
		return garden;
	}

	public void setGarden(String garden)
	{
		this.garden = garden;
	}

	@Override
	public String toString()
	{
		return "Child{" +
			"garden='" + garden + '\'' +
			", id=" + id +
			", address=" + address +
			", name='" + name + '\'' +
			", birthDate=" + birthDate +
			'}';
	}
}
