package impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.FacetRecord;
import util.NormalRecord;
import dao.SQLiteDiskOperationDao;
import dao.SQLiteMemoryOperationDao;
import db.DiskDBConnector;

public class SQLiteDiskOperation implements SQLiteDiskOperationDao{
	private Connection conn = null;
	private Statement stmt = null;
	
	private int tableid = 0;

	public void getConnection() {
		// TODO Auto-generated method stub
		
		this.conn = DiskDBConnector.getConn();

	}
	
	public void getConnection(String disk) {
		// TODO Auto-generated method stub
//		DiskDBConnector diskdbconnector = new DiskDBConnector();
//		this.conn = diskdbconnector.getConn(disk);
		this.conn = DiskDBConnector.getConn(disk);
	}
	
	
	public int createTable() {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			stmt = this.conn.createStatement();
			String sql = "CREATE TABLE DISK_TABLE" +
					"(ID INTEGER PRIMARY KEY NOT NULL," +
					"AUTHOR VARCHAR(255) ," +      
					"TITLE VARCHAR(255)," +
					"PUBLISHER VARCHAR(255)," +
					"YEAR TEXT ," +
					"TYPE VARCHAR(255) ," +
					"BOOKTITLE VARCHAR(255) ," +
					"CROSSREF VARCHAR(255) ," +
					"PAGES VARCHAR(255) ," +
					"VOLUME VARCHAR(255) ," +
					"NUMBER VARCHAR(255) ," +
					"EE VARCHAR(255) ," +
					"URL VARCHAR(255) ," +
					"EDITOR VARCHAR(255),"+
					"FILENAME VARCHAR(255),"+
					"ADDR VARCHAR(255))";
			result = stmt.executeUpdate(sql);
			sql = "CREATE INDEX INX_ADDR ON DISK_TABLE(ADDR ASC)";
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
//			System.out.println("Create table successfully!");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return result;
	}
	
	public int createTable(int tableid) {
		// TODO Auto-generated method stub
		int result = 0;
		
		this.tableid = tableid;
		
		try {
			stmt = this.conn.createStatement();
			String sql = "CREATE TABLE DISK_TABLE" + this.tableid +
					"(ID INTEGER PRIMARY KEY NOT NULL," +
					"AUTHOR VARCHAR(255) ," +      
					"TITLE VARCHAR(255)," +
					"PUBLISHER VARCHAR(255)," +
					"YEAR TEXT ," +
					"TYPE VARCHAR(255) ," +
					"BOOKTITLE VARCHAR(255) ," +
					"CROSSREF VARCHAR(255) ," +
					"PAGES VARCHAR(255) ," +
					"VOLUME VARCHAR(255) ," +
					"NUMBER VARCHAR(255) ," +
					"EE VARCHAR(255) ," +
					"URL VARCHAR(255) ," +
					"EDITOR VARCHAR(255),"+
					"FILENAME VARCHAR(255),"+
					"ADDR VARCHAR(255))";
			result = stmt.executeUpdate(sql);
			sql = "CREATE INDEX INX_ADDR" + this.tableid + " ON DISK_TABLE" + this.tableid + "(ADDR ASC)";
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
//			System.out.println("Create table successfully!");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return result;
	}


	public int insert(int id,String author, String title, String publisher, String year, String type, String booktitle,
			String crossref, String pages, String volume, String number, String ee, String url, String editor) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "INSERT OR REPLACE INTO DISK_TABLE" + this.tableid + "(ID,AUTHOR,TITLE,PUBLISHER,YEAR,TYPE,BOOKTITLE,CROSSREF,PAGES,VOLUME,NUMBER,EE,URL,EDITOR) " +
					"VALUES (\"" + id + "\",\""+ author + "\",\"" + title + "\",\"" + publisher + "\",\"" + year + "\",\"" + type + "\",\"" + booktitle + "\",\"" + crossref + "\",\""+ pages + "\",\"" + volume + "\",\"" + number + "\",\"" + ee+ "\",\"" + url+ "\",\"" + editor  +"\")";
	
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int insert(int id,String author, String title, String publisher, String year, String type, String booktitle,
			String crossref, String pages, String volume, String number, String ee, String url, String editor, String filename, String addr) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "INSERT OR REPLACE INTO DISK_TABLE" + this.tableid + "(ID,AUTHOR,TITLE,PUBLISHER,YEAR,TYPE,BOOKTITLE,CROSSREF,PAGES,VOLUME,NUMBER,EE,URL,EDITOR,FILENAME,ADDR) " +
					"VALUES (\"" + id + "\",\""+ author + "\",\"" + title + "\",\"" + publisher + "\",\"" + year + "\",\"" + type + "\",\"" + booktitle + "\",\"" + crossref + "\",\""+ pages + "\",\"" + volume + "\",\"" + number + "\",\"" + ee+ "\",\"" + url+ "\",\"" + editor  + "\",\"" + filename + "\",\"" + addr + "\")";
	
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}


