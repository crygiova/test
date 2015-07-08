package test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class SchedulerTest implements Runnable {

	BlockingQueue<EventTest> bq = new LinkedBlockingDeque<EventTest>(); 	
	SimulationCalendar cal;

	public SchedulerTest(SimulationCalendar s) {
		super();
		this.cal = s;
		this.schedule();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
		}

	}

	public void execute() {
		if (cal.get(Calendar.HOUR_OF_DAY) == bq.peek().h) {
			System.out.println("TEST: " + cal.getTime());
		}
	}

	public void schedule() {
		bq.add(new EventTest());
	}

}
