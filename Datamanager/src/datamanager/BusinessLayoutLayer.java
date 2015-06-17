package datamanager;

import datamanager.dao.DataAccessResponse;


public interface BusinessLayoutLayer<Entity> {
	public DataAccessResponse salvar(Entity entity);

	public DataAccessResponse deletar(Entity entity);

	public DataAccessResponse getById(int id);

	public DataAccessResponse listar();
}
