package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;



public class MyParser {
	
	public static void Read(String filename, String memory, String disk, int id) {
//		File file = new File("F:/eclipse-workspace/FacetBaseFinal-version2/XMLsource/dblp.xml");
		File file = new File(filename);
		// 获取一个SAXParserFactory实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		ArrayList<String> content = new ArrayList<String>();
		//通过factory获取SAXParser实例
		try {
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			//创建SAXParserHandler对象
			SAXParserHandler handler = new SAXParserHandler(id,memory,disk);
//			handler.setId(id);
//			handler.setMemory(memory);
//			handler.setDisk(disk);
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new FileInputStream(file)));
			//parser.parse(args[0], handler);
//			parser.parse(args[0], handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Read(String filename, String memory, String disk, int id, int tableid, int startid) {
//		File file = new File("F:/eclipse-workspace/FacetBaseFinal-version2/XMLsource/dblp.xml");
		File file = new File(filename);
		// 获取一个SAXParserFactory实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		ArrayList<String> content = new ArrayList<String>();
		//通过factory获取SAXParser实例
		try {
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			//创建SAXParserHandler对象
			SAXParserHandler handler = new SAXParserHandler(id,memory,disk,tableid,startid);
//			handler.setId(id);
//			handler.setMemory(memory);
//			handler.setDisk(disk);
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new FileInputStream(file)));
			//parser.parse(args[0], handler);
//			parser.parse(args[0], handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

}

