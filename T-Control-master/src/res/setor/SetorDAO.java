package res.setor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import res.setor.Setor;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;
import app.ConnectionFactory;

public class SetorDAO implements DataAccessObject<Setor> {

	@Override
	public DataAccessResponse salvar(Setor entity, boolean novo) {
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
	
	
	private DataAccessResponse update(Connection conexao, Setor setor)
			throws SQLException {

		PreparedStatement ps = conexao
				.prepareStatement("UPDATE setor SET id_setor = ?, nome= ?, sigla_setor = ?");
		
		ps.setInt(1, setor.getId());
		ps.setString(2, setor.getNome());
		ps.setString(3, setor.getSigla());

		ps.execute();

		ps.close();

		return new DataAccessResponse(true, ResponseType.NULL, null);
	}

	private DataAccessResponse insert(Connection conexao, Setor setor)
			throws SQLException {

		PreparedStatement ps = conexao
				.prepareStatement(
						"INSERT INTO setor SET id_setor= ?, nome = ?, sigla = ?",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, setor.getId());
		ps.setString(2, setor.getNome());
		ps.setString(3, setor.getSigla());

		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();

		int idSetor;
		if (rs.next()) {
			idSetor = rs.getInt(1);
		} else {
			idSetor = 0;
		}

		rs.close();
		ps.close();

		return new DataAccessResponse(true, ResponseType.INTEGER, idSetor);

	}


	@Override
	public DataAccessResponse deletar(Setor entity) {
		
			DataAccessResponse r;

			Connection conexao = new ConnectionFactory().getConnection();

			if(conexao != null){
				String query = "DELETE FROM setor WHERE id_setor = ?";

				try {
					PreparedStatement ps = conexao.prepareStatement(query);

					ps.setInt(1, entity.getId());

					ps.execute();

					ps.close();

					r = new DataAccessResponse(true, ResponseType.STRING, entity.getNome() + " Deletad@ com sucesso!");
				} catch (SQLException e) {
					r = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
				}
			}else{
				r = new DataAccessResponse(false, ResponseType.STRING,
						"Não foi possível se comunicar com o banco de dados!");
			}

			return r;
	}

	public DataAccessResponse getById(int id) {

		DataAccessResponse r;

		Connection conexao = new ConnectionFactory().getConnection();

		if (conexao != null) {

			String query = "SELECT * FROM setor WHERE id_setor = ?";

			Setor setor = null;
			SetorDAO dao = null;

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, id);

				ResultSet resultado = ps.executeQuery();
				if (resultado.next()) {
					dao = new SetorDAO();

					DataAccessResponse response = dao.getById(resultado
							.getInt(5));

					setor = (Setor) response.getResponse();

					setor = new Setor(resultado.getInt(1),
							resultado.getString(2), resultado.getString(3));
				}
				resultado.close();
				ps.close();

				r = new DataAccessResponse(true, ResponseType.OBJECT, (Serializable) setor);
			} catch (SQLException e) {

				r = new DataAccessResponse(false, ResponseType.STRING,
						e.getMessage());
			}
		} else {
			r = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return r;
	}

	@Override
	public DataAccessResponse listar() {

		DataAccessResponse r;

		Connection conexao = new ConnectionFactory().getConnection();

		if (conexao != null) {

			String query = "SELECT * FROM setor";

			Setor setor = null;
			ArrayList<Setor> array = new ArrayList<Setor>();
			SetorDAO setordao = null;

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ResultSet resultado = ps.executeQuery();
				while (resultado.next()) {
					setordao = new SetorDAO();

					DataAccessResponse response = setordao.getById(resultado
							.getInt(5));

					setor = (Setor) response.getResponse();

					setor = new Setor(resultado.getInt(1),
							resultado.getString(2), resultado.getString(3));

					array.add(setor);
				}
				resultado.close();
				ps.close();

				r = new DataAccessResponse(true, ResponseType.ARRAY_LIST, array);
			} catch (SQLException e) {

				r = new DataAccessResponse(false, ResponseType.STRING,
						e.getMessage());
			}
		} else {
			r = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return r;
	}

	public DataAccessResponse listarWhere(String key, String value) {

		DataAccessResponse r;

		Connection conexao = new ConnectionFactory().getConnection();

		if (conexao != null) {
			ArrayList<Setor> array = new ArrayList<Setor>();
			SetorDAO setordao = null;

			try {
				PreparedStatement ps = null;
				
				if (key.equals("id")) {
					String query = "SELECT * FROM setor WHERE id_setor = %?%";
					ps = conexao.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(value));

				} else if (key.equals("nome")) {
					String query = "SELECT * FROM setor WHERE nome = %?%";
					ps = conexao.prepareStatement(query);
					ps.setString(1, value);

				} else if (key.equals("sigla")) {
					String query = "SELECT * FROM setor WHERE sigla = %?%";
					ps = conexao.prepareStatement(query);
					ps.setString(1, value);
				} 

				ResultSet resultado = ps.executeQuery();
				while (resultado.next()) {
					setordao = new SetorDAO();

					DataAccessResponse response = setordao.getById(resultado
							.getInt(1));

					Setor s = (Setor) response.getResponse();

					s = new Setor(resultado.getInt(1),
							resultado.getString(2), resultado.getString(3));

					array.add(s);
				}
				resultado.close();
				ps.close();

				r = new DataAccessResponse(true, ResponseType.ARRAY_LIST, array);
			} catch (SQLException e) {

				r = new DataAccessResponse(false, ResponseType.STRING,
						e.getMessage());
			}
		} else {
			r = new DataAccessResponse(false, ResponseType.STRING,
					"Não foi possível se comunicar com o banco de dados!");
		}

		return r;
	}


	
	
}
