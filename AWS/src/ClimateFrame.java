import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



public class ClimateFrame{
	
	private String[] columnName;
	private Object[][] data;
	private JFrame jf;
	//private GridBagConstraints c;
	
	MyTable mt1 = new MyTable();  // used for download the existing AWM data
	MyTable mt2 = new MyTable();  // used for input data by the custom
	
	JLabel label;
	JTable databaseTable;
	JTable customTable;
	JScrollPane scrollPane;

	String[] s = {" ","Prec(in)","Evap(in)"};
	Object[][] o1 = {
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
	
	// the values in different textfields
	JTextField valueOfPre;
	JTextField valueOfKVAL;
	JTextField valueOfOCV;
	JTextField valueOfLRV;
	JTextField valueOfAna;



	
	public void buildClimateFrame() {		
		
		jf = new JFrame();
		
		BorderLayout border= new BorderLayout();  //split the screen into two parts: 	A. firstLine(north), B.secondPart(center)
		jf.setLayout(border);
		jf.setTitle("Climate Selection");
		
		
		
		// build actionlisterner for button to show whether the action works or not
			
		ActionListener sliceActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		        System.out.println("Selected: " + aButton.getText()); ///show whether the action works or not
		      }
		    };

		
		/***k***
		 * A. build the first part , which including two child panel:  "Select Climate Data Source" and "Options for Evaluating Monthly Net Prec - Evap"
		 ***z***/
		
		JPanel firstPart = new JPanel(); 
		GridBagLayout firstLineLayOut = new GridBagLayout();
		GridBagConstraints gbc1 =  new GridBagConstraints();		
		firstPart.setLayout(firstLineLayOut);
		
		gbc1.insets = new Insets(0,1,0,3);	
		
		//// A.1 : the panel for "Select Climate Data Source"	
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		gbc1.fill = GridBagConstraints.BOTH;
		
		JPanel scds = new JPanel();
		scds.setLayout(new GridLayout(2,1));
		JRadioButton r1 = new JRadioButton("Use AWM Database");
		JRadioButton r2 = new JRadioButton("Enter custom climate data for this job   ");
		ButtonGroup bg1 = new ButtonGroup();			
		bg1.add(r1);bg1.add(r2);
		scds.add(r1);scds.add(r2);
		r1.setSelected(true);		
		r1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					r1.setSelected(true);
					
					valueOfPre.setText("7.6");
					valueOfKVAL.setText("0");
					valueOfOCV.setText("0");
					valueOfLRV.setText("0");
					valueOfAna.setText("6.2");
					scrollPane.setViewportView(databaseTable);				
					System.out.println("aaaaaaaaaaa");					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//r2.addActionListener(sliceActionListener);
		r2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					r2.setSelected(true);					
					valueOfPre.setText("0");
					valueOfKVAL.setText("0");
					valueOfOCV.setText("0");
					valueOfLRV.setText("0");
					valueOfAna.setText("0");
					scrollPane.setViewportView(customTable);
			        System.out.println("bbbbb");			        
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
		scds.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Select Climate Data Source"));
		
		firstPart.add(scds, gbc1);  // add the panel into the firstpart panel

		//// A.2 :  the panel for "Options for Evaluating Monthly Net Prec - Evap"	
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		gbc1.gridwidth = 2;
		
		JPanel mnpe = new JPanel();
		mnpe.setLayout(new GridLayout(3,2));
		JRadioButton r3 = new JRadioButton("If prec - evap < 0 then set net value to 0              ");
		JRadioButton r4 = new JRadioButton("Always set net value to prec-evap");
		JRadioButton r5 = new JRadioButton("Ignore evap value, and use prec. only");
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(r3);bg2.add(r4);bg2.add(r5);
		mnpe.add(r3);mnpe.add(r4);mnpe.add(r5);	
		r3.setSelected(true);
		r3.addActionListener(sliceActionListener);
		r4.addActionListener(sliceActionListener);
		r5.addActionListener(sliceActionListener);
		mnpe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Options for Evaluating Monthly Net Prec - Evap"));	
		
		firstPart.add(mnpe, gbc1);   //add the panel into the firstpart panel
		jf.add(firstPart,border.NORTH);  // add the first part into the frame.
		
	
		/***k***
		 * B. build the second part, which including two child panel: secondLeft and secondRight. The "secondLeft" includes "location", "other", "lagoon loading rates",
		 *    and the "secondRight" includes "table" and "buttons".
		 ***z***/
		
		
		// build the main panel of the second part
		JPanel secondPart = new JPanel();   
		GridBagLayout secondPartLayOut = new GridBagLayout();
		GridBagConstraints gbcTwo =  new GridBagConstraints();		
		secondPart.setLayout(secondPartLayOut);
		
