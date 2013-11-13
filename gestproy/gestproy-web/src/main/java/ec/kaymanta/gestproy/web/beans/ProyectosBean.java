/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEmpleado;
import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.CantonPK;
import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.DocumentosProyectoServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.FechasActividadServicio;
import ec.kaymanta.gestproy.servicio.HistorialDocumentoServicio;
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
    @EJB
    private HistorialDocumentoServicio historialDocumentoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private FechasActividadServicio fechasActividadServicio;
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
    private List<Actividad> actividades;
    private List<Actividad> subActividades;
    private Documento documento;
    private Documento documentoSeleccionado;
    private Documento documentoAnt;
    private HistorialDocumento historialDocumento;
    //Variables de funcionalidad lógica de la página
    private Proyecto proyecto;
    private Proyecto proyectoSeleccionado;
    private Proyecto respaldo;
    private DocumentosProyecto documentosProyecto;
    //Variables de actividad
    private Actividad actividad;
    private Actividad actividadSeleccionada;
    private Actividad respaldoActividad;
    //Variables SubActividad
    private Actividad subActividad;
    private Actividad subActividadSeleccionada;
    private Actividad respaldoSubActividad;
    //Variables de Actividad Responsable
    private ActividadEmpleado actividadEmpleado;
    //Variables Fechas Actividad
    private FechasActividad fechasActividad;
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
        this.fechasActividad = new FechasActividad();
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

    public void nuevoActividad(ActionEvent evento) {
        super.crearActividad();
        this.actividad = new Actividad();
    }

    public void nuevoSubActividad(ActionEvent evento) {
        super.crearSubActividad();
        this.subActividad = new Actividad();
        this.fechasActividad = new FechasActividad();
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

    public void verAuditoriaActividad(ActionEvent evento) throws IllegalAccessException {
        try {
            this.actividad = new Actividad();
            this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
            super.verAuditoriaActividad();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaSubActividad(ActionEvent evento) throws IllegalAccessException {
        try {
            this.subActividad = new Actividad();
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
            super.verAuditoriaActividad();
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

    public void verActividades(ActionEvent evento) {
        try {
            super.verActividades();
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            this.actividades = this.actividadServicio.findByProyecto(proyecto);
            this.actividad = new Actividad();
            this.empleado = new String();
            System.out.println("EN LA PANTALLA DE ACTIVIDADES");
            System.out.println("VALOR:" + super.getEnActividades());
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verSubActividades(ActionEvent evento) {
        try {
            super.verSubActividades();
            System.out.println("EN LA PANTALLA DE SUBACTIVIDADES");
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD: " + actividad.getNombreActividad());
            this.subActividades = this.actividadServicio.findByProyectoAndActividad(proyecto, actividad);
            this.subActividad = new Actividad();
            this.empleado = new String();
            System.out.println("VALOR:" + super.getEnSubActividades());
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
            this.instControl = this.documento.getInstitucionControl().getCodigo().toString();
            this.tipoDoc = this.documento.getTipoDocumento().getCodigo().toString();
        } catch (Exception e) {
        }
        super.modificarDocumento();
    }

    public void modificarActividad(ActionEvent evento) {
        this.actividad = new Actividad();
        try {
            this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
        } catch (Exception e) {
        }
        super.modificarActividad();
    }

    public void modificarSubActividad(ActionEvent evento) {
        this.subActividad = new Actividad();
        try {
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
        } catch (Exception e) {
        }
        super.modificarSubActividad();
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

    public void guardarActividad(ActionEvent evento) {
        try {
            if (super.getEnNuevaActividad()) {
                //CREAR ACTIVIDAD
                this.actividad.setProyecto(proyecto);
                this.actividad.setCodProyecto(proyecto.getCodigo());
                this.actividad.setUsrCreacion(usrSesion.getCodigo());
                this.actividad.setFcreacion(new Date());
                this.actividad.setAvance(BigDecimal.ZERO);
                System.out.println("Actividad: " + actividad.getNombreActividad());
                this.actividadServicio.crear(actividad);
                //--CREAR ACTIVIDAD
                this.actividades.add(this.actividad);
                MensajesGenericos.infoCrear("Actividad", this.actividad.getCodigo().toString().concat(" - ").concat(this.actividad.getNombreActividad()), Boolean.TRUE);
            } else if (super.getEnEdicionActividad()) {
                int i = this.actividades.indexOf(this.actividad);
                this.actividad.setUsrModificacion(usrSesion.getCodigo());
                this.actividad.setFmodificacion(new Date());
                this.actividadServicio.actualizar(actividad);
                System.out.println("Actividad: " + actividad.getNombreActividad());
                this.actividadServicio.actualizar(actividad);
                this.actividades.set(i, this.actividad);
                MensajesGenericos.infoModificar("Actividad", this.actividad.getCodigo().toString().concat(" - ").concat(this.actividad.getNombreActividad()), Boolean.TRUE);
            }
            super.sinSeleccionActividad();
            //CREAR ACTIVIDAD CON RESPONSABLE
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarSubActividad(ActionEvent evento) {
        try {
            if (super.getEnNuevaSubActividad()) {
                //CREAR SUBACTIVIDAD
                this.subActividad.setActividad(actividad);
                this.subActividad.setProyecto(proyecto);
                this.subActividad.setCodProyecto(proyecto.getCodigo());
                this.subActividad.setSubactividad(actividad.getCodigo());
                this.subActividad.setAvance(BigDecimal.ZERO);
                this.subActividad.setUsrCreacion(usrSesion.getCodigo());
                this.subActividad.setFcreacion(new Date());
                System.out.println("SubActividad: " + subActividad.getNombreActividad());
                this.actividadServicio.crear(subActividad);
                this.subActividades.add(this.subActividad);
                //CREAR  FECHAS ACTIVIDAD
                this.fechasActividad.setActividad(subActividad);
                this.fechasActividad.getPk().setActividad(subActividad.getCodigo());
                this.fechasActividad.getPk().setCodigoFechasActividad(Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);
                this.fechasActividad.setUsrCreacion(usrSesion.getCodigo());
                this.fechasActividad.setFcreacion(new Date());
                System.out.println("FechasActividad: " + fechasActividad.getActividad().getNombreActividad());
                this.fechasActividadServicio.crear(fechasActividad);
                MensajesGenericos.infoCrear("Sub-Actividad", this.subActividad.getCodigo().toString().concat(" - ").concat(this.subActividad.getNombreActividad()), Boolean.TRUE);
            } else if (super.getEnEdicionSubActividad()) {
                System.out.println("SubActividad: " + subActividad);
                
                
                this.subActividad.setUsrModificacion(usrSesion.getCodigo());
                this.subActividad.setFmodificacion(new Date());
                System.out.println("SubActividad FI: " + fechasActividad.getFinicio());
                System.out.println("SubActividad FE: " + fechasActividad.getFestimada());
                System.out.println("SubActividad FF: " + fechasActividad.getFfin());
                System.out.println("SubActividad COD: " + fechasActividad);
            }
            super.sinSeleccionSubActividad();
            //CREAR ACTIVIDAD CON RESPONSABLE
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

    public void subirDocumentoModificado(FileUploadEvent event) {
        System.out.println("Inicar carga de documento modificado: ");
        //GUARDAR DOCUMENTO EN SESIÓN
        //DOCUMENTO ANTERIOR
        documentoAnt = new Documento();
        String resultado = "";
        try {
            documentoAnt = (Documento) BeanUtils.cloneBean(this.documento);
            String name = event.getFile().getFileName();
            this.documento.setDocumento(ArrayUtils.toObject(IOUtils.toByteArray(event.getFile().getInputstream())));
            //this.documento.setDocumento(IOUtils);
            this.documento.setNombreDocumento(name);
            /////////////
            System.out.println("Codigo documento en subir documento modificado " + documento.getCodigo());
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
        try {
            if (super.getEnNuevoDocumento()) {
                this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
                this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
                this.documento.setUsrCreacion(usrSesion.getCodigo());
                this.documento.setFcreacion(new Date());
                this.documentoServicio.crear(documento);
                //CREACIÖN TABLA DOCUMENTOS PROYECTO
                System.out.println("EL PROYECTO ES: " + proyecto.getNombreProyecto() + " Y EK DOCUMENTO ES: " + documento.getNombreDocumento());
                System.out.println("EL PROYECTO ES: " + proyecto.getCodigo() + " Y EK DOCUMENTO ES: " + documento.getCodigo());
                this.documentosProyecto = new DocumentosProyecto();
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
            } else if (super.getEnEdicionDocumento()) {
                System.out.println("EN EDICIÓN DE DOCUMENTO");
                int i = this.documentos.indexOf(this.documento);
                this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
                this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
                this.documento.setUsrModificacion(usrSesion.getCodigo());
                this.documento.setFmodificacion(new Date());
                System.out.println("Código documento en guardar la actualizacion documento modificado " + documento.getCodigo());
                this.documentoServicio.actualizar(documento);
                System.out.println("El valor de I es:" + documentos.get(i).getNombreDocumento());
                System.out.println("EL PROYECTO ES: " + proyecto.getNombreProyecto() + " Y EK DOCUMENTO ES: " + documento.getNombreDocumento());
                System.out.println("EL PROYECTO ES: " + proyecto.getCodigo() + " Y EK DOCUMENTO ES: " + documento.getCodigo());

                //CREACION DE HISTORIAL DE DOCUMENTO
                this.historialDocumento = new HistorialDocumento();
                this.historialDocumento.getPk().setDocumento(documentoAnt.getCodigo());
                this.historialDocumento.getPk().setCodigoHistorialDocumento(Long.parseLong(String.valueOf(historialDocumentoServicio.obtener().size())) + 1);
                this.historialDocumento.setDocumento(documentoAnt);
                this.historialDocumento.setNombre(documentoAnt.getNombreDocumento());
                this.historialDocumento.setRespaldoDocumento(documentoAnt.getDocumento());
                this.historialDocumento.setUsrCreacion(usrSesion.getCodigo());
                this.historialDocumento.setFcreacion(new Date());
                System.out.println("Codigo documento en guardar la actualizacion documento modificado " + historialDocumento.getPk().getCodigoHistorialDocumento() + "," + historialDocumento.getPk().getDocumento());
                this.historialDocumentoServicio.crear(historialDocumento);
                documentos.set(i, this.documento);
                ////CREACION DE HISTORIAL DE DOCUMENTO
                MensajesGenericos.infoModificar("Proyecto", this.proyecto.getCodigo().toString().concat(" - ").concat(this.proyecto.getNombreProyecto()), Boolean.TRUE);
                super.sinSeleccion();
                MensajesGenericos.infoModificar("Documento", this.documento.getCodigo().toString().concat(" - ").concat(this.documento.getNombreDocumento()), Boolean.TRUE);
            }
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

    public void filaSeleccionadaActividad(ActionEvent evento) {
        if (actividadSeleccionada instanceof Actividad) {
            super.seleccionadoUnoActividades();
            try {
                this.actividad = new Actividad();
                this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + actividad.getNombreActividad());

            } catch (Exception e) {
                System.out.println("Error en Actividad");
            }
        } else {
            super.sinSeleccion();
            System.out.println("ESTOY ACA Y NO SELECCIONE");
        }
    }

    public void filaSeleccionadaSubActividad(ActionEvent evento) {
        if (subActividadSeleccionada instanceof Actividad) {
            super.seleccionadoUnoSubActividades();

            System.out.println("ESTOY AQUI Y SI SELECCIONE");
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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public Actividad getRespaldoActividad() {
        return respaldoActividad;
    }

    public void setRespaldoActividad(Actividad respaldoActividad) {
        this.respaldoActividad = respaldoActividad;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public Actividad getSubActividad() {
        return subActividad;
    }

    public void setSubActividad(Actividad subActividad) {
        this.subActividad = subActividad;
    }

    public Actividad getSubActividadSeleccionada() {
        return subActividadSeleccionada;
    }

    public void setSubActividadSeleccionada(Actividad subActividadSeleccionada) {
        this.subActividadSeleccionada = subActividadSeleccionada;
    }

    public Actividad getRespaldoSubActividad() {
        return respaldoSubActividad;
    }

    public void setRespaldoSubActividad(Actividad respaldoSubActividad) {
        this.respaldoSubActividad = respaldoSubActividad;
    }

    public List<Actividad> getSubActividades() {
        return subActividades;
    }

    public void setSubActividades(List<Actividad> subActividades) {
        this.subActividades = subActividades;
    }

    public FechasActividad getFechasActividad() {
        return fechasActividad;
    }

    public void setFechasActividad(FechasActividad fechasActividad) {
        this.fechasActividad = fechasActividad;
    }
}
