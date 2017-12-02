package uo.ri.bussiness.impl.admin;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class AddMechanic {
	

	private String nombre;
	private String apellidos;
	private String dni;

	public AddMechanic(String nombre, String apellidos,String dni) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
	}

	public void execute() {
		// Procesar	
		MecanicosGateway mecanicosGetaway = new PersistenceFactory().getMecanicosGateway();
		try {
			mecanicosGetaway.setConnection(Jdbc.getConnection());
			mecanicosGetaway.save(nombre, apellidos,dni);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}