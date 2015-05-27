package res.datamanager;


public interface BusinessLayoutLayer<Entity> {
	public DataAccessObject salvar(Entity entity);

	public DataAccessObject deletar(Entity entity);

	public DataAccessObject getById(int id);

	public DataAccessObject listar();

	public DataAccessObject listarWhere(String key, String value);
}
