package res.grupo;

import gui.MainFrame;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import res.usuario.Usuario;
import app.ConnectionFactory;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ParcialDataAccessObject;
import datamanager.dao.ResponseType;


public class GrupoDAO implements ParcialDataAccessObject<Grupo> {

	@Override
	public DataAccessResponse salvar(Grupo entity, boolean novo) {
		DataAccessResponse r;
		Connection conexao = new ConnectionFactory().getConnection();
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

	private DataAccessResponse update(Connection conexao, Grupo grupo) throws SQLException{

		PreparedStatement ps = conexao
				.prepareStatement("UPDATE grupos SET nome= ?, sigla = ?, ativo = ?  WHERE id_grupo = ?");

		ps.setString(1, grupo.getNome());

		ps.setString(2, grupo.getSigla());

		ps.setBoolean(3, grupo.getAtivo());
		
		ps.setInt(4, grupo.getId());
		
		ps.execute();
		ps.close();

		return new DataAccessResponse(true, ResponseType.NULL, null);
	}

	private DataAccessResponse insert(Connection conexao, Grupo grupo) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(
				"INSERT INTO grupos SET nome= ?, silga = ?, ativo = ? ",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, grupo.getNome());
		ps.setString(2, grupo.getSigla());
		ps.setBoolean(3, grupo.getAtivo());

		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();

		int idGrupo;
		if(rs.next()){
			idGrupo = rs.getInt(1);
		}else{
			idGrupo = 0;
		}

		rs.close();
		ps.close();

		return new DataAccessResponse(true, ResponseType.INTEGER, idGrupo);
	}

	@Override
	public DataAccessResponse deletar(Grupo entity) {
		DataAccessResponse r;

		Connection conexao = new ConnectionFactory().getConnection();

		if(conexao != null){
			String query = "DELETE FROM grupos WHERE id_grupos = ?";

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, entity.getId());

				ps.execute();

				ps.close();

				r = new DataAccessResponse(true, ResponseType.STRING, entity.getNome() + " Deletado com sucesso!");
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

		Connection conexao = new ConnectionFactory().getConnection();

		if (conexao != null) {

			String query = "SELECT * FROM grupos";

			Grupo entity = null;
			ArrayList<Grupo> lista = new ArrayList<Grupo>();


			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ResultSet resultado = ps.executeQuery();


				while (resultado.next()) {


					entity.setId(resultado.getInt("id"));
					entity.setSigla(resultado.getString("sigla"));
					entity.setNome(resultado.getString("nome"));
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

		Connection conexao = new ConnectionFactory().getConnection();

		if (conexao != null) {

			String query = "SELECT * FROM grupos WHERE id_grupo = ?";

			Grupo grupo= null;



			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, id);

				ResultSet resultado = ps.executeQuery();
				if (resultado.next()) {

					grupo = new Grupo(resultado.getInt("id"),resultado.getString("sigla"),resultado.getString("nome"),resultado.getBoolean("ativo"));


				}
				resultado.close();
				ps.close();

				resp = new DataAccessResponse(true, ResponseType.OBJECT, (Serializable) grupo);
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



