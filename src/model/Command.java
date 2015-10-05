package model;

public class Command {
	private String arg0;
	private String arg1;
	private String arg2;
	private String arg3;
	
	// count number of arguments that the user typed in 
	private int num = 0;
	
	private String str;
	
	public Command(String string) {
		this.str = string;
		initCommand(this.str);
	}
	
	public void initCommand(String str) {
		if(str.isEmpty()) System.out.println("String is emptyyyyy");
		String[] args = str.split("\\s+");
		System.out.println("Passed this point, lenght:  " + args.length );
		for(String s : args)
			System.out.println(s);
		if (args.length == 0) {
			System.err.println("900 Invalid command. empty line");		//Empty line case? 
		}else if(args.length < 0){
			System.err.println("901 Incorrect number of arguments.");
		}
		if (args.length >=1) {
			this.arg0 = args[0];
			this.num = 1;
		}
		if (args.length >=2) {
			this.arg1 = args[1];
			this.num = 2;
		}
		if (args.length >= 3) {
			this.arg2 = args[2];
			this.num = 3;
		}
		if (args.length == 4) {
			this.arg3 = args[3];
			this.num = 4;
		}
		
	}
	
	public int getNumArgs() {
		return this.num;
	}
	public String getArg0() {
		return this.arg0;
	}
	public String getArg1() {
		return this.arg1;
	}
	public String getArg2() {
		return this.arg2;
	}
	public String getArg3() {
		return this.arg3;
	}
	
}