	public List<NormalRecord> searchAll(String author, String title, String publisher, String year, String type,
			String booktitle, String crossref, String pages,String volume, String number, String ee, String url, String editor) {
		// TODO Auto-generated method stub
		List<NormalRecord> list = new ArrayList<NormalRecord>();
		try {

			stmt = conn.createStatement();
	
			String sql = "";
			sql = "SELECT * FROM DISK_TABLE WHERE 1=1";
			
			NormalRecord normalRecord = null;
	/*		
			if(author1 != "" && author1 != null)
				sql += " AND AUTHOR1=\"" + author1 + "\"";
				System.out.println("aaa");
			if(author2 != "")
				sql += " "+connect1+"  AUTHOR2=\"" + author2 + "\"";
			if(author3 != "")
				sql += " "+connect2+ "  AUTHOR3=\"" + author3 + "\"";
			if(author4 != "")
				sql += " "+connect3+ "  AUTHOR4=\"" + author4 + "\"";
			if(title1 != "")
				sql += " "+connect4+ "  TITLE1=\"" + title1 + "\"";
			if(title2 != "")
				sql += " "+connect5+ "  TITLE2=\"" + title2 + "\"";
			if(title3 != "")
				sql += " "+connect6+ "  TITLE3=\"" + title3 + "\"";
			if(publisher != "")
				sql += " "+connect7+ "  PUBLISHER=\"" + publisher + "\"";
			if(year != "")
				sql += " "+connect8+ "  YEAR=\"" + year + "\"";
			if(type != "")
				sql += " AND TYPE=\"" + type + "\"";
			*/
			
			if(author != "" && author != null)
				sql += " AND AUTHOR LIKE '%\"" + author + "%'\"";
			if(title != "")
				sql += " AND TITLE LIKE '%\"" + title + "%'\"";
			if(publisher != "")
				sql += " AND PUBLISHER LIKE '%\"" + publisher + "%'\"";
			if(year != "")
				sql += " AND  YEAR LIKE '%\"" + year + "%'\"";
			if(type != "")
				sql += " AND TYPE LIKE '%\"" + type + "%'\"";
			if(author != "" && author != null)
				sql += " AND BOOKTITLE LIKE '%\"" + booktitle + "%'\"";
			if(title != "")
				sql += " AND CROSSREF LIKE '%\"" + crossref + "%'\"";
			if(publisher != "")
				sql += " AND PAGES LIKE '%\"" + pages + "%'\"";
			if(year != "")
				sql += " AND  VOLUME LIKE '%\"" + volume + "%'\"";
			if(type != "")
				sql += " AND NUMBER LIKE '%\"" + number + "%'\"";
			if(publisher != "")
				sql += " AND EE LIKE '%\"" + ee + "%'\"";
			if(year != "")
				sql += " AND  URL LIKE '%\"" + url + "%'\"";
			if(type != "")
				sql += " AND EDITOR LIKE '%\"" + editor + "%'\"";
		
			ResultSet rs = stmt.executeQuery(sql);
	
			while(rs.next()) {
				
				int _id = rs.getInt("ID");
				String _author = rs.getString("AUTHOR");
				String _title = rs.getString("TITLE");
				String _publisher = rs.getString("PUBLISHER");
				String _year = rs.getString("YEAR");
				String _type = rs.getString("TYPE");
				String _booktitle = rs.getString("BOOKTITLE");
				String _crossref = rs.getString("CROSSREF");
				String _pages = rs.getString("PAGES");
				String _volume = rs.getString("VOLUME");
				String _number = rs.getString("NUMBER");
				String _ee = rs.getString("EE");
				String _url = rs.getString("URL");
				String _editor = rs.getString("EDITOR");
				String _filediskname = rs.getString("FILENAME");
				
				normalRecord = new NormalRecord(_id, _author, _title, _publisher, _year, _type, _booktitle, _crossref, _pages, 
						_volume, _number, _ee, _url, _editor, _filediskname);
				list.add(normalRecord);
			}
			
			rs.close();
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}


	public void disConnection() {
		// TODO Auto-generated method stub
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}

	public int update(int id, String filename, String ADD) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "UPDATE DISK_TABLE SET FILENAME =" + "\"" + filename + "\"" +","+"ADDR = "+ "\"" + ADD + "\""+ " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
	//		System.out.println("update succsse"+sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
//	public int updateAuthor1(int id, String author1) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//		//	sql = "UPDATE FACET_TABLE SET AUTHOR1 = author1 WHERE ID = id ";
//			sql = "UPDATE FACET_TABLE SET AUTHOR1 =" + "\"" + author1 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateAuthor2(int id, String author2) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET AUTHOR2 =" + "\"" + author2 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateAuthor3(int id, String author3) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET AUTHOR3 =" + "\"" + author3 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//		
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateAuthor4(int id, String author4) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET AUTHOR4 =" + "\"" + author4 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateTitle1(int id, String title1) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET TITLE1 =" + "\"" + title1 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateTitle2(int id, String title2) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET TITLE2 =" + "\"" + title2 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updateTitle3(int id, String title3) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET TITLE3 =" + "\"" + title3 + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
//	
//	public int updatePublisher(int id, String publisher) {
//		int result = 0;
//		try {
//			stmt = conn.createStatement();
//			String sql = "";
//			sql = "UPDATE FACET_TABLE SET PUBLISHER =" + "\"" + publisher + "\"" + " WHERE ID = " + id;
//			result = stmt.executeUpdate(sql);
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}

