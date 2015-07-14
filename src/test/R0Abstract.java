package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public abstract class R0Abstract implements Runnable {

	// TODO make it client server, means that the AG can have a list of clients
	// which
	// at the moment are peers and the Prosumers they can have only a server at
	// the moment
	// Queue to take the token from and release it from the Simulator
	protected SynchronousQueue<Integer> simulationToken;
	// Messages queue between R0Abstract
	protected LinkedBlockingQueue<String> messageQueue;
	// Count down to communicate that the current R0Abstract has finished it s
	// own tasks
	// TODO change the name of all the variables with a proper meaning
	protected CountDownLatch endOfSimulationTasks;
	private boolean countDown = false;
	// releaseToken for releasing the simulationToken to the simulator
	private boolean releaseToken = false;

	public R0Abstract() {
		super();
		this.simulationToken = new SynchronousQueue<Integer>();
		this.messageQueue = new LinkedBlockingQueue<String>();

	}

	public void updateEndOfSimulationTasks(CountDownLatch ended) {
		this.endOfSimulationTasks = ended;
		this.countDown = true;
	}

	public void notifyEndOfSimulationTasks() {
		// TODO each R0Abstract can countDown only once
		if (countDown) {
			this.endOfSimulationTasks.countDown();
			countDown = false;
		}
	}

	public Integer takeSimulationToken() {
		Integer buffer = -1;
		try {
			buffer = this.simulationToken.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}

	public void releaseSimulationToken() {
		try {
			this.simulationToken.put(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Add a msg to this R0Abstract
	public void addMessage(String msg) {
		this.messageQueue.add(msg);
	}

	// Send a msg to a specified R0Abstract
	public void sendMessage(R0Abstract peer, String msg) {
		peer.addMessage(msg);
	}

	public String takeMsg() {
		try {
			return this.messageQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String pollMsgMs(long timeout) {
		try {
			return this.messageQueue.poll(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isReleaseToken() {
		return releaseToken;
	}

	public void setReleaseToken(boolean releaseToken) {
		this.releaseToken = releaseToken;
	}
}
