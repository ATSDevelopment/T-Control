package atsmod.textfield.documents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import atsmod.textfield.TitleModeListener;

public class DefaultTitledDocument extends PlainDocument{
	private String title;
	private TitleModeListener listener;
	private boolean titleMode;
	public DefaultTitledDocument(String title, TitleModeListener listener){
		this.title = title;
		this.listener = listener;
		try {
			super.insertString(0, title, null);
		} catch (BadLocationException e) {}
		setTitleMode(true);
	}
	@Override
	public void insertString(int off, String text, AttributeSet att) throws BadLocationException{
		int length = super.getLength();
		
		String currentText = super.getText(0, length);
		
		if(currentText.equals(this.title)){
			super.remove(0, length);
			
			off = 0;
		}
		
		super.insertString(off, modInsert(off, text), att);
		
		setTitleMode(false);
	}
	public String modInsert(int off, String text){
		return text;
	}
	@Override
	public void remove(int off, int length) throws BadLocationException{
		int currentLength = super.getLength();
		
		String currentText = super.getText(0, currentLength);
		
		if(currentText.equals(this.title)){
			return;
		}
		
		super.remove(off, length);
		
		if(super.getLength()==0){
			super.insertString(0, this.title, null);
			
			setTitleMode(true);
		}
	}
	
	private void setTitleMode(boolean titleMode){
		this.titleMode = titleMode;
		listener.setTitleMode(titleMode);
	}
	
	public boolean isTitledMode(){
		return titleMode;
	}
	
	public String getTextT() throws BadLocationException{
		if(isTitledMode()){
			return null;
		}else{
			return super.getText(0, super.getLength());
		}
	}
}
