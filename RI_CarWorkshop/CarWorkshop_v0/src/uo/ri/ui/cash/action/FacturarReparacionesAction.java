package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class FacturarReparacionesAction implements Action {

    @Override
    public void execute() throws BusinessException {
	List<Long> idsAveria = new ArrayList<Long>();

	// pedir las averias a incluir en la factura
	do {
	    Long id = Console.readLong("ID de averia");
	    idsAveria.add(id);
	} while (masAverias());

	ServicesFactory servicesFactory = new ServicesFactory();
	Map<String, Object> factura = servicesFactory.getCashService().createInvoiceFor(idsAveria);

	mostrarFactura((long) factura.get("numero"), (Date) factura.get("fecha"), (double) factura.get("importe"),
		(double) factura.get("iva"), (double) factura.get("total"));
	// sacamos todas las facturas y las mostramos

    }

    private void mostrarFactura(long numeroFactura, Date fechaFactura, double totalFactura, double iva,
	    double totalConIva) {

	Console.printf("Factura nº: %d\n", numeroFactura);
	Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", fechaFactura);
	Console.printf("\tTotal: %.2f €\n", totalFactura);
	Console.printf("\tIva: %.1f %% \n", iva);
	Console.printf("\tTotal con IVA: %.2f €\n", totalConIva);
    }

    private boolean masAverias() {
	return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
    }

}
