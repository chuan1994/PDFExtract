package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;

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
			
			ArrayList<FontGroupBlock> textGroups2 = splitOutcome(text);
			
			System.out.println("===========================================================");
			UOAReportChecker checker2 = new UOAReportChecker(textGroups2);
			addSurfaceMeta();
			meta = checker2.getAllMeta(meta);
			System.out.println("OUTPUT:");
			meta.print(System.out);
			System.out.println("===========================================================");
			
			//======================================================================================
			//Writing to outputFile
			String outputPath = outputFolder.getCanonicalFile() + System.getProperty("file.separator") + pdf.getName().split("\\.")[0] +".txt";
			File f= new File(outputPath);
			if(!f.exists()){
				f.createNewFile();
			}else{
				int i = 0;
				while(f.exists()){
					i++;
					outputPath = outputFolder.getCanonicalPath() + System.getProperty("file.separator") + pdf.getName().split("\\.")[0] + "(" + i + ")" + ".txt";

					f = new File (outputPath);
				}
				f.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(f);
			PrintStream ps = new PrintStream(fos);
			ps.println("File: " + pdf.getAbsolutePath());
			ps.println();
			meta.print(ps);
			
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
	
	public void makeTextFile(){
		
	}
	
	
	//=============================================================================
	//PRIVATE METHODS
	private void setup(){
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);
			
			myStripper = new MyTextStripper();
			
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
			String[] fontInfo = matcher.group(1).split(",");
			String font = fontInfo[0].replaceAll("(\\[|\\s+)", "");
			float size = Float.parseFloat(fontInfo[fontInfo.length - 2].replaceAll("\\s+", ""));
			int pageNum = Integer.parseInt(fontInfo[fontInfo.length - 1].replaceAll("(\\]|\\s+)", ""));
			String texts = matcher.group(2);
			
			FontGroupBlock fg = new FontGroupBlock(font, size, texts, pageNum);
			fgb.add(fg);
		}
		
		return fgb;
	}
}