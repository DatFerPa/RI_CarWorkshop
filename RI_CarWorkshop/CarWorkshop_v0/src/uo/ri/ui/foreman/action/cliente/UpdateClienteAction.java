package uo.ri.ui.foreman.action.cliente;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class UpdateClienteAction implements Action {

    @Override
    public void execute() throws Exception {
	Long id = Console.readLong("Id del cliente a actualizar");
	String nombre = Console.readString("nombre");
	String apellidos = Console.readString("apellidos");
	String calle = Console.readString("calle");
	String ciudad = Console.readString("ciudad");
	String zipcode = Console.readString("zipcode");
	String telefono = Console.readString("telefono");
	String email = Console.readString("email");

	ServicesFactory servicesFactory = new ServicesFactory();
	servicesFactory.getForemanService().updateCliente(id, nombre, apellidos, calle, ciudad, zipcode, telefono,
		email);

	Console.println("Cliente actualizado");

    }

}
