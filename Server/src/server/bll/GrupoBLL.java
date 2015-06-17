package server.bll;

import server.dao.GrupoDAO;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import entity.Grupo;

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