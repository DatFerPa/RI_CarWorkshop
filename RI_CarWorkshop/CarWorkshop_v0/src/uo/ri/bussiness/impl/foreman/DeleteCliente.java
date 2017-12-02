package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class DeleteCliente {

    private Long idCliente;

    public DeleteCliente(Long idCliente) {
	this.idCliente = idCliente;
    }

    public void execute() throws BusinessException {
	ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
	try {
	    if(numeroVehiculosCliente() == 0) {
		clientesGateway.setConnection(Jdbc.getConnection());
		clientesGateway.delete_mediopago(idCliente);
		clientesGateway.setConnection(Jdbc.getConnection());
		clientesGateway.delte_recomendaciones(idCliente);
		clientesGateway.setConnection(Jdbc.getConnection());
		clientesGateway.delete(idCliente);
	    }else {
		throw new BusinessException("No se puede eliminar un cliente con vehículos registrados en taller");
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private int numeroVehiculosCliente() {
	ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
	int numVehiculos = 0;
	try {
	    clientesGateway.setConnection(Jdbc.getConnection());
	    numVehiculos = clientesGateway.getNumeroCochesCliente(idCliente);
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
	return numVehiculos;
    }

}
