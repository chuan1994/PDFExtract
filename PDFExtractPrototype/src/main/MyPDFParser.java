package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;

import reportCheckers.UOAReportChecker;
import helperClasses.FontGroupBlock;
import helperClasses.MyTextStripper;
import helperClasses.PageDimensionCalc;

public class MyPDFParser {
	private String path;
	private File pdf;
	private File outputFolder;
	private PDDocument pdDoc;
	private MetadataStorer meta= new MetadataStorer();
	private PageDimensionCalc pdCalc = new PageDimensionCalc();
	private MyTextStripper myStripper;
	private PDFTextStripper stripper;
	
	public MyPDFParser(String path, File pdf, File outputFolder){
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;
		setup();
	}
	
	
	/**
	 * Method to mine all metadata
	 */
	public void parseAll(){
		addSurfaceMeta();
		//meta.print();
		try {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 1; i <= 5; i++){
				myStripper.setStartPage(i);
				myStripper.setEndPage(i);
				sb.append(myStripper.getText(pdDoc));
			}
			
//			String text = myStripper.getText(pdDoc);
//			System.out.println(text);
			
			ArrayList<FontGroupBlock> textGroup = myStripper.getFontGroups();
			
			UOAReportChecker checker = new UOAReportChecker(textGroup);
			meta = checker.getAllMeta(meta);
			
			meta.print();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addComplexMeta();
	
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
	
	
	//=============================================================================
	//PRIVATE METHODS
	private void setup(){
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);
			
			myStripper = new MyTextStripper();
			stripper = new PDFTextStripper();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addSurfaceMeta(){
		meta.setPageLength(pdDoc.getNumberOfPages());
		meta.setPageSize(pdCalc.getPageSize(pdDoc.getPages()));
	}
	
	private void addComplexMeta(){
		//UOAReportChecker checker = new UOAReportChecker(stripper.getFontGroups());
	}
}