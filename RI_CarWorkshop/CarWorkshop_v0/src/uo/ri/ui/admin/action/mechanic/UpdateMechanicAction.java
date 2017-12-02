package uo.ri.ui.admin.action.mechanic;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		ServicesFactory servicesFactory = new ServicesFactory();
		servicesFactory.getAdminService().updateMechanic(id, nombre, apellidos);

		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
