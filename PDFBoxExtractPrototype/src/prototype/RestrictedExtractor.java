package prototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class RestrictedExtractor {
	private int startPage;
	private int length;
	private String path;
	private File pdf;
	private File outputFolder;
	
	private PDFTextStripper stripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	
	
	public RestrictedExtractor(int startPage, int length, String path, File pdf, File outputFolder){
		this.startPage = startPage;
		this.length = length;
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;	
	}
	
	public void parse(){
		
		try {
			RandomAccessRead fis = new RandomAccessBufferedFileInputStream(pdf);
			PDFParser parser = new PDFParser(fis);
			parser.parse();
				
			stripper = new PDFTextStripper();
			stripper.setStartPage(startPage);
			stripper.setEndPage(startPage + length);
			
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			
			String parsed = stripper.getText(pdDoc);
			System.out.println(parsed);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
