package res.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import res.datamanager.ConnectionFactory;
import res.datamanager.DataAccessResponse;
import res.datamanager.ParcialDataAccessObject;
import res.datamanager.ResponseType;

public class PessoaDAO implements ParcialDataAccessObject<Pessoa> {

	@Override
	public DataAccessResponse salvar(Pessoa entity, boolean novo) {
		DataAccessResponse r;
		Connection conexao = new ConnectionFactory().getConnection();
		if (conexao != null) {
			String query;
			if (novo) {
				query = "INSERT INTO pessoas SET nome = ?";
			} else {
				query = "UPDATE pessoas SET nome = ? WHERE id_pessoa = ?";
			}

			try {
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setString(1, entity.getNome());

				if (!novo) {
					ps.setInt(2, entity.getId());
				}

				ps.execute();

				ps.close();

				r = new DataAccessResponse(true, ResponseType.MESSAGE,
						"Pessoa " + (novo ? "salva" : "atualizada")
								+ " com sucesso!");
			} catch (SQLException e) {
				e.printStackTrace();
				r = new DataAccessResponse(false, ResponseType.EXCEPTION, e);
			}
		} else {
			r = new DataAccessResponse(false, ResponseType.MESSAGE,
					"Não foi possível se comunicar com o banco de dados!");
		}
		return r;
	}

	@Override
	public DataAccessResponse deletar(Pessoa entity) {
		DataAccessResponse r;
		
		Connection conexao = new ConnectionFactory().getConnection();
		
		if(conexao != null){
			String query = "DELETE FROM pessoas WHERE id_pessoa = ?";
			
			try {
				PreparedStatement ps = conexao.prepareStatement(query);
				
				ps.setInt(1, entity.getId());
				
				ps.execute();
				
				ps.close();
				
				r = new DataAccessResponse(true, ResponseType.MESSAGE, entity.getNome() + " Deletad@ com sucesso!");
			} catch (SQLException e) {
				r = new DataAccessResponse(false, ResponseType.EXCEPTION, e);
			}
		}else{
			r = new DataAccessResponse(false, ResponseType.MESSAGE,
					"Não foi possível se comunicar com o banco de dados!");
		}
		
		return r;
	}
}
