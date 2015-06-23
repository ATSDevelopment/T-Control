package client.view.nodes;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import client.bll.SetorBLL;
import client.view.forms.AbstractForm;
import client.view.forms.FormSetor;
import datamanager.dao.DataAccessResponse;
import entity.Setor;

public class NodeSetor extends AbstractNode<Setor>{
	private static final long serialVersionUID = 1L;
	private FormSetor form;
	public NodeSetor() {
		super("Setores");
		
		form = new FormSetor();
	}
	
	@Override
	public void load() {
		SetorBLL bll = new SetorBLL();
		
		DataAccessResponse res = bll.listar();
				
		if(res.getStatus()){
			ArrayList<Setor> setores = (ArrayList<Setor>) res.getResponse();
			
			for(Setor s : setores){
				DefaultMutableTreeNode node = new DefaultMutableTreeNode();
				
				node.setUserObject(s);
				
				this.add(node);
			}
		}
	}

	@Override
	public AbstractForm<Setor> getForm() {
		return form;
	}

}
