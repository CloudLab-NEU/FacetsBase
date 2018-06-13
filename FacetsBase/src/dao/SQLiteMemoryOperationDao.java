package dao;

import java.util.List;

import util.FacetRecord;

public interface SQLiteMemoryOperationDao {
	/**
	 * �������ݿ�
	 */
	public void getConnection();
	/**
	 * ����������ʽ��
	 * @return
	 */
	public int createTable();
	
	public int createTable(int tableid);
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
	
	/**
	 * ���ڴ����ݿ��Ӧ���������ص��ڴ����ݿ���			
	 * @param diskDb ϣ�����ص��ڴ��е�Ӳ��������·�����ļ��� ���磺test.db
	 * @return
	 */
	public int loadData(String diskDB);
	
	public int loadData(String diskDB, int len);
	
	public int insert(int id, String author1,String author2,String author3,String author4,String title1,String title2,String title3,String publisher,String year,String type);
	
	/**
	 * ��ѯƥ�����
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
	public List<FacetRecord> searchAll(String author1,String author2,String author3,String author4,String title1,
				String title2,String title3,String publisher,String year,String type);
	
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
	//public List<FacetRecord> search(String author1,String author2,String author3,String author4,String title1,String title2,String title3,String publisher,String year,String type, String connect1, String connect2, String connect3, String connect4, String connect5, String connect6, String connect7, String connect8);
	 public List<FacetRecord> search (String author1, String author2, String author3, String author4, String title1,
				String title2, String title3, String publisher, String year, String type,String ADD);
	/**
	 * ����filename
	 * @param id
	 * @param filename
	 * @return
	 */
	public int update(int id,String filename, String ADD);
	/**
	 * �Ͽ����ݿ�����
	 */
	public int updateAuthor1(int id, String author1);
	public int updateAuthor2(int id, String author2);
	public int updateAuthor3(int id, String author3);
	public int updateAuthor4(int id, String author4);
	public int updateTitle1(int id, String title1);
	public int updateTitle2(int id, String title2);
	public int updateTitle3(int id, String title3);
	public int updatePublisher(int id, String publisher);
	public int updateOther(int id, String type, String year);
	public void disConnection();
}