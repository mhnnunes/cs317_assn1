import java.net.*;
import java.io.*;

public class CSClient {
    public static void main(String[] args) throws IOException {
        
        if ((args.length != 1) && (args.length !=2)) {
            System.err.println("Usage: java CSServer <port number>");
            System.exit(1);
        }
        // get hostName and portNumber
        String hostName = args[0];
        int portNumber = 0;
        // not specified portNumber
        if (args.length == 1) {
        	portNumber = 2628;
        }
        // specified portNumber
        else {
        portNumber = Integer.parseInt(args[1]);
        }
        try (
             Socket csSocket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(csSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                new InputStreamReader(csSocket.getInputStream()));
             ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            //String fromServer = in.readLine();
            //System.out.println("Server: " + fromServer);
            String fromServer; // added
            String fromUser;
            
//            while ((fromUser = stdIn.readLine()) != null) {
//            	System.out.println("User: " + fromUser);
//            	out.println(fromUser);
//            	
//            	while ((fromServer = in.readLine()) != null) {
//                System.out.println("Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;
//            	}
//            }
            // listen
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
                // standard input
                fromUser = stdIn.readLine();
                // send the text to the server 
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                               hostName);
            System.exit(1);
        }
    }
}
