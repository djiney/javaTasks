package telran.lessons._24.measure;

public class Length
{
	float number;
	LengthUnit unit;

	public Length(float number, LengthUnit unit)
	{
		this.number = number;
		this.unit = unit;
	}

	public Length plus(Length length)
	{
		return new Length((getRealNumber() + length.getRealNumber()) / unit.getValue(), unit);
	}

	public Length minus(Length length)
	{
		return new Length((getRealNumber() - length.getRealNumber()) / unit.getValue(), unit);
	}

	public Length convert(LengthUnit newUnit)
	{
		return new Length(number * unit.getValue() / newUnit.getValue(), newUnit);
	}

	public float getRealNumber()
	{
		return number * unit.getValue();
	}

	public float getNumber()
	{
		return number;
	}

	public void setNumber(float number)
	{
		this.number = number;
	}

	public LengthUnit getUnit()
	{
		return unit;
	}

	public void setUnit(LengthUnit unit)
	{
		this.unit = unit;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Length length = (Length) o;
		return Float.compare(length.number, number) == 0;
	}

	@Override
	public String toString()
	{
		return number + unit.toString();
	}
}
