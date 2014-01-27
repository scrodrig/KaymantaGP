/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.modelo.UsuarioRolPK;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.RolServicio;
import ec.kaymanta.gestproy.servicio.UsuarioRolServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class UsuariosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of UsuariosBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private RolServicio rolServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    private List<Usuario> usuarios;
    private List<Empleado> empleados;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private Usuario respaldo;
    private Usuario usrSesion;
    private Usuario usrAuditoria;
    private List<Rol> rolesFuente;
    private List<Rol> rolesSeleccionados;
    private List<String> rolesFuenteString;
    private List<String> rolesDestinoString;
    private List<String> rolesString;
    private UsuarioRol usuarioRol;
    private DualListModel<String> roles;
    private List<UsuarioRol> usuariosRoles;
    //VARIABLES CONTROL DE MODIFICACIÖN DE TABLAS DE RELACIÖN EXTERNA
    private List<String> rolesAEliminar;
    private List<String> rolesAIngresar;
    private List<Rol> rolesEliminar;
    private List<Rol> rolesIngresar;
    private String codigoEmpleado;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
        this.empleados=this.empleadoServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.usuario = new Usuario();
        this.rolesFuente = this.rolServicio.obtener();
        this.rolesSeleccionados = new ArrayList<Rol>();
        this.rolesFuenteString = new ArrayList<String>();
        this.rolesFuenteString = this.getListStringRol(rolesFuente);
        this.rolesDestinoString = new ArrayList<String>();
        this.usuariosRoles = new ArrayList<UsuarioRol>();
        roles = new DualListModel<String>(rolesFuenteString, rolesDestinoString);

    }

    public List<Rol> getListRolString(List<String> source) {
        List<Rol> target = new ArrayList<Rol>();
        for (int i = 0; i < source.size(); i++) {
            target.add(this.rolServicio.findByID(Long.parseLong(source.get(i))));
        }
        return target;
    }

    public List<Rol> getListRolByNameString(List<String> source) {
        List<Rol> target = new ArrayList<Rol>();
        for (int i = 0; i < source.size(); i++) {
            target.add(this.rolServicio.findByName(source.get(i)));
        }
        return target;
    }

    public List<String> getListStringRol(List<Rol> source) {
        List<String> target = new ArrayList<String>();
        for (int i = 0; i < source.size(); i++) {
            //target.add(source.get(i).getCodigo().toString());
            target.add(source.get(i).getNombre().toString());

        }
        return target;
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        usuario = new Usuario();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        //super.sinSeleccion();
        usuario = new Usuario();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.usuario = new Usuario();
            this.usuario = (Usuario) BeanUtils.cloneBean(this.usuarioSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
            try {
                usuarioServicio.findByID(usr);
                return usuarioServicio.findByID(usr).getUsuario();
            } catch (NullPointerException e) {
                return "";
            }
        }
    }
    
    public String getNombreEmpleado(String usr) {
        if (usr == null || "".equals(usr)) {
            return "No asignado";
        } else {
            try {
                empleadoServicio.findByID(usr);
                return empleadoServicio.findByID(usr).getNombre();
            } catch (NullPointerException e) {
                return "No asignado";
            }
        }
    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.usuario.setUsrCreacion(usrSesion.getCodigo());
                /*PICKLIST ROLES*/
                //this.empleadoServicio.findByID(codigoEmpleado);
                this.rolesSeleccionados = this.getListRolByNameString(rolesDestinoString);
                this.usuario.setCodigo(codigoEmpleado);
                this.usuario.setFcreacion(new Date());
                this.usuario.setUsuario(usuario.getUsuario().toLowerCase());
                this.usuarioServicio.crear(this.usuario);
                this.usuarios.add(this.usuario);
                //CREACION LLAVES COMPUESTAS
                for (int i = 0; i < rolesSeleccionados.size(); i++) {
                    this.usuarioRol = new UsuarioRol();
                    this.usuarioRol.getPk().setUsuario(this.usuario.getCodigo());
                    this.usuarioRol.getPk().setRol(rolesSeleccionados.get(i).getCodigo());
                    this.usuarioRol.setUsuario(usuario);
                    this.usuarioRol.setRol(rolesSeleccionados.get(i));
                    this.usuarioRol.setFecha(new Date());
                    this.usuarioRolServicio.crear(usuarioRol);
                }
                MensajesGenericos.infoCrear("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
                this.rolesDestinoString = new ArrayList<String>();
                this.rolesSeleccionados = new ArrayList<Rol>();
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.usuarios.indexOf(this.usuario);
                this.usuario.setUsuario(usuario.getUsuario().toLowerCase());
                //PICKLIST
                if (!this.rolesAEliminar.isEmpty()) {
                    this.rolesEliminar = this.getListRolByNameString(rolesAEliminar);
                    //CREACION LLAVES COMPUESTAS
                    for (int j = 0; j < rolesEliminar.size(); j++) {
                        this.usuarioRol = new UsuarioRol();
                        this.usuarioRol = this.usuarioRolServicio.findByID(new UsuarioRolPK(usuario.getCodigo(), rolesEliminar.get(j).getCodigo()));
                        this.usuarioRolServicio.eliminar(usuarioRol);
                    }
                }
                if (!this.rolesAIngresar.isEmpty()) {
                    this.rolesIngresar = this.getListRolByNameString(rolesAIngresar);
                    //CREACION LLAVES COMPUESTAS
                    for (int k = 0; k < rolesIngresar.size(); k++) {
                        this.usuarioRol = new UsuarioRol();
                        this.usuarioRol.getPk().setUsuario(this.usuario.getCodigo());
                        this.usuarioRol.getPk().setRol(rolesIngresar.get(k).getCodigo());
                        this.usuarioRol.setUsuario(usuario);
                        this.usuarioRol.setRol(rolesIngresar.get(k));
                        this.usuarioRol.setFecha(new Date());
                        this.usuarioRolServicio.crear(usuarioRol);
                    }
                }
                //ENCERAR
                this.rolesAEliminar = new ArrayList<String>();
                this.rolesAIngresar = new ArrayList<String>();
                this.rolesEliminar = new ArrayList<Rol>();
                this.rolesIngresar = new ArrayList<Rol>();
                this.rolesDestinoString = new ArrayList<String>();
                this.rolesSeleccionados = new ArrayList<Rol>();
                this.usuarioServicio.actualizar(this.usuario);
                usuarios.set(i, this.usuario);
                MensajesGenericos.infoModificar("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.usuario = new Usuario();
        try {
            this.usuario = (Usuario) BeanUtils.cloneBean(this.usuarioSeleccionado);
            this.usuario.setUsrModificacion(usrSesion.getCodigo());
            this.usuario.setFmodificacion(new Date());
            //INICIO DE ARREGLOS
            this.rolesAEliminar = new ArrayList<String>();
            this.rolesAIngresar = new ArrayList<String>();
            this.usuariosRoles = this.usuarioRolServicio.getByUser(usuario);
            if (!usuariosRoles.isEmpty()) {
                this.rolesSeleccionados = new ArrayList<Rol>();
                for (int i = 0; i < usuariosRoles.size(); i++) {
                    this.rolesSeleccionados.add(usuariosRoles.get(i).getRol());
                }
                this.rolesDestinoString = new ArrayList<String>();
                this.rolesDestinoString = this.getListStringRol(rolesSeleccionados);
                //ROLES FUENTE
                this.rolesFuente = new ArrayList<Rol>();
                this.rolesFuenteString = new ArrayList<String>();
                this.rolesFuente = this.rolServicio.obtener();
                this.rolesFuenteString = this.getListStringRol(rolesFuente);

                for (int i = 0; i < rolesFuenteString.size(); i++) {
                    for (int j = 0; j < rolesDestinoString.size(); j++) {
                        if (rolesFuenteString.get(i).equals(rolesDestinoString.get(j))) {
                            rolesFuenteString.remove(rolesDestinoString.get(j));
                        }
                    }
                }
            } else {
                this.rolesFuente = this.rolServicio.obtener();
                this.rolesSeleccionados = new ArrayList<Rol>();
                this.rolesFuenteString = new ArrayList<String>();
                this.rolesFuenteString = this.getListStringRol(rolesFuente);
                this.rolesDestinoString = new ArrayList<String>();
                this.usuariosRoles = new ArrayList<UsuarioRol>();
            }
            this.roles = new DualListModel<String>(rolesFuenteString, rolesDestinoString);


            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        this.usuarioServicio.eliminar(this.usuarioSeleccionado);
        this.usuarios.remove(this.usuarioSeleccionado);
        MensajesGenericos.infoEliminar("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (usuarioSeleccionado instanceof Usuario) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Usuario getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Usuario respaldo) {
        this.respaldo = respaldo;
    }

    public Usuario getUsrSesion() {
        return usrSesion;
    }

    public void setUsrSesion(Usuario usrSesion) {
        this.usrSesion = usrSesion;
    }

    public Usuario getUsrAuditoria() {
        return usrAuditoria;
    }

    public void setUsrAuditoria(Usuario usrAuditoria) {
        this.usrAuditoria = usrAuditoria;
    }

    public List<Rol> getRolesFuente() {
        return rolesFuente;
    }

    public void setRolesFuente(List<Rol> rolesFuente) {
        this.rolesFuente = rolesFuente;
    }

    public List<Rol> getRolesSeleccionados() {
        return rolesSeleccionados;
    }

    public void setRolesSeleccionados(List<Rol> rolesSeleccionados) {
        this.rolesSeleccionados = rolesSeleccionados;
    }

    public DualListModel<String> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<String> roles) {
        this.roles = roles;
    }

    public List<String> getRolesFuenteString() {
        return rolesFuenteString;
    }

    public void setRolesFuenteString(List<String> rolesFuenteString) {
        this.rolesFuenteString = rolesFuenteString;
    }

    public List<String> getRolesDestinoString() {
        return rolesDestinoString;
    }

    public void setRolesDestinoString(List<String> rolesDestinoString) {
        this.rolesDestinoString = rolesDestinoString;
    }

    public List<String> getRolesString() {
        return rolesString;
    }

    public void setRolesString(List<String> rolesString) {
        this.rolesString = rolesString;
    }

    public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public List<UsuarioRol> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(List<UsuarioRol> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    

    public void onTransfer(TransferEvent event) {
        for (Object item : event.getItems()) {
            if (!rolesDestinoString.contains((String) item)) {
                this.rolesDestinoString.add((String) item);
                if (super.getEnEdicion()) {
                    if (!rolesAIngresar.contains((String) item)) {
                        this.rolesAIngresar.add((String) item);
                    }
                }
            } else {
                if (super.getEnEdicion()) {
                    if (!rolesAEliminar.contains((String) item)) {
                        this.rolesAEliminar.add((String) item);
                    }
                }
                this.rolesDestinoString.remove((String) item);
            }
        }
        //COMPROBACION
        for (int i = 0; i < rolesDestinoString.size(); i++) {
            if (rolesDestinoString.get(i) != null) {
            } else {
            }
        }
    }
}
