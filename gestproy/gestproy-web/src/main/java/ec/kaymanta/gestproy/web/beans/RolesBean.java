/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.servicio.RolServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class RolesBean extends BotonesBean implements Serializable{ 

    /**
     * Creates a new instance of ModulosBean
     */
    @EJB
    private RolServicio rolServicio;

    private List<Rol> roles;
    private Rol rol;
    private Rol rolSeleccionado;
    private Rol respaldo;


    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.roles = this.rolServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.rol = new Rol();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        rol = new Rol();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        rol = new Rol();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.rol = new Rol();
            this.rol = (Rol) BeanUtils.cloneBean(this.rolSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }


    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.rolServicio.crear(this.rol);
                this.roles.add(this.rol);
                MensajesGenericos.infoCrear("Rol", this.rol.getCodigo().toString().concat(" - ").concat(this.rol.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.roles.indexOf(this.rol);
                this.rolServicio.actualizar(this.rol);
                roles.set(i, this.rol);
                MensajesGenericos.infoModificar("Rol", this.rol.getCodigo().toString().concat(" - ").concat(this.rol.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.rol = new Rol();
        try {
            this.rol = (Rol) BeanUtils.cloneBean(this.rolSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.rolSeleccionado);
        this.rolServicio.eliminar(this.rolSeleccionado);        
        this.roles.remove(this.rolSeleccionado);
        MensajesGenericos.infoEliminar("Rol", this.rol.getCodigo().toString().concat(" - ").concat(this.rol.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (rolSeleccionado instanceof Rol) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    //Getters and Setters
    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Rol getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Rol respaldo) {
        this.respaldo = respaldo;
    }
  
    
}
