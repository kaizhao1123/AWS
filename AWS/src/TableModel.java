
import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableModel extends AbstractTableModel{
	

	/*
     String[] n = {" ","Prec(in)","Evap(in)"};
	 Object[][] p = {
		 		    {"January","1.20","1.41"},    
		            {"February","1.22","1.41"}, 
		            {"March","3.13","2.82"}, 
		            {"April","3.38","4.23"}, 
		            {"May","4.82","5.64"}, 
		            {"June","5.44","6.58"}, 
		            {"July","3.90","7.05"}, 
		            {"August","4.17","6.58"}, 
		            {"September","4.81","4.70"}, 
		            {"October","3.79","3.29"}, 
		            {"November","2.58","1.88"}, 
		            {"December","1.48","1.41"},
		};*/
     
	 String[] n;
	 Object[][] p;
	 
	 public void buildModel(String[] s, Object[][] o) {
		 n = s;
		 p = o;
	 }

	 
     public void addRow(String[] s){
   	 
    	 int l = p.length + 1 ;
    	 int cl = n.length;

    	 Object[][] np = new Object[l][cl];   	   	 
    	 int npl = np.length;
    	 
    	 for(int i = 0; i < p.length; i ++) {
    		 for (int j = 0 ; j < n.length; j ++){
    			 np[i][j] = p[i][j];
    		 }
    	 }
 	 
    	 for(int i = 0; i < s.length; i ++) {
    		 np[l-1][i] = s[i];
    	 }  	
    	 p = np;
    	 
     }
     
     @Override
     public int getRowCount() {
         return p.length;
     }

     @Override
     public int getColumnCount() {
         return n.length;
     }

     @Override
     public Object getValueAt(int row, int col) {
         return p[row][col];
     }

     @Override
     public String getColumnName(int col) {
         return n[col];
     }

     @Override
     public Class<?> getColumnClass(int c) {
         return getValueAt(0, c).getClass();
     }

     @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
         
         return true;
     }
		
     
     @Override
     public void setValueAt(Object value, int row, int col) {
         p[row][col] = value;
         fireTableCellUpdated(row, col);
     }

     public void mySetValueAt(Object value, int row, int col) {
         p[row][col] = value;
     }
     
     public double getSum(int column) {	
	    double sum1 = 0.0;
	    for(int i = 0; i < getRowCount(); i++) {
	        String ddd = (String) getValueAt(i, column);	
	        for(int j = 0; j < ddd.length(); j++) {
	        	if(!Character.isDigit(ddd.charAt(j)) && ddd.charAt(j) != '.') {
	        		return 0.0;
	        	}
	        }
	        Double val = Double.parseDouble(ddd);
	        sum1 += val;	       
	    }	   
	    DecimalFormat df = new DecimalFormat("0.00");	    
        return Double.parseDouble(df.format(sum1));	 
	 }
     
     public double getNewSum(int column) {	
 	    double sum1 = 0.0;
 	    for(int i = 0; i < getRowCount() - 1 ; i++) {
 	        String ddd = (String) getValueAt(i, column);		        
 	        Double val = Double.parseDouble(ddd);
 	        sum1 += val;	       
 	    }	   
 	    DecimalFormat df = new DecimalFormat("0.00");	    
         return Double.parseDouble(df.format(sum1));	 
 	 }
	
     public String[] getEachSum() {
		String [] eachSum = new String [getColumnCount()];
		eachSum[0] = "Total";
		for(int i = 1; i < getColumnCount(); i++) {
			double num = getSum(i);
			if(num == 0.0) {
				eachSum[i] = "N/A";
			}
			else
				eachSum[i] = Double.toString(getSum(i));			
		}
		return eachSum;
	}


}
