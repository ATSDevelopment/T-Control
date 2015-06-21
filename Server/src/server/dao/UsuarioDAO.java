package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datamanager.connection.ConnectionManager;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import entity.Usuario;

public class UsuarioDAO implements DataAccessObject<Usuario> {
	private Connection conexao;
	protected UsuarioDAO(Connection conexao){
		this.conexao = conexao;
	}
	public UsuarioDAO(){
		this.conexao = ConnectionManager.get();
	}
	@Override
	public DataAccessResponse salvar(Usuario entity, boolean novo) {
		DataAccessResponse r;
		if (conexao != null) {
			try{
				if (novo) {
					r = insert(conexao, entity);
				} else {
					r = update(conexao, entity);
				}
			}catch(SQLException e){
				r = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
			}
		} else {
			r = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}
		
		return r;
	}

	private DataAccessResponse update(Connection conexao, Usuario usuario) throws SQLException{
		PreparedStatement ps = conexao
				.prepareStatement("UPDATE usuarios SET nome_de_usuario= ?, password = ?, ativo = ?, exp_data = ?  WHERE id_usuario = ?");
		
		ps.setString(1, usuario.getNomeDeUsuario());
		
		ps.setString(2, usuario.getPassword());
		
		ps.setBoolean(3, usuario.isAtivo());
		
		ps.setString(4, usuario.getExpData());
		
		ps.setInt(5, usuario.getId());
		
		ps.execute();

		ps.close();

		return new DataAccessResponse(true, ResponseType.NULL, null);
	}

	private DataAccessResponse insert(Connection conexao, Usuario usuario) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(
						"INSERT INTO usuarios SET nome_de_usuario= ?, password = ?, ativo = ?, exp_data = ? ",
						Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, usuario.getNomeDeUsuario());
		
		ps.setString(2, usuario.getPassword());
		
		ps.setBoolean(3, usuario.isAtivo());
		
		ps.setString(4, usuario.getExpData());
		
		
		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();

		int idUsuario;
		if(rs.next()){
			idUsuario = rs.getInt(1);
		}else{
			idUsuario = 0;
		}

		rs.close();
		ps.close();

		return new DataAccessResponse(true, ResponseType.INTEGER, idUsuario);
	}

	@Override
	public DataAccessResponse deletar(Usuario entity) {
		DataAccessResponse r;


		if(conexao != null){
			String query = "DELETE FROM usuarios WHERE id_usuario = ?";

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, entity.getId());

				ps.execute();

				ps.close();

				r = new DataAccessResponse(true, ResponseType.STRING, entity.getNomeDeUsuario() + " Deletado com sucesso!");
			} catch (SQLException e) {
				r = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
			}
		}else{
			r = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return r;
	}
	
	
	public DataAccessResponse listar(){

		DataAccessResponse resp;


		if (conexao != null) {

			String query = "SELECT * FROM usuarios";

			Usuario entity = null;
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ResultSet resultado = ps.executeQuery();
			
			
			while (resultado.next()) {
				
				
				entity.setNomeDeUsuario(resultado.getString("nome_de_usuario"));
				entity.setExpData(resultado.getString("expData"));
				entity.setId(resultado.getInt("id"));
				entity.setPassword(resultado.getString("password"));
				entity.setAtivo(resultado.getBoolean("ativo"));
				
				lista.add(entity);
			}
		
				
				resultado.close();
				conexao.close();
				resp = new DataAccessResponse(true, ResponseType.ARRAY_LIST, lista);
			} catch (SQLException e) {

				resp = new DataAccessResponse(false, ResponseType.STRING,
						e.getMessage());
			}
		} else {
			resp = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return resp;
	}
	
	public DataAccessResponse getById(int id) {

		DataAccessResponse resp;

		if (conexao != null) {

			String query = "SELECT nome_de_usuario, ativo, exp_data FROM usuarios WHERE id_usuario = ?";

			Usuario usuario = null;
			
			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, id);

				ResultSet resultado = ps.executeQuery();
				
				if (resultado.next()) {
					String nomeDeUsuario = resultado.getString("nome_de_usuario");
					boolean ativo = resultado.getBoolean("ativo");
					String expData = resultado.getString("exp_data");
					
					usuario = new Usuario(id, nomeDeUsuario, null, ativo, expData);
				}
				resultado.close();
				ps.close();

				resp = new DataAccessResponse(true, ResponseType.OBJECT, usuario);
			} catch (SQLException e) {

				resp = new DataAccessResponse(false, ResponseType.STRING,
						e.getMessage());
			}
		} else {
			resp = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return resp;
	}
}
