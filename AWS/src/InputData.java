import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;



//import test3.Test3.stationInfo;

public class InputData {

	Workbook workbook = null;

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
		InputStream fis = new FileInputStream(filePath);
		
		if (filePath.toLowerCase().endsWith("xlsx")) {
           workbook = new XSSFWorkbook(fis);
        } else if (filePath.toLowerCase().endsWith("xls")) {
           workbook = new HSSFWorkbook(fis);
        }
	}
	
	public void readSheet(String sheetName) {
		
    	try {    		
    	    Sheet sheet = workbook.getSheet(sheetName);	     
    	    Row row;   	      	     
    	    for(int i = 1; i < sheet.getLastRowNum(); i++ ) {
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
