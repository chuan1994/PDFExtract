package reportCheckers;

import java.util.ArrayList;
import java.util.Date;

import main.MetadataStorer;
import helperClasses.FontGroupBlock;

public class UOAReportChecker implements ReportChecker {

	ArrayList<FontGroupBlock> fontGroupings = new ArrayList<FontGroupBlock>();
	FontGroupBlock titleFGB = new FontGroupBlock(null, 0, null, 0);
	int titleIndex = 0;
	
	public UOAReportChecker(ArrayList<FontGroupBlock> fontGroupings){
		this.fontGroupings = fontGroupings;
	}
	
	@Override
	public MetadataStorer getAllMeta(MetadataStorer ms){
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
			if(fgb.getFontSize() == max){
				this.titleFGB = fgb;
				title = fgb.getText();
			}
		}
		return title;
	}

	@Override
	public String[] findAuthor() {
		String author = "";
		String authorArray[] = new String[]{null};
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
				} else {
					isName = true;
				}
			}
			if(isName){
				authorArray[0] = author;
				return authorArray;
			}
		}
		return authorArray;
	}


	@Override
	public String findAbstract() {
		boolean isAbstract = false;
		int abstIndex = 0;
		String abst;
		String abstContent = "";

		for (int i=this.titleIndex + 1; i< this.fontGroupings.size(); i++){
			abst = this.fontGroupings.get(i).getText();
			if (abst.toLowerCase().equals("abstract")){
				abstIndex = i;
				isAbstract = true;
			}
		}

		abstContent = this.fontGroupings.get(abstIndex + 1).getText();
		
		return abstContent;

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
	
	public float maxFontSize() {
		float maxFontSize = 0;
		
		
		for(FontGroupBlock fgb : this.fontGroupings) {
			if (fgb.getFontSize() >= maxFontSize && fgb.getPageNum() <= 3){
				maxFontSize = fgb.getFontSize();
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

	
}
