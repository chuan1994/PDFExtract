package prototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Main {
	private static HashMap<String,File> inputFiles = new HashMap<String, File>();
	private static File outputFolder;
	
	public static void main(String[] args) {
		getProperties();
		//Parsing input pdfs
		if(isPopulated()){
			Set<String> keys = inputFiles.keySet();
			for(String x : keys){
				RestrictedExtractor RE = new RestrictedExtractor(0, 10, x, inputFiles.get(x), outputFolder);
				RE.parse();
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