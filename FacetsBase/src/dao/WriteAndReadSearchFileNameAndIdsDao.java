package dao;

public interface WriteAndReadSearchFileNameAndIdsDao {

	public void write(String filename, String context);
	
	public String read(String filename);
}
