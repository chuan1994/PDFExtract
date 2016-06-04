package reportCheckers;

import java.util.Date;

import main.MetadataStorer;

public interface ReportChecker {

	public String findTitle();
	public String findSubtitle();
	public String[] findAuthor();
	public String findDegree();
	public String findDiscipline();
	public Date findPubDate();
	public String findAbstract();
	public String findPublisher();
	public String findUUID();
	public MetadataStorer getAllMeta(MetadataStorer ms);
}
