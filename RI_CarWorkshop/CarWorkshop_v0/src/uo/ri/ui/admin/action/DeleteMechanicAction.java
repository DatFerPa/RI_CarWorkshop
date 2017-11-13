package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.bussiness.impl.admin.DeleteMechanic;
import uo.ri.common.BusinessException;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		DeleteMechanic deleteMechanic = new DeleteMechanic(idMecanico);
		deleteMechanic.execute();

		Console.println("Se ha eliminado el mecánico");
	}

}
