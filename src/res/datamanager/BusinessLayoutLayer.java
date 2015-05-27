package res.datamanager;

import java.util.ArrayList;

public interface BusinessLayoutLayer<Entity> {
	public int salvar(Entity entity);

	public int deletar(Entity entity);

	public Entity getById(int id);

	public ArrayList<Entity> listar();

	public ArrayList<Entity> listarWhere(String key, String value);
}
