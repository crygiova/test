package test;

import java.util.concurrent.CountDownLatch;

public class CountDownRunnable implements Runnable {

	CountDownLatch waitFor;
	CountDownLatch countDown;

	public CountDownRunnable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("CountDownRunnableTasks");
			this.countDown.countDown();
			try {
				this.waitFor.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void updateCoundDowndReferences(CountDownLatch waitFor,
			CountDownLatch countDown) {
		this.waitFor = waitFor;
		this.countDown = countDown;
	}
}
