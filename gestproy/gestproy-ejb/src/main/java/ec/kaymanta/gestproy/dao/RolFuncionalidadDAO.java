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
import ec.kaymanta.gestproy.modelo.Funcionalidad;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import ec.kaymanta.gestproy.modelo.RolFuncionalidadPK;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * La Clase RolFuncionalidadDAO especifica e implementa las operaciones de
 * acceso a datos relacionadas con la entidad RolFuncionalidad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class RolFuncionalidadDAO extends DefaultGenericDAOImple<RolFuncionalidad, RolFuncionalidadPK> {

    public RolFuncionalidadDAO() {
        super(RolFuncionalidad.class);

    }

    public List<RolFuncionalidad> findByRol(Rol rol) {
         String sql = "SELECT obj FROM RolFuncionalidad obj WHERE obj.rol=?1";
        Query qry = this.getEntityManager().createQuery(sql);
        qry.setParameter(1, rol);
        return qry.getResultList();
    }
    
    
    public RolFuncionalidad findByIdObj(Funcionalidad funcionalidad,Rol rol) {
         String sql = "SELECT obj FROM RolFuncionalidad obj WHERE obj.rol=?1 AND obj.funcionalidad=?2";
        Query qry = this.getEntityManager().createQuery(sql);
        qry.setParameter(1, rol);
        qry.setParameter(2, funcionalidad);
        return (RolFuncionalidad) qry.getSingleResult();
    }
}
