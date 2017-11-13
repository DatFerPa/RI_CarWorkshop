package uo.ri.bussiness.impl;

import java.util.List;
import java.util.Map;

import uo.ri.bussiness.CashService;
import uo.ri.bussiness.impl.cash.CreateInvoiceFor;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService{

	@Override
	public Map<String, Object> createInvoiceFor(List<Long> listaIds) throws BusinessException {
		return new CreateInvoiceFor(listaIds).execute();
	}
	
	

}
