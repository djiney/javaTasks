package telran.lessons._33.components;

public class Height
{
	private final int id;

	/* STATE */
	private Drone drone;

	/* COUNTERS */
	private int flightsCount = 0;

	public Height(int id)
	{
		this.id = id;
	}

	public int getFlightsCount()
	{
		return flightsCount;
	}

	public boolean isBusy()
	{
		return drone != null;
	}

	public void take(Drone drone)
	{
		this.drone = drone;
		flightsCount++;
	}

	public void clear()
	{
		drone = null;
	}

	@Override
	public String toString()
	{
		return "Height " + id;
	}

	public String getStat()
	{
		return String.format("%s: %d flights", toString(), flightsCount);
	}
}