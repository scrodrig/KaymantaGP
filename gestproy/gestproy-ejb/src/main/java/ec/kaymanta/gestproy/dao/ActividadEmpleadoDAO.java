/*
 * KAYMANTA CIA. LTDA.
 * Sistema: GESTPROY 1.0 - KAYMANTA
 * Creado: 15-sep-2013 - 15:12:12
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2013-2014 Kaymanta Todos los derechos reservados.
 */
package ec.kaymanta.gestproy.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEmpleado;
import ec.kaymanta.gestproy.modelo.ActividadEmpleadoPK;
import ec.kaymanta.gestproy.modelo.Empleado;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * La Clase ActividadEmpleadoDAO especifica e implementa las operaciones de
 * acceso a datos relacionadas con la entidad ActividadEmpleado.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class ActividadEmpleadoDAO extends DefaultGenericDAOImple<ActividadEmpleado, ActividadEmpleadoPK> {

    public ActividadEmpleadoDAO() {
        super(ActividadEmpleado.class);

    }
    
    public List<ActividadEmpleado> findBySubActividad(Actividad subActividad) {
        String sql = "SELECT obj FROM ActividadEmpleado obj WHERE obj.actividad=?1";
        Query qry = this.getEntityManager().createQuery(sql);
        qry.setParameter(1, subActividad);
        return qry.getResultList();
    }
    
    public List<ActividadEmpleado> findBySubActividadAndEmpleado(Actividad subActividad, Empleado empleado) {
        String sql = "SELECT obj FROM ActividadEmpleado obj WHERE obj.actividad=?1 AND obj.empleado=?2";
        Query qry = this.getEntityManager().createQuery(sql);
        qry.setParameter(1, subActividad);
        qry.setParameter(2, empleado);
        return qry.getResultList();
    }
}
