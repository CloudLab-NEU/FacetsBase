package impl;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import util.FacetRecord;
import util.NormalRecord;

import dao.SearchEngineUIDao;
import impl.SQLiteMemoryOperation;

public class SearchEngineUI extends JFrame implements SearchEngineUIDao{
	/**
	 * 
	 */
	TrieTreeOpeartion treeOpeartion = new TrieTreeOpeartion();
	
	private static final long serialVersionUID = -8194001490497238447L;
	public String sqlArray[] =  new String[10];
	String selectValue1;
	String selectValue2;
	String selectValue3;
	String selectValue4;
	String selectValue5;
	String selectValue6;
	String selectValue7;
	String selectValue8;
	String selectValue9;
	String selectValue10;
	String selectValue11;
	String selectValue12;
	String selectValue13;
	String selectValue14;
	String selectValue15;
	String selectValue16;
	String selectValue17;
	String selectValue18;
	String textValue1;
	String textValue2;
	String textValue3;
	String textValue4;
	String textValue5;
	String textValue6;
	String textValue7;
	String textValue8;
	String textValue9;
	String textValue10;
	JButton sureButton;
	JComboBox selectBox;
	JTextField text;
	JComboBox selectBox1;
	JComboBox selectBox2;
	JTextField text1 ;
	JComboBox selectBox3;
	JComboBox selectBox4;
	JTextField text2;
	JComboBox selectBox5;
	JComboBox selectBox6 ;
	JTextField text3;
	JComboBox selectBox7;
	JComboBox selectBox8;
	JTextField text4 ;
	JComboBox selectBox9;
	JComboBox selectBox10;
	JTextField text5 ;
	JComboBox selectBox11;
	JComboBox selectBox12;
	JTextField text6 ;
	JComboBox selectBox13;
	JComboBox selectBox14;
	JTextField text7 ;
	JComboBox selectBox15;
	
	JLabel yearLabel;
	JTextField text9 ;
	JLabel typeLabel;
	JComboBox selectBox17;
	
