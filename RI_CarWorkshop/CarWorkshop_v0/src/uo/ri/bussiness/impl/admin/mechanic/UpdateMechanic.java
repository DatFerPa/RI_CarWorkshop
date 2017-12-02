package uo.ri.bussiness.impl.admin.mechanic;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class UpdateMechanic {

	private long id;
	private String nombre;
	private String apellidos;

	public UpdateMechanic(long id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() {

		MecanicosGateway mecanicosGateway = new PersistenceFactory().getMecanicosGateway();

		try {
			mecanicosGateway.setConnection(Jdbc.getConnection());
			mecanicosGateway.update(id, nombre, apellidos);
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
	}

}
