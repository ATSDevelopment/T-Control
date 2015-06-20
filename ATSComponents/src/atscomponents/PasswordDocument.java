package atscomponents;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;


public class PasswordDocument extends TitledDocument{
	private StringBuilder buffer;
	public PasswordDocument(String title) {
		super(title);
		
		buffer = new StringBuilder();
	}
	
	@Override
	public void insertString(int off, String text, AttributeSet att) throws BadLocationException{
		int i=off;
		for(char c : text.toCharArray()){
			buffer.append(text.toCharArray(), off, text.length());
		}
		super.insertString(off, text, att);
	}
	@Override
	public String getTextModifiers(String text){
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<text.length(); i++){
			builder.append('*');
		}
		return builder.toString();
	}
}
