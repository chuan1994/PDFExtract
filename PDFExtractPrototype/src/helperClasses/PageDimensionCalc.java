package helperClasses;

import java.util.Arrays;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

/**
 * Class to calculate the size of the pdf pages
 * Only considers ISO A paper sizes
 * 
 * @author chuan
 *
 */
public class PageDimensionCalc {
	int A1 = 0;
	int A2 = 0;
	int A3 = 0;
	int A4 = 0;
	int A5 = 0;
	int A6 = 0;
	int A0 = 0;
	int other = 0;
	public String getPageSize(PDPageTree pages) {
		for (PDPage p : pages){
			addSize(p.getMediaBox().getHeight(), p.getMediaBox().getWidth());
		}
		
		return getBiggest();
	}
	
	private void addSize(float height, float width){
		if(floatComparator(height, 1684 , 1)&& floatComparator(width, 2384 ,1)){
			A0++;
		}else if(floatComparator(height, 1684 , 1)&& floatComparator(width, 2384 ,1)){
			A1++;
		}else if(floatComparator(height, 1191 , 1)&& floatComparator(width, 1684 ,1)){
			A2++;
		}else if(floatComparator(height, 842 , 1)&& floatComparator(width, 1191 , 1)){
			A3++;
		}else if(floatComparator(height, 595 , 1)&& floatComparator(width, 842 , 1)){
			A4++;
		}else if(floatComparator(height, 420 , 1)&& floatComparator(width, 595 , 1)){
			A5++;
		}else if(floatComparator(height, 298 , 1)&& floatComparator(width, 420 ,1)){
			A6++;
		}else {
			other++;
		}
	}
	
	private boolean floatComparator(float x0, float x1, int allowedDist){
		
		if(Math.abs(x0-x1)<=allowedDist){
			return true;
		}
		return false;
	}
	
	private String getBiggest(){
		int largest = Collections.max(Arrays.asList(A0,A1,A2,A3,A4,A5,A6,other));
		
		if(largest == A0){
			return "A0";
		}else if(largest == A1){
			return "A1";
		}else if(largest == A2){
			return "A2";
		}else if(largest == A3){
			return "A3";
		}else if(largest == A4){
			return "A4";
		}else if(largest == A5){
			return "A5";
		}else if(largest == A6){
			return "A1";
		}else {
			return "other";
		}
	}
}
