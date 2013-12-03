/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEmpleado;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.CantonPK;
import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.EntregableDocumento;
import ec.kaymanta.gestproy.modelo.Expectativa;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.Gasto;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Interesado;
import ec.kaymanta.gestproy.modelo.LeccionesAprendidas;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Reunion;
import ec.kaymanta.gestproy.modelo.Riesgo;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.modelo.TipoEntregable;
import ec.kaymanta.gestproy.modelo.TipoGasto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadEmpleadoServicio;
import ec.kaymanta.gestproy.servicio.ActividadEntregableServicio;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.DocumentosProyectoServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.EntregableDocumentoServicio;
import ec.kaymanta.gestproy.servicio.ExpectativaServicio;
import ec.kaymanta.gestproy.servicio.FechasActividadServicio;
import ec.kaymanta.gestproy.servicio.GastoServicio;
import ec.kaymanta.gestproy.servicio.HistorialDocumentoServicio;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
import ec.kaymanta.gestproy.servicio.InteresadoServicio;
import ec.kaymanta.gestproy.servicio.LeccionesAprendidasServicio;
import ec.kaymanta.gestproy.servicio.ParroquiaServicio;
import ec.kaymanta.gestproy.servicio.ProvinciaServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.ReunionServicio;
import ec.kaymanta.gestproy.servicio.RiesgoServicio;
import ec.kaymanta.gestproy.servicio.TipoDocumentoServicio;
import ec.kaymanta.gestproy.servicio.TipoEntregableServicio;
import ec.kaymanta.gestproy.servicio.TipoGastoServicio;
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
    @EJB
    private GastoServicio gastoServicio;
    @EJB
    private TipoGastoServicio tipoGastoServicio;
    @EJB
    private ActividadEntregableServicio actividadEntregableServicio;
    @EJB
    private TipoEntregableServicio tipoEntregableServicio;
    @EJB
    private EntregableDocumentoServicio entregableDocumentoServicio;
    @EJB
    private ReunionServicio reunionServicio;
    @EJB
    private RiesgoServicio riesgoServicio;
    @EJB
    private ExpectativaServicio expectativaServicio;
    @EJB
    private LeccionesAprendidasServicio leccionesAprendidasServicio;
    @EJB
    private InteresadoServicio interesadoServicio;
    @EJB
    private ActividadEmpleadoServicio actividadEmpleadoServicio;
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
    private List<Gasto> gastos;
    private List<Reunion> reuniones;
    private List<Riesgo> riesgos;
    private List<LeccionesAprendidas> leccionesAprendidasList;
    private List<Interesado> interesados;
    private List<Expectativa> expectativas;
    private List<TipoGasto> tiposGasto;
    private List<TipoEntregable> tiposEntregable;
    private List<ActividadEntregable> actividadEntregables;
    private List<ActividadEmpleado> actividadEmpleados;
    private Documento documento;
    private Documento documentoSeleccionado;
    private Documento documentoAnt;
    private HistorialDocumento historialDocumento;
    //Variables de funcionalidad lógica de la página
    private Proyecto proyecto;
    private Proyecto proyectoSeleccionado;
    private Proyecto respaldo;
    private DocumentosProyecto documentosProyecto;
    private EntregableDocumento entregableDocumento;
    //Variables de actividad
    private Actividad actividad;
    private Actividad actividadSeleccionada;
    private Actividad respaldoActividad;
    //Variables SubActividad
    private Actividad subActividad;
    private Actividad subActividadSeleccionada;
    private Actividad respaldoSubActividad;
    //Variables de Gastos
    private Gasto gasto;
    private Gasto gastoSeleccionado;
    //Variables de Reuniones
    private Reunion reunion;
    private Reunion reunionSeleccionado;
    //Variables de Reuniones
    private Riesgo riesgo;
    private Riesgo riesgoSeleccionado;
    //Variables de Expectativa
    private Expectativa expectativa;
    private Expectativa expectativaSeleccionado;
    //Variables de Lecciones Aprendidas
    private LeccionesAprendidas leccionesAprendidas;
    private LeccionesAprendidas leccionesAprendidasSeleccionada;
    //Variables de Lecciones Aprendidas
    private Interesado interesado;
    private Interesado interesadoSeleccionado;
    //Variables de Gastos
    private ActividadEntregable actividadEntregable;
    private ActividadEntregable actividadEntregableSeleccionado;
    //Variables de Actividad Responsable
    private ActividadEmpleado actividadEmpleado;
    private ActividadEmpleado actividadEmpleadoSeleccionado;
    //Variables Fechas Actividad
    private FechasActividad fechasActividad;
    private FechasActividad fechasActividadRespaldo;
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
    private String tipoGasto;
    private String tipoEntregable;
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
        this.tiposGasto = this.tipoGastoServicio.obtener();
        this.tiposEntregable = this.tipoEntregableServicio.obtener();
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

    public void nuevoDoc(ActionEvent evento) {
        super.crearDoc();
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

    public void nuevoGasto(ActionEvent evento) {
        super.crearGasto();
        this.gasto = new Gasto();
    }

    public void nuevaReunion(ActionEvent evento) {
        super.crearReunion();
        this.reunion = new Reunion();
    }

    public void nuevaRiesgo(ActionEvent evento) {
        super.crearRiesgo();
        this.riesgo = new Riesgo();
    }

    public void nuevaExpectativa(ActionEvent evento) {
        super.crearExpectativa();
        this.expectativa = new Expectativa();
    }

    public void nuevaLeccion(ActionEvent evento) {
        super.crearLeccion();
        System.out.println("NUEVO" + super.getEnNuevaLeccion());
        this.leccionesAprendidas = new LeccionesAprendidas();
    }

    public void nuevoInteresado(ActionEvent evento) {
        super.crearInteresado();
        this.interesado = new Interesado();
    }

    public void nuevoResponsable(ActionEvent evento) {
        super.crearResponsable();
        this.actividadEmpleado = new ActividadEmpleado();
    }

    public void nuevaActividadEntregable(ActionEvent evento) {
        super.crearEntregable();
        this.actividadEntregable = new ActividadEntregable();
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

    public void verAuditoriaReunion(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaReunion();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaRiesgo(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaRiesgo();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaExpectativa(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaExpectativa();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaLeccion(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaLeccion();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaInteresado(ActionEvent evento) throws IllegalAccessException {
        try {
            this.interesado = new Interesado();
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            super.verAuditoriaInteresado();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaResponsable(ActionEvent evento) throws IllegalAccessException {
        try {
            this.actividadEmpleado = new ActividadEmpleado();
            this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
            super.verAuditoriaResponsable();
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

    public void verAuditoriaDoc(ActionEvent evento) throws IllegalAccessException {
        try {
            this.documento = new Documento();
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            super.verAuditoriaDoc();
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
            super.verAuditoriaSubActividad();
            this.subActividad = new Actividad();
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaGastos(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaGastos();
            this.gasto = new Gasto();
            this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void verAuditoriaEntregables(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoriaEntregables();
            this.actividadEntregable = new ActividadEntregable();
            this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
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

    public void verDocumentos(ActionEvent evento) {
        try {
            super.verDocumentos();
            this.instControl = new String();
            this.tipoDoc = new String();
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            //this.proyecto = new Proyecto();
            this.documentos = this.documentoServicio.findBySubActividad(actividadEntregable);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD: " + actividad.getNombreActividad());
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

    public void verGastos(ActionEvent evento) {
        try {
            super.verGastos();
            System.out.println("EN LA PANTALLA DE GASTOS");
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD: " + actividad.getNombreActividad() + " SUBACTIVIDAD: " + subActividad.getNombreActividad());
            this.gastos = this.gastoServicio.findBySubActividad(subActividad);
            this.gasto = new Gasto();
            System.out.println("VALOR:" + super.getEnGastos());
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verReuniones(ActionEvent evento) {
        try {
            super.verReuniones();

            System.out.println("EN LA PANTALLA DE REUNIONES");
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            this.reuniones = this.reunionServicio.findByProyecto(proyecto);
            this.reunion = new Reunion();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verRiesgos(ActionEvent evento) {
        try {
            super.verRiesgos();

            System.out.println("EN LA PANTALLA DE REUNIONES");
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            this.riesgos = this.riesgoServicio.findByProyecto(proyecto);
            this.riesgo = new Riesgo();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verExpectativas(ActionEvent evento) {
        try {
            super.verExpectativas();

            System.out.println("EN LA PANTALLA DE EXPECTATIVAS");
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            this.expectativas = this.expectativaServicio.findByProyecto(proyecto);
            this.expectativa = new Expectativa();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verLecciones(ActionEvent evento) {
        try {
            super.verLecciones();

            System.out.println("EN LA PANTALLA DE LECCIONES APRENDIDAS");
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            this.leccionesAprendidasList = this.leccionesAprendidasServicio.findByProyecto(proyecto);
            this.leccionesAprendidas = new LeccionesAprendidas();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verInteresados(ActionEvent evento) {
        try {
            super.verInteresados();

            System.out.println("EN LA PANTALLA DE INTERESADOS");
            this.proyecto = new Proyecto();
            this.proyecto = (Proyecto) BeanUtils.cloneBean(this.proyectoSeleccionado);
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
            this.interesados = this.interesadoServicio.obtenerByProyecto(proyecto.getEmpresa(), proyecto.getCodigo());
            this.interesado = new Interesado();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verResponsables(ActionEvent evento) {
        try {
            super.verResponsables();

            System.out.println("EN LA PANTALLA DE RESPONSABLES");
            this.actividadEmpleados = this.actividadEmpleadoServicio.findBySubActividad(subActividad);
            this.actividadEmpleado = new ActividadEmpleado();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }


    }

    public void verEntregables(ActionEvent evento) {
        try {
            super.verEntregables();
            System.out.println("EN LA PANTALLA DE Entregables");
            System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD: " + actividad.getNombreActividad() + " SUBACTIVIDAD: " + subActividad.getNombreActividad());
            this.actividadEntregables = this.actividadEntregableServicio.findBySubActividad(subActividad);
            this.actividadEntregable = new ActividadEntregable();
            System.out.println("VALOR:" + super.getEnEntregables());
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

    public String getImpacto(String impacto) {
        if (impacto == null || "".equals(impacto)) {
            return "";
        } else {
            System.out.println(impacto);
            if (impacto.equals("A")) {
                return "Alto";
            } else if (impacto.equals("M")) {
                return "Medio";
            } else if (impacto.equals("B")) {
                return "Bajo";
            }
            return "";
        }
    }

    public String getEstado(String estado) {
        if (super.getEnEntregables()) {
            if (estado == null || "".equals(estado)) {
                return "";
            } else {
                System.out.println(estado);
                if (estado.equals("I")) {
                    return "Inicial";
                } else if (estado.equals("P")) {
                    return "Procesado";
                } else if (estado.equals("F")) {
                    return "Finalizado";
                } else if (estado.equals("R")) {
                    return "Revisado";
                }
                return "Desconocido";
            }

        } else if (super.getEnRiesgos()) {
            if (estado == null || "".equals(estado)) {
                return "";
            } else {
                System.out.println(estado);
                if (estado.equals("I")) {
                    return "Pendiente";
                } else if (estado.equals("M")) {
                    return "Mitigado";
                }
                return "Desconocido";
            }
        } else if (super.getEnExpectativas()) {
            if (estado == null || "".equals(estado)) {
                return "";
            } else {
                System.out.println(estado);
                if (estado.equals("P")) {
                    return "Pendiente";
                } else if (estado.equals("C")) {
                    return "Cumplida";
                }
                return "Desconocido";
            }
        }
        return "Desconocido";
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

    public void modificarDoc(ActionEvent evento) {
        this.documento = new Documento();
        try {
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            this.instControl = this.documento.getInstitucionControl().getCodigo().toString();
            this.tipoDoc = this.documento.getTipoDocumento().getCodigo().toString();
        } catch (Exception e) {
        }
        super.modificarDoc();
    }

    public void modificarActividad(ActionEvent evento) {
        this.actividad = new Actividad();
        try {
            this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
        } catch (Exception e) {
        }
        super.modificarActividad();
    }

    public void modificarReunion(ActionEvent evento) {
        this.reunion = new Reunion();
        try {
            this.reunion = (Reunion) BeanUtils.cloneBean(this.reunionSeleccionado);
        } catch (Exception e) {
        }
        super.modificarReunion();
    }

    public void modificarRiesgo(ActionEvent evento) {
        this.riesgo = new Riesgo();
        try {
            this.riesgo = (Riesgo) BeanUtils.cloneBean(this.riesgoSeleccionado);
            System.out.println("RIESGO " + riesgo.getNombre());
        } catch (Exception e) {
        }
        super.modificarRiesgo();
    }

    public void modificarExpectativa(ActionEvent evento) {
        this.expectativa = new Expectativa();
        try {
            this.expectativa = (Expectativa) BeanUtils.cloneBean(this.expectativaSeleccionado);
            System.out.println("EXPECTATIVA " + expectativa.getRequerimiento());
        } catch (Exception e) {
        }
        super.modificarExpectativa();
    }

    public void modificarLeccion(ActionEvent evento) {
        this.leccionesAprendidas = new LeccionesAprendidas();
        try {
            this.leccionesAprendidas = (LeccionesAprendidas) BeanUtils.cloneBean(this.leccionesAprendidasSeleccionada);
            System.out.println("LECCIONES APRENDIDAS " + leccionesAprendidas.getProblema());
            System.out.println("MODIFICACION" + super.getEnEdicionLeccion());

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.modificarLeccion();
    }

    public void modificarInteresado(ActionEvent evento) {
        this.interesado = new Interesado();
        try {
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            //Invariable Objetos de Auditoria            
            this.empresa = this.empresaServicio.findByID(interesado.getCodEmpresa()).getCodigo();
            super.modificarInteresado();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void modificarResponsable(ActionEvent evento) {
        this.actividadEmpleado = new ActividadEmpleado();
        try {
            this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificarResponsable();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void modificarSubActividad(ActionEvent evento) {
        this.subActividad = new Actividad();
        this.fechasActividad = new FechasActividad();
        try {
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
            this.fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividad);
            this.fechasActividadRespaldo = (FechasActividad) BeanUtils.cloneBean(this.fechasActividad);
            if (this.fechasActividad == null) {
                this.fechasActividad = new FechasActividad();
            }
        } catch (Exception e) {
        }
        super.modificarSubActividad();
    }

    public void modificarGasto(ActionEvent evento) {
        this.gasto = new Gasto();
        try {
            this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
            this.tipoGasto = String.valueOf(gasto.getTipoGasto().getCodigo());
        } catch (Exception e) {
        }
        super.modificarGasto();
    }

    public void modificarEntregable(ActionEvent evento) {
        this.actividadEntregable = new ActividadEntregable();
        try {
            this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
            this.instControl = String.valueOf(actividadEntregable.getCodInstitucionControl());
            this.tipoEntregable = String.valueOf(actividadEntregable.getCodTipoEntregable());
        } catch (Exception e) {
        }
        super.modificarEntregable();
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

                this.proyecto.sethDiaEst(0L);
                this.proyecto.sethDiaReal(0L);
                this.proyecto.sethTrabEst(BigDecimal.ZERO);
                this.proyecto.sethTrabReal(BigDecimal.ZERO);
                this.proyecto.settTotalEst(0L);
                this.proyecto.settTotalReal(0L);

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

                this.actividad.sethDiaEst(0L);
                this.actividad.sethDiaReal(0L);
                this.actividad.sethTrabEst(BigDecimal.ZERO);
                this.actividad.sethTrabReal(BigDecimal.ZERO);
                this.actividad.settTotalEst(0L);
                this.actividad.settTotalReal(0L);

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

    public void guardarGasto(ActionEvent evento) {
        try {
            if (super.getEnNuevoGasto()) {
                this.gasto.setActividad(subActividad);
                this.gasto.setTipoGasto(this.tipoGastoServicio.findByID(Long.parseLong(tipoGasto)));
                this.gasto.setCodTipoGasto(Long.parseLong(tipoGasto));
                this.gasto.getPk().setActividad(subActividad.getCodigo());
                this.gasto.getPk().setCodigoGasto(Long.parseLong(String.valueOf(this.gastoServicio.obtener().size())) + 1);
                this.gasto.setUsrCreacion(usrSesion.getCodigo());
                this.gasto.setFcreacion(new Date());
                this.gasto.setValorReal(BigDecimal.ZERO);

                this.gastoServicio.crear(gasto);
                this.gastos.add(gasto);
                MensajesGenericos.infoCrear("Gasto", this.gasto.getPk().toString().concat(" - ").concat(this.gasto.getValorPlan().toString()), Boolean.TRUE);

            } else if (super.getEnEdicionGasto()) {
                int i = this.gastos.indexOf(this.gasto);
                this.gasto.setTipoGasto(this.tipoGastoServicio.findByID(Long.parseLong(tipoGasto)));
                this.gasto.setUsrModificacion(usrSesion.getCodigo());
                this.gasto.setFmodificacion(new Date());
                this.gastoServicio.actualizar(gasto);
                this.gastos.set(i, this.gasto);
                MensajesGenericos.infoModificar("Gasto", this.gasto.getPk().toString().concat(" - ").concat(this.gasto.getValorPlan().toString()), Boolean.TRUE);

            }
            super.sinSeleccionGastos();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarReunion(ActionEvent evento) {
        try {
            if (super.getEnNuevaReunion()) {
                reunion.getPk().setProyecto(proyecto.getCodigo());
                reunion.getPk().setCodigoReunion(Long.parseLong(String.valueOf(this.reunionServicio.obtener().size())) + 1);
                reunion.setProyecto(proyecto);
                reunion.setUsrCreacion(usrSesion.getCodigo());
                reunion.setFcreacion(new Date());
                reunionServicio.crear(reunion);
                this.reuniones.add(reunion);
                MensajesGenericos.infoCrear("Reunion", this.reunion.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicionReunion()) {
                int i = this.reuniones.indexOf(this.reunion);
                reunion.setUsrModificacion(usrSesion.getCodigo());
                reunion.setFmodificacion(new Date());
                this.reuniones.set(i, this.reunion);
                reunionServicio.actualizar(reunion);
                MensajesGenericos.infoModificar("Reunion", this.reunion.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccionReuniones();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarRiesgo(ActionEvent evento) {
        try {
            if (super.getEnNuevaRiesgo()) {
                riesgo.getPk().setProyecto(proyecto.getCodigo());
                riesgo.getPk().setCodigoRiesgo(Long.parseLong(String.valueOf(this.riesgoServicio.obtener().size())) + 1);
                riesgo.setProyecto(proyecto);
                riesgo.setUsrCreacion(usrSesion.getCodigo());
                riesgo.setFcreacion(new Date());
                riesgoServicio.crear(riesgo);
                this.riesgos.add(riesgo);
                MensajesGenericos.infoCrear("Riesgo", this.riesgo.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicionRiesgo()) {
                int i = this.riesgos.indexOf(this.riesgo);
                riesgo.setUsrModificacion(usrSesion.getCodigo());
                riesgo.setFmodificacion(new Date());
                this.riesgos.set(i, this.riesgo);
                riesgoServicio.actualizar(riesgo);
                MensajesGenericos.infoModificar("Riesgo", this.riesgo.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccionRiesgos();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarExpectativa(ActionEvent evento) {
        try {
            if (super.getEnNuevaExpectativa()) {
                expectativa.getPk().setProyecto(proyecto.getCodigo());
                expectativa.getPk().setCodigoExpectativa(Long.parseLong(String.valueOf(this.expectativaServicio.obtener().size())) + 1);
                expectativa.setProyecto(proyecto);
                expectativa.setUsrCreacion(usrSesion.getCodigo());
                expectativa.setFcreacion(new Date());
                expectativaServicio.crear(expectativa);
                this.expectativas.add(expectativa);
                MensajesGenericos.infoCrear("Expectativa", this.expectativa.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicionExpectativa()) {
                int i = this.expectativas.indexOf(this.expectativa);
                expectativa.setUsrModificacion(usrSesion.getCodigo());
                expectativa.setFmodificacion(new Date());
                this.expectativas.set(i, this.expectativa);
                expectativaServicio.actualizar(expectativa);
                MensajesGenericos.infoModificar("Expectativa", this.expectativa.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccionExpectativas();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarLeccion(ActionEvent evento) {
        System.out.println("EDICION" + super.getEnEdicionLeccion());
        System.out.println("NUEVO" + super.getEnNuevaLeccion());
        try {
            if (super.getEnNuevaLeccion()) {
                leccionesAprendidas.getPk().setProyecto(proyecto.getCodigo());
                leccionesAprendidas.getPk().setCodigoLeccionesAprendidas(Long.parseLong(String.valueOf(this.leccionesAprendidasServicio.obtener().size())) + 1);
                leccionesAprendidas.setProyecto(proyecto);
                leccionesAprendidas.setUsrCreacion(usrSesion.getCodigo());
                leccionesAprendidas.setFcreacion(new Date());
                leccionesAprendidasServicio.crear(leccionesAprendidas);
                this.leccionesAprendidasList.add(leccionesAprendidas);
                MensajesGenericos.infoCrear("Lecciones Aprendidas", this.leccionesAprendidas.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicionLeccion()) {
                int i = this.leccionesAprendidasList.indexOf(this.leccionesAprendidas);
                leccionesAprendidas.setUsrModificacion(usrSesion.getCodigo());
                leccionesAprendidas.setFmodificacion(new Date());
                this.leccionesAprendidasList.set(i, this.leccionesAprendidas);
                leccionesAprendidasServicio.actualizar(leccionesAprendidas);
                MensajesGenericos.infoModificar("Lecciones Aprendidas", this.leccionesAprendidas.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccionLecciones();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarInteresado(ActionEvent evento) {
        try {
            if (super.getEnNuevaInteresado()) {
                this.interesado.setUsrCreacion(usrSesion.getCodigo());
                this.interesado.setFcreacion(new Date());
                this.interesado.setProyecto(proyecto.getCodigo());
                this.interesado.setEmpresa(this.proyecto.getEmpresa());
                this.interesado.setCodEmpresa(this.proyecto.getEmpresa().getCodigo());
                this.interesadoServicio.crear(this.interesado);
                this.interesados.add(this.interesado);
                MensajesGenericos.infoCrear("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccionInteresados();
            } else if (super.getEnEdicionInteresado()) {
                int i = this.interesados.indexOf(this.interesado);
                this.interesado.setUsrModificacion(usrSesion.getCodigo());
                this.interesado.setFmodificacion(new Date());
                this.interesadoServicio.actualizar(this.interesado);
                this.interesados.set(i, this.interesado);
                MensajesGenericos.infoModificar("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccionInteresados();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarResponsable(ActionEvent evento) {
        try {
            if (super.getEnNuevaResponsable()) {
                //RESPONSABLES
                this.actividadEmpleado.setActividad(subActividad);
                this.actividadEmpleado.getPk().setActividad(subActividad.getCodigo());
                this.actividadEmpleado.setEmpleado(this.empleadoServicio.findByID(this.actividadEmpleado.getPk().getResponsable()));
                this.actividadEmpleado.setUsrCreacion(usrSesion.getCodigo());
                this.actividadEmpleado.setFcreacion(new Date());
                //MEANWHILE
                this.actividadEmpleado.setAvance(BigDecimal.ZERO);
                this.actividadEmpleado.sethDiaEst(0L);
                this.actividadEmpleado.sethDiaReal(0L);
                this.actividadEmpleado.sethTrabEst(BigDecimal.ZERO);
                this.actividadEmpleado.sethTrabReal(BigDecimal.ZERO);
                this.actividadEmpleado.settTotalEst(0L);
                this.actividadEmpleado.settTotalReal(0L);
                this.actividadEmpleadoServicio.crear(actividadEmpleado);

                this.actividadEmpleados.add(this.actividadEmpleado);
                MensajesGenericos.infoCrear("Responsable", this.actividadEmpleado.getPk().toString(), Boolean.TRUE);
                super.sinSeleccionResponsables();
            } else if (super.getEnEdicionResponsable()) {
                int i = this.actividadEmpleados.indexOf(this.actividadEmpleado);
                this.actividadEmpleado.setUsrModificacion(usrSesion.getCodigo());
                this.actividadEmpleado.setFmodificacion(new Date());
                this.actividadEmpleadoServicio.actualizar(actividadEmpleado);
                this.actividadEmpleados.set(i, this.actividadEmpleado);
                MensajesGenericos.infoModificar("Responsable", this.actividadEmpleado.getPk().toString(), Boolean.TRUE);
                super.sinSeleccionResponsables();
            }
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
                this.subActividad.sethDiaEst(0L);
                this.subActividad.sethDiaReal(0L);
                this.subActividad.sethTrabEst(BigDecimal.ZERO);
                this.subActividad.sethTrabReal(BigDecimal.ZERO);
                this.subActividad.settTotalEst(0L);
                this.subActividad.settTotalReal(0L);
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
                int i = this.subActividades.indexOf(this.subActividad);

                this.subActividad.setUsrModificacion(usrSesion.getCodigo());
                this.subActividad.setFmodificacion(new Date());
                this.actividadServicio.actualizar(subActividad);
                this.subActividades.set(i, this.subActividad);


                System.out.println("SubActividad FI: " + fechasActividad.getFinicio());
                System.out.println("SubActividad FE: " + fechasActividad.getFestimada());
                System.out.println("SubActividad FF: " + fechasActividad.getFfin());
                System.out.println("SubActividad COD: " + fechasActividad);


                System.out.println(" FI: " + fechasActividadRespaldo.getFinicio());
                System.out.println(" FE: " + fechasActividadRespaldo.getFestimada());
                System.out.println(" FF: " + fechasActividadRespaldo.getFfin());
                System.out.println(" COD: " + fechasActividadRespaldo);

                MensajesGenericos.infoModificar("SubActividad", this.subActividad.getCodigo().toString().concat(" - ").concat(this.subActividad.getNombreActividad()), Boolean.TRUE);


                if (fechasActividad.getFinicio().compareTo(fechasActividadRespaldo.getFinicio()) == 0
                        && fechasActividad.getFestimada().compareTo(fechasActividadRespaldo.getFestimada()) == 0) {
                    System.out.println("NO GUARDO FECHAS YA QUE SON LAS MISMOS");
                } else {
                    FechasActividad fechasActividadNueva = new FechasActividad();
                    fechasActividadNueva.getPk().setActividad(subActividad.getCodigo());
                    System.out.println("CLAVE AUTOSUMA: " + Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);

                    fechasActividadNueva.getPk().setCodigoFechasActividad(Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);

                    fechasActividadNueva.setActividad(subActividad);
                    fechasActividadNueva.setFinicio(fechasActividad.getFinicio());
                    fechasActividadNueva.setFestimada(fechasActividad.getFestimada());
                    fechasActividadNueva.setFfin(fechasActividad.getFestimada());
                    fechasActividadNueva.setUsrCreacion(usrSesion.getCodigo());
                    fechasActividadNueva.setFcreacion(new Date());
                    this.fechasActividadServicio.crear(fechasActividadNueva);
                }
            }
            super.sinSeleccionSubActividad();
            //CREAR ACTIVIDAD CON RESPONSABLE
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void guardarEntregables(ActionEvent evento) {
        try {
            if (super.getEnNuevoEntregable()) {
                this.actividadEntregable.setActividad(subActividad);
                this.actividadEntregable.setCodTipoEntregable(Long.parseLong(tipoEntregable));
                this.actividadEntregable.setCodInstitucionControl(Long.parseLong(instControl));
                this.actividadEntregable.setTipoEntregable(this.tipoEntregableServicio.findByID(Long.parseLong(tipoEntregable)));
                this.actividadEntregable.setInstitucionControl(this.institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.actividadEntregable.setEstado("I");
                this.actividadEntregable.getPk().setActividad(subActividad.getCodigo());
                this.actividadEntregable.getPk().setCodigoActividadEntregable(Long.parseLong(String.valueOf(this.actividadEntregableServicio.obtener().size())) + 1);
                this.actividadEntregable.setUsrCreacion(usrSesion.getCodigo());
                this.actividadEntregable.setFcreacion(new Date());
                this.actividadEntregableServicio.crear(actividadEntregable);
                this.actividadEntregables.add(actividadEntregable);

                MensajesGenericos.infoCrear("Entregable", this.actividadEntregable.getPk().toString().concat(" - ").concat(this.actividadEntregable.getNombreEntregable().toString()), Boolean.TRUE);
            } else if (super.getEnEdicionEntregable()) {
                int i = this.actividadEntregables.indexOf(this.actividadEntregable);
                this.actividadEntregable.setCodTipoEntregable(Long.parseLong(tipoEntregable));
                this.actividadEntregable.setCodInstitucionControl(Long.parseLong(instControl));
                this.actividadEntregable.setTipoEntregable(this.tipoEntregableServicio.findByID(Long.parseLong(tipoEntregable)));
                this.actividadEntregable.setInstitucionControl(this.institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.actividadEntregable.setUsrModificacion(usrSesion.getCodigo());
                this.actividadEntregable.setFmodificacion(new Date());
                this.actividadEntregableServicio.actualizar(actividadEntregable);
                this.actividadEntregables.set(i, this.actividadEntregable);
                MensajesGenericos.infoModificar("Entregable", this.actividadEntregable.getPk().toString().concat(" - ").concat(this.actividadEntregable.getNombreEntregable().toString()), Boolean.TRUE);
            }
            super.sinSeleccionEntregables();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.gastoSeleccionado);
        this.gastoServicio.eliminar(this.gastoSeleccionado);
        this.gastos.remove(this.gastoSeleccionado);
        MensajesGenericos.infoEliminar("Empleado", this.gasto.getActividad().getNombreActividad().toString().concat(" - ").concat(this.gasto.getCodTipoGasto().toString()), Boolean.TRUE);
        super.sinSeleccion();
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

    public void guardarDocumento(ActionEvent evento) {
        try {
            if (super.getEnNuevoDoc()) {
                this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
                this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
                this.documento.setUsrCreacion(usrSesion.getCodigo());
                this.documento.setFcreacion(new Date());
                this.documentoServicio.crear(documento);
                this.documentos.add(documento);
                //CREACIÓN TABLA ENTREGABLE DOCUMENTOS 
                this.entregableDocumento = new EntregableDocumento();
                this.entregableDocumento.getPk().setActividad(actividadEntregable.getPk().getActividad());
                this.entregableDocumento.getPk().setEntregable(actividadEntregable.getPk().getCodigoActividadEntregable());
                this.entregableDocumento.getPk().setDocumento(documento.getCodigo());
                this.entregableDocumento.setActividadEntregable(actividadEntregable);
                this.entregableDocumento.setDocumento(documento);
                this.entregableDocumento.setFecha(new Date());
                this.entregableDocumento.setUsrCreacion(usrSesion.getCodigo());
                this.entregableDocumento.setFcreacion(new Date());
                this.entregableDocumentoServicio.crear(entregableDocumento);
                MensajesGenericos.infoCargado("Se ha cargado el archivo " + documento.getNombreDocumento());
            } else if (super.getEnEditarDoc()) {
                System.out.println("EN EDICIÓN DE DOCUMENTO");
                int i = this.documentos.indexOf(this.documento);
                this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
                this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
                this.documento.setUsrModificacion(usrSesion.getCodigo());
                this.documento.setFmodificacion(new Date());
                this.documentoServicio.actualizar(documento);
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
                MensajesGenericos.infoCargado("Se ha cargado el archivo " + documento.getNombreDocumento());
            }
            super.sinSeleccionDocs();
        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
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
                this.documentos.add(documento);
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

    public void filaSeleccionadaDoc(ActionEvent evento) {
        if (documentoSeleccionado instanceof Documento) {
            super.seleccionadoUnoDocs();
            System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + subActividad.getNombreActividad());

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
            try {
                this.subActividad = new Actividad();
                this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + subActividad.getNombreActividad());
            } catch (Exception e) {
                System.out.println("Error en Sub-actividad");
            }
        } else {
            super.sinSeleccion();
            System.out.println("ESTOY ACA Y NO SELECCIONE");
        }
    }

    public void filaSeleccionadaGasto(ActionEvent evento) {
        if (gastoSeleccionado instanceof Gasto) {
            super.seleccionadoUnoGastos();
            try {
                this.gasto = new Gasto();
                this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, EL GASTO ES: " + gasto.getPk());
            } catch (Exception e) {
                System.out.println("Error en Gasto");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaReunion(ActionEvent evento) {
        if (reunionSeleccionado instanceof Reunion) {
            super.seleccionadoUnoReunion();
            try {
                this.reunion = new Reunion();
                this.reunion = (Reunion) BeanUtils.cloneBean(this.reunionSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE LA REUNION");
            } catch (Exception e) {
                System.out.println("Error en Gasto");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaRiesgo(ActionEvent evento) {
        if (riesgoSeleccionado instanceof Riesgo) {
            super.seleccionadoUnoRiesgo();
            try {
                this.riesgo = new Riesgo();
                this.riesgo = (Riesgo) BeanUtils.cloneBean(this.riesgoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE EL RIESGO");
            } catch (Exception e) {
                System.out.println("Error en Riesgo");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaExpectativa(ActionEvent evento) {
        if (expectativaSeleccionado instanceof Expectativa) {
            super.seleccionadoUnoExpectativa();
            try {
                this.expectativa = new Expectativa();
                this.expectativa = (Expectativa) BeanUtils.cloneBean(this.expectativaSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE LA EXPECTATIVA");
            } catch (Exception e) {
                System.out.println("Error en Expectativa");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaLeccion(ActionEvent evento) {
        if (leccionesAprendidasSeleccionada instanceof LeccionesAprendidas) {
            super.seleccionadoUnoLeccion();
            try {
                this.leccionesAprendidas = new LeccionesAprendidas();
                this.leccionesAprendidas = (LeccionesAprendidas) BeanUtils.cloneBean(this.leccionesAprendidasSeleccionada);
                System.out.println("ESTOY AQUI Y SI SELECCIONE LA LECCION APRENDIDA");
            } catch (Exception e) {
                System.out.println("Error en Lección");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaInteresado(ActionEvent evento) {
        if (interesadoSeleccionado instanceof Interesado) {
            super.seleccionadoUnoInteresados();
            try {
                this.interesado = new Interesado();
                this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE EL INTERESADO");
            } catch (Exception e) {
                System.out.println("Error en Interesado");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaResponsable(ActionEvent evento) {
        if (actividadEmpleadoSeleccionado instanceof ActividadEmpleado) {
            super.seleccionadoUnoResponsables();
            try {
                this.actividadEmpleado = new ActividadEmpleado();
                this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE EL RESPONSABLE");
            } catch (Exception e) {
                System.out.println("Error en Responsable");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void filaSeleccionadaEntregable(ActionEvent evento) {
        if (actividadEntregableSeleccionado instanceof ActividadEntregable) {
            super.seleccionadoUnoEntregables();
            try {
                this.actividadEntregable = new ActividadEntregable();
                this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, EL ENTREGABLE ES: " + actividadEntregable.getPk());
            } catch (Exception e) {
                System.out.println("Error en Gasto");
            }
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

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public Gasto getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(Gasto gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public TipoGastoServicio getTipoGastoServicio() {
        return tipoGastoServicio;
    }

    public void setTipoGastoServicio(TipoGastoServicio tipoGastoServicio) {
        this.tipoGastoServicio = tipoGastoServicio;
    }

    public List<TipoGasto> getTiposGasto() {
        return tiposGasto;
    }

    public void setTiposGasto(List<TipoGasto> tiposGasto) {
        this.tiposGasto = tiposGasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public List<ActividadEntregable> getActividadEntregables() {
        return actividadEntregables;
    }

    public void setActividadEntregables(List<ActividadEntregable> actividadEntregables) {
        this.actividadEntregables = actividadEntregables;
    }

    public ActividadEntregable getActividadEntregable() {
        return actividadEntregable;
    }

    public void setActividadEntregable(ActividadEntregable actividadEntregable) {
        this.actividadEntregable = actividadEntregable;
    }

    public ActividadEntregable getActividadEntregableSeleccionado() {
        return actividadEntregableSeleccionado;
    }

    public void setActividadEntregableSeleccionado(ActividadEntregable actividadEntregableSeleccionado) {
        this.actividadEntregableSeleccionado = actividadEntregableSeleccionado;
    }

    public List<TipoEntregable> getTiposEntregable() {
        return tiposEntregable;
    }

    public void setTiposEntregable(List<TipoEntregable> tiposEntregable) {
        this.tiposEntregable = tiposEntregable;
    }

    public String getTipoEntregable() {
        return tipoEntregable;
    }

    public void setTipoEntregable(String tipoEntregable) {
        this.tipoEntregable = tipoEntregable;
    }

    public ReunionServicio getReunionServicio() {
        return reunionServicio;
    }

    public void setReunionServicio(ReunionServicio reunionServicio) {
        this.reunionServicio = reunionServicio;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public Reunion getReunionSeleccionado() {
        return reunionSeleccionado;
    }

    public void setReunionSeleccionado(Reunion reunionSeleccionado) {
        this.reunionSeleccionado = reunionSeleccionado;
    }

    public List<Reunion> getReuniones() {
        return reuniones;
    }

    public void setReuniones(List<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    public RiesgoServicio getRiesgoServicio() {
        return riesgoServicio;
    }

    public void setRiesgoServicio(RiesgoServicio riesgoServicio) {
        this.riesgoServicio = riesgoServicio;
    }

    public List<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Riesgo getRiesgoSeleccionado() {
        return riesgoSeleccionado;
    }

    public void setRiesgoSeleccionado(Riesgo riesgoSeleccionado) {
        this.riesgoSeleccionado = riesgoSeleccionado;
    }

    public List<Expectativa> getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(List<Expectativa> expectativas) {
        this.expectativas = expectativas;
    }

    public Expectativa getExpectativa() {
        return expectativa;
    }

    public void setExpectativa(Expectativa expectativa) {
        this.expectativa = expectativa;
    }

    public Expectativa getExpectativaSeleccionado() {
        return expectativaSeleccionado;
    }

    public void setExpectativaSeleccionado(Expectativa expectativaSeleccionado) {
        this.expectativaSeleccionado = expectativaSeleccionado;
    }

    public List<LeccionesAprendidas> getLeccionesAprendidasList() {
        return leccionesAprendidasList;
    }

    public void setLeccionesAprendidasList(List<LeccionesAprendidas> leccionesAprendidasList) {
        this.leccionesAprendidasList = leccionesAprendidasList;
    }

    public LeccionesAprendidas getLeccionesAprendidas() {
        return leccionesAprendidas;
    }

    public void setLeccionesAprendidas(LeccionesAprendidas leccionesAprendidas) {
        this.leccionesAprendidas = leccionesAprendidas;
    }

    public LeccionesAprendidas getLeccionesAprendidasSeleccionada() {
        return leccionesAprendidasSeleccionada;
    }

    public void setLeccionesAprendidasSeleccionada(LeccionesAprendidas leccionesAprendidasSeleccionada) {
        this.leccionesAprendidasSeleccionada = leccionesAprendidasSeleccionada;
    }

    public List<Interesado> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<Interesado> interesados) {
        this.interesados = interesados;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Interesado getInteresadoSeleccionado() {
        return interesadoSeleccionado;
    }

    public void setInteresadoSeleccionado(Interesado interesadoSeleccionado) {
        this.interesadoSeleccionado = interesadoSeleccionado;
    }

    public List<ActividadEmpleado> getActividadEmpleados() {
        return actividadEmpleados;
    }

    public void setActividadEmpleados(List<ActividadEmpleado> actividadEmpleados) {
        this.actividadEmpleados = actividadEmpleados;
    }

    public ActividadEmpleado getActividadEmpleado() {
        return actividadEmpleado;
    }

    public void setActividadEmpleado(ActividadEmpleado actividadEmpleado) {
        this.actividadEmpleado = actividadEmpleado;
    }

    public ActividadEmpleado getActividadEmpleadoSeleccionado() {
        return actividadEmpleadoSeleccionado;
    }

    public void setActividadEmpleadoSeleccionado(ActividadEmpleado actividadEmpleadoSeleccionado) {
        this.actividadEmpleadoSeleccionado = actividadEmpleadoSeleccionado;
    }
}
