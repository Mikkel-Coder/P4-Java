/* 
 * Repeat with the server-side code that you may have. 
 * Write at least three fault possibilities.
 */

package Opgave2;

import java.net.Socket;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import Opgave1.X_TrainsList;
import Opgave1.X_TrainProtocol;
import Opgave1.X_TrainProtocolCommandType;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private X_TrainsList trains;
	private boolean exit_flag;

	public ClientHandler(Socket clientSocket, X_TrainsList trains) {
		this.clientSocket = clientSocket;
		this.trains = trains;
		this.exit_flag = false;
	}

	private void processRequest(X_TrainProtocol request) {
		X_TrainProtocolCommandType command = request.getCommand();

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
		X_TrainsList copyOfTrainsList = new X_TrainsList();

		// copy everything from trainlist to TrainProtocol
		copyOfTrainsList.addAll(this.trains);
		X_TrainProtocol payload = new X_TrainProtocol(null, copyOfTrainsList);

		// Send Train Protocol to client
		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();
			System.out.println("[CLIENT_HANDLER] Sent Copy of train list to client with IP: " + this.clientSocket.getInetAddress());

		} catch (IOException e) {
			System.out.println("[CLIENT_HANDLER] Failed to send a copy of trainlist: " + e.getMessage());
		}
	}

	private void modifyTrainList() {

		// When a client wants to modify the train list, we must block every
		// other thread on the server side. This is to insure against race
		// conditions so that no client may add or remove trains from the
		// trainlist at the same time
		synchronized (this.trains) {

			// Push the current trainlist to client
			X_TrainProtocol payload = new X_TrainProtocol(null, this.trains);
			try {
				ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
				output.writeObject(payload);
				output.flush();
			} catch (IOException e) {
				System.out.println("[CLIENT_HANDLER] Failed to send train list to client: " + e.getMessage());
			}

			// Get the new train list from the client
			try {
				ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
				X_TrainProtocol response = (X_TrainProtocol) input.readObject();

				// Update the trainsList now
				this.trains.clear();
				this.trains.addAll(response.getTrains());
				System.out.println("[CLIENT_HANDLER] Updated server side Train List with size: " + this.trains.size() + " from clinet IP: " + this.clientSocket.getInetAddress());
			} catch (IOException e) {
				System.out.println("[CLIENT_HANDLER] Failed read train list from client: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("[CLIENT_HANDLER] Failed to decode Trainprotocol from client: " + e.getMessage());
			}

			// relase trainlist to other threads is now safe to do so
		}
	}

	@Override
	public void run() {
		try {

			// Keep the socket open for as long as the client wishes
			while (this.exit_flag != true) {
				ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
				X_TrainProtocol request = (X_TrainProtocol) input.readObject();
				processRequest(request);
			}
			System.out.println("[CLIENT_HANDLER] Terminated service for client with IP: " + this.clientSocket.getInetAddress());
			
		} catch (IOException e) {
			System.out.println("[CLIENT_HANDLER] Failed read from input from client: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[CLIENT_HANDLER] Failed to decoed Trainprotocol from client: " + e.getMessage());
		}
	}
}
