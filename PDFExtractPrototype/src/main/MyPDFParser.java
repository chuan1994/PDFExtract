package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import org.apache.pdfbox.pdmodel.*;

import reportCheckers.ReportExtractor;
import reportCheckers.UOA.UOAReportChecker;
import reportCheckers.UOA.UOAThesisExtractor;
import helperClasses.FontGroup;
import helperClasses.MyTextStripper;
import helperClasses.PageDimensionCalc;

/**
 * this class is responsible for obtaining the metadata information
 * It extends swingworker to run the extraction on its own thread
 * @author cwu323
 *
 */
public class MyPDFParser extends SwingWorker<Void, Void> {
	private String path;
	private File pdf;
	private File outputFolder;
	private PDDocument pdDoc;
	private MetadataStorer meta = new MetadataStorer();
	private PageDimensionCalc pdCalc = new PageDimensionCalc();
	private MyTextStripper myStripper;
	private OutputPrinter OP = new OutputPrinter();

	public MyPDFParser(String path, File pdf, File outputFolder) {
		this.path = path;
		this.pdf = pdf;
		this.outputFolder = outputFolder;
		setup();
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		parseAll();
		return null;
	}
	
	/**
	 * Returns the metadataStorer in its current state.
	 * 
	 * @return
	 */
	public MetadataStorer getMetaData() {
		return this.meta;
	}

	/**
	 * Method to mine all metadata
	 */
	public void parseAll() {

		try {
			StringBuilder sb = new StringBuilder();

			// Retrieves the text in the first 15 pages of the document
			for (int i = 1; i <= 15; i++) {
				myStripper.setStartPage(i);
				myStripper.setEndPage(i);
				sb.append("\n" + myStripper.getText(pdDoc));
			}


			String text = sb.toString();

			//Parsing information and extracting using reportCheckers
			ArrayList<FontGroup> textGroups2 = splitOutcome(text);
			ReportExtractor checker = new UOAThesisExtractor(textGroups2);
			addSurfaceMeta();
			meta = checker.getAllMeta(meta);
			
//			printToConsole();
			writeToFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to close the pdDoc to stop resource leaking
	 */
	public void close() {
		try {
			pdDoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =============================================================================
	// PRIVATE METHODS
	/**
	 * Method to setup this parser. Ensures file is loaded and creates a text
	 * stripper for it.
	 */
	private void setup() {
		this.pdDoc = new PDDocument();
		try {
			pdDoc = pdDoc.load(pdf);

			myStripper = new MyTextStripper();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to get simple metadata. Such metadata does not need processing and
	 * analysis.
	 */
	private void addSurfaceMeta() {
		meta.setPageLength(pdDoc.getNumberOfPages());
		meta.setPageSize(pdCalc.getPageSize(pdDoc.getPages()));
	}
	
	/**
	 * Print current results to the output
	 */
	private void printToConsole(){
		System.out.println("===========================================================");
		System.out.println("OUTPUT:");
		OP.JSONPrinter(System.out, meta);
		System.out.println("===========================================================");
	}
			
	/**
	 * Logic to write to the output JSON file
	 */
	private void writeToFile() {
		String outputPath;
		try {
			outputPath = outputFolder.getCanonicalFile() + File.separator + pdf.getName().split("\\.")[0] + ".json";

			File f = new File(outputPath);
			if (!f.exists()) {
				f.createNewFile();
			} else {
				int i = 0;
				while (f.exists()) {
					i++;
					outputPath = outputFolder.getCanonicalPath() + File.separator + pdf.getName().split("\\.")[0] + "("
							+ i + ")" + ".json";

					f = new File(outputPath);
				}
				f.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(f);
			PrintStream ps = new PrintStream(fos);
			OP.JSONPrinter(ps, meta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Accepts a string of extracted text and font information from PDF
	 * documents. Parses it to split the text into the corresponding fonts
	 * 
	 * @param text
	 * @return
	 */
	private ArrayList<FontGroup> splitOutcome(String text) {
		ArrayList<FontGroup> fgb = new ArrayList<FontGroup>();
		Pattern pattern = Pattern.compile("(\\[.*,.*\\] )(((.+|\\s+)(?!(\\[.*,.*\\] )))*)");

		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String[] fontInfo = matcher.group(1).split(",");
			String font = fontInfo[0].replaceAll("(\\[|\\s+)", "");
			float size = Float.parseFloat(fontInfo[fontInfo.length - 2].replaceAll("\\s+", ""));
			int pageNum = Integer.parseInt(fontInfo[fontInfo.length - 1].replaceAll("(\\]|\\s+)", ""));
			String texts = matcher.group(2);

			FontGroup fg = new FontGroup(font, size, texts, pageNum);
			fgb.add(fg);
		}

		return fgb;
	}

}