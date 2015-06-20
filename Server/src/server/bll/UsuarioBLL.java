package server.bll;

import server.dao.UsuarioDAO;
import datamanager.BusinessLayoutLayer;
import datamanager.dao.DataAccessResponse;
import entity.Usuario;


public class UsuarioBLL implements BusinessLayoutLayer<Usuario> {
	@Override
	public DataAccessResponse salvar(Usuario entity) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		return usuarioDao.salvar(entity, entity.getId()==0);
	}

	@Override
	public DataAccessResponse deletar(Usuario entity) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		return usuarioDao.deletar(entity);
	}

	@Override
	public DataAccessResponse listar() {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		return usuarioDao.listar();
	}

	@Override
	public DataAccessResponse getById(int id) {
		UsuarioDAO usuarioDao = new UsuarioDAO();
		return usuarioDao.getById(id);
	}
}