/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class CabeceraBean implements Serializable{

    private Long modulo;
    private String nombreFuncionalidad;

    @PostConstruct
    public void postConstructor() {
        this.modulo = 2l;
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "inicio";
    }

    public Long getModulo() {
        return modulo;
    }

    public void setModulo(Long modulo) {
        this.modulo = modulo;
    }

    public String getNombreFuncionalidad() {
        return nombreFuncionalidad;
    }

    public void setNombreFuncionalidad(String nombreFuncionalidad) {
        this.nombreFuncionalidad = nombreFuncionalidad;
    }
}
