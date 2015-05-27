package res.datamanager;

public interface ParcialDataAccessObject<Entity> {
	public DataAccessResponse salvar(Entity entity, boolean novo);
	
	public DataAccessResponse deletar(Entity entity);
}
