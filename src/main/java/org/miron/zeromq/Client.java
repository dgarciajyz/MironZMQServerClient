package org.miron.zeromq;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Client {
	String socketUrl;
	int port;
	ZMQ.Socket socket;

	public Client(String ip, int port) {
		socketUrl = "tcp://" + ip + ":" + port;
	}

	public void sendMessage(String message) {
		try (ZContext context = new ZContext()) {
			System.out.println("Connecting to server");
			socket = context.createSocket(SocketType.REQ);
			socket.connect(socketUrl);
			System.out.println("Sending message");
			socket.send(message.getBytes(ZMQ.CHARSET), 0);
			byte[] reply = socket.recv(0);
			System.out.println("Received from server: " + new String(reply, ZMQ.CHARSET));
			System.out.println("Close Connection");
			socket.close();
		}
	}

}