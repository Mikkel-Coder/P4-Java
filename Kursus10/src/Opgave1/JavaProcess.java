/*
 * THIS FILE IS NOT A PART OF THE ASSIGENMENT
 * 
 * This JavaProcess class is used to compile and run a java process
 */

package Opgave1;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class JavaProcess extends Process {
	/* Setup java paths */

	// Path to the install location of java
	private String javaHome = System.getProperty("java.home");

	// Path to the java compiler
	private String javacPath = new File(javaHome, "bin" + File.separator + "javac").getPath();

	// Path to he java runtime
	private String javaPath = new File(javaHome, "bin" + File.separator + "java").getPath();

	/* Setup project paths */

	// Path to the current working directory for the java project
	private String currentDir = System.getProperty("user.dir");

	// Path to the binary folder where .class files are
	private String binPath = new File(currentDir, "bin").getPath();

	// Path to source code folder where the project is
	private String srcPath = new File(currentDir, "src").getPath();

	/* Setup process context */
	// This is only used as a wrapper so that this class can be a Process and not a
	// ProcessBuilder
	private Process process;

	/*
	 * Configure and starts a new Java process
	 * 
	 * @param packageName The name of the currently working directories packages
	 * name such as `Opgave1`
	 * 
	 * @param fileName The name of a java file to compile and run WITHOUT the file
	 * extension such as `Client`
	 * 
	 * @return A new and running Java Process
	 * 
	 * @throws Exception if compilation fails
	 */
	public JavaProcess(String packageName, String fileName) throws Exception {
		// Path to file to compile "Opgave1", "Client" for example
		String processFilePath = new File(
				srcPath + File.separator + packageName + File.separator + fileName.concat(".java")).getPath();

		// Compile the java file and put it in the bin folder
		ProcessBuilder compileProcessBuilder = new ProcessBuilder(javacPath, processFilePath, "-d", binPath);
		compileProcessBuilder.directory(new File(currentDir));
		compileProcessBuilder.redirectErrorStream(true);
		Process compileProcess = compileProcessBuilder.start();

		// Wait for the compilation to finish
		int exitCode = compileProcess.waitFor();

		// Check if the compilation failed
		if (exitCode != 0) {
			throw new Exception("Compilation failt with status: " + exitCode);
		}

		// Run the compiled java file
		ProcessBuilder executableCompiledProcessBuilder = new ProcessBuilder(javaPath, "-cp", binPath,
				packageName + "." + fileName);
		executableCompiledProcessBuilder.directory(new File(currentDir));
		executableCompiledProcessBuilder.redirectErrorStream(true);

		// Returns a Process type to represent the now running java process
		this.process = executableCompiledProcessBuilder.start();

	}

	/* Everything after this line is only used for default process behaviour */

	@Override
	public OutputStream getOutputStream() {
		return this.process.getOutputStream();
	}

	@Override
	public InputStream getInputStream() {
		return this.process.getInputStream();
	}

	@Override
	public InputStream getErrorStream() {
		return this.process.getErrorStream();
	}

	@Override
	public int waitFor() throws InterruptedException {
		return this.process.waitFor();
	}

	@Override
	public int exitValue() {
		return this.process.exitValue();
	}

	@Override
	public void destroy() {
		this.process.destroy();
	}
}
