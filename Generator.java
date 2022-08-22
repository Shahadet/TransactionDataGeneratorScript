import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.HashMap;
import java.util.Map;

public class Generator {
	
	public static String inputFile="src/input.txt";
	public static String ouputFileName="src/output.csv";
	public static Map<String, String> options;
	public static PrintStream fileOut = null;
	
	public static void main(String[] args) {
		takeInputProperties(inputFile);
		int column = Integer.parseInt(options.get(Options.COLUMN).trim());
		int row = Integer.parseInt(options.get(Options.ROW).trim());
		
		//Setting Header
		String header="";
		for(int i=1;i<=column;i++) {
			if(i!=1)
				header+=",";
			header+=options.get("column"+i);
		}
		//printing header in CSV
		try {
			fileOut = new PrintStream(ouputFileName);
			System.setOut(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(header);
		String line;
		for(int i=1;i<=row;i++) {
			line = "";
			
			//Transaction Id
			line += i;
			
			//Customer Id
			line += ",";
			line += "C"+i;
			
			//Merchant Id
			line += ",";
			line += "M"+i;
			
			//MC Code
			line += ",";
			line += (char)('A'+i)+""+i+""+'B'+(i+1)+""+(i-1);
			
			//amount
			line += ",";
			line += i*100;
			
			//Date
			line += ",";
			line += "21-07-22";
			System.out.println(line);
		}
		fileOut.close();
	}
	
	
	public static void takeInputProperties(String fileName) {
		String line;
		String[] tokens;
		options = new HashMap<String, String>();
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	tokens = line.split(":");
            	options.put(tokens[0], tokens[1]);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
