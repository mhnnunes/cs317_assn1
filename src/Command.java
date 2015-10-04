
public class Command {
	String arg0;
	String arg1;
	String arg2;
	String arg3;
	
	// count number of arguments that the user typed in 
	int num = 0;
	
	String str;
	
	public Command(String str) {
		this.str = str;
		initCommand(str);
	}
	
	public void initCommand(String str) {
		String[] args = str.split("\\s+");
		if (args.length == 0) {
			System.err.println("900 Invalid command.");		}
		if (args.length >=1) {
			arg0 = args[0];
			num = 1;
		}
		if (args.length >=2) {
			arg1 = args[1];
			num = 2;
		}
		if (args.length >= 3) {
			arg2 = args[2];
			num = 3;
		}
		if (args.length == 4) {
			arg3 = args[3];
			num = 4;
		}
		else {
			System.err.println("901 Incorrect number of arguments.");}
	}
	
	public int getNumArgs() {
		return num;
	}
	public String getArg0() {
		return arg0;
	}
	public String getArg1() {
		return arg1;
	}
	public String getArg2() {
		return arg2;
	}
	public String getArg3() {
		return arg3;
	}
	
}
