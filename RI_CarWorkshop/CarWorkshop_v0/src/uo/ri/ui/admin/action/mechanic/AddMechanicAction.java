package uo.ri.ui.admin.action.mechanic;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class AddMechanicAction implements Action {

    @Override
    public void execute() throws BusinessException {

	String nombre = Console.readString("Nombre");
	String apellidos = Console.readString("Apellidos");
	String dni = Console.readString("dni");

	ServicesFactory servicesFactory = new ServicesFactory();

	servicesFactory.getAdminService().newMechanic(nombre, apellidos, dni);

	Console.println("Nuevo mecánico añadido");
    }

}
