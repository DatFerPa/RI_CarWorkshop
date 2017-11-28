package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class DeleteCliente {
	
	private Long idCliente;
	
	public DeleteCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public void execute() {
		ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
		try {
			clientesGateway.setConnection(Jdbc.getConnection());
			clientesGateway.delete(idCliente);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
