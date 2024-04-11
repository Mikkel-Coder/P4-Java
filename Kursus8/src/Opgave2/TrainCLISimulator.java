package Opgave2;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import Opgave1.TrainsList;

public class TrainCLISimulator implements Runnable {
	private PipedInputStream from_trainCLI;
	private PipedOutputStream to_trainCLI;
	private TrainCLI traincli;
	
	public TrainCLISimulator(TrainsList trains) throws IOException {
		// Connect pipes
		this.from_trainCLI = new PipedInputStream();
		this.to_trainCLI = new PipedOutputStream(from_trainCLI);
		
		PrintStream nullPrintStream = new PrintStream(new FileOutputStream("/dev/null"));
		
		this.traincli = new TrainCLI(trains, this.from_trainCLI, nullPrintStream);
		
	}

	@Override
	public void run() {
		Thread trainCLIThread = new Thread(this.traincli);
		trainCLIThread.start();
		
		try {
			seek();
			insert();
			delete();
			exit();
		} catch (Exception e) {
			e.printStackTrace();
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
