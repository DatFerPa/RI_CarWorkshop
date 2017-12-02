package uo.ri.conf;

import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.ClientesGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class PersistenceFactory {
    public MecanicosGateway getMecanicosGateway() {
	return new MecanicosGatewayImpl();
    }

    public FacturasGateway getFacturaGateway() {
	return new FacturasGatewayImpl();
    }

    public AveriasGateway getAveriasGateway() {
	return new AveriasGatewayImpl();
    }

    public ClientesGateway getClientesGateway() {
	return new ClientesGatewayImpl();
    }
}
