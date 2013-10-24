/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Funcionalidad;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import ec.kaymanta.gestproy.servicio.FuncionalidadServicio;
import ec.kaymanta.gestproy.servicio.RolFuncionalidadServicio;
import ec.kaymanta.gestproy.servicio.RolServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.Date;
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
public class RolesFuncionalidadBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of RolesFuncionalidadBean
     */
    /**
     * Creates a new instance of UsuariosRolBean
     */
    @EJB
    private RolFuncionalidadServicio rolFuncionalidadServicio;
    @EJB
    private FuncionalidadServicio funcionalidadServicio;
    @EJB
    private RolServicio rolServicio;
    private List<RolFuncionalidad> rolesFuncionalidad;
    private RolFuncionalidad rolFuncionalidadSeleccionado;
    private RolFuncionalidad respaldo;
    private RolFuncionalidad rolFuncionalidad;
    private List<Funcionalidad> funcionalidades;
    private List<Rol> roles;
    private String rol;
    private String funcionalidad;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.rolesFuncionalidad = this.rolFuncionalidadServicio.obtener();
        this.funcionalidades = this.funcionalidadServicio.obtener();
        this.roles = this.rolServicio.obtener();
    }

    /**
     * Método que se ejecuta cuando se selecciona una fila de la tabla.
     *
     * @param evento Evento relacionado con la selección de la fila en la tabla.
     */
    public void filaSeleccionada(ActionEvent evento) {
        if (rolFuncionalidadSeleccionado instanceof RolFuncionalidad) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón nuevo.
     *
     * @param evento Evento relacionado con el botón nuevo.
     */
    public void nuevo(ActionEvent evento) {

        this.rolFuncionalidad = new RolFuncionalidad();
        this.funcionalidad = new String();
        this.rol = new String();
        super.crear();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón modificar.
     *
     * @param evento Evento relacionado con el botón modificar.
     */
    public void modificar(ActionEvent evento) {
        try {
            this.respaldo = this.rolFuncionalidadSeleccionado;
            this.rolFuncionalidad = new RolFuncionalidad();
            BeanUtils.copyProperties(this.rolFuncionalidad, this.rolFuncionalidadSeleccionado);
            this.funcionalidad = rolFuncionalidad.getFuncionalidad().getCodigo().toString();
            this.rol = rolFuncionalidad.getRol().getCodigo().toString();
            super.modificar();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón eliminar.
     *
     * @param evento Evento relacionado con el botón eliminar.
     */
    public void eliminar(ActionEvent evento) {
        this.rolFuncionalidadServicio.eliminar(this.rolFuncionalidad);
        this.rolesFuncionalidad.remove(this.rolFuncionalidadSeleccionado);
        MensajesGenericos.infoEliminar("RolFuncionalidad", this.rolFuncionalidadSeleccionado.getPk().toString(), Boolean.TRUE);
        super.sinSeleccion();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón guardar.
     *
     * @param evento Evento relacionado con el botón guardar.
     */
    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                Funcionalidad funcionalidadTmp = this.funcionalidadServicio.findByID(Long.parseLong(this.funcionalidad));
                Rol rolTmp = this.rolServicio.findByID(Long.parseLong(this.rol));
                this.rolFuncionalidad.getPk().setFuncionalidad(Long.parseLong(this.funcionalidad));
                this.rolFuncionalidad.getPk().setRol(Long.parseLong(this.rol));
                this.rolFuncionalidad.setFuncionalidad(funcionalidadTmp);
                this.rolFuncionalidad.setRol(rolTmp);
                this.rolFuncionalidad.setFecha(new Date());
                this.rolFuncionalidadServicio.crear(this.rolFuncionalidad);
                this.rolesFuncionalidad.add(this.rolFuncionalidad);

                this.funcionalidad = new String();
                this.rol = new String();
                MensajesGenericos.infoCrear("RolFuncionalidad", this.rolFuncionalidad.getPk().toString().concat(" - ").concat(this.rolFuncionalidad.getPk().toString()), Boolean.TRUE);
                super.sinSeleccion();
            } else {
                this.rolFuncionalidad.setFecha(new Date());
                this.rolFuncionalidadServicio.actualizar(this.rolFuncionalidad);
                BeanUtils.copyProperties(this.respaldo, this.rolFuncionalidad);
                MensajesGenericos.infoModificar("RolFuncionalidad", this.rolFuncionalidad.getPk().toString().concat(" - ").concat(this.rolFuncionalidad.getPk().toString()), Boolean.TRUE);
                super.seleccionadoUno();
                this.funcionalidad = new String();
                this.rol = new String();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón cancelar.
     *
     * @param evento Evento relacionado con el botón cancelar.
     */
    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        MensajesGenericos.infoCancelar();
    }

    //Getters And Setters
    public List<RolFuncionalidad> getRolesFuncionalidad() {
        return rolesFuncionalidad;
    }

    public void setRolesFuncionalidad(List<RolFuncionalidad> rolesFuncionalidad) {
        this.rolesFuncionalidad = rolesFuncionalidad;
    }

    public RolFuncionalidad getRolFuncionalidadSeleccionado() {
        return rolFuncionalidadSeleccionado;
    }

    public void setRolFuncionalidadSeleccionado(RolFuncionalidad rolFuncionalidadSeleccionado) {
        this.rolFuncionalidadSeleccionado = rolFuncionalidadSeleccionado;
    }

    public RolFuncionalidad getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(RolFuncionalidad respaldo) {
        this.respaldo = respaldo;
    }

    public RolFuncionalidad getRolFuncionalidad() {
        return rolFuncionalidad;
    }

    public void setRolFuncionalidad(RolFuncionalidad rolFuncionalidad) {
        this.rolFuncionalidad = rolFuncionalidad;
    }

    public List<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(String funcionalidad) {
        this.funcionalidad = funcionalidad;
    }
}
