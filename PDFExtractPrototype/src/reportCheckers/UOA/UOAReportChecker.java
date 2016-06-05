package reportCheckers.UOA;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import reportCheckers.ReportChecker;
import main.MetadataStorer;
import helperClasses.FontGroupBlock;

public class UOAReportChecker implements ReportChecker {

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();
	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);
	int titleIndex = 0;
	int titlePage = -1;
	
	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings){
		this.fontGroupings = fontGroupings;
	}
	
	@Override
	public MetadataStorer getAllMeta(MetadataStorer ms){
		if(fontGroupings.size() == 0)
			return ms;
		
		ms.setTitle(findTitle());
		ms.setAuthors(findAuthor());
		ms.setAbstractx(findAbstract());
		ms.setDegree(findDegree());
		ms.setDegreeDiscp(findDiscipline());
		return ms;
	}
	
	@Override
	public String findTitle() {
		float max = maxFontSize();
		String title = "";
		for (FontGroupBlock fgb : this.fontGroupings){
			if(fgb.getFontSize() == max && fgb.getPageNum() <= 3){
				this.titleFGB = fgb;
				this.titlePage = fgb.getPageNum();
				title = fgb.getText();
			}
		}
		return title;
	}

	@Override
	public String[] findAuthor() {
		String[] author = {""};
		boolean isName;
		for (int i=0; i< this.fontGroupings.size(); i++){
			if(this.fontGroupings.get(i) == this.titleFGB){
				this.titleIndex = i;
			}		
		}
		for (int i=this.titleIndex + 1; i< this.fontGroupings.size(); i++){
			isName = true;
			author[0] = this.fontGroupings.get(i).getText();
			String authorName[] = author[0].split(" ");
				
			for(String name : authorName){
				if (!Character.isUpperCase(name.charAt(0))){
					isName = false;
				}
			}
			if(isName){
				return author;
			}
		}
		return author;
	}

	@Override
	public String findAbstract() {
		boolean isAbstract = false;
		int abstIndex = 0;
		String abst;
		String abstContent = "";
		while (!isAbstract){
			for (int i=this.titleIndex + 1; i< this.fontGroupings.size(); i++){
				abst = this.fontGroupings.get(i).getText();
				if (abst.toLowerCase().equals("abstract")){
					abstIndex = i;
					isAbstract = true;
				}
			}
		}
		abstContent = this.fontGroupings.get(abstIndex + 1).getText();
		return abstContent;
	}
	
	@Override
	public String findDegree() {

		ArrayList<FontGroupBlock> searchBlocks = new ArrayList<FontGroupBlock>();
		//Find appropriate blocks to search through
		if(titlePage != -1){
			for (FontGroupBlock fb : fontGroupings){
				if (fb.getPageNum() == titlePage){
					searchBlocks.add(fb);
				}
			}
		}
		
		//Search for master/doctor word count in likely places
		int mastersCount = 0;
		int doctorsCount = 0;
		
		ArrayList<FontGroupBlock> foundMaster = new ArrayList<FontGroupBlock>();
		ArrayList<FontGroupBlock> foundDoctor = new ArrayList<FontGroupBlock>();
		
		if(searchBlocks.size() > 0){
			for(FontGroupBlock fb : searchBlocks){
				int masterIncr = findOccurrences(fb.getText(), Pattern.compile("\\bmaster(s)?\\b",Pattern.CASE_INSENSITIVE));
				int doctorIncr = findOccurrences(fb.getText(), Pattern.compile("\\bdoctor(s)?\\b",Pattern.CASE_INSENSITIVE));
				
				if(masterIncr != 0){
					foundMaster.add(fb);
					mastersCount += masterIncr;
				}
				
				if(doctorIncr != 0){
					foundDoctor.add(fb);
					doctorsCount += doctorIncr;
				}
				
			}
		}
		
		//If not found, search everywhere
		if(mastersCount + doctorsCount == 0){
			for(FontGroupBlock fb : fontGroupings){
				int masterIncr = findOccurrences(fb.getText(), Pattern.compile("\\bmaster(s)?\\b",Pattern.CASE_INSENSITIVE));
				int doctorIncr = findOccurrences(fb.getText(), Pattern.compile("\\bdoctor(s)?\\b",Pattern.CASE_INSENSITIVE));
				
				if(masterIncr != 0){
					foundMaster.add(fb);
					mastersCount += masterIncr;
				}
				
				if(doctorIncr != 0){
					foundDoctor.add(fb);
					doctorsCount += doctorIncr;
				}
				
			}
		}
		
		//Generating heading regex to find degree name
		String type = "";
		if(mastersCount > doctorsCount){
			type = "((?i)\\bmaster(s)?\\b)";
		}
		else if(doctorsCount > mastersCount){
			type = "((?i)\\bdoctor(s)?\\b)";
		}
		else{
			type = "((?i)\\b(doctor|master)(s)?\\b)";
		}
		
		//Find degree
		
		return null;
	}

	@Override
	public String findDiscipline() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public float maxFontSize() {
		float maxFontSize = 0;
		
		for(FontGroupBlock fg : this.fontGroupings) {
			if (fg.getFontSize() >= maxFontSize){
				maxFontSize = fg.getFontSize();
			}
		}		
		return maxFontSize;
	}
	
	//=========================================================================
	//Unimplemented

	@Override
	public String findSubtitle() {
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
	
	//=========================================================================
	//Private helper methods
	private int findOccurrences(String text, Pattern pattern){
		int count = 0;
		Matcher m = pattern.matcher(text);
		
		while(m.find()){
			count++;
		}
		return count;
	}
}
