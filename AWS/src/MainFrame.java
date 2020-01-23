import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame{

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
	};
	
	Object[][] o2 = {
 		    {"January","0.00","0.00"},    
            {"February","0.00","0.00"}, 
            {"March","0.00","0.00"}, 
            {"April","0.00","0.00"}, 
            {"May","0.00","0.00"}, 
            {"June","0.00","0.00"}, 
            {"July","0.00","0.00"}, 
            {"August","0.00","0.00"}, 
            {"September","0.00","0.00"}, 
            {"October","0.00","0.00"}, 
            {"November","0.00","0.00"}, 
            {"December","0.00","0.00"},
	};
	
	
	
	
	
	String [] data = new String[] {};
	String ownerName;
	String designerName;
	
	
	public void buildFrame(){
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();		
		this.setLayout(grid);
		setTitle("New");
		
		gbc.insets = new Insets(10,10,0,0); 

		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(new JLabel("Landowner:"), gbc);
		

		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.ipadx = 70; 
		gbc.gridx = 3;
		gbc.gridy = 0;		
		this.add(new JTextField(),gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(new JLabel("Designer:"), gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 2;		
		this.add(new JTextField(),gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(new JLabel("Data Source:"), gbc);
				
		 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 4;
		String[] source = {" ","MWPS","NRCS-2008}"};
		JComboBox ds = new JComboBox(source);
		ds.setSelectedIndex(0);
		ds.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					ds.getSelectedIndex();				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(ds, gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.add(new JLabel("Select State:"), gbc);
		
		
		gbc.fill = GridBagConstraints.WEST;
		gbc.gridx = 3;
		gbc.gridy = 6;
		gbc.ipadx = 20; 
		String[] state = {" ","AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID",
							  "IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS",
							  "MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK",
							  "OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV",
							  "WI","WY"};
		JComboBox st = new JComboBox(state);
		st.setSelectedIndex(0);
		st.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					st.getSelectedIndex();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(st, gbc);
		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 8;
		this.add(new JButton("Operating Period Setup"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 10;
		this.add(new JLabel("<html> Click button above to define or <br> modify the operating period(s) </html>"), gbc);
	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 8;
		gbc.ipadx = 20;
		JButton b1 = new JButton("OK");
		b1.addActionListener(new ActionListener() ////After selected the data source, open the climate frame with data;
		{
			public void actionPerformed(ActionEvent e){
				if(ds.getSelectedIndex() == 0){
					System.out.print("Nothing selected"); /////////must select one data source, otherwise, the button doesn't work					
				}
				else if(ds.getSelectedIndex() == 1 && st.getSelectedIndex() != 0){ /////////select the first data source

					try {
						data = InputData.readTextFile("test.txt") ;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(String s : data){
						System.out.print(s);
					}
					ClimateFrame cf = new ClimateFrame();
					cf.buildClimateFrame();
				}
				else if(ds.getSelectedIndex() == 2 && st.getSelectedIndex() != 0){/////////select the second data source
					//String[] data = new String[]{};
					try {
						data = InputData.readTextFile("test1.txt");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(String s : data){
						System.out.print(s);
					}
					ClimateFrame cf = new ClimateFrame();
					cf.buildClimateFrame();
				}
				
			}
			
		});
		this.add(b1, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 10;
		gbc.ipadx = 20;
		this.add(new JButton("Cancel"), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 12;
		gbc.ipadx = 20;
		this.add(new JButton("Help"), gbc);
		
	
		setSize(500,400);
		setPreferredSize(getSize());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	
	
}
