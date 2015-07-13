package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Run1 extends Run0 {

	public Run1() {
		super();
	}
	

	@Override
	public void run() {
		while (true) {
			this.takeSq();
			System.out.println("Run1....");
			this.sendMsg("Run1TORun2");
			try {
				System.out.println(this.msg.take());
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.sendMsg("Run1TORun2-SecondOne");
			try {
				System.out.println(this.msg.take());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.countDown();
			try {
				this.ended.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.putSq(0);
		}
	}
}
