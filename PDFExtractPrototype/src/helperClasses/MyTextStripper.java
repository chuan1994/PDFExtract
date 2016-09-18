package helperClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

/**
 * Class is responsible for extracting the text and providing font based
 * information It sorts the specified page ranges into different blocks of text.
 * These blocks are oragnised by the font, font size and page numbers.
 * 
 * @author cwu323
 *
 */
public class MyTextStripper extends PDFTextStripper {

	private String prevBaseFont = "";
	private float prevBaseFontSize = 0;
	private float prevYPosition = 0;
	private StringBuilder localString = new StringBuilder();
	private ArrayList<FontGroup> fontGrouping;
	private int currentPage = 0;
	private boolean newLine = false;

	public MyTextStripper() throws IOException {
		super();
		fontGrouping = new ArrayList<FontGroup>();
	}

	// ==========================================================
	// Using own page setting methods to avoid concurrency issues
	@Override
	public void setStartPage(int i) {
		super.setStartPage(i);
		currentPage = i;
	}

	@Override
	public int getCurrentPageNo() {
		return this.currentPage;
	}

	// ==========================================================
	// Finding out when new lines are
	@Override
	protected void writeLineSeparator() throws IOException {
		newLine = true;
		super.writeLineSeparator();
	}

	@Override
	protected void startPage(PDPage page) throws IOException {
		newLine = true;
		super.startPage(page);
	}

	/**
	 * Sorting the text into fontgroups. Identifies the font metadata of the
	 * current text to be written creates a new fontgroup when the font metadata
	 * changes to create a list of text organised by this metadata
	 */
	@Override
	public void writeString(String text, List<TextPosition> textPositions)
			throws IOException {

		if (newLine) {
//			newLine = false;
			text = text.trim();

			if (text.matches("\\s+") || text.equals("")) {
				return;
			}

			ArrayList<String> fonts = new ArrayList<String>();
			ArrayList<Float> fontSizes = new ArrayList<Float>();

			for (TextPosition t : textPositions) {
				fonts.add(t.getFont().getName());
				fontSizes.add(t.getFontSize());
			}

			String commonFont = this.getCommonFont(fonts);
			float commonFontSize = this.getCommonFontSize(fontSizes);

			if (commonFont.matches("\\s+") || commonFont.equals("")
					|| ((int) commonFontSize) == 0) {
				return;
			}

			if (commonFont.equals(prevBaseFont)
					&& Math.abs(commonFontSize - prevBaseFontSize) < 0.1f) {
				localString.append(" " + text);
				writeString(text);
			} else {
				// Add previous block into a group
				FontGroup f = new FontGroup(prevBaseFont, prevBaseFontSize,
						localString.toString(), this.getCurrentPageNo());
				fontGrouping.add(f);

				// Resetting for next block
				prevBaseFont = commonFont;
				prevBaseFontSize = commonFontSize;
				writeString("[" + commonFont + "," + commonFontSize + ","
						+ this.getCurrentPageNo() + "] " + text);
				localString = new StringBuilder();
				localString.append(text);
			}
		}
		else{
			super.writeString(text, textPositions);
		}
	}

	/**
	 * getter for the font groups
	 * 
	 * @return
	 */
	public ArrayList<FontGroup> getFontGroups() {
		return this.fontGrouping;
	}

	/**
	 * method to display all the font groups so far in the console output
	 */
	public void print() {
		for (FontGroup f : this.fontGrouping) {
			System.out.println("Font = " + f.getFont());
			System.out.println("Font Size = " + f.getFontSize());
			System.out.println("Text = " + f.getText());
			System.out.println();
		}
	}

	// Helper methods to retrieve most common font and size in a text block
	private String getCommonFont(ArrayList<String> fonts) {
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		for (String s : fonts) {
			if (countMap.containsKey(s)) {
				countMap.put(s, countMap.get(s) + 1);
			} else {
				countMap.put(s, 0);
			}
		}

		Entry<String, Integer> max = null;
		for (Entry<String, Integer> e : countMap.entrySet()) {
			if (max == null || max.getValue() < e.getValue()) {
				max = e;
			}
		}

		return max.getKey();
	}

	private float getCommonFontSize(ArrayList<Float> fontSizes) {
		HashMap<Float, Integer> countMap = new HashMap<Float, Integer>();
		for (Float f : fontSizes) {
			if (countMap.containsKey(f)) {
				countMap.put(f, countMap.get(f) + 1);
			} else {
				countMap.put(f, 0);
			}
		}

		Entry<Float, Integer> max = null;
		for (Entry<Float, Integer> e : countMap.entrySet()) {
			if (max == null || max.getValue() < e.getValue()) {
				max = e;
			}
		}

		return max.getKey();
	}
}