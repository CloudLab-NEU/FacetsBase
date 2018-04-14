package impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import db.*;
import util.FacetRecord;

public class SQLiteOperation implements SQLiteOperationDao{
	
	private Connection conn = null;
	private Statement stmt = null;
	
	@Override
	public void getConnection() {
		// TODO Auto-generated method stub
		
//		this.conn = DiskDBConnector.getConn();
		this.conn = MemoryDBConnector.getConn();
	}
	
	@Override
	public int createTable() {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			stmt = this.conn.createStatement();
			String sql = "CREATE TABLE FACET_TABLE" +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
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
					"FILENAME VARCHAR(255) NOT NULL)";
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			System.out.println("Create table successfully!");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int insert(String author1, String author2, String author3, String author4, String title1, String title2,
			String title3, String publisher, String year, String type, String filename) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "INSERT INTO FACET_TABLE(AUTHOR1,AUTHOR2,AUTHOR3,AUTHOR4,TITLE1,TITLE2,TITLE3,PUBLISHER,YEAR,TYPE,FILENAME) " +
					"VALUES (\"" + author1 + "\",\"" + author2 + "\",\"" + author3 + "\",\"" + author4 + "\",\"" + title1 + "\",\"" + title2 + "\",\"" + title3 + "\",\"" + publisher + "\",\"" + year + "\",\"" + type + "\",\"" + filename +"\")";
			
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	@Override
	public List<FacetRecord> search(String author1, String author2, String author3, String author4, String title1,
			String title2, String title3, String publisher, String year, String type) {
		// TODO Auto-generated method stub
		List<FacetRecord> list = new ArrayList<FacetRecord>();
		try {
			stmt = conn.createStatement();
			
			String sql = "";
			sql = "SELECT * FROM FACET_TABLE WHERE 1=1";
			
			FacetRecord facetRecord = null;
			
			if(author1 != "")
				sql += " AND AUTHOR1=\"" + author1 + "\"";
			if(author2 != "")
				sql += " AND AUTHOR2=\"" + author2 + "\"";
			if(author3 != "")
				sql += " AND AUTHOR3=\"" + author3 + "\"";
			if(author4 != "")
				sql += " AND AUTHOR4=\"" + author4 + "\"";
			if(title1 != "")
				sql += " AND TITLE1=\"" + title1 + "\"";
			if(title2 != "")
				sql += " AND TITLE2=\"" + title2 + "\"";
			if(title3 != "")
				sql += " AND TITLE3=\"" + title3 + "\"";
			if(publisher != "")
				sql += " AND PUBLISHER=\"" + publisher + "\"";
			if(year != "")
				sql += " AND YEAR=\"" + year + "\"";
			if(type != "")
				sql += " AND TYPE=\"" + type + "\"";
			
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

	@Override
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

	@Override
	public int update(int id, String filename) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "";
			
			sql = "UPDATE FACET_TABLE SET FILENAME =" + "\"" + filename + "\"" + " WHERE ID = " + id;
			result = stmt.executeUpdate(sql);
			System.out.println("update succsse");
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}



	

	
}
