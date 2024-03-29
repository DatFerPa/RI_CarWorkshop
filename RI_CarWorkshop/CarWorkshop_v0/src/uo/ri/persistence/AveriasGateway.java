package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.common.BusinessException;

public interface AveriasGateway {

    public void setConnection(Connection connection);

    public void cambiarEstadoAverias(List<Long> idsAveria, String status);

    public void verificarAveriasTerminadas(List<Long> idsAveria) throws BusinessException;

    public void actualizarImporteAveria(Long idAveria, double totalAveria);

    public double consultaImporteRepuestos(Long idAveria);

    public double consultaImporteManoObra(Long idAveria) throws BusinessException;

    public void actualizarUsadaBono(Long idAveria, boolean usadaBono);
}
