package uo.ri.bussiness.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;

public class CreateInvoiceFor {

	private Connection connection;

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Map<String, Object> execute() throws BusinessException {
		Map<String, Object> factura = new HashMap<String, Object>();
		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);

			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva / 100);
			importe = Round.twoCents(importe);

			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");

			factura.put("numero", numeroFactura);
			factura.put("importe", importe);
			factura.put("iva", iva);
			factura.put("total", (importe * iva) + importe);
			factura.put("fecha", fechaFactura);
			

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);
		}
		return factura;
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}

	private void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException {
		AveriasGateway averiasGateway = new AveriasGatewayImpl();
		try {
			averiasGateway.setConnection(Jdbc.getConnection());
			boolean averiasCorrectas = averiasGateway.verificarAveriasTerminadas(idsAveria);
			if(!averiasCorrectas) {
				throw new BusinessException("Alguna averia no existe o no está terminada");
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {

		AveriasGateway averiasGateway = new AveriasGatewayImpl();
		try {
			averiasGateway.setConnection(Jdbc.getConnection());
			averiasGateway.cambiarEstadoAverias(idsAveria, status);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {
			FacturasGateway facturasGateway = new PersistenceFactory().getFacturaGateway();
			try {
				facturasGateway.setConnection(Jdbc.getConnection());
				facturasGateway.vincularAveriasConFactura(idFactura, idsAveria);
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws SQLException {

		FacturasGateway facturasGateway = new PersistenceFactory().getFacturaGateway();
		try {
			facturasGateway.setConnection(Jdbc.getConnection());
			return facturasGateway.crearFactura(numeroFactura, fechaFactura, iva, totalConIva);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unused")
	private long getGeneratedKey(long numeroFactura) throws SQLException {
		FacturasGateway facturasGateway = new PersistenceFactory().getFacturaGateway();
		try {
			facturasGateway.setConnection(Jdbc.getConnection());
			return facturasGateway.getGeneratedKey(numeroFactura);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Long generarNuevoNumeroFactura() throws SQLException {
		FacturasGateway facturasGateway = new PersistenceFactory().getFacturaGateway();
		try {
			facturasGateway.setConnection(Jdbc.getConnection());
			return facturasGateway.generarNuevoNumeroFactura();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		AveriasGateway averiasGateway = new AveriasGatewayImpl();
		try {
			averiasGateway.setConnection(Jdbc.getConnection());
			averiasGateway.actualizarImporteAveria(idAveria, totalAveria);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		AveriasGateway averiasGateway = new AveriasGatewayImpl();
		try {
			averiasGateway.setConnection(Jdbc.getConnection());
			return averiasGateway.consultaImporteRepuestos(idAveria);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		AveriasGateway averiasGateway = new AveriasGatewayImpl();
		try {
			averiasGateway.setConnection(Jdbc.getConnection());
			return averiasGateway.consultaImporteManoObra(idAveria);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}

	protected double calcularImportesAverias(List<Long> idsAveria) throws BusinessException, SQLException {

		double totalFactura = 0.0;
		for (Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;

			actualizarImporteAveria(idAveria, totalAveria);

			totalFactura += totalAveria;
		}
		return totalFactura;
	}

}
