package reportCheckers;

public interface ReportChecker {

	public boolean isTitle(String content, String font, float fontSize);
	public boolean isAuthor(String content, String font, float fontSize);
	public boolean isAbstract(String content, String font, float fontSize);
}
