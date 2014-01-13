/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.DocumentoServicio;
import ec.kaymanta.gestproy.servicio.HistorialDocumentoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class BuscadorDocumentosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of BuscadorDocumentos
     */
    @EJB
    private DocumentoServicio documentoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private HistorialDocumentoServicio historialDocumentoServicio;
    private List<Documento> documentos;
    private Documento documento;
    private Documento documentoSeleccionado;
    private HistorialDocumento historialDocumento;
    private HistorialDocumento historialDocumentoSeleccionado;
    private List<HistorialDocumento> historialDocumentos;
    private String ENTIDAD = "Buscador de Documentos";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private String parametro;
    private String valor;
    private String criterio;
    private boolean flag;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        //documentos = documentoServicio.obtener();
        flag=true;
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
    }

    public void buscar(ActionEvent evento) {
        documentos = documentoServicio.getDocumentos(parametro, valor, criterio);
        //super.modificar();
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

    public void filaSeleccionadaHistorial(ActionEvent evento) {
        if (historialDocumentoSeleccionado instanceof HistorialDocumento) {
            try {
                //super.seleccionadoUno();
                historialDocumento = new HistorialDocumento();
                historialDocumento = (HistorialDocumento) BeanUtils.cloneBean(this.historialDocumentoSeleccionado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.sinSeleccion();
        }
    }

    public StreamedContent download(Long codigo) {
        Documento archivo = this.documentoServicio.findByID(codigo);
        InputStream stream = new ByteArrayInputStream(ArrayUtils.toPrimitive(archivo.getDocumento()));
        StreamedContent file = new DefaultStreamedContent(stream, "application/octet-stream", archivo.getNombreDocumento());
        return file;
    }

    public StreamedContent downloadHistorial(Byte[] codigo, String nombre) {

        InputStream stream = new ByteArrayInputStream(ArrayUtils.toPrimitive(codigo));
        StreamedContent file = new DefaultStreamedContent(stream, "application/octet-stream", "Versión previa de " + nombre);
        return file;
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
        historialDocumentos = this.historialDocumentoServicio.findByDocumento(documento.getCodigo());
        super.crear();
        
    }

    public void modificarDocumento(ActionEvent evento) {

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

    public UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public HistorialDocumento getHistorialDocumento() {
        return historialDocumento;
    }

    public void setHistorialDocumento(HistorialDocumento historialDocumento) {
        this.historialDocumento = historialDocumento;
    }

    public HistorialDocumento getHistorialDocumentoSeleccionado() {
        return historialDocumentoSeleccionado;
    }

    public void setHistorialDocumentoSeleccionado(HistorialDocumento historialDocumentoSeleccionado) {
        this.historialDocumentoSeleccionado = historialDocumentoSeleccionado;
    }

    public List<HistorialDocumento> getHistorialDocumentos() {
        return historialDocumentos;
    }

    public void setHistorialDocumentos(List<HistorialDocumento> historialDocumentos) {
        this.historialDocumentos = historialDocumentos;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    
}
