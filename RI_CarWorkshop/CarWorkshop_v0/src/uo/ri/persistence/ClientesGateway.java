package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ClientesGateway {

    public void setConnection(Connection connection);

    public Map<String, Object> findById(Long id);

    public List<Map<String, Object>> findAll();

    public List<Map<String, Object>> findRecomendados(Long id);

    public void update(Long id, String nombre, String apellidos, String street, String ciudad, String zipcode,
	    String telefono, String email);

    public void save(String nombre, String apellidos, String street, String ciudad, String zipcode, String telefono,
	    String email, String dni);

    public void createMediopagoMetalico(Long id);

    public void createRecomendacion(Long idRecomendado, Long idRecomendador);

    public void delete(Long id);

    public void delete_mediopago(Long id);

    public void delte_recomendaciones(Long id);

    /**
     * Método que consulta el último id de la tabla de los clientes
     * 
     * @return el identificador del último cliente
     */
    public long getLastClienteId();

    /**
     * Método que devuleve el status de las facturas de un clinete
     * 
     * @param id,
     *            el identificador del cliente del cual se quieren sacar los status
     * @return
     */
    public List<String> getEstadosFacturasCliente(Long id);

    public int getNumeroCochesCliente(Long id);

    public List<Map<String, Object>> getAveriasCliente(Long id);

    public void createBono(Long idCliente, String type, double acumulado, double disponible, String codigo,
	    String descripcion);

    /**
     * Método que consulta el último valor de la columna código de la tabla TBonos
     * 
     * @return el valor de dicho código
     */
    public String getUltimoCodigo();
}
