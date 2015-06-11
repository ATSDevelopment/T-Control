package res.datamanager.dao;

public interface ParcialDataAccessObject<Entity> {
	public DataAccessResponse salvar(Entity entity, boolean novo);
	
	public DataAccessResponse deletar(Entity entity);
}
