package res.funcionario.mapadeservico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import app.ConnectionFactory;
import datamanager.dao.DataAccessObject;
import datamanager.dao.DataAccessResponse;
import datamanager.dao.ResponseType;

public class MapaDeServicoDAO implements DataAccessObject<MapaDeServico>{
<<<<<<< HEAD
=======
	
>>>>>>> c7e62495131564f521c8a055fed6089ebd6d841f
	@Override
	public DataAccessResponse salvar(MapaDeServico map, boolean novo) {
		DataAccessResponse res;

		Connection conexao = new ConnectionFactory().getConnection();

		if(novo){
			try {
				String query = "INSERT INTO mapa_de_servico SET id_funcionario=?, id_setor=?, seg=?, ter=?, qua=?, qui=?, sex=?, sab=?, dom=?";
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setInt(1, map.getIdFuncionario());
				ps.setInt(2, map.getIdSetor());
				ps.setBoolean(3, map.getDias().contains(DiasServico.SEGUNDA));
				ps.setBoolean(4, map.getDias().contains(DiasServico.TERCA));
				ps.setBoolean(5, map.getDias().contains(DiasServico.QUARTA));
				ps.setBoolean(6, map.getDias().contains(DiasServico.QUINTA));
				ps.setBoolean(7, map.getDias().contains(DiasServico.SEXTA));
				ps.setBoolean(8, map.getDias().contains(DiasServico.SABADO));
				ps.setBoolean(9, map.getDias().contains(DiasServico.DOMINGO));

				ps.execute();
				ps.close();

				res = new DataAccessResponse(true, ResponseType.NULL, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
			}
		}else{
			try{
				String query = "UPDATE mapa_de_servico SET seg=?, ter=?, qua=?, qui=?, sex=?, sab=?, dom=? WHERE id_funcionario=? AND id_setor=?";
				PreparedStatement ps = conexao.prepareStatement(query);

				ps.setBoolean(1, map.getDias().contains(DiasServico.SEGUNDA));
				ps.setBoolean(2, map.getDias().contains(DiasServico.TERCA));
				ps.setBoolean(3, map.getDias().contains(DiasServico.QUARTA));
				ps.setBoolean(4, map.getDias().contains(DiasServico.QUINTA));
				ps.setBoolean(5, map.getDias().contains(DiasServico.SEXTA));
				ps.setBoolean(6, map.getDias().contains(DiasServico.SABADO));
				ps.setBoolean(7, map.getDias().contains(DiasServico.DOMINGO));
				ps.setInt(8, map.getIdFuncionario());
				ps.setInt(9, map.getIdSetor());

				ps.execute();
				ps.close();

				res = new DataAccessResponse(true, ResponseType.NULL, null);
			}catch(SQLException e){
				e.printStackTrace();

				res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
			}
		}
		return res;
	}

	@Override
	public DataAccessResponse deletar(MapaDeServico map) {
		DataAccessResponse res;

		Connection conexao = new ConnectionFactory().getConnection();

		try {
			PreparedStatement ps = conexao.prepareStatement("DELETE FROM mapa_de_servico WHERE id_funcionario=? AND id_setor=?");

			ps.setInt(1, map.getIdFuncionario());
			ps.setInt(2, map.getIdSetor());

			ps.execute();

			ps.close();

			res = new DataAccessResponse(true, ResponseType.NULL, null);
		} catch (SQLException e) {
			res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
		}

		return res;
	}

	//Id do Funcionario
	
	@Override
	public DataAccessResponse getById(int id) {
		DataAccessResponse res;
		
		Connection conexao = new ConnectionFactory().getConnection();

		try {
			PreparedStatement ps = conexao.prepareStatement(
					"SELECT id_setor, seg, ter, qua, qui, sex, sab, dom FROM mapa_de_servico WHERE id_funcionario=?");

			ResultSet rs = ps.executeQuery();
			
			ArrayList<MapaDeServico> mapas = new ArrayList<MapaDeServico>();
			while(rs.next()){
				MapaDeServico map = new MapaDeServico();

				map.setIdSetor(rs.getInt("id_setor"));
				map.setIdFuncionario(id);
				
				ArrayList<DiasServico> dias = new ArrayList<DiasServico>();
				if(rs.getBoolean("seg"))
					dias.add(DiasServico.SEGUNDA);
				if(rs.getBoolean("ter"))
					dias.add(DiasServico.TERCA);
				if(rs.getBoolean("qua"))
					dias.add(DiasServico.QUARTA);
				if(rs.getBoolean("qui"))
					dias.add(DiasServico.QUINTA);
				if(rs.getBoolean("sex"))
					dias.add(DiasServico.SEXTA);
				if(rs.getBoolean("sab"))
					dias.add(DiasServico.SABADO);
				if(rs.getBoolean("dom"))
					dias.add(DiasServico.DOMINGO);
				
				map.setDias(dias);
				
				Calendar horarioEntrada = Calendar.getInstance();
				horarioEntrada.setTime(rs.getDate("horario_entrada"));
				
				Calendar horarioSaida = Calendar.getInstance();
				horarioSaida.setTime(rs.getDate("horario_saida"));
				
				map.setHorarioEntrada(horarioEntrada);
				map.setHorarioSaida(horarioSaida);
				
				mapas.add(map);
			}
			
			rs.close();
			ps.close();
			
			res = new DataAccessResponse(true, ResponseType.ARRAY_LIST, mapas);
		} catch (SQLException e) {
			res = new DataAccessResponse(false, ResponseType.STRING, e.getMessage());
		}
		
		return res;
	}

	@Override
	public DataAccessResponse listar() {
		return null;
	}
}
