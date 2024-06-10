
package com.finalshare.microbf;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Stack;

public class Interpreter{

    private Stack<Integer> blockStack = new Stack<>();
    public int pointer = 0;
    private char[] mem = new char[1024];
    private char[] src;
    private PrintStream out = System.out;
    private InputStream in = System.in;
	
    public Interpreter(String src){
		this.src = src.toCharArray();
	}

    public Interpreter(String src, int memSize){
		this(src);
		this.mem = new char[memSize];
    }
    
    public void parse() throws IOException{
		for(int ch = 0; ch < src.length; ch++){
			switch(src[ch]){
			case '>':
				pointer++;
				break;
			case '<':
				pointer--;
				break;
				
			case '+':
				mem[pointer]++;
				break;
		
			case '-':
				mem[pointer]--;
				break;
		
			case '.':
				out.print(mem[pointer]);
				break;
		
			case ',':
				mem[pointer] = (char) in.read();
				break;

			case '[':
				if(mem[pointer] == 0){
					ch = findBlockClosing(true, ch);
				}
				break;
			   
			case ']':
				if(mem[pointer] > 0){
					ch = findBlockClosing(false, ch);
				}
				break;
			}
		}
	}

	private int findBlockClosing(boolean isJumpIfZero, int currentChar){

		char tokenToFind = ']';
		// 0, because its fucking impossible to a block be closed in the same char as it started.
		// If you somehow manages to do that, i will suck you up.
		int blockClosingChar = 0;
		
		if(isJumpIfZero){
			tokenToFind = '[';
		}

		int cycle = currentChar;
		while(blockClosingChar == 0 && cycle < this.src.length){
		    
			if(this.src[cycle] == tokenToFind){
				blockClosingChar = cycle;
			}
			
			cycle++;
		}
		
		return blockClosingChar;
	}

    public void setOutputStream(PrintStream out){
		this.out = out;
    }

    public void setInputStream(InputStream in){
		this.in = in;
    }

	public void debugFuckMachine(int row) {
		System.out.print("Memory: ");

		for (int c = 0, rowCount = 0; c < mem.length; c++, rowCount++) {
			if(rowCount == row){
				System.out.println();
				rowCount = 0;
			}
			
			if (c == pointer) {
				System.out.print("["+ c + ": " + mem[c]+ "]" + " ");
			} else {
				System.out.print(c + ": " + mem[c] + " ");
			}
		}
		System.out.println("\n Src size: " + src.length);
	}
}
