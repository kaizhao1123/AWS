import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class Main extends JFrame {
	
	
	public static void main(String[] args) throws Exception {

		//String[] data = InputData.readTextFile("test.txt");
		
		String path = "C:/Users/kaizhao/Desktop/AWSdata.xlsx";
		InputData example = new InputData(path);
		
		System.out.println("read excel");
		MainFrame frame = new MainFrame();
		frame.buildFrame(example);
	
	}
	
	
}
