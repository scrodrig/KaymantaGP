/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.DocumentosProyectoServicio;
import ec.kaymanta.gestproy.servicio.HistorialDocumentoServicio;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.TipoDocumentoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class PanelDocumentosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelDocumentosBean
     */
    @EJB
    private DocumentoServicio documentoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private DocumentosProyectoServicio documentosProyectoServicio;
    @EJB
    private HistorialDocumentoServicio historialDocumentoServicio;
    @EJB
    private InstitucionControlServicio institucionControlServicio;
    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;
    private String ENTIDAD = "Documentos";
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
    private HistorialDocumento historialDocumentoRespaldo;
    private List<HistorialDocumento> historialDocumentos;
    private Proyecto proyecto;
    private String codProyecto;
    private UploadedFile file;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModel;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModelSalud;
    private String instControl;
    private String tipoDoc;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.emplSesion = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parametros = context.getExternalContext().getRequestParameterMap();
        if (this.proyecto == null) {
            this.codProyecto = parametros.get("codProyecto");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
        }
        this.documentos = this.documentoServicio.findByProyecto(proyecto);
        this.institucionesControl = this.institucionControlServicio.obtener();
        this.tipoDocumento = this.tipoDocumentoServicio.obtener();
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.documento = new Documento();
    }

    public StreamedContent download(Long codigo) {
        Documento archivo = this.documentoServicio.findByID(codigo);
        InputStream stream = new ByteArrayInputStream(ArrayUtils.toPrimitive(archivo.getDocumento()));
        StreamedContent file = new DefaultStreamedContent(stream, "application/octet-stream", archivo.getNombreDocumento());
        return file;
    }

    public StreamedContent downloadHistorial(Byte[] codigo, String nombre) {

        InputStream stream = new ByteArrayInputStream(ArrayUtils.toPrimitive(codigo));
        StreamedContent file = new DefaultStreamedContent(stream, "application/octet-stream", "Versi�n previa de " + nombre);
        return file;
    }

    public void versionesPrevias(ActionEvent evento) {
        historialDocumentos = this.historialDocumentoServicio.findByDocumento(documento.getCodigo());
        super.verDetalles();

    }

    private void createMeterGaugeChart() {
        meterGaugeChartModel = new MeterGaugeChartModel();
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(25);
                add(50);
                add(75);
                add(100);
            }
        };
        meterGaugeChartModel = new MeterGaugeChartModel(proyecto.getAvance(), intervals);
    }

    private void createMeterGaugeChartSalubridad() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(-10);
                add(0);
                add(10);
                add(100);
            }
        };
        if (!this.proyecto.getEstado().equals("F")) {
            meterGaugeChartModelSalud = new MeterGaugeChartModel(numeroDias(proyecto.getFestimada()), intervals);
        } else {
            meterGaugeChartModelSalud = new MeterGaugeChartModel(0, intervals);
        }
    }

    public int numeroDias(Date d2) {
        try {
            Date d1 = new Date();
            if (d1.before(d2)) {
                DateTime dt1 = new DateTime(d1);
                DateTime dt2 = new DateTime(d2);
                Days daysBetween = Days.daysBetween(dt1, dt2);
                return -daysBetween.getDays();
            } else if (d1.after(d2)) {
                DateTime dt1 = new DateTime(d2);
                DateTime dt2 = new DateTime(d1);
                Days daysBetween = Days.daysBetween(dt1, dt2);
                return daysBetween.getDays();
            } else if (d1.compareTo(d2) == 0) {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public boolean holgura(int dias) {
        if (dias <= 0) {
            return true;
        } else {
            return false;
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

    public void nuevoDocumento(ActionEvent evento) {
        super.crear();
        this.documento = new Documento();
    }

    public void modificarDocumento(ActionEvent evento) {
        this.documento = new Documento();
        try {
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            this.instControl = this.documento.getInstitucionControl().getCodigo().toString();
            this.tipoDoc = this.documento.getTipoDocumento().getCodigo().toString();
        } catch (Exception e) {
        }
        super.modificar();
    }

    public void verAuditoriaDocumento(ActionEvent evento) throws IllegalAccessException {
        try {
            this.documento = new Documento();
            this.documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void subirDocumento(FileUploadEvent event) {
        this.documento = new Documento();
        String resultado = "";
        try {
            String name = event.getFile().getFileName();
            this.documento.setDocumento(ArrayUtils.toObject(IOUtils.toByteArray(event.getFile().getInputstream())));
            //this.documento.setDocumento(IOUtils);
            this.documento.setNombreDocumento(name);
            /////////////
            super.setDisableCargaDocumentos(Boolean.FALSE);
            FacesMessage msg = new FacesMessage(name + " documento cargado. ");
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg1 = new FacesMessage("Error", event.getFile().getFileName() + " no se cargo. " + resultado);

        }
    }

    public void subirDocumentoModificado(FileUploadEvent event) {
        documentoAnt = new Documento();
        String resultado = "";
        try {
            documentoAnt = (Documento) BeanUtils.cloneBean(this.documento);
            String name = event.getFile().getFileName();
            this.documento.setDocumento(ArrayUtils.toObject(IOUtils.toByteArray(event.getFile().getInputstream())));
            this.documento.setNombreDocumento(name);
            super.setDisableCargaDocumentos(Boolean.FALSE);
            FacesMessage msg = new FacesMessage(name + " documento cargado. ");
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg1 = new FacesMessage("Error", event.getFile().getFileName() + " no ha sido cargo. " + resultado);

        }
    }

    public void cargarDocumento(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                if (instControl != null || !"".equals(instControl) || !"0".equals(instControl)) {
                    this.documento.setInstitucionControl(institucionControlServicio.findByID(Long.parseLong(instControl)));
                    this.documento.setCodInstitucionControl(Long.parseLong(instControl));
                }
                this.documento.setTipoDocumento(tipoDocumentoServicio.findByID(Long.parseLong(tipoDoc)));
                this.documento.setCodTipoDocumento(Long.parseLong(tipoDoc));
                this.documento.setUsrCreacion(usrSesion.getCodigo());
                this.documento.setFcreacion(new Date());
                this.documentoServicio.crear(documento);
                this.documentos.add(documento);
                //CREACI�N TABLA DOCUMENTOS PROYECTO
                this.documentosProyecto = new DocumentosProyecto();
                this.documentosProyecto.getPk().setDocumento(documento.getCodigo());
                this.documentosProyecto.getPk().setProyecto(proyecto.getCodigo());
                this.documentosProyecto.setFecha(new Date());
                this.documentosProyecto.setDocumento(documento);
                this.documentosProyecto.setProyecto(proyecto);
                this.documentosProyecto.setFcreacion(new Date());
                this.documentosProyecto.setUsrCreacion(usrSesion.getCodigo());
                this.documentosProyectoServicio.crear(documentosProyecto);
                //_____CREACI�N DE DOCUMENTOS
                MensajesGenericos.infoCargado("Se ha cargado el archivo " + documento.getNombreDocumento());
                super.sinSeleccion();
                this.instControl = new String();
                this.tipoDoc = new String();
            } else if (super.getEnEdicion()) {
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

    public void filaSeleccionadaDocumento(ActionEvent evento) {
        if (documentoSeleccionado instanceof Documento) {
            try {
                super.seleccionadoUno();
                documento = new Documento();
                documento = (Documento) BeanUtils.cloneBean(this.documentoSeleccionado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.sinSeleccion();
        }
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

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public MeterGaugeChartModel getMeterGaugeChartModel() {
        return meterGaugeChartModel;
    }

    public void setMeterGaugeChartModel(MeterGaugeChartModel meterGaugeChartModel) {
        this.meterGaugeChartModel = meterGaugeChartModel;
    }

    public MeterGaugeChartModel getMeterGaugeChartModelSalud() {
        return meterGaugeChartModelSalud;
    }

    public void setMeterGaugeChartModelSalud(MeterGaugeChartModel meterGaugeChartModelSalud) {
        this.meterGaugeChartModelSalud = meterGaugeChartModelSalud;
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

    public HistorialDocumentoServicio getHistorialDocumentoServicio() {
        return historialDocumentoServicio;
    }

    public void setHistorialDocumentoServicio(HistorialDocumentoServicio historialDocumentoServicio) {
        this.historialDocumentoServicio = historialDocumentoServicio;
    }

    public HistorialDocumento getHistorialDocumentoRespaldo() {
        return historialDocumentoRespaldo;
    }

    public void setHistorialDocumentoRespaldo(HistorialDocumento historialDocumentoRespaldo) {
        this.historialDocumentoRespaldo = historialDocumentoRespaldo;
    }

    public List<HistorialDocumento> getHistorialDocumentos() {
        return historialDocumentos;
    }

    public void setHistorialDocumentos(List<HistorialDocumento> historialDocumentos) {
        this.historialDocumentos = historialDocumentos;
    }
}
