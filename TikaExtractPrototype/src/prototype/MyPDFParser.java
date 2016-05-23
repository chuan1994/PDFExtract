package prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ExpandedTitleContentHandler;
import org.xml.sax.SAXException;


public class MyPDFParser {
	private String path;
	private File pdf;
	private File outputFolder;
	
	private BodyContentHandler handler = new BodyContentHandler();
	private Metadata metadata = new Metadata();
	private ParseContext context = new ParseContext();
	private PDFParser parser = new PDFParser();
	
	
	public MyPDFParser(String path, File pdf, File outputFolder){
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;
		
	}
	
	public void parse() {
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(pdf);
			parser.parse(fis, handler, metadata, context);
			
			
			System.out.println("Contents of the PDF: ");
			System.out.println(handler.toString());
			
			//System.out.println("Metadata of the PDF: ");
			//for(String name: metadata.names()){
			//	System.out.println(name + " : " + metadata.get(name));
			//}
		} catch (IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeToTxt(){
		String fileName = pdf.getName();
		String[] splitName = fileName.split("\\.");
		splitName[0] = splitName[0] + "(Extracted)";
		splitName[splitName.length - 1] = "." + splitName[splitName.length -1];
		String newName = "";
		
		for(String x : splitName){
			newName = newName + x;
		}
	}
	
	public void writeToHTML(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SAXTransformerFactory factory = (SAXTransformerFactory)
		SAXTransformerFactory.newInstance();
		
		Tika tikaParser = new Tika();
		
		try {
			TransformerHandler handler;
			handler = factory.newTransformerHandler();
			
			handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "html");
			handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
			handler.getTransformer().setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			handler.setResult(new StreamResult(out));
			
			ExpandedTitleContentHandler handler1 = new ExpandedTitleContentHandler(handler);
			
			
			tikaParser.parse(new ByteArrayInputStream(Files.readAllBytes(Paths.get(path))), new Metadata());
			System.out.println( new String(out.toByteArray(), "UTF-8"));
		} catch (IOException | TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
}
