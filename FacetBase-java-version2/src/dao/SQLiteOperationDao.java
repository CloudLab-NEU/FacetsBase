package dao;

import java.util.List;

import util.FacetRecord;

public interface SQLiteOperationDao {
	/**
	 * �������ݿ�
	 */
	public void getConnection();
	/**
	 * ����������ʽ��
	 * @return
	 */
	public int createTable();
	/**
	 * ����в�������
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
	 * ����ƥ���¼
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
	 * ����filename
	 * @param id
	 * @param filename
	 * @return
	 */
	public int update(int id,String filename);
	/**
	 * �Ͽ����ݿ�����
	 */
	public void disConnection();
}