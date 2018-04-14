package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
	private List<String> authors;
	private String title;
	private String year;
	private String publisher;
	private String type;
	private List<Map<String, String>> others;
	
	private List<String> authorsKeyWords;
	private List<String> titleKeyWords;
	private String yearKeyWords;
	private List<String> publisherKeyWords;
	private String typeKeyWords;
	private List<Map<String, String>> othersKeyWords;
	
	public XMLElement(String data) {
		this.authors = new ArrayList<String>();
		this.title = "";
		this.year = "";
		this.publisher = "";
		this.type = "";
		this.others = new ArrayList<Map<String, String>>();
		
		this.authorsKeyWords = new ArrayList<String>();
		this.titleKeyWords = new ArrayList<String>();
		this.yearKeyWords = "";
		this.publisherKeyWords = new ArrayList<String>();
		this.typeKeyWords = "";
		this.othersKeyWords = new ArrayList<Map<String, String>>();
		
		Parse(data);
	}
	
	/**
	 * 解析字符串中的数据并保存
	 * @param data
	 */
	public void Parse(String data) {
		String[] datas = data.split("\r\n");
		for(int i=0; i<datas.length; i++) {
			String[] keyWord_value = datas[i].split("\\|");
			if(keyWord_value.length == 2) {
				if(keyWord_value[0].equals("author")) {
					String[] _authors = keyWord_value[1].split(",");
					for(int j=0; j<_authors.length; j++) {
						this.authors.add(_authors[j]);
					}
					setAuthorKeyWords();
				}
				else if(keyWord_value[0].equals("title")) {
					this.title = keyWord_value[1];
					setTitleKeyWords();
				}
				else if(keyWord_value[0].equals("year")) {
					this.year = keyWord_value[1];
					setYearKeyWords();
				}
				else if(keyWord_value[0].equals("school")) {
					this.publisher = keyWord_value[1];
					setPublisherKeyWords();
				}
				else if(keyWord_value[0].equals("type")) {
					this.type = keyWord_value[1];
					setTypeKeyWords();
				}
				else {
					Map<String, String> _others = new HashMap<String, String>();
					_others.put(keyWord_value[0], keyWord_value[1]);
					this.others.add(_others);
				}
				
			}
			else {
				System.out.println("Error in xml_String format");
			}
		}
		setOthersKeyWords();
	}
	
	public void setAuthorKeyWords() {
		List<String> keywords = new ArrayList<String>();
		
		for (String author : this.authors) {
			String[] strs = author.split(",");
			for(int i = 0; i < strs.length; i++) {
				String[] _str = strs[i].split(" ");
				for(int j=0; j<_str.length; j++) {
					keywords.add(_str[j]);
				}
			}
		}
		
		this.authorsKeyWords = keywords;
	}
	
	public List<String> getAuthorKeyWords() {
		return this.authorsKeyWords;
	}
	
	public void setTitleKeyWords() {
		List<String> keywords = new ArrayList<String>();
		
		String[] strs = this.title.split(" ");
		for(int i = 0; i < strs.length; i++) {
			keywords.add(strs[i]);
		}
		this.titleKeyWords = keywords;
	}
	
	public List<String> getTitleKeyWords() {
		return this.titleKeyWords;
	}
	
	
	public void setYearKeyWords() {
		this.yearKeyWords = this.year;
	}
	
	public String getYearKeyWords() {
		return this.yearKeyWords;
	}
	
	public void setPublisherKeyWords() {
		List<String> keywords = new ArrayList<String>();
		
		String[] strs = this.publisher.split(" ");
		for(int i = 0; i < strs.length; i++) {
			keywords.add(strs[i]);
		}	
		
		this.publisherKeyWords = keywords;
	}
	
	public List<String> getPublisherKeyWords() {
		return this.publisherKeyWords;
	}
	
	public void setTypeKeyWords() {
		this.typeKeyWords = this.type;
	}
	
	public String getTypeKeyWords() {
		return this.typeKeyWords;
	}
	
	public void setOthersKeyWords(){
		this.othersKeyWords = others;
	}
	
	public List<Map<String, String>> getOthersKeyWords(){
		return this.othersKeyWords;
	}
}
