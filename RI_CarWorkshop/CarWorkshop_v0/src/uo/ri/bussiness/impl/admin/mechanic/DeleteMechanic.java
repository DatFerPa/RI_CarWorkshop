package uo.ri.bussiness.impl.admin.mechanic;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class DeleteMechanic {

	private long idMecanico;

	public DeleteMechanic(long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() {
		MecanicosGateway mecanicosGateway = new PersistenceFactory().getMecanicosGateway();
		try {
			mecanicosGateway.setConnection(Jdbc.getConnection());
			mecanicosGateway.delete(idMecanico);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
