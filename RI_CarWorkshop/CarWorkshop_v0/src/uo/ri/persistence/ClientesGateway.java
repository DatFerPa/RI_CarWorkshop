package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ClientesGateway {

	public void setConnection(Connection connection);

	public Map<String, Object> findById(Long id);

	public List<Map<String, Object>> findAll();

	public void update(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni);

	public void save( String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni);

	public void delete(Long id);
}
