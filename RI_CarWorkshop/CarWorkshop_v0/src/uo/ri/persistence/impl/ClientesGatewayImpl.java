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
import uo.ri.persistence.ClientesGateway;

public class ClientesGatewayImpl implements ClientesGateway {

	private static String SQL_FIND_CLIENTE_ID = "SQL_FIND_CLIENTE_ID";
	private static String SQL_FIND_CLIENTES = "SQL_FIND_CLIENTES";
	private static String SQL_UPDATE_CLIENTE = "SQL_UPDATE_CLIENTE";	
	private static String SQL_SAVE_CLIENTE = "SQL_SAVE_CLIENTE";
	private static String SQL_DELETE_CLIENTE = "SQL_DELETE_CLIENTE";
	
	
	Connection conection = null;

	@Override
	public void setConnection(Connection connection) {
		this.conection = connection;
	}

	@Override
	public Map<String, Object> findById(Long id) {
		HashMap<String, Object> cliente = new HashMap<String, Object>();

		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			pst = conection.prepareStatement(Conf.get(SQL_FIND_CLIENTE_ID));
			pst.setLong(1, id);

			rs = pst.executeQuery();

			cliente.put("id", rs.getLong(1));
			cliente.put("nombre", rs.getString(2));
			cliente.put("apellidos", rs.getString(3));
			cliente.put("street",rs.getString(4));
			cliente.put("city",rs.getString(5));
			cliente.put("zipcode",rs.getString(6));
			cliente.put("dni",rs.getString(7));
			cliente.put("telefono",rs.getString(8));
			cliente.put("email",rs.getString(9));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}

		return cliente;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String, Object>> clientes = new ArrayList<Map<String, Object>>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conection.prepareStatement(Conf.get(SQL_FIND_CLIENTES));
			
			rs = pst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> cliente = new HashMap<String, Object>();

				cliente.put("id", rs.getLong(1));
				cliente.put("nombre", rs.getString(2));
				cliente.put("apellidos", rs.getString(3));
				cliente.put("street",rs.getString(4));
				cliente.put("city",rs.getString(5));
				cliente.put("zipcode",rs.getString(6));
				cliente.put("dni",rs.getString(7));
				cliente.put("telefono",rs.getString(8));
				cliente.put("email",rs.getString(9));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}
		return clientes;
	}

	@Override
	public void update(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email,String dni) {
		PreparedStatement pst = null;

		try {
			conection = Jdbc.getConnection();

			pst = conection.prepareStatement(Conf.get(SQL_UPDATE_CLIENTE));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, street);
			pst.setString(4, ciudad);
			pst.setString(5, zipcode);
			pst.setString(6, telefono);
			pst.setString(7, email);
			pst.setString(8, dni);
			pst.setLong(9, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}

	}

	@Override
	public void save(String nombre, String apellidos, String street, String ciudad, String zipcode, String telefono,
			String email,String dni) {
		PreparedStatement pst = null;
		try {
			pst = conection.prepareStatement(Conf.get(SQL_SAVE_CLIENTE));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, street);
			pst.setString(4, ciudad);
			pst.setString(5, zipcode);
			pst.setString(6, telefono);
			pst.setString(7, email);
			pst.setString(8, dni);
			pst.executeQuery();

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
			pst = conection.prepareStatement(Conf.get(SQL_DELETE_CLIENTE));
			pst.setLong(1, id);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(pst);
			Jdbc.close(conection);
		}

	}

	@Override
	public void delete_mediopago(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delte_recomendaciones(Long id) {
		// TODO Auto-generated method stub
		
	}

}
