package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import db.*;
import util.FacetRecord;

public class SQLiteMemoryOperation implements SQLiteMemoryOperationDao{
	
	private Connection conn = null;
	private Statement stmt = null;
	
	private int tableid = 0;

	public void getConnection() {
		// TODO Auto-generated method stub
		
//		this.conn = DiskDBConnector.getConn();
		this.conn = MemoryDBConnector.getConn();
	}
	
	public void getConnection(String memory) {
		// TODO Auto-generated method stub
//		MemoryDBConnector memorydbconnector = new MemoryDBConnector();
//		this.conn = memorydbconnector.getConn(memory);
		this.conn = MemoryDBConnector.getConn(memory);
	}
	
	
	public int createTable() {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			stmt = this.conn.createStatement();
			String sql = "CREATE TABLE FACET_TABLE" +
					"(ID INTEGER PRIMARY KEY  NOT NULL," +
					"AUTHOR1 VARCHAR(255) NOT NULL," +
					"AUTHOR2 VARCAHR(255)," +
					"AUTHOR3 VARCAHR(255)," +       
					"AUTHOR4 VARCHAR(255)," +       
					"TITLE1 VARCHAR(255) NOT NULL," +
					"TITLE2 VARCHAR(255) NOT NULL," +
					"TITLE3 VARCHAR(255) NOT NULL," +
					"PUBLISHER VARCHAR(255) NOT NULL," +
					"YEAR TEXT NOT NULL," +
					"TYPE VARCHAR(255) NOT NULL," +
					"FILENAME VARCHAR(255),"+
					"ADDR VARCHAR(255))";
			result = stmt.executeUpdate(sql);
			System.out.println("suc");
			sql = "CREATE INDEX INX_ADDR ON FACET_TABLE(ADDR ASC)";
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
			String sql = "CREATE TABLE FACET_TABLE" + this.tableid +
					"(ID INTEGER PRIMARY KEY  NOT NULL," +
					"AUTHOR1 VARCHAR(255) NOT NULL," +
					"AUTHOR2 VARCAHR(255)," +
					"AUTHOR3 VARCAHR(255)," +       
					"AUTHOR4 VARCHAR(255)," +       
					"TITLE1 VARCHAR(255) NOT NULL," +
					"TITLE2 VARCHAR(255) NOT NULL," +
					"TITLE3 VARCHAR(255) NOT NULL," +
					"PUBLISHER VARCHAR(255) NOT NULL," +
					"YEAR TEXT NOT NULL," +
					"TYPE VARCHAR(255) NOT NULL," +
					"FILENAME VARCHAR(255),"+
					"ADDR VARCHAR(255))";
			result = stmt.executeUpdate(sql);
			System.out.println("suc");
			sql = "CREATE INDEX INX_ADDR" + this.tableid + " ON FACET_TABLE" + this.tableid + "(ADDR ASC)";
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
//			System.out.println("Create table successfully!");
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			System.err.println("create error!!!!!");
		}
		
		return result;
	}
	
	public int loadData(String diskDB) {
		int i = 0;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + diskDB);
			conn.setAutoCommit(false);
//			System.out.println("Opened disk database successfully");
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM FACET_TABLE0;" );

			while (rs.next()) {
		         int ID = rs.getInt("ID");
		         String AUTHOR1 = rs.getString("AUTHOR1");
		         String AUTHOR2 = rs.getString("AUTHOR2");
		         String AUTHOR3 = rs.getString("AUTHOR3");
		         String AUTHOR4 = rs.getString("AUTHOR4");
		         String TITLE1 = rs.getString("TITLE1");
		         String TITLE2 = rs.getString("TITLE2");
		         String TITLE3 = rs.getString("TITLE3");
		         String PUBLISHER = rs.getString("PUBLISHER");
		         String YEAE = rs.getString("YEAR");
		         String TYPE = rs.getString("TYPE");
		         String FILENAME = rs.getString("FILENAME");
		         String ADDR = rs.getString("ADDR");
		         
		         i += insert(ID, AUTHOR1, AUTHOR2, AUTHOR3, AUTHOR4, TITLE1, TITLE2, TITLE3, PUBLISHER, YEAE, TYPE, FILENAME, ADDR);
		      }
		      rs.close();
		      stmt.close();
		      conn.close();
			
		}
		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return i;
	}
	
	public int loadData(String diskDB, int len) {
		int i = 0;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + diskDB);
			conn.setAutoCommit(false);
