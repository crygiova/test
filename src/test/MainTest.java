package test;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest implements Observer {

	public static Double test = 10d;
	public static SimulationCalendar cal;
	public static CountDownLatch hasToWaitFor = new CountDownLatch(1);
	public static CountDownLatch hasToCountDown = new CountDownLatch(1);
	public static CountDownRunnable cdr;
	public static TimerTask myTask = new TimerTask() {
		@Override
		public void run() {

			System.out.println("Task Scheduled at : "
					+ SimulationCalendar.getInstance().getTime());
		}
	};

	public synchronized static void updateReferences() {
		hasToWaitFor = new CountDownLatch(1);
		hasToCountDown = new CountDownLatch(1);
		cdr.updateCoundDowndReferences(hasToWaitFor, hasToCountDown);
	}

	
	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, InterruptedException {
		cal = SimulationCalendar.getInstance();
		Run0 r[] = new Run0[2];
		r[0] = new Run1();
		r[1] = new Run2();
		Thread t[] = new Thread[2];
		t[0] = new Thread(r[0]);
		t[1] = new Thread(r[1]);
		
		cal.startSimulationCalendar(6000);
		System.out.println("Run");
		t[0].start();
		t[1].start();
		
		while (true) {
			r[0].getSq().put(10);
			r[1].getSq().put(10);
			
//			sq.put(10);
			r[0].getSq().take();
			r[1].getSq().take();
			cal.add(cal.HOUR_OF_DAY, 1);
			System.out.println("ADDING one hour " + cal.getTime());
			Thread.sleep(1000);
		}
	}
	
	
//	public static void main(String[] args) throws NoSuchFieldException,
//	SecurityException, InterruptedException {
//cal = SimulationCalendar.getInstance();
//Semaphore rl = new Semaphore(1, true);
//LinkedBlockingDeque<Integer> in = new LinkedBlockingDeque<Integer>();
//LinkedBlockingDeque<Integer> out = new LinkedBlockingDeque<Integer>();
//SynchronousQueue<Integer> sq = new SynchronousQueue<Integer>();
//SchedulerTest st = new SchedulerTest(cal, in, out, sq);
//cal.startSimulationCalendar(6000);
//System.out.println("Run");
//Thread th = new Thread(st);
//th.start();
//while (true) {
//	sq.put(10);
//	sq.take();
//	System.out.println("Sleeping " + cal.getTime());
//	cal.add(cal.HOUR_OF_DAY, 1);
//	System.out.println("ADDING one hour " + cal.getTime());
//	Thread.sleep(1000);
//}
//}

//	public static void main(String[] args) throws NoSuchFieldException,
//			SecurityException, InterruptedException {
//		cal = SimulationCalendar.getInstance();
//		Semaphore rl = new Semaphore(1,true);
//		LinkedBlockingDeque<Integer> in = new LinkedBlockingDeque<Integer>();
//		LinkedBlockingDeque<Integer> out = new LinkedBlockingDeque<Integer>();
//		SchedulerTest st = new SchedulerTest(cal, in,out);
//		cal.startSimulationCalendar(6000);
//		System.out.println("Run");
//		Thread th = new Thread(st);
//		th.start();
//		while (true) {
//			
//			in.add(10);
//			//Aggregator
//			out.takeFirst();
//			System.out.println("Sleeping " + cal.getTime());
//			cal.add(cal.HOUR_OF_DAY, 1);
//			System.out.println("ADDING one hour " + cal.getTime());
//			Thread.sleep(1000);
//		}
//	}

//	public static void main(String[] args) throws NoSuchFieldException,
//			SecurityException, InterruptedException {
//		cal = SimulationCalendar.getInstance();
//		// SimCalObservable obs = new SimCalObservable(cal);
//		// obs.addObserver(new MainTest());
//		System.out.println("Start " + cal.getInstance().getTime() + " CC "
//				+ Calendar.getInstance().getTime());
//		cdr = new CountDownRunnable();
//		cdr.updateCoundDowndReferences(hasToWaitFor, hasToCountDown);
//		// Timer timer = new Timer();
//		cal.startSimulationCalendar(6000);
//		Thread th = new Thread(cdr);
//		th.start();
//		System.out.println("Run");
//		while (true) {
//			hasToCountDown.await();
//			Thread.sleep(2000);
//			cal.add(cal.HOUR_OF_DAY, 1);
//			System.out.println("ADDING one hour " + cal.getTime());
//			hasToWaitFor.countDown();
//			updateReferences();
//		}
//		// testSimulationCalendar();
//
//		// CalendarTest.addNMinutesToTime(Calendar.getInstance());
//		// CalDemo();
//		// System.out.println("Stop");
//
//	}

	// public static void main(String[] args) throws NoSuchFieldException,
	// SecurityException, InterruptedException {
	// cal = SimulationCalendar.getInstance();
	// // SimCalObservable obs = new SimCalObservable(cal);
	// // obs.addObserver(new MainTest());
	// System.out.println("Start " + cal.getInstance().getTime() + " CC "
	// + Calendar.getInstance().getTime());
	// // Timer timer = new Timer();
	// cal.startSimulationCalendar(6000);
	// SchedulerTest st = new SchedulerTest(cal, waitFor, countDown);
	// // st.run();
	// Thread th = new Thread(st);
	// th.start();
	// // timer.scheduleAtFixedRate(myTask, Calendar.getInstance().getTime(),
	// // 60000);
	// while (true) {
	// countDown.await();
	// cal.add(cal.HOUR_OF_DAY, 1);
	// System.out.println("ADDING one hour " + cal.getTime());
	// Thread.sleep(1000);
	// waitFor.countDown();
	// waitFor = new CountDownLatch(1);
	// countDown = new CountDownLatch(1);
	// st.updateCDL(waitFor, countDown);
	//
	// System.out.println("Await");
	// }
	// // testSimulationCalendar();
	//
	// // CalendarTest.addNMinutesToTime(Calendar.getInstance());
	// // CalDemo();
	// // System.out.println("Stop");
	//
	// }

	public static void testSimulationCalendar() {
		SimulationCalendar cal = SimulationCalendar.getInstance();
		// cal.startSimulationCalendar(60000);// 1 hour in 1 minute

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
		System.out.println("GIOVAAAAAA" + cal.getTime());

	}

}
