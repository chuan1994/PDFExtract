package reportCheckers;

import java.util.ArrayList;

public class UOAReportChecker implements ReportChecker {

	ArrayList<String> fontGroupings = new ArrayList<String>();
	
	public UOAReportChecker(ArrayList<String> fontGroupings){
		this.fontGroupings = fontGroupings;
	}
	
	@Override
	public boolean isTitle(String content, String font, float fontSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAuthor(String content, String font, float fontSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAbstract(String content, String font, float fontSize) {
		// TODO Auto-generated method stub
		return false;
	}

}
