package util;

public class LabelXMLElement {
	private String authors;
	private String title;
	private String year;
	private String publisher;
	private String type;
	private String bookTitle;
	private String crossref;
	private String pages;
	private String volume;
	private String number;
	private String ee;
	private String url;
	private String editor;
	public LabelXMLElement(String data){
		this.authors = "";
		this.title = "";
		this.publisher = "";
		this.year = "";
		this.type = "";
		this.bookTitle = "";
		this.crossref = "";
		this.pages = "";
		this.volume = "";
		this.number = "";
		this.ee = "";
		this.url = "";
		this.editor = "";
		Parse(data);
	}
	public void Parse(String data){
		String[] datas = data.split("\r\n");
		for(int i=0; i<datas.length; i++) {
			String[] afterSplit = datas[i].split("\\|");
			if(afterSplit[0].equals("author")) {
			      for(int j=1; j<afterSplit.length;j++){
			    	  this.authors += afterSplit[j];
			      }
			}
			if(afterSplit[0].equals("title")) {
			    this.title = afterSplit[1];
			}
			if(afterSplit[0].equals("year")) {
			    this.year = afterSplit[1];
			}
			if(afterSplit[0].equals("type")) {
			    this.type = afterSplit[1];
			}
			if(afterSplit[0].equals("publisher")) {
			    this.publisher = afterSplit[1];
			}
			if(afterSplit[0].equals("booktitle")) {
			    this.bookTitle = afterSplit[1];
			}
			if(afterSplit[0].equals("crossref")) {
			    this.crossref = afterSplit[1];
			}
			if(afterSplit[0].equals("volume")) {
			    this.volume = afterSplit[1];
			}
			if(afterSplit[0].equals("pages")) {
			    this.pages = afterSplit[1];
			}
			if(afterSplit[0].equals("number")) {
			    this.number = afterSplit[1];
			}
			if(afterSplit[0].equals("ee")) {
				for(int j=0; j<afterSplit.length;j++){
					
			    	  this.ee += afterSplit[j];
			      }
			}
			if(afterSplit[0].equals("url")) {
			    this.url = afterSplit[1];
			}
			if(afterSplit[0].equals("editor")) {
			      for(int j=0; j<afterSplit.length;j++){
			    	  this.editor += afterSplit[j];
			      }
			}
		}
	}
	public String getAuthor(){
		return this.authors;
	}
	public String getTitle(){
		return this.title;
	}
	public String getYear(){
		return this.year;
	}
	public String getType(){
		return this.type;
	}
	public String getPublisher(){
		return this.publisher;
	}
	public String getBooktitle(){
		return this.bookTitle;
	}
	public String getCrossref(){
		return this.crossref;
	}
	public String getVolume(){
		return this.volume;
	}
	public String getPages(){
		return this.pages;
	}
	public String getnumber(){
		return this.number;
	}
	public String getEe(){
		return this.ee;
	}
	public String getUrl(){
		return this.url;
	}
	public String getEditor(){
		return this.editor;
	}
}
