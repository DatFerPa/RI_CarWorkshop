package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class UpdateCliente {
	
	private Long id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String calle;
	private String ciudad;
	private String zipcode;
	private String telefono;
	private String email;
	
	public UpdateCliente(Long id, String dni, String nombre, String apellidos, String calle, String ciudad,
			String zipcode, String telefono, String email) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.calle = calle;
		this.ciudad = ciudad;
		this.zipcode = zipcode;
		this.telefono = telefono;
		this.email = email;
	}
	
	
	public void execute() {
		ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
		
		try {
			clientesGateway.setConnection(Jdbc.getConnection());
			clientesGateway.update(id, nombre, apellidos, calle, ciudad, zipcode, telefono, email, dni);
		
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
}
