package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import impl.FacetSegmentation;
import impl.FileControl;
import impl.SQLiteOperation;
import impl.SaveADDFile;
import impl.TrieTreeOpeartion;
import util.FacetRecord;

public class test1 {

	public static void main(String[] args) {
		SQLiteOperation sqLiteOperation = new SQLiteOperation();
		TrieTreeOpeartion treeOpeartion = new TrieTreeOpeartion();
		List<FacetRecord> list = null;
		List<FacetRecord> afterlist = null;
		FileControl fileControl = null;
		FacetSegmentation facetSegmentation = null;
		
		String author1;
		String author2;
		String author3;
		String author4;
		String title1;
		String title2;
		String title3;
		String publisher;
		String year;
		String type;
		Map<String, String> trival = new HashMap<String, String>();
		
		//����Trie��
		treeOpeartion.BuildTree();
		
		//�����ڴ����ݿⲢ��������(��Ҫת���ɱ���)
		sqLiteOperation.getConnection();
		sqLiteOperation.createTable();
		
		sqLiteOperation.insert("aa", "aaa", "aaai", "aaas", "aab", "aabb", "aac", "aadc", "2017", "phdthesis", "a");
		sqLiteOperation.insert("aa", "aaa", "aaai", "aaas", "aab", "aabb", "aac", "aadc", "2016", "p", "a");
		sqLiteOperation.insert("aa", "aaa", "aaai", "aaas", "aab", "aabb", "aac", "aadc", "2017", "hdthesis", "a");
		sqLiteOperation.insert("aa", "aaa", "aaai", "aaas", "aab", "aabb", "aac", "aadc", "2017", "dthesis", "a");
		sqLiteOperation.insert("aa", "aaa", "aaai", "aaas", "aab", "aabb", "aac", "aadc", "2017", "thesis", "a");
		
		
		//ģ����Ҳ��� 
//		���в�ѯ����ʱ���뵥�ʣ���������null
		System.out.println("��������������͵��ʣ�");
		Scanner sc = new Scanner(System.in);
		System.out.print("AUTHOR1:");
		author1 = sc.nextLine();
		System.out.print("AUTHOR2:");
		author2 = sc.nextLine();
		System.out.print("AUTHOR3:");
		author3 = sc.nextLine();
		System.out.print("AUTHOR4:");
		author4 = sc.nextLine();
		System.out.print("TITLE1:");
		title1 = sc.nextLine();
		System.out.print("TITLE2:");
		title2 = sc.nextLine();
		System.out.print("TITLE3:");
		title3 = sc.nextLine();
		System.out.print("PUBLISHER:");
		publisher = sc.nextLine();
		System.out.print("YEAR:");
		year = sc.nextLine();
		System.out.print("TYPE:");
		type = sc.nextLine();
		
		if(author1.equals("null"))
			author1 = "";
		if(author2.equals("null"))
			author2 = "";
		if(author3.equals("null"))
			author3 = "";
		if(author4.equals("null"))
			author4 = "";
		if(title1.equals("null"))
			title1 = "";
		if(title2.equals("null"))
			title2 = "";
		if(title3.equals("null"))
			title3 = "";
		if(publisher.equals("null"))
			publisher = "";
		if(year.equals("null"))
			year = "";
		if(type.equals("null"))
			type = "";
//		else
//			;
		
		//segementation����
		facetSegmentation = new FacetSegmentation();
		System.out.println("����������Ӧcube�߼���ַΪ��" + facetSegmentation.GetCubeByFacets(author1, author2, author3, author4, title1, title2, title3, publisher));
		
		System.out.println("��ѯ�����");
 		list = sqLiteOperation.search(author1, author2, author3, author4, title1, title2, title3, publisher, year, type);
//		Map<String, List<SaveADDFile>> map = new HashMap<String,List<SaveADDFile>>();
 		fileControl = new FileControl();
		for (FacetRecord facetRecord : list) {
			SaveADDFile data = new SaveADDFile(facetRecord);
			String diskadd = null;
			String address1 = data.getAddress();
			FileWriter fw;
			Random random = new Random();
			int x = random.nextInt(2);
			File file;
			
			if(x == 1){
				file = new File("D://test2//"+address1 + ".txt");
				diskadd = "D://test2//"+address1 + ".txt";
				//
				fileControl.AddToFile(facetRecord.getId(),
						facetRecord.getAuthor1(),
						facetRecord.getAuthor2(),
						facetRecord.getAuthor3(),
						facetRecord.getAuthor4(),
						facetRecord.getTitle1(),
						facetRecord.getTitle2(),
						facetRecord.getTitle3(),
						facetRecord.getPublisher(),
						facetRecord.getYear(),
						facetRecord.getType(),
						trival,
						diskadd);
				//
			}
			else{
				file = new File("D://test1//"+address1 + ".txt");
				diskadd = "D://test1//"+address1 + ".txt";
				//
				fileControl.AddToFile(facetRecord.getId(),
						facetRecord.getAuthor1(),
						facetRecord.getAuthor2(),
						facetRecord.getAuthor3(),
						facetRecord.getAuthor4(),
						facetRecord.getTitle1(),
						facetRecord.getTitle2(),
						facetRecord.getTitle3(),
						facetRecord.getPublisher(),
						facetRecord.getYear(),
						facetRecord.getType(),
						trival,
						diskadd);
				//
			}
			sqLiteOperation.update(facetRecord.getId(),diskadd);
			facetRecord.setFilename(diskadd);
//			try {
//			fw = new FileWriter(file,true);
//			fw.write(facetRecord.toString()+ "\r\n");
//			fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println(facetRecord.toString());
		}
//		afterlist = sqLiteOperation.search(author1, author2, author3, author4, title1, title2, title3, publisher, year, type);
//		for (FacetRecord facetRecordaafter : list) {
//			System.out.println(facetRecordaafter.toString());
//		}
		//�Ͽ��ڴ����ݿ�����
		sqLiteOperation.disConnection();
	}

}
