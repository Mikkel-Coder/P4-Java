package Opgave3;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import java.util.Random;

import Opgave1.TrainsList;
import Opgave1.TrainCLI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TrainCLISimulator extends JFrame implements Runnable, ActionListener {
	private static final long serialVersionUID = -4507126832614408409L;
	private PipedInputStream from_trainCLI;
	private PipedOutputStream to_trainCLI;
	public TrainCLI traincli;

	private Random random;

	private JButton randomActionBtn;
	private boolean exit_flag;
	
	public TrainCLISimulator(TrainsList trains) throws IOException {
		// Connect pipes
		this.from_trainCLI = new PipedInputStream();
		this.to_trainCLI = new PipedOutputStream(from_trainCLI);

		// This will not work on Windows!
		PrintStream nullPrintStream = new PrintStream(new FileOutputStream("/dev/null"));

		// Use System.out instead of nullPrintStream to print to the console
		this.traincli = new TrainCLI(trains, this.from_trainCLI, System.out);
		this.random = new Random();
		
		// Setup GUI button
		setSize(200, 100);
        this.randomActionBtn = new JButton("Random Action");
        this.randomActionBtn.addActionListener(this);
        getContentPane().add(randomActionBtn);
        setVisible(true);
        
        // Add event to terminate connection with the server 
        // when the window is closed
        this.exit_flag = false;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	TrainCLISimulator.this.exit_flag = true; 
            }
        });
	}

	@Override
	public void run() {
		Thread trainCLIThread = new Thread(this.traincli);
		trainCLIThread.start();
		
		// Keep alive and only run actions based on GUI button press
		while (this.exit_flag != true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// We can now close the traincli thread by writing "exit" to it
		try {
			this.dispose(); // Terminate GUI thread
			this.exit();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void randomAction() {
		int randomFunc = random.nextInt(2);
		
		try {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == randomActionBtn) {
	        randomAction();
	    }
	}
}