		//// B.L build the panel: secondLeft, including  "location", including two child panel "county" and "station"
		
		gbcTwo.gridx = 0;
		gbcTwo.gridy = 0;
		gbcTwo.fill = GridBagConstraints.BOTH;
		
		
		JPanel secondLeft = new JPanel();   
		GridBagLayout secondLeftLayOut = new GridBagLayout();
		GridBagConstraints gbcTwoLeft =  new GridBagConstraints();		
		secondLeft.setLayout(secondLeftLayOut);
		
		// B.L.1 : the panel for "location"
		gbcTwoLeft.gridx = 0;
		gbcTwoLeft.gridy = 0;
		gbcTwoLeft.fill = GridBagConstraints.BOTH;

		JPanel location = new JPanel();
		GridLayout locLayOut = new GridLayout(2,1);
		location.setLayout(locLayOut);
		
		// B.L.1.1 :the panel for "county"
		JPanel couPanel = new JPanel();
		FlowLayout flo1 = new FlowLayout();
		couPanel.setLayout(flo1);
		JLabel county = new JLabel("Select County:");		
		String listOfCounty[] = {"Allen","Anderson","Chase"};
		JComboBox bCounty = new JComboBox(listOfCounty);
		bCounty.setPreferredSize(new Dimension(230,25));
		bCounty.setSelectedIndex(0);
		bCounty.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					bCounty.getSelectedIndex();				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		couPanel.add(county);couPanel.add(bCounty);
				
		// B.L.1.2 : the panel for "station"
		JPanel staPanel = new JPanel();
		FlowLayout flo2 = new FlowLayout();
		couPanel.setLayout(flo2);
		JLabel station = new JLabel("Select Station:");
		String listOfStation[] = {"Allen","Anderson","Chase"};		
		JComboBox bStation = new JComboBox(listOfStation);
		bStation.setPreferredSize(new Dimension(230,25));
		bStation.setSelectedIndex(0);
		bStation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					bStation.getSelectedIndex();				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		staPanel.add(station);staPanel.add(bStation);

		location.add(couPanel);location.add(staPanel);
		secondLeft.add(location,gbcTwoLeft);   // add "location" to second left.
			
		
		//// B.L.2 : the panel for ***other***
		gbcTwoLeft.fill = GridBagConstraints.HORIZONTAL;     
		gbcTwoLeft.gridx = 0;
		gbcTwoLeft.gridy = 1;
        
        JPanel other = new JPanel();
		JLabel precipitation = new JLabel("25 Yr. -24 Hr. Storm Precipitation:");
		valueOfPre = new JTextField();
		valueOfPre.setPreferredSize(new Dimension(60,25));
		JLabel inches = new JLabel("inches");		
		other.add(precipitation);other.add(valueOfPre);other.add(inches);	
		secondLeft.add(other, gbcTwoLeft);  // add "other" to second left.
        
		
		//// B.L.3 : the panel for "Lagoon Loading Rates:, including three child panels: title, "rational design method" and "NRCS Design Method"

		gbcTwoLeft.gridx = 0;
		gbcTwoLeft.gridy = 2;		
             
		JPanel lagoon = new JPanel();
		lagoon.setLayout(new GridBagLayout());
		GridBagConstraints gbcOfLag = new GridBagConstraints();
		gbcOfLag.gridwidth = GridBagConstraints.REMAINDER;
		gbcOfLag.fill = GridBagConstraints.HORIZONTAL;
		
		// B.L.3.1 : title
		JLabel title = new JLabel("Lagoon Loading Rates:");
		lagoon.add(title, gbcOfLag);
			
		// B.L.3.2 : the panel for "rational design method", including three small parts: "KVAL", "OCV" and "LRV"
		gbcOfLag.insets= new Insets(5,0,0,0);
		JPanel rational = new JPanel();			
		rational.setLayout(new GridLayout(3,1));
		
		// B.L.3.2.1 : the panel for "KVAL"
		JPanel barthKVAL = new JPanel();
		FlowLayout flo3 = new FlowLayout(FlowLayout.LEFT);
		barthKVAL.setLayout(flo3);		
		JLabel kval = new JLabel("Barth KVAL:");
		valueOfKVAL = new JTextField();
		valueOfKVAL.setPreferredSize(new Dimension(65,25));
		barthKVAL.add(kval);
		barthKVAL.add(Box.createHorizontalStrut(65));
		barthKVAL.add(valueOfKVAL);
		rational.add(barthKVAL);
		
