package main;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

public class MetadataStorer {
	private String title;
	private String altTitle;
	private String[] authors = {};
	private String supervisors;
	private String degree;
	private String degreeDiscp;
	private String abstractx;
	private String publisher;
	private String UUID;

	private Date pubDate;

	private int pageLength;
	private String pageSize;

	// need representation of page size;

	// =================================================
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// =================================================
	public String getAltTitle() {
		return altTitle;
	}

	public void setAltTitle(String altTitle) {
		this.altTitle = altTitle;
	}

	// =================================================
	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	// =================================================
	public String getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(String supervisors) {
		this.supervisors = supervisors;
	}

	// =================================================
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	// =================================================
	public String getDegreeDiscp() {
		return degreeDiscp;
	}

	public void setDegreeDiscp(String degreeDiscp) {
		this.degreeDiscp = degreeDiscp;
	}

	// =================================================
	public String getAbstractx() {
		return abstractx;
	}

	public void setAbstractx(String abstractx) {
		this.abstractx = abstractx;
	}

	// =================================================
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	// =================================================
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	// =================================================
	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	// =================================================
	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	// =================================================
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	// =================================================
	/**
	 * Print out collected metadata;
	 */
	public void print(PrintStream ps) {
		ps.print("Title: ");
		ps.println(this.title);
		ps.println();

		ps.print("Alternative/Sub Title: ");
		ps.println(this.altTitle);
		ps.println();
		
		ps.print("Authors: ");
		if(authors == null){
			ps.println(this.authors);
		}
		else if (authors.length > 0) {
			for (int i = 0; i < authors.length - 1; i++) {
				ps.print(this.authors[i] + ", ");
			}
			ps.println(this.authors[authors.length - 1]);
		}else{
			ps.println();
		}
		ps.println();

		ps.print("Supervisors: ");
		ps.println(this.supervisors);
		ps.println();

		ps.print("Degree: ");
		ps.println(this.degree);
		ps.println();

		ps.print("Degree Discipline: ");
		ps.println(this.degreeDiscp);
		ps.println();

		ps.print("Abstract: ");
		ps.println(this.abstractx);
		ps.println();

		ps.print("Publisher: ");
		ps.println(this.publisher);
		ps.println();

		ps.print("UUID: ");
		ps.println(this.UUID);
		ps.println();

		ps.print("Publication Date: ");
		ps.println(this.pubDate);
		ps.println();

		ps.print("Number of Pages: ");
		ps.println(this.pageLength);
		ps.println();

		ps.print("Page Size: ");
		ps.println(this.pageSize);
		ps.println();
	}
	
	
}
