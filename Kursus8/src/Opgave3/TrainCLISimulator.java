package Opgave3;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import java.util.Random;

import Opgave1.TrainsList;



public class TrainCLISimulator implements Runnable {
	private PipedInputStream from_trainCLI;
	private PipedOutputStream to_trainCLI;
	private TrainCLI traincli;
	
	private Random random;
	
	public TrainCLISimulator(TrainsList trains) throws IOException {
		// Connect pipes
		this.from_trainCLI = new PipedInputStream();
		this.to_trainCLI = new PipedOutputStream(from_trainCLI);
		
		PrintStream nullPrintStream = new PrintStream(new FileOutputStream("/dev/null"));
		
		// Use System.out instead of nullPrintStream to print to the console
		this.traincli = new TrainCLI(trains, this.from_trainCLI, nullPrintStream);
		
		this.random = new Random();
		
	}

	@Override
	public void run() {
		Thread trainCLIThread = new Thread(this.traincli);
		trainCLIThread.start();
		
		while (true) {
			int randomFunc = random.nextInt(2);
			int randomSleep = random.nextInt(901) + 100;
			
			try {
				Thread.sleep(randomSleep);
				
				switch (randomFunc) {
				case 0:
					insert();
					break;
				
				case 1:
					delete();
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}
	}
	
	private void seek() throws IOException {
		write("s");
		write("l");
		write("g");
		write("10");
		write("back");
		write("10");
		write("back");
	}
	
	private void insert() throws IOException {
		write("a");
		write("m");
		write("320");
		write("140");
		write("true");
	}
	
	private void delete() throws IOException {
		write("r");
	}
	
	private void write(String s) throws IOException {
		this.to_trainCLI.write(s.concat("\n").getBytes());
	}
	
	private void exit() throws IOException {
		write("exit");
	}
	
}
