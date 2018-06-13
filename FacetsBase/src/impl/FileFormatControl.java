package impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


import util.FileFormat;
import dao.FileFormatControlDao;

public class FileFormatControl implements FileFormatControlDao{
	public void AddToFile(Integer _id, String _author, String _title, 
			String _publisher, String _year, String _type, String _bookTitle, String _crossref, String _pages, String _volume, String _number, String _ee, String _url, String _editor, String _fileName){
		FileFormat formatFile = new FileFormat( _id,  _author,  _title, 
				 _publisher,  _year,  _type,  _bookTitle,  _crossref,  _pages, 
				 _volume,  _number,  _ee,  _url,  _editor,  _fileName);
		File facetFile = null;
		File trivalFile = null;
		FileOutputStream fos1 = null;
		FileOutputStream fos2 = null;
		PrintWriter pw1 = null;
		PrintWriter pw2 = null;
		
		try {
			facetFile = new File(formatFile.getFacetFilePath());
			trivalFile = new File(formatFile.getTrivalFilePath());
			if(!facetFile.exists())
				facetFile.createNewFile();
			if(!trivalFile.exists())
				trivalFile.createNewFile();
			fos1 = new FileOutputStream(facetFile, true);
			pw1 = new PrintWriter(fos1);
			fos2 = new FileOutputStream(trivalFile, true);
			pw2 = new PrintWriter(fos2);
			
			pw1.println(formatFile.facetToString());
			pw2.println(formatFile.trivalToString());
		//	System.out.println("Write data to " + facetFile.getPath() + " and " + trivalFile.getPath());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pw1 != null)
					pw1.close();
				if(pw2 != null)
					pw2.close();
				if(fos1 != null)
					fos1.close();
				if(fos2 != null)
					fos2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
