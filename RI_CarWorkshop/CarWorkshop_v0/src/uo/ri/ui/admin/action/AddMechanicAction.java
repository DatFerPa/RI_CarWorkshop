package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.bussiness.impl.admin.AddMechanic;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		AddMechanic addMechanic = new AddMechanic(nombre, apellidos);
		addMechanic.execute();

		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
