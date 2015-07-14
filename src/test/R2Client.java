package test;

public class R2Client extends R0Abstract {

	R0Abstract server;

	public R2Client() {
		super();
	}

	public void setServer(R0Abstract server) {
		this.server = server;
	}

	@Override
	public void run() {
		while (true) {
			// start execution
			this.takeSimulationToken();
			// TODO run tasks
			System.out.println("Client....");
			// TODO if there are messages use those till the queue is not empty
			String str;
			// while (!this.msg.isEmpty()) {
			while ((str = this.pollMessageMs(10)) != null) {
				System.out.println("Client<-Server: " + str);
				str += " RESP1";
				System.out.println("Client->Server: " + str);
				this.sendMessage(this.server, str);
			}
			// Notify the end of the tasks
			this.notifyEndOfSimulationTasks();
			System.out.println("ClientEndOfSimTasks");
			// TODO if there are messages use those till the simulator does not
			while (!this.isReleaseToken() || !this.messageQueue.isEmpty()) {
				str = this.pollMessageMs(10);
				if (str != null) {
					System.out.println("Client<-Server: " + str);
					str += " RESP2";
					System.out.println("Client->Server: " + str);
					this.sendMessage(this.server, str);
				}
			}
			// send the release signal
			// TODO wait for signal of the simulator to putSQ
			// TODO use release for putSq
			this.setReleaseToken(false);
			this.releaseSimulationToken();
		}
	}
}
