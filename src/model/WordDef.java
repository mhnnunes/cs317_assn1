package model;

public class WordDef {
	private String Word;
	private String Definition;
	
	public WordDef(){
		this.Word = "";
		this.Definition = "";
	}
	public void setWord(String w){
		this.Word = w;
	}
	
	public void setDefinition(String def){
		this.Definition = def;
	}
	
	public String getWord(){
		return this.Word;
	}
	
	public String getDefinition(){
		return this.Definition;
	}
	
	public void appendDefinition(String apd){
		this.Definition = this.Definition + "\n" + apd;
	}

}
