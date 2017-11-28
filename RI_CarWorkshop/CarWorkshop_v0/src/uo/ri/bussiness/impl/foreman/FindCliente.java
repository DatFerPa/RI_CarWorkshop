package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindCliente {
	
	private Long id;

	public FindCliente(Long id) {
		this.id = id;
	}
	
	public Map<String, Object> execute() {
		ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
		Map<String, Object> cliente = null;
		try {
			clientesGateway.setConnection(Jdbc.getConnection());
			cliente = clientesGateway.findById(id);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return cliente;
	}
	

}
