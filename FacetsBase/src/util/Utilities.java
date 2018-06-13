package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utilities {
	
	public static String arrToStr(ArrayList<Long> arr) {
		String ids = "";
		
		Iterator<Long> it = arr.iterator();
		
		while(it.hasNext()) {
			Long l = it.next();
			ids += String.valueOf(l) + "|";
		}
		
		return ids;
	}
	
	public static ArrayList<Long> strToArr(String str) {
		ArrayList<Long> arr = new ArrayList<Long>();
		
		String[] tmp = str.split("\\|");
		
		for(int i=0; i<tmp.length; i++) {
			if(tmp[i]!=null||tmp[i]!="") {
				arr.add(Long.valueOf(tmp[i]));
			}
		}
		
		return arr;
	}
	
	public static String mapToStr(Map<String, List<Integer>> map) {
		String str = "";
		
		Iterator<Map.Entry<String, List<Integer>>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, List<Integer>> entry = entries.next();  
		  
			String filename = entry.getKey();
		    List<Integer> list = entry.getValue();
		    
		    Iterator<Integer> it = list.iterator();
		    str += filename + ":";
		    while(it.hasNext()) {
		    	Integer i = it.next();
		    	str += String.valueOf(i) + ",";
		    }
		    str += "|";
		}
		
		return str;
	}
	
	public static Map<String, List<Integer>> strToMap(String str) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		
		String[] strs = str.split("\\|");
		
		for(int i=0; i<strs.length; i++) {
			strs[i] = strs[i].replaceAll("F://", "/").replaceAll("//", "/");
			String filename = strs[i].split(":", 2)[0];
			String[] values = strs[i].split(":", 2)[1].split(",");
			List<Integer> list = new ArrayList<Integer>();
			for(int j=0; j<values.length; j++) {
				list.add(Integer.valueOf(values[j]));
			}
			map.put(filename, list);
		}
		
		return map;
	}
	
	public static boolean isValueInArr(Long id, ArrayList<Long> arr) {
		if(arr.contains(id))
			return true;
		return false;
	}
	
	public static boolean isValueInArr(Integer id, List<Integer> arr) {
		if(arr.contains(id))
			return true;
		return false;
	}
}
