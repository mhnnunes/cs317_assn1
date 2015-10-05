package view;

import java.lang.System;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.*;
import controller.*;
import connection.ServerConnect;
public class Main {

	static final int MAX_LEN = 255;
	static final int PERMITTED_ARGUMENT_COUNT = 1;
	static Boolean debugOn = false;
	
	public static void main(String [] args){
		
		String command = null;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		ServerConnect dictServer = new ServerConnect();
		ServerResponse sR = new ServerResponse();
//		DictControl controller = new DictControl();
		Interpret interpreter = new Interpret();
		Command cmd;
		
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
				cmd = new Command(command);
				//TODO Treat empty line cases and # line cases
				if(cmd.getNumArgs() >= 1){
					if(cmd.getArg0().equalsIgnoreCase("open")){ //Is it an "open" command?
						System.out.println("Got in the open command case");
						if(dictServer.isConnected()){ //Is a connection already established?
							System.out.println("900 Invalid command. connection already opened");
						}else{
							System.out.println("Opening connection, Hostname : " + cmd.getArg1());
							if(cmd.getNumArgs() == 3) {
								dictServer = new ServerConnect(cmd.getArg1(),Integer.parseInt(cmd.getArg2())); //User provides hostname & port
								interpreter = new Interpret();
							}
							else if (cmd.getNumArgs() == 2) {
								dictServer = new ServerConnect(cmd.getArg1()); //User provides only hostname
								interpreter = new Interpret();
							}
						}
					}else{
						interpreter.setCommand(cmd);
						interpreter.interpret(interpreter, dictServer, interpreter.getDictionary(), sR, debugOn);
						System.out.println("Got here after interpreting.");
					}
				}
			}
		} catch (IOException exception) {
			System.err.println("998 Input error while reading commands, terminating.");
		}catch(Exception e){
			System.err.println("999 Processing error.");
			e.printStackTrace();
		}
	}

}
