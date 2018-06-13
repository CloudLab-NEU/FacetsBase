package util;

public class NormalRecord {
	
	private int id;
	private String author;
	private String title;
	private String publisher;
	private String year;
	private String type;
	private String booktitle;
	private String crossref;
	private String pages;
	private String volume;
	private String number;
	private String ee;
	private String url;
	private String editor;
	private String filediskname;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getCrossref() {
		return crossref;
	}
	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEe() {
		return ee;
	}
	public void setEe(String ee) {
		this.ee = ee;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getFilediskname() {
		return filediskname;
	}
	public void setFilediskname(String filediskname) {
		this.filediskname = filediskname;
	}
	
	public NormalRecord(int id,String author,String title,String publisher,
			String year,String type,String booktitle,String crossref,String pages,
			String volume,String number,String ee,String url,String editor,String filediskname) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.publisher = publisher;
		this.year = year;
		this.type = type;
		this.booktitle = booktitle;
		this.crossref = crossref;
		this.pages = pages;
		this.volume = volume;
		this.number = number;
		this.ee = ee;
		this.volume = url;
		this.type = editor;
		this.filediskname = filediskname;
	}
	
	public String toString() {
		
		return "AUTHOR:" + this.author
				+ ", TITLE:" + this.title
				+ ", PUBLISHER:" + this.publisher
				+ ", YEAR:" + this.year
				+ ", TYPE:" + this.type
				+ ", BOOKTITLE:" + this.booktitle
				+ ", CROSSREF:" + this.crossref
				+ ", PAGES:" + this.pages
				+ ", VOLUME:" + this.volume
				+ ", NNUMBER:" + this.number
				+ ", EE:" + this.ee
				+ ", URL:" + this.url
				+ ", EDITOR:" + this.editor
				+ ", FILEDISKNAME:" + this.filediskname;
	}

}
