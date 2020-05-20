package telran.lessons._33.application;

import telran.lessons._33.components.Drone;
import telran.lessons._33.components.Height;

import java.util.*;

public class DronesApplication
{
	public final int DRONES_NUMBER = 20;
	public final int HEIGHTS_NUMBER = 15;
	public final int ITERATIONS_NUMBER = 240;

	public final int FLIGHT_DURATION_MIN = 1;
	public final int FLIGHT_DURATION_MAX = 15;

	protected final Drone[] drones = new Drone[DRONES_NUMBER];
	protected final Height[] heights = new Height[HEIGHTS_NUMBER];

	Queue<Drone> queue = new LinkedList<>();

	public void run()
	{
		setUpDrones();
		setUpHeights();

		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			preCycle();
			cycle();
			postCycle();
		}

		landAll();
		printStats();
	}

	private void setUpDrones()
	{
		for (int i = 0; i < DRONES_NUMBER; i++) {
			drones[i] = new Drone(i + 1);
		}
	}

	private void setUpHeights()
	{
		for (int i = 0; i < HEIGHTS_NUMBER; i++) {
			heights[i] = new Height(i + 1);
		}
	}

	private void cycle()
	{
		for (Height height : heights) {
			if (height.isBusy()) {
				continue;
			}

			Drone drone = queue.poll();
			if (drone == null) {
				break;
			}

			drone.takeOff(height, calcDuration());
		}
	}

	private int calcDuration()
	{
		return new Random()
			.nextInt(FLIGHT_DURATION_MAX - FLIGHT_DURATION_MIN + 1) + FLIGHT_DURATION_MIN;
	}

	private void preCycle()
	{
		for (Drone drone : drones) {
			drone.preIteration();

			if (drone.isLanded() && !queue.contains(drone)) {
				queue.add(drone);
			}
		}
	}

	private void postCycle()
	{
		for (Drone drone : drones) {
			drone.postIteration();
		}
	}

	private void landAll()
	{
		for (Drone drone : drones) {
			if (!drone.isLanded()) {
				drone.land();
			}
		}
	}

	private void printStats()
	{
		for (Drone drone : drones) {
			System.out.println(drone);
		}

		System.out.println();

		int maxFlights = 0;
		ArrayList<Height> maxheights = new ArrayList<>();

		for (Height height : heights) {
			System.out.println(height.getStat());

			if (height.getFlightsCount() > maxFlights) {
				maxFlights = height.getFlightsCount();
				maxheights = new ArrayList<>();
			}

			if (height.getFlightsCount() == maxFlights) {
				maxheights.add(height);
			}
		}

		System.out.printf("\nThe most used heights: %s - %d flights\n", maxheights.toString(), maxFlights);
	}
}
