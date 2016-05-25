package helperClasses;

public class FontGroupBlock {
	private String font;
	private float fontSize;
	private String text;
	
	public FontGroupBlock(String font, float fontSize, String text){
		this.font = font;
		this.fontSize = fontSize;
		this.text = text;
	}
	
	public String getFont(){
		return this.font;
	}
	
	public float getFontSize(){
		return this.fontSize;
	}
	
	public String getText(){
		return this.text;
	}
}
