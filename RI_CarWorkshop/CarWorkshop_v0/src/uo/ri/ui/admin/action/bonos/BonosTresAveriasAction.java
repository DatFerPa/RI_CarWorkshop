package uo.ri.ui.admin.action.bonos;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class BonosTresAveriasAction implements Action {

    @Override
    public void execute() throws Exception {
	ServicesFactory servicesFactory = new ServicesFactory();

	servicesFactory.getAdminService().generarBonosTresAverias();

	Console.println("Bonos por tres averias generados");
    }

}
