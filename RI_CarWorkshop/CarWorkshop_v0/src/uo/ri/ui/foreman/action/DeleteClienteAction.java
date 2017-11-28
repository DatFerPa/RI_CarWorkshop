package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class DeleteClienteAction implements Action{

	@Override
	public void execute() throws Exception {
		//pedimos el id del cliente
		Long idCliente = Console.readLong("Id del cliente");
		
		ServicesFactory servicesFactory = new ServicesFactory();
		
		servicesFactory.getForemanService().deleteCliente(idCliente);
		
		Console.println("Se ha eliminado el cliente");
		
		
	}

}
