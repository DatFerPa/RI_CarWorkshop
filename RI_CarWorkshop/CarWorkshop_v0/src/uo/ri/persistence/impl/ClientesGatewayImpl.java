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
    private static String SQL_DELETE_MEDIOPAGO = "SQL_DELETE_MEDIOPAGO";
    private static String SQL_CREATE_METALICO = "SQL_CREATE_METALICO";
    private static String SQL_FIND_CLIENTES_RECOMENDADOS = "SQL_FIND_CLIENTES_RECOMENDADOS";
    private static String SQL_DELETE_RECOMENDACION = "SQL_DELETE_RECOMENDACION";
    private static String SQL_CREATE_RECMENDACION = "SQL_CREATE_RECMENDACION";
    private static String SQL_ULTIMO_ID_CLIENTE = "SQL_ULTIMO_ID_CLIENTE";
    private static String SQL_FIND_FACTURAS_ESTADOS = "SQL_FIND_FACTURAS_ESTADOS";
    private static String SQL_NUMERO_COCHES_CLIENTE = "SQL_NUMERO_COCHES_CLIENTE";

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
	    cliente.put("street", rs.getString(4));
	    cliente.put("city", rs.getString(5));
	    cliente.put("zipcode", rs.getString(6));
	    cliente.put("dni", rs.getString(7));
	    cliente.put("telefono", rs.getString(8));
	    cliente.put("email", rs.getString(9));
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
		cliente.put("street", rs.getString(4));
		cliente.put("city", rs.getString(5));
		cliente.put("zipcode", rs.getString(6));
		cliente.put("dni", rs.getString(7));
		cliente.put("telefono", rs.getString(8));
		cliente.put("email", rs.getString(9));

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
	    String telefono, String email) {
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
	    pst.setLong(8, id);

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
	    String email, String dni) {
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
	    pst = conection.prepareStatement(Conf.get(SQL_DELETE_CLIENTE));
	    pst.setLong(1, id);
	    pst.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(conection);
	}

    }

    @Override
    public void delete_mediopago(Long idCliente) {
	PreparedStatement pst = null;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_DELETE_MEDIOPAGO));
	    pst.setLong(1, idCliente);
	    pst.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(conection);
	}
    }

    @Override
    public void delte_recomendaciones(Long id) {
	PreparedStatement pst = null;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_DELETE_RECOMENDACION));
	    pst.setLong(1, id);
	    pst.executeUpdate();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(conection);
	}
    }

    @Override
    public List<Map<String, Object>> findRecomendados(Long id) {
	List<Map<String, Object>> clientes = new ArrayList<Map<String, Object>>();

	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_FIND_CLIENTES_RECOMENDADOS));
	    pst.setLong(1, id);
	    rs = pst.executeQuery();
	    while (rs.next()) {
		HashMap<String, Object> cliente = new HashMap<String, Object>();

		cliente.put("id", rs.getLong(1));
		cliente.put("city", rs.getString(2));
		cliente.put("street", rs.getString(3));
		cliente.put("zipcode", rs.getString(4));
		cliente.put("apellidos", rs.getString(5));
		cliente.put("dni", rs.getString(6));
		cliente.put("nombre", rs.getString(7));
		cliente.put("telefono", rs.getString(8));
		cliente.put("email", rs.getString(9));

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
    public void createMediopagoMetalico(Long id) {

	PreparedStatement pst = null;
	try {
	    pst = conection.prepareStatement(Conf.get(SQL_CREATE_METALICO));
	    pst.setString(1, "TMetalico");
	    pst.setDouble(2, 0);
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
    public void createRecomendacion(Long idRecomendado, Long idRecomendador) {
	PreparedStatement pst = null;
	try {
	    pst = conection.prepareStatement(Conf.get(SQL_CREATE_RECMENDACION));
	    pst.setLong(1, idRecomendado);
	    pst.setLong(2, idRecomendador);
	    pst.executeUpdate();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(conection);
	}

    }

    @Override
    public long getLastClienteId() {
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_ULTIMO_ID_CLIENTE));
	    rs = pst.executeQuery();
	    rs.next();

	    return rs.getLong(1);

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public List<String> getEstadosFacturasCliente(Long id) {
	List<String> estados = new ArrayList<String>();

	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_FIND_FACTURAS_ESTADOS));
	    pst.setLong(1, id);
	    rs = pst.executeQuery();
	    while (rs.next()) {
		estados.add(rs.getString(1));
	    }

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(conection);
	}
	return estados;
    }

    @Override
    public int getNumeroCochesCliente(Long id) {
	PreparedStatement pst = null;
	ResultSet rs = null;
	int numVehiculos = 0;

	try {
	    pst = conection.prepareStatement(Conf.get(SQL_NUMERO_COCHES_CLIENTE));
	    pst.setLong(1, id);
	    rs = pst.executeQuery();
	    rs.next();

	    numVehiculos = rs.getInt(1);

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
	return numVehiculos;
    }

}
