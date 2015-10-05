package controller;

import model.ServerResponse;
import java.io.*;
import connection.ServerConnect;

public class DictControl {
	
	
	
	public ServerResponse getsResponse(ServerConnect dictServer, boolean debugOn) throws IOException{
		String fromServer;
		String[] aux;
		ServerResponse serverResponse = new ServerResponse();
		fromServer = dictServer.getLine();
		if(fromServer != null && !fromServer.isEmpty()){
			if(fromServer.charAt(0) == '5'){
				if(fromServer.charAt(0) == '5') { //No matches found case
					serverResponse.setSuccess(false);
					if(debugOn) serverResponse.setDefinition("<-- " + fromServer);
					return serverResponse;
				}
			}else if(fromServer.charAt(0) == '1'){
				if(fromServer.charAt(0) == '5') { //# of definitions case
					serverResponse.setSuccess(true);
					if(debugOn) serverResponse.appendText("<-- " + fromServer);
				}else if(fromServer.charAt(0) == '1') { //# of databases case
					serverResponse.setSuccess(true);
					if(debugOn) serverResponse.appendText("<-- " + fromServer);
				}
			}else if(fromServer.charAt(0) == '4'){
				if(fromServer.charAt(0) == '2') { //Server unavailable
					serverResponse.setSuccess(false);
					if(debugOn) serverResponse.setDefinition("<-- " + fromServer);
					return serverResponse;
				}
			}
		}
		while(true){
			fromServer = dictServer.getLine();
			if(fromServer != null && !fromServer.isEmpty()){
            	if(fromServer.charAt(0) == '2'){
            		if(fromServer.charAt(1) == '5') //End of Response
            			if(debugOn){
            				serverResponse.setSuccess(true);
            				serverResponse.appendText("<-- " + fromServer);
            				break;
            			}else{
            				serverResponse.setSuccess(true);
            				break;
            			}
            	}else if(fromServer.charAt(0) == '1'){
            		if(fromServer.charAt(0) == '5'){ //Treats the case where the name of the dictionaries are displayed like "@ etc etc"
            			aux = fromServer.split("\\s+");
            			if(debugOn) serverResponse.appendText("<-- @ " + aux[0] + " " + aux[2]);
            			else serverResponse.appendText("@ " + aux[0] + " " + aux[2]);
            		}
            	}else serverResponse.appendText(fromServer); 
			}else serverResponse.appendText(fromServer);
		}
		return serverResponse;
	}
}