		//B.L.3.2.2 : the panel for "OCV"
		JPanel loadRateForOCV = new JPanel();
		FlowLayout flo4 = new FlowLayout(FlowLayout.LEFT);
		loadRateForOCV.setLayout(flo4);	
		JLabel ocv = new JLabel("Load Rate for Odor,OCV:");
		valueOfOCV = new JTextField();
		valueOfOCV.setPreferredSize(new Dimension(65,25));
		JLabel jl1 = new JLabel("lbx VS/cu. ft/day");
		loadRateForOCV.add(ocv); loadRateForOCV.add(valueOfOCV); loadRateForOCV.add(jl1);
		rational.add(loadRateForOCV);
		
		// B.L.3.2.3 : the panel for "LRV"
		JPanel LRVMax = new JPanel();
		FlowLayout flo5 = new FlowLayout(FlowLayout.LEFT);
		LRVMax.setLayout(flo5);	
		JLabel lrv = new JLabel("LRV Max:");
		valueOfLRV = new JTextField();
		valueOfLRV.setPreferredSize(new Dimension(65,25));
		JLabel jl2 = new JLabel("lbx VS/cu. ft/day");
		LRVMax.add(lrv); 
		LRVMax.add(Box.createHorizontalStrut(80));
		LRVMax.add(valueOfLRV); LRVMax.add(jl2);
		rational.add(LRVMax);		
		
		rational.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Rational Design Method"));		
        lagoon.add(rational, gbcOfLag);
		
        // B.L.3.3 : the panel for "NRCS design method"
		JPanel nrcs = new JPanel();
		JLabel alr = new JLabel("Anaerobic Load Rate:");
		JLabel jl3 = new JLabel("lbs VS/1000 cu.ft/day");
		valueOfAna = new JTextField();
		valueOfAna.setPreferredSize(new Dimension(65,25));
		nrcs.add(alr);
		nrcs.add(Box.createHorizontalStrut(10));
		nrcs.add(valueOfAna);nrcs.add(jl3);
		nrcs.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"NRCS Design Method"));
        lagoon.add(nrcs, gbcOfLag);
		
        secondLeft.add(lagoon,gbcTwoLeft);  // add "lagoon" into the second left.
        secondPart.add(secondLeft,gbcTwo);  // add second left into the second part.

		// B.R : the panel secondRight, including "table" and "buttons".
        		
		gbcTwo.gridx = 2;
		gbcTwo.gridy = 0;
		gbcTwo.fill = GridBagConstraints.VERTICAL;
		
		// build the second right panel
		JPanel secondRight = new JPanel();   
		GridBagLayout secondRightLayOut = new GridBagLayout();
		GridBagConstraints gbcTwoRight =  new GridBagConstraints();		
		secondRight.setLayout(secondRightLayOut);
		
		gbcTwoRight.insets = new Insets(0,0,0,10);	
		gbcTwoRight.fill = GridBagConstraints.BOTH;
		gbcTwoRight.anchor = GridBagConstraints.EAST;
		
		// B.R.1 : build the panel for "table"			
		gbcTwoRight.gridx = 0;
		gbcTwoRight.gridy = 0;
		JPanel jtab = new JPanel();
		jtab.setLayout(new BorderLayout());	
				
		databaseTable = mt1.buildMyTable(s, o1);	// the table with data from the AWS
		customTable = mt2.buildMyTable(s, o2);      // the table without data.

		scrollPane = new JScrollPane(databaseTable);	
		scrollPane.setPreferredSize(new Dimension(210,250));
        jtab.add(scrollPane,BorderLayout.CENTER);       
        secondRight.add(jtab, gbcTwoRight);   // add the table into the second right.
		
        gbcTwoRight.insets = new Insets(20,0,0,0);	
        
        // B.R.2 : build the panel for "buttons"
        gbcTwoRight.gridx = 0;
        gbcTwoRight.gridy = 1;
		JPanel jbtn = new JPanel();
		FlowLayout flo6 = new FlowLayout(FlowLayout.RIGHT);
		jbtn.setLayout(flo6);	
		JButton bHelp = new JButton("Help");
		JButton bOK = new JButton("OK");
		
		bOK.addActionListener(new ActionListener() ////After selected the data source, open the climate frame with data;
				{
					public void actionPerformed(ActionEvent e){						
							AnimalFrame af = new AnimalFrame();
							af.buildAnimalFrame();
						}
						
					}
					
				);
		jbtn.add(bHelp);jbtn.add(bOK);
		secondRight.add(jbtn,gbcTwoRight);     // add the buttons into the second right.
		
		secondPart.add(secondRight,gbcTwo);  // add the second right part into the second part.
		
				
        jf.add(secondPart,border.CENTER);  // add the second part into the main frame.

		
		jf.setSize(600,520);
		//setPreferredSize(getSize());
		//jf.setResizable(false);
		jf.setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


}
