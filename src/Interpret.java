import connection.ServerConnect;
// TODO: consider arg2 and arg3
// TODO: where to implement open, close, quit?
// TODO: use assert instead of a bunch of throw exceptions

public class Interpret {
	Command command;

	// to check the connection
	ServerConnect dictServer;

	// the command
	private static String cmd;
	// args
	private static String arg1;

	//private static String arg2;

	//private static String arg3;

	// number of strings; won't be 0
	private static int num;

	// the dictionary we choose
	private static String dictionary;

	public Interpret(Command command, ServerConnect dictServer) {
		this.command = command;
		cmd = command.getArg0();
		arg1 = command.getArg1();
		//arg2 = command.getArg2();
		//arg3 = command.getArg3();
		num = command.getNumArgs();
		dictionary = "*";

		interpret(cmd, dictServer, dictionary);
	}

	public static void interpret(String cmd, ServerConnect dictServer, String dictionary) {

		if (dictServer.isConnected() == true) {
			// call dict
			// require: connection
			if (cmd.toLowerCase().equals("dict")) {
				if (num > 1) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					dictServer.sendCommand("show db");}}

			//call set
			if (cmd.toLowerCase().equals("set")) {
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					// TODO: check if the dictionary is valid
					dictionary = arg1;}}

			// call define
			if (cmd.toLowerCase().equals("define")) {
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						dictServer.sendCommand("define " + dictionary + " " + arg1);
					}
					System.err.println("902 Invalid argument.");}}

			// call match
			if (cmd.toLowerCase().equals("match")) {
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						dictServer.sendCommand("match * " + arg1);
					}
					System.err.println("902 Invalid argument.");}}

			// call prefixmatch
			if (cmd.toLowerCase().equals("prefixmatch")) {
				if (num != 2) {
					System.err.println("901 Incorrect number of arguments.");
				} else {
					if (arg1.matches("[a-zA-Z_]+$")) {
						dictServer.sendCommand("match * prefix " + arg1);
					}
					System.err.println("902 Invalid argument.");}}

			// call close
			// close the connection but remain in the program
			if (cmd.toLowerCase().equals("close")) {
				dictServer.sendCommand("QUIT");
				System.out.println("quit the dictionary.");}

			// call quit
			if (cmd.toLowerCase().equals("quit")) {
				dictServer.sendCommand("QUIT");
				System.out.println("See you next time!");
				System.exit(0);
			}
			// invalid command
			else {
				System.err.println("900 Invalid command.");}
		}
		System.err.println("903 Supplied command not expected at this time.");
	}

}
