package uo.ri.ui.foreman.action.cliente;

import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class DetalleClienteAction implements Action {

    @Override
    public void execute() throws Exception {
	Console.println("\nDetalles de un cliente");

	ServicesFactory servicesFactory = new ServicesFactory();

	Long id = Console.readLong("Inserte el id del cliente que desea ver");

	Map<String, Object> cliente = servicesFactory.getForemanService().findCliente(id);

	Console.println("Id: " + cliente.get("id") + " - Nombre: " + cliente.get("nombre") + " - Apellidos: "
		+ cliente.get("apellidos") + " - Calle: " + cliente.get("street") + " - Ciudad: " + cliente.get("city")
		+ " - Zipcode: " + cliente.get("zipcode") + " - DNI: " + cliente.get("dni") + " - Telefono: "
		+ cliente.get("telefono") + " - Email: " + cliente.get("email"));
    }

}
