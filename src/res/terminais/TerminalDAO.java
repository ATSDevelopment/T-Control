package res.terminais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import res.connection.ConnectionManager;
import res.setor.Setor;
import res.setor.SetorDAO;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;

public class TerminalDAO implements DataAccessObject<Terminal> {

	@Override
	public DataAccessResponse salvar(Terminal entity, boolean novo) {

		DataAccessResponse r;
		Connection conexao = ConnectionManager.get();
		if (conexao != null) {
			try {
				if (novo) {
					r = insert(conexao, entity);
				} else {
					r = update(conexao, entity);
				}
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

	private DataAccessResponse update(Connection conexao, Terminal terminal)
			throws SQLException {

		PreparedStatement ps = conexao
				.prepareStatement("UPDATE terminais SET hostname = ?, ip_address = ?, ativo = ?, id_setor = ?  WHERE id_terminal = ?");

		ps.setString(1, terminal.getHostname());
		ps.setString(2, terminal.getIpAddress());
		ps.setBoolean(3, terminal.isAtivo());
		ps.setInt(4, terminal.getSetor().getId());
		ps.setInt(5, terminal.getId());

		ps.execute();

		ps.close();

		return new DataAccessResponse(true, ResponseType.NULL, null);
	}

	private DataAccessResponse insert(Connection conexao, Terminal terminal)
			throws SQLException {

		PreparedStatement ps = conexao
				.prepareStatement(
						"INSERT INTO terminal SET hostname = ?, ip_address = ?, ativo = ?, id_setor = ?",
						Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, terminal.getHostname());
		ps.setString(2, terminal.getIpAddress());
		ps.setBoolean(3, terminal.isAtivo());
		ps.setInt(4, terminal.getSetor().getId());

		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();

		int idTerminal;
		if (rs.next()) {
			idTerminal = rs.getInt(1);
		} else {
			idTerminal = 0;
		}

		rs.close();
		ps.close();

		return new DataAccessResponse(true, ResponseType.INTEGER, idTerminal);

	}

	@Override
	public DataAccessResponse deletar(Terminal entity) {

		DataAccessResponse r;

		Connection conexao = ConnectionManager.get();

		if (conexao != null) {
			String query = "DELETE FROM terminal WHERE id_terminal = ?";

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, entity.getId());

				ps.execute();

				ps.close();

				r = new DataAccessResponse(true, ResponseType.STRING,
						"Terminal " + entity.getHostname()
								+ " Deletado com sucesso!");
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
	public DataAccessResponse getById(int id) {

		DataAccessResponse r;

		Connection conexao = ConnectionManager.get();

		if (conexao != null) {

			String query = "SELECT * FROM terminal WHERE id_terminal = ?";

			Terminal terminal = null;
			
			
			DataAccessObject<Setor> dao =new SetorDAO();

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, id);

				ResultSet resultado = ps.executeQuery();
				if (resultado.next()) {

					DataAccessResponse response = dao.getById(resultado
							.getInt(5));

					Setor setor = (Setor) response.getResponse();

					terminal = new Terminal(resultado.getInt(1),
							resultado.getString(2), resultado.getString(3),
							resultado.getBoolean(4), setor);
				}
				resultado.close();
				ps.close();

				r = new DataAccessResponse(true, ResponseType.OBJECT, terminal);
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

			String query = "SELECT * FROM terminal";

			Terminal terminal = null;
			ArrayList<Terminal> array = new ArrayList<Terminal>();
			DataAccessObject<Setor> dao = new SetorDAO();

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ResultSet resultado = ps.executeQuery();
				while (resultado.next()) {

					DataAccessResponse response = dao.getById(resultado
							.getInt(5));

					Setor setor = (Setor) response.getResponse();

					terminal = new Terminal(resultado.getInt(1),
							resultado.getString(2), resultado.getString(3),
							resultado.getBoolean(4), setor);

					array.add(terminal);
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
