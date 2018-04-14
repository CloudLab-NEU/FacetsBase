package impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import dao.FileControlDao;
import util.FormatFile;

public class FileControl implements FileControlDao {

	@Override
	public void AddToFile(Integer _id, String _author1, String _author2, String _author3, String _author4,
			String _title1, String _title2, String _title3, String _publisher, String _year, String _type,
			Map<String, String> _trival, String _fileName) {
		// TODO Auto-generated method stub
		
		FormatFile formatFile = new FormatFile(_id, _author1, _author2, _author3, _author4, _title1, _title2, _title3, _publisher, _year, _type, _trival, _fileName);
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
			System.out.println("Write data to " + facetFile.getPath() + " and " + trivalFile.getPath());
			
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
