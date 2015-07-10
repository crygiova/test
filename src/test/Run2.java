package test;

import java.util.concurrent.SynchronousQueue;

public class Run2 extends Run0{
	private Run0 r;

	public Run2(Run0 r) {
		super();
		this.r = r;
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
			r.sendMsg("Ciao");
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
