
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;


import javax.swing.*;  
public class SelectAnimals {
	
	
	JList<String> beef;
	JList<String> dairy;
	
	ArrayList<JList<String>> list = new ArrayList<>();
	
	JList<String> choicesList;            // to show the current JList in t1
	
	JList<String> selectedList;           // to show the current JList in t2
	DefaultListModel<String> selectedModel;
	
	//JScrollPane choicesScrollPane = new JScrollPane();
	JScrollPane choicesScrollPane;
	JScrollPane selectedScrollPane;
	
	HashMap<String, JList<String>> choicesMap = new HashMap<>();          // K is the name of animal type, V is the original JList respond the name
	HashMap<String, JList<String>> selectedMap = new HashMap<>();		 // K is element,  V is the JList where the element come from
	
	String[] beefData = {"450-750lb Feeder", "Beef Cow", "Finishing Cattle"};
	String[] dairyData = {"Calf(330 lb)", "Dry Cow", "Heifer(970 lb)", "Milker(100lb Milk)", 
			 			"Milker(125lb Milk)", "Milker(50lb Milk)", "Milker(75lb Milk)"} ; 
	String[] animal = {"Beef","Dairy","Goat","Horse","Poultry", "Sheep","Swine","Veal"};
	
	
	
    public void buildSelectAnimals(){ 
  
        JFrame f= new JFrame("Select Animals"); 
        BorderLayout blo = new BorderLayout();          ////split frame into four parts: left, center, right, bottom
        f.setLayout(blo);
        blo.setHgap(5);       
        Font font = new Font("Arial Narrow", Font.PLAIN, 13);
        
        JLabel jl1, jl2, jl3;
	    jl1 = new JLabel(" Choices");
	    jl2 = new JLabel("    Animal Type:");
	    jl3 = new JLabel("Selected");	      	      
	    jl1.setFont(font);
	    jl2.setFont(font);
	    jl3.setFont(font);
     
        /****
         * Left ***********
         */
		JPanel leftPanel = new JPanel(); 
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(110,180));

        DefaultListModel<String> l1 = new DefaultListModel<>();  
        DefaultListModel<String> l2 = new DefaultListModel<>();

        
        InputElementIntoModel(beefData,l1);
	    beef = new JList<String>(l1); 	      	      	      	    
	    beef.setFont(font);
	      
	    InputElementIntoModel(dairyData,l2);	    
	    dairy = new JList<String>(l2); 
	    dairy.setFont(font);

	    choicesList = beef;
	    choicesScrollPane = new JScrollPane(choicesList);
	    

	    leftPanel.add(jl1, BorderLayout.NORTH);
	    leftPanel.add(choicesScrollPane, BorderLayout.CENTER);       ///add scrollPane into frame
	    leftPanel.add(new JLabel(" "), BorderLayout.WEST);           // add gap in the left
	    f.add(leftPanel, BorderLayout.WEST);
	    
