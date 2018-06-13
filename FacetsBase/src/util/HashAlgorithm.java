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
     *真实节点
      */
    private static String[] servers = { "1","2", "3","4","5"};
   
   /**
      * 真实结点列表
     */
   private static List<String> realNodes = new LinkedList<String>();
   /**
    * 虚拟节点
    */
   private static SortedMap<Integer, String> virtualNodes = 
           new TreeMap<Integer, String>();
    
    /**
      * 虚拟节点的数目，一个真实结点对应5个虚拟节点
    */
     private static final int VIRTUAL_NODES = 5;
   
  static
  {
        // 一台服务器是一个真实节点
         for (int i = 0; i < servers.length; i++)
             realNodes.add(servers[i]);
         
        // 再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
         for (String str : realNodes)
         {
             for (int i = 0; i < VIRTUAL_NODES; i++)
             {
               String virtualNodeName = str + "&&虚拟节点" + String.valueOf(i);
                 int hash = getHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
         }
         System.out.println();
   }
   
     /**
      * 使用FNV1_32_HASH算法计算服务器的Hash值
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
       
        // 如果算出来的值为负数则取其绝对值
         if (hash < 0)
             hash = Math.abs(hash);
         return hash;
     }
    
     /**
      * 得到应当路由到的结点
      */
     public static String getServer(String node)
     {
         // 得到带路由的结点的Hash值
         int hash = getHash(node);
         // 得到大于该Hash值的所有Map
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
         // 第一个Key就是顺时针过去离node最近的那个结点
       
         // 返回对应的虚拟节点名称，这里字符串稍微截取一下
         return virtualNode.substring(0, virtualNode.indexOf("&&"));
     }
     
}