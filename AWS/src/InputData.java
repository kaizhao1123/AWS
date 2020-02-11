import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import test3.Test3.stationInfo;

public class InputData {

	XSSFWorkbook xwb;
	String filePath;
	ArrayList<stationInfo> allData = new ArrayList<>();
	
	class stationInfo{
		String state;
		String county;
		String name; 
		String[] data;
		
		stationInfo(String s, String c, String n, String[] d ){
			state = s;
			county = c;
			name = n;
			data = d;
		}
	}
	
	public InputData(String path) throws IOException {
		filePath = path;
		xwb = new XSSFWorkbook(filePath);
	}
	
	public void readSheet(String sheetName) {
		
    	try {    		
    	    XSSFSheet sheet = xwb.getSheet(sheetName);	     
    	    XSSFRow row;
    	    XSSFCell cell;  	     
    	     for(int i = 0; i < sheet.getLastRowNum(); i++ ) {
    	    	row = sheet.getRow(i); 	
    	    	stationInfo element;
    	    	String state = row.getCell(0).toString();
	    		String county = row.getCell(1).toString();
	    		String name = row.getCell(2).toString();
	    		String[] data = new String[row.getLastCellNum()-3];
    	    	for(int j = 3; j <row.getLastCellNum(); j ++) {
    	    		data[j-3] = row.getCell(j).toString();	    		
    	    	}
    	    	element = new stationInfo(state, county, name, data);
    	    	allData.add(element);   	    	 
    	     }		 
    	}catch(Exception e) {
    		 e.printStackTrace();
    	}  	
	}
	
	public ArrayList<stationInfo> filterByState(String stateName, ArrayList<stationInfo> upperLevelData) {
		ArrayList<stationInfo> list = new ArrayList<>();
		for(stationInfo ele : upperLevelData) {
			if(ele.state.equals(stateName)) {
				list.add(ele);
			}
		}		
		return list;
	}
	
	public ArrayList<stationInfo> filterByCounty(String countyName, ArrayList<stationInfo> upperLevelData) {
		ArrayList<stationInfo> list = new ArrayList<>();
		for(stationInfo ele : upperLevelData) {
			if(ele.county.equals(countyName)) {
				list.add(ele);
			}
		}		
		return list;
	}

	
}
