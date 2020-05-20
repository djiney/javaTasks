package telran.lessons._33.components;

public class Drone implements Comparable<Drone>
{
	private final int id;

	/* STATE */
	private DroneStatus status = DroneStatus.LANDED;
	private Height height;
	private int flightDuration = 0;

	/* COUNTERS */
	private int passengersCount = 0;
	private int flightTime = 0;
	private int landedTime = 0;

	public Drone(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public int getPassengersCount()
	{
		return passengersCount;
	}

	public int getFlightTime()
	{
		return flightTime;
	}

	public int getLandedTime()
	{
		return landedTime;
	}

	public boolean isLanded()
	{
		return status == DroneStatus.LANDED;
	}

	public void preIteration()
	{
		if (!isLanded() && --flightDuration == 0) {
			land();
		}
	}

	public void postIteration()
	{
		if (isLanded()) {
			landedTime++;
		} else {
			flightTime++;
		}
	}

	public void takeOff(Height height, int duration)
	{
		passengersCount++;
		status = DroneStatus.FLYING;
		flightDuration = duration;

		this.height = height;
		height.take(this);
	}

	public void land()
	{
		status = DroneStatus.LANDED;
		height.clear();
		height = null;
	}

	@Override
	public int compareTo(Drone drone)
	{
		return drone.getId() == id ? 0 : 1;
	}

	@Override
	public String toString()
	{
		return String.format(
			"Drone %d was in the air %d minutes. It transferred %d passengers. It was in a waiting queue %d minutes",
			id,
			flightTime,
			passengersCount,
			landedTime
		);
	}
}