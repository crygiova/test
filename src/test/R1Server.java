package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class R1Server extends Run0 {

	public R1Server() {
		super();
	}

	@Override
	public void run() {
		while (true) {
			// take the token and start working
			this.takeSq();
			// TODO Do the all the operations needed at that time
			System.out.println("Run1..Tasks..");
			for (int i = 0; i < 3; i++) {
				this.sendMsg("Ciao1To2 " + i);
				// System.out.println(this.takeMsg());
				MainTest.sleep(1000);
			}
			// TODO check message queue and keep doing it till is not empty
			// TODO Notify the Simulator that the operations are finished
			this.countDown();
			System.out.println("Run1EndedTasks");
			// TODO check message queue and keep doing it till the main Thread
			// update release
			while (!this.isRelease() || !this.msg.isEmpty()) {
				String str = this.pollMsgMs(10);
				if (str != null) {
					System.out.println("ReceivedRun1Post:" + str);
					// this.sendMsg(str + " Risposta dopo CountDown Run0");
				}
			}
			// TODO use release to putSq and reset release to false
			this.setRelease(false);
			this.putSq(0);
		}
	}
}
