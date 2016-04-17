package prototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Main {
	private static HashMap<String,File> inputFiles = new HashMap<String, File>();
	private static File outputFolder;
	
	public static void main(String[] args) {
		// Reading in properties
		getProperties();
		if(isPopulated()){
			
		}
	}

	public static void getProperties() {

		Properties configProp = new Properties();


		try {
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
