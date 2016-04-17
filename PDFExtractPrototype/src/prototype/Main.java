package prototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

public class Main {
	private static HashMap<String,File> inputFiles = new HashMap<String, File>();
	private static File outputFolder;
	
	public static void main(String[] args) {
		getProperties();
		//Parsing input pdfs
		if(isPopulated()){
			Set<String> keys = inputFiles.keySet();
			for(String x : keys){
				MyPDFParser z = new MyPDFParser(x, inputFiles.get(x), outputFolder);
				z.parse();
				z.writeToTxt();
			}
		}
	}

	//========================================================================================================================
	//Helper methods for loading files;
	public static void getProperties() {

		Properties configProp = new Properties();

		try {
			//Loading input files
			FileInputStream configInputStream = new FileInputStream(".\\resources\\config.properties");
			configProp.load(configInputStream);
			String inputFilePaths = configProp.getProperty("input");
			String[] paths = inputFilePaths.split(";");
			
			for(String x : paths){
				File y = new File(x);
				if(!y.isFile()){
					System.out.println("Invalid input file: " + x);
					continue;
				}
				
				Tika tika = new Tika();
				String mediaType = tika.detect(y);
				if(!mediaType.contains("pdf")){
					System.out.println("File is not a PDF: " + x);
					continue;
				}
				inputFiles.put(x, y);
			}
			
			//Loading output directory
			File temp = new File(configProp.getProperty("output"));
			if(temp.isFile()){
				return;
			}
			outputFolder = temp;
			if (!outputFolder.exists()){
				outputFolder.mkdir();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static boolean isPopulated(){
		boolean one = inputFiles.size() > 0;
		boolean two = outputFolder!=null && outputFolder.isDirectory();
		
		if (one && two){
			return true;
		}
		if(!one){
			System.out.println("No valid input files.");
		}
		if(!two){
			System.out.println("Invalid output directory.");
		}
		return false;
	}
}
