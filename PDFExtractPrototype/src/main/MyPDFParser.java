package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.*;

import helperClasses.PageDimensionCalc;

public class MyPDFParser {
	private String path;
	private File pdf;
	private File outputFolder;
	private PDDocument pdDoc;
	private MetadataStorer meta= new MetadataStorer();
	private PageDimensionCalc pdCalc = new PageDimensionCalc();
	
	public MyPDFParser(String path, File pdf, File outputFolder){
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;
		setup();
		addSurfaceMeta();
	}
	
	private void setup(){
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to extract whole file into text
	 */
	public void parseAll(){
		
	}
	
	private void addSurfaceMeta(){
		meta.setPageLength(pdDoc.getNumberOfPages());
		meta.setPageSize(pdCalc.getPageSize(pdDoc.getPages()));
	}
}


