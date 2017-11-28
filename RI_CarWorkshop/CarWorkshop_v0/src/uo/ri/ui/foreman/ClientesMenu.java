package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.foreman.action.AddClienteAction;
import uo.ri.ui.foreman.action.DeleteClienteAction;
import uo.ri.ui.foreman.action.UpdateClienteAction;

public class ClientesMenu extends BaseMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] { { "Jefe de Taller > Gestión de Clientes", null },

				{ "Añadir cliente", AddClienteAction.class },
				{ "Modificar datos de cliente", UpdateClienteAction.class },
				{ "Eliminar cliente", DeleteClienteAction.class },
				{ "Listar clientes", NotYetImplementedAction.class }, };
	}

}
