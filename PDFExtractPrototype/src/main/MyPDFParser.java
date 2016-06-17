package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;

import reportCheckers.UOA.UOAReportChecker;
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
		
		try {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 1; i <= 5; i++){
				myStripper.setStartPage(i);
				myStripper.setEndPage(i);
				sb.append("\n" + myStripper.getText(pdDoc));
			}

			String text = sb.toString();
			
			ArrayList<FontGroupBlock> textGroups1 = myStripper.getFontGroups();
			ArrayList<FontGroupBlock> textGroups2 = splitOutcome(text);
			
			System.out.println("===========================================================");
			UOAReportChecker checker = new UOAReportChecker(textGroups1);
			meta = new MetadataStorer();
			addSurfaceMeta();
			meta = checker.getAllMeta(meta);
			System.out.println("Output 1");
			meta.print();
			System.out.println("===========================================================");
			UOAReportChecker checker2 = new UOAReportChecker(textGroups2);
			MetadataStorer meta1 = new MetadataStorer();
			addSurfaceMeta();
			meta1 = checker2.getAllMeta(meta1);
			System.out.println("Output 2");
			meta1.print();
			System.out.println("===========================================================");
			MetadataComparer mdc = new MetadataComparer();
			MetadataStorer ms = mdc.mergeByNull(meta, meta1);
			System.out.println("Merged Output");
			ms.print();
			System.out.println("===========================================================");
			
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
	
	private ArrayList<FontGroupBlock> splitOutcome(String text){
		ArrayList<FontGroupBlock> fgb = new ArrayList<FontGroupBlock>();
		Pattern pattern = Pattern.compile("(\\[.*,.*\\] )(((.+|\\s+)(?!(\\[.*,.*\\] )))*)");
		
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			String font = matcher.group(1).split(",")[0].replaceAll("(\\[|\\s+)", "");
			float size = Float.parseFloat(matcher.group(1).split(",")[1].replaceAll("\\s+", ""));
			int pageNum = Integer.parseInt(matcher.group(1).split(",")[2].replaceAll("(\\]|\\s+)", ""));
			String texts = matcher.group(2);
			
			FontGroupBlock fg = new FontGroupBlock(font, size, texts, pageNum);
			fgb.add(fg);
		}
		
		return fgb;
	}

}