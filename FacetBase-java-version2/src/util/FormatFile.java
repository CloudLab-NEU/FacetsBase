package util;

import java.util.Iterator;
import java.util.Map;

public class FormatFile {
	
	private Integer id = 0;
	private String author1 = "";
	private String author2 = "";
	private String author3 = "";
	private String author4 = "";
	private String title1 = "";
	private String title2 = "";
	private String title3 = "";
	private String publisher = "";
	private String year = "";
	private String type = "";
	private Map<String, String> trival = null;
	private String fileName = "";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor1() {
		return author1;
	}
	public void setAuthor1(String author1) {
		this.author1 = author1;
	}
	public String getAuthor2() {
		return author2;
	}
	public void setAuthor2(String author2) {
		this.author2 = author2;
	}
	public String getAuthor3() {
		return author3;
	}
	public void setAuthor3(String author3) {
		this.author3 = author3;
	}
	public String getAuthor4() {
		return author4;
	}
	public void setAuthor4(String author4) {
		this.author4 = author4;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
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
	public Map<String, String> getTrival() {
		return trival;
	}
	public void setTrival(Map<String, String> trival) {
		this.trival = trival;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FormatFile(Integer _id, String _author1, String _author2, String _author3, String _author4, String _title1, String _title2, String _title3, 
			String _publisher, String _year, String _type, Map<String, String> _trival, String _fileName) {
		this.id = _id;
		this.author1 = _author1;
		this.author2 = _author2;
		this.author3 = _author3;
		this.author4 = _author4;
		this.title1 = _title1;
		this.title2 = _title2;
		this.title3 = _title3;
		this.publisher = _publisher;
		this.year = _year;
		this.type = _type;
		this.trival = _trival;
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
				+ author1 + "|"
				+ author2 + "|"
				+ author3 + "|"
				+ author4 + "|"
				+ title1 + "|"
				+ title2 + "|"
				+ title3 + "|"
				+ publisher + "|"
				+ year + "|"
				+ type + "|";
	}
	
	public String trivalToString() {
		Iterator<Map.Entry<String, String>> entries  = trival.entrySet().iterator();
		
		String result = "";
		result += getId().toString() + "|";
		while(entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			result += entry.getKey() + ":" + entry.getValue() + "|";
		}
		result.substring(0, result.length() - 1);
		
		return result;
	}
}
