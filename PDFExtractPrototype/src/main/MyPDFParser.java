package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;

import helperClasses.MyTextStripper;
import helperClasses.PageDimensionCalc;

public class MyPDFParser {
	private String path;
	private File pdf;
	private File outputFolder;
	private PDDocument pdDoc;
	private MetadataStorer meta= new MetadataStorer();
	private PageDimensionCalc pdCalc = new PageDimensionCalc();
	private MyTextStripper stripper;
	
	public MyPDFParser(String path, File pdf, File outputFolder){
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;
		setup();
	}
	
	/**
	 * Method to extract whole file into text
	 */
	public void parseAll(){
		addSurfaceMeta();
		meta.print();
		try {
			String text = stripper.getText(pdDoc);
			//System.out.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stripper.print();
	}

	public MetadataStorer getMetaData(){
		return this.meta;
	}
	
	public void close(){
		try {
			pdDoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setup(){
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);
			
			stripper = new MyTextStripper();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addSurfaceMeta(){
		meta.setPageLength(pdDoc.getNumberOfPages());
		meta.setPageSize(pdCalc.getPageSize(pdDoc.getPages()));
	}
	
	
}