package util;

import impl.BTOpeartion;
import impl.FileFormatControl;
import impl.SQLiteDiskOperation;
import impl.SQLiteMemoryOperation;
import impl.TrieTreeOpeartion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import test.WriteAndSearch;


/*public class SAXParserHandler extends DefaultHandler {
	static FileFormatControl fileControl = null;
	static LabelXMLElement labelXML;
	static XMLElement xmlElement;
	static TrieTreeOpeartion treeOpeartion = new TrieTreeOpeartion();
	static BTOpeartion btOpeartion = new BTOpeartion();
	static SQLiteOperation sqLiteOperation = new SQLiteOperation();
	static SQLiteDiskOperation sqLiteDiskOperation = new SQLiteDiskOperation();
	static int line = 0;//计normalfileName的行数
	static 	int id = 1 ;
	static 	int facetId = 1;
	String NodeName = "";
    String TextContent = "";
    String info = "";
 
	boolean isEnd = false;
	public static Set<String> types = new HashSet<String>();
	static {
		types.add("article");
		types.add("inproceedings");
		types.add("proceedings");
		types.add("book");
		types.add("incollection");
		types.add("phdthesis");
		types.add("mastersthesis");
		types.add("www");
		treeOpeartion.BuildTree();
		btOpeartion.buildBTree();
		//连接内存数据库并插入数据(需要转换成编码)
		sqLiteOperation.getConnection();
		sqLiteOperation.createTable();	
		//磁盘数据库
		sqLiteDiskOperation.getConnection();
		sqLiteDiskOperation.createTable();
		
	}
	
	//用来遍历xml的开始标签
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		System.out.println("start");
		if(types.contains(qName)) {
		   NodeName = "type";
		   TextContent = qName; 
		   info += NodeName + "|" + TextContent + "\r\n"; 	

		}
		else if (!types.contains(qName) && !"dblp".equalsIgnoreCase(qName)){
			NodeName = qName;
			
		}
	}
	
	//用来遍历xml的结束标签
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(types.contains(qName)){
			writeToFile(info);
		}
		
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		 String str = new String(ch, start, length);  
	        if(!"".equals(str.trim())) {  
	        	TextContent = str; 
	        	if(NodeName == "author" || NodeName == "title" || NodeName == "school"){
	        	TextContent = TextContent.replaceAll("[^0-9a-zA-Z]"," ");
	        	}
	        	info += NodeName + "|" + TextContent + "\r\n";
	        }  
	}
	
	//用来标识解析开始

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("XML解析开始。。。");		
	}
	
	//用来标识解析结束

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();

		System.out.println("XML解析结束！");
		String author1;
		String author2;
		String author3;
		String author4;
		String title1;
		String title2;
		String title3;
		String publisher;
		String year;
		String type;
		System.out.println("请输入查询关键字");
		Scanner sc = new Scanner(System.in);
		System.out.print("AUTHOR1:");
		author1 = sc.nextLine();
		System.out.print("AUTHOR2:");
		author2 = sc.nextLine();
		System.out.print("AUTHOR3:");
		author3 = sc.nextLine();
		System.out.print("AUTHOR4:");
		author4 = sc.nextLine();
		System.out.print("TITLE1:");
		title1 = sc.nextLine();
		System.out.print("TITLE2:");
		title2 = sc.nextLine();
		System.out.print("TITLE3:");
		title3 = sc.nextLine();
		System.out.print("PUBLISHER:");
		publisher = sc.nextLine();
		System.out.print("YEAR:");
		year = sc.nextLine();
		System.out.print("TYPE:");
		type = sc.nextLine();
		List<FacetRecord> list = null;
		List<NormalRecord> listNormal = null;
		if (treeOpeartion.searchExist(author1)&&treeOpeartion.searchExist(author2)&&treeOpeartion.searchExist(author3)
				&&treeOpeartion.searchExist(author4)&&treeOpeartion.searchExist(title1)&&treeOpeartion.searchExist(title2)
				&&treeOpeartion.searchExist(title3)&&treeOpeartion.searchExist(publisher)&&treeOpeartion.searchExist(year)
				&&treeOpeartion.searchExist(type)){
			String inx_ADD = "";
			 inx_ADD = treeOpeartion.SearchOne(author1).substring(treeOpeartion.SearchOne(author1).length()-3, treeOpeartion.SearchOne(author1).length())
					+ treeOpeartion.SearchOne(author2).substring(treeOpeartion.SearchOne(author2).length()-3, treeOpeartion.SearchOne(author2).length())+
					treeOpeartion.SearchOne(author3).substring(treeOpeartion.SearchOne(author3).length()-3, treeOpeartion.SearchOne(author3).length())+
					treeOpeartion.SearchOne(author4).substring(treeOpeartion.SearchOne(author4).length()-3, treeOpeartion.SearchOne(author4).length())+
					treeOpeartion.SearchOne(title1).substring(treeOpeartion.SearchOne(title1).length()-3, treeOpeartion.SearchOne(title1).length())+
					treeOpeartion.SearchOne(title2).substring(treeOpeartion.SearchOne(title2).length()-3, treeOpeartion.SearchOne(title2).length())+
					treeOpeartion.SearchOne(title3).substring(treeOpeartion.SearchOne(title3).length()-3, treeOpeartion.SearchOne(title3).length())+
					treeOpeartion.SearchOne(publisher).substring(treeOpeartion.SearchOne(publisher).length()-3, treeOpeartion.SearchOne(publisher).length())+
					treeOpeartion.SearchOne(year).substring(treeOpeartion.SearchOne(year).length()-3, treeOpeartion.SearchOne(year).length())+
					treeOpeartion.SearchOne(type).substring(treeOpeartion.SearchOne(type).length()-3, treeOpeartion.SearchOne(type).length());
//			list = sqLiteOperation.search(textValue1, textValue2, textValue3, textValue4, textValue5, textValue6, textValue7, textValue8, textValue9, selectValue18,selectValue2,
//					selectValue4,selectValue6,selectValue8,selectValue10,selectValue12,selectValue14,selectValue15);
			 list = sqLiteOperation.search(author1,author2,author3,author4,title1,title2,title3,publisher,year,type,inx_ADD);
		
			for (FacetRecord facetRecord : list) {
				System.out.println(facetRecord.toString());
			}
		}
		else{
			String inx_ADDDisk = "";
			inx_ADDDisk = btOpeartion.searchBT(author1)+ btOpeartion.searchBT(author2)+ btOpeartion.searchBT(author3)
					+ btOpeartion.searchBT(author4)+ btOpeartion.searchBT(title1)+btOpeartion.searchBT(title2)+
					btOpeartion.searchBT(title3)+btOpeartion.searchBT(publisher)+btOpeartion.searchBT(year)+btOpeartion.searchBT(type);
			listNormal = sqLiteDiskOperation.search(author1,author2,author3,author4,title1,title2,title3,publisher,year,type,inx_ADDDisk);
			for (NormalRecord normalRecord : listNormal) {
				System.out.println(normalRecord.toString());
			}
		}
	}
	

	public void writeToFile(String info){
		String _temp = info;
		String fileNameMemory = "";
		String ADD="";
		String ADDDisk = "";
		String fileNameDisk = "";
		xmlElement = new XMLElement(_temp);
		labelXML = new LabelXMLElement(_temp);
		List<String> authorKeyWords = xmlElement.getAuthorKeyWords();
		int m=0;
		List<String> titleKeyWords = xmlElement.getTitleKeyWords();
		List<String> publisherKeyWords = xmlElement.getPublisherKeyWords();
		sqLiteOperation.insert(facetId, null, null, null, null, null, null, null, null, null, null);
		sqLiteDiskOperation.insert(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		int k=0;
		
		for(int i=0;i<authorKeyWords.size();i++) {//i应为数组长度
			if(authorKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(authorKeyWords.get(i))) {
						k++;
						fileNameMemory = fileNameMemory+treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(0, 2)+"_";
						if(k==1){
							sqLiteOperation.updateAuthor1(id,treeOpeartion.SearchOne(authorKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==2){
							sqLiteOperation.updateAuthor2(id,treeOpeartion.SearchOne(authorKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==3){
							sqLiteOperation.updateAuthor3(id,treeOpeartion.SearchOne(authorKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==4){
							sqLiteOperation.updateAuthor4(id,treeOpeartion.SearchOne(authorKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else
							break;
					}	
				}
		}
		if (k ==0 ){
			ADD += "000000000000";
		}
		else if (k==1){
			 ADD += "000000000";
		}
		else if (k==2){
			ADD += "000000";
		}
		else if (k==3){
			ADD += "000";
		}
		else {
		}

		
		for(int i=0;i<titleKeyWords.size();i++) {//i应为数组长度
			if(titleKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(titleKeyWords.get(i))) {	
						m++;
						//System.out.println(titleKeyWords.get(i));
						fileNameMemory = fileNameMemory+treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(0, 2)+"_";
						if(m==1){
							sqLiteOperation.updateTitle1(id,treeOpeartion.SearchOne(titleKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						else if(m==2){
							sqLiteOperation.updateTitle2(id,treeOpeartion.SearchOne(titleKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						else if(m==3){
							sqLiteOperation.updateTitle3(id,treeOpeartion.SearchOne(titleKeyWords.get(i)));
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						
						else
							break;
					}	
				}
		}
		if (m ==0 ){
			ADD += "000000000";
		}
		else if (m==1){
			 ADD += "000000";
		}
		else if (m==2){
			ADD += "000";
		}
		else {
		}
		
	
		int p = 0;
		for(int i=0;i<publisherKeyWords.size();i++) {
			if(publisherKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(publisherKeyWords.get(i))) {
						p++;
						fileNameMemory = fileNameMemory+treeOpeartion.SearchOne(publisherKeyWords.get(i)).substring(0, 2)+"_";
						sqLiteOperation.updatePublisher(id,treeOpeartion.SearchOne(publisherKeyWords.get(i)));
						ADD += treeOpeartion.SearchOne(publisherKeyWords.get(i)).substring(treeOpeartion.SearchOne(publisherKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(publisherKeyWords.get(i)).length());
					}						
				}
			else
				break;
		}
		if (p == 0){
			ADD += "000";
		}
//		fileNameMemory1 = fileNameMemory + treeOpeartion.SearchOne(xmlElement.getYearKeyWords())+
//				treeOpeartion.SearchOne(xmlElement.getTypeKeyWords());
//	System.out.println("fileNameMemory1"+fileNameMemory);
		if (fileNameMemory!=""){
			ADD += treeOpeartion.SearchOne(labelXML.getYear()).substring(treeOpeartion.SearchOne(labelXML.getYear()).length()-3,treeOpeartion.SearchOne(labelXML.getYear()).length())
					+treeOpeartion.SearchOne(labelXML.getType()).substring(treeOpeartion.SearchOne(labelXML.getType()).length()-3,treeOpeartion.SearchOne(labelXML.getType()).length());
			String diskadd = "D://test2//"+ fileNameMemory+treeOpeartion.SearchOne(labelXML.getType())+"_"+ treeOpeartion.SearchOne(labelXML.getYear())+ ".txt";
			fileControl = new FileFormatControl();
				fileControl.AddToFile(id,
						labelXML.getAuthor(),
						labelXML.getTitle(),
						labelXML.getPublisher(),
						labelXML.getYear(),
						labelXML.getType(),
						labelXML.getBooktitle(),
						labelXML.getCrossref(),
						labelXML.getPages(),
						labelXML.getVolume(),
						labelXML.getnumber(),
						labelXML.getEe(),
						labelXML.getUrl(),
						labelXML.getEditor(),
						diskadd);

				sqLiteDiskOperation.insert(id,labelXML.getAuthor(),
						labelXML.getTitle(),
						labelXML.getPublisher(),
						labelXML.getYear(),
						labelXML.getType(),
						labelXML.getBooktitle(),
						labelXML.getCrossref(),
						labelXML.getPages(),
						labelXML.getVolume(),
						labelXML.getnumber(),
						labelXML.getEe(),
						labelXML.getUrl(),
						labelXML.getEditor());
				sqLiteOperation.updateOther(facetId, treeOpeartion.SearchOne(labelXML.getType()), treeOpeartion.SearchOne(labelXML.getYear()));
				sqLiteOperation.update(facetId,diskadd,ADD);
				sqLiteDiskOperation.update(id, diskadd,ADD);
				facetId++;
				id++;
		}
		else{
			int whetherWrite = 0;
			int diskauthor = 0;
			for(int i=0;i<authorKeyWords.size();i++) {//i应为数组长度
				if(authorKeyWords.get(i) != null) {			
					if(btOpeartion.isExistBT(authorKeyWords.get(i))){
						diskauthor++;
						fileNameDisk = fileNameDisk+btOpeartion.searchBT(authorKeyWords.get(i))+"_";
						whetherWrite++;
						ADDDisk+=btOpeartion.searchBT(authorKeyWords.get(i));
					}
				}
			}
			if (diskauthor ==0 ){
				ADDDisk += "0000000000000000";
			}
			else if (diskauthor==1){
				ADDDisk += "000000000000";
			}
			else if (diskauthor==2){
				ADDDisk += "00000000";
			}
			else if (diskauthor==3){
				ADDDisk += "0000";
			}
			else {
			}
			
			int disktitle = 0;
			for(int i=0;i<titleKeyWords.size();i++) {//i应为数组长度
				if(titleKeyWords.get(i) != null) {
						if(btOpeartion.isExistBT(titleKeyWords.get(i))) {	
							disktitle++;
							//System.out.println(titleKeyWords.get(i));
							fileNameDisk = fileNameDisk+btOpeartion.searchBT(titleKeyWords.get(i))+"_";
							whetherWrite++;
							ADDDisk+=btOpeartion.searchBT(titleKeyWords.get(i));
						}	
					}
			}
			if (disktitle ==0 ){
				ADDDisk += "000000000000";
			}
			else if (disktitle==1){
				ADDDisk += "00000000";
			}
			else if (disktitle==2){
				ADDDisk += "0000";
			}
			else {
			}
			int diskpublisher = 0;
			for(int i=0;i<publisherKeyWords.size();i++) {
				if(publisherKeyWords.get(i) != null) {
						if(btOpeartion.isExistBT(publisherKeyWords.get(i))) {
							diskpublisher++;
							fileNameDisk = fileNameDisk+btOpeartion.searchBT(titleKeyWords.get(i))+"_";
							ADDDisk += treeOpeartion.SearchOne(publisherKeyWords.get(i)).substring(treeOpeartion.SearchOne(publisherKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(publisherKeyWords.get(i)).length());
						}						
					}
				else
					break;
			}
			if (diskpublisher == 0){
				ADDDisk += "fff";
			}
			if(whetherWrite!=0){
				sqLiteDiskOperation.insert(id,labelXML.getAuthor(),
					labelXML.getTitle(),
					labelXML.getPublisher(),
					labelXML.getYear(),
					labelXML.getType(),
					labelXML.getBooktitle(),
					labelXML.getCrossref(),
					labelXML.getPages(),
					labelXML.getVolume(),
					labelXML.getnumber(),
					labelXML.getEe(),
					labelXML.getUrl(),
					labelXML.getEditor());
				line++;
				int fileAmount = 1;
			
				if(line%1000==0) {
					fileAmount++;
				
			}
				String diskadd1 = "D://test3//"+ fileNameDisk +btOpeartion.searchBT(labelXML.getType())+btOpeartion.searchBT(labelXML.getYear())+ fileAmount + ".txt";
			
				fileControl = new FileFormatControl();
				fileControl.AddToFile(id,
						labelXML.getAuthor(),
				labelXML.getTitle(),
				labelXML.getPublisher(),
				labelXML.getYear(),
				labelXML.getType(),
				labelXML.getBooktitle(),
				labelXML.getCrossref(),
				labelXML.getPages(),
				labelXML.getVolume(),
				labelXML.getnumber(),
				labelXML.getEe(),
				labelXML.getUrl(),
				labelXML.getEditor(),
				diskadd1);
				ADDDisk += treeOpeartion.SearchOne(labelXML.getYear())+treeOpeartion.SearchOne(labelXML.getType());
				sqLiteDiskOperation.update(id,diskadd1,ADDDisk);
				id++;
			}
		}
	}
		
}*/
public class SAXParserHandler extends DefaultHandler {
	static FileFormatControl fileControl = null;
	static LabelXMLElement labelXML;
	static XMLElement xmlElement;
	static TrieTreeOpeartion treeOpeartion = new TrieTreeOpeartion();
	static BTOpeartion btOpeartion = new BTOpeartion();
	static SQLiteMemoryOperation sqLiteMemoryOperation = new SQLiteMemoryOperation();
	static SQLiteDiskOperation sqLiteDiskOperation = new SQLiteDiskOperation();
	static int line = 0;//计normalfileName的行数
	static 	int id = 0 ;
	static 	int facetId = 0;
	