//			System.out.println("Opened disk database successfully");
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM FACET_TABLE0 LIMIT 1," + len + ";" );

			while (rs.next()) {
		         int ID = rs.getInt("ID");
		         String AUTHOR1 = rs.getString("AUTHOR1");
		         String AUTHOR2 = rs.getString("AUTHOR2");
		         String AUTHOR3 = rs.getString("AUTHOR3");
		         String AUTHOR4 = rs.getString("AUTHOR4");
		         String TITLE1 = rs.getString("TITLE1");
		         String TITLE2 = rs.getString("TITLE2");
		         String TITLE3 = rs.getString("TITLE3");
		         String PUBLISHER = rs.getString("PUBLISHER");
		         String YEAE = rs.getString("YEAR");
		         String TYPE = rs.getString("TYPE");
		         String FILENAME = rs.getString("FILENAME");
		         String ADDR = rs.getString("ADDR");
		         
		         i += insert(ID, AUTHOR1, AUTHOR2, AUTHOR3, AUTHOR4, TITLE1, TITLE2, TITLE3, PUBLISHER, YEAE, TYPE, FILENAME, ADDR);
		      }
		      rs.close();
		      stmt.close();
		      conn.close();
			
		}
		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return i;
	}

	public int insert(int id,String author1, String author2, String author3, String author4, String title1, String title2,
			String title3, String publisher, String year, String type) {
		// TODO Auto-generated method stub

		int result = 0;
		
		try {

			stmt = conn.createStatement();
		
			String sql = "";
			
			sql = "INSERT OR REPLACE INTO FACET_TABLE(ID,AUTHOR1,AUTHOR2,AUTHOR3,AUTHOR4,TITLE1,TITLE2,TITLE3,PUBLISHER,YEAR,TYPE) " +
					"VALUES (\"" + id + "\",\""+ author1 + "\",\"" + author2 + "\",\"" + author3 + "\",\"" + author4 + "\",\"" + title1 + "\",\"" + title2 + "\",\"" + title3 + "\",\"" + publisher + "\",\"" + year + "\",\"" + type  +"\")";
	
			result = stmt.executeUpdate(sql);
	
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public int insert(int id,String author1, String author2, String author3, String author4, String title1, String title2,
			String title3, String publisher, String year, String type, String fileName, String addr) {
		
		int result = 0;
		
		try {

			stmt = conn.createStatement();
		
			String sql = "";
			
			sql = "INSERT OR REPLACE INTO FACET_TABLE(ID,AUTHOR1,AUTHOR2,AUTHOR3,AUTHOR4,TITLE1,TITLE2,TITLE3,PUBLISHER,YEAR,TYPE,FILENAME,ADDR) " +
					"VALUES (\"" + id + "\",\""+ author1 + "\",\"" + author2 + "\",\"" + author3 + "\",\"" + author4 + "\",\"" + title1 + "\",\"" + title2 + "\",\"" + title3 + "\",\"" + publisher + "\",\"" + year + "\",\"" + type  + "\",\"" + fileName + "\",\"" + addr + "\")";
	
			result = stmt.executeUpdate(sql);
	
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result; 
	}


//	public List<FacetRecord> search(String author1, String author2, String author3, String author4, String title1,
//			String title2, String title3, String publisher, String year, String type, String connect1, String connect2,
//			String connect3, String connect4, String connect5, String connect6, String connect7, String connect8) {
//		// TODO Auto-generated method stub
//		List<FacetRecord> list = new ArrayList<FacetRecord>();
//		try {
//   
//			stmt = conn.createStatement();
//	
//			String sql = "";
//			sql = "SELECT * FROM FACET_TABLE WHERE 1=1";
//			
//			FacetRecord facetRecord = null;
//	/*		
//			if(author1 != "" && author1 != null)
//				sql += " AND AUTHOR1=\"" + author1 + "\"";
//				System.out.println("aaa");
//			if(author2 != "")
//				sql += " "+connect1+"  AUTHOR2=\"" + author2 + "\"";
//			if(author3 != "")
//				sql += " "+connect2+ "  AUTHOR3=\"" + author3 + "\"";
//			if(author4 != "")
//				sql += " "+connect3+ "  AUTHOR4=\"" + author4 + "\"";
//			if(title1 != "")
//				sql += " "+connect4+ "  TITLE1=\"" + title1 + "\"";
//			if(title2 != "")
//				sql += " "+connect5+ "  TITLE2=\"" + title2 + "\"";
//			if(title3 != "")
//				sql += " "+connect6+ "  TITLE3=\"" + title3 + "\"";
//			if(publisher != "")
//				sql += " "+connect7+ "  PUBLISHER=\"" + publisher + "\"";
//			if(year != "")
//				sql += " "+connect8+ "  YEAR=\"" + year + "\"";
//			if(type != "")
//				sql += " AND TYPE=\"" + type + "\"";
//			*/
//			
//			if(author1 != "" && author1 != null)
//				sql += " AND AUTHOR1=\"" + author1 + "\"";
//			
//			if(author2 != ""&&connect1=="AND")
//				sql += " "+connect1+"  AUTHOR2=\"" + author2 + "\"";
//			if(author3 != ""&&connect2=="AND")
//				sql += " "+connect2+ "  AUTHOR3=\"" + author3 + "\"";
//			if(author4 != ""&&connect3=="AND")
//				sql += " "+connect3+ "  AUTHOR4=\"" + author4 + "\"";
//			if(title1 != ""&&connect4=="AND")
//				sql += " "+connect4+ "  TITLE1=\"" + title1 + "\"";
//			if(title2 != ""&&connect5=="AND")
//				sql += " "+connect5+ "  TITLE2=\"" + title2 + "\"";
//			if(title3 != ""&&connect6=="AND")
//				sql += " "+connect6+ "  TITLE3=\"" + title3 + "\"";
//			if(publisher != ""&&connect7=="AND")
//				sql += " "+connect7+ "  PUBLISHER=\"" + publisher + "\"";
//			if(year != ""&&connect8=="AND")
//				sql += " "+connect8+ "  YEAR=\"" + year + "\"";
//			if(type != "")
//				sql += " AND TYPE=\"" + type + "\"";
//			
//			if(author2 != ""&&connect1=="OR")
//				sql += " "+connect1+"  AUTHOR2=\"" + author2 + "\"";
//			if(author3 != ""&&connect2=="OR")
//				sql += " "+connect2+ "  AUTHOR3=\"" + author3 + "\"";
//			if(author4 != ""&&connect3=="OR")
//				sql += " "+connect3+ "  AUTHOR4=\"" + author4 + "\"";
//			if(title1 != ""&&connect4=="OR")
//				sql += " "+connect4+ "  TITLE1=\"" + title1 + "\"";
//			if(title2 != ""&&connect5=="OR")
//				sql += " "+connect5+ "  TITLE2=\"" + title2 + "\"";
//			if(title3 != ""&&connect6=="OR")
//				sql += " "+connect6+ "  TITLE3=\"" + title3 + "\"";
//			if(publisher != ""&&connect7=="OR")
//				sql += " "+connect7+ "  PUBLISHER=\"" + publisher + "\"";
//			if(year != ""&&connect8=="OR")
//				sql += " "+connect8+ "  YEAR=\"" + year + "\"";
//		
//			
//			
//			ResultSet rs = stmt.executeQuery(sql);
//		
//			while(rs.next()) {
//				
//				int _id = rs.getInt("ID");
//				String _author1 = rs.getString("AUTHOR1");
//				String _author2 = rs.getString("AUTHOR2");
//				String _author3 = rs.getString("AUTHOR3");
//				String _author4 = rs.getString("AUTHOR4");
//				String _title1 = rs.getString("TITLE1");
//				String _title2 = rs.getString("TITLE2");
//				String _title3 = rs.getString("TITLE3");
//				String _publisher = rs.getString("PUBLISHER");
//				String _year = rs.getString("YEAR");
//				String _type = rs.getString("TYPE");
//				String _filename = rs.getString("FILENAME");
//				
//				facetRecord = new FacetRecord(_id, _author1, _author2, _author3, _author4, _title1, _title2, _title3, _publisher, _year, _type, _filename);
//				list.add(facetRecord);
//			}
//			
//			rs.close();
//			stmt.close();
//			conn.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		return list;
//	}
	
	public List<FacetRecord> searchAll(String author1, String author2, String author3, String author4, String title1,
			String title2, String title3, String publisher, String year, String type) {
		List<FacetRecord> list = new ArrayList<FacetRecord>();
		try {
			   
			stmt = conn.createStatement();
			String sql = "";
			sql = "SELECT * FROM FACET_TABLE WHERE 1=1";
			
			FacetRecord facetRecord = null;
			if(!author1.equals("")&&!author1.equals("null"))
				sql += " AND AUTHOR1=\"" + author1 + "\"";
			if(!author2.equals("")&&!author2.equals("null"))
				sql += " AND "+"  AUTHOR2=\"" + author2 + "\"";
			if(!author3.equals("")&&!author3.equals("null"))
				sql += " AND"+ "  AUTHOR3=\"" + author3 + "\"";
			if(!author4.equals("")&&!author4.equals("null"))
				sql += " AND"+ "  AUTHOR4=\"" + author4 + "\"";
			if(!title1.equals("")&&!title1.equals("null"))
				sql += " AND"+ "  TITLE1=\"" + title1 + "\"";
			if(!title2.equals("")&&!title2.equals("null"))
				sql += " AND"+ "  TITLE2=\"" + title2 + "\"";
			if(!title3.equals("")&&!title3.equals("null"))
				sql += " AND"+ "  TITLE3=\"" + title3 + "\"";
			if(!publisher.equals("")&&!publisher.equals("null"))
				sql += " AND"+ "  PUBLISHER=\"" + publisher + "\"";
			if(!year.equals("")&&!year.equals("null"))
				sql += " AND"+ "  YEAR=\"" + year + "\"";
			if(!type.equals("")&&!type.equals("null"))
				sql += " AND TYPE=\"" + type + "\"";
//			System.out.println(sql);
			
//			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int _id = rs.getInt("ID");
				String _author1 = rs.getString("AUTHOR1");
				String _author2 = rs.getString("AUTHOR2");
				String _author3 = rs.getString("AUTHOR3");
				String _author4 = rs.getString("AUTHOR4");
				String _title1 = rs.getString("TITLE1");
				String _title2 = rs.getString("TITLE2");
				String _title3 = rs.getString("TITLE3");
				String _publisher = rs.getString("PUBLISHER");
				String _year = rs.getString("YEAR");
				String _type = rs.getString("TYPE");
				String _filename = rs.getString("FILENAME");
				
				facetRecord = new FacetRecord(_id, _author1, _author2, _author3, _author4, _title1, _title2, _title3, _publisher, _year, _type, _filename);
				list.add(facetRecord);
			}
			
			rs.close();
			stmt.close();
			conn.commit();
			} catch (Exception e) {
				// TODO: handle exception
			}
		 return list;
	}
	
	public List<FacetRecord> search(String author1, String author2, String author3, String author4, String title1,
			String title2, String title3, String publisher, String year, String type,String ADD){
		List<FacetRecord> list = new ArrayList<FacetRecord>();
		try {
   
			stmt = conn.createStatement();
			String sql = "";
			sql = "SELECT * FROM FACET_TABLE WHERE 1=1 AND ADDR < \"" + ADD+"\"";
			
			FacetRecord facetRecord = null;
			if(!author1.equals(""))
				sql += " AND AUTHOR1=\"" + author1 + "\"";
			if(!author2.equals(""))
				sql += "AND "+"  AUTHOR2=\"" + author2 + "\"";
			if(!author3.equals(""))
				sql += " AND"+ "  AUTHOR3=\"" + author3 + "\"";
			if(!author4.equals(""))
				sql += " AND"+ "  AUTHOR4=\"" + author4 + "\"";
			if(!title1.equals(""))
				sql += "AND"+ "  TITLE1=\"" + title1 + "\"";
			if(!title2.equals(""))
				sql += "AND"+ "  TITLE2=\"" + title2 + "\"";
			if(!title3.equals(""))
				sql += "AND"+ "  TITLE3=\"" + title3 + "\"";
			if(!publisher.equals(""))
				sql += "AND"+ "  PUBLISHER=\"" + publisher + "\"";
			if(!year.equals(""))
				sql += "AND"+ "  YEAR=\"" + year + "\"";
			if(!type.equals(""))
				sql += " AND TYPE=\"" + type + "\"";
//			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int _id = rs.getInt("ID");
				String _author1 = rs.getString("AUTHOR1");
				String _author2 = rs.getString("AUTHOR2");
				String _author3 = rs.getString("AUTHOR3");
				String _author4 = rs.getString("AUTHOR4");
				String _title1 = rs.getString("TITLE1");
				String _title2 = rs.getString("TITLE2");
				String _title3 = rs.getString("TITLE3");
				String _publisher = rs.getString("PUBLISHER");
				String _year = rs.getString("YEAR");
				String _type = rs.getString("TYPE");
				String _filename = rs.getString("FILENAME");
				
				facetRecord = new FacetRecord(_id, _author1, _author2, _author3, _author4, _title1, _title2, _title3, _publisher, _year, _type, _filename);
				list.add(facetRecord);
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
			
			sql = "UPDATE FACET_TABLE SET FILENAME =" + "\"" + filename + "\"" +","+"ADDR = "+ "\"" + ADD + "\""+ " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
		
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int updateAuthor1(int id, String author1) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
		//	sql = "UPDATE FACET_TABLE SET AUTHOR1 = author1 WHERE ID = id ";
			sql = "UPDATE FACET_TABLE SET AUTHOR1 =" + "\"" + author1 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);

			stmt.close();

			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateAuthor2(int id, String author2) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET AUTHOR2 =" + "\"" + author2 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateAuthor3(int id, String author3) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET AUTHOR3 =" + "\"" + author3 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
		
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateAuthor4(int id, String author4) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET AUTHOR4 =" + "\"" + author4 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);

			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateTitle1(int id, String title1) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET TITLE1 =" + "\"" + title1 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateTitle2(int id, String title2) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET TITLE2 =" + "\"" + title2 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updateTitle3(int id, String title3) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET TITLE3 =" + "\"" + title3 + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int updatePublisher(int id, String publisher) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			sql = "UPDATE FACET_TABLE SET PUBLISHER =" + "\"" + publisher + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}





	public int updateOther(int id, String type, String year) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "UPDATE FACET_TABLE SET TYPE =" + "\"" + type + "\"" +","+"Year = "+ "\"" + year + "\""+ " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
	
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
