package util;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.NamedNodeMap;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;  

public class ReadXML {
	public static ArrayList<String> Triviality(){
		ArrayList<String> triviality = new ArrayList<String>();
        DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();   
        String NodeName ;
        String TextContent ;
        String info = "";
        
        try {             
            DocumentBuilder b = a.newDocumentBuilder();    
            Document document = b.parse("XMLsource/dblp.xml");    
            NodeList booklist = document.getElementsByTagName("phdthesis");    
            for(int i =0; i<booklist.getLength(); i++){   
            	info = "";
                Node book = booklist.item(i);                  
                NodeList childlist = book.getChildNodes();    
                for(int t = 0; t<childlist.getLength(); t++){                          
                    if(childlist.item(t).getNodeType() == Node.ELEMENT_NODE){   
                    	NodeName = childlist.item(t).getNodeName();
                    	TextContent = childlist.item(t).getTextContent();
                    	
                    	info += NodeName + "|" + TextContent + "\r\n";
                    	
                    	
      //                  System.out.print(childlist.item(t).getNodeName()+"|");                       
      //                  System.out.println(childlist.item(t).getTextContent()); 
                    	
                    }    
                }    
                triviality.add(info);
            }    
        } catch (ParserConfigurationException e) {    
            e.printStackTrace();    
        } catch (SAXException e) {    
            e.printStackTrace();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }   

        return triviality;
	}
	
//	public static void main(String[] args) {
//		ArrayList<String> abc = Triviality();
//		Iterator it = abc.iterator();
//		while(it.hasNext())
//		{
//			 System.out.print(it.next());           
//		}
//	}

}
