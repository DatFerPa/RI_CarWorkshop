package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class FindCliente {

    private Long id;

    public FindCliente(Long id) {
	this.id = id;
    }

    public Map<String, Object> execute() throws BusinessException {
	ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
	Map<String, Object> cliente = null;
	try {
	    clientesGateway.setConnection(Jdbc.getConnection());
	    cliente = clientesGateway.findById(id);

	    if (cliente == null) {
		throw new BusinessException("El cliente que ha buscado no existe");
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
	return cliente;
    }

}
