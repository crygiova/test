package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Run2 extends Run0 {

	public Run2() {
		super();
	}

	@Override
	public void run() {
		while (true) {
			this.takeSq();// Put Start
			System.out.println("Run2....");
			try {
				System.out.println(this.msg.take());
				this.sendMsg("Run2ToRun1");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.countDown();
			try {
				while (!this.ended.await(10, TimeUnit.MILLISECONDS)) {
					String str = this.msg.poll(10, TimeUnit.MILLISECONDS);
					if (str != null) {
						System.out.println(str);
						this.sendMsg("Nipote ");
					}
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				this.putSq(0);
			}

		}

	}

}
