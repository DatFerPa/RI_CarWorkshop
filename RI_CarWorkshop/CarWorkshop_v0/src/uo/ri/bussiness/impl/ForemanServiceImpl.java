package uo.ri.bussiness.impl;

import java.util.List;
import java.util.Map;

import uo.ri.bussiness.ForemanService;
import uo.ri.bussiness.impl.foreman.CreateCliente;
import uo.ri.bussiness.impl.foreman.DeleteCliente;
import uo.ri.bussiness.impl.foreman.FindAllClientes;
import uo.ri.bussiness.impl.foreman.FindCliente;
import uo.ri.bussiness.impl.foreman.UpdateCliente;

public class ForemanServiceImpl implements ForemanService {

	@Override
	public void deleteCliente(Long id) {
		new DeleteCliente(id).execute();

	}

	@Override
	public void updateCliente(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
			String telefono, String email) {
		new UpdateCliente(id,nombre, apellidos, street, ciudad, zipcode, telefono, email).execute();

	}

	@Override
	public List<Map<String, Object>> findAllClients() {
		return new FindAllClientes().execute();
	}

	@Override
	public void newCliente(String dni, String nombre, String apellidos, String calle, String ciudad, String zipcode,
			String telefono, String email) {
		new CreateCliente(dni, nombre, apellidos, calle, ciudad, zipcode, telefono, email).execute();

	}

	@Override
	public Map<String, Object> detalleCliente(Long id) {
		return new FindCliente(id).execute();
	}

}
