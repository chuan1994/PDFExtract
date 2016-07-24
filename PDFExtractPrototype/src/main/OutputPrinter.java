package main;

import java.io.PrintStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class OutputPrinter {

	/**
	 * Print out collected metadata;
	 */
	public void simplePrinter(PrintStream ps, MetadataStorer ms) {
		ps.print("Title: ");
		ps.println(ms.getTitle());
		ps.println();

		ps.print("Alternative/Sub Title: ");
		ps.println(ms.getAltTitle());
		ps.println();
		
		ps.print("Authors: ");
		if(ms.getAuthors() == null){
			ps.println(ms.getAuthors());
		}
		else if (ms.getAuthors().length > 0) {
			for (int i = 0; i < ms.getAuthors().length - 1; i++) {
				ps.print(ms.getAuthors()[i] + ", ");
			}
			ps.println(ms.getAuthors()[ms.getAuthors().length - 1]);
		}else{
			ps.println();
		}
		ps.println();

		ps.print("Supervisors: ");
		ps.println(ms.getSupervisors());
		ps.println();

		ps.print("Degree: ");
		ps.println(ms.getDegree());
		ps.println();

		ps.print("Degree Discipline: ");
		ps.println(ms.getDegreeDiscp());
		ps.println();

		ps.print("Abstract: ");
		ps.println(ms.getAbstractx());
		ps.println();

		ps.print("Number of Pages: ");
		ps.println(ms.getPageLength());
		ps.println();

		ps.print("Page Size: ");
		ps.println(ms.getPageSize());
		ps.println();
	}
	
	public void JSONPrinter(PrintStream ps, MetadataStorer ms){
		ObjectMapper mapper = new ObjectMapper();
			try {
				String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ms);
				System.out.println(output);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
