package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.bussiness.impl.admin.FindAllMechanics;
import uo.ri.common.BusinessException;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		FindAllMechanics findAllMechanics = new FindAllMechanics();

		List<Map<String, Object>> mecanicos = findAllMechanics.execute();

		for (Map<String, Object> mecanico : mecanicos) {
			Console.println("Id: " + mecanico.get("id") + " - Nombre: " + mecanico.get("nombre") + " - Apellidos: "
					+ mecanico.get("apellidos"));
		}
	}
}
