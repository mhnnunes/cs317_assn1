package model;

public class ServerResponse {
	private boolean success;
	private String rText;
	
	public ServerResponse(){
		this.success = false;
		this.rText = "";
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setDefinition(String def){
		this.rText = def;
	}
	
	public String getText(){
		return this.rText;
	}
	
	public void appendText(String apd){
		this.rText = this.rText + "\n" + apd;
	}

}
