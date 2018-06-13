package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class HashAlgorithm {
    /**
     *��ʵ�ڵ�
      */
    private static String[] servers = { "1","2", "3","4","5"};
   
   /**
      * ��ʵ����б�
     */
   private static List<String> realNodes = new LinkedList<String>();
   /**
    * ����ڵ�
    */
   private static SortedMap<Integer, String> virtualNodes = 
           new TreeMap<Integer, String>();
    
    /**
      * ����ڵ����Ŀ��һ����ʵ����Ӧ5������ڵ�
    */
     private static final int VIRTUAL_NODES = 5;
   
  static
  {
        // һ̨��������һ����ʵ�ڵ�
         for (int i = 0; i < servers.length; i++)
             realNodes.add(servers[i]);
         
        // ���������ڵ㣬����LinkedListʹ��foreachѭ��Ч�ʻ�Ƚϸ�
         for (String str : realNodes)
         {
             for (int i = 0; i < VIRTUAL_NODES; i++)
             {
               String virtualNodeName = str + "&&����ڵ�" + String.valueOf(i);
                 int hash = getHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
         }
         System.out.println();
   }
   
     /**
      * ʹ��FNV1_32_HASH�㷨�����������Hashֵ
      */
     private static int getHash(String str)
     {
         final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
          hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
       
        // ����������ֵΪ������ȡ�����ֵ
         if (hash < 0)
             hash = Math.abs(hash);
         return hash;
     }
    
     /**
      * �õ�Ӧ��·�ɵ��Ľ��
      */
     public static String getServer(String node)
     {
         // �õ���·�ɵĽ���Hashֵ
         int hash = getHash(node);
         // �õ����ڸ�Hashֵ������Map
         SortedMap<Integer, String> subMap = 
                 virtualNodes.tailMap(hash);
         Integer i=null;
         String virtualNode = null;
         if(subMap==null||subMap.size()==0){
             i=virtualNodes.firstKey();
             virtualNode=virtualNodes.get(i);
         }else{
              i = subMap.firstKey();
              virtualNode= subMap.get(i);
         }
         // ��һ��Key����˳ʱ���ȥ��node������Ǹ����
       
         // ���ض�Ӧ������ڵ����ƣ������ַ�����΢��ȡһ��
         return virtualNode.substring(0, virtualNode.indexOf("&&"));
     }
     
}