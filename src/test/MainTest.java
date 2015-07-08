package test;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;


public class MainTest implements Observer {

	public static Double test = 10d;
	public static SimulationCalendar cal;

	public static TimerTask myTask = new TimerTask() {
		@Override
		public void run() {

			System.out.println("Task Scheduled at : "
					+ SimulationCalendar.getInstance().getTime());
		}
	};

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, InterruptedException {
		cal = SimulationCalendar.getInstance();
		// SimCalObservable obs = new SimCalObservable(cal);
		// obs.addObserver(new MainTest());
		System.out.println("Start " + cal.getInstance().getTime() + " CC "
				+ Calendar.getInstance().getTime());
		// Timer timer = new Timer();
		cal.startSimulationCalendar(6000);
		SchedulerTest st = new SchedulerTest(cal);
		// timer.scheduleAtFixedRate(myTask, Calendar.getInstance().getTime(),
		// 60000);
		while(true)
		{
			st.execute();
			cal.add(cal.HOUR_OF_DAY, 1);
			System.out.println("ADDING one hour "+ cal.getTime());
			Thread.sleep(1000);
		}
		// testSimulationCalendar();

		// CalendarTest.addNMinutesToTime(Calendar.getInstance());
		// CalDemo();
//		System.out.println("Stop");

	}

	public static void testSimulationCalendar() {
		SimulationCalendar cal = SimulationCalendar.getInstance();
		//cal.startSimulationCalendar(60000);// 1 hour in 1 minute

		// Test One hour is passed
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 1; i++)
			System.out.println("One Hour!!!");
		System.out.println("Stop Calendar");
		cal.stopSimluationCalendar();

	}

	public static void CalDemo() {
		// create a calendar
		Calendar cal = Calendar.getInstance();
		SimulationCalendar cal2 = SimulationCalendar.getInstance();
		// print current time
		System.out.println("Current year is :" + cal.getTime());
		// set the year,month and day to something else
		cal.set(1995, 5, 25);
		// print the result
		System.out.println("Altered year is :" + cal.getTime());
		// print current time
		System.out.println("2) Current year is :" + cal2.getTime());
		// set the year,month and day to something else
		cal2.set(1995, 5, 25);
		// print the result
		System.out.println("2) Altered year is :" + cal2.getTime());
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("GIOVAAAAAA"+cal.getTime());

	}

}