	static int startid = 0;
	
	static int tableid = 0;
	static String memory = "";
	static String disk = "";
	
	String NodeName = "";
    String TextContent = "";
    String info = "";
    

	boolean isEnd = false;
	public static Set<String> types = new HashSet<String>();
	static {
		types.add("article");
		types.add("inproceedings");
		types.add("proceedings");
		types.add("book");
		types.add("incollection");
		types.add("phdthesis");
		types.add("mastersthesis");
		types.add("www");
		treeOpeartion.BuildTree();
		btOpeartion.buildBTree();
//		//连接内存数据库并插入数据(需要转换成编码)
//		sqLiteOperation.getConnection(memory);
////		sqLiteOperation.getConnection();
//		sqLiteOperation.createTable();	
//		//磁盘数据库
//		sqLiteDiskOperation.getConnection(disk);
////		sqLiteDiskOperation.getConnection();
//		sqLiteDiskOperation.createTable();
	}
	
	public SAXParserHandler(int id, String memory, String disk) {
		setId(id);
		setMemory(memory);
		setDisk(disk);
		setTableId(tableid);
		
		//连接内存数据库并插入数据(需要转换成编码)
		sqLiteMemoryOperation.getConnection(memory);
//		sqLiteOperation.getConnection();
		sqLiteMemoryOperation.createTable(tableid);	
		//磁盘数据库
		sqLiteDiskOperation.getConnection(disk);
//		sqLiteDiskOperation.getConnection();
		sqLiteDiskOperation.createTable(tableid);
	}
	
