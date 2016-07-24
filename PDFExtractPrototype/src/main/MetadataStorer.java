package main;

public class MetadataStorer {
	private String title;
	private String altTitle;
	private String[] authors = {};
	private String supervisors;
	private String degree;
	private String degreeDiscp;
	private String abstractx;

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
	
}
