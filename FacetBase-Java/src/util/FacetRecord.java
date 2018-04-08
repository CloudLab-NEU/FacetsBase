package util;

public class FacetRecord {
	private int id;
	private String author1;
	private String author2;
	private String author3;
	private String author4;
	private String title1;
	private String title2;
	private String title3;
	private String publisher;
	private String year;
	private String type;
	private String filename;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public FacetRecord(int id,String author1,String author2,String author3,String author4,String title1,String title2,String title3,String publisher,String year,String type,String filename) {
		this.id = id;
		this.author1 = author1;
		this.author2 = author2;
		this.author3 = author3;
		this.author4 = author4;
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;
		this.publisher = publisher;
		this.year = year;
		this.type = type;
		this.filename = filename;
	}
	
	public String toString() {
		
		return "AUTHOR1:" + this.author1
				+ ", AUTHOR2:" + this.author2
				+ ", AUTHOR3:" + this.author3
				+ ", AUTHOR4:" + this.author4
				+ ", TITLE1:" + this.title1
				+ ", TITLE2:" + this.title2
				+ ", TITLE3:" + this.title3
				+ ", PUBLISHER:" + this.publisher
				+ ", YEAR:" + this.year
				+ ", TYPE:" + this.type
				+ ", FILENAME:" + this.filename;
	}
}
