package util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BTree<Key extends Comparable<Key>, Value> 
{
  
  private static final int M = 6;
  private String add;
  private Node root;    // root of the B-tree
  private int height;   // height of the B-tree
  private int n;      // number of key-value pairs in the B-tree
 
  // helper B-tree node data type
  private static final class Node 
  {
    private int m;               // number of children
    private Entry[] children = new Entry[M];  // the array of children
 
    // create a node with k children
    private Node(int k) 
    {
      m = k;
    }
  }

  private static class Entry 
  {
    private Comparable key;
    private Object val;
    private Node next;   // helper field to iterate over array entries
    
    public Entry(Comparable key, Object val, Node next) 
    {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }
 

  public BTree() 
  {
    root = new Node(0);
  }
 
 
  public boolean isEmpty() 
  {
    return size() == 0;
  }

  public int size() 
  {
    return n;
  }
 

  public int height() 
  {
    return height;
  }
 
 
  public Value get(Key key) 
  {
    if (key == null) 
    {
      throw new NullPointerException("key must not be null");
    }
    return search(root, key, height);
  }
 

  private Value search(Node x, Key key, int ht) 
  {
    Entry[] children = x.children;
 
    // external node到最底层叶子结点，遍历
    if (ht == 0) 
    {
      for (int j = 0; j < x.m; j++)       
      {
        if (eq(key, children[j].key)) 
        {
        	StringBuilder s = new StringBuilder();
        	s.append(children[j].val);
        	add = s.toString();
        //	System.out.println("a"+add);
          return (Value) children[j].val;
        }
      }
    }
 
 
    else
    {
      for (int j = 0; j < x.m; j++) 
      {
        if (j+1 == x.m || less(key, children[j+1].key))
        {
          return search(children[j].next, key, ht-1);
        }
      }
    }
    return null;
  }
 

  public void put(Key key, int val) 
  {
    if (key == null) 
    {
      throw new NullPointerException("key must not be null");
    }
    Node u = insert(root, key, val, height); //分裂后生成的右结点
    n++;
    if (u == null) 
    {
      return;
    }
 
    // need to split root重组root
    Node t = new Node(2);
    t.children[0] = new Entry(root.children[0].key, null, root);
    t.children[1] = new Entry(u.children[0].key, null, u);
    root = t;
    height++;
  }
 
  private Node insert(Node h, Key key, int val, int ht) 
  {
    int j;
    Entry t = new Entry(key, val, null);
 
    // external node外部结点，也是叶子结点，在树的最底层，存的是内容value
    if (ht == 0) 
    {
      for (j = 0; j < h.m; j++) 
      {
        if (less(key, h.children[j].key)) 
        {
          break;
        }
      }
    }
 
    // internal node内部结点，存的是next地址
    else
    {
      for (j = 0; j < h.m; j++) 
      {
        if ((j+1 == h.m) || less(key, h.children[j+1].key)) 
        {
          Node u = insert(h.children[j++].next, key, val, ht-1);
          if (u == null) 
          {
            return null;
          }
          t.key = u.children[0].key;
          t.next = u;
          break;
        }
      }
    }
 
    for (int i = h.m; i > j; i--)
    {
      h.children[i] = h.children[i-1];
    }
    h.children[j] = t;
    h.m++;
    if (h.m < M) 
    {
      return null;
    }
    else    
    {  //分裂结点
      return split(h);
    }
  }
 
  // split node in half
  private Node split(Node h) 
  {
    Node t = new Node(M/2);
    h.m = M/2;
    for (int j = 0; j < M/2; j++)
    {
      t.children[j] = h.children[M/2+j];     
    }
    return t;  
  }
 
  public String toString() 
  {
    return toString(root, height, "") + "\n";
  }
 
  private String toString(Node h, int ht, String indent) 
  {
    StringBuilder s = new StringBuilder();
    Entry[] children = h.children;
 
    if (ht == 0) 
    {
      for (int j = 0; j < h.m; j++) 
      {
        s.append(indent + children[j].key + " " + children[j].val + "\n");
      }
    }
    else
    {
      for (int j = 0; j < h.m; j++) 
      {
        if (j > 0) 
        {
          s.append(indent + "(" + children[j].key + ")\n");
        }
        s.append(toString(children[j].next, ht-1, indent + "   "));
      }
    }
    return s.toString();
  }
 
 public String getAdd(){
//	 System.out.println("add"+add);
	return add;
	 
 }
  // comparison functions - make Comparable instead of Key to avoid casts
  private boolean less(Comparable k1, Comparable k2) 
  {
    return k1.compareTo(k2) < 0;
  }
 
  private boolean eq(Comparable k1, Comparable k2) 
  {
    return k1.compareTo(k2) == 0;
  }
 
 
  /**
   * Unit tests the {@code BTree} data type.
   *
   * @param args the command-line arguments
   */
 
}