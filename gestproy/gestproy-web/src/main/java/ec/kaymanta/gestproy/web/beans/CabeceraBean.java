/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@SessionScoped
public class CabeceraBean {

    /**
     * Creates a new instance of CabeceraBean
     */
    public CabeceraBean() {
    }
    
    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "inicio";
    }
}
