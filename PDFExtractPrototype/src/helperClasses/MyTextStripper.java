package helperClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class MyTextStripper extends PDFTextStripper {

	private String prevBaseFont = "";
	private float prevBaseFontSize = 0;
	private StringBuilder localString = new StringBuilder();
	private ArrayList<FontGroup> fontGrouping;
	private int currentPage = 0;

	public MyTextStripper() throws IOException {
		super();
		fontGrouping = new ArrayList<FontGroup>();
	}
	
	@Override 
	public void setStartPage(int i){
		super.setStartPage(i);
		currentPage = i;
	}
	
	@Override
	public int getCurrentPageNo(){
		return this.currentPage;
	}

	@Override
	public void writeString(String text, List<TextPosition> textPositions)
			throws IOException {
		
		text = text.trim();
		
		if(text.matches("\\s+") || text.equals("")){
			return;
		}
		
		ArrayList<String> fonts = new ArrayList<String>();
		ArrayList<Float> fontSizes = new ArrayList<Float>();
		
		for(TextPosition t: textPositions){
			fonts.add(t.getFont().getName());
			fontSizes.add(t.getFontSize());
		}
		
		String commonFont = this.getCommonFont(fonts);
		float commonFontSize = this.getCommonFontSize(fontSizes);
		
		if(commonFont.matches("\\s+") || commonFont.equals("") || ((int)commonFontSize) == 0){
			return;
		}
		
		
		if(commonFont.equals(prevBaseFont) && Math.abs(commonFontSize - prevBaseFontSize) < 0.1f){
			localString.append(" " + text);
			writeString(text);
		}
		else{
			//Add previous block into a group
			FontGroup f = new FontGroup(prevBaseFont, prevBaseFontSize, localString.toString(), this.getCurrentPageNo());
			fontGrouping.add(f);
			
			//Resetting for 
			prevBaseFont = commonFont;
			prevBaseFontSize = commonFontSize;
			writeString("[" + commonFont + "," + commonFontSize + "," + this.getCurrentPageNo() +"] " + text );
			localString = new StringBuilder();
			localString.append(text);
		}
	}
	
	public ArrayList<FontGroup> getFontGroups(){
		return this.fontGrouping;
	}
	
	public void print(){
		for(FontGroup f : this.fontGrouping){
			System.out.println("Font = " + f.getFont());
			System.out.println("Font Size = " + f.getFontSize());
			System.out.println("Text = " + f.getText());
			System.out.println();
		}
	}
	
	//Helper methods to retrieve most common font and size in a text block
	private String getCommonFont(ArrayList<String> fonts){
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		for(String s : fonts){
			if(countMap.containsKey(s)){
				countMap.put(s, countMap.get(s) + 1);
			}
			else{
				countMap.put(s, 0);
			}
		}
		
		Entry<String, Integer> max = null;
		for(Entry<String, Integer> e: countMap.entrySet()){
			if(max == null || max.getValue() < e.getValue()){
				max = e;
			}
		}
		
		
		return max.getKey();
	}
	
	private float getCommonFontSize(ArrayList<Float> fontSizes){
		HashMap<Float, Integer> countMap = new HashMap<Float, Integer>();
		for(Float f : fontSizes){
			if(countMap.containsKey(f)){
				countMap.put(f, countMap.get(f) + 1);
			}
			else{
				countMap.put(f, 0);
			}
		}
		
		Entry<Float, Integer> max = null;
		for(Entry<Float, Integer> e: countMap.entrySet()){
			if(max == null || max.getValue() < e.getValue()){
				max = e;
			}
		}
		
		return max.getKey();
	}
}