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
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.HistorialDocumentoPK;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * La Clase HistorialDocumentoDAO especifica e implementa las operaciones de
 * acceso a datos relacionadas con la entidad HistorialDocumento.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class HistorialDocumentoDAO extends DefaultGenericDAOImple<HistorialDocumento, HistorialDocumentoPK> {

    public HistorialDocumentoDAO() {
        super(HistorialDocumento.class);

    }

    public List<HistorialDocumento> findByDocumento(Long documento) {
        try {
            String sql = "SELECT obj FROM HistorialDocumento obj WHERE obj.pk.documento=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, documento);
            return qry.getResultList();

        } catch (NoResultException e) {
            return null;
        }
    }
}
