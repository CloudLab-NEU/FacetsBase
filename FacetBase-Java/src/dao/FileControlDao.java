package dao;

import java.util.Map;

public interface FileControlDao {
	/**
	 * 将数据写入格式化文件
	 * @param _id
	 * @param _author1
	 * @param _author2
	 * @param _author3
	 * @param _author4
	 * @param _title1
	 * @param _title2
	 * @param _title3
	 * @param _publisher
	 * @param _year
	 * @param _type
	 * @param _trival
	 * @param _fileName
	 */
	public void AddToFile(Integer _id, String _author1, String _author2, String _author3, String _author4, String _title1, String _title2, String _title3, 
			String _publisher, String _year, String _type, Map<String, String> _trival, String _fileName);
}
