package com.finalshare.microbf.interfaces.cli;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import com.finalshare.microbf.Interpreter;
import com.finalshare.microbf.Main;

public class Cli {

	public boolean showOtherTextUtil = false, showDebugTable = false;
	private Interpreter interpreter;

	public Cli(String[] args) {

		if (args.length == 0) {
			System.err.println("Bad Argument: more arguments expected\n" + cliComponents.helpText);
			return;
		}

		parseOptions(args);

		if (!showOtherTextUtil) {

			setupInterpreter(args);

			try {
				interpreter.parse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (showDebugTable) interpreter.debugFuckMachine(6);
		
	}

	private void parseOptions(String[] args) {

		for (int currentArg = 0; currentArg < args.length; currentArg++) {
			switch (args[currentArg]) {
				case "-d":
					this.showDebugTable = true;
					break;

				case "-v":
					this.showOtherTextUtil = true;
					System.out.println(cliComponents.versionText);
					break;

				case "-h":
					this.showOtherTextUtil = true;
					System.out.println(cliComponents.helpText);
					break;
			}
		}
	}

	private void setupInterpreter(String args[]) {
		if (args[0].contains(".bf") || args[0].contains(".b")) {

			String src = new String();

			try {
				Scanner scanner = new Scanner(new File(args[0]));

				while (scanner.hasNext()) {
					src += scanner.next();
				}

				interpreter = new Interpreter(src);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			interpreter = new Interpreter(args[0]);
		}
	}

	static class cliComponents {

		private static String version = cliComponents.class.getPackage().getImplementationVersion();

		public static String versionText = "MicroBF: " + getVersion() + " - Micro Brainfuck interpreter\n" +
				"By Finalshare\n" +
				"Licenced under the AGPL3";

		public static String helpText = "Usage: [SOURCE] or [SOURCE PATH.bf or PATH.b] + [OPTIONS]: \n" +
				"-d : Activate debug mode(with a memory and pointer display)\n" +
				"-v : Display version text\n" +
				"-h : Display this screen\n";

		public static String getVersion() {
			ClassLoader cl = Cli.class.getClassLoader();
			String implVersion = "INDEV";

			try {
				URL url = cl.getResource("META-INF/MANIFEST.MF");
				Manifest manifest = new Manifest(url.openStream());
				Attributes mainAttributes = manifest.getMainAttributes();
				implVersion = mainAttributes.getValue("Implementation-Version");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return implVersion;
		}
	}
}
