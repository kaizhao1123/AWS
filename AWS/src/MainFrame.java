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

	String [] data = new String[] {};
	String ownerName;
	String designerName;
	
	
	public void buildFrame(){
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();		
		this.setLayout(grid);
		setTitle("New");
		
		// set the gap of each component
		gbc.insets = new Insets(10,10,0,0); 
		
		// add *** Landowner ***
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(new JLabel("Landowner:"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 0;		
		this.add(new JTextField(),gbc);
		
		// add *** Designer ***		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(new JLabel("Designer:"), gbc);
				
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 2;		
		this.add(new JTextField(),gbc);
		
		// add *** Data Source ***
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
		
		// add  ***Select State ***
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
		
		// add  *** Operating Period Setup ***
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 8;
		this.add(new JButton("Operating Period Setup"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 10;
		this.add(new JLabel("<html> Click button above to define or <br> modify the operating period(s) </html>"), gbc);
	
		// add  *** OK ***
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
		
		// add  *** Cancel ***
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 10;
		gbc.ipadx = 20;
		this.add(new JButton("Cancel"), gbc);
		
		// add  *** Help ***
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		gbc.gridy = 12;
		gbc.ipadx = 20;
		this.add(new JButton("Help"), gbc);
		
		// set this frame ***
		setSize(500,400);
		setPreferredSize(getSize());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	
	
}
