package telran.lessons._36.dto.persons;

import java.io.Serializable;

public class Address implements Serializable
{
	private static final long serialVersionUID = 20L;

	String city;
	String street;
	int building;
	int apartment;

	public Address() {}

	public Address(String city, String street, int building, int apartment)
	{
		this.city = city;
		this.street = street;
		this.building = building;
		this.apartment = apartment;
	}

	public String getCity()
	{
		return city;
	}

	public String getStreet()
	{
		return street;
	}

	public int getBuilding()
	{
		return building;
	}

	public int getApartment()
	{
		return apartment;
	}

	@Override
	public String toString()
	{
		return "Address{" +
			"city='" + city + '\'' +
			", street='" + street + '\'' +
			", building=" + building +
			", apartment=" + apartment +
			'}';
	}
}
