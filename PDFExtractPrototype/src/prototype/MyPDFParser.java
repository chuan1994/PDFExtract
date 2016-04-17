package prototype;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class MyPDFParser {
	String path;
	File pdf;
	
	BodyContentHandler handler = new BodyContentHandler();
	Metadata metadata = new Metadata();
	ParseContext context = new ParseContext();
	PDFParser parser = new PDFParser();
	
	
	public MyPDFParser(String path, File pdf, File outputFolder){
		this.path = path;
		this.pdf = pdf;
		
	}
	
	public void parse() throws IOException, SAXException, TikaException{
		FileInputStream fis = new FileInputStream(pdf);
		parser.parse(fis, handler, metadata, context);
		
		System.out.println("Contents of the PDF: ");
		System.out.println(handler.toString());
		
		System.out.println("Metadata of the PDF: ");
		for(String name: metadata.names()){
			System.out.println(name + " : " + metadata.get(name));
		}
	}
}
