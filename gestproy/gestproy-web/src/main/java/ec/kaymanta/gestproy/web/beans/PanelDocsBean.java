/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.EntregableDocumento;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadEntregableServicio;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.DocumentosProyectoServicio;
import ec.kaymanta.gestproy.servicio.EntregableDocumentoServicio;
import ec.kaymanta.gestproy.servicio.HistorialDocumentoServicio;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.TipoDocumentoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class PanelDocsBean extends BotonesBean implements Serializable {

    @EJB
    private DocumentoServicio documentoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private ActividadEntregableServicio actividadEntregableServicio;
    @EJB
    private DocumentosProyectoServicio documentosProyectoServicio;
    @EJB
    private HistorialDocumentoServicio historialDocumentoServicio;
    @EJB
    private InstitucionControlServicio institucionControlServicio;
    @EJB
    private EntregableDocumentoServicio entregableDocumentoServicio;
    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;
    private String ENTIDAD = "Panel de Documentos";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private List<InstitucionControl> institucionesControl;
    private List<TipoDocumento> tipoDocumento;
    private List<Documento> documentos;
    private Documento documento;
    private Documento documentoSeleccionado;
    private Documento documentoAnt;
    private DocumentosProyecto documentosProyecto;
    private HistorialDocumento historialDocumento;
    private Proyecto proyecto;
    private Actividad actividad;
    private Actividad subActividad;
    private ActividadEntregable actividadEntregable;
    private String codProyecto;
    private String codActividad;
    private String codSubActividad;
    private String codActividadEntregable;
    private UploadedFile file;
    private EntregableDocumento entregableDocumento;
    //ELEMENTO DE VISTA
    private PieChartModel pieModel;
    private String instControl;
    private String tipoDoc;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();

        System.out.println("PROYECTO: " + codProyecto);
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        System.out.println("USUARIO: " + usrSesion);
        this.emplSesion = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parametros = context.getExternalContext().getRequestParameterMap();
        if (this.proyecto == null) {
            this.codProyecto = parametros.get("codProyecto");
            this.codActividad = parametros.get("codActividad");
            this.codSubActividad = parametros.get("codSubActividad");
            this.codActividadEntregable = parametros.get("codActividadEntregable");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
            this.actividad = this.actividadServicio.findByID(Long.parseLong(codActividad));
            this.subActividad = this.actividadServicio.findByID(Long.parseLong(codSubActividad));
            this.actividadEntregable = this.actividadEntregableServicio.findBySubActividadAndEntregable(subActividad, Long.parseLong(codActividadEntregable));

        }
        System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD " + actividad.getNombreActividad());
        this.documentos = this.documentoServicio.findBySubActividad(actividadEntregable);
        this.institucionesControl = this.institucionControlServicio.obtener();
        this.tipoDocumento = this.tipoDocumentoServicio.obtener();
        createPieModel();
        this.documento = new Documento();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Avance", subActividad.getAvance().floatValue());
        pieModel.set("Restante", 100 - subActividad.getAvance().floatValue());
    }

    public StreamedContent download(Long codigo) {
        Documento archivo = this.documentoServicio.findByID(codigo);
        InputStream stream = new ByteArrayInputStream(ArrayUtils.toPrimitive(archivo.getDocumento()));
        StreamedContent file = new DefaultStreamedContent(stream, "application/octet-stream", archivo.getNombreDocumento());
        return file;
    }

    public String getEstado(String estado) {

        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            System.out.println(estado);
            if (estado.equals("I")) {
                return "Inicial";
            } else if (estado.equals("P")) {
                return "Procesando";
            } else if (estado.equals("F")) {
                return "Finalizado";
            } else if (estado.equals("R")) {
                return "Revisado";
            }
            return "Desconocido";

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
            super.setDisableCargaDocumentos(Boolean.FALSE);
            System.out.println(super.getDisableCargaDocumentos());
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
            super.setDisableCargaDocumentos(Boolean.FALSE);
            System.out.println(super.getDisableCargaDocumentos());
            FacesMessage msg = new FacesMessage(name + " documento cargado. ");
            System.out.println("Carga: " + msg.getSummary() + " - " + event.getComponent().getClientId());
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg1 = new FacesMessage("Error", event.getFile().getFileName() + " no se cargo. " + resultado);

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

    public void nuevoDoc(ActionEvent evento) {
        super.crear();
        this.documento = new Documento();
    }

    public void verAuditoriaDoc(ActionEvent evento) throws IllegalAccessException {
        try {
            this.documento = new Documento();
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void modificarDoc(ActionEvent evento) {
        this.documento = new Documento();
        try {
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            this.instControl = this.documento.getInstitucionControl().getCodigo().toString();
            this.tipoDoc = this.documento.getTipoDocumento().getCodigo().toString();
        } catch (Exception e) {
        }
        super.modificar();
    }

    public void guardarDocumento(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                if (instControl != null || !!"".equals(instControl) || !"0".equals(instControl)) {
                    this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                    this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                }
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
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
            } else if (super.getEnEdicion()) {
                System.out.println("EN EDICIÓN DE DOCUMENTO");
                int i = this.documentos.indexOf(this.documento);
                if (instControl != null || !"".equals(instControl) || !"0".equals(instControl)) {
                    this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                    this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                }
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
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
            super.sinSeleccion();
        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }
    }

    public void filaSeleccionadaDoc(ActionEvent evento) {
        if (documentoSeleccionado instanceof Documento) {
            super.seleccionadoUno();
            System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + subActividad.getNombreActividad());

        } else {
            super.sinSeleccion();
            System.out.println("ESTOY ACA Y NO SELECCIONE");
        }
    }

    public DocumentoServicio getDocumentoServicio() {
        return documentoServicio;
    }

    public void setDocumentoServicio(DocumentoServicio documentoServicio) {
        this.documentoServicio = documentoServicio;
    }

    public ActividadServicio getActividadServicio() {
        return actividadServicio;
    }

    public void setActividadServicio(ActividadServicio actividadServicio) {
        this.actividadServicio = actividadServicio;
    }

    public HistorialDocumentoServicio getHistorialDocumentoServicio() {
        return historialDocumentoServicio;
    }

    public void setHistorialDocumentoServicio(HistorialDocumentoServicio historialDocumentoServicio) {
        this.historialDocumentoServicio = historialDocumentoServicio;
    }

    public InstitucionControlServicio getInstitucionControlServicio() {
        return institucionControlServicio;
    }

    public void setInstitucionControlServicio(InstitucionControlServicio institucionControlServicio) {
        this.institucionControlServicio = institucionControlServicio;
    }

    public String getENTIDAD() {
        return ENTIDAD;
    }

    public void setENTIDAD(String ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }

    public Usuario getUsrSesion() {
        return usrSesion;
    }

    public void setUsrSesion(Usuario usrSesion) {
        this.usrSesion = usrSesion;
    }

    public Empleado getEmplSesion() {
        return emplSesion;
    }

    public void setEmplSesion(Empleado emplSesion) {
        this.emplSesion = emplSesion;
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(Documento documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public Documento getDocumentoAnt() {
        return documentoAnt;
    }

    public void setDocumentoAnt(Documento documentoAnt) {
        this.documentoAnt = documentoAnt;
    }

    public DocumentosProyecto getDocumentosProyecto() {
        return documentosProyecto;
    }

    public void setDocumentosProyecto(DocumentosProyecto documentosProyecto) {
        this.documentosProyecto = documentosProyecto;
    }

    public HistorialDocumento getHistorialDocumento() {
        return historialDocumento;
    }

    public void setHistorialDocumento(HistorialDocumento historialDocumento) {
        this.historialDocumento = historialDocumento;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getSubActividad() {
        return subActividad;
    }

    public void setSubActividad(Actividad subActividad) {
        this.subActividad = subActividad;
    }

    public ActividadEntregable getActividadEntregable() {
        return actividadEntregable;
    }

    public void setActividadEntregable(ActividadEntregable actividadEntregable) {
        this.actividadEntregable = actividadEntregable;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public String getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(String codActividad) {
        this.codActividad = codActividad;
    }

    public String getCodSubActividad() {
        return codSubActividad;
    }

    public void setCodSubActividad(String codSubActividad) {
        this.codSubActividad = codSubActividad;
    }

    public String getCodActividadEntregable() {
        return codActividadEntregable;
    }

    public void setCodActividadEntregable(String codActividadEntregable) {
        this.codActividadEntregable = codActividadEntregable;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public EntregableDocumento getEntregableDocumento() {
        return entregableDocumento;
    }

    public void setEntregableDocumento(EntregableDocumento entregableDocumento) {
        this.entregableDocumento = entregableDocumento;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
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
}
