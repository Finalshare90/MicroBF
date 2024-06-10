package com.finalshare.microbf;

import com.finalshare.microbf.interfaces.cli.Cli;

public class Main {

	static Interpreter interpreter;
	
	// Entrance for Standalone interpreter
	public static void main(String[] args) {
		Cli cli = new Cli(args);
	}
}
