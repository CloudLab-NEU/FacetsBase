package impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.TrieTreeOperationDao;
import util.TrieTree;

public class TrieTreeOpeartion implements TrieTreeOperationDao{

	private TrieTree trie = new TrieTree();
	
	@Override
	public int BuildTree() {
		// TODO Auto-generated method stub
//		File file = new File("F:/workspace/SQLiteSearching/facets");
		File file = new File("F:/workspace/SQLiteSearching/All_Facet_with_English_word_only");
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null){
				trie.insert(tempString); 
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		} finally {
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					return 0;
				}
			}
		}
		return 1;
	}

	@Override
	public String SearchOne(String word) {
		// TODO Auto-generated method stub
		
		String address = "";
		
		if(trie.isExist(word)){
			System.out.println("�ֵ����в�����");
		}
		return address;
	}

	@Override
	public List<String> SearchList(List<String> words) {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<String>();
		String address = "";
		
		for (String word : words) {
			if(trie.isExist(word)){
//				System.out.println("�ֵ����в�����");
			}
			list.add(address);
		}
		
		return list;
	}
	
	
}
