package test;

import java.util.concurrent.SynchronousQueue;

public class Run2 extends Run0{

	public Run2() {
		this.sq = new SynchronousQueue<Integer>();
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
			System.out.println("Run2....");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Run2....I have slept");
			try {
				sq.put(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
