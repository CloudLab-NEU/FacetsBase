package util;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class TrieTree {
	private class Node{
		private int dumpliNum;
		private int prefixNum;
		private int id;
		private String add;
		private Node childs[];
		private boolean isLeaf;
		public Node(){
			dumpliNum = 0;
			prefixNum = 0;
			isLeaf = false;
			childs = new Node[26];
			id = 0;
			add = null;
		}
	}
	private Node root;
	private int code = 1;
	private int count ;
	public String address1 = "";
	private int randCode[]= new int[99]; 
	Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
	public TrieTree(){
		root = new Node();
		 numbers.put("a", 0);
	     numbers.put("b", 1);
	     numbers.put("c", 2);
	     numbers.put("d", 3);
	     numbers.put("e", 4);
	     numbers.put("f", 5);
	     numbers.put("g", 6);
	     numbers.put("h", 7);
	     numbers.put("i", 8);
	     numbers.put("j", 9);
	     numbers.put("k", 10);
	     numbers.put("l", 11);
	     numbers.put("m", 12);
	     numbers.put("n", 13);
	     numbers.put("o", 14);
	     numbers.put("p", 15);
	     numbers.put("q", 16);
	     numbers.put("r", 17);
	     numbers.put("s", 18);
	     numbers.put("t", 19);
	     numbers.put("u", 20);
	     numbers.put("v", 21);
	     numbers.put("w", 22);
	     numbers.put("x", 23);
	     numbers.put("y", 24);
	     numbers.put("z", 25);
	     numbers.put("!", 26);
	     numbers.put("@", 27);
	}
	public void insert(String words){
		insert(this.root,words);
	}
	private void insert(Node root,String words){
		words = words.toLowerCase();
		char[] chrs = words.toCharArray();
		Random random = new Random();              
		int pre = 0;
		int ceng =0;
		for(int i = 0, length = chrs.length; i < length; i++){
			String s = String.valueOf(chrs[i]);
			 int index = numbers.get(s);  
			if(root.childs[index]!=null && code ==1){
				
				root.childs[index].prefixNum++;
				root.childs[index].id = randCode[index];
				code++;
				
				//System.out.println("a"+Integer.toBinaryString(root.childs[index].id));
			}
			else if(root.childs[index]!=null && code > 1){
				root.childs[index].prefixNum++;
				root.childs[index].id = randCode[index];
				pre++;
			}
			else {
				root.childs[index] = new Node();
				if(pre > 0){
					int number = random.nextInt(99 - 1 + 1) + 1;
					randCode[index] = number;
					root.childs[index].id =randCode[index];
				root.childs[index].prefixNum++;
				}
				else{
					if (ceng == 0){
						int number = random.nextInt(99 - 1 + 1) + 1;
						randCode[index] = number;
						for (i = 0; i < index; i++){
							if (randCode[i] == randCode[index]){
								randCode[index] = random.nextInt(99 - 1 + 1) + 1;
								i = 0;
								continue;
							}
						}
						root.childs[index].id =randCode[index];
						root.childs[index].prefixNum++;
					}
					else{
						int number = random.nextInt(99 - 1 + 1) + 1;
						randCode[index] = number;
						root.childs[index].id =randCode[index];
						root.childs[index].prefixNum++;
					}
					
				}
			}

			if(i == length-1){
				root.childs[index].isLeaf = true;
				root.childs[index].dumpliNum++;
			}

//			String num = Integer.toBinaryString(root.childs[index].id);
//			root.childs[index].add = BinaryToHex(num);
//			System.out.print(root.childs[index].add);
			root = root.childs[index];
			ceng++;
		}
	}
	public HashMap<String,Integer> getAllWords(){
		return preTraversal(this.root,"");
	}
	private HashMap<String,Integer> preTraversal(Node root, String prefixs){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(root != null){
			if(root.isLeaf == true){
				map.put(prefixs, root.dumpliNum);
			}
			for(int i = 0,length = root.childs.length; i < length; i++){
				if(root.childs[i] != null){
					char ch = (char)(i+'a');
					int s = ch - 'a';
					String num = Integer.toBinaryString(s);
					if(BinaryToHex(num).length()<2){
						address1 = address1+ "0"+BinaryToHex(num);
					}
					else{
					address1 = address1+BinaryToHex(num);
					}
					String tempStr = prefixs+ch;
					map.putAll(preTraversal(root.childs[i],tempStr));

				}
			}
		}
		return map;
	}
	public String isExist(String word){
		return search(this.root, word);
	}
	private String search(Node root, String word){
		char[] chs = word.toLowerCase().toCharArray();
		String address = "";
		for(int i = 0,length = chs.length; i < length; i++){
			int index = chs[i]-'a';
			if(root.childs[index] == null){
				return "";
			}
			String num = Integer.toBinaryString(root.childs[index].id);
			if(BinaryToHex(num).length()<2){
				root.childs[index].add = "0"+BinaryToHex(num);
			}
			else{
			root.childs[index].add = BinaryToHex(num);
			}
			address = address+root.childs[index].add;
			root = root.childs[index];
		}
//		System.out.println(address);
		return address;
	}
	public HashMap<String, Integer> getWordsForPrefix(String prefix){
		return getWordsForPrefix(this.root, prefix);
	}
	private HashMap<String, Integer> getWordsForPrefix(Node root, String prefix){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		char[] chrs = prefix.toLowerCase().toCharArray();
		String address = "";
		for(int i = 0,length = chrs.length; i < length; i++){
			int index = chrs[i]-'a';
			if(root.childs[index] == null){
				return null;
			}
			String num = Integer.toBinaryString(root.childs[index].id);
			if(BinaryToHex(num).length()<2){
				root.childs[index].add = "0"+BinaryToHex(num);
			}
			else{
			root.childs[index].add = BinaryToHex(num);
			}
			address = address+root.childs[index].add;
			//System.out.print(root.childs[index].add);
			root = root.childs[index];
		}
		System.out.print("ǰ׺��ַΪ");
		System.out.println(address);
		return preTraversal(root,prefix);
	}
	public static String BinaryToHex(String s){
		return Long.toHexString(Long.parseLong(s,2));
		}
}



