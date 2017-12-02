package uo.ri.bussiness.impl;

import java.util.List;
import java.util.Map;

import uo.ri.bussiness.AdminService;
import uo.ri.bussiness.impl.admin.bono.GenerarBonosTresAverias;
import uo.ri.bussiness.impl.admin.mechanic.AddMechanic;
import uo.ri.bussiness.impl.admin.mechanic.DeleteMechanic;
import uo.ri.bussiness.impl.admin.mechanic.FindAllMechanics;
import uo.ri.bussiness.impl.admin.mechanic.UpdateMechanic;

public class AdminServiceImpl implements AdminService {

    @Override
    public void newMechanic(String nombre, String apellidos, String dni) {
	new AddMechanic(nombre, apellidos, dni).execute();

    }

    @Override
    public void deleteMechanic(Long id) {
	new DeleteMechanic(id).execute();

    }

    @Override
    public void updateMechanic(Long id, String nombre, String apellidos) {
	new UpdateMechanic(id, nombre, apellidos).execute();

    }

    @Override
    public List<Map<String, Object>> findAllMechanics() {
	return new FindAllMechanics().execute();
    }

    @Override
    public void generarBonosTresAverias() {
	new GenerarBonosTresAverias().execute();
    }

}
