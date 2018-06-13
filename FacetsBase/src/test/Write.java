package test;

public class Write {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//("xml路径","内存数据库名","外村数据库名","id","tableid","startid")
//		Thread thread = new Thread(new WriteAndSearch("F:\\eclipse-workspace\\FacetBaseFinal-version2\\XMLsource\\dblp.xml",
		Thread thread = new Thread(new WriteAndSearch("F:/software/dblp/change/dblp39.xml",
				"db/memory/memory.db",
				"db/disk/disk.db",
				126067, 0, 0));
		thread.start();
		
//		Thread thread = new Thread(new WriteAndSearch("F:/software/dblp/change/dblp0.xml",
//				"db/memory/memory0.db",
//				"db/disk/disk0.db",
//				0));
//		thread.start();
		
//		for(int i=0; i<3; i++) {
//			Thread thread = new Thread(new WriteAndSearch("F:/software/dblp/change/dblp" + i + ".xml",
////					"db/memory/memory.db",
////					"db/disk/disk.db",
//					"db/memory/memory"+ i +".db",
//					"db/disk/disk" + i + ".db",
//					i*500, i));
//			thread.start();
//			
//		}
		
	}

}
