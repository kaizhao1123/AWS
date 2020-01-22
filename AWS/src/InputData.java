import java.io.FileReader;

public class InputData {
	
	public static String[] readTextFile(String text)throws Exception{
		FileReader fr = new FileReader("C:\\" + text);
		int i;
		String file;
		StringBuilder sb = new StringBuilder();
		while( (i = fr.read()) != -1){
			sb = sb.append((char)i);					
		}
		fr.close();
		
		file = sb.toString();
		String[] data = file.split(",");		
		return data;
	}


	
}
