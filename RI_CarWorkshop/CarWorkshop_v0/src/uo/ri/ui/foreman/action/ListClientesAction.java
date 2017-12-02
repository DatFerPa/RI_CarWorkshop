package uo.ri.ui.foreman.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class ListClientesAction implements Action {

    @Override
    public void execute() throws Exception {
	Console.println("\nListado de Clientes");

	ServicesFactory servicesFactory = new ServicesFactory();

	List<Map<String, Object>> clientes = servicesFactory.getForemanService().findAllClients();

	for (Map<String, Object> cliente : clientes) {
	    Console.println("Id: " + cliente.get("id") + " - Nombre: " + cliente.get("nombre") + " - Apellidos: "
		    + cliente.get("apellidos") + " - Calle: " + cliente.get("street") + " - Ciudad: "
		    + cliente.get("city") + " - Zipcode: " + cliente.get("zipcode") + " - DNI: " + cliente.get("dni")
		    + " - Telefono: " + cliente.get("telefono") + " - Email: " + cliente.get("email"));
	}
    }

}
