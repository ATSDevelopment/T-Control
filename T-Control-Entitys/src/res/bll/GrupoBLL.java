package res.bll;

import res.dao.GrupoDAO;
import res.entity.Grupo;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;

public class GrupoBLL implements BusinessLayoutLayer<Grupo> {

	@Override
	public DataAccessResponse salvar(Grupo entity) {
		GrupoDAO grupoDao = new GrupoDAO();
		return grupoDao.salvar(entity, entity.getId()==0);
	}

	@Override
	public DataAccessResponse deletar(Grupo entity) {
		GrupoDAO grupoDao = new GrupoDAO();
		return grupoDao.deletar(entity);
	}

	@Override
	public DataAccessResponse getById(int id) {
		GrupoDAO grupoDao = new GrupoDAO();
		return grupoDao.getById(id);
	}

	@Override
	public DataAccessResponse listar() {
		GrupoDAO grupoDao = new GrupoDAO();
		return grupoDao.listar();
	}


	

	
}
