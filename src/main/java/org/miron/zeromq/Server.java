package org.miron.zeromq;

//Hello World server in Java
//Binds REP socket to tcp://*:5555
//Expects "Hello" from client, replies with "World"
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class Server {
	final String socketUrl = "tcp://*:";
	int port;
	ZMQ.Socket socket;

	public Server(int port) {
		try (ZContext context = new ZContext()) {
			// Socket to talk to clients
			socket = context.createSocket(SocketType.REP);
			socket.bind(socketUrl + port);
			System.out.println("Server started...");
			while (!Thread.currentThread().isInterrupted()) {
				byte[] reply = socket.recv(0);
				System.out.println("Received "+ ": [" + new String(reply, ZMQ.CHARSET) + "]");
				String response = "Win-Win";			
				sendResponse(response);
				
			}
		}
	}
	public void sendResponse(String response) {
		try {
			socket.send(response.getBytes(ZMQ.CHARSET), 0);
			Thread.sleep(1000);// Do some 'work'
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}