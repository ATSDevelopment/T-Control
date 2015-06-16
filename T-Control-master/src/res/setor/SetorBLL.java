package res.setor;


import java.sql.SQLException;
import java.util.List;

import res.setor.SetorDAO;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;

public class SetorBLL implements BusinessLayoutLayer <Setor> {

	@Override
	public DataAccessResponse salvar(Setor entity) {
		SetorDAO setordao = new SetorDAO();
		return setordao.salvar(entity, entity.getId()==0);
	}

	@Override
	public DataAccessResponse deletar(Setor entity) {
		SetorDAO setordao = new SetorDAO();
		return setordao.deletar(entity);
	}

	@Override
	public DataAccessResponse getById(int id) {
		SetorDAO setordao = new SetorDAO();
		return setordao.getById(id);
	}

	@Override
	public DataAccessResponse listar() {
		SetorDAO setordao = new SetorDAO();
		return setordao.listar();
	}

	public DataAccessResponse listarWhere(String key, String value) {
		SetorDAO setordao = new SetorDAO();
		return setordao.listarWhere(key, value);
	}

}
