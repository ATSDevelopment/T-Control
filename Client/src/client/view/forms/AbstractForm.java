package client.view.forms;

import javax.swing.JPanel;

public abstract class AbstractForm<ObjectEdit> extends JPanel {
	public AbstractForm() {
	}
	public abstract void setObjectEdit(ObjectEdit obj);
	public abstract ObjectEdit getEditedObject();
	public abstract void resetForm();
}
