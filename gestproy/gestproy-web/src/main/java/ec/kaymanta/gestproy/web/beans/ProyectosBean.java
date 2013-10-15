/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.CantonPK;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.ParroquiaServicio;
import ec.kaymanta.gestproy.servicio.ProvinciaServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class ProyectosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of ProyectosBean
     */
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private EmpresaServicio empresaServicio;
    @EJB
    private ParroquiaServicio parroquiaServicio;
    @EJB
    private CantonServicio cantonServicio;
    @EJB
    private ProvinciaServicio provinciaServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    //Listas
    private List<Empresa> empresas;
    private List<Proyecto> proyectos;
    private List<Empleado> empleados;
    private List<Parroquia> parroquias;
    private List<Provincia> provinciasB;
    private List<Canton> cantonesB;
    //Variables de funcionalidad lógica de la página
    private Proyecto proyecto;
    private Proyecto proyectoSeleccionado;
    private Proyecto respaldo;
    //Variables de auditoría
    private Usuario usrSesion;
    private Usuario usrAuditoria;
    private String codigoUsuario;
    //Codigos de Llaves foráneas
    private String empresa;
    private String empleado;
    private Parroquia parroquia;
    private String provincia;
    private String canton;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.proyectos = this.proyectoServicio.obtener();
        this.empresas = this.empresaServicio.obtener();
        this.empleados = this.empleadoServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.provinciasB = this.provinciaServicio.obtener();
        System.out.println(provinciasB.size());
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.proyecto = new Proyecto();
    }

    public void actualizaCantonesB(ActionEvent evento) {
        this.cantonesB = this.cantonServicio.obtenerPorProvincia(this.provincia);
    }

    public void cargarTabla(ActionEvent evento) {
        this.parroquias = this.parroquiaServicio.obtenerPorProvinciaCanton(this.provincia, this.canton);
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.proyecto = new Proyecto();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.proyecto = new Proyecto();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
            System.out.println(usr);
            System.out.println(usuarioServicio.findByID(usr));
            try {
                usuarioServicio.findByID(usr);
                return usuarioServicio.findByID(usr).getUsuario();
            } catch (NullPointerException e) {
                return "";
            }
        }
    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.proyecto.setUsrCreacion(usrSesion.getCodigo());
                this.proyecto.setFcreacion(new Date());
                this.proyecto.setEmpresa(this.empresaServicio.findByID(empresa));
                this.proyecto.setEmpleado(this.empleadoServicio.findByID(empleado));

                /*Codigo, Sometido a revisión para selección simple de parroquia*/

                CantonPK cantonPK = new CantonPK(Long.parseLong(this.canton), Long.parseLong(this.provincia));
                System.out.println(cantonPK.getCodigoCanton() + "  " + cantonPK.getProvincia());
                Canton cantonTmp = this.cantonServicio.obtenerPorId(cantonPK);
                System.out.println(cantonTmp.toString());

                this.parroquia = new Parroquia();
                ParroquiaPK parroquiaPK = new ParroquiaPK();
//                parroquiaPK.setCanton(Long.parseLong(this.canton));
//                parroquiaPK.setProvincia(Long.parseLong(this.provincia));
                System.out.println(parroquia.getPk().getCodigoParroquia() +" "+ parroquia.getPk().getCanton() +" "+ parroquia.getPk().getProvincia());
//                this.parroquia.setPk(parroquiaPK);
//                this.parroquia.setCanton(cantonTmp);
                                
                /*Final del código de prueba*/
                //this.proyecto.setParroquia(this.parroquiaServicio.);

                this.proyecto.setParroquia(parroquia);
                
                
                //this.proyectoServicio.crear(this.proyecto);
                this.proyectos.add(this.proyecto);
                MensajesGenericos.infoCrear("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.empresas.indexOf(this.proyecto);
                this.proyectoServicio.actualizar(this.proyecto);
                proyectos.set(i, this.proyecto);
                MensajesGenericos.infoModificar("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.proyectoSeleccionado);
        this.proyectoServicio.eliminar(this.proyectoSeleccionado);
        //this.empresaServicio.actualizar(empresaSeleccionado);
        this.proyectos.remove(this.proyectoSeleccionado);
        MensajesGenericos.infoEliminar("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (proyectoSeleccionado instanceof Proyecto) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    //Getters And Setters
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Parroquia> getParroquias() {
        return parroquias;
    }

    public void setParroquias(List<Parroquia> parroquias) {
        this.parroquias = parroquias;
    }

    public List<Provincia> getProvinciasB() {
        return provinciasB;
    }

    public void setProvinciasB(List<Provincia> provinciasB) {
        this.provinciasB = provinciasB;
    }

    public List<Canton> getCantonesB() {
        return cantonesB;
    }

    public void setCantonesB(List<Canton> cantonesB) {
        this.cantonesB = cantonesB;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public Proyecto getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Proyecto respaldo) {
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

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Parroquia getParroquia() {
        return parroquia;
    }

    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }
    
}
