package client.view.nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import client.view.forms.AbstractForm;

public abstract class AbstractNode<Entity> extends DefaultMutableTreeNode{
	public AbstractNode(String title){
		super(title);
	}
	public abstract void load();
	public abstract AbstractForm<Entity> getForm();
}
