package dao;

import java.util.List;

public interface TrieTreeOperationDao {
	/**
	 * ����Tire��
	 * @return �����ɹ�����1����֮Ϊ0
	 */
	public int BuildTree();
	/**
	 * ����Tire�������Ҹ������ʱ���
	 * @param word
	 * @return ���ҵ����ر��룬��֮Ϊ��""
	 */
	public String SearchOne(String word);
	/**
	 * ����Tire�������Ҹ������ʼ��ϱ���
	 * @param words
	 * @return ȫ�����ҵ����ص��ʼ��ϱ��룬���ֵ��ʲ��ҵ��ķ��ص��ʱ��룬δ���ҵ��ķ���Ϊ��
	 */
	public List<String> SearchList(List<String> words);
}
