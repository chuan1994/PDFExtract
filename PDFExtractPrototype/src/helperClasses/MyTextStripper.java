package helperClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class MyTextStripper extends PDFTextStripper {

	private String prevBaseFont = "";

	private ArrayList<String> fontGrouping = new ArrayList<String>();

	public MyTextStripper() throws IOException {
		super();
	}

	@Override
	public void writeString(String text, List<TextPosition> textPositions)
			throws IOException {
		StringBuilder builder = new StringBuilder();

		for (TextPosition position : textPositions) {
			String baseFont = position.getFont().getName();
			float baseFontSize = position.getFontSize();
			if (baseFont != null && !baseFont.equals(prevBaseFont)) {
				builder.append('[').append(baseFont).append(',')
						.append(baseFontSize).append(']');
				prevBaseFont = baseFont;
			}
			for (int i : position.getCharacterCodes()) {
				builder.append(Character.toString((char) i));
			}
		}
		writeString(builder.toString());
	}
	
	public ArrayList<String> getText(){
		return this.fontGrouping;
	}
	
	public void print(){
		for(String x : this.fontGrouping){
			System.out.println(x);
		}
	}
}
