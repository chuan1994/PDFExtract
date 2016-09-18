package reportCheckers.UOA;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import helperClasses.FontGroup;
import main.MetadataStorer;
import reportCheckers.ReportExtractor;

public class UOAThesisExtractor implements ReportExtractor {

	MetadataStorer ms;

	ArrayList<FontGroup> fontGroupings = new ArrayList<FontGroup>();

	FontGroup titleFGB = new FontGroup(null, 0, null, 0);

	// Indexes and pages required as dependencies for other entities
	int titleIndex = -1;
	int titlePage = -1;
	int authorIndex = -1;
	int degreeIndex = -1;

	public UOAThesisExtractor(ArrayList<FontGroup> fontGroupings) {
		this.fontGroupings = fontGroupings;
	}

	@Override
	public MetadataStorer getAllMeta(MetadataStorer ms) {
		this.ms = ms;
		if (fontGroupings.size() == 0)
			return ms;

		ms.setTitle(findTitle());
		ms.setAuthors(findAuthor());
		ms.setDegree(findDegree());
		ms.setDegreeDiscp(findDiscipline());
		// ms.setAbstractx(findAbstract());
		// ms.setSupervisors(findSupervisors());
		 
		// ms.setAltTitle(findSubtitle());
		return ms;
	}

	// =================================================================
	@Override
	public String findTitle() {
		String returnVal = null;

		// Finding cover page. Almost always page 2, unless page 2 is empty then
		// its page 3
		ArrayList<FontGroup> coverPage = getBlocksInPage(2);
		StringBuilder contents = new StringBuilder();
		for (FontGroup f : coverPage) {
			contents.append(" " + f.getText());
		}

		if (contents.toString().split(" ").length < 8) {
			coverPage = getBlocksInPage(3);
		}

		FontGroup titleFontGroup = getLargestFontText(coverPage);
		titleIndex = fontGroupings.indexOf(titleFontGroup);
		titlePage = titleFontGroup.getPageNum();

		returnVal = reduceOutput(titleFontGroup.getText());

		return returnVal;
	}

	@Override
	public String findSubtitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findAuthor() {
		String[] returnVal = null;

		ArrayList<FontGroup> coverPage = fontGroupings;

		// reducing scope to titlepage
		if (titlePage != -1) {
			coverPage = getBlocksInPage(titlePage);
		}

		ArrayList<FontGroup> finalGroups = new ArrayList<FontGroup>();
		// reducing list to only search before supervisors are specified
		for (FontGroup fg : coverPage) {
			String text = fg.getText().replaceAll("[^a-zA-Z0-9 -]", "");
			if (text.matches("(.*((?i)supervisor).*)")) {
				break;
			}
			if (fontGroupings.indexOf(fg) > titleIndex) {
				finalGroups.add(fg);
			}
		}

		// Searching for author based on regex
		for (int i = 0; i < finalGroups.size(); i++) {
			String text = finalGroups.get(i).getText();
			String[] split = text.trim().split("(\r?\n)");
			for (String x : split) {
				x = x.replaceAll("\r?\n", "");
				if (x.matches("(((?i)by )?)(((Mc|Mac)?[A-Z][a-z]*('?)[a-z]+(-| |\\b|\\.)){2,})")
						|| x.matches("((?i)by )(([A-Z]+('?)[A-Z]+(-| |\\b|\\.)){2,})")) {

					if (x.startsWith("by") || x.startsWith("By")) {
						x = x.replace("by", "");
						x = x.replace("By", "");
						if (x.length() == 0) {
							continue;
						}
					}

					if (ms.getTitle().contains(x)) {
						continue;
					}

					returnVal = new String[1];
					returnVal[0] = reduceOutput(x);
					// Theses can only have 1 author;
					// do not need to keep finding names
					this.authorIndex = i;
					return returnVal;
				}
			}
		}
		return returnVal;
	}

	@Override
	public String findDegree() {
		String returnVal = null;
		ArrayList<FontGroup> searchArea = new ArrayList<FontGroup>();
		
		// Find appropriate blocks to search through
		if (titlePage != -1 && titleIndex != -1) {
			int index = titleIndex;
			while (fontGroupings.size() > index && fontGroupings.get(index).getPageNum() == titlePage) {
				searchArea.add(fontGroupings.get(index));
				index++;
			}
		}
		else{
			searchArea = fontGroupings;
		}

		returnVal = findCommon(searchArea, Pattern.compile("((?i)(master|doctor)(s)? (of|in))(( [A-Z][a-zA-z]*)+)"));

		if (returnVal.equals("")) {
			degreeIndex = -1;
			return null;
		}

		for (FontGroup f : searchArea) {
			String text = f.getText().replaceAll("\r?\n", " ").replaceAll("  ", " ");
			if (text.contains(returnVal)) {
				degreeIndex = fontGroupings.indexOf(f);
				break;
			}
		}
		
		return reduceOutput(returnVal);
	}

	@Override
	public String findDiscipline() {
		//Check what comes after the degree specified, obtain discipline from that using regex
		if (degreeIndex == -1) {
			return null;
		}
		
		String returnVal = null;
		
		String degree = ms.getDegree();
		StringBuilder text = new StringBuilder();
		
		text.append(fontGroupings.get(degreeIndex).getText());
		Pattern p = Pattern.compile(degree + " in(( (?!The\\b)[A-Z][a-z]+)+)");
		Matcher m = p.matcher(singleLine(text.toString()));

		if (m.find()) {
			returnVal = m.group(1);
		} else {
			if (degreeIndex + 1 < fontGroupings.size()) {
				text.append(fontGroupings.get(degreeIndex + 1).getText());
			}

			Matcher m2 = p.matcher(singleLine(text.toString()));
			if (m2.find()) {
				returnVal = m.group(1);
			}
		}

		if (returnVal == null||returnVal.equals("")) {
			return null;
		}

		return reduceOutput(returnVal);
	}

	@Override
	public String findSupervisors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAbstract() {
		// TODO Auto-generated method stub
		return null;
	}

	// ==================================================================================================================================
	// Helper Methods to help with extracting
	/**
	 * Returns all blocks in a specified page
	 * 
	 * @param pageNumber
	 * @return
	 */
	private ArrayList<FontGroup> getBlocksInPage(int pageNumber) {
		ArrayList<FontGroup> returnVal = new ArrayList<FontGroup>();
		for (FontGroup block : fontGroupings) {
			if (block.getPageNum() == pageNumber) {
				returnVal.add(block);
			}
		}
		return returnVal;
	}

	/**
	 * Returns the fontgroup with teh larget font size from a list
	 * 
	 * @param group
	 * @return
	 */
	private FontGroup getLargestFontText(ArrayList<FontGroup> group) {
		float largestFontSize = 0;
		FontGroup returnVal = new FontGroup("", 0, "", 0);
		for (FontGroup fg : group) {
			if (fg.getFontSize() > largestFontSize) {
				largestFontSize = fg.getFontSize();
				returnVal = fg;
			}
		}
		return returnVal;

	}

	/**
	 * Method to find the text which matches the pattern p from a list of font
	 * groups It returns the most commonly found
	 * 
	 * @param blocks
	 * @param p
	 * @return
	 */
	private String findCommon(ArrayList<FontGroup> blocks, Pattern p) {
		ArrayList<String> results = new ArrayList<String>();
		for (FontGroup f : blocks) {
			String searchText = f.getText();

			Matcher m = p.matcher(searchText);

			while (m.find()) {
				results.add(m.group());
			}

			if (results.size() == 0) {
				String[] split = searchText.split("(\r?\n){2,}");
				for (String x : split) {
					searchText = x.replaceAll("\r?\n", " ").replaceAll("  ", " ");
					m = p.matcher(searchText);

					while (m.find()) {
						results.add(m.group());
					}
				}
			}
		}

		Map<String, Long> map = results.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		String result = "";
		long count = 0;

		for (String x : map.keySet()) {
			if (map.get(x) > count) {
				count = map.get(x);
				result = x;
			}
		}
		return result;
	}

	// ==================================================================================================================================
	// Helper methods to help with formatting
	/**
	 * Method to form simple paragraphs
	 * 
	 * @param value
	 * @return
	 */

	private String reduceOutput(String value) {
		
		String[] firstBlock = value.split("(\r?\n){2,}");
		String returnVal = firstBlock[0].replaceAll("\r?\n", " ").replaceAll("  ", " ").trim();

		return returnVal;
	}

	/**
	 * method to remove all excess spaces
	 * 
	 * @param value
	 * @return
	 */
	private String singleLine(String value) {
		return value.replaceAll("\\s+", " ").trim();
	}

	/**
	 * method to remove page numbers
	 * 
	 * @param input
	 * @return
	 */
	private String removePageNumbers(String input) {
		String[] split = input.split("(\r?\n)");
		StringBuilder sb = new StringBuilder();
		for (String x : split) {
			if (x.trim().matches("(?i)(ix|iv|v?i{0,3})") || x.trim().matches("(?i)page ([0-9])*")) {
				continue;
			}
			sb.append(x + "\r\n");
		}

		return sb.toString().trim();
	}

	// ==========================================================================================================================================
	// Not required for UOA Theses
	@Override
	public Date findPubDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findUUID() {
		// TODO Auto-generated method stub
		return null;
	}

	// ==========================================================================================================================================

}
