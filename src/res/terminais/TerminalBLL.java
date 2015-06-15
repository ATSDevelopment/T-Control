package res.terminais;

import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;

public class TerminalBLL implements BusinessLayoutLayer<Terminal> {

	@Override
	public DataAccessResponse salvar(Terminal entity, boolean novo) {

		TerminalDAO dao = new TerminalDAO();

		return dao.salvar(entity, novo);
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

	@Override
	public DataAccessResponse listarWhere(String key, String value) {
		
		TerminalDAO dao = new TerminalDAO();
		
		return dao.listarWhere(key, value);
	}

}
