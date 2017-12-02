package uo.ri.bussiness;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {
	
	public void newCliente(String dni,String nombre, String apellidos,String calle,String ciudad,String zipcode,String telefono,String email);
	
	public void deleteCliente(Long id) throws BusinessException;
	
	public void updateCliente(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email);

	public List<Map<String,Object>> findAllClients();
	
	public Map<String,Object> detalleCliente(Long id);
	
	public List<Map<String,Object>> finClientesRecomendados(Long id);
	
	public void crearRecomendacionUltimoCliente(Long idRecomendador) throws BusinessException;
}
