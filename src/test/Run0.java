package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public abstract class Run0 implements Runnable {

	SynchronousQueue<Integer> sq;
	ConcurrentLinkedQueue<String> msg;

	public Run0() {
		super();
		this.sq = new SynchronousQueue<Integer>();
		this.msg = new ConcurrentLinkedQueue<String>();
	}

	public SynchronousQueue<Integer> getSq() {
		return sq;
	}

	public void setSq(SynchronousQueue<Integer> sq) {
		this.sq = sq;
	}

	public ConcurrentLinkedQueue<String> getMsg() {
		return msg;
	}

	public void setMsg(ConcurrentLinkedQueue<String> msg) {
		this.msg = msg;
	}

	public void sendMsg(String msg) {
		this.msg.add(msg);
	}
}
