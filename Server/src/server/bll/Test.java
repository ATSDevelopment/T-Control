package server.bll;

import java.util.ArrayList;

import datamanager.connection.ConnectionManager;
import datamanager.dao.DataAccessResponse;
import entity.Usuario;
import entity.funcionario.Funcionario;
import entity.funcionario.MapaDeServico;

public class Test {
	public static void main(String[] args){
		ConnectionManager.open();
		
		Usuario u = new Usuario(0, "carlos.rodrigues", "asd", true, null);
		Funcionario f = new Funcionario(0, "Carlos Souza Rodrigues Filho", "", "", null, u, new ArrayList<MapaDeServico>());
		
		FuncionarioBLL bll = new FuncionarioBLL();
		DataAccessResponse res = bll.listar();
		
		System.out.println(res.getStatus()+" : "+res.getResponse());
		
		f = (Funcionario) ((ArrayList)res.getResponse()).get(0);
		
		System.out.println(f.getUsuario());
		ConnectionManager.close();
	}
}