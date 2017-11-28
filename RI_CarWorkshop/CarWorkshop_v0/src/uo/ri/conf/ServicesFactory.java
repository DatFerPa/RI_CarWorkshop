package uo.ri.conf;

import uo.ri.bussiness.AdminService;
import uo.ri.bussiness.CashService;
import uo.ri.bussiness.ForemanService;
import uo.ri.bussiness.impl.AdminServiceImpl;
import uo.ri.bussiness.impl.CashServiceImpl;
import uo.ri.bussiness.impl.ForemanServiceImpl;

public class ServicesFactory {

	public AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public CashService getCashService() {
		return new CashServiceImpl();
	}
	
	public ForemanService getForemanService() {
		return new ForemanServiceImpl();
	}

}
