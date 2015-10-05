package controller;

import model.Command;
import connection.ServerConnect;
// TODO: consider arg2 and arg3
// TODO: use assert instead of a bunch of throw exceptions

public class Interpret {
	Command command;
	
	// the command
	private static String cmd;
	// args
	private static String arg1;

	private static String arg2;

	private static String arg3;

	// number of strings; won't be 0
	private static int num;

	// the dictionary we choose
	private static String dictionary;

	public Interpret(Command command, ServerConnect dictServer, boolean debugOn) {
		this.command = command;
		cmd = command.getArg0();
		arg1 = command.getArg1();
		arg2 = command.getArg2();
		arg3 = command.getArg3();
		num = command.getNumArgs();
		dictionary = "*";

		interpret(cmd, dictServer, dictionary, debugOn);
	}

	public static void interpret(String cmd, ServerConnect dictServer, String dictionary, boolean debugOn) {

		if (dictServer.isConnected()) {
			// call dict
			// require: connection
			if (cmd.toLowerCase().equals("dict")) {
				if (num > 1) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if(debugOn) System.out.println("--> show db");
					dictServer.sendCommand("show db");}
			}else if (cmd.toLowerCase().equals("set")) { 		//call set
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					// TODO: check if the dictionary is valid
					dictionary = arg1;}
			}else if (cmd.toLowerCase().equals("define")) { // call define
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> define " + dictionary + " " + arg1);
						dictServer.sendCommand("define " + dictionary + " " + arg1);
					}else System.err.println("902 Invalid argument.");}
			}else if (cmd.toLowerCase().equals("match")) { // call match
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> match * exact " + arg1);
						dictServer.sendCommand("match * exact " + arg1);
					}else System.err.println("902 Invalid argument.");}
			}else if (cmd.toLowerCase().equals("prefixmatch")) { // call prefixmatch
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> match * prefix " + arg1);
						dictServer.sendCommand("match * prefix " + arg1);
					}else System.err.println("902 Invalid argument.");}
			}else if (cmd.toLowerCase().equals("close")) { // call close
				// close the connection but remain in the program
				if(debugOn) System.out.println("--> QUIT");
				dictServer.sendCommand("QUIT");
				System.out.println("quit the dictionary.");
			}else if (cmd.toLowerCase().equals("quit")) { // call quit
				if(debugOn) System.out.println("--> QUIT");
				dictServer.sendCommand("QUIT");
				System.out.println("See you next time!");
				System.exit(0);
			}else {
				System.err.println("900 Invalid command.");
			}
		}
		else System.err.print("903 Supplied command not expected at this time.");
	}

}
