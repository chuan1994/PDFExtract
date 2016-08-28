package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import helperClasses.PDFValidator;

public class Main {
	private static HashMap<String, File> inputFiles = new HashMap<String, File>();
	private static File outputFolder;
	static PDFValidator validator = new PDFValidator();
	
	public static void main(String[] args) {

		
		if(args.length > 1){
			processArgs(args);
		}
		else if(new File("./resources/config.properties").isFile()){
			getProperties();
		}
		else{
			System.out.println("To execute this jar please follow either of the following:");
			System.out.println("1) Populate the config file. Instructions are found within");
			System.out.println("2) Run the jar with a list of input files separated by a space followed by an output folder");
			System.out.println("Example: java -jar PDFExtractPrototype.jar example.pdf example1.pdf example2.pdf outputFolder");
			return;
		}
		
		// Parsing input pdfs
		if (isPopulated()) {
			Set<String> keys = inputFiles.keySet();
			for (String x : keys) {
				MyPDFParser parser = new MyPDFParser(x, inputFiles.get(x),
						outputFolder);
				parser.parseAll();
				parser.close();
				parser.makeTextFile();
			}
		}
	}

	// ========================================================================================================================
	// Helper methods for loading files;
	public static void processArgs(String[] args){
		int argLength = args.length;
		File outTemp = new File(args[argLength -1]);
		setOutput(outTemp);
		
		for(int i = 0; i < argLength-1; i++){
			addInput(args[i]);
		}
	}
	
	
	public static void getProperties() {

		Properties configProp = new Properties();

		try {
			// Loading input files
			FileInputStream configInputStream = new FileInputStream(
					"./resources/config.properties");
			configProp.load(configInputStream);
			String inputFilePaths = configProp.getProperty("input");
			String[] paths = inputFilePaths.split(";");

			for (String x : paths) {
				addInput(x);
			}

			// Loading output directory
			File temp = new File(configProp.getProperty("output"));
			setOutput(temp);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addInput(String path){
		File y = new File(path);
		if (!y.isFile()) {
			System.out.println("Invalid input file: " + path);
			return;
		}

		// Checking validity of files
		validator.setFile(y);
		if (!validator.isPDF()) {
			System.out.println("File is not a PDF: " + path);
			return;
		}
		inputFiles.put(path, y);
	}
	
	public static void setOutput(File outFolder){
		if (outFolder.isFile()) {
			return;
		}
		outputFolder = outFolder;
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		}
	}

	public static boolean isPopulated() {
		boolean one = inputFiles.size() > 0;
		boolean two = outputFolder != null && outputFolder.isDirectory();

		if (one && two) {
			return true;
		}
		if (!one) {
			System.out.println("No valid input files.");
		}
		if (!two) {
			System.out.println("Invalid output directory.");
		}
		return false;
	}
}