
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class MainFrame extends JFrame{
	
	InputData excelData;
	ArrayList<InputData.stationInfo> stateList;
	
	String ownerName;
	String designerName;
			
	public void buildFrame(InputData data) throws IOException{
		
		excelData = data;   	
    	excelData.readSheet("Climate");

    	
    	/***k***
    	 * Get all state names from the excelData, and to show in "select state:"
    	 ***z***/
    	HashSet allStateNames = new HashSet();
    	allStateNames.add(" ");
    	for(InputData.stationInfo element : excelData.allData) {  		
    		allStateNames.add(element.state);   		
    	}

	
		/***k***
		 * Set up the layout and add components into it
		 ***z***/
				
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
		
		gbc.gridx = 3;
		gbc.gridy = 0;		
		this.add(new JTextField(),gbc);
		
		// add *** Designer ***		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(new JLabel("Designer:"), gbc);
				
		gbc.gridx = 3;
		gbc.gridy = 2;		
		this.add(new JTextField(),gbc);
		
		// add *** Data Source ***
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(new JLabel("Data Source:"), gbc);
						 
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
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.add(new JLabel("Select State:"), gbc);
				
		gbc.fill = GridBagConstraints.WEST;
		gbc.gridx = 3;
		gbc.gridy = 6;
		gbc.ipadx = 20; 
		
		String[] state = new String[allStateNames.size()];
		allStateNames.toArray(state);
		Arrays.sort(state);		
		JComboBox st = new JComboBox(state);
		st.setSelectedIndex(0);
		st.addActionListener(new ActionListener()                //state listener: select different state name to get corresponding data.
		{
			public void actionPerformed(ActionEvent e){
				try {
					int index = st.getSelectedIndex();
					String stateName = state[index];
					stateList = excelData.filterByState(stateName, excelData.allData);
					//System.out.println(stateList.size());					
				} catch (Exception e1) {
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
		
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 10;
		this.add(new JLabel("<html> Click button above to define or <br> modify the operating period(s) </html>"), gbc);
	
		// add  *** OK ***
		gbc.gridx = 5;
		gbc.gridy = 8;
		gbc.ipadx = 20;
		JButton okBt = new JButton("OK");
		okBt.addActionListener(new ActionListener()                   //After selected the data source, open the climate frame with data;
		{
			public void actionPerformed(ActionEvent e){
				if(ds.getSelectedIndex() == 0){
					System.out.print("Nothing selected");            //must select one data source, otherwise, the button doesn't work					
				}
				else if( (ds.getSelectedIndex() == 1 || ds.getSelectedIndex() == 2) && st.getSelectedIndex() != 0){     //select the first or second data source

					try {
						ClimateFrame cf = new ClimateFrame();
						cf.buildClimateFrame(stateList);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}					
				}			
			}			
		});
		this.add(okBt, gbc);
		
		// add  *** Cancel ***
		gbc.gridx = 5;
		gbc.gridy = 10;
		gbc.ipadx = 20;
		JButton cancelBt = new JButton("Cancel");
		
		cancelBt.addActionListener(new ActionListener()                   //After selected the data source, open the climate frame with data;
				{
					public void actionPerformed(ActionEvent e){
						dispose();
						//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}			
				});
		this.add(cancelBt, gbc);
		
		// add  *** Help ***
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
