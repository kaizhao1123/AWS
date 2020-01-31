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
	private GridBagConstraints c;
	
	MyTable mt1 = new MyTable();  // used for download the existing AWM data
	MyTable mt2 = new MyTable();  // used for input data by the custom
	
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
		
		GridBagLayout grid = new GridBagLayout();
		c = new GridBagConstraints();		
		jf.setLayout(grid);
		jf.setTitle("Climate Selection");
		
		// build actionlisterner for button
		ActionListener sliceActionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		        System.out.println("Selected: " + aButton.getText()); ///show whether the action works or not
		      }
		    };

		
		c.insets = new Insets(10,10,10,10); 
		
		//// the panel for "Select Climate Data Source"	
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		JPanel scds = new JPanel();
		scds.setPreferredSize(new Dimension(300,100));
		scds.setLayout(new GridLayout(2,1));
		JRadioButton r1 = new JRadioButton("Use AWM Database");
		JRadioButton r2 = new JRadioButton("Enter custom climate data for this job");
		ButtonGroup bg1 = new ButtonGroup();			
		bg1.add(r1);bg1.add(r2);
		scds.add(r1);scds.add(r2);
		r1.setSelected(true);
		//r1.addActionListener(sliceActionListener);		
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
		jf.add(scds,c);
		
		
		
		//// the panel for "Options for Evaluating Monthly Net Prec - Evap"
		c.weightx = 0;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		JPanel mnpe = new JPanel();
		mnpe.setLayout(new GridLayout(3,2));
		JRadioButton r3 = new JRadioButton("If prec - evap < 0 then set net value to 0       ");
		JRadioButton r4 = new JRadioButton("Always set net value to prec-evap");
		JRadioButton r5 = new JRadioButton("Ignore evap value, and use prec. only");
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(r3);bg2.add(r4);bg2.add(r5);
		mnpe.add(r3);mnpe.add(r4);mnpe.add(r5);	
		r3.setSelected(true);
		r3.addActionListener(sliceActionListener);
		r4.addActionListener(sliceActionListener);
		r5.addActionListener(sliceActionListener);
		mnpe.setPreferredSize(new Dimension(300,100));
		mnpe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Options for Evaluating Monthly Net Prec - Evap"));
		jf.add(mnpe,c);
		
		//// the panel for ***place***
		c.gridwidth = 1;
		//c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 2;
		JPanel place = new JPanel();
		place.setLayout(new GridLayout(2,2));
		JLabel county = new JLabel("Select County:");
		JLabel station = new JLabel("Select Station:");
		String listOfCounty[] = {"Allen","Anderson","Chase"};
		String listOfStation[] = {"Allen","Anderson","Chase"};
		JComboBox bCounty = new JComboBox(listOfCounty);
		JComboBox bStation = new JComboBox(listOfStation);
		place.add(county);place.add(bCounty);
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
		place.add(station);place.add(bStation);
		place.setPreferredSize(new Dimension(300,50));
		jf.add(place,c);
		
		////the ***table***
		
		c.gridwidth = 2;
		c.gridheight = 7;
		c.gridx = 1;
		c.gridy = 1;	
		
		JPanel jtab = new JPanel();
		jtab.setLayout(new BorderLayout());	
				
		databaseTable = mt1.buildMyTable(s, o1);	
		customTable = mt2.buildMyTable(s, o2);

		scrollPane = new JScrollPane(databaseTable);
	
        jtab.add(scrollPane,BorderLayout.CENTER);
        jf.add(jtab,c);
		//AddTable(s, o);
        
        System.out.println("ccccccccc");
		
        ////the panel for ***other***
        c.gridwidth = 1;
        c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 4;
        JPanel other = new JPanel();
		JLabel precipitation = new JLabel("25 Yr. -24 Hr. Storm Precipitation:");
		valueOfPre = new JTextField();
		valueOfPre.setPreferredSize(new Dimension(50,20));
		JLabel inches = new JLabel("inches");		
		other.add(precipitation);other.add(valueOfPre);other.add(inches);
		other.setPreferredSize(new Dimension(300,50));
        jf.add(other,c);
        
		//// the panel for ****rational design method****
        //c.gridwidth = 3;
        //c.gridheight = 2;
        c.gridx = 0;
		c.gridy = 6;		
		JPanel rational = new JPanel();
		rational.setLayout(new GridLayout(3,3));
		JLabel kval = new JLabel("Barth KVAL:");
		JLabel ocv = new JLabel("Load Rate for Odor,OCV:");
		JLabel lrv = new JLabel("LRV Max:");
		JLabel jl1 = new JLabel("lbx VS/cu.ft/day");
		JLabel jl2 = new JLabel("lbx VS/cu.ft/day");
		valueOfKVAL = new JTextField();
		valueOfOCV = new JTextField();
		valueOfLRV = new JTextField();
		valueOfKVAL.setPreferredSize(new Dimension(50,20));
		valueOfOCV.setPreferredSize(new Dimension(50,20));
		valueOfLRV.setPreferredSize(new Dimension(50,20));

		rational.add(kval);rational.add(valueOfKVAL);rational.add(new JLabel(""));
		rational.add(ocv);rational.add(valueOfOCV);rational.add(jl1);
		rational.add(lrv);rational.add(valueOfLRV);rational.add(jl2);
		rational.setPreferredSize(new Dimension(300,80));
		rational.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Rational Design Method"));
        jf.add(rational,c);
        
		//// the panel for ***NRCS Design Method***
        //c.gridwidth = 3;
        c.gridx = 0;
		c.gridy = 7;
		
		JPanel nrcs = new JPanel();
		nrcs.setLayout(new GridLayout(1,3));
		JLabel alr = new JLabel("Anaerobic Load Rate:");
		JLabel jl3 = new JLabel("lbs VS/1000 cu.ft/day");
		valueOfAna = new JTextField();
		valueOfAna.setPreferredSize(new Dimension(50,20));
		nrcs.add(alr);nrcs.add(valueOfAna);nrcs.add(jl3);
		nrcs.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"NRCS Design Method"));
        jf.add(nrcs,c);
        
		//// the panel for ***button***
        c.gridx = 1;
		c.gridy = 8;
		JPanel jbtn = new JPanel();
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
		jbtn.add(bHelp);
		jbtn.add(bOK);		
        jf.add(jbtn,c);
 
		
		jf.setSize(600,520);
		//setPreferredSize(getSize());
		//jf.setResizable(false);
		jf.setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


}
