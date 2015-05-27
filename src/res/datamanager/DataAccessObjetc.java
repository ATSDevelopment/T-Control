package res.datamanager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccessObjetc<Entity> {
	public void salvar(Entity entity) throws SQLException;
	
	public void deletar(Entity entity) throws SQLException;
	
	public Entity getById(int id) throws SQLException;
	
	public ArrayList<Entity> listar() throws SQLException;
	
	public ArrayList<Entity> listarWhere(String key, String value) throws SQLException;
}
