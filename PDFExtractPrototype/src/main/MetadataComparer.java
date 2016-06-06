package main;

public class MetadataComparer {

	public MetadataStorer mergeByNull(MetadataStorer m1, MetadataStorer m2){
		MetadataStorer returnVal = new MetadataStorer();
		if(m1.getTitle() != null && !m1.getTitle().equals(""))
			returnVal.setTitle(m1.getTitle());
		else
			returnVal.setTitle(m2.getTitle());
		
		if(m1.getAltTitle() != null && !m1.getAltTitle().equals(""))
			returnVal.setAltTitle(m1.getAltTitle());
		else
			returnVal.setAltTitle(m2.getAltTitle());

		if(m1.getAuthors() != null && m1.getAuthors().length != 0)
			returnVal.setAuthors(m1.getAuthors());
		else
			returnVal.setAuthors(m2.getAuthors());
		
		if(m1.getSupervisors() != null && !m1.getSupervisors().equals(""))
			returnVal.setSupervisors(m1.getSupervisors());
		else
			returnVal.setSupervisors(m2.getSupervisors());
		
		if(m1.getDegree() != null && !m1.getDegree().equals(""))
			returnVal.setDegree(m1.getDegree());
		else
			returnVal.setDegree(m2.getDegree());
		
		if(m1.getDegreeDiscp() != null && !m1.getDegreeDiscp().equals(""))
			returnVal.setDegreeDiscp(m1.getDegreeDiscp());
		else
			returnVal.setDegreeDiscp(m2.getDegreeDiscp());
		
		if(m1.getAbstractx() != null && !m1.getAbstractx().equals(""))
			returnVal.setAbstractx(m1.getAbstractx());
		else
			returnVal.setAbstractx(m2.getAbstractx());
		
		if(m1.getPublisher() != null && !m1.getPublisher().equals(""))
			returnVal.setPublisher(m1.getPublisher());
		else
			returnVal.setPublisher(m2.getPublisher());
		
		if(m1.getUUID() != null && !m1.getUUID().equals(""))
			returnVal.setUUID(m1.getUUID());
		else
			returnVal.setUUID(m2.getUUID());
		
		if(m1.getPubDate() != null )
			returnVal.setPubDate(m1.getPubDate());
		else
			returnVal.setPubDate(m2.getPubDate());
		
		if(m1.getPageLength() != 0)
			returnVal.setPageLength(m1.getPageLength());
		else
			returnVal.setPageLength(m2.getPageLength());
		
		if(m1.getPageSize() != null && !m1.getPageSize().equals(""))
			returnVal.setPageSize(m1.getPageSize());
		else
			returnVal.setPageSize(m2.getPageSize());
		
		return returnVal;
	}
}
