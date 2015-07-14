package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

public class R0MainTest {

	public static R0SimulationCalendar cal;

	public static CountDownLatch simulationCountDownLatch;

	public static ArrayList<R0Abstract> simulationElements = new ArrayList<R0Abstract>();

	public static ArrayList<Thread> threads = new ArrayList<Thread>();;

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, InterruptedException {

		cal = initSimulationCalendar();

		initSimulationEnvironment();

		updateSimulationCountDownLatch();

		startThreads();

		System.out.println("Run");

		while (true) {
			System.out.println("Time : " + cal.getTime());
			releaseTokens();
			// Threads are running, wait them to finish the simulation step
			simulationCountDownLatch.await();
			takeTokens();
			// Simulator Progress
			System.out.println("Sleep Time: " + cal.getTime());
			R0MainTest.sleep(1000);
			cal.add(Calendar.HOUR_OF_DAY, 1);
		}
	}

	private static void initSimulationEnvironment() {
		// add Server
		simulationElements.add(0, new R1Server());
		// add One client
		ArrayList<R0Abstract> clients = new ArrayList<R0Abstract>();
		// Add as much as clients you want theoretically
		clients.add(0, new R2Client());
		simulationElements.addAll(clients);
		// Adding clients to Server #in this case only ONE
		((R1Server) simulationElements.get(0)).setClients(clients);
		// Adding server reference to Clients
		for (int i = 1; i < simulationElements.size(); i++) {
			((R2Client) simulationElements.get(i)).setServer(simulationElements
					.get(0));
		}

	}

	public static R0SimulationCalendar initSimulationCalendar() {
		R0SimulationCalendar.getInstance().initSimulationCalendar();
		return R0SimulationCalendar.getInstance();
	}

	public static synchronized void releaseTokens() {
		for (R0Abstract r : simulationElements) {
			r.releaseSimulationToken();
		}
	}

	public static void takeTokens() {
		for (R0Abstract r : simulationElements) {
			r.setReleaseToken(true);
		}
		for (R0Abstract r : simulationElements) {
			r.takeSimulationToken();
		}
		updateSimulationCountDownLatch();
	}

	public static void updateSimulationCountDownLatch() {
		simulationCountDownLatch = new CountDownLatch(simulationElements.size());
		for (R0Abstract r : simulationElements) {
			r.updateEndOfSimulationTasks(simulationCountDownLatch);
		}
	}

	public static void startThreads() {
		for (R0Abstract r : simulationElements) {
			threads.add(new Thread(r));
		}
		for (Thread thread : threads) {
			thread.start();
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