	public SAXParserHandler(int id, String memory, String disk, int tableid, int startid) {
		setId(id);
		setMemory(memory);
		setDisk(disk);
		setTableId(tableid);
		setStartId(startid);
		
		//连接内存数据库并插入数据(需要转换成编码)
		sqLiteMemoryOperation.getConnection(memory);
//		sqLiteOperation.getConnection();
		sqLiteMemoryOperation.createTable(tableid);	
		//磁盘数据库
		sqLiteDiskOperation.getConnection(disk);
//		sqLiteDiskOperation.getConnection();
		sqLiteDiskOperation.createTable(tableid);
	}
	
	public void setId(int id) {
		this.id = id;
		this.facetId = id;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	public void setTableId(int tableid) {
		this.tableid = tableid;
	}
	public void setStartId(int startid) {
		this.startid = startid;
	}
	
	//用来遍历xml的开始标签
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		//String info = "";
		if(types.contains(qName)) {
			NodeName = "type";
			TextContent = qName;
			info += NodeName + "|" + TextContent + "\r\n";

		}
		else if (!types.contains(qName) && !"dblp".equalsIgnoreCase(qName)){
			NodeName = qName;
			
		}
	}
	
	//用来遍历xml的结束标签
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(types.contains(qName)){
			if(this.id>=this.startid) {
				writeToFile(info);
				info = "";
//				System.out.println(this.id);
//				this.id++;
			}
			else {
				System.out.println(this.id);
				this.id++;
				this.facetId++;
				return;
			}
		}
		
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		 String str = new String(ch, start, length);  
	        if(!"".equals(str.trim())) {  
	        	TextContent = str;
	        	if (NodeName.equals("author") ||NodeName.equals("title")||NodeName.equals("school")){
	        	TextContent = TextContent.replaceAll("[^0-9a-zA-Z]"," ");}
	        	info += NodeName + "|" + TextContent + "\r\n";
	        }  
	}
	
	//用来标识解析开始

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("XML解析开始。。。");	
		
	}
	
	//用来标识解析结束

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("XML解析完成。。。");
//		String author1;
//		String author2;
//		String author3;
//		String author4;
//		String title1;
//		String title2;
//		String title3;
//		String publisher;
//		String year;
//		String type;
//		System.out.println("请输入查询关键字");
//		Scanner sc = new Scanner(System.in);
//		System.out.print("AUTHOR1:");
//		author1 = sc.nextLine();
//		System.out.print("AUTHOR2:");
//		author2 = sc.nextLine();
//		System.out.print("AUTHOR3:");
//		author3 = sc.nextLine();
//		System.out.print("AUTHOR4:");
//		author4 = sc.nextLine();
//		System.out.print("TITLE1:");
//		title1 = sc.nextLine();
//		System.out.print("TITLE2:");
//		title2 = sc.nextLine();
//		System.out.print("TITLE3:");
//		title3 = sc.nextLine();
//		System.out.print("PUBLISHER:");
//		publisher = sc.nextLine();
//		System.out.print("YEAR:");
//		year = sc.nextLine();
//		System.out.print("TYPE:");
//		type = sc.nextLine();
//		List<FacetRecord> list = null;
//		List<NormalRecord> listNormal = null;
//		if (treeOpeartion.searchExist(author1)&&treeOpeartion.searchExist(author2)&&treeOpeartion.searchExist(author3)
//				&&treeOpeartion.searchExist(author4)&&treeOpeartion.searchExist(title1)&&treeOpeartion.searchExist(title2)
//				&&treeOpeartion.searchExist(title3)&&treeOpeartion.searchExist(publisher)&&treeOpeartion.searchExist(year)
//				&&treeOpeartion.searchExist(type)){
//			String inx_ADD = "";
//			 inx_ADD = treeOpeartion.SearchOne(author1).substring(treeOpeartion.SearchOne(author1).length()-3, treeOpeartion.SearchOne(author1).length())
//					+ treeOpeartion.SearchOne(author2).substring(treeOpeartion.SearchOne(author2).length()-3, treeOpeartion.SearchOne(author2).length())+
//					treeOpeartion.SearchOne(author3).substring(treeOpeartion.SearchOne(author3).length()-3, treeOpeartion.SearchOne(author3).length())+
//					treeOpeartion.SearchOne(author4).substring(treeOpeartion.SearchOne(author4).length()-3, treeOpeartion.SearchOne(author4).length())+
//					treeOpeartion.SearchOne(title1).substring(treeOpeartion.SearchOne(title1).length()-3, treeOpeartion.SearchOne(title1).length())+
//					treeOpeartion.SearchOne(title2).substring(treeOpeartion.SearchOne(title2).length()-3, treeOpeartion.SearchOne(title2).length())+
//					treeOpeartion.SearchOne(title3).substring(treeOpeartion.SearchOne(title3).length()-3, treeOpeartion.SearchOne(title3).length())+
//					treeOpeartion.SearchOne(publisher).substring(treeOpeartion.SearchOne(publisher).length()-3, treeOpeartion.SearchOne(publisher).length())+
//					treeOpeartion.SearchOne(year).substring(treeOpeartion.SearchOne(year).length()-3, treeOpeartion.SearchOne(year).length())+
//					treeOpeartion.SearchOne(type).substring(treeOpeartion.SearchOne(type).length()-3, treeOpeartion.SearchOne(type).length());
////			list = sqLiteOperation.search(textValue1, textValue2, textValue3, textValue4, textValue5, textValue6, textValue7, textValue8, textValue9, selectValue18,selectValue2,
////					selectValue4,selectValue6,selectValue8,selectValue10,selectValue12,selectValue14,selectValue15);
////			 System.out.println(inx_ADD);
//			 list = sqLiteOperation.search(author1,author2,author3,author4,title1,title2,title3,publisher,year,type,inx_ADD);
//		
//			for (FacetRecord facetRecord : list) {
//				System.out.println(facetRecord.toString());
//			}
//		}
//		else{
//			String inx_ADDDisk = "";
//			inx_ADDDisk = btOpeartion.searchBT(author1)+ btOpeartion.searchBT(author2)+ btOpeartion.searchBT(author3)
//					+ btOpeartion.searchBT(author4)+ btOpeartion.searchBT(title1)+btOpeartion.searchBT(title2)+
//					btOpeartion.searchBT(title3)+btOpeartion.searchBT(publisher)+btOpeartion.searchBT(year)+btOpeartion.searchBT(type);
//			listNormal = sqLiteDiskOperation.search(author1,author2,author3,author4,title1,title2,title3,publisher,year,type,inx_ADDDisk);
//			for (NormalRecord normalRecord : listNormal) {
//				System.out.println(normalRecord.toString());
//			}
//		}
	}
	
	public void writeToFile(String info){
		String _temp = info;
		String fileNameMemory = "";
		String ADD="";
		String ADDDisk = "";
		String fileNameDisk = "";
		xmlElement = new XMLElement(_temp);
		labelXML = new LabelXMLElement(_temp);
		List<String> authorKeyWords = xmlElement.getAuthorKeyWords();
		int m=0;
		List<String> titleKeyWords = xmlElement.getTitleKeyWords();
		List<String> publisherKeyWords = xmlElement.getPublisherKeyWords();
//		sqLiteOperation.insert(facetId, null, null, null, null, null, null, null, null, null, null);
//		sqLiteDiskOperation.insert(id, null, null, null, null, null, null, null, null, null, null, null, null, null);
		int k=0;
		
		String _author1 = "";
		String _author2 = "";
		String _author3 = "";
		String _author4 = "";
		String _title1 = "";
		String _title2 = "";
		String _title3 = "";
		String _publisher = "";
		String _year = "";
		String _type = "";
		String _filename = "";
		String _addr = "";
		
		String __author = "";
		String __title = "";
		String __publisher = "";
		String __year = "";
		String __type = "";
		String __booktitle = "";
		String __crossref = "";
		String __pages = "";
		String __volume = "";
		String __number = "";
		String __ee = "";
		String __url = "";
		String __editor = "";
		String __filename = "";
		String __addr = "";
		
		for(int i=0;i<authorKeyWords.size();i++) {//i应为数组长度
			if(authorKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(authorKeyWords.get(i))) {
						k++;
						
						if(k==1){
							fileNameMemory = fileNameMemory+treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(0, 2)+"_";
//							sqLiteOperation.updateAuthor1(id,authorKeyWords.get(i));
							_author1 = authorKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==2){
//							sqLiteOperation.updateAuthor2(id,authorKeyWords.get(i));
							_author2 = authorKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==3){
//							sqLiteOperation.updateAuthor3(id,authorKeyWords.get(i));
							_author3 = authorKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else if(k==4){
//							sqLiteOperation.updateAuthor4(id,authorKeyWords.get(i));
							_author4 = authorKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(authorKeyWords.get(i)).substring(treeOpeartion.SearchOne(authorKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(authorKeyWords.get(i)).length());
						}
						else
							break;
					}	
				}
		}
		if (k ==0 ){
			ADD += "000000000000";
		}
		else if (k==1){
			 ADD += "000000000";
		}
		else if (k==2){
			ADD += "000000";
		}
		else if (k==3){
			ADD += "000";
		}
		else {
		}

		
		for(int i=0;i<titleKeyWords.size();i++) {//i应为数组长度
			if(titleKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(titleKeyWords.get(i))) {	
						m++;
						//System.out.println(titleKeyWords.get(i));
						if(m==1){
//							sqLiteOperation.updateTitle1(id,titleKeyWords.get(i));
							_title1 = titleKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						else if(m==2){
//							sqLiteOperation.updateTitle2(id,titleKeyWords.get(i));
							_title2 = titleKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						else if(m==3){
//							sqLiteOperation.updateTitle3(id,titleKeyWords.get(i));
							_title3 = titleKeyWords.get(i);
							ADD += treeOpeartion.SearchOne(titleKeyWords.get(i)).substring(treeOpeartion.SearchOne(titleKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(titleKeyWords.get(i)).length());
						}
						
						else
							break;
					}	
				}
		}
		if (m ==0 ){
			ADD += "000000000";
		}
		else if (m==1){
			 ADD += "000000";
		}
		else if (m==2){
			ADD += "000";
		}
		else {
		}
		
	
		int p = 0;
		for(int i=0;i<publisherKeyWords.size();i++) {
			if(publisherKeyWords.get(i) != null) {
					if(treeOpeartion.Exist(publisherKeyWords.get(i))) {
						p++;
						if(p==1){
							fileNameMemory = fileNameMemory+treeOpeartion.SearchOne(publisherKeyWords.get(i)).substring(0, 2)+"_";
						}
//						sqLiteOperation.updatePublisher(id,publisherKeyWords.get(i));
						_publisher = publisherKeyWords.get(i);
						ADD += treeOpeartion.SearchOne(publisherKeyWords.get(i)).substring(treeOpeartion.SearchOne(publisherKeyWords.get(i)).length()-3, treeOpeartion.SearchOne(publisherKeyWords.get(i)).length());
					}						
				}
			else
				break;
		}
		if (p == 0){
			ADD += "000";
		}
//		fileNameMemory1 = fileNameMemory + treeOpeartion.SearchOne(xmlElement.getYearKeyWords())+
//				treeOpeartion.SearchOne(xmlElement.getTypeKeyWords());
//	System.out.println("fileNameMemory1"+fileNameMemory);
		if (fileNameMemory!=""){
			ADD += treeOpeartion.SearchOne(labelXML.getYear()).substring(treeOpeartion.SearchOne(labelXML.getYear()).length()-3,treeOpeartion.SearchOne(labelXML.getYear()).length())
					+treeOpeartion.SearchOne(labelXML.getType()).substring(treeOpeartion.SearchOne(labelXML.getType()).length()-3,treeOpeartion.SearchOne(labelXML.getType()).length());
			String diskadd = "F://test2//"+ fileNameMemory+treeOpeartion.SearchOne(labelXML.getType())+"_"+ treeOpeartion.SearchOne(labelXML.getYear())+ ".txt";
			fileControl = new FileFormatControl();
				fileControl.AddToFile(id,
						labelXML.getAuthor(),
						labelXML.getTitle(),
						labelXML.getPublisher(),
						labelXML.getYear(),
						labelXML.getType(),
						labelXML.getBooktitle(),
						labelXML.getCrossref(),
						labelXML.getPages(),
						labelXML.getVolume(),
						labelXML.getnumber(),
						labelXML.getEe(),
						labelXML.getUrl(),
						labelXML.getEditor(),
						diskadd);

				__author = labelXML.getAuthor();
				__title = labelXML.getTitle();
				__publisher = labelXML.getPublisher();
				__year = labelXML.getYear();
				__type = labelXML.getType();
				__booktitle = labelXML.getBooktitle();
				__crossref = labelXML.getCrossref();
				__pages = labelXML.getPages();
				__volume = labelXML.getVolume();
				__number = labelXML.getnumber();
				__ee = labelXML.getEe();
				__url = labelXML.getUrl();
				__editor = labelXML.getEditor();
//				sqLiteDiskOperation.insert(id,labelXML.getAuthor(),
//						labelXML.getTitle(),
//						labelXML.getPublisher(),
//						labelXML.getYear(),
//						labelXML.getType(),
//						labelXML.getBooktitle(),
//						labelXML.getCrossref(),
//						labelXML.getPages(),
//						labelXML.getVolume(),
//						labelXML.getnumber(),
//						labelXML.getEe(),
//						labelXML.getUrl(),
//						labelXML.getEditor());
//				sqLiteOperation.updateOther(facetId, labelXML.getType(),labelXML.getYear());
				_year = labelXML.getYear();
				_type = labelXML.getType();
	
//				sqLiteOperation.update(facetId,diskadd,ADD);
				_filename = diskadd;
				_addr = ADD;
				
				sqLiteMemoryOperation.insert(facetId, _author1, _author2, _author3, _author4, _title1, _title2, _title3, _publisher, _year, _type, _filename, _addr);
				
				__filename = diskadd;
				__addr = ADD;
//				sqLiteDiskOperation.update(id, diskadd,ADD);
				sqLiteDiskOperation.insert(id, __author, __title, __publisher, __year, __type, __booktitle, __crossref, __pages, __volume, __number, __ee, __url, __editor, __filename, __addr);
				
				System.out.println("id:" + this.id + ", facetid:" + this.facetId);
				facetId++;
				id++;
		}
		else{
			int whetherWrite = 0;
			int diskauthor = 0;
			for(int i=0;i<authorKeyWords.size();i++) {//i应为数组长度
				if(authorKeyWords.get(i) != null) {			
					if(btOpeartion.isExistBT(authorKeyWords.get(i))){
						diskauthor++;
						if (diskauthor == 1){
							fileNameDisk = fileNameDisk+btOpeartion.searchBT(authorKeyWords.get(i))+"_";
						}
						
						whetherWrite++;
						ADDDisk+=btOpeartion.searchBT(authorKeyWords.get(i));
					}
				}
			}
			if (diskauthor ==0 ){
				ADDDisk += "0000000000000000";
			}
			else if (diskauthor==1){
				ADDDisk += "000000000000";
			}
			else if (diskauthor==2){
				ADDDisk += "00000000";
			}
			else if (diskauthor==3){
				ADDDisk += "0000";
			}
			else {
			}
			
			int disktitle = 0;
			for(int i=0;i<titleKeyWords.size();i++) {//i应为数组长度
				if(titleKeyWords.get(i) != null) {
						if(btOpeartion.isExistBT(titleKeyWords.get(i))) {	
							disktitle++;
							//System.out.println(titleKeyWords.get(i));
							
							whetherWrite++;
							ADDDisk+=btOpeartion.searchBT(titleKeyWords.get(i));
						}	
					}
			}
			if (disktitle ==0 ){
				ADDDisk += "000000000000";
			}
			else if (disktitle==1){
				ADDDisk += "00000000";
			}
			else if (disktitle==2){
				ADDDisk += "0000";
			}
			else {
			}
			int diskpublisher = 0;
			for(int i=0;i<publisherKeyWords.size();i++) {
				if(publisherKeyWords.get(i) != null) {
						if(btOpeartion.isExistBT(publisherKeyWords.get(i))) {
							diskpublisher++;
							if (diskpublisher==1){
								fileNameDisk = fileNameDisk+btOpeartion.searchBT(publisherKeyWords.get(i))+"_";
							}
							ADDDisk += btOpeartion.searchBT(publisherKeyWords.get(i));
						}						
					}
				else
					break;
			}
			if (diskpublisher == 0){
				ADDDisk += "0000";
			}
			if(whetherWrite!=0){
				
				__author = labelXML.getAuthor();
				__title = labelXML.getTitle();
				__publisher = labelXML.getPublisher();
				__year = labelXML.getYear();
				__type = labelXML.getType();
				__booktitle = labelXML.getBooktitle();
				__crossref = labelXML.getCrossref();
				__pages = labelXML.getPages();
				__volume = labelXML.getVolume();
				__number = labelXML.getnumber();
				__ee = labelXML.getEe();
				__url = labelXML.getUrl();
				__editor = labelXML.getEditor();
				
//				sqLiteDiskOperation.insert(id,labelXML.getAuthor(),
//					labelXML.getTitle(),
//					labelXML.getPublisher(),
//					labelXML.getYear(),
//					labelXML.getType(),
//					labelXML.getBooktitle(),
//					labelXML.getCrossref(),
//					labelXML.getPages(),
//					labelXML.getVolume(),
//					labelXML.getnumber(),
//					labelXML.getEe(),
//					labelXML.getUrl(),
//					labelXML.getEditor());
				line++;
				int fileAmount = 1;
			
				if(line%1000==0) {
					fileAmount++;
				
				}
				String diskadd1 = "F://test3//"+ fileNameDisk +btOpeartion.searchBT(labelXML.getType())+"_"+btOpeartion.searchBT(labelXML.getYear())+"_"+ fileAmount + ".txt";
			
				fileControl = new FileFormatControl();
				fileControl.AddToFile(id,
						labelXML.getAuthor(),
				labelXML.getTitle(),
				labelXML.getPublisher(),
				labelXML.getYear(),
				labelXML.getType(),
				labelXML.getBooktitle(),
				labelXML.getCrossref(),
				labelXML.getPages(),
				labelXML.getVolume(),
				labelXML.getnumber(),
				labelXML.getEe(),
				labelXML.getUrl(),
				labelXML.getEditor(),
				diskadd1);
				ADDDisk += btOpeartion.searchBT(labelXML.getYear())+btOpeartion.searchBT(labelXML.getType());
				
				__filename = diskadd1;
				__addr = ADDDisk;
				
//				sqLiteDiskOperation.update(id,diskadd1,ADDDisk);
				sqLiteDiskOperation.insert(id, __author, __title, __publisher, __year, __type, __booktitle, __crossref, __pages, __volume, __number, __ee, __url, __editor, __filename, __addr);
				System.out.println(this.id);
				id++;
			}
		}
	}
	
}

