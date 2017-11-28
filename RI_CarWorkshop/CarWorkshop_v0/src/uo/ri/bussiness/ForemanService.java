package uo.ri.bussiness;

import java.util.List;
import java.util.Map;

public interface ForemanService {
	
	public void newCliente(String nombre, String apellidos,String calle,String ciudad,String zipcode,String telefono,String email);
	
	public void deleteCliente(Long id);
	
	public void updateCliente();

	public List<Map<String,Object>> findAllClients();
	
	
}
