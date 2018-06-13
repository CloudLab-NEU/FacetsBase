package impl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.BTOpearationDao;

import util.*;

public class BTOpeartion implements BTOpearationDao{
	private BTree<String, String> bt = new BTree<String, String>();
	public void buildBTree(){
		File file = new File("allelementfinal.txt");
		  BufferedReader reader = null;
		  int i = 0;
		  try{
//			  reader = new BufferedReader(new FileReader(file));
			  reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			  String tempString = null;
			  while ((tempString = reader.readLine()) != null){
				  bt.put(tempString,i); 
				  i++;
			  }
			  reader.close();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  finally{
			  if(reader != null){
				  try{
					  reader.close();
				  }catch(IOException e1){
				  }
			  }
		  }
	}
	public String searchBT(String word){
		if (word.equals("")){
			return "ffff";
		}
		else {
			bt.get(word);
			return bt.getAdd();
		}
	}
	public boolean isExistBT(String word){
		bt.get(word);
		if (bt.getAdd() == null){
			return false;
		}
		else{
			return true;
		}
	}
}
