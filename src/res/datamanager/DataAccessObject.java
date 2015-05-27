package res.datamanager;


public interface DataAccessObject<Entity> extends ParcialDataAccessObject<Entity>{
	public DataAccessResponse getById(int id);
	
	public DataAccessResponse listar();
	
	public DataAccessResponse listarWhere(String key, String value);
}
