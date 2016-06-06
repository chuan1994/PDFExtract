package main;

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
	public void print() {
		System.out.print("Title: ");
		System.out.println(this.title);

		System.out.print("Alternative/Sub Title: ");
		System.out.println(this.altTitle);

		System.out.print("Authors: ");
		if(authors == null){
			System.out.println(this.authors);
		}
		else if (authors.length > 0) {
			for (int i = 0; i < authors.length - 1; i++) {
				System.out.print(this.authors[i] + ", ");
			}
			System.out.println(this.authors[authors.length - 1]);
		}else{
			System.out.println();
		}

		System.out.print("Supervisors: ");
		System.out.println(this.supervisors);

		System.out.print("Degree: ");
		System.out.println(this.degree);

		System.out.print("Degree Discipline: ");
		System.out.println(this.degreeDiscp);

		System.out.print("Abstract: ");
		System.out.println(this.abstractx);

		System.out.print("Publisher: ");
		System.out.println(this.publisher);

		System.out.print("UUID: ");
		System.out.println(this.UUID);

		System.out.print("Publication Date: ");
		System.out.println(this.pubDate);

		System.out.print("Number of Pages: ");
		System.out.println(this.pageLength);

		System.out.print("Page Size: ");
		System.out.println(this.pageSize);
	}
	
	
}
