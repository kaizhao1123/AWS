import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;



public class MyTable implements TableModelListener{

	JTable ntable;
	TableModel model;
	
	String[] columnNamess;
	Object[][] dataa;
	Color cc = Color.lightGray;
	
	
	
	
	public JTable buildMyTable(String[] s, Object[][] o) {
		/*
		String[] s = {" ","Prec(in)","Evap(in)"};
		Object[][] o = {
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
		
		columnNamess = s;
		dataa = o;
		
		model = new TableModel();
		model.buildModel(columnNamess,dataa);
		  
	    model.addTableModelListener(this);
	    model.addRow(model.getEachSum());
		
		
		ntable = new JTable(model);
		
		
		int rowcount = ntable.getRowCount();
		int colcount = ntable.getColumnCount();
		//setColor(0,rowcount,1,2, Color.yellow);
		
		//setColor(rowcount,rowcount,1,colcount,Color.pink);	
		
		
		//model.addRow(getEachSum());	
		model.getColumnClass(0);
		
		for(int i = 1; i < colcount; i ++) {
			
			setColor(rowcount-1,i,Color.pink);
		}
		
		
		
		ntable.setVisible(true);
		ntable.setSize(100,100);
		return ntable;
	}

	private void setColor(int indexr, int indexcol, Color ncolor){
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){				
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus,int row,int column){
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					if(row == indexr && column == indexcol) {
						c.setBackground(ncolor);
					}
					
					/*
					if(row >= indexr && row <= indexendr && column >= indexcol&& column <= indexendcol){
						setBackground(ncolor);
						//cc = ncolor;
					}
					else if(column == 0) {
						setBackground(null);
					}
					else 
						setBackground(cc);*/

					
					return c;
				}
			};	
			/*
			for(int i = 0; i < indexendcol ; i++) {
				ntable.setDefaultRenderer(ntable.getColumnClass(i), tcr);
				
			}	*/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	private double getSum(int column) {
		
	    double sum1 = 0.0;
	    for(int i = 0; i < ntable.getRowCount(); i++) {
	        String ddd = (String) ntable.getModel().getValueAt(i, column);	
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
	
	private String[] getEachSum() {
		String [] eachSum = new String [ntable.getColumnCount()];
		eachSum[0] = "Total";
		for(int i = 1; i < ntable.getColumnCount(); i++) {
			double num = getSum(i);
			if(num == 0.0) {
				eachSum[i] = "N/A";
			}
			else
				eachSum[i] = Double.toString(getSum(i));			
		}
		return eachSum;
	}
	*/

	@Override
    public void tableChanged(TableModelEvent e) {
 		
        int col = e.getColumn();      
        model.mySetValueAt(model.getNewSum(col), model.getRowCount()-1, col);       
        ntable.repaint();
    }
}
