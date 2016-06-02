package reportCheckers;

import java.util.ArrayList;

import main.MetadataStorer;
import helperClasses.FontGroupBlock;

public class UOAReportChecker implements ReportChecker {

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();
	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);
	int titleIndex = 0;
	
	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings){
		this.fontGroupings = fontGroupings;
	}
	
	public MetadataStorer getAllMeta(MetadataStorer ms){
		ms.setTitle(findTitle());
		ms.setAuthors(findAuthor());
		ms.setAbstractx(findAbstract());
		
		return ms;
	}
	
	@Override
	public String findTitle() {
		float max = maxFontSize();
		String title = "";
		for (FontGroupBlock fgb : this.fontGroupings){
			if(fgb.getFontSize() == max && fgb.getPageNum() <= 3){
				this.titleFGB = fgb;
				title = fgb.getText();
			}
		}
		return title;
	}

	@Override
	public String findAuthor() {
		String author = "";
		boolean isName;
		for (int i=0; i< this.fontGroupings.size(); i++){
			if(this.fontGroupings.get(i) == this.titleFGB){
				this.titleIndex = i;
			}		
		}
		for (int i=this.titleIndex + 1; i< this.fontGroupings.size(); i++){
			isName = true;
			author = this.fontGroupings.get(i).getText();
			String authorName[] = author.split(" ");
				
			for(String name : authorName){
				if (!Character.isUpperCase(name.charAt(0))){
					isName = false;
				}
			}
			if(isName){
				return author;
			}
		}
		return null;
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
	
	public float maxFontSize() {
		float maxFontSize = 0;
		
		for(FontGroupBlock fg : this.fontGroupings) {
			if (fg.getFontSize() >= maxFontSize){
				maxFontSize = fg.getFontSize();
			}
		}		
		return maxFontSize;
	}
}
