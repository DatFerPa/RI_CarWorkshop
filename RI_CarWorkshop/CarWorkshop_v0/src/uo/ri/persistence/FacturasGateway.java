package uo.ri.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public interface FacturasGateway {

    public void setConnection(Connection connection);

    public void vincularAveriasConFactura(long idFactura, List<Long> idsAveria);

    public long crearFactura(long numeroFactura, Date fechaFacturam, double iva, double totalConIva);

    public long getGeneratedKey(long numeroFactura);

    public long generarNuevoNumeroFactura();
}