	public SearchEngineUI(){
		super("SearchEngineUI");
		treeOpeartion.BuildTree();
		setLayout(null);
		setBounds(0,0,520,440);
		Container container = getContentPane();
		
		sureButton = new JButton("È·¶¨");
		selectBox = new JComboBox();
		 text = new JTextField();
		 selectBox1= new JComboBox();
		 selectBox2 = new JComboBox();
		 text1 = new JTextField();
		 selectBox3= new JComboBox();
		 selectBox4 = new JComboBox();
		 text2 = new JTextField();
		 selectBox5= new JComboBox();
		 selectBox6 = new JComboBox();
		 text3 = new JTextField();
		 selectBox7= new JComboBox();
		 selectBox8 = new JComboBox();
		 text4 = new JTextField();
		 selectBox9= new JComboBox();
		 selectBox10 = new JComboBox();
		 text5 = new JTextField();
		 selectBox11= new JComboBox();
		 selectBox12 = new JComboBox();
		 text6 = new JTextField();
		 selectBox13= new JComboBox();
		 selectBox14 = new JComboBox();
		 text7 = new JTextField();
		 selectBox15= new JComboBox();

		 yearLabel = new JLabel("year");
		 text9 = new JTextField();
		 typeLabel = new JLabel("type");
		 selectBox17= new JComboBox();
		
		sureButton.setBounds(400, 350, 80, 30);
		
		selectBox.setBounds(20, 20, 80, 30);
		selectBox.addItem("author1");
//		selectBox.addItem("author2");
//		selectBox.addItem("author3");
//		selectBox.addItem("author4");
//		selectBox.addItem("title1");
//		selectBox.addItem("title2");
//		selectBox.addItem("title3");
//		selectBox.addItem("publisher");
//		selectBox.addItem("null");
		
		text.setBounds(100,20,70,30);
		
		selectBox1.setBounds(190, 20, 50, 30);
		selectBox1.addItem("AND");
		selectBox1.addItem("OR");
		
		selectBox2.setBounds(260, 20, 80, 30);
//		selectBox2.addItem("author1");
		selectBox2.addItem("author2");
//		selectBox2.addItem("author3");
//		selectBox2.addItem("author4");
//		selectBox2.addItem("title1");
//		selectBox2.addItem("title2");
//		selectBox2.addItem("title3");
//		selectBox2.addItem("publisher");
//		selectBox2.addItem("null");
		
		text1.setBounds(340,20,70,30);
		
		selectBox3.setBounds(430, 20, 50, 30);
		selectBox3.addItem("AND");
		selectBox3.addItem("OR");
		
		
		selectBox4.setBounds(20, 70, 80, 30);
//		selectBox4.addItem("author1");
//		selectBox4.addItem("author2");
		selectBox4.addItem("author3");
//		selectBox4.addItem("author4");
//		selectBox4.addItem("title1");
//		selectBox4.addItem("title2");
//		selectBox4.addItem("title3");
//		selectBox4.addItem("publisher");
//		selectBox4.addItem("null");
		
		text2.setBounds(100,70,70,30);
		
		selectBox5.setBounds(190, 70, 50, 30);
		selectBox5.addItem("AND");
		selectBox5.addItem("OR");
		
		selectBox6.setBounds(260, 70, 80, 30);
//		selectBox6.addItem("author1");
//		selectBox6.addItem("author2");
//		selectBox6.addItem("author3");
		selectBox6.addItem("author4");
//		selectBox6.addItem("title1");
//		selectBox6.addItem("title2");
//		selectBox6.addItem("title3");
//		selectBox6.addItem("publisher");
//		selectBox6.addItem("null");
		
		text3.setBounds(340,70,70,30);
		
		selectBox7.setBounds(430, 70, 50, 30);
		selectBox7.addItem("AND");
		selectBox7.addItem("OR");
		
		
		selectBox8.setBounds(20, 120, 80, 30);
//		selectBox8.addItem("author1");
//		selectBox8.addItem("author2");
//		selectBox8.addItem("author3");
//		selectBox8.addItem("author4");
		selectBox8.addItem("title1");
//		selectBox8.addItem("title2");
//		selectBox8.addItem("title3");
//		selectBox8.addItem("publisher");
//		selectBox8.addItem("null");
		
		text4.setBounds(100,120,70,30);
		
		selectBox9.setBounds(190, 120, 50, 30);
		selectBox9.addItem("AND");
		selectBox9.addItem("OR");
		
		selectBox10.setBounds(260, 120, 80, 30);
//		selectBox10.addItem("author1");
//		selectBox10.addItem("author2");
//		selectBox10.addItem("author3");
//		selectBox10.addItem("author4");
//		selectBox10.addItem("title1");
		selectBox10.addItem("title2");
//		selectBox10.addItem("title3");
//		selectBox10.addItem("publisher");
//		selectBox10.addItem("null");
		
		text5.setBounds(340,120,70,30);
		
		selectBox11.setBounds(430, 120, 50, 30);
		selectBox11.addItem("AND");
		selectBox11.addItem("OR");
		
		selectBox12.setBounds(20, 170, 80, 30);
//		selectBox12.addItem("author1");
//		selectBox12.addItem("author2");
//		selectBox12.addItem("author3");
//		selectBox12.addItem("author4");
//		selectBox12.addItem("title1");
//		selectBox12.addItem("title2");
		selectBox12.addItem("title3");
//		selectBox12.addItem("publisher");
//		selectBox12.addItem("null");
		
		text6.setBounds(100,170,70,30);
		
		selectBox13.setBounds(190, 170, 50, 30);
		selectBox13.addItem("And");
		selectBox13.addItem("Or");
		
		selectBox14.setBounds(260, 170, 80, 30);
//		selectBox14.addItem("author1");
//		selectBox14.addItem("author2");
//		selectBox14.addItem("author3");
//		selectBox14.addItem("author4");
//		selectBox14.addItem("title1");
//		selectBox14.addItem("title2");
//		selectBox14.addItem("title3");
		selectBox14.addItem("publisher");
//		selectBox14.addItem("null");
		
		text7.setBounds(340,170,70,30);
		
		selectBox15.setBounds(430, 170, 50, 30);
		selectBox15.addItem("AND");
		selectBox15.addItem("OR");
		
		
		yearLabel.setBounds(20, 220, 30, 30);
		
		text9.setBounds(50,220,70,30);
		
		typeLabel.setBounds(20, 270, 30, 30);
		
		selectBox17.setBounds(50, 270, 80, 30);
		selectBox17.addItem("article");
		selectBox17.addItem("inproceedings");
		selectBox17.addItem("proceedings");
		selectBox17.addItem("book");
		selectBox17.addItem("incollection");
		selectBox17.addItem("masterthesis");
		selectBox17.addItem("phdthesis");
		selectBox17.addItem("www");
		selectBox17.addItem("null");
		
		
		
		container.add(sureButton);
		container.add(selectBox);
		container.add(selectBox1);
		container.add(selectBox2);
		container.add(text);
		container.add(text1);
		container.add(selectBox3);
		
		container.add(selectBox4);
		container.add(selectBox5);
		container.add(selectBox6);
		container.add(text2);
		container.add(text3);
		container.add(selectBox7);
		
		container.add(selectBox8);
		container.add(selectBox9);
		container.add(selectBox10);
		container.add(text4);
		container.add(text5);
		container.add(selectBox11);
		
		container.add(selectBox12);
		container.add(selectBox13);
		container.add(selectBox14);
		container.add(text6);
		container.add(text7);
		container.add(selectBox15);
		
	
		
		container.add(yearLabel);
		container.add(text9);
		
		container.add(typeLabel);
		container.add(selectBox17);
		
		sureButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				quiry();
				SQLiteMemoryOperation sqLiteMemoryOperation = new SQLiteMemoryOperation();
				sqLiteMemoryOperation.getConnection();
				SQLiteDiskOperation sqLiteDiskOperation = new SQLiteDiskOperation();
				sqLiteDiskOperation.getConnection();
				sqLiteMemoryOperation.createTable();
				//sqLiteOperation.insert(1, "Carmen", "1", "1", "1", "1", "1", "1", "1", "1", "1");
				
				List<FacetRecord> list = null;
				List<NormalRecord> listNormal = null;
				//list = sqLiteOperation.search(textValue1, textValue2, textValue3, textValue4, textValue5, textValue6, textValue7, textValue8, textValue9, textValue10, "");
				if(textValue1.equals("")){
				textValue1 = "";
			}
			if(textValue2.equals("")){
				textValue2 = "";
			}
			if(textValue3.equals("")){
				textValue3 = "";
			}
			if(textValue4.equals("")){
				textValue4 = "";
			}
			if(textValue5.equals("")){
				textValue5 = "";
			}
			if(textValue6.equals("")){
				textValue6 = "";
			}
			if(textValue7.equals("")){
				textValue7 = "";
			}
			if(textValue8.equals("")){
				textValue8 = "";
			}
			if(textValue9.equals("")){
				textValue9 = "";
			}
			if(selectValue18.equals("null")){
				selectValue18 = "";
			}
//			if(selectValue2 == null){
//				selectValue2 = "";
//			}
//			if(selectValue4 == null){
//				selectValue4 = "";
//			}
//			if(selectValue6 == null){
//				selectValue6 = "";
//			}
//			if(selectValue8 == null){
//				selectValue8 = "";
//			}
//			if(selectValue10 == null){
//				selectValue10 = "";
//			}
//			if(selectValue12 == null){
//				selectValue12 = "";
//			}
//			if(selectValue14 == null){
//				selectValue14 = "";
//			}
//			if(selectValue15 == null){
//				selectValue15 = "";
//			}
//			    sqlArray[0] = textValue1;
//			    sqlArray[1] = textValue2;
//			    sqlArray[3] = textValue3;
//			    sqlArray[4] = textValue4;
//			    sqlArray[5] = textValue5;
//			    sqlArray[6] = textValue6;
//			    sqlArray[7] = textValue7;
//			    sqlArray[8] = textValue8;
//			    sqlArray[9] = textValue9;
//			    sqlArray[10] = selectValue18;
			if (treeOpeartion.Exist(textValue1)&&treeOpeartion.Exist(textValue2)&&treeOpeartion.Exist(textValue3)
					&&treeOpeartion.Exist(textValue4)&&treeOpeartion.Exist(textValue5)&&treeOpeartion.Exist(textValue6)
					&&treeOpeartion.Exist(textValue7)&&treeOpeartion.Exist(textValue8)&&treeOpeartion.Exist(textValue9)
					&&treeOpeartion.Exist(selectValue18)){
		
//				list = sqLiteOperation.search(textValue1, textValue2, textValue3, textValue4, textValue5, textValue6, textValue7, textValue8, textValue9, selectValue18,selectValue2,
//						selectValue4,selectValue6,selectValue8,selectValue10,selectValue12,selectValue14,selectValue15);
//			
				for (FacetRecord facetRecord : list) {
					System.out.println(facetRecord.toString());
				}
			}
			else{
				listNormal = sqLiteDiskOperation.search(textValue1, textValue5, textValue8, textValue9, selectValue18);
				for (NormalRecord normalRecord : listNormal) {
					System.out.println(normalRecord.toString());
				}
			}
			//sqLiteOperation.disConnection();
			sqLiteDiskOperation.disConnection();
			}
			
			
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}
	public void quiry(){
//		selectValue1 = selectBox.getSelectedItem().toString();
		selectValue2 = selectBox1.getSelectedItem().toString();
//		selectValue3 = selectBox2.getSelectedItem().toString();
		selectValue4 = selectBox3.getSelectedItem().toString();
//		selectValue5 = selectBox4.getSelectedItem().toString();
		selectValue6 = selectBox5.getSelectedItem().toString();
//		selectValue7 = selectBox6.getSelectedItem().toString();
		selectValue8 = selectBox7.getSelectedItem().toString();
//		selectValue9 = selectBox8.getSelectedItem().toString();
		selectValue10 = selectBox9.getSelectedItem().toString();
//		selectValue11 = selectBox10.getSelectedItem().toString();
		selectValue12 = selectBox11.getSelectedItem().toString();
//		selectValue13 = selectBox12.getSelectedItem().toString();
		selectValue14 = selectBox13.getSelectedItem().toString();
		selectValue15 = selectBox15.getSelectedItem().toString();
		
		selectValue18 = selectBox17.getSelectedItem().toString();
		textValue1 = text.getText().toString();
		textValue2 = text1.getText().toString();
		textValue3 = text2.getText().toString();
		textValue4 = text3.getText().toString();
		textValue5 = text4.getText().toString();
		textValue6 = text5.getText().toString();
		textValue7 = text6.getText().toString();
		textValue8 = text7.getText().toString();
//		textValue9 = text8.getText().toString();
		textValue9 = text9.getText().toString();

//		if(textValue1.equals("")){
//			textValue1 = null;
//		}
//		if(textValue2.equals("")){
//			textValue2 = null;
//		}
//		if(textValue3.equals("")){
//			textValue3 = null;
//		}
//		if(textValue4.equals("")){
//			textValue4 = null;
//		}
//		if(textValue5.equals("")){
//			textValue5 = null;
//		}
//		if(textValue6.equals("")){
//			textValue6 = null;
//		}
//		if(textValue7.equals("")){
//			textValue4 = null;
//		}
//		if(textValue8.equals("")){
//			textValue5 = null;
//		}
//		if(textValue9.equals("")){
//			textValue6 = null;
//		}
	}
//	public String getTextValue1(){
//		return textValue1;
//	}
//	public String getTextValue2(){
//		return textValue2;
//	}
//	public String getTextValue3(){
//		return textValue3;
//	}
//	public String getTextValue4(){
//		return textValue4;
//	}
//	public String getTextValue5(){
//		return textValue5;
//	}
//	public String getTextValue6(){
//		return textValue6;
//	}
//	public String getTextValue7(){
//		return textValue7;
//	}
//	public String getTextValue8(){
//		return textValue8;
//	}
//	public String getTextValue9(){
//		return textValue9;
//	}
//	public String getTextValue10(){
//		return textValue10;
//	}
}