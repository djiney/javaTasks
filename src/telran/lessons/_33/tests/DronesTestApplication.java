package telran.lessons._33.tests;

import telran.lessons._33.application.DronesApplication;
import telran.lessons._33.components.Drone;
import telran.lessons._33.components.Height;

public class DronesTestApplication extends DronesApplication
{
	public void test()
	{
		run();

		int totalPassengers = 0;
		for (Drone drone : drones) {
			totalPassengers += drone.getPassengersCount();
			if (drone.getFlightTime() + drone.getLandedTime() != ITERATIONS_NUMBER) {
				throw new RuntimeException("Invalid total drone activity time");
			}

			if (!drone.isLanded()) {
				throw new RuntimeException("Drones are still in the air");
			}
		}

		int totalFlights = 0;
		for (Height height : heights) {
			if (height.isBusy()) {
				throw new RuntimeException("Height is still busy");
			}

			totalFlights += height.getFlightsCount();
		}

		if (totalFlights != totalPassengers) {
			throw new RuntimeException("Flights count and passengers transferred counters do not match");
		}

		System.out.println("All tests passed");
	}
}
