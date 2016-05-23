package prototype;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class myPDFStripper extends PDFTextStripper{

	public myPDFStripper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void writeString(String text, List<TextPosition> textPositions){
		
	}
}
