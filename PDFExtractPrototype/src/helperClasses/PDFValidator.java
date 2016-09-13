package helperClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;

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
			PDDocument pdDoc = new PDDocument();
			pdDoc = pdDoc.load(file);
			pdDoc.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
