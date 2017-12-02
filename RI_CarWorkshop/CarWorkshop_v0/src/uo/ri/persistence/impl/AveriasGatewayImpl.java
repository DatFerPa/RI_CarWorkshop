package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.AveriasGateway;

public class AveriasGatewayImpl implements AveriasGateway {
	
	private static String SQL_UPDATE_IMPORTE_AVERIA ="SQL_UPDATE_IMPORTE_AVERIA";
	private static String SQL_ACTUALIZAR_ESTADO_AVERIA = "SQL_ACTUALIZAR_ESTADO_AVERIA";
	private static String SQL_VERIFICAR_ESTADO_AVERIA = "SQL_VERIFICAR_ESTADO_AVERIA";
	private static final String SQL_IMPORTE_REPUESTOS = "SQL_IMPORTE_REPUESTOS";
	private static final String SQL_IMPORTE_MANO_OBRA = "SQL_IMPORTE_MANO_OBRA";
	private static String SQL_ACTUALIZAR_USADA_BONO = "SQL_ACTUALIZAR_USADA_BONO";
	
	Connection connection = null;
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}

	@Override
	public void cambiarEstadoAverias(List<Long> idsAveria, String status) {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Conf.get(SQL_ACTUALIZAR_ESTADO_AVERIA));

			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
		}
	}

	
	@Override
	public void verificarAveriasTerminadas(List<Long> idsAveria) throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_VERIFICAR_ESTADO_AVERIA));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}

				String status = rs.getString(1);
				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}

				rs.close();
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void actualizarImporteAveria(Long idAveria, double totalAveria) {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_UPDATE_IMPORTE_AVERIA));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public double consultaImporteRepuestos(Long idAveria) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_IMPORTE_REPUESTOS));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public double consultaImporteManoObra(Long idAveria) throws BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_IMPORTE_MANO_OBRA));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void actualizarUsadaBono(Long idAveria, boolean usada) {
	    PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(Conf.get(SQL_ACTUALIZAR_USADA_BONO));
			pst.setBoolean(1, usada);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	    
	}

	

}
