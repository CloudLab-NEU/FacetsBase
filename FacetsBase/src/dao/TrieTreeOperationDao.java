package dao;

import java.util.List;

public interface TrieTreeOperationDao {
	/**
	 * 构建Tire树
	 * @return 构建成功返回1，反之为0
	 */
	public int BuildTree();
	/**
	 * 遍历Tire树，查找给定单词编码
	 * @param word
	 * @return 查找到返回编码，反之为空""
	 */
	public String SearchOne(String word);
	/**
	 * 遍历Tire树，查找给定单词集合编码
	 * @param words
	 * @return 全部查找到返回单词集合编码，部分单词查找到的返回单词编码，未查找到的返回为空
	 */
	public boolean Exist(String word);
	public List<String> SearchList(List<String> words);
}
