package test;

import java.util.concurrent.SynchronousQueue;

public class Run1 extends Run0 {

	public Run1() {
		super();
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
			System.out.println("Run1....");
			if (!this.msg.isEmpty()) {
				System.out.println("Message in the Queue Run 1 "+ this.msg.remove());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Run1....After Sleep");
			try {
				sq.put(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
