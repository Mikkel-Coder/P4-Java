package Opgave3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Opgave1.ClientHandler;
import Opgave1.Server;
import Opgave1.TrainProtocol;

public class PublicServer {
	public static void main(String[] args) {
		// Start a new server with default arguments
		Server server = new Server(TrainProtocol.DEFAULT_PORT);

		// Generate some random trains for the sever to use
		server.generateRandomTrains();
		
		try {
			// Use a custom IP
			InetAddress ip = InetAddress.getByName("::1");
			
			ServerSocket serverSocket = new ServerSocket(server.servicePort, server.maxConnectionQueue, ip);
			System.out.println("[PUBLIC_SERVER] Listening for connections on IP: " + serverSocket.getInetAddress() + " ...");

			// Start the sever!
			while (true) {
				Socket clientSocket = serverSocket.accept();

				System.out.println("[PUBLIC_SERVER] Got connection from IP: " + clientSocket.getInetAddress());

				ClientHandler clientHandler = new ClientHandler(clientSocket, server.trains);
				Thread clientHandlerThread = new Thread(clientHandler);
				clientHandlerThread.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
