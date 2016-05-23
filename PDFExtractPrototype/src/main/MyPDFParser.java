package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import helperClasses.PageDimensionCalc;

public class MyPDFParser {
	private String path;
	private File pdf;
	private File outputFolder;
	private PDDocument pdDoc;
	private MetadataStorer meta= new MetadataStorer();
	private PageDimensionCalc pdCalc = new PageDimensionCalc();
	private PDFTextStripper stripper;
	
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
			System.out.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MetadataStorer getMetaData(){
		return this.meta;
	}		
	private void setup(){
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);
			
			stripper = new PDFTextStripper() {
			    String prevBaseFont = "";

			    protected void writeString(String text, List<TextPosition> textPositions) throws IOException
			    {
			        StringBuilder builder = new StringBuilder();

			        for (TextPosition position : textPositions)
			        {
			            String baseFont = position.getFont().getName();
			            float baseFontSize = position.getFontSize();
			            if (baseFont != null && !baseFont.equals(prevBaseFont))
			            {
			                builder.append('[').append(baseFont).append(',').append(baseFontSize).append(']');
			                prevBaseFont = baseFont;
			            }
			            for(int i : position.getCharacterCodes()){
			            	 builder.append(Character.toString((char)i));
			            }
			        }
			        writeString(builder.toString());
			    }
			};
			
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