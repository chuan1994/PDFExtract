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

public class UOAThesisExtractor implements ReportExtractor{


	MetadataStorer ms;

	ArrayList<FontGroup> fontGroupings = new ArrayList<FontGroup>();

	FontGroup titleFGB = new FontGroup(null, 0, null, 0);

	//Indexes and pages required as dependencies for other entities
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
		ms.setAbstractx(findAbstract());
		ms.setSupervisors(findSupervisors());
		ms.setDegree(findDegree());
		ms.setDegreeDiscp(findDiscipline());
		ms.setAltTitle(findSubtitle());
		return ms;
	}
	//=================================================================
	@Override
	public String findTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findSubtitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDegree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDiscipline() {
		// TODO Auto-generated method stub
		return null;
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

	//==================================================================================================================================
	//Helper Methods to help with extracting
	/**
	 * Returns all blocks in a specified page
	 * @param pageNumber
	 * @return
	 */
	private ArrayList<FontGroup> getBlocksInPage(int pageNumber){
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
	 * @param group
	 * @return
	 */
	private FontGroup getLargestFontText(ArrayList<FontGroup> group){
		float largestFontSize = 0;
		FontGroup returnVal = new FontGroup("",0,"", 0);
		for (FontGroup fg : group) {
			if (fg.getFontSize() > largestFontSize) {
				largestFontSize = fg.getFontSize();
				returnVal = fg;
			}
		}
		return returnVal;
		
		
	}
	
	/**
	 * Method to find the text which matches the pattern p from a list of font groups
	 * It returns the most commonly found
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
			
			if(results.size() == 0){
				String[] split = searchText.split("(\r?\n){2,}");
				for(String x: split){
					searchText = x.replaceAll("\r?\n", " ").replaceAll("  ", " ");
					m = p.matcher(searchText);
					
					while (m.find()){
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
	
	
	//==================================================================================================================================
	//Helper methods to help with formatting
	/**
	 * Method to form simple paragraphs
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
	 * @param value
	 * @return
	 */
	private String singleLine(String value) {
		return value.replaceAll("\\s+", " ").trim();
	}
	

	/**
	 * method to remove page numbers 
	 * @param input
	 * @return
	 */
	private String removePageNumbers(String input){
		String[] split = input.split("(\r?\n)");
		StringBuilder sb = new StringBuilder();
		for(String x : split){
			if(x.trim().matches("(?i)(ix|iv|v?i{0,3})") || x.trim().matches("(?i)page ([0-9])*")){
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
