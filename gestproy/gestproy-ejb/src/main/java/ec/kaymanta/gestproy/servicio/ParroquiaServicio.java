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
import ec.kaymanta.gestproy.dao.ParroquiaDAO;
import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import java.util.List;
import javax.ejb.*;

/**
 * La Clase ParroquiaServicio especifica e implementa las operaciones de reglas
 * de negocio relacionadas con Parroquia.
 *
 * @author Henry Coral
 * @version 1.0
 */
@Stateless
@LocalBean
public class ParroquiaServicio {

    /**
     * Referencia al EJB de acceso a datos: ParroquiaDAO.
     */
    @EJB
    private ParroquiaDAO parroquiaDAO;

    /**
     * Ejecuta b�squedas sobre la entidad Parroquia de acuerdo a las condiciones
     * especificadas.
     *
     * @param resultado Resultado de la b�squeda anterior.
     * @param parroquia Objeto que define las condiciones de b�squeda.
     * @param order Cadena opcional utilizada para definir el orden de
     * despliegue de los resultados.
     * @return Resultado de la b�squeda.
     */
    public ResultadoBusqueda<Parroquia> busqueda(ResultadoBusqueda<Parroquia> resultado, Parroquia parroquia, String... order) {
        return this.parroquiaDAO.find(parroquia, resultado, order);
    }

    /**
     * Crea un nuevo registro perteneciente a la entidad: Parroquia.
     *
     * @param parroquia Objeto que contiene la informaci�n del registro a ser
     * insertado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void crear(Parroquia parroquia) {
        this.parroquiaDAO.insert(parroquia);
    }

    /**
     * Modifica un registro perteneciente a la entidad: Parroquia.
     *
     * @param parroquia Objeto que contiene la informaci�n del registro a ser
     * modificado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizar(Parroquia parroquia) {
        this.parroquiaDAO.update(parroquia);
    }

    /**
     * Elimina un registro perteneciente a la entidad: Parroquia.
     *
     * @param codigo Clave primaria correspondiente al objeto a ser eliminado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void eliminar(ParroquiaPK codigo) {
        Parroquia parroquiaTmp = this.parroquiaDAO.find(new Parroquia(codigo)).get(0);
        this.parroquiaDAO.remove(parroquiaTmp);
    }

    /**
     * Obtiene un objeto de acuerdo al valor de su clave primaria.
     *
     * @param pk Valor de la clave primaria para realizar la b�squeda del
     * objeto.
     * @return Objeto de tipo Parroquia cuya clave primaria es igual a la
     * recibida en el par�metro pk.
     */
    public Parroquia obtenerPorId(ParroquiaPK pk) {
        return this.parroquiaDAO.findById(pk, false);
    }

    /**
     * Obtiene todas las parroquias de acuerdo a una provincia y a un canton.
     * @param provincia Codigo de la provincia.
     * @param canton C�digo del cant�n.
     * @return Listado de parroquias.
     */
    public List<Parroquia> obtenerPorProvinciaCanton(String provincia, String canton) {
        ParroquiaPK pk = new ParroquiaPK();
        pk.setProvincia(Long.parseLong(provincia));
        pk.setCanton(Long.parseLong(canton));
        Parroquia parroquiaB = new Parroquia(pk);
        return this.parroquiaDAO.find(parroquiaB);
    }
    
    /**
     * Obtiene todas las parroquias de acuerdo a una provincia y a un canton.
     * @param canton C�digo del cant�n.
     * @return Listado de parroquias.
     */
    public List<Parroquia> findByCantonAndProvincia(Long canton, Long provincia) {
        return this.parroquiaDAO.findByCantonAndProvincia(provincia, canton);
    }
}
