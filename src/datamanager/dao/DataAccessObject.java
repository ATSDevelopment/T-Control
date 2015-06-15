package datamanager.dao;


public interface DataAccessObject<Entity> extends ParcialDataAccessObject<Entity>{
	public DataAccessResponse getById(int id);
	
	public DataAccessResponse listar();
}