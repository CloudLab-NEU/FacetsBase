package impl;

import util.FacetRecord;

public class SaveADDFile {
	String address = null;
	public SaveADDFile(FacetRecord facetRecord){
		address = facetRecord.getAuthor1().substring(0, 2)+facetRecord.getYear()+facetRecord.getType()+facetRecord.getPublisher().substring(0,2)
				+facetRecord.getTitle1().substring(0, 2);
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}

}