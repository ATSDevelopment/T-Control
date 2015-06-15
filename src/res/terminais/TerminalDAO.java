package res.terminais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.ConnectionFactory;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;

public class TerminalDAO implements DataAccessObject<Terminal> {

	@Override
	public DataAccessResponse salvar(Terminal entity, boolean novo) {

		DataAccessResponse r;
		Connection conexao = new ConnectionFactory().getConnection();
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

		Connection conexao = new ConnectionFactory().getConnection();

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

		return null;
	}

	@Override
	public DataAccessResponse listar() {

		return null;
	}

}
