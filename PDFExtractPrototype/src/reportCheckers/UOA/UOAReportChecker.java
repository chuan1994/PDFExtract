package reportCheckers.UOA;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import reportCheckers.ReportChecker;
import main.MetadataStorer;
import helperClasses.FontGroupBlock;

public class UOAReportChecker implements ReportChecker {

	MetadataStorer ms;

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();

	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);

	int titleIndex = -1;
	int titlePage = -1;
	int authorIndex = -1;
	int degreeIndex = -1;

	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings) {
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

	@Override
	public String findTitle() {
		String title = "";
		ArrayList<FontGroupBlock> temp = getPageBlocks(2);
		StringBuilder contents = new StringBuilder();

		for (FontGroupBlock f : temp) {
			contents.append(f.getText() + " ");
		}

		if (contents.toString().split(" ").length < 8) {
			temp = getPageBlocks(3);
		}
		FontGroupBlock titleBlock = maxFontSizeBlock(temp);

		titleIndex = fontGroupings.indexOf(titleBlock);
		titlePage = titleBlock.getPageNum();
		title = titleBlock.getText();

		return reduceOutput(title);
	}

	@Override
	public String[] findAuthor() {
		String[] author = { null };

		for (int i = this.titleIndex; i < this.fontGroupings.size(); i++) {
			String text = this.fontGroupings.get(i).getText();
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

					author[0] = reduceOutput(x);
					// Theses can only have 1 author;
					// do not need to keep finding names
					this.authorIndex = i;
					return author;
				}
			}
		}
		return null;
	}

	@Override
	public String findSubtitle() {
		int nextIndex = this.titleIndex + 1;
		int fgPageNum = this.fontGroupings.get(nextIndex).getPageNum();
		String subtitleText = this.fontGroupings.get(nextIndex).getText().replaceAll("\r?\n", " ");
		if (nextIndex < this.authorIndex && nextIndex < this.degreeIndex && fgPageNum == this.titlePage) {

			return reduceOutput(subtitleText);
		}

		return null;
	}

	@Override
	public String findAbstract() {
		FontGroupBlock abstBlock = null;
		String abst;
		String abstContent = "";
		int titleOffSet = 1;
		boolean found = false;

		while (!found) {
			if (titlePage + titleOffSet > 5) {
				found = true;
				break;
			}
			ArrayList<FontGroupBlock> pageBlock = getPageBlocks(titlePage + titleOffSet);
			for (FontGroupBlock f : pageBlock) {
				FontGroupBlock abstTitle;

				abst = f.getText();
				if (abst.toLowerCase().contains("abstract")) {
					abstTitle = f;
					found = true;
					int index = fontGroupings.indexOf(abstTitle);

					if (abstTitle.getText().split(" ").length > 5) {
						abstBlock = abstTitle;
						String[] blocks = abstBlock.getText().split("(\r?\n){2,}");
						abstContent = blocks[1];
					} else if (found && (index + 1 < fontGroupings.size())) {
						abstBlock = fontGroupings.get(index + 1);
						abstContent = abstBlock.getText();
						if (!(abstContent.split(" ").length > 20)) {
							found = false;
							continue;
						}
						break;
					}
				}
			}
			titleOffSet++;
		}

		if (abstBlock == null) {
			return null;
		}

		return abstContent.trim();
	}

	@Override
	public String findDegree() {

		ArrayList<FontGroupBlock> searchBlocks = new ArrayList<FontGroupBlock>();

		// Find appropriate blocks to search through
		if (titlePage != -1 && titleIndex != -1) {
			int index = titleIndex;
			while (fontGroupings.size() > index && fontGroupings.get(index).getPageNum() == titlePage) {
				searchBlocks.add(fontGroupings.get(index));
				index++;
			}
		}

		// Generating according to findings regex to find degree name
		String result = "";

		result = findCommon(fontGroupings, Pattern.compile("((?i)(master|doctor)(s)? (of|in))(( [A-Z][a-zA-z]*)+)"));

		if (result.equals("")) {
			degreeIndex = -1;
			return null;
		}

		for (FontGroupBlock f : searchBlocks) {
			String text = f.getText().replaceAll("\r?\n", " ").replaceAll("  ", " ");
			if (text.contains(result)) {
				degreeIndex = fontGroupings.indexOf(f);
				break;
			}
		}

		return reduceOutput(result);
	}

	@Override
	public String findSupervisors() {
		String returnVal;
		Pattern p = Pattern.compile(
				"(supervisor(s?), )(([A-Z]([a-z]*)('?)([a-z]+)((-| |\\. )|\\b))+)((and |, )(([A-Z]([a-z]*)('?)([a-z]+)((-| |\\. )|\\b))+))*");

		ArrayList<FontGroupBlock> possibleIndexes = new ArrayList<FontGroupBlock>();
		for (int i = this.titleIndex; i < this.fontGroupings.size(); i++) {
			if (fontGroupings.get(i).getText().toLowerCase().contains("acknowledgment")) {
				if (i + 1 < fontGroupings.size())
					possibleIndexes.add(fontGroupings.get(i + 1));
			}
		}

		returnVal = findCommon(possibleIndexes, p);
		returnVal = returnVal.replaceAll("(?i)supervisor(s?), ", "").replaceAll(" (?!\\b)", "");

		if (returnVal.equals("")) {
			return null;
		}

		return reduceOutput(returnVal);
	}

	@Override
	public String findDiscipline() {
		if (degreeIndex == -1) {
			return null;
		}
		if (ms.getDegree() == null) {
			return null;
		}

		String degree = ms.getDegree();
		String result = "";
		StringBuilder text = new StringBuilder();

		text.append(fontGroupings.get(degreeIndex).getText());

		Pattern p = Pattern.compile(degree + " in(( (?!The\\b)[A-Z][a-z]+)+)");
		Matcher m = p.matcher(singleLine(text.toString()));

		if (m.find()) {
			result = m.group(1);
		} else {
			if (degreeIndex + 1 < fontGroupings.size()) {
				text.append(fontGroupings.get(degreeIndex + 1).getText());
			}

			Matcher m2 = p.matcher(singleLine(text.toString()));
			if (m2.find()) {
				result = m.group(1);
			}
		}

		if (result.equals("")) {
			return null;
		}

		return reduceOutput(result);
	}
	// =========================================================================
	// Unimplemented

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

	// =========================================================================
	// Private helper methods
	private FontGroupBlock maxFontSizeBlock(ArrayList<FontGroupBlock> group) {
		float maxFontSize = 0;
		FontGroupBlock returnVal = group.get(0);

		for (FontGroupBlock fg : group) {
			if (fg.getFontSize() > maxFontSize) {
				maxFontSize = fg.getFontSize();
				returnVal = fg;
			}
		}
		return returnVal;
	}

	private String findCommon(ArrayList<FontGroupBlock> blocks, Pattern p) {
		ArrayList<String> results = new ArrayList<String>();
		for (FontGroupBlock f : blocks) {
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

	private ArrayList<FontGroupBlock> getPageBlocks(int pageNumber) {
		ArrayList<FontGroupBlock> group = new ArrayList<FontGroupBlock>();
		for (FontGroupBlock block : fontGroupings) {
			if (block.getPageNum() == pageNumber) {
				group.add(block);
			}
		}
		return group;
	}

	private String reduceOutput(String value) {
		String[] firstBlock = value.split("(\r?\n){2,}");
		String returnVal = firstBlock[0].replaceAll("\r?\n", " ").replaceAll("  ", " ").trim();

		return returnVal;
	}

	private String singleLine(String value) {
		return value.replaceAll("\\s+", " ").trim();
	}
}
