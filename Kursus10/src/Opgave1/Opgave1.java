/*
 * This file is only used to both start one server and one client 
 * 
 * The two processes form the server and clients standard output out is then 
 * piped into this main process terminal output 
 * 
 * RUN SERVER.JAVA OR CLIENT.JAVA TO START THEM INDEPENDENTLY
 */

package Opgave1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Opgave1 {
	public static void main(String[] args) {

		try {
			// First start the server and then the client
			Process server = new JavaProcess("Opgave1", "Server");
			Process client = new JavaProcess("Opgave1", "Client");

			// Create thread that logs from the server process
			Thread serverLogThread = new Thread(() -> {
				try {
					printOutput(server.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			serverLogThread.start();

			// And also a thread that logs from the client process
			Thread clientLogThread = new Thread(() -> {
				try {
					printOutput(client.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			clientLogThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Only used to print output from server and client processes
	private static void printOutput(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}
}
