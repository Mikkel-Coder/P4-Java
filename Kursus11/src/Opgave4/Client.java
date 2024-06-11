package Opgave4;

import java.net.Socket;
import java.net.InetAddress;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import Opgave1.X_TrainCLIEvents;
import Opgave1.X_TrainsList;
import Opgave1.X_TrainProtocol;
import Opgave1.X_TrainProtocolCommandType;
import Opgave1.X_Train;
import Opgave1.X_TrainCLI;

// Errors
import java.net.UnknownHostException;

public class Client implements X_TrainCLIEvents {
	public Socket clientSocket;
	public X_TrainsList trains;

	public Client(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.trains = new X_TrainsList();
	}

	public void updateLocalTrainsList() {
		// Create payload as GET command
		X_TrainProtocol payload = new X_TrainProtocol(X_TrainProtocolCommandType.GET, null);

		try {
			// Write to server
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush(); // Empty the Stream so that everything is sent
			System.out.println("[CLIENT] Sent GET from client!");

			// Read the response from the server
			ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
			X_TrainProtocol response = (X_TrainProtocol) input.readObject();

			// Update local train list
			this.trains = response.getTrains();

		} catch (IOException e) {
			System.out.println("[CLIENT] Failed IO operation: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[CLIENT] Failed to decode Train protocol: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void exit() {
		// Create payload as EXIT command
		X_TrainProtocol payload = new X_TrainProtocol(X_TrainProtocolCommandType.EXIT, null);

		try {
			// Write to server
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush(); // Empty the Stream so that everything is sent
			System.out.println("[CLIENT] Sent EXIT from client!");

		} catch (IOException e) {
			System.out.println("[CLIENT] Failed to sent EXIT to server: " + e.getMessage());
		}
	}

	@Override
	public void onBeforeTrainListModification() {

		// Get train list from server
		X_TrainProtocol payload = new X_TrainProtocol(X_TrainProtocolCommandType.MODIFY, null);

		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();

			// Read the response from the server
			ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
			X_TrainProtocol response = (X_TrainProtocol) input.readObject();

			// Update local train list
			this.trains.clear();
			this.trains.addAll(response.getTrains());

		} catch (IOException  e) {
			System.out.println("[CLIENT] Failed IO operation: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[CLIENT] Failed decoed trainprotocol: " + e.getMessage());
		}

		// Now call the orginial methods add
		// Then continue to onAfterTraListModification
	}

	@Override
	public void onAfterTrainListModification() {

		// Push the new train list with the added train to the server
		X_TrainProtocol payload = new X_TrainProtocol(null, this.trains);

		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();

		} catch (IOException e) {
			System.out.println("[CLIENT] Failed to sent local Trainlist to server with error: " + e.getMessage());
		}

	}

	public static void main(String[] args) {
		try {
			// Setup Socket
			Socket clientSocket = new Socket(InetAddress.getLocalHost(), 5234); // Unknow host and IO Exception
			Client client = new Client(clientSocket);
			System.out.println("[CLINET] Socket open with IP: " + clientSocket.getInetAddress());

			// First time update local train list
			client.updateLocalTrainsList();
			for (X_Train train : client.trains) {
				train.printInfo();
			}

			// Create the train cli thread
			X_TrainCLI traincli = new X_TrainCLI(client.trains, System.in, System.out);
			Thread traincliThread = new Thread(traincli);
			traincliThread.start();

			// Add event when the TranCLI updates or deletes trains
			traincli.addListener(client);

			// Only continue to exit when the traincli thread is dead
			while (traincliThread.isAlive()) {

				// Sleep so that we do not consume to much CPU time
				Thread.sleep(100); // Interrupt exception
			}

			// Remember to close the socket gracefully
			client.exit();
			System.out.println("[CLIENT] Sokcet closed.");

		} catch (UnknownHostException  e) {
			System.out.println("[CLIENT] Unknow host: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[CLIENT] Sokcet error: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("[CLIENT] Traincli thread interrerupted: " + e.getMessage());
		}

	}

}
