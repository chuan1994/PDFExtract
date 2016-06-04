package reportCheckers;

import java.util.ArrayList;
import java.util.Date;

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
		StringBuilder sb = new StringBuilder();
		if(titlePage != -1){
			for (FontGroupBlock fb : fontGroupings){
				if (fb.getPageNum() == titlePage){
					sb.append(" " + fb.getText().replaceAll("\\s+", " "));
				}
			}
		}else{
			for (FontGroupBlock fb : fontGroupings){
				sb.append(" " + fb.getText().replaceAll("\\s+", " "));
			}
		}

		String text = sb.toString();
		
		int mastersCount = findOccurrences(text, "master");
		int doctorsCount = findOccurrences(text, "doctor");
		
		if(mastersCount > doctorsCount){
			return "Masters";
		}
		else if(doctorsCount > mastersCount){
			return "Doctors";
		}
		
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
	//Unimplemented
	private int findOccurrences(String text, String subString){
		int lastIndex = 0;
		int count = 0;
		
		text = text.toLowerCase();
		subString = subString.toLowerCase();

		while(lastIndex != -1){
		    lastIndex = text.indexOf(subString,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += subString.length();
		    }
		}
		return count;
	}
}
