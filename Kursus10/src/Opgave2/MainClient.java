/*
 * Extend the train list server to also from time to time write the list to a 
 * file. If we wish to restart the server part, we should also read the file 
 * and initiate the list with the saved list.
 * 
 * THIS FILE IS ONLY A WRAPPER TO RUN THE CLIENT PROGRAM FROM ASSIGMENT 1
 */

package Opgave2;

public class MainClient {
	public static void main(String[] args) {
		Opgave1.Client.main(null);
	}
	
}
