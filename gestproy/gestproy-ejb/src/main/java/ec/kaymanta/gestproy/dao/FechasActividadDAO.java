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
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.FechasActividadPK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase FechasActividadDAO especifica e implementa las operaciones de acceso
 * a datos relacionadas con la entidad FechasActividad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class FechasActividadDAO extends DefaultGenericDAOImple<FechasActividad, FechasActividadPK> {

    public FechasActividadDAO() {
        super(FechasActividad.class);

    }
    
    public FechasActividad findLastByActividad(Actividad actividad){
        try {
            List<FechasActividad> factividades = new ArrayList<FechasActividad>();
            String sql = "SELECT obj FROM FechasActividad obj WHERE obj.actividad=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, actividad);
            factividades = qry.getResultList();            
            return factividades.get(factividades.size()-1);

        } catch (NoResultException e) {
            return null;
        }
    }
    
    
}
