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
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase UsuarioDAO especifica e implementa las operaciones de 
 * acceso a datos relacionadas con la entidad Usuario.
 * 
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class UsuarioDAO extends DefaultGenericDAOImple<Usuario, String> {

    
    public UsuarioDAO()
    {
        super(Usuario.class);
    
    }
    
    public Usuario findByName(String nombreUsuario)
    {
        try {
            System.out.println(nombreUsuario);
            String sql = "SELECT obj FROM Usuario obj WHERE obj.usuario=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, nombreUsuario);
            System.out.println(qry.toString());
            return (Usuario) qry.getSingleResult();
        }catch(NoResultException e)
        {
            return null;
        }
    }
    
}
