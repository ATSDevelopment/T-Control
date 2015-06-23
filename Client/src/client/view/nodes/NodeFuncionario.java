package client.view.nodes;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import client.bll.FuncionarioBLL;
import client.view.forms.FormFuncionario;
import datamanager.dao.DataAccessResponse;
import entity.funcionario.Funcionario;

public class NodeFuncionario extends AbstractNode<Funcionario>{
	private ArrayList<Funcionario> funcionarios;
	private FormFuncionario form;
	public NodeFuncionario(){
		super("Funcionarios");
		
		form = new FormFuncionario();
	}
	public void load(){
		FuncionarioBLL bll = new FuncionarioBLL();
		
		DataAccessResponse res = bll.listar();
		
		if(res.getStatus()){
			ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>)res.getResponse();
			
			for(Funcionario f : funcionarios){
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getNome());
				
				node.setUserObject(f);
				
				this.add(node);
			}
			
			this.funcionarios = funcionarios;
		}
	}
	public FormFuncionario getForm(){
		return form;
	}
}
