 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

/**
 *
 * @author schubert_david
 */
import javax.annotation.PostConstruct;

public class BotonesBeanProyecto {

    private Boolean disabledNuevo;
    private Boolean disabledModificar;
    private Boolean disabledEliminar;
    private Boolean disableAuditoria;
    private Boolean disableCargaDocumentos;
    private Boolean uno;
    private Boolean varios;
    private Boolean noSeleccionados;
    private Boolean enEdicion;
    private Boolean enRegistro;
    private Boolean soloLectura;
    private Boolean enAuditoria;
    private Boolean enCargaDocumentos;
    //FLAGS BOTONES UPLOAD DOCUMENTOS
    private Boolean enNuevoDocumento;
    private Boolean enEdicionDocumento;
    private Boolean enAuditoriaDocumento;
    private Boolean disableGuardarDocumento;
    private Boolean disableNuevoDocumento;
    private Boolean disableEdicionDocumento;
    private Boolean disableAuditoriaDocumento;

    @PostConstruct
    public void postConstructor() {
        this.sinSeleccion();
    }

    public void sinSeleccion() {
        this.reset();
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;

        this.noSeleccionados = Boolean.TRUE;

        this.disableAuditoria = Boolean.TRUE;
        this.disableAuditoriaDocumento = Boolean.TRUE;

        this.disableEdicionDocumento = Boolean.TRUE;

        this.disableCargaDocumentos = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;

        this.uno = Boolean.FALSE;
    }

    public void sinSeleccionDocumento() {
        this.resetTablaDocumentos();
        this.noSeleccionados = Boolean.TRUE;
        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableCargaDocumentos = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.uno = Boolean.FALSE;
        
    }

    public void seleccionadoUno() {
        this.reset();
        this.uno = Boolean.TRUE;
    }

    public void seleccionadoUnoDocumentos() {
        this.resetTablaDocumentos();
        this.uno = Boolean.TRUE;
    }

    public void seleccionadoVarios() {
        this.reset();
        this.disabledModificar = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.varios = Boolean.TRUE;
    }

