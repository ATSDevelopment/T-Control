package res.setores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datamanager.dao.DataAccessResponse;
import datamanager.dao.ParcialDataAccessObject;
import datamanager.dao.ResponseType;
import app.ConnectionFactory;

public class SetorDAO implements ParcialDataAccessObject<Setor> {

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

	private DataAccessResponse update(Connection conexao, Setor setor) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement("UPDATE setor SET nome = ? WHERE id_setor = ? AND sigla_setor");

		ps.setString(1, setor.getNome());

		ps.setString(2, setor.getSigla());		

		ps.setInt(3, setor.getId());

		ps.execute();

		ps.close();

		return new DataAccessResponse(true, ResponseType.NULL, null);
	}

	private DataAccessResponse insert(Connection conexao, Setor setor) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement("INSERT INTO pessoas SET nome = ? WHERE sigla_setor ", Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, setor.getNome());

		ps.setString(2, setor.getSigla());

		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();

		int idSetor;
		if(rs.next()){
			idSetor = rs.getInt(1);
		}else{
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
}
