package res.general;

import java.util.ArrayList;

public interface EntityManager<Entity> {
	public int salvar(Entity entity);
	
	public int deletar(Entity entity);
	
	public Entity getById(int id);
	
	public ArrayList<Entity> listar();
	
	public ArrayList<Entity> listarWhere(String key, String value);
}
