package uo.ri.ui.admin.action.mechanic;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class DeleteMechanicAction implements Action {

    @Override
    public void execute() throws BusinessException {
	Long idMecanico = Console.readLong("Id de mecánico");

	ServicesFactory servicesFactory = new ServicesFactory();

	servicesFactory.getAdminService().deleteMechanic(idMecanico);

	Console.println("Se ha eliminado el mecánico");
    }

}
