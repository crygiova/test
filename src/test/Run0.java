package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public abstract class Run0 implements Runnable {

	protected SynchronousQueue<Integer> sq;
	protected LinkedBlockingQueue<String> msg;
	protected Run0 peer;
	CountDownLatch ended;
	boolean start = false;

	public Run0() {
		super();
		this.sq = new SynchronousQueue<Integer>();
		this.msg = new LinkedBlockingQueue<String>();

	}

	public void init(Run0 peer, CountDownLatch ended) {
		this.peer = peer;
		this.ended = ended;
	}

	public void countDown() {
		this.ended.countDown();
	}

	public Integer takeSq() {
		Integer buffer = -1;
		try {
			buffer = this.sq.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}

	public void putSq(Integer i) {
		try {
			this.sq.put(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedBlockingQueue<String> getMsg() {
		return msg;
	}

	public void inputMsg(String msg) {
		this.msg.add(msg);
	}

	public void sendMsg(String msg) {
		this.peer.inputMsg(msg);
	}

	public void updateCountdownReference(CountDownLatch ended) {
		this.ended = ended;
	}
}
