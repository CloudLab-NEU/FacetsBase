package dao;

import java.util.List;
import java.util.Map;

public interface FileFormatControlDao {
	public void AddToFile(Integer _id, String _author, String _title, 
			String _publisher, String _year, String _type, String _bookTitle, String _crossref, String _pages, String _volume, String _number, String _ee, String _url, String _editor, String _fileName);
}
