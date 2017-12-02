package uo.ri.bussiness.impl.foreman;

import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class crearRecomendacionUltimoCliente {

    Long idRecomendador;

    public crearRecomendacionUltimoCliente(Long idRecomendador) {
	super();
	this.idRecomendador = idRecomendador;
    }

    /**
     * Método que genera una recomendación
     * 
     * @throws BusinessException,
     *             cuando el usuario que definimos como recomendador no tiene
     *             Facturas pagadas
     */
    public void execute() throws BusinessException {
	ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
	try {
	    Long idRecomndado = getIdUltimoCliente();
	    if (recomendadorConFactura(idRecomendador)) {
		clientesGateway.setConnection(Jdbc.getConnection());
		clientesGateway.createRecomendacion(idRecomndado, idRecomendador);
	    } else {
		throw new BusinessException("Cliente no aceptado para recomendar: no tiene facturas abonadas");
	    }

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Método que devuelve un booleano en función de si el clinete que le pasamos
     * con un id, tiene facturas pagadas
     * 
     * @param idRecomendador
     * 
     */
    private boolean recomendadorConFactura(Long idRecomendador) {
	ClientesGateway clientesGateway = new PersistenceFactory().getClientesGateway();
	try {
	    clientesGateway.setConnection(Jdbc.getConnection());
	    List<String> estados = clientesGateway.getEstadosFacturasCliente(idRecomendador);
	    for (String estado : estados) {
		if (estado.equals("ABONADA")) {
		    return true;
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}

	return false;
    }

    private Long getIdUltimoCliente() {
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

}
