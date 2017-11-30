package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class AddClienteAction implements Action{
	
	@Override
	public void execute() throws Exception {
		//pedimos los datos 
		String dni = Console.readString("dni");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String calle = Console.readString("Calle");
		String ciudad = Console.readString("Ciudad");
		String zipcode = Console.readString("Zipcode");
		String telefono = Console.readString("Telefono");
		String email = Console.readString("Email");
		
		ServicesFactory servicesFactory = new ServicesFactory();
		
		servicesFactory.getForemanService().newCliente(dni,nombre, apellidos, calle, ciudad, zipcode, telefono, email);
		
		Console.println("Nuevo cliente añadido");
	}
	

}
