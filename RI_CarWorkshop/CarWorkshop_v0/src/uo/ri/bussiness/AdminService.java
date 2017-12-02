package uo.ri.bussiness;

import java.util.List;
import java.util.Map;

public interface AdminService {

	public void newMechanic(String nombre, String apellidos,String dni);

	public void deleteMechanic(Long id);

	public void updateMechanic(Long id, String nombre, String apellidos);

	public List<Map<String, Object>> findAllMechanics();

}
