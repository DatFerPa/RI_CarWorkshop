package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway {

	private static String SQL_INSERTAR_MECANICO = "SQL_INSERTAR_MECANICO";
	private static String SQL_LISTAR_TODOS_MECANICOS = "SQL_LISTAR_TODOS_MECANICOS";
	private static String SQL_ELIMINAR_MECANICO = "SQL_ELIMINAR_MECANICO";
	private static String SQL_ACTUALIZAR_MECANICO = "SQL_ACTUALIZAR_MECANICO";

	Connection conection = null;

	@Override
	public void setConnection(Connection connection) {
		this.conection = connection;

	}

	@Override
	public Map<String, Object> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String, Object>> mecanicos = new ArrayList<Map<String, Object>>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conection.prepareStatement(Conf.get(SQL_LISTAR_TODOS_MECANICOS));

			rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> mecanico = new HashMap<String, Object>();

				mecanico.put("id", rs.getLong(1));
				mecanico.put("nombre", rs.getString(2));
				mecanico.put("apellidos", rs.getString(3));

				mecanicos.add(mecanico);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}
		return mecanicos;
	}

	@Override
	public void update(Long id, String nombre, String apellidos) {
		PreparedStatement pst = null;

		try {
			conection = Jdbc.getConnection();

			pst = conection.prepareStatement(Conf.get(SQL_ACTUALIZAR_MECANICO));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}

	}

	@Override
	public void save(String nombre, String apellidos) {
		PreparedStatement pst = null;
		try {
			pst = conection.prepareStatement(Conf.get(SQL_INSERTAR_MECANICO));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}
	}

	@Override
	public void delete(Long id) {
		PreparedStatement pst = null;
		
		try {
			pst = conection.prepareStatement(Conf.get(SQL_ELIMINAR_MECANICO));
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}
		
		
		
	}

}
