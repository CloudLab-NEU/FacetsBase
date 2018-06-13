package test;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.SearchEngineUIDao;

import impl.*;
import util.*;


public class WriteAndSearch implements Runnable{
	
	private String filename = "";
	private int id = 0;
	private String memory = "";
	private String disk = "";
	private int tableid = 0;
	private int startid = 0;
	
	public WriteAndSearch(String filename, String memory, String disk, int id) {
		this.filename = filename;
		this.id = id;
		this.memory = memory;
		this.disk = disk;
	}
	
	public WriteAndSearch(String filename, String memory, String disk, int id, int tableid) {
		this.filename = filename;
		this.id = id;
		this.memory = memory;
		this.disk = disk;
		this.tableid = tableid;
	}
	
	public WriteAndSearch(String filename, String memory, String disk, int id, int tableid, int startid) {
		this.filename = filename;
		this.id = id;
		this.memory = memory;
		this.disk = disk;
		this.tableid = tableid;
		this.startid= startid;
	}
	
	public void setFileName(String filename) {
		this.filename = filename;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public void run() {	
//		String filename = "F:/software/dblp/new/dblp1997.xml";
		MyParser.Read(this.filename, this.memory, this.disk, this.id, this.tableid, this.startid);
//		MyParser.Read(this.filename, this.memory, this.disk, this.id);
	}
//	public static void main(String[] args){
//		String filename = "F:/software/dblp/new/dblp1997.xml";
//		MyParser.Read(filename);
//	}
	
	
}