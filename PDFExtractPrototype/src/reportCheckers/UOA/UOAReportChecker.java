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

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();
	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);
	int titleIndex = -1;
	int titlePage = -1;

	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings) {
		this.fontGroupings = fontGroupings;
	}

	@Override
	public MetadataStorer getAllMeta(MetadataStorer ms) {
		if (fontGroupings.size() == 0)
			return ms;

		ms.setTitle(findTitle());
		ms.setAuthors(findAuthor());
		ms.setAltTitle(findSubtitle());
		ms.setAbstractx(findAbstract());
		ms.setSupervisors(findSupervisors());
		ms.setDegree(findDegree());
		ms.setDegreeDiscp(findDiscipline());
		return ms;
	}

	@Override
	public String findTitle() {
		float max = maxFontSize(0, 2);
		String title = "";
		for (FontGroupBlock fgb : this.fontGroupings) {
			if (fgb.getFontSize() == max && fgb.getPageNum() <= 3) {
				this.titleFGB = fgb;
				this.titlePage = fgb.getPageNum();
				this.titleIndex = fontGroupings.indexOf(fgb);
				title = fgb.getText().replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("  ", " ");
			}
		}
		return title;
	}

	@Override
	public String[] findAuthor() {
		String[] author = { null };

		for (int i = this.titleIndex + 1; i < this.fontGroupings.size(); i++) {
			String text = this.fontGroupings.get(i).getText();
			String[] split = text.split("(\\s*(\n?\r)\\s*)");
			for (String x : split) {
				x = x.replaceAll("\n?\r", "");
				if (x.matches("(((?i)by )?)(([A-Z][a-z]*('?)[a-z]+(-| |\\b|\\.))+)")
						|| x.matches("(((?i)by )?)(([A-Z]+('?)[A-Z]+(-| |\\b|\\.))+)")) {

					if (x.startsWith("by") || x.startsWith("By")) {
						x = x.replace("by", "");
						x = x.replace("By", "");
						if (x.length() == 0) {
							continue;
						}
					}
					author[0] = x;
					// Theses can only have 1 author;
					// do not need to keep finding names
					return author;
				}
			}
		}
		return null;
	}

	@Override
	public String findSubtitle() {
		if (titleIndex != -1) {
			if (fontGroupings
					.get(titleIndex + 1)
					.getText()
					.matches("((?i)by)?(([A-Z])([a-z]*(')?[a-z]*(-)?)( |\\b))*")) {
				return "";
			}
			return fontGroupings.get(titleIndex + 1).getText().replaceAll("(\r?\n){2,}", "\r\n");
		}

		return "";
	}

	@Override
	public String findAbstract() {
		int abstIndex = 0;
		String abst;
		String abstContent = "";
		for (int i = this.titleIndex + 1; i < this.fontGroupings.size(); i++) {
			abst = this.fontGroupings.get(i).getText();
			if (abst.toLowerCase().contains("abstract")) {
				abstIndex = i;
			}
		}
		for (int i = abstIndex + 1; i < this.fontGroupings.size(); i++) {
			abstContent = this.fontGroupings.get(i).getText();
			if(abstContent.split(" ").length > 20) {
				return abstContent.replaceAll("(\r?\n){2,}", "\r\n");
			}
		}
		return abstContent;
	}

	@Override
	public String findDegree() {

		ArrayList<FontGroupBlock> searchBlocks = new ArrayList<FontGroupBlock>();
		// Find appropriate blocks to search through
		if (titlePage != -1) {
			for (FontGroupBlock fb : fontGroupings) {
				if (fb.getPageNum() == titlePage) {
					searchBlocks.add(fb);
				}
			}
		}

		// Search for master/doctor word count in likely places

		ArrayList<FontGroupBlock> foundMaster = new ArrayList<FontGroupBlock>();
		ArrayList<FontGroupBlock> foundDoctor = new ArrayList<FontGroupBlock>();

		if (searchBlocks.size() > 0) {
			for (FontGroupBlock fb : searchBlocks) {
				int masterIncr = findOccurrences(fb.getText(), Pattern.compile(
						"\\bmaster(s)?\\b", Pattern.CASE_INSENSITIVE));
				int doctorIncr = findOccurrences(fb.getText(), Pattern.compile(
						"\\bdoctor(s)?\\b", Pattern.CASE_INSENSITIVE));

				if (masterIncr != 0) {
					foundMaster.add(fb);
				}

				if (doctorIncr != 0) {
					foundDoctor.add(fb);
				}

			}
		}

		// If not found, search everywhere
		if (foundMaster.size() + foundDoctor.size() == 0) {
			for (FontGroupBlock fb : fontGroupings) {
				int masterIncr = findOccurrences(fb.getText(), Pattern.compile(
						"\\bmaster(s)?\\b", Pattern.CASE_INSENSITIVE));
				int doctorIncr = findOccurrences(fb.getText(), Pattern.compile(
						"\\bdoctor(s)?\\b", Pattern.CASE_INSENSITIVE));

				if (masterIncr != 0) {
					foundMaster.add(fb);
				}
				if (doctorIncr != 0) {
					foundDoctor.add(fb);
				}
			}
		}

		// Generating according to findings regex to find degree name
		String result = "";
		if (foundMaster.size() > foundDoctor.size()) {
			result = findCommon(
					foundMaster,
					Pattern.compile("((?i)(master)(s)? (of|in))(( [A-Z][a-zA-z]*)*)"));

		} else if (foundDoctor.size() > foundMaster.size()) {
			result = findCommon(
					foundDoctor,
					Pattern.compile("((?i)(doctor)(s)? (of|in))(( [A-Z][a-zA-z]*)*)"));

		} else {
			Pattern.compile("((?i)(master|doctor)(s)? (of|in))(( [A-Z][a-zA-z]*)*)");
			result = findCommon(
					fontGroupings,
					Pattern.compile("((?i)(doctor)(s)? (of|in))(( [A-Z][a-zA-z]*)*)"));
		}

		return result;
	}

	// =========================================================================
	// Unimplemented

	@Override
	public String findDiscipline() {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public String findSupervisors() {
		String results = "";
		Pattern p = Pattern
				.compile("(supervisor(s?), )(([A-Z]([a-z]*)('?)([a-z]+)((-| |\\. )|\\b))+)((and |, )(([A-Z]([a-z]*)('?)([a-z]+)((-| |\\. )|\\b))+))*");

		ArrayList<FontGroupBlock> possibleIndexes = new ArrayList<FontGroupBlock>();
		for (int i = this.titleIndex; i < this.fontGroupings.size(); i++) {
			if (fontGroupings.get(i).getText().toLowerCase()
					.contains("acknowledgment")) {
				if (i + 1 < fontGroupings.size())
					possibleIndexes.add(fontGroupings.get(i + 1));
			}
		}

		String returnVal = findCommon(possibleIndexes, p);
		returnVal = returnVal.replaceAll("(?i)supervisor(s?), ", "").replaceAll(" (?!\\b)","");

		return returnVal;
	}

	// =========================================================================
	// Private helper methods
	private float maxFontSize(int startPage, int endPage) {
		float maxFontSize = 0;

		for (FontGroupBlock fg : this.fontGroupings) {
			if (fg.getFontSize() >= maxFontSize && fg.getPageNum() >= startPage
					&& fg.getPageNum() <= endPage) {
				maxFontSize = fg.getFontSize();
			}
		}
		return maxFontSize;
	}

	private int findOccurrences(String text, Pattern pattern) {
		int count = 0;
		Matcher m = pattern.matcher(text);

		while (m.find()) {
			count++;
		}
		return count;
	}

	private String findCommon(ArrayList<FontGroupBlock> blocks, Pattern p) {
		ArrayList<String> results = new ArrayList<String>();

		for (FontGroupBlock f : blocks) {
			String searchText = f.getText();
			if (fontGroupings.contains(f)
					&& fontGroupings.indexOf(f) + 1 < fontGroupings.size()) {
				searchText = (searchText + " " + (fontGroupings
						.get(fontGroupings.indexOf(f) + 1)).getText())
						.replaceAll("\n?\r", " ").replaceAll("  ", " ");
			}
			Matcher m = p.matcher(searchText);

			while (m.find()) {
				results.add(m.group());
			}
		}

		Map<String, Long> map = results.stream().collect(
				Collectors.groupingBy(w -> w, Collectors.counting()));
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

}
