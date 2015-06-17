package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datamanager.connection.ConnectionManager;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.funcionario.Funcionario;

public class FuncionarioDAO implements DataAccessObject<Funcionario>{
	@Override
	public DataAccessResponse salvar(Funcionario entity, boolean novo) {
		DataAccessResponse res;
		
		Connection conexao = ConnectionManager.get();
		
		if(novo){
			try {
				PessoaDAO pessoaDAO = new PessoaDAO();
				
				DataAccessResponse resPessoa = pessoaDAO.salvar(entity, novo);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				
				DataAccessResponse resUsuario = usuarioDAO.salvar(entity.getUsuario(), novo);
				
				if(resPessoa.getStatus()){
					
					int idPessoa = (Integer) resPessoa.getResponse();
					
					int idUsuario = (Integer) resUsuario.getResponse();
					
					PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionarios VALUES (?, ?, ?, ?, ?)");
					
					ps.setInt(1, idPessoa);
					ps.setString(2, entity.getTelefone());
					ps.setString(3, entity.getEmail());
					ps.setString(4, entity.getDataSaida());
					ps.setInt(5, idUsuario);
					
					ps.execute();
					
					ps.close();
					
					res = new DataAccessResponse(true, ResponseType.INTEGER, idPessoa);
					
				}else{
					res = resPessoa;
				}
			} catch (SQLException e) {
				res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
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
