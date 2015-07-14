package test;

import java.util.ArrayList;

public class R1Server extends R0Abstract {

	ArrayList<R0Abstract> clients;

	public R1Server() {
		super();
	}

	public void setClients(ArrayList<R0Abstract> clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		while (true) {
			// take the token and start working
			this.takeSimulationToken();
			// TODO Do the all the operations needed at that time
			System.out.println("Server....");
			for (int i = 0; i < 3; i++) {
				String str = "SToC " + i;
				System.out.println("Server->Client: " + str);
				this.sendMessage(clients.get(0), str);
				// System.out.println(this.takeMsg());
				R0MainTest.sleep(1000);
			}
			// TODO check message queue and keep doing it till is not empty
			// TODO Notify the Simulator that the operations are finished
			this.notifyEndOfSimulationTasks();
			System.out.println("ServerEndOfSimTasks");
			// TODO check message queue and keep doing it till the main Thread
			// update release
			while (!this.isReleaseToken() || !this.messageQueue.isEmpty()) {
				String str = this.pollMessageMs(10);
				if (str != null) {
					System.out.println("Server<-Client: " + str);
					// this.sendMsg(str + " Risposta dopo CountDown Run0");
				}
			}
			// TODO use release to putSq and reset release to false
			this.setReleaseToken(false);
			this.releaseSimulationToken();
		}
	}
}
