package test;

import java.util.concurrent.SynchronousQueue;

public abstract class Run0 implements Runnable{

	SynchronousQueue<Integer> sq;
	
	public SynchronousQueue<Integer> getSq() {
		return sq;
	}

	public void setSq(SynchronousQueue<Integer> sq) {
		this.sq = sq;
	}

	public Run0() {
		// TODO Auto-generated constructor stub
	}

}
