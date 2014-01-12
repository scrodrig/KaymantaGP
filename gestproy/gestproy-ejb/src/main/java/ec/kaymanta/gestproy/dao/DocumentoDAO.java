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
import ec.kaymanta.gestproy.modelo.Documento;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * La Clase DocumentoDAO especifica e implementa las operaciones de acceso a
 * datos relacionadas con la entidad Documento.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Stateless
@LocalBean
public class DocumentoDAO extends DefaultGenericDAOImple<Documento, Long> {

    public DocumentoDAO() {
        super(Documento.class);

    }

    public List<Documento> getDocumentos(String parametro, String valor, String criterio) {
        String sql = "SELECT obj FROM Documento obj WHERE obj." + parametro + " "+ criterio +"?1";

        Query qry = this.getEntityManager().createQuery(sql);
        if ("LIKE".equals(criterio)) {
            if ("codigo".equals(parametro)) {
                qry.setParameter(1, "%" + Long.valueOf(valor) + "%");

            } else {
                qry.setParameter(1, "%" + valor + "%");
            }
        } else if("=".equals(criterio)){
            if ("codigo".equals(parametro)) {
                qry.setParameter(1,Long.valueOf(valor));

            } else {
                qry.setParameter(1, valor);
            }
        }

        return qry.getResultList();
    }
}
