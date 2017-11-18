package uo.ri.bussiness.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;

public class DeleteMechanic {

	private static String SQL_ELIMINAR_MECANICO = "SQL_ELIMINAR_MECANICO";

	private long idMecanico;

	public DeleteMechanic(long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get(SQL_ELIMINAR_MECANICO));
			pst.setLong(1, idMecanico);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
