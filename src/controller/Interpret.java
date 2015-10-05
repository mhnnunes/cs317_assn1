package controller;

import java.io.IOException;

import model.*;
import connection.ServerConnect;
import controller.DictControl;
// TODO: consider arg2 and arg3
// TODO: use assert instead of a bunch of throw exceptions

public class Interpret {
	
	// the command
	private String cmd;

	// args
	private String arg1;

	// number of strings; won't be 0
	private int num;

	// the dictionary we choose
	private String dictionary;

	public Interpret(){
		this.cmd = "";
		this.arg1 = "";
		this.num = 0;
		this.dictionary = "*";
	}
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	
	public void setCommand(Command cmd){
		this.cmd = cmd.getArg0();
		this.arg1 = cmd.getArg1();
		this.num = cmd.getNumArgs();
	}
	public void interpret(Interpret iNT, ServerConnect dictServer, String dictionary, ServerResponse sR,  boolean debugOn) throws IOException {
		DictControl controller = new DictControl();
		if (dictServer.isConnected()) {
			// call dict
			// require: connection
			if (cmd.toLowerCase().equals("dict")) {
				if (iNT.getNum() > 1) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if(debugOn) System.out.println("--> show db");
					dictServer.sendCommand("show db");
					sR = controller.getsResponse(dictServer, debugOn);
					if(sR.isSuccess()){
						System.out.println(sR.getText());
					}
				}
			}else if (cmd.toLowerCase().equals("set")) { 		//call set
				if (iNT.getNum() != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					// TODO: check if the dictionary is valid
					this.dictionary = iNT.getArg1();}
			}else if (cmd.toLowerCase().equals("define")) { // call define
				if (iNT.getNum() != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (iNT.getArg1().matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> define " + dictionary + " " + iNT.getArg1());
						dictServer.sendCommand("define " + dictionary + " " + iNT.getArg1());
						sR = controller.getsResponse(dictServer, debugOn);
						if(sR.isSuccess()){
							System.out.println(sR.getText());
						}
					}else System.err.println("902 Invalid argument.");}
			}else if (iNT.getCmd().toLowerCase().equals("match")) { // call match
				if (iNT.getNum() != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (iNT.getArg1().matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> match * exact " + iNT.getArg1());
						dictServer.sendCommand("match * exact " + iNT.getArg1());
						sR = controller.getsResponse(dictServer, debugOn);
						if(sR.isSuccess()){
							System.out.println(sR.getText());
						}
					}else System.err.println("902 Invalid argument.");}
			}else if (iNT.getCmd().toLowerCase().equals("prefixmatch")) { // call prefixmatch
				if (iNT.getNum() != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (iNT.getArg1().matches("[a-zA-Z_]+$")) {
						if(debugOn) System.out.println("--> match * prefix " + iNT.getArg1());
						dictServer.sendCommand("match * prefix " + iNT.getArg1());
						sR = controller.getsResponse(dictServer, debugOn);
						if(sR.isSuccess()){
							System.out.println(sR.getText());
						}
					}else System.err.println("902 Invalid argument.");}
			}else if (iNT.getCmd().toLowerCase().equals("close")) { // call close
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
