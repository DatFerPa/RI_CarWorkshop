package uo.ri.ui.admin.action.mechanic;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		ServicesFactory servicesFactory = new ServicesFactory();

		List<Map<String, Object>> mecanicos = servicesFactory.getAdminService().findAllMechanics();
		for (Map<String, Object> mecanico : mecanicos) {
			Console.println("Id: " + mecanico.get("id") + " - Dni: " + mecanico.get("dni") + " - Nombre: " + mecanico.get("nombre") + " - Apellidos: "
					+ mecanico.get("apellidos"));
		}
	}
}
