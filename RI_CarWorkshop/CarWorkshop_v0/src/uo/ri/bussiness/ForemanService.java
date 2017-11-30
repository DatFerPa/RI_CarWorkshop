package uo.ri.bussiness;

import java.util.List;
import java.util.Map;

public interface ForemanService {
	
	public void newCliente(String dni,String nombre, String apellidos,String calle,String ciudad,String zipcode,String telefono,String email);
	
	public void deleteCliente(Long id);
	
	public void updateCliente(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni);

	public List<Map<String,Object>> findAllClients();
	
	public Map<String,Object> detalleCliente(Long id);
	
	
}
