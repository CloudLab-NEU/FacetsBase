package run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import impl.BTOpeartion;
import impl.SQLiteMemoryOperation;
import impl.TrieTreeOpeartion;
import impl.WriteAndReadSearchFileNameAndIds;
import util.FacetRecord;
import util.Utilities;

public class Search {
	
	private static final String diskDB = "memory.db";
	private static final String resFilename = "hdfs://192.168.73.134:9000/res/FileName";
	private static Scanner scanner = new Scanner(System.in);
	private static List<FacetRecord> facetRes;
	private static Map<String, List<Integer>> facetMap;
	
	private static TrieTreeOpeartion treeOpeartion = null;
	private static BTOpeartion btOpeartion = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Configuration conf = null;
		Path path = null;
		FileSystem fs = null;
		
		try {
			conf = new Configuration();
			path = new Path(resFilename);
			fs = path.getFileSystem(conf);
			if(fs.exists(path)) {
				fs.delete(path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SQLiteMemoryOperation sqliteMemoryOperation = new SQLiteMemoryOperation();
		WriteAndReadSearchFileNameAndIds writeAndReadSearchFileNameAndIds = new WriteAndReadSearchFileNameAndIds();
		//建立连接
		sqliteMemoryOperation.getConnection();
		//创建内存数据库的表
		sqliteMemoryOperation.createTable();
		//加载硬盘数据库到内存数据库中
		System.out.print("请输入LEN：");
		String len = scanner.nextLine();
		System.out.println("len:"+len);
		
		int recordCount = 0;
		long loadstarttime = System.currentTimeMillis();
		if(len=="ALL") {
			System.out.println("正在加载内存数据库，请稍等。。。");
			recordCount = sqliteMemoryOperation.loadData(diskDB);
		}
		else {
			int length = Integer.valueOf(len);
			System.out.println("正在加载内存数据库，请稍等。。。");
			recordCount = sqliteMemoryOperation.loadData(diskDB, length);
		}
		
		if(recordCount!=0) {
			System.out.println("硬盘数据库加载成功！，共加载" + recordCount + "条记录");
			
			long loadendtime = System.currentTimeMillis();
			
			System.out.println("加载内存数据库用时:"+ (loadendtime-loadstarttime) + "ms");
			
			System.out.println("请输入查询条件：");
			System.out.print("请输入AUTHOR1：");
			String author1 = scanner.nextLine();
			System.out.print("请输入AUTHOR2：");
			String author2 = scanner.nextLine();
			System.out.print("请输入AUTHOR3：");
			String author3 = scanner.nextLine();
			System.out.print("请输入AUTHOR4：");
			String author4 = scanner.nextLine();
			System.out.print("请输入TITLE1：");
			String title1 = scanner.nextLine();
			System.out.print("请输入TITLE2：");
			String title2 = scanner.nextLine();
			System.out.print("请输入TITLE3：");
			String title3 = scanner.nextLine();
			System.out.print("请输入PUBLISHER：");
			String publisher = scanner.nextLine();
			System.out.print("请输入YEAR：");
			String year = scanner.nextLine();
			System.out.print("请输入TYPE：");
			String type = scanner.nextLine();
			
			//查询字典树
			treeOpeartion = new TrieTreeOpeartion();
			btOpeartion = new BTOpeartion();
			
			treeOpeartion.BuildTree();
			if(!treeOpeartion.searchExist(author1)) {
				author1 = "";
			}
			if(!treeOpeartion.searchExist(author2)) {
				author2 = "";
			}
			if(!treeOpeartion.searchExist(author3)) {
				author3 = "";
			}
			if(!treeOpeartion.searchExist(author4)) {
				author4 = "";
			}
			if(!treeOpeartion.searchExist(title1)) {
				title1 = "";
			}
			if(!treeOpeartion.searchExist(title2)) {
				title2 = "";
			}
			if(!treeOpeartion.searchExist(title3)) {
				title3 = "";
			}
			if(!treeOpeartion.searchExist(publisher)) {
				publisher = "";
			}
			if(!treeOpeartion.searchExist(year)) {
				year = "";
			}
			if(!treeOpeartion.searchExist(type)) {
				type = "";
			}
			System.out.println("输入的查询条件为:");
			System.out.println("author1:"+author1);
			System.out.println("author2:"+author2);
			System.out.println("author3:"+author3);
			System.out.println("author4:"+author4);
			System.out.println("title1:"+title1);
			System.out.println("title2:"+title2);
			System.out.println("title3:"+title3);
			System.out.println("publisher:"+publisher);
			System.out.println("year:"+year);
			System.out.println("type:"+type);
			//未匹配到字典树中的单词
			if(author1.equals("")&&author2.equals("")&&author3.equals("")&&author4.equals("")&&title1.equals("")&&title2.equals("")&&title3.equals("")&&publisher.equals("")&&year.equals("")&&type.equals("")) {
				System.out.println("查询条件都不符合存在于字典树中，结束查询");
				sqliteMemoryOperation.disConnection();
				System.exit(1);
			}

			//匹配到字典树中的单词
			long searchstarttime = System.currentTimeMillis();
			facetRes = sqliteMemoryOperation.searchAll(author1, author2, author3, author4, title1, title2, title3, publisher, year, type);
			long searchendtime = System.currentTimeMillis();
			
			if(facetRes.size()==0) {
				System.out.println("未查找到匹配记录,用时" + (searchendtime-searchstarttime) +"ms");
				return;
			}
			System.out.println("查找到" + facetRes.size() + "条匹配记录,用时"+ (searchendtime-searchstarttime) +"ms");
			Iterator<FacetRecord> it = facetRes.iterator();
			FacetRecord fr;
			facetMap = new HashMap<String, List<Integer>>();
			while(it.hasNext()) {
				fr = it.next();
				if(facetMap.containsKey(fr.getFilename())) {
					List<Integer> arr = facetMap.get(fr.getFilename());
					arr.add(fr.getId());
					facetMap.put(fr.getFilename(), arr);
				}
				else {
					List<Integer> arr = new ArrayList<Integer>();
					arr.add(fr.getId());
					facetMap.put(fr.getFilename(), arr);
				}
			}

			sqliteMemoryOperation.disConnection();
			
			writeAndReadSearchFileNameAndIds.write(resFilename, Utilities.mapToStr(facetMap));
//			System.out.println(writeAndReadSearchFileNameAndIds.read(resDilename));
			System.out.println("写入完成");
		}
		else {
			System.out.println("硬盘数据为空或加载未成功");
			sqliteMemoryOperation.disConnection();
			System.exit(1);
		}

	}

}
