package dao;

public interface FacetSegmentationDao {
	
	/**
	 * 根据传入的单词word匹配该单词属于当前facet的第几段（当前分为三段:首字母a-m, n-z, 其他字符）
	 * @param word
	 * @return
	 * 匹配到第一段返回01， 第二段返回10， 第三段11，未匹配到返回00
	 */
	public String FacetSegment(String word);
	
	/**
	 * 根据传入的8个维的facet(author1, author2, author3, author4, title1, title2, title3, publisher)
	 * 导出当前单词所属的cube的逻辑地址
	 * @param author1
	 * @param author2
	 * @param author3
	 * @param author4
	 * @param title1
	 * @param title2
	 * @param title3
	 * @param publisher
	 * @return
	 */
	public String GetCubeByFacets(String author1, String author2, String author3, String author4,
			String title1, String title2, String title3,
			String publisher);
}
