package view;

import java.lang.System;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import connection.ServerConnect;
public class Main {

	static final int MAX_LEN = 255;
	static final int PERMITTED_ARGUMENT_COUNT = 1;
	static Boolean debugOn = false;
	
	public static void main(String [] args){
		
		String[] parameters;
		String command = null;
//		byte cmdString[] = new byte[MAX_LEN];
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		int len = 0;
		ServerConnect dictServer;
		
		//Check the number of arguments passed to the program
		if (args.length == PERMITTED_ARGUMENT_COUNT) {
			debugOn = args[0].equals("-d");
			if (debugOn) {
				System.out.println("Debugging output enabled");
			} else {
				System.out.println("997 Invalid command line option - Only -d is allowed");
				return;
			} 
		} else if (args.length > PERMITTED_ARGUMENT_COUNT) {
			System.out.println("996 Too many command line options - Only -d is allowed");
			return;
		}
		
		//Reads input from the user
		try {
			while(true){
				System.out.print("csdict> ");
				command = stdIn.readLine();
				len = command.length();
				parameters = command.split("\\s+");
				JOptionPane.showMessageDialog(null, "Len: " + len + "  cmdString: " + command);
				JOptionPane.showMessageDialog(null, "parameter 1" + parameters[0] + "parameter 2" + parameters[1] + "parameter 3" + parameters[2]);
				if (len <= 0) break;
				//TODO processing / translating the commands will be done by ellie
				// Start processing the command here.
				System.out.println("900 Invalid command.");
			}
		} catch (IOException exception) {
			System.err.println("998 Input error while reading commands, terminating.");
		}catch(Exception e){
			System.err.println("999 Processing error.");
			e.printStackTrace();
		}
	}

}
