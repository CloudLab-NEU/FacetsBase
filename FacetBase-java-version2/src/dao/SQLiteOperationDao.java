package dao;

import java.util.List;

import util.FacetRecord;

public interface SQLiteOperationDao {
	/**
	 * 连接数据库
	 */
	public void getConnection();
	/**
	 * 创建给定格式表
	 * @return
	 */
	public int createTable();
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
	public int insert(String author1,String author2,String author3,String author4,String title1,String title2,String title3,String publisher,String year,String type,String filename);
	/**
	 * 查找匹配记录
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
	 * @return
	 */
	public List<FacetRecord> search(String author1,String author2,String author3,String author4,String title1,String title2,String title3,String publisher,String year,String type);
	/**
	 * 更新filename
	 * @param id
	 * @param filename
	 * @return
	 */
	public int update(int id,String filename);
	/**
	 * 断开数据库连接
	 */
	public void disConnection();
}