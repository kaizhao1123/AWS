import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AnimalFrame extends JFrame{
	
	
	String[] columnNamess = {"<html> Animal <br> (species) </html>",
									"<html>Animal <br> (type) </html>",
									"<html>Weight <br> (lbs) </html>",
									"<html>Manure <br> (cu.ft/day/AU) </html>",
									"<html> VS <br> (lbs/day/AU) </html>",
									"<html> TS <br> (lbs/day/AU) </html>",
									"<html> Manure <br> (cu.ft/day) </html>",
									"<html> VS <br> (lbs/day) </html>",
									"<html> TS <br> (lbs/day) </html>",
									"<html>Manure <br> (lbs/day) </html>"};
	Object[][] dataa = {{"Feeder Calf","Beef","0","0","0","0","0","0","0","0","0"},
						{"Feeder Beef","Beef","1","2","3","4","5","0","0","0","0"},
						{"Feeder Beef","Beef","1","2","3","4","5","0","0","0","0"},
						{"Feeder Beef","Beef","1","2","3","4","5","0","0","0","0"}}; 

	public void buildAnimalFrame() {

		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();		
		this.setLayout(grid);
		setTitle("Animal");
		
		gbc.insets = new Insets(10,10,0,0); 
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel ("Enter quantity and average weight of animals: ");
		JButton bSelect = new JButton("Select Animal");
		JButton bNew = new JButton("New Animal");
		JButton bDelete = new JButton("Delete Selected Row");
		p1.add(l1);p1.add(bSelect);p1.add(bNew);p1.add(bDelete);
				
		this.add(p1, gbc);
		
		
		
		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		//gbc.ipadx = 500;


		JPanel jtab = new JPanel();
		jtab.setLayout(new BorderLayout());	
		jtab.setPreferredSize(new Dimension(600,200));
		MyTable mm = new MyTable();	
		
		//mm.getTableHeader().setPreferredSize(new Dimension(50, 50));
		JScrollPane scrollPane = new JScrollPane(mm.buildMyTable(columnNamess, dataa));       
		jtab.add(scrollPane);
        this.add(jtab,gbc);
        
		
        gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;	
		//gbc.ipadx = 0;
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(3,1));
		JLabel l2 = new JLabel("AU = Animal Unit");
		JLabel l3 = new JLabel("VS = Volatile Solids");
		JLabel l4 = new JLabel("TS = Total Solids");
		p2.add(l2);p2.add(l3);p2.add(l4);
		this.add(p2,gbc);
		
	
		setSize(700,400);
		setPreferredSize(getSize());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
