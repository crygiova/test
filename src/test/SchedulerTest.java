package test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SchedulerTest implements Runnable {

	BlockingQueue<EventTest> bq = new LinkedBlockingDeque<EventTest>();
	SimulationCalendar cal;
	CountDownLatch waitFor;
	CountDownLatch countDown;
	Runnable rl;
	public LinkedBlockingDeque<Integer> in;
	public LinkedBlockingDeque<Integer> out;
	SynchronousQueue<Integer> sq;
	int i;

	public SchedulerTest(SimulationCalendar s, LinkedBlockingDeque<Integer> in,
			LinkedBlockingDeque<Integer> out, SynchronousQueue<Integer> sq) {
		super();
		this.cal = s;
		this.schedule();
		this.in = in;
		this.out = out;
		this.sq = sq;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sq.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out
					.println("Scheduler Test:" + i + " cal: " + cal.getTime());
			try {
				sq.put(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
//	@Override
//	public void run() {
//		while (true) {
//			try {
//				in.takeFirst();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out
//					.println("Scheduler Test:" + i + " cal: " + cal.getTime());
//			out.add(10);
//		}
//
//	}

	public void updateCDL(CountDownLatch waitFor, CountDownLatch countDown) {
		this.waitFor = waitFor;
		this.countDown = countDown;
	}

	public void execute() throws InterruptedException {
		if (cal.get(Calendar.HOUR_OF_DAY) == bq.peek().h) {
			System.out.println("TEST : " + cal.getTime());
			Thread.sleep(10000);
			System.out.println("TEST 2 : ");
		}
	}

	public void schedule() {
		bq.add(new EventTest());
	}

}
