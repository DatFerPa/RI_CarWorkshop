package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindAllClientes {

    public List<Map<String, Object>> execute() {
	ClientesGateway clienteGateway = new PersistenceFactory().getClientesGateway();
	List<Map<String, Object>> clientes = null;
	try {
	    clienteGateway.setConnection(Jdbc.getConnection());
	    clientes = clienteGateway.findAll();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

	return clientes;

    }

}
