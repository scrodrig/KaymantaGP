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
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.modelo.UsuarioRolPK;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase UsuarioRolDAO especifica e implementa las operaciones de 
 * acceso a datos relacionadas con la entidad UsuarioRol.
 * 
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class UsuarioRolDAO extends DefaultGenericDAOImple<UsuarioRol, UsuarioRolPK> {

    public UsuarioRolDAO()
    {
        super(UsuarioRol.class);
    
    }
    
    public List<UsuarioRol> findByUser(Usuario usuario)
    {
        try {
            System.out.println(usuario.getUsuario());
            String sql = "SELECT obj FROM UsuarioRol obj WHERE obj.usuario=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, usuario);
            System.out.println(qry.toString());
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }        
}
