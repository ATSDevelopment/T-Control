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

		PessoaDAO pessoaDAO = new PessoaDAO();

		DataAccessResponse resPessoa = pessoaDAO.salvar(entity, novo);

		if(resPessoa.getStatus()){
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			DataAccessResponse resUsuario = usuarioDAO.salvar(entity.getUsuario(), novo);
			
			if(resUsuario.getStatus()){
				if(novo){
					try {
						int idPessoa = (Integer) resPessoa.getResponse();

						int idUsuario = (Integer) resUsuario.getResponse();
						
						res = insert(conexao, idPessoa, idUsuario, entity);
					} catch (SQLException e) {
						res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
					}
				}else{
					try{
						res = update(conexao, entity);
					}catch(SQLException e){
						res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
					}
				}
			}else{
				res = resUsuario;
			}
		}else{
			res = resPessoa;
		}
		return res;
	}
	
	private DataAccessResponse update(Connection conexao, Funcionario entity) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionarios SET telefone=?, email=?, dataSaida=? WHERE id_funcionario=?");
		ps.setString(1, entity.getTelefone());
		ps.setString(2, entity.getEmail());
		ps.setString(3, entity.getDataSaida());
		ps.setInt(4, entity.getId());
		
		ps.execute();
		ps.close();
		
		return new DataAccessResponse(true, ResponseType.NULL, null);
	}
	
	private DataAccessResponse insert(Connection conexao, int idPessoa, int idUsuario, Funcionario entity) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionarios VALUES (?, ?, ?, ?, ?)");

		ps.setInt(1, idPessoa);
		ps.setString(2, entity.getTelefone());
		ps.setString(3, entity.getEmail());
		ps.setString(4, entity.getDataSaida());
		ps.setInt(5, idUsuario);

		ps.execute();

		ps.close();

		return new DataAccessResponse(true, ResponseType.INTEGER, idPessoa);
	}
	@Override
	public DataAccessResponse deletar(Funcionario entity) {
		return new DataAccessResponse(false, ResponseType.STRING, "Não implementado!");
	}

	@Override
	public DataAccessResponse getById(int id) {
		return new DataAccessResponse(false, ResponseType.STRING, "Não implementado!");
	}

	@Override
	public DataAccessResponse listar() {
		return new DataAccessResponse(false, ResponseType.STRING, "Não implementado!");
	}
}
