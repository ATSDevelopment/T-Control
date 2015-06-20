package atscomponents;
import java.awt.Color;

import javax.swing.JTextField;


public class TitledTextField extends JTextField{
	
	private String title;
	private TitledDocument doc;
	private Color titleColor;
	private Color normalColor;
	
	public TitledTextField(String title, Color titleColor, Color normalColor, TitledDocument doc){
		super();
		
		this.titleColor = titleColor;
		this.normalColor = normalColor;
		this.doc = doc;
		
		this.setDocument(doc);
		doc.setTitledTextField(this);
	}
	public TitledTextField(String title, TitledDocument doc){
		this(title, Color.gray, Color.black, doc);
	}
	public TitledTextField(String title){
		this(title, new TitledDocument(title));
	}
	
	public void setTitleColor(Color color){
		this.titleColor = color;
	}
	public void setNormalColor(Color color){
		this.normalColor = color;
	}
	
	public void setTitleMode(boolean mode){
		this.setForeground(mode ? titleColor:normalColor);
	}
}