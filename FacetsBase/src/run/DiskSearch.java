package run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import impl.BTOpeartion;
import impl.SQLiteDiskOperation;
import impl.SQLiteMemoryOperation;
import impl.TrieTreeOpeartion;
import impl.WriteAndReadSearchFileNameAndIds;
import util.FacetRecord;
import util.NormalRecord;
import util.Utilities;

public class DiskSearch {

	private static final String diskDB = "disk.db";
	private static final String resFilename = "hdfs://192.168.73.134:9000/res/FileName";
	private static Scanner scanner = new Scanner(System.in);
	private static List<NormalRecord> trivialRes;
	private static Map<String, List<Integer>> trivialMap;
	
//	private static TrieTreeOpeartion treeOpeartion = null;
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
		
		SQLiteDiskOperation sqliteDiskOperation = new SQLiteDiskOperation();
		WriteAndReadSearchFileNameAndIds writeAndReadSearchFileNameAndIds = new WriteAndReadSearchFileNameAndIds();
		//建立连接
		sqliteDiskOperation.getConnection(diskDB);
		//创建内存数据库的表
		sqliteDiskOperation.createTable();
		//加载硬盘数据库到内存数据库中
		System.out.print("请输入LEN：");
		String len = scanner.nextLine();
		System.out.println("len:"+len);
		

			
			System.out.println("请输入查询条件：");
			System.out.print("请输入AUTHOR：");
			String author = scanner.nextLine();
			System.out.print("请输入TITLE：");
			String title = scanner.nextLine();
			System.out.print("请输入PUBLISHER：");
			String publisher = scanner.nextLine();
			System.out.print("请输入YEAR：");
			String year = scanner.nextLine();
			System.out.print("请输入TYPE：");
			String type = scanner.nextLine();
			System.out.print("请输入booktitle：");
			String booktitle = scanner.nextLine();
			System.out.print("请输入crossref：");
			String crossref = scanner.nextLine();
			System.out.print("请输入pages：");
			String pages = scanner.nextLine();
			System.out.print("请输入volume：");
			String volume = scanner.nextLine();
			System.out.print("请输入number：");
			String number = scanner.nextLine();
			System.out.print("请输入ee：");
			String ee = scanner.nextLine();
			System.out.print("请输入url：");
			String url = scanner.nextLine();
			System.out.print("请输入editor：");
			String editor = scanner.nextLine();
			
			//查询B树
//			treeOpeartion = new TrieTreeOpeartion();
			btOpeartion = new BTOpeartion();
			
			btOpeartion.buildBTree();;
			if(!btOpeartion.isExistBT(author)) {
				author = "";
			}
			if(!btOpeartion.isExistBT(title)) {
				title = "";
			}
			if(!btOpeartion.isExistBT(publisher)) {
				publisher = "";
			}
			if(!btOpeartion.isExistBT(year)) {
				year = "";
			}
			if(!btOpeartion.isExistBT(type)) {
				type = "";
			}
			if(!btOpeartion.isExistBT(booktitle)) {
				booktitle = "";
			}
			if(!btOpeartion.isExistBT(crossref)) {
				crossref = "";
			}
			if(!btOpeartion.isExistBT(pages)) {
				pages = "";
			}
			if(!btOpeartion.isExistBT(volume)) {
				volume = "";
			}
			if(!btOpeartion.isExistBT(number)) {
				number = "";
			}
			if(!btOpeartion.isExistBT(ee)) {
				ee = "";
			}
			if(!btOpeartion.isExistBT(url)) {
				url = "";
			}
			if(!btOpeartion.isExistBT(editor)) {
				editor = "";
			}
			System.out.println("输入的查询条件为:");
			System.out.println("author:"+author);
			System.out.println("title:"+title);
			System.out.println("publisher:"+publisher);
			System.out.println("year:"+year);
			System.out.println("type:"+type);
			System.out.println("booktitle:"+booktitle);
			System.out.println("crossref:"+crossref);
			System.out.println("pages:"+pages);
			System.out.println("volume:"+volume);
			System.out.println("number:"+number);
			System.out.println("number:"+ee);
			System.out.println("number:"+url);
			System.out.println("number:"+editor);
			//未匹配到字典树中的单词
			if(author.equals("")&&title.equals("")&&publisher.equals("")&&year.equals("")&&type.equals("")&&booktitle.equals("")&&crossref.equals("")&&pages.equals("")&&volume.equals("")&&number.equals("")&&ee.equals("")&&url.equals("")&&editor.equals("")) {
				System.out.println("查询条件都不符合存在于字典树中，结束查询");
				sqliteDiskOperation.disConnection();
				System.exit(1);
			}

			//匹配到字典树中的单词
			long searchstarttime = System.currentTimeMillis();
			trivialRes = sqliteDiskOperation.searchAll(author, title, publisher, year, type, booktitle, crossref, pages, volume, number, ee, url, editor);
			long searchendtime = System.currentTimeMillis();
			
			if(trivialRes.size()==0) {
				System.out.println("未查找到匹配记录,用时" + (searchendtime-searchstarttime) +"ms");
				return;
			}
			System.out.println("查找到" + trivialRes.size() + "条匹配记录,用时"+ (searchendtime-searchstarttime) +"ms");
			Iterator<NormalRecord> it = trivialRes.iterator();
			NormalRecord fr;
			trivialMap = new HashMap<String, List<Integer>>();
			while(it.hasNext()) {
				fr = it.next();
				if(trivialMap.containsKey(fr.getFilediskname())) {
					List<Integer> arr = trivialMap.get(fr.getFilediskname());
					arr.add(fr.getId());
					trivialMap.put(fr.getFilediskname(), arr);
				}
				else {
					List<Integer> arr = new ArrayList<Integer>();
					arr.add(fr.getId());
					trivialMap.put(fr.getFilediskname(), arr);
				}
			}

			sqliteDiskOperation.disConnection();
			
			writeAndReadSearchFileNameAndIds.write(resFilename, Utilities.mapToStr(trivialMap));
//			System.out.println(writeAndReadSearchFileNameAndIds.read(resDilename));
			System.out.println("写入完成");


	}

}
