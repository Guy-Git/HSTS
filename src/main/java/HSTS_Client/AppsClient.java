package HSTS_Client;

import java.io.IOException;
import java.util.logging.Logger;

import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.client.AbstractClient;

public class AppsClient extends AbstractClient {
	private static final Logger LOGGER =
			Logger.getLogger(AppsClient.class.getName());
	
	private AppsCLI chatClientCLI;	
	public AppsClient(String host, int port) {
		super(host, port);
		this.chatClientCLI = new AppsCLI(this);
	}
	
	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
		
		try {
			chatClientCLI.loop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		chatClientCLI.displayMessage(msg);
	}
	
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		chatClientCLI.closeConnection();
	}
}
