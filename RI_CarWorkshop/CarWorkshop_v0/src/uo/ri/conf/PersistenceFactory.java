package uo.ri.conf;

import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class PersistenceFactory {
	public MecanicosGateway getMecanicosGateway() {
		return new MecanicosGatewayImpl();
	}

	public FacturasGateway getFacturaGateway() {
		return new FacturasGatewayImpl();
	}
}
