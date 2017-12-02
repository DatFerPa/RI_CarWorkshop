package uo.ri.bussiness;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService {

    public Map<String, Object> createInvoiceFor(List<Long> listaIds) throws BusinessException;

}
