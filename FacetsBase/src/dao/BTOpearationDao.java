package dao;

public interface BTOpearationDao {
	public void buildBTree();
	public String searchBT(String word);
	public boolean isExistBT(String word);
}
