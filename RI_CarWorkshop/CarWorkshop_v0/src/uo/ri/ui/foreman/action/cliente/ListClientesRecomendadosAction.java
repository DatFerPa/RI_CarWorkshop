package uo.ri.ui.foreman.action.cliente;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class ListClientesRecomendadosAction implements Action{

    @Override
    public void execute() throws Exception {
	Long idCliente = Console.readLong("Id del cliente del que desea ver las recomendaciones");
	
	Console.println("\nListado de clientes recomendados");

	ServicesFactory servicesFactory = new ServicesFactory();

	List<Map<String, Object>> clientes = servicesFactory.getForemanService().finClientesRecomendados(idCliente);
	
	for (Map<String, Object> cliente : clientes) {
	    Console.println("Id: " + cliente.get("id") + " - Nombre: " + cliente.get("nombre") + " - Apellidos: "
		    + cliente.get("apellidos") + " - Calle: " + cliente.get("street") + " - Ciudad: "
		    + cliente.get("city") + " - Zipcode: " + cliente.get("zipcode") + " - DNI: " + cliente.get("dni")
		    + " - Telefono: " + cliente.get("telefono") + " - Email: " + cliente.get("email"));
	}
	
    }

}
