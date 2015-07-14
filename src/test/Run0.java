package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public abstract class Run0 implements Runnable {

	// TODO make it client server, means that the AG can have a list of clients
	// which
	// at the moment are peers and the Prosumers they can have only a server at
	// the moment
	protected SynchronousQueue<Integer> sq;
	protected LinkedBlockingQueue<String> msg;
	protected Run0 peer;
	CountDownLatch ended;
	boolean release = false;

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

	public String takeMsg() {
		try {
			return this.msg.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String pollMsgMs(long timeout) {
		try {
			return this.msg.poll(timeout,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCountdownReference(CountDownLatch ended) {
		this.ended = ended;
	}

	public boolean isRelease() {
		return release;
	}

	public void setRelease(boolean release) {
		this.release = release;
	}
}