	public List<NormalRecord> search(String author1, String author2, String author3, String author4, String title1,
			String title2, String title3, String publisher, String year, String type, String ADDDisk) {
		// TODO Auto-generated method stub
		List<NormalRecord> list = new ArrayList<NormalRecord>();
		try {

			stmt = conn.createStatement();
	
			String sql = "";
			sql = "SELECT * FROM DISK_TABLE WHERE 1=1  AND ADDR < \"" + ADDDisk+"\"";
			
			NormalRecord normalRecord = null;
	/*		
			if(author1 != "" && author1 != null)
				sql += " AND AUTHOR1=\"" + author1 + "\"";
				System.out.println("aaa");
			if(author2 != "")
				sql += " "+connect1+"  AUTHOR2=\"" + author2 + "\"";
			if(author3 != "")
				sql += " "+connect2+ "  AUTHOR3=\"" + author3 + "\"";
			if(author4 != "")
				sql += " "+connect3+ "  AUTHOR4=\"" + author4 + "\"";
			if(title1 != "")
				sql += " "+connect4+ "  TITLE1=\"" + title1 + "\"";
			if(title2 != "")
				sql += " "+connect5+ "  TITLE2=\"" + title2 + "\"";
			if(title3 != "")
				sql += " "+connect6+ "  TITLE3=\"" + title3 + "\"";
			if(publisher != "")
				sql += " "+connect7+ "  PUBLISHER=\"" + publisher + "\"";
			if(year != "")
				sql += " "+connect8+ "  YEAR=\"" + year + "\"";
			if(type != "")
				sql += " AND TYPE=\"" + type + "\"";
			*/
			if(author1 != "" && author1 != null)
				sql += " AND AUTHOR1=\"" + author1 + "\"";
				System.out.println("aaa");
			if(author2 != "")
				sql += "AND "+"  AUTHOR2=\"" + author2 + "\"";
			if(author3 != "")
				sql += " AND"+ "  AUTHOR3=\"" + author3 + "\"";
			if(author4 != "")
				sql += " AND"+ "  AUTHOR4=\"" + author4 + "\"";
			if(title1 != "")
				sql += "AND"+ "  TITLE1=\"" + title1 + "\"";
			if(title2 != "")
				sql += "AND"+ "  TITLE2=\"" + title2 + "\"";
			if(title3 != "")
				sql += "AND"+ "  TITLE3=\"" + title3 + "\"";
			if(publisher != "")
				sql += "AND"+ "  PUBLISHER=\"" + publisher + "\"";
			if(year != "")
				sql += "AND"+ "  YEAR=\"" + year + "\"";
			if(type != "")
				sql += " AND TYPE=\"" + type + "\"";
			
			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("44"+rs.next());
			while(rs.next()) {
		
				int _id = rs.getInt("ID");
				String _author = rs.getString("AUTHOR");
				String _title = rs.getString("TITLE");
		
				String _publisher = rs.getString("PUBLISHER");
				String _year = rs.getString("YEAR");
				String _type = rs.getString("TYPE");
			
				String _booktitle = rs.getString("BOOKTITLE");
				String _crossref = rs.getString("CROSSREF");
				String _pages = rs.getString("PAGES");
				
				String _volume = rs.getString("VOLUME");
				String _number = rs.getString("NUMBER");
				String _ee = rs.getString("EE");
				
				String _url = rs.getString("URL");
				String _editor = rs.getString("EDITOR");
		
				String _filediskname = rs.getString("FILENAME");
		
				normalRecord = new NormalRecord(_id, _author, _title, _publisher, _year, _type, _booktitle, _crossref, _pages, 
						_volume, _number, _ee, _url, _editor, _filediskname);
				list.add(normalRecord);
			}
			
			rs.close();
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}


	public List<NormalRecord> search(String author, String title,
			String publisher, String year, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
