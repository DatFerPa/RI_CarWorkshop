package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class BonosMenu extends BaseMenu{
    
    public BonosMenu() {
	menuOptions = new Object[][] { {"Administrador > Gestión de bonos",null},
	    {"Generar bonos por 3 recomendaciones",NotYetImplementedAction.class},
	    {"Generar bonos por 3 averías",},
	    {"Generar bonos por facturas superiores a 500 \u20A0",NotYetImplementedAction.class},
	};
    }

}
