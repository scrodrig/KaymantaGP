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
import ec.kaymanta.gestproy.modelo.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase ActividadDAO especifica e implementa las operaciones de acceso a
 * datos relacionadas con la entidad Actividad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class ActividadDAO extends DefaultGenericDAOImple<Actividad, Long> {

    public ActividadDAO() {
        super(Actividad.class);

    }

    public List<Actividad> findByProyecto(Proyecto proyecto) {
        System.out.println("ESTOY EN DAO y EL PROYECTO ES " + proyecto);

        try {
            List<Actividad> actividades = new ArrayList<Actividad>();
            List<Actividad> retorno = new ArrayList<Actividad>();

            String sql = "SELECT obj FROM Actividad obj WHERE obj.proyecto=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, proyecto);
            System.out.println(qry.toString());
            System.out.println("La dimensión del arreglo 0 es: " + qry.getResultList().size());
            actividades = qry.getResultList();
            for (int i = 0; i < actividades.size(); i++) {
                if (actividades.get(i).getActividad() == null) {
                    retorno.add(actividades.get(i));
                }
            }
            return retorno;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Actividad> findByProyectoAndActividad(Proyecto proyecto, Actividad actividad) {
        System.out.println("ESTOY EN DAO y EL PROYECTO ES " + proyecto + "ESTOY EN DAO y LA ACTIVIDAD ES " + actividad);

        try {
            String sql = "SELECT obj FROM Actividad obj WHERE obj.proyecto=?1 AND obj.actividad=?2";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, proyecto);
            qry.setParameter(2, actividad);
            System.out.println(qry.toString());
            System.out.println("La dimensión del arreglo 0 es: " + qry.getResultList().size());
            return qry.getResultList();

        } catch (NoResultException e) {
            return null;
        }
    }
}
