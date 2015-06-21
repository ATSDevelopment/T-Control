package atsmod.textfield;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import atsmod.textfield.documents.DefaultTitledDocument;
import atsmod.textfield.documents.PasswordTitledDocument;

public class TitledTextField extends JTextField implements TitleModeListener{
	private Color titled = Color.gray, untitled=Color.black;
	private InputType inputType;
	public TitledTextField(String title, InputType inputType){
		this.inputType = inputType;

		switch(inputType){
		case ALL:
			this.setDocument(new DefaultTitledDocument(title, this));
			break;
		case PASSWORD:
			this.setDocument(new PasswordTitledDocument(title, this));
			break;
		}
	}
	@Override
	public void setTitleMode(boolean titleMode) {
		this.setForeground(titleMode ? titled:untitled);
	}
	
	@Override
	public String getText(){
		try {
			return ((DefaultTitledDocument) this.getDocument()).getTextT();
		} catch (BadLocationException e) {
			e.printStackTrace();
			return "";
		}
	}
}