	    list.add(beef);list.add(dairy);	    
	    choicesMap.put("beef", beef);
	    choicesMap.put("dairy", dairy);
	    

	      
	  	 /****
	  	  * right******
	  	  */
	    
	    
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(110,180));
		

	    selectedModel = new DefaultListModel<>();	  
	    selectedList = new JList<String>(selectedModel); 	          
	    selectedList.setFont(font);
		
		selectedScrollPane = new JScrollPane(selectedList);
	    rightPanel.add(jl3, BorderLayout.NORTH);
	    rightPanel.add(selectedScrollPane, BorderLayout.CENTER);       ///add scrollPane into frame
	    rightPanel.add(new JLabel(" "), BorderLayout.EAST);           // add gap in the right
	    f.add(rightPanel, BorderLayout.EAST);      ///add scrollPane into frame
	    
	    
    
	    /*****
	     * Center
	     */
	    
	    JPanel centerPanel = new JPanel();
	    centerPanel.setLayout(new GridLayout(7,1,5,5));
	    
	    JComboBox animalType = new JComboBox(animal);
	    animalType.setSelectedIndex(0);      
	    animalType.addActionListener(new ActionListener() {  
        		public void actionPerformed(ActionEvent e) {       
        			String data = animalType.getItemAt(animalType.getSelectedIndex()).toString().toLowerCase();
        		    choicesList = choicesMap.get(data);
        		    choicesScrollPane.setViewportView(choicesList);
        			
        		}  
    	}); 
	      
	      
	      
	      
	      JButton add = new JButton("Add >");	
	      add.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e){	
							addToSelectedList();
							}							
						}						
					);
    
	      JButton remove = new JButton("< Remove");	
	      remove.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e){						
							removeFromSelectedList();
							}							
						}						
					);
	      
	      JButton addAll = new JButton("Add All >>");
	      addAll.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e){						
							addAllToSelectedList();
							}							
						}						
					);
	      
	      JButton removeAll =new JButton("<Remove All");
	      removeAll.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e){						
							removeAllFromSelectedList();
							}							
						}						
					);
	      
	      
	    animalType.setFont(font);
	    add.setFont(font);
	    remove.setFont(font);
	    addAll.setFont(font);
	    removeAll.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
	      
	    centerPanel.add(jl2);
	    centerPanel.add(animalType);
	    centerPanel.add(add);
	    centerPanel.add(remove);
	    centerPanel.add(addAll);
	    centerPanel.add(removeAll);
	    centerPanel.add(new JLabel(" "));
    
	    f.add(centerPanel, BorderLayout.CENTER);
	    
	    /*****
	     * bottom
	     */
	    
	    JPanel bottomPanel = new JPanel();
	    
	    JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() ////After selected the data source, open the climate frame with data;
				{
					public void actionPerformed(ActionEvent e){						
							AnimalFrame af = new AnimalFrame();
							af.buildAnimalFrame();
						}
						
					}
					
				);
	    ok.setFont(font);
	    ok.setPreferredSize(new Dimension(70,25));
	
	    JButton cancel = new JButton("Cancel");
	    cancel.setFont(font);
	    cancel.setPreferredSize(new Dimension(70,25));

	    bottomPanel.add(ok);bottomPanel.add(cancel);  
	    f.add(bottomPanel, BorderLayout.SOUTH);
	    
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setSize(325,275);  
	    f.setResizable(false);
	    f.setVisible(true);  
     }  
     
     private void InputElementIntoModel(String[] s, DefaultListModel<String> lm) {

    	 for(int i = 0; i < s.length; i++) {
    		 lm.addElement(s[i]);
    	 }
   	
     }
     
     private void RemoveElementFromModel(String[] s, DefaultListModel<String> lm) {

    	 for(int i = 0; i < s.length; i++) {
    		 lm.addElement(s[i]);
    	 }
   	
     }
     
     private void addToSelectedList() {
     	int index = choicesList.getSelectedIndex();		
     	if(index >= 0) {
     		DefaultListModel<String> model = (DefaultListModel<String>) choicesList.getModel();
     		String item = model.getElementAt(index);
     		model.remove(index);
     		selectedModel.addElement(item);	
     		selectedMap.put(item, choicesList); 
     	}		 	
      }
      
      private void removeFromSelectedList() {

     		int index = selectedList.getSelectedIndex();
     		if(index >= 0) {
         		DefaultListModel<String> model = (DefaultListModel<String>) selectedList.getModel();
         		String item = model.getElementAt(index);							
         		model.remove(index);
         		JList oldList =  selectedMap.get(item);							
         		DefaultListModel<String> oldModel = (DefaultListModel<String>) oldList.getModel();
         		oldModel.addElement(item);			
         		selectedMap.remove(item, oldList);
     		}
  	   	 					
  	
      }
      
      private void addAllToSelectedList() {
     	DefaultListModel<String> model = (DefaultListModel<String>) choicesList.getModel();
     	int size = model.getSize();  	
     	for(int i = size - 1; i >= 0; i --) {     		
      		String item = model.getElementAt(i);
      		model.remove(i);
      		selectedModel.addElement(item);	
      		selectedMap.put(item, choicesList);
     	}   	 
      }
      
      private void removeAllFromSelectedList() {
     	 
      	DefaultListModel<String> model = (DefaultListModel<String>) selectedList.getModel();     	
     	int size = model.getSize();  
     	
     	for(int i = size - 1; i >= 0; i --) {  
     		String item = model.getElementAt(i);							
     		model.remove(i);
     		JList oldList =  selectedMap.get(item);							
     		DefaultListModel<String> oldModel = (DefaultListModel<String>) oldList.getModel();
     		oldModel.addElement(item);			
     		selectedMap.remove(item, oldList);
      		
     	} 
      }
}   
     