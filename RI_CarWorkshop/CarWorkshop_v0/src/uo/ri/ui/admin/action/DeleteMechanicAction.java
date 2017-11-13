package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.bussiness.AdminService;
import uo.ri.bussiness.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		AdminService adminServie = new AdminServiceImpl();
		adminServie.deleteMechanic(idMecanico);

		Console.println("Se ha eliminado el mecánico");
	}

}
