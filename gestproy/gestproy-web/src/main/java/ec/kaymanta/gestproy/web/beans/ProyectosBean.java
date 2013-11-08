/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.CantonPK;
import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.DocumentosProyectoPK;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.DocumentosProyectoServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
import ec.kaymanta.gestproy.servicio.ParroquiaServicio;
import ec.kaymanta.gestproy.servicio.ProvinciaServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.TipoDocumentoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class ProyectosBean extends BotonesBeanProyecto implements Serializable {

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
    @EJB
    private DocumentoServicio documentoServicio;
    @EJB
    private InstitucionControlServicio institucionControlServicio;
    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;
    @EJB
    private DocumentosProyectoServicio documentosProyectoServicio;
    //Listas
    private List<Empresa> empresas;
    private List<Proyecto> proyectos;
    private List<Empleado> empleados;
    private List<Parroquia> parroquiasB;
    private List<Provincia> provinciasB;
    private List<Canton> cantonesB;
    private List<InstitucionControl> institucionesControl;
    private List<TipoDocumento> tipoDocumento;
    private List<Documento> documentos;
    private Documento documento;
    private Documento documentoSeleccionado;
    //Variables de funcionalidad lógica de la página
    private Proyecto proyecto;
    private Proyecto proyectoSeleccionado;
    private Proyecto respaldo;
    private DocumentosProyecto documentosProyecto;
    //Variables de auditoría
    private Usuario usrSesion;
    private Usuario usrAuditoria;
    private String codigoUsuario;
    private UploadedFile file;
    //Codigos de Llaves foráneas
    private String empresa;
    private String empleado;
    private Parroquia parroquia;
    private String provincia;
    private String canton;
    private String codParroquia;
    //Documentos Llaves Foraneas
    private String instControl;
    private String tipoDoc;
    //Flags

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

        this.institucionesControl = this.institucionControlServicio.obtener();
        this.tipoDocumento = this.tipoDocumentoServicio.obtener();

        this.documento = new Documento();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.proyecto = new Proyecto();
    }

    public void nuevoDocumento(ActionEvent evento) {
        super.crearDocumento();
        this.documento = new Documento();
    }

    public void actualizaCantonesB(ActionEvent evento) {
        this.cantonesB = this.cantonServicio.obtenerPorProvincia(this.provincia);
    }

    public void actualizaParroquiasB(ActionEvent evento) {
        this.parroquiasB = this.parroquiaServicio.obtenerPorProvinciaCanton(this.provincia, this.canton);
    }

    public void cargarTabla(ActionEvent evento) {
        this.parroquiasB = this.parroquiaServicio.obtenerPorProvinciaCanton(this.provincia, this.canton);
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro() || super.getEnEdicion() || super.getEnCargaDocumentos()) {
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

    public void verAuditoriaDocumento(ActionEvent evento) throws IllegalAccessException {
        try {
            this.documento = new Documento();
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            super.verAuditoriaDocumento();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void cargaDeDocumentos(ActionEvent evento) {
        try {
            super.verCargaDocumentos();
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            this.documentos = this.documentoServicio.findByProyecto(proyecto);
            
            this.documento = new Documento();
            System.out.println("CARGA DE DOCUMENTOS");
        } catch (Exception e) {
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

    public void modificar(ActionEvent evento) {
        this.proyecto = new Proyecto();


        try {
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            this.proyecto.setUsrModificacion(usrSesion.getCodigo());
            this.proyecto.setFmodificacion(new Date());
            this.provincia = this.proyecto.getCodProvincia().toString();
            this.canton = this.proyecto.getCodCanton().toString();
            this.codParroquia = this.proyecto.getCodParroquia().toString();
            /*CARGA DE LISTAS*/
            this.provinciasB = this.provinciaServicio.obtener();
            this.cantonesB = this.cantonServicio.obtenerPorProvincia(this.provincia);
            this.parroquiasB = this.parroquiaServicio.obtenerPorProvinciaCanton(this.provincia, this.canton);
            /*FINAL DE CARGA DE LISTAS*/
            this.empleado = this.proyecto.getResponsable().toString();
            this.empresa = this.proyecto.getCodEmpresa().toString();

        } catch (Exception e) {
        }
        super.modificar();
    }

    public void modificarDocumento(ActionEvent evento) {
        this.documento = new Documento();
        try {
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            this.documento.setUsrModificacion(usrSesion.getCodigo());
            this.documento.setFmodificacion(new Date());
            this.instControl = this.documento.getInstitucionControl().getCodigo().toString();
            this.tipoDoc = this.documento.getTipoDocumento().getCodigo().toString();
        } catch (Exception e) {
        }
        super.modificarDocumento();
    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.proyecto.setUsrCreacion(usrSesion.getCodigo());
                this.proyecto.setFcreacion(new Date());
                this.proyecto.setEmpresa(this.empresaServicio.findByID(empresa));
                this.proyecto.setEmpleado(this.empleadoServicio.findByID(empleado));
                CantonPK cantonPK = new CantonPK(Long.parseLong(this.canton), Long.parseLong(this.provincia));
                Canton cantonTmp = this.cantonServicio.obtenerPorId(cantonPK);
                ParroquiaPK parroquiaPK = new ParroquiaPK(Long.parseLong(this.codParroquia), Long.parseLong(this.canton), Long.parseLong(this.provincia));
                this.parroquia = this.parroquiaServicio.obtenerPorId(parroquiaPK);
                this.proyecto.setCodCanton(Long.parseLong(canton));
                this.proyecto.setCodProvincia(Long.parseLong(provincia));
                this.proyecto.setCodParroquia(Long.parseLong(codParroquia));
                this.proyecto.setCodEmpresa(empresa);
                this.proyecto.setParroquia(parroquia);
                this.proyecto.setAvance(BigDecimal.ZERO);
                this.proyecto.setResponsable(empleado);

                this.proyecto.setUsrCreacion(usrSesion.getCodigo());
                this.proyecto.setFcreacion(new Date());

                this.proyectoServicio.crear(this.proyecto);
                this.proyectos.add(this.proyecto);

                this.provincia = new String();
                this.canton = new String();
                this.codParroquia = new String();
                this.empleado = new String();
                this.empresa = new String();
                this.parroquia = new Parroquia();


                MensajesGenericos.infoCrear("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.proyectos.indexOf(this.proyecto);

                this.proyecto.setEmpresa(this.empresaServicio.findByID(empresa));
                this.proyecto.setEmpleado(this.empleadoServicio.findByID(empleado));
                CantonPK cantonPK = new CantonPK(Long.parseLong(this.canton), Long.parseLong(this.provincia));
                Canton cantonTmp = this.cantonServicio.obtenerPorId(cantonPK);
                ParroquiaPK parroquiaPK = new ParroquiaPK(Long.parseLong(this.codParroquia), Long.parseLong(this.canton), Long.parseLong(this.provincia));
                this.parroquia = this.parroquiaServicio.obtenerPorId(parroquiaPK);
                this.proyecto.setCodCanton(Long.parseLong(canton));
                this.proyecto.setCodProvincia(Long.parseLong(provincia));
                this.proyecto.setCodParroquia(Long.parseLong(codParroquia));
                this.proyecto.setCodEmpresa(empresa);
                this.proyecto.setParroquia(parroquia);
                this.proyecto.setAvance(BigDecimal.ZERO);
                this.proyecto.setResponsable(empleado);
                this.proyecto.setUsrModificacion(usrSesion.getCodigo());
                this.proyecto.setFmodificacion(new Date());
                this.proyectoServicio.actualizar(this.proyecto);
                proyectos.set(i, this.proyecto);
                MensajesGenericos.infoModificar("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
                super.sinSeleccion();

                this.provincia = new String();
                this.canton = new String();
                this.codParroquia = new String();
                this.empleado = new String();
                this.empresa = new String();
                this.parroquia = new Parroquia();

            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void subirDocumento(FileUploadEvent event) {
        this.documento = new Documento();
        System.out.println("Inicar carga: ");
        String resultado = "";
        try {
            String name = event.getFile().getFileName();
            this.documento.setDocumento(ArrayUtils.toObject(IOUtils.toByteArray(event.getFile().getInputstream())));
            //this.documento.setDocumento(IOUtils);
            this.documento.setNombreDocumento(name);
            /////////////
            super.setDisableGuardarDocumento(Boolean.FALSE);
            System.out.println(super.getDisableGuardarDocumento());
            FacesMessage msg = new FacesMessage(name + " documento cargado. ");
            System.out.println("Carga: " + msg.getSummary() + " - " + event.getComponent().getClientId());
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg1 = new FacesMessage("Error", event.getFile().getFileName() + " no se cargo. " + resultado);

        }
    }

    public void cargarDocumento(ActionEvent evento) {
        ////////
        try {
            this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
            this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
            this.documento.setCodInstitucionControl(Long.parseLong(instControl));
            this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
            this.documento.setUsrCreacion(usrSesion.getCodigo());
            this.documento.setFcreacion(new Date());
            this.documentoServicio.crear(documento);

            //CREACIÖN TABLA DOCUMENTOS PROYECTO
            System.out.println("EL PROYECTO ES: " + proyecto.getNombreProyecto() + " Y EK DOCUMENTO ES: " + documento.getNombreDocumento());
            
            System.out.println("EL PROYECTO ES: " + proyecto.getCodigo()+ " Y EK DOCUMENTO ES: " + documento.getCodigo());
            this.documentosProyecto=new DocumentosProyecto();
            this.documentosProyecto.getPk().setDocumento(documento.getCodigo()); 
            this.documentosProyecto.getPk().setProyecto(proyecto.getCodigo()); 
            this.documentosProyecto.setFecha(new Date());
            this.documentosProyecto.setDocumento(documento);
            this.documentosProyecto.setProyecto(proyecto);
            this.documentosProyecto.setFcreacion(new Date());
            this.documentosProyecto.setUsrCreacion(usrSesion.getCodigo());
            this.documentosProyectoServicio.crear(documentosProyecto);

            //_____CREACIÓN DE DOCUMENTOS
            MensajesGenericos.infoCargado("Se ha cargado el archivo " + documento.getNombreDocumento());
            super.sinSeleccion();
            this.instControl = new String();
            this.tipoDoc = new String();
        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void filaSeleccionada(ActionEvent evento) {
        if (proyectoSeleccionado instanceof Proyecto) {
            super.seleccionadoUno();
            System.out.println(super.getDisableAuditoria() + "   " + super.getDisabledModificar());

        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaDocumento(ActionEvent evento) {
        if (documentoSeleccionado instanceof Documento) {
            super.seleccionadoUnoDocumentos();
            System.out.println("ESTOY AQUI Y SI SELECCIONE");
            System.out.println(super.getDisableAuditoriaDocumento() + "   " + super.getDisableEdicionDocumento());
        } else {
            super.sinSeleccion();
            System.out.println("ESTOY ACA Y NO SELECCIONE");
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

    public List<Parroquia> getParroquiasB() {
        return parroquiasB;
    }

    public void setParroquiasB(List<Parroquia> parroquiasB) {
        this.parroquiasB = parroquiasB;
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

    public String getCodParroquia() {
        return codParroquia;
    }

    public void setCodParroquia(String codParroquia) {
        this.codParroquia = codParroquia;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<InstitucionControl> getInstitucionesControl() {
        return institucionesControl;
    }

    public void setInstitucionesControl(List<InstitucionControl> institucionesControl) {
        this.institucionesControl = institucionesControl;
    }

    public List<TipoDocumento> getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(List<TipoDocumento> tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getInstControl() {
        return instControl;
    }

    public void setInstControl(String instControl) {
        this.instControl = instControl;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Documento getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(Documento documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }
}
