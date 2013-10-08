/*
 * KAYMANTA CIA. LTDA.
 * Sistema: SIGA 1.0 - KAYMANTA
 * Creado: 11-oct-2012 - 01:52:08
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2012-2013 Kaymanta Todos los derechos reservados.
 */
package ec.kaymanta.gestproy.servicio;

import com.persist.common.dao.ResultadoBusqueda;
import ec.kaymanta.gestproy.dao.CantonDAO;
import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.CantonPK;
import java.util.List;
import javax.ejb.*;

/**
 * La Clase CantonServicio especifica e implementa las operaciones de reglas de
 * negocio relacionadas con Canton.
 *
 * @author Henry Coral
 * @version 1.0
 */
@Stateless
@LocalBean
public class CantonServicio {

    /**
     * Referencia al EJB de acceso a datos: CantonDAO.
     */
    @EJB
    private CantonDAO cantonDAO;

    /**
     * Ejecuta b�squedas sobre la entidad Canton de acuerdo a las condiciones
     * especificadas.
     *
     * @param resultado Resultado de la b�squeda anterior.
     * @param canton Objeto que define las condiciones de b�squeda.
     * @param order Cadena opcional utilizada para definir el orden de
     * despliegue de los resultados.
     * @return Resultado de la b�squeda.
     */
    public ResultadoBusqueda<Canton> busqueda(ResultadoBusqueda<Canton> resultado, Canton canton, String... order) {
        return this.cantonDAO.find(canton, resultado, order);
    }

    /**
     * Crea un nuevo registro perteneciente a la entidad: Canton.
     *
     * @param canton Objeto que contiene la informaci�n del registro a ser
     * insertado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void crear(Canton canton) {
        this.cantonDAO.insert(canton);
    }

    /**
     * Modifica un registro perteneciente a la entidad: Canton.
     *
     * @param canton Objeto que contiene la informaci�n del registro a ser
     * modificado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizar(Canton canton) {
        this.cantonDAO.update(canton);
    }

    /**
     * Elimina un registro perteneciente a la entidad: Canton.
     *
     * @param codigo Clave primaria correspondiente al objeto a ser eliminado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void eliminar(CantonPK codigo) {
        Canton cantonTmp = this.cantonDAO.find(new Canton(codigo)).get(0);
        this.cantonDAO.remove(cantonTmp);
    }

    /**
     * Obtiene un objeto de acuerdo al valor de su clave primaria.
     *
     * @param pk Valor de la clave primaria para realizar la b�squeda del
     * objeto.
     * @return Objeto de tipo Canton cuya clave primaria es igual a la recibida
     * en el par�metro pk.
     */
    public Canton obtenerPorId(CantonPK pk) {
        return this.cantonDAO.findById(pk, false);
    }

    public List<Canton> obtenerPorProvincia(String provincia) {
        CantonPK pk = new CantonPK();
        pk.setProvincia(Long.parseLong(provincia));
        Canton canton = new Canton(pk);
        return this.cantonDAO.find(canton);
    }
}
