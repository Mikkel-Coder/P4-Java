/*
 * Extend the train list server to also from time to time write the list to a 
 * file. If we wish to restart the server part, we should also read the file 
 * and initiate the list with the saved list.
 * 
 * 
 * THE MAIN SERVER PROGRAM
 * 
 * Run this file to start a server
 */

package Opgave2;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.Date;

import Opgave1.TrainsList;
import Opgave1.TrainProtocol;
import Opgave1.ClientHandler;
import Opgave1.Server;

public class AdvancedServer extends Server {
	public File trainListFile;
	public int autoSaveFrequncyInSecounds;

	public AdvancedServer(int port, String trainListFileName) {
		super(port);
		this.trainListFile = new File(trainListFileName);
		this.autoSaveFrequncyInSecounds = 20;
	}

	/*
	 * Import a TrainList object form a file
	 */
	public void importTrainListFromFile() throws IOException, ClassNotFoundException {
		FileInputStream fileInput = new FileInputStream(this.trainListFile.getPath());
		ObjectInputStream objectInput = new ObjectInputStream(fileInput);
		this.trains = (TrainsList) objectInput.readObject();
		objectInput.close();
	}

	/*
	 * Export a TrainList object to a file
	 */
	public void exportTrainListToFile() throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(this.trainListFile.getPath());
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		objectOutput.writeObject(this.trains);
		objectOutput.close();
	}
	
	public static void main(String[] args) {

		// Start a new server with default arguments
		AdvancedServer server = new AdvancedServer(TrainProtocol.DEFAULT_PORT, "default_trainlist.ser");

		// Import the default train if it exists
		if (server.trainListFile.exists()) {
			try {
				server.importTrainListFromFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("[ADVANCED_SERVER] Using TrainList: \"" + server.trainListFile.getPath() + "\"");

		// Else generate a new file with random trains
		} else {
			System.out.println("[ADVANCED_SERVER] No TrainList file found at: \"" + server.trainListFile.getAbsolutePath() + "\" Using random TrainList");
			server.generateRandomTrains();
			try {
				server.exportTrainListToFile();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// From time to time save the TrainList
		new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(TimeUnit.SECONDS.toMillis(server.autoSaveFrequncyInSecounds));
					server.exportTrainListToFile();
					System.out.println("[ADVANCED_SERVER_AUTOSAVER] TrainList with capacity: " + server.trains.size() +  " saved at " + new Date());					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}).start();

		// Start the sever!
		try {
			ServerSocket serverSocket = new ServerSocket(server.servicePort, server.maxConnectionQueue, InetAddress.getLocalHost());
			System.out.println("[ADVANCED_SERVER] Listening for connections...");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("[ADVANCED_SERVER] Got connection from IP: " + clientSocket.getInetAddress());

				ClientHandler clientHandler = new ClientHandler(clientSocket, server.trains);
				Thread clientHandlerThread = new Thread(clientHandler);
				clientHandlerThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
