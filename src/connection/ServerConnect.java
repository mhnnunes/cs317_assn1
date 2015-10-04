package connection;

import java.io.*;
import java.net.*;

public class ServerConnect{
	
	private Socket dictSocket;
	private PrintWriter out;
	private BufferedReader in;
	//TODO Java doesn't support default values to method parameters, make it yourself
	public ServerConnect(String hostname, int port){
		try{
			this.dictSocket = new Socket(hostname, 2628);
            this.out = new PrintWriter(dictSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(dictSocket.getInputStream()));
		}catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostname);
            System.exit(1);
        }
	}
	
	public void sendCommand(String command){
		this.out.println(command);
	}
	
	public String getLine() throws IOException{
		return this.in.readLine();
	}
	
	public boolean isConnected(){
		return this.dictSocket.isConnected();
	}
	
//	public static void main(String[] args){
//		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//		String fromUser = null;
//		DictControl controller = new DictControl();
//		WordDef WD;
//        System.out.println("Type the server you want to connect to:");
//        //Reads the first command
//        try{
//        	fromUser = stdIn.readLine();
//        }catch(IOException e){
//        	System.err.println("Couldn't get I/O for the connection to " + fromUser);
//        	System.exit(1);
//        }
//        //Connects to the server
//        try (
//            Socket kkSocket = new Socket(fromUser, 2628);
//            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(
//                new InputStreamReader(kkSocket.getInputStream()));
//        ) {
//            
//            String fromServer;
//            //prints the connect response from the server
//            fromServer = in.readLine();
//            if(fromServer.substring(0,3).equalsIgnoreCase("220")) System.out.println("Server: " + fromServer);
//            
//            System.out.println("type a command to that server");
//            fromUser = stdIn.readLine();
//            
//            out.println(fromUser);
//            
//            fromServer = in.readLine();
//            //Checks if there are definitions of that word
//            if(fromServer.substring(0, 3).equalsIgnoreCase("150")) System.out.println("Response from server was:  " + fromServer.substring(0, 3)+
//            		"\n"+ "Start Reading");
//            else System.out.println(fromServer);
////            ServerConnect dictServer;
//			//Reads the word
////            WD = controller.getsDefinition(dictServer, "substring");
//            System.out.println("Got out of the while loop");
////            System.out.println("Word:" + WD.getDefinition());
//            
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host " + fromUser);
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to " +
//                fromUser);
//            System.exit(1);
//        }
//	}
}