package atscomponents;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class TitledDocument extends PlainDocument{
	public String title;
	public TitledTextField textField;
	public TitledDocument(String title){
		this.title = title;
		try {
			this.remove(0, 0);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setTitledTextField(TitledTextField textField){
		this.textField = textField;
	}
	@Override
	public void insertString(int off, String text, AttributeSet att) throws BadLocationException{
		boolean caret = false;
		String texto = this.getText(0, this.getLength());
		
		if(texto.equals(title)){
			super.remove(0, this.getLength());
			off = 0;
			textField.setTitleMode(false);
		}
		
		super.insertString(off, getTextModifiers(text), att);
	}
	
	public String getTextModifiers(String text){
		return text;
	}

	@Override
	public void remove(int off, int length) throws BadLocationException{
		super.remove(off, length);
		if(this.getLength()==0){
			super.insertString(0, title, null);
			//textField.setCaretPosition(0);
			//textField.setTitleMode(true);
		}
	}
}
