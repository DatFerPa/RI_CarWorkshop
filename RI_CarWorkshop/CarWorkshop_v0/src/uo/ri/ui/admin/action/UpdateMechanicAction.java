package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.bussiness.AdminService;
import uo.ri.bussiness.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		AdminService adminService = new AdminServiceImpl();
		adminService.updateMechanic(id, nombre, apellidos);
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