    public void crear() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.enRegistro = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.disableCargaDocumentos = Boolean.TRUE;
    }

    public void crearDocumento() {
        this.reset();
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.disableCargaDocumentos = Boolean.TRUE;
        this.enNuevoDocumento = Boolean.TRUE;
        //this.enCargaDocumentos= Boolean.TRUE;
    }

    public void modificar() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.disableCargaDocumentos = Boolean.TRUE;
        this.enEdicion = Boolean.TRUE;

    }

    public void modificarDocumento() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.disableCargaDocumentos = Boolean.TRUE;
        this.enEdicionDocumento = Boolean.TRUE;

    }

    public void cancelar() {
        this.reset();
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.enAuditoria = Boolean.FALSE;
        this.enAuditoriaDocumento = Boolean.FALSE;
        this.disableCargaDocumentos = Boolean.TRUE;
        this.soloLectura = Boolean.FALSE;
    }

    public void verAuditoria() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.disableCargaDocumentos = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.enAuditoria = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }

    public void verAuditoriaDocumento() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.disableCargaDocumentos = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.enAuditoriaDocumento = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }

    public void verDetalles() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoria = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disableNuevoDocumento = Boolean.TRUE;

        this.disableGuardarDocumento = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }

    public void verCargaDocumentos() {
        this.reset();

        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;

        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableEdicionDocumento = Boolean.TRUE;

        this.disableGuardarDocumento = Boolean.TRUE;

        this.soloLectura = Boolean.FALSE;

        this.enCargaDocumentos = Boolean.TRUE;
    }

    private void reset() {
        this.disabledNuevo = Boolean.FALSE;
        this.disabledModificar = Boolean.FALSE;
        this.disabledEliminar = Boolean.FALSE;
        this.disableAuditoria = Boolean.FALSE;

        this.disableCargaDocumentos = Boolean.FALSE;
        this.disableNuevoDocumento = Boolean.FALSE;
        this.disableAuditoriaDocumento = Boolean.FALSE;
        this.disableEdicionDocumento = Boolean.FALSE;

        this.enNuevoDocumento = Boolean.FALSE;
        this.enEdicionDocumento = Boolean.FALSE;
        this.uno = Boolean.FALSE;
        this.varios = Boolean.FALSE;
        this.noSeleccionados = Boolean.FALSE;
        this.enEdicion = Boolean.FALSE;
        this.enRegistro = Boolean.FALSE;
        this.enAuditoria = Boolean.FALSE;
        this.enCargaDocumentos = Boolean.FALSE;
        this.disableGuardarDocumento = Boolean.FALSE;
        this.soloLectura = Boolean.FALSE;
    }

    private void resetTablaDocumentos() {
        this.disabledNuevo = Boolean.FALSE;
        this.disabledModificar = Boolean.FALSE;
        this.disabledEliminar = Boolean.FALSE;
        this.disableAuditoria = Boolean.FALSE;

        this.disableCargaDocumentos = Boolean.FALSE;
        this.disableNuevoDocumento = Boolean.FALSE;
        this.disableAuditoriaDocumento = Boolean.FALSE;
        this.disableEdicionDocumento = Boolean.FALSE;

        this.enNuevoDocumento = Boolean.FALSE;
        this.enEdicionDocumento = Boolean.FALSE;
        this.uno = Boolean.FALSE;
        this.varios = Boolean.FALSE;
        this.noSeleccionados = Boolean.FALSE;
        this.enEdicion = Boolean.FALSE;
        this.enRegistro = Boolean.FALSE;
        this.enAuditoria = Boolean.FALSE;
        this.disableGuardarDocumento = Boolean.FALSE;
        this.soloLectura = Boolean.FALSE;
    }

    public Boolean getDisabledEliminar() {
        return disabledEliminar;
    }

    public void setDisabledEliminar(Boolean disabledEliminar) {
        this.disabledEliminar = disabledEliminar;
    }

    public Boolean getDisabledModificar() {
        return disabledModificar;
    }

    public void setDisabledModificar(Boolean disabledModificar) {
        this.disabledModificar = disabledModificar;
    }

    public Boolean getDisabledNuevo() {
        return disabledNuevo;
    }

    public void setDisabledNuevo(Boolean disabledNuevo) {
        this.disabledNuevo = disabledNuevo;
    }

    public Boolean getEnEdicion() {
        return enEdicion;
    }

    public Boolean getEnRegistro() {
        return enRegistro;
    }

    public void setEnEdicion(Boolean enEdicion) {
        this.enEdicion = enEdicion;
    }

    public Boolean getSoloLectura() {
        return soloLectura;
    }

    public Boolean getNoSeleccionados() {
        return noSeleccionados;
    }

    public void setNoSeleccionados(Boolean noSeleccionados) {
        this.noSeleccionados = noSeleccionados;
    }

    public Boolean getUno() {
        return uno;
    }

    public void setUno(Boolean uno) {
        this.uno = uno;
    }

    public Boolean getVarios() {
        return varios;
    }

    public void setVarios(Boolean varios) {
        this.varios = varios;
    }

    public Boolean getDisableAuditoria() {
        return disableAuditoria;
    }

    public void setDisableAuditoria(Boolean disableAuditoria) {
        this.disableAuditoria = disableAuditoria;
    }

    public Boolean getEnAuditoria() {
        return enAuditoria;
    }

    public void setEnAuditoria(Boolean enAuditoria) {
        this.enAuditoria = enAuditoria;
    }

    public Boolean getDisableCargaDocumentos() {
        return disableCargaDocumentos;
    }

    public void setDisableCargaDocumentos(Boolean disableCargaDocumentos) {
        this.disableCargaDocumentos = disableCargaDocumentos;
    }

    public Boolean getEnCargaDocumentos() {
        return enCargaDocumentos;
    }

    public void setEnCargaDocumentos(Boolean enCargaDocumentos) {
        this.enCargaDocumentos = enCargaDocumentos;
    }

    public Boolean getDisableGuardarDocumento() {
        return disableGuardarDocumento;
    }

    public void setDisableGuardarDocumento(Boolean disableGuardarDocumento) {
        this.disableGuardarDocumento = disableGuardarDocumento;
    }

    public Boolean getEnNuevoDocumento() {
        return enNuevoDocumento;
    }

    public void setEnNuevoDocumento(Boolean enNuevoDocumento) {
        this.enNuevoDocumento = enNuevoDocumento;
    }

    public Boolean getEnEdicionDocumento() {
        return enEdicionDocumento;
    }

    public void setEnEdicionDocumento(Boolean enEdicionDocumento) {
        this.enEdicionDocumento = enEdicionDocumento;
    }

    public Boolean getEnAuditoriaDocumento() {
        return enAuditoriaDocumento;
    }

    public void setEnAuditoriaDocumento(Boolean enAuditoriaDocumento) {
        this.enAuditoriaDocumento = enAuditoriaDocumento;
    }

    public Boolean getDisableNuevoDocumento() {
        return disableNuevoDocumento;
    }

    public void setDisableNuevoDocumento(Boolean disableNuevoDocumento) {
        this.disableNuevoDocumento = disableNuevoDocumento;
    }

    public Boolean getDisableEdicionDocumento() {
        return disableEdicionDocumento;
    }

    public void setDisableEdicionDocumento(Boolean disableEdicionDocumento) {
        this.disableEdicionDocumento = disableEdicionDocumento;
    }

    public Boolean getDisableAuditoriaDocumento() {
        return disableAuditoriaDocumento;
    }

    public void setDisableAuditoriaDocumento(Boolean disableAuditoriaDocumento) {
        this.disableAuditoriaDocumento = disableAuditoriaDocumento;
    }
}
