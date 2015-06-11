package res.datamanager;

import res.datamanager.dao.DataAccessResponse;


public interface EntityManager<Entity> {
	public DataAccessResponse salvar(Entity entity);
	
	public DataAccessResponse deletar(Entity entity);
	
	public DataAccessResponse getById(int id);
	
	public DataAccessResponse listar();
	
	public DataAccessResponse listarWhere(String key, String value);
}
