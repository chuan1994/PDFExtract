package reportCheckers;

import java.util.Date;

import main.MetadataStorer;

/**
 * Interface with all the methods to extract the identified entities
 * Implement to write own logic to extract entities
 * @author chuan
 *
 */
public interface ReportExtractor {

	public String findTitle();
	public String findSubtitle();
	public String[] findAuthor();
	public String findDegree();
	public String findDiscipline();
	public String findSupervisors();
	public Date findPubDate();
	public String findAbstract();
	public String findPublisher();
	public String findUUID();
	public MetadataStorer getAllMeta(MetadataStorer ms);
}
