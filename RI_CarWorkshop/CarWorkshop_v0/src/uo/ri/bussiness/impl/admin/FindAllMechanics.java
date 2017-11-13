package uo.ri.bussiness.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;

public class FindAllMechanics {

	private static String SQL = "select id, nombre, apellidos from TMecanicos";

	public List<Map<String, Object>> execute() {
		List<Map<String, Object>> mecanicos = new ArrayList<Map<String, Object>>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);

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
		} finally {
			Jdbc.close(rs, pst, c);
		}

		return mecanicos;
	}

}
