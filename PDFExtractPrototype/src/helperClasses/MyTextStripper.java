package helperClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class MyTextStripper extends PDFTextStripper {

	private String prevBaseFont = "";
	private float prevBaseFontSize = 0;

	private ArrayList<FontGroupBlock> fontGrouping = new ArrayList<FontGroupBlock>();

	public MyTextStripper() throws IOException {
		super();
	}

	@Override
	public void writeString(String text, List<TextPosition> textPositions)
			throws IOException {
		//Creating builders
		StringBuilder builder = new StringBuilder();
		StringBuilder localBuilder = new StringBuilder();
		
		//Processing parsed text
		for (TextPosition position : textPositions) {
			String baseFont = position.getFont().getName();
			float baseFontSize = position.getFontSize();
			if (baseFont != null && (!baseFont.equals(prevBaseFont) || (Math.abs(baseFontSize - prevBaseFontSize)) > 1f)) {
				
				if(prevBaseFontSize > 0){
					System.out.println(localBuilder.toString());
					FontGroupBlock f = new FontGroupBlock(baseFont, baseFontSize, localBuilder.toString(), 0);
					fontGrouping.add(f);
				}
				
				localBuilder = new StringBuilder();
				builder.append('[').append(baseFont).append(',')
						.append(baseFontSize).append(']');
				prevBaseFont = baseFont;
				prevBaseFontSize = baseFontSize;
			}
			for (int i : position.getCharacterCodes()) {
				localBuilder.append(Character.toString((char) i));
				builder.append(Character.toString((char) i));
			}
		}
		writeString(builder.toString());
	}
	
	public ArrayList<FontGroupBlock> getText(){
		return this.fontGrouping;
	}
	
	public void print(){
		for(FontGroupBlock f : this.fontGrouping){
			System.out.println("Font = " + f.getFont());
			System.out.println("Font Size = " + f.getFontSize());
			System.out.println("Text = " + f.getText());
			System.out.println();
		}
	}
}