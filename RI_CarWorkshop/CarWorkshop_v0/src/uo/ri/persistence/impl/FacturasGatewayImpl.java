package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway{

	private static String SQL_ULTIMO_NUMERO_FACTURA = "SQL_ULTIMO_NUMERO_FACTURA";
	private static String SQL_INSERTAR_FACTURA = "SQL_INSERTAR_FACTURA";
	private static String SQL_VINCULAR_AVERIA_FACTURA = "SQL_VINCULAR_AVERIA_FACTURA";
	private static String SQL_RECUPERAR_CLAVE_GENERADA = "SQL_RECUPERAR_CLAVE_GENERADA";
	
	Connection connection = null;
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}

	@Override
	public void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Conf.get(SQL_VINCULAR_AVERIA_FACTURA));
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva) {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Conf.get(SQL_INSERTAR_FACTURA));
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

			return getGeneratedKey(numeroFactura); // Id de la nueva factura generada

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long getGeneratedKey(long numeroFactura) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_RECUPERAR_CLAVE_GENERADA));
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public long generarNuevoNumeroFactura() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_ULTIMO_NUMERO_FACTURA));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else { // todavía no hay ninguna
				return 1L;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst);
		}
	}

	
	
	
}
