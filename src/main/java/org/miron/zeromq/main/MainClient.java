package org.miron.zeromq.main;

import org.miron.zeromq.Client;

public class MainClient {
	public static void main(String[] args) {
		Client client = new Client("localhost", 5555);
		client.sendMessage("Test...");
	}
}
