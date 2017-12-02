package uo.ri.bussiness.impl.admin.bono;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.ServicesFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.ClientesGatewayImpl;

/**
 * @author Fernando
 *
 */
/**
 * @author Fernando
 *
 */
public class GenerarBonosTresAverias {

    /**
     * Método base para ejecutar la accion de generar bonos cuando el usuario tiene
     * 3 averias pagadas
     */
    public void execute() {
	ServicesFactory servicesFactory = new ServicesFactory();
	List<Map<String, Object>> clientes = servicesFactory.getForemanService().findAllClients();
	for (Map<String, Object> cliente : clientes) {
	    List<Map<String, Object>> averias = getAveriasCliente((long) cliente.get("id"));
	    if (averias != null) {
		cadaTresAverias(averias, (long) cliente.get("id"));
	    }
	    averias = null;
	}

    }

    /**
     * Método para recibir la lista de averias que tiene un cliente
     * 
     * @param id
     *            el identificador del cliente
     * @return la lista con un mapa(clave/valor) , con las averias
     */
    private List<Map<String, Object>> getAveriasCliente(Long id) {
	ClientesGateway clientesGateway = new ClientesGatewayImpl();
	List<Map<String, Object>> averias = null;
	try {
	    clientesGateway.setConnection(Jdbc.getConnection());
	    averias = clientesGateway.getAveriasCliente(id);
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
	return averias;
    }

    /**
     * Método que se encarga de llamar al generador de bonos cada 3 averia pagadas
     * 
     * @param averias
     *            averias que tiene cada cliente
     * @param idCliente
     *            identificador del cliente
     */
    private void cadaTresAverias(List<Map<String, Object>> averias, Long idCliente) {
	int contador = 0;
	List<Long> idsParaCada3 = new ArrayList<Long>();
	for (Map<String, Object> averia : averias) {
	    ++contador;
	    idsParaCada3.add((long) averia.get("id"));
	    if (contador == 3) {
		generarBonoPara3averias(idsParaCada3, idCliente);
		idsParaCada3 = new ArrayList<Long>();
		contador = 0;
	    }
	}
    }

    /**
     * Método básico que genera un bono para las 3 averias pagadas
     * 
     * @param identificadoresAverias
     *            lista con los identificadores de las averias que han dado lugar a
     *            ese bono
     * @param idCliente
     *            identificador del cliente que va a recibir dicho bono
     */
    private void generarBonoPara3averias(List<Long> identificadoresAverias, Long idCliente) {
	ClientesGateway clientesGateway = new ClientesGatewayImpl();
	AveriasGateway averiasGateway = new AveriasGatewayImpl();
	try {
	    for (Long identificador : identificadoresAverias) {
		averiasGateway.setConnection(Jdbc.getConnection());
		averiasGateway.actualizarUsadaBono(identificador, true);
	    }
	    clientesGateway.setConnection(Jdbc.getConnection());
	    clientesGateway.createBono(idCliente, "TBonos", 0, 20, getCodgioActual(), "Por tres averias");

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Método que captura el código más alto hasta el momento, y genera uno nuevo
     * para otro bono
     * 
     * @return un String, que será el nuevo codigo del bono
     */
    private String getCodgioActual() {
	ClientesGateway clientesGateway = new ClientesGatewayImpl();
	String code = "";
	try {
	    clientesGateway.setConnection(Jdbc.getConnection());
	    code = clientesGateway.getUltimoCodigo();
	    String[] codeArray = code.split("B");
	    int valorNuevo = Integer.parseInt(codeArray[1]) + 10;
	    code = "B" + valorNuevo;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
	return code;
    }

}
