package dao;

import java.util.List;

import util.NormalRecord;

public interface SQLiteDiskOperationDao {
	/**
	 * 连接数据库
	 */
	public void getConnection();
	/**
	 * 创建给定格式表
	 * @return
	 */
	public int createTable();
	
	public int createTable(int tableid);
	/**
	 * 向表中插入数据
	 * @param author1
	 * @param author2
	 * @param author3
	 * @param author4
	 * @param title1
	 * @param title2
	 * @param title3
	 * @param publisher
	 * @param year
	 * @param type
	 * @param filename
	 * @return
	 */
	public int insert(int id,String author, String title, String publisher, String year, String type, String booktitle,
			String crossref, String pages, String volume, String number, String ee, String url, String editor);
	public int insert(int id,String author, String title, String publisher, String year, String type, String booktitle,
			String crossref, String pages, String volume, String number, String ee, String url, String editor, String filename, String addr);
	public List<NormalRecord> searchAll(String author, String title, String publisher, String year, String type,
			String booktitle, String crossref, String pages,String volume, String number, String ee, String url, String editor);
	public List<NormalRecord> search(String author, String title, String publisher, String year, String type);
	public void disConnection();
	public int update(int id, String filename,String ADD);
}
