package res.setor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import res.connection.ConnectionManager;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;

public class SetorDAO implements DataAccessObject<Setor> {

	@Override
	public DataAccessResponse salvar(Setor entity, boolean novo) {
		DataAccessResponse r;
		Connection conexao = ConnectionManager.get();
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

			Connection conexao = ConnectionManager.get();

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

		Connection conexao = ConnectionManager.get();

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

		Connection conexao = ConnectionManager.get();

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
	
}
