package impl;

import dao.FacetSegmentationDao;

public class FacetSegmentation implements FacetSegmentationDao {

	
	public String FacetSegment(String word) {
		// TODO Auto-generated method stub
		//单词不为空
		if(word == "")
			return "00";
		//判断首字母是否为数字
		char firstAlpha = word.substring(0, 1).charAt(0);
		if(Character.isDigit(firstAlpha))
			return "00";
		
		if((firstAlpha >= 'a' && firstAlpha <= 'm') || (firstAlpha >= 'A' && firstAlpha <= 'M')) {
			return "01";
		}
		else if((firstAlpha >= 'n' && firstAlpha <= 'z') || (firstAlpha >= 'N' && firstAlpha <= 'Z')) {
			return "10";
		}
		else
			return "11";
	}


	public String GetCubeByFacets(String author1, String author2, String author3, String author4, String title1,
			String title2, String title3, String publisher) {
		// TODO Auto-generated method stub
		String result = "";
		
		String _author1Add = FacetSegment(author1);
		String _author2Add = FacetSegment(author2);
		String _author3Add = FacetSegment(author3);
		String _author4Add = FacetSegment(author4);
		String _title1Add = FacetSegment(title1);
		String _title2Add = FacetSegment(title2);
		String _title3Add = FacetSegment(title3);
		String _publisherAdd = FacetSegment(publisher);
		
		result = _author1Add + _author2Add + _author3Add + _author4Add + _title1Add + _title2Add + _title3Add + _publisherAdd;
		
		return result;
	}

}
