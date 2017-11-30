package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ClientesGateway {

	public void setConnection(Connection connection);

	public Map<String, Object> findById(Long id);

	public List<Map<String, Object>> findAll();
	
	public List<Map<String, Object>> findRecomendados(Long id);

	public void update(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni);

	public void save( String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni);
	
	public void createMediopagoMetalico(Long id);
	
	public void createRecomendacion(Long idRecomendado, Long idRecomendador);

	public void delete(Long id);
	
	public void delete_mediopago(Long id);
	
	public void delte_recomendaciones(Long id);
}
