package reportCheckers;

import java.util.ArrayList;

import helperClasses.FontGroupBlock;

public class UOAReportChecker implements ReportChecker {

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();
	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);
	
	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings){
		this.fontGroupings = fontGroupings;
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
		int titleIndex = 0;
		boolean isName;
		for (int i=0; i< this.fontGroupings.size(); i++){
			if(this.fontGroupings.get(i) == this.titleFGB){
				titleIndex = i;
			}		
		}
		for (int i=titleIndex + 1; i< this.fontGroupings.size(); i++){
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
}
