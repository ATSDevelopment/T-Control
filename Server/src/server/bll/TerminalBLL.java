package server.bll;

import server.dao.TerminalDAO;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import entity.Terminal;

public class TerminalBLL implements BusinessLayoutLayer<Terminal> {

	@Override
	public DataAccessResponse salvar(Terminal entity) {

		TerminalDAO dao = new TerminalDAO();

		return dao.salvar(entity, entity.getId()==0);
	}

	@Override
	public DataAccessResponse deletar(Terminal entity) {
		TerminalDAO dao = new TerminalDAO();

		return dao.deletar(entity);
	}

	@Override
	public DataAccessResponse getById(int id) {
		
		TerminalDAO dao = new TerminalDAO();
		
		return dao.getById(id);
	}

	@Override
	public DataAccessResponse listar() {
		
		TerminalDAO dao = new TerminalDAO();
		return dao.listar();
	}
}
