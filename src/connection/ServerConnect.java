package connection;

import java.io.*;
import java.net.*;

public class ServerConnect{
	
	private Socket dictSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ServerConnect(String hostname, int port){
		String aux;
		try{
			System.out.println("In the ServerConnect constructor. Hostname: " + hostname + "Port: " + String.valueOf(port));
			this.dictSocket = new Socket(hostname, port);
            this.out = new PrintWriter(dictSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(dictSocket.getInputStream()));
            aux = in.readLine();
            System.out.println("AUX é: " + aux);
		}catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("925 Control connection I/O error, closing control connection." + hostname);
            System.exit(1);
        }
	}
	
	public ServerConnect(){
		return;
	}
	
	public ServerConnect(String hostname){
		this(hostname, 2628);
	}
	
	public void sendCommand(String command){
		this.out.println(command);
	}
	
	public String getLine() throws IOException{
		return this.in.readLine();
	}
	
	public boolean isConnected(){
		return (this.dictSocket != null) && (this.dictSocket.isConnected());
	}
	
	
}