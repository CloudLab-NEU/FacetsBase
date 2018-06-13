package util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileFormat {
	private Integer id = 0;
	private String author = "";
	private String title = "";
	private String publisher = "";
	private String year = "";
	private String type = "";
	private String bookTitle = "";
	private String crossref = "";
	private String pages = "";
	private String volume = "";
	private String number = "";
	private String ee = "";
	private String url = "";
	private String editor = "";
	private String fileName = "";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
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
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileFormat(Integer _id, String _author, String _title, 
			String _publisher, String _year, String _type, String _bookTitle, String _crossref, String _pages, String _volume, String _number, String _ee, String _url, String _editor, String _fileName) {
		this.id = _id;
		this.author = _author;
		this.title = _title;
		this.publisher = _publisher;
		this.year = _year;
		this.type = _type;
		this.bookTitle = _bookTitle;
		this.crossref = _crossref;
		this.pages = _pages;
		this.volume = _volume;
		this.number = _number;
		this.ee = _ee;
		this.url = _url;
		this.editor = _editor;
		this.fileName = _fileName;
	}
	
	public String getFacetFilePath() {
		//去除空格
		String _str = getFileName().trim();
		String _temp[] = _str.split("//");
		String _file = _temp[_temp.length - 1];	
		String _fileName = _file.split("\\.")[0];
		String _fileFormat = _file.split("\\.")[1];
		//组合成新的文件名
		_file = _fileName + "_facet." + _fileFormat;
		String _facetFilePath = "";

		for(int i = 0; i < _temp.length - 1; i++) {
			_facetFilePath += _temp[i] + "//";
		}
		_facetFilePath += _file;
		
		return _facetFilePath; 
	}
	
	public String getTrivalFilePath() {
		//去除空格
		String _str = getFileName().trim();
		String _temp[] = _str.split("//");
		String _file = _temp[_temp.length - 1];
		String _fileName = _file.split("\\.")[0];
		String _fileFormat = _file.split("\\.")[1];
		//组合成新的文件名
		_file = _fileName + "_trival." + _fileFormat;
		String _trivalFilePath = "";

		for(int i = 0; i < _temp.length - 1; i++) {
			_trivalFilePath += _temp[i] + "//";
		}
		_trivalFilePath += _file;
		
		return _trivalFilePath;  
	}
	
	public String facetToString() {
		return id.toString() + "|"
				+ author + "|"
				+ title + "|"
				+ publisher + "|"
				+ year + "|"
				+ type + "|";
	}
	
	public String trivalToString() {
		String info ="";
		if (!bookTitle.equals("")){
			info+= "| bookTitle:"+bookTitle;
		}
		if (!crossref.equals("")){
			info+= "| crossref:"+crossref;
		}
		if (!pages.equals("")){
			info+= "| pages:"+pages;
		}
		if (!volume.equals("")){
			info+= "| volume:"+volume;
		}
		if (!number.equals("")){
			info+= "| number:"+number;
		}
		if (!ee.equals("")){
			info+= "| ee:"+ee;
		}
		if (!editor.equals("")){
			info+= "| editor:"+editor;
		}
		return id.toString() +info;
	}
}
