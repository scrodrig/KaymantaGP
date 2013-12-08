/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.kaymanta.gestproy.modelo.FechasNoLaborables;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author schubert_david
 */
@Stateless
@LocalBean
public class FechasNoLaborablesDAO extends DefaultGenericDAOImple<FechasNoLaborables, Date> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public FechasNoLaborablesDAO() {
        super(FechasNoLaborables.class);

    }
    
    public FechasNoLaborables findLastByActividad(Date d){

        try {
            String sql = "SELECT obj FROM FechasNoLaborables obj WHERE obj.fecha=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, d);
            System.out.println(qry.toString());
             return (FechasNoLaborables) qry.getSingleResult();            
        } catch (NoResultException e) {
            return null;
        }
    }

}
