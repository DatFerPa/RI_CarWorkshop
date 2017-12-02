package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class CreateCliente {

	private String dni;
	private String nombre;
	private String apellidos;
	private String calle;
	private String ciudad;
	private String zipcode;
	private String telefono;
	private String email;

	public CreateCliente(String dni, String nombre, String apellidos, String calle, String ciudad, String zipcode,
			String telefono, String email) {
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
			clientesGateway.save(nombre, apellidos, calle, ciudad, zipcode, telefono, email,dni);
			Long idCliente = UltimoClienteIdentificador();
			createMetalico(idCliente);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private long UltimoClienteIdentificador() {
		ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
		long id = 0;
		try {
			clientesGateway.setConnection(Jdbc.getConnection());
			id = clientesGateway.getLastClienteId();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}
	
	private void createMetalico(Long id) {
		ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
		try {
			clientesGateway.setConnection(Jdbc.getConnection());
			clientesGateway.createMediopagoMetalico(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
