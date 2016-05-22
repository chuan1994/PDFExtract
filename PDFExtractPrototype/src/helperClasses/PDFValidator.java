package helperClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Primitive check to see if file is a valid pdf
 * 
 * @author chuan
 *
 */
public class PDFValidator {
	private File file;
	
	public void setFile(File file){
		this.file = file;
	}
	
	public File getFile(){
		return this.file;
	}
	
	/**
	 * Method to read PDF to check if valid
	 * @return
	 */
	public boolean isPDF(){
		try {
			Scanner input = new Scanner(file);
			while (input.hasNextLine()){
				String line = input.nextLine();
				if(line.contains("%PDF-")){
					return true;
				}
			}
				input.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
