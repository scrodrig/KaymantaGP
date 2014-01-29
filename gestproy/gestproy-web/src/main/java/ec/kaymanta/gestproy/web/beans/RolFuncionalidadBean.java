/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Funcionalidad;
import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import ec.kaymanta.gestproy.modelo.RolFuncionalidadPK;
import ec.kaymanta.gestproy.servicio.FuncionalidadServicio;
import ec.kaymanta.gestproy.servicio.ModuloServicio;
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
public class RolFuncionalidadBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of RolFuncionalidadBean
     */
    private final String ENTIDAD = "Roles Funcionalidad";
    @EJB
    private FuncionalidadServicio funcionalidadServicio;
    @EJB
    private ModuloServicio moduloServicio;
    @EJB
    private RolServicio rolServicio;
    @EJB
    private RolFuncionalidadServicio rolFuncionalidadServicio;
    private List<Funcionalidad> funcionalidades;
    private Funcionalidad funcionalidadSeleccionado;
    private Funcionalidad respaldo;
    private Funcionalidad funcionalidad;
    private Modulo moduloB;
    private String modulo;
    private String rol;
    private List<Modulo> modulosB;
    private List<Rol> rolesB;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.rolesB = this.rolServicio.obtener();
    }

    public void actualizarModulosB(ActionEvent evento) {
        this.modulosB = this.moduloServicio.obtener();
    }

    public void cargarTabla(ActionEvent evento) {
        this.funcionalidades = this.funcionalidadServicio.fingByModulo(this.moduloServicio.findByID(Long.valueOf(this.modulo)));
    }

    /**
     * Método que se ejecuta cuando se selecciona una fila de la tabla.
     *
     * @param evento Evento relacionado con la selección de la fila en la tabla.
     */
    public void filaSeleccionada(ActionEvent evento) {
        if (funcionalidadSeleccionado instanceof Funcionalidad) {
            try {
                this.funcionalidad = new Funcionalidad();
                this.funcionalidad = (Funcionalidad) BeanUtils.cloneBean(this.funcionalidadSeleccionado);
            } catch (Exception e) {
            }
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
        Modulo modulo = this.moduloServicio.findByID(Long.valueOf(this.modulo));
        this.funcionalidad = new Funcionalidad();
        this.funcionalidad = new Funcionalidad();
        this.funcionalidad.setModulo(modulo);
        this.funcionalidad.setCodModulo(modulo.getCodigo());
        super.crear();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón modificar.
     *
     * @param evento Evento relacionado con el botón modificar.
     */
    public void modificar(ActionEvent evento) {
        try {
            this.respaldo = this.funcionalidadSeleccionado;
            this.funcionalidad = new Funcionalidad();
            BeanUtils.copyProperties(this.funcionalidad, this.funcionalidadSeleccionado);
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
        RolFuncionalidad rolFuncionalidad = new RolFuncionalidad();
        rolFuncionalidad = this.rolFuncionalidadServicio.findByID(this.rolServicio.findByID(Long.valueOf(rol)), funcionalidad);
        this.rolFuncionalidadServicio.eliminar(rolFuncionalidad);
        this.funcionalidadServicio.eliminar(this.funcionalidad);
        this.funcionalidades.remove(this.funcionalidadSeleccionado);
        MensajesGenericos.infoEliminar(ENTIDAD, this.funcionalidadSeleccionado.getNombre().toString(), Boolean.TRUE);
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
                this.funcionalidadServicio.crear(this.funcionalidad);
                this.funcionalidades.add(this.funcionalidad);
                RolFuncionalidad rolFuncionalidad = new RolFuncionalidad();
                rolFuncionalidad.getPk().setFuncionalidad(this.funcionalidad.getCodigo());
                rolFuncionalidad.getPk().setRol(Long.parseLong(this.rol));
                rolFuncionalidad.setFuncionalidad(funcionalidad);
                rolFuncionalidad.setRol(rolServicio.findByID(Long.valueOf(rol)));
                rolFuncionalidad.setFecha(new Date());
                rolFuncionalidadServicio.crear(rolFuncionalidad);
                MensajesGenericos.infoCrear(ENTIDAD, this.funcionalidad.getNombre().toString(), Boolean.TRUE);
                super.sinSeleccion();
            } else {
                this.funcionalidadServicio.actualizar(this.funcionalidad);
                RolFuncionalidad rolFuncionalidad = new RolFuncionalidad();
                rolFuncionalidad.getPk().setFuncionalidad(this.funcionalidad.getCodigo());
                rolFuncionalidad.getPk().setRol(Long.parseLong(this.rol));
                rolFuncionalidad.setFuncionalidad(funcionalidad);
                rolFuncionalidad.setRol(rolServicio.findByID(Long.valueOf(rol)));
                rolFuncionalidad.setFecha(new Date());
                rolFuncionalidadServicio.actualizar(rolFuncionalidad);
                BeanUtils.copyProperties(this.respaldo, this.funcionalidad);
                MensajesGenericos.infoModificar(ENTIDAD, this.funcionalidad.getNombre().toString().concat(" - "), Boolean.TRUE);
                super.seleccionadoUno();
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
        this.modulo = new String();
        this.rol = new String();
        MensajesGenericos.infoCancelar();
    }

    public List<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public Funcionalidad getFuncionalidadSeleccionado() {
        return funcionalidadSeleccionado;
    }

    public void setFuncionalidadSeleccionado(Funcionalidad funcionalidadSeleccionado) {
        this.funcionalidadSeleccionado = funcionalidadSeleccionado;
    }

    public Funcionalidad getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Funcionalidad respaldo) {
        this.respaldo = respaldo;
    }

    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidad funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Modulo> getModulosB() {
        return modulosB;
    }

    public void setModulosB(List<Modulo> modulosB) {
        this.modulosB = modulosB;
    }

    public List<Rol> getRolesB() {
        return rolesB;
    }

    public void setRolesB(List<Rol> rolesB) {
        this.rolesB = rolesB;
    }

    public Modulo getModuloB() {
        return moduloB;
    }

    public void setModuloB(Modulo moduloB) {
        this.moduloB = moduloB;
    }
}
