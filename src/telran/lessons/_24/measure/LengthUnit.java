package telran.lessons._24.measure;

public enum  LengthUnit
{
	MM(1), CM(10), IN(25.4f), FT(304.8f), M(1000);

	float value;

	LengthUnit(float value)
	{
		this.value = value;
	}

	public float between(Length firstLength, Length secondLength)
	{
		return (secondLength.getRealNumber() - firstLength.getRealNumber()) / value;
	}

	public float getValue()
	{
		return value;
	}
}
