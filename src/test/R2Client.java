package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class R2Client extends Run0 {

	public R2Client() {
		super();
	}

	@Override
	public void run() {
		while (true) {
			// start execution
			this.takeSq();
			// TODO run tasks
			System.out.println("Run2..Tasks..");

			// TODO if there are messages use those till the queue is not empty
			String str;
			// while (!this.msg.isEmpty()) {
			while ((str = this.pollMsgMs(10)) != null) {
				System.out.println("ReceivedRun2Pre:"+str);
				this.sendMsg("SendedByRun2PreInRespTo " + str);
			}
			// Notify the end of the tasks
			this.countDown();
			System.out.println("Run2EndedTasks");
			// TODO if there are messages use those till the simulator does not
			while (!this.isRelease() || !this.msg.isEmpty()) {
				str = this.pollMsgMs(10);
				if (str != null) {
					System.out.println("ReceivedRun2Post:" + str);
					this.sendMsg("SendedByRun2PostInRespTo " + str);
				}
			}
			// send the release signal
			// TODO wait for signal of the simulator to putSQ
			// TODO use release for putSq
			this.setRelease(false);
			this.putSq(0);
		}
	}
}
