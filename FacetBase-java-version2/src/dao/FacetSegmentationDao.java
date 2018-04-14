package dao;

public interface FacetSegmentationDao {
	
	/**
	 * ���ݴ���ĵ���wordƥ��õ������ڵ�ǰfacet�ĵڼ��Σ���ǰ��Ϊ����:����ĸa-m, n-z, �����ַ���
	 * @param word
	 * @return
	 * ƥ�䵽��һ�η���01�� �ڶ��η���10�� ������11��δƥ�䵽����00
	 */
	public String FacetSegment(String word);
	
	/**
	 * ���ݴ����8��ά��facet(author1, author2, author3, author4, title1, title2, title3, publisher)
	 * ������ǰ����������cube���߼���ַ
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
