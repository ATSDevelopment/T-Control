package res.datamanager;

import java.sql.SQLException;

public interface DataAccessObject<Entity> {
	public DataAccessResponse salvar(Entity entity) throws SQLException;
	
	public DataAccessResponse deletar(Entity entity) throws SQLException;
	
	public DataAccessResponse getById(int id) throws SQLException;
	
	public DataAccessResponse listar() throws SQLException;
	
	public DataAccessResponse listarWhere(String key, String value) throws SQLException;
}
