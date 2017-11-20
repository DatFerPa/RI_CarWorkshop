package uo.ri.bussiness.impl.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class FindAllMechanics {

	public List<Map<String, Object>> execute() {
		MecanicosGateway mecanicosGetaway = new PersistenceFactory().getMecanicosGateway();
		List<Map<String, Object>> mecanicos = null;
		try {
			mecanicosGetaway.setConnection( Jdbc.getConnection());
			mecanicos = mecanicosGetaway.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		
		return mecanicos;
	}
}
