package controller;

import model.WordDef;
import java.io.*;
import connection.ServerConnect;

public class DictControl {
	
	public WordDef getsDefinition(ServerConnect dictServer, String Word) throws IOException{
		String fromServer;
		WordDef WD = new WordDef();
		fromServer = dictServer.getLine();
		while(true){
			WD.appendDefinition(fromServer);
			fromServer = dictServer.getLine();
			if(fromServer != null && !fromServer.isEmpty())
            	if(fromServer.charAt(0) == '2')
            		if(fromServer.charAt(1) == '5') break;
		}
		return WD;
	}
	
//	public boolean
}
