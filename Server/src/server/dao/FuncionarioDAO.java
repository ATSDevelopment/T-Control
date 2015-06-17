package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datamanager.connection.ConnectionManager;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import entity.funcionario.Funcionario;

public class FuncionarioDAO implements DataAccessObject<Funcionario>{
	@Override
	public DataAccessResponse salvar(Funcionario entity, boolean novo) {
		Connection conexao = ConnectionManager.get();
		
		if(novo){
			try {
				PreparedStatement ps = conexao.prepareStatement("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
		}
		return null;
	}

	@Override
	public DataAccessResponse deletar(Funcionario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataAccessResponse getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataAccessResponse listar() {
		// TODO Auto-generated method stub
		return null;
	}
}
