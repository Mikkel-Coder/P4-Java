/*
 * This file is used to managed a client in a thread when they are connected
 */

package Opgave1;

import java.net.Socket;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private TrainsList trains;
	private boolean exit_flag;

	public ClientHandler(Socket clientSocket, TrainsList trains) {
		this.clientSocket = clientSocket;
		this.trains = trains;
		this.exit_flag = false;
	}

	private void processRequest(TrainProtocol request) {
		TrainProtocolCommandType command = request.getCommand();

		switch (command) {
		case EXIT:
			System.out.println("[CLIENT_HANDLER] Got EXIT from client with IP: " + this.clientSocket.getInetAddress());
			this.exit_flag = true;
			return;

		case GET:
			System.out.println("[CLIENT_HANDLER] Got GET from client with IP: " + this.clientSocket.getInetAddress());
			getTrains();
			return;

		case MODIFY:
			System.out.println("[CLIENT_HANDLER] Got MODIFY from client with IP: " + this.clientSocket.getInetAddress());
			modifyTrainList();
			return;
		}
	}

	private void getTrains() {

		// Get trainlist
		TrainsList copyOfTrainsList = new TrainsList();

		// copy everything from trainlist to TrainProtocol
		copyOfTrainsList.addAll(this.trains);
		TrainProtocol payload = new TrainProtocol(null, copyOfTrainsList);

		// Send Train Protocol to client
		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();
			System.out.println("[CLIENT_HANDLER] Sent Copy of train list to client with IP: " + this.clientSocket.getInetAddress());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modifyTrainList() {
		
		// When a client wants to modify the train list, we must block every
		// other thread on the server side. This is to insure against race
		// conditions so that no client may add or remove trains from the
		// trainlist at the same time
		synchronized (this.trains) {

			// Push the current trainlist to client
			TrainProtocol payload = new TrainProtocol(null, this.trains);
			try {
				ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
				output.writeObject(payload);
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Get the new train list from the client
			try {
				ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
				TrainProtocol response = (TrainProtocol) input.readObject();

				// Update the trainsList now
				this.trains.clear();
				this.trains.addAll(response.getTrains());
				System.out.println("[CLIENT_HANDLER] Updated server side Train List with size: " + this.trains.size() + " from clinet IP: " + this.clientSocket.getInetAddress());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// relase trainlist to other thread is now safe to do so
		}
	}

	@Override
	public void run() {
		try {

			// Keep the socket open for as long as the client wishes
			while (this.exit_flag != true) {
				ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
				TrainProtocol request = (TrainProtocol) input.readObject();
				processRequest(request);
			}

			System.out.println("[CLIENT_HANDLER] Terminated service for client with IP: " + this.clientSocket.getInetAddress());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
