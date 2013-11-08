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
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.DocumentosProyectoPK;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase DocumentosProyectoDAO especifica e implementa las operaciones de
 * acceso a datos relacionadas con la entidad DocumentosProyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class DocumentosProyectoDAO extends DefaultGenericDAOImple<DocumentosProyecto, DocumentosProyectoPK> {

    public DocumentosProyectoDAO() {
        super(DocumentosProyecto.class);

    }

    public List<DocumentosProyecto> findByProyecto(Long proyecto) {
        System.out.println("ESTOY EN DAO y EL PROYECTO ES "+ proyecto);

        try {
            String sql = "SELECT obj FROM DocumentosProyecto obj WHERE obj.pk.proyecto=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, proyecto);
            System.out.println(qry.toString());
             System.out.println("La dimensión del arreglo 0 es: "+qry.getResultList().size());
            return qry.getResultList();
            
        } catch (NoResultException e) {
            return null;
        }
    }
}
