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
    //FLAGS BOTONES ACTIVIDADES
    private Boolean enAuditoriaActividad;
    private Boolean enNuevaActividad;
    private Boolean enEdicionActividad;
    private Boolean enActividades;
    private Boolean disableNuevaActividad;
    private Boolean disableModificarActividad;
    private Boolean disableAuditoriaActividad;
    //FLAGS BOTONES SUB-ACTIVIDADES
    private Boolean enAuditoriaSubActividad;
    private Boolean enNuevaSubActividad;
    private Boolean enEdicionSubActividad;
    private Boolean enSubActividades;
    private Boolean disableNuevaSubActividad;
    private Boolean disableModificarSubActividad;
    private Boolean disableAuditoriaSubActividad;
    //FLAGS PARA GASTOS
    private Boolean enGastos;
    private Boolean enNuevoGasto;
    private Boolean enEdicionGasto;
    private Boolean enAuditoriaGasto;
    private Boolean disableBotonesGastos;

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

    public void sinSeleccionActividad() {
        this.resetTablaActividad();
        this.enActividades = Boolean.TRUE;
        this.noSeleccionados = Boolean.TRUE;
        this.disableAuditoriaActividad = Boolean.TRUE;
        this.disableModificarActividad = Boolean.TRUE;
        this.uno = Boolean.FALSE;

    }

    public void sinSeleccionSubActividad() {
        this.resetTablaSubActividad();
        this.enSubActividades = Boolean.TRUE;
        this.noSeleccionados = Boolean.TRUE;
        this.disableAuditoriaSubActividad = Boolean.TRUE;
        this.disableModificarSubActividad = Boolean.TRUE;
        this.uno = Boolean.FALSE;

    }
    
    public void sinSeleccionGastos() {
        this.resetTablaGastos();
        this.enGastos = Boolean.TRUE;
        this.noSeleccionados = Boolean.TRUE;
        this.disableBotonesGastos = Boolean.TRUE;
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

    public void seleccionadoUnoActividades() {
        this.resetTablaActividad();
        this.uno = Boolean.TRUE;
    }

    public void seleccionadoUnoSubActividades() {
        this.resetTablaSubActividad();
        this.uno = Boolean.TRUE;
    }
    
     public void seleccionadoUnoGastos() {
        this.resetTablaGastos();
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

    public void crearActividad() {
        this.reset();
        this.disableModificarActividad = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoriaActividad = Boolean.TRUE;
        this.enNuevaActividad = Boolean.TRUE;
    }

    public void crearSubActividad() {
        this.reset();
        this.disableModificarSubActividad = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoriaSubActividad = Boolean.TRUE;
        this.enNuevaSubActividad = Boolean.TRUE;
    }
    
    public void crearGasto() {
        this.reset();
        this.disableBotonesGastos = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.enNuevoGasto = Boolean.TRUE;
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
        this.disableEdicionDocumento = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.disableAuditoriaDocumento = Boolean.TRUE;
        this.disableGuardarDocumento = Boolean.TRUE;
        this.disableCargaDocumentos = Boolean.TRUE;
        this.enEdicionDocumento = Boolean.TRUE;
    }

    public void modificarActividad() {
        this.reset();
        this.enEdicionActividad = Boolean.TRUE;

    }

    public void modificarSubActividad() {
        this.reset();
        this.enEdicionSubActividad = Boolean.TRUE;
    }
    
    public void modificarGasto() {
        this.reset();
        this.enEdicionGasto = Boolean.TRUE;
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

    public void verAuditoriaActividad() {
        this.reset();
        this.disableAuditoriaActividad = Boolean.TRUE;
        this.disableModificarActividad = Boolean.TRUE;
        this.disableNuevaActividad = Boolean.TRUE;
        this.enAuditoriaActividad = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }

    public void verAuditoriaSubActividad() {
        this.reset();
        this.disableAuditoriaSubActividad = Boolean.TRUE;
        this.disableModificarSubActividad = Boolean.TRUE;
        this.disableNuevaSubActividad = Boolean.TRUE;
        this.enAuditoriaSubActividad = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }
    
    public void verAuditoriaGastos() {
        this.reset();
        this.disableBotonesGastos = Boolean.TRUE;
        this.enAuditoriaGasto = Boolean.TRUE;
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

    public void verActividades() {
        this.reset();
        this.enActividades = Boolean.TRUE;
        this.disableAuditoriaActividad = Boolean.TRUE;
        this.disableModificarActividad = Boolean.TRUE;
    }

    public void verSubActividades() {
        this.reset();
        this.enSubActividades = Boolean.TRUE;
        this.disableAuditoriaSubActividad = Boolean.TRUE;
        this.disableModificarSubActividad = Boolean.TRUE;
    }
    
    public void verGastos() {
        this.reset();
        this.enGastos = Boolean.TRUE;
        this.disableBotonesGastos = Boolean.TRUE;
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

        //ACTIVIDADES
        this.disableAuditoriaActividad = Boolean.FALSE;
        this.disableModificarActividad = Boolean.FALSE;
        this.disableNuevaActividad = Boolean.FALSE;
        this.enAuditoriaActividad = Boolean.FALSE;
        this.enEdicionActividad = Boolean.FALSE;
        this.enNuevaActividad = Boolean.FALSE;
        this.enActividades = Boolean.FALSE;

        //SUB-ACTIVIDADES
        this.disableAuditoriaSubActividad = Boolean.FALSE;
        this.disableModificarSubActividad = Boolean.FALSE;
        this.disableNuevaSubActividad = Boolean.FALSE;
        this.enAuditoriaSubActividad = Boolean.FALSE;
        this.enEdicionSubActividad = Boolean.FALSE;
        this.enNuevaSubActividad = Boolean.FALSE;
        this.enSubActividades = Boolean.FALSE;

        //GASTOS
        this.enGastos = Boolean.FALSE;
        this.enNuevoGasto = Boolean.FALSE;
        this.enEdicionGasto = Boolean.FALSE;
        this.enAuditoriaGasto = Boolean.FALSE;
        this.disableBotonesGastos = Boolean.FALSE;



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

        this.disableAuditoriaActividad = Boolean.FALSE;
        this.disableModificarActividad = Boolean.FALSE;
        this.disableNuevaActividad = Boolean.FALSE;
        this.enAuditoriaActividad = Boolean.FALSE;
        this.enEdicionActividad = Boolean.FALSE;
        this.enNuevaActividad = Boolean.FALSE;

        //SUB-ACTIVIDADES
        this.disableAuditoriaSubActividad = Boolean.FALSE;
        this.disableModificarSubActividad = Boolean.FALSE;
        this.disableNuevaSubActividad = Boolean.FALSE;
        this.enAuditoriaSubActividad = Boolean.FALSE;
        this.enEdicionSubActividad = Boolean.FALSE;
        this.enNuevaSubActividad = Boolean.FALSE;
        this.enSubActividades = Boolean.FALSE;
        
        //GASTOS
        this.enGastos = Boolean.FALSE;
        this.enNuevoGasto = Boolean.FALSE;
        this.enEdicionGasto = Boolean.FALSE;
        this.enAuditoriaGasto = Boolean.FALSE;
        this.disableBotonesGastos = Boolean.FALSE;
    }

    private void resetTablaActividad() {
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

        //SUB-ACTIVIDADES
        this.disableAuditoriaSubActividad = Boolean.FALSE;
        this.disableModificarSubActividad = Boolean.FALSE;
        this.disableNuevaSubActividad = Boolean.FALSE;
        this.enAuditoriaSubActividad = Boolean.FALSE;
        this.enEdicionSubActividad = Boolean.FALSE;
        this.enNuevaSubActividad = Boolean.FALSE;
        this.enSubActividades = Boolean.FALSE;


        this.disableAuditoriaActividad = Boolean.FALSE;
        this.disableModificarActividad = Boolean.FALSE;
        this.disableNuevaActividad = Boolean.FALSE;
        this.enAuditoriaActividad = Boolean.FALSE;
        this.enEdicionActividad = Boolean.FALSE;
        this.enNuevaActividad = Boolean.FALSE;
        
        
        //GASTOS
        this.enGastos = Boolean.FALSE;
        this.enNuevoGasto = Boolean.FALSE;
        this.enEdicionGasto = Boolean.FALSE;
        this.enAuditoriaGasto = Boolean.FALSE;
        this.disableBotonesGastos = Boolean.FALSE;
    }

    private void resetTablaSubActividad() {
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

        //SUB-ACTIVIDADES
        this.disableAuditoriaSubActividad = Boolean.FALSE;
        this.disableModificarSubActividad = Boolean.FALSE;
        this.disableNuevaSubActividad = Boolean.FALSE;
        this.enAuditoriaSubActividad = Boolean.FALSE;
        this.enEdicionSubActividad = Boolean.FALSE;
        this.enNuevaSubActividad = Boolean.FALSE;
        this.enActividades = Boolean.FALSE;
        
        //GASTOS
        this.enGastos = Boolean.FALSE;
        this.enNuevoGasto = Boolean.FALSE;
        this.enEdicionGasto = Boolean.FALSE;
        this.enAuditoriaGasto = Boolean.FALSE;
        this.disableBotonesGastos = Boolean.FALSE;


        this.disableAuditoriaActividad = Boolean.FALSE;
        this.disableModificarActividad = Boolean.FALSE;
        this.disableNuevaActividad = Boolean.FALSE;
        this.enAuditoriaActividad = Boolean.FALSE;
        this.enEdicionActividad = Boolean.FALSE;
        this.enNuevaActividad = Boolean.FALSE;
    }
    
    
    private void resetTablaGastos() {
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

        //SUB-ACTIVIDADES
        this.disableAuditoriaSubActividad = Boolean.FALSE;
        this.disableModificarSubActividad = Boolean.FALSE;
        this.disableNuevaSubActividad = Boolean.FALSE;
        this.enAuditoriaSubActividad = Boolean.FALSE;
        this.enEdicionSubActividad = Boolean.FALSE;
        this.enNuevaSubActividad = Boolean.FALSE;
        this.enActividades = Boolean.FALSE;
        
        //GASTOS
        this.enNuevoGasto = Boolean.FALSE;
        this.enEdicionGasto = Boolean.FALSE;
        this.enAuditoriaGasto = Boolean.FALSE;
        this.disableBotonesGastos = Boolean.FALSE;


        this.disableAuditoriaActividad = Boolean.FALSE;
        this.disableModificarActividad = Boolean.FALSE;
        this.disableNuevaActividad = Boolean.FALSE;
        this.enAuditoriaActividad = Boolean.FALSE;
        this.enEdicionActividad = Boolean.FALSE;
        this.enNuevaActividad = Boolean.FALSE;
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

    public Boolean getEnAuditoriaActividad() {
        return enAuditoriaActividad;
    }

    public void setEnAuditoriaActividad(Boolean enAuditoriaActividad) {
        this.enAuditoriaActividad = enAuditoriaActividad;
    }

    public Boolean getEnNuevaActividad() {
        return enNuevaActividad;
    }

    public void setEnNuevaActividad(Boolean enNuevaActividad) {
        this.enNuevaActividad = enNuevaActividad;
    }

    public Boolean getEnEdicionActividad() {
        return enEdicionActividad;
    }

    public void setEnEdicionActividad(Boolean enEdicionActividad) {
        this.enEdicionActividad = enEdicionActividad;
    }

    public Boolean getDisableNuevaActividad() {
        return disableNuevaActividad;
    }

    public void setDisableNuevaActividad(Boolean disableNuevaActividad) {
        this.disableNuevaActividad = disableNuevaActividad;
    }

    public Boolean getDisableModificarActividad() {
        return disableModificarActividad;
    }

    public void setDisableModificarActividad(Boolean disableModificarActividad) {
        this.disableModificarActividad = disableModificarActividad;
    }

    public Boolean getDisableAuditoriaActividad() {
        return disableAuditoriaActividad;
    }

    public void setDisableAuditoriaActividad(Boolean disableAuditoriaActividad) {
        this.disableAuditoriaActividad = disableAuditoriaActividad;
    }

    public Boolean getEnActividades() {
        return enActividades;
    }

    public void setEnActividades(Boolean enActividades) {
        this.enActividades = enActividades;
    }

    public Boolean getEnAuditoriaSubActividad() {
        return enAuditoriaSubActividad;
    }

    public void setEnAuditoriaSubActividad(Boolean enAuditoriaSubActividad) {
        this.enAuditoriaSubActividad = enAuditoriaSubActividad;
    }

    public Boolean getEnNuevaSubActividad() {
        return enNuevaSubActividad;
    }

    public void setEnNuevaSubActividad(Boolean enNuevaSubActividad) {
        this.enNuevaSubActividad = enNuevaSubActividad;
    }

    public Boolean getEnEdicionSubActividad() {
        return enEdicionSubActividad;
    }

    public void setEnEdicionSubActividad(Boolean enEdicionSubActividad) {
        this.enEdicionSubActividad = enEdicionSubActividad;
    }

    public Boolean getEnSubActividades() {
        return enSubActividades;
    }

    public void setEnSubActividades(Boolean enSubActividades) {
        this.enSubActividades = enSubActividades;
    }

    public Boolean getDisableNuevaSubActividad() {
        return disableNuevaSubActividad;
    }

    public void setDisableNuevaSubActividad(Boolean disableNuevaSubActividad) {
        this.disableNuevaSubActividad = disableNuevaSubActividad;
    }

    public Boolean getDisableModificarSubActividad() {
        return disableModificarSubActividad;
    }

    public void setDisableModificarSubActividad(Boolean disableModificarSubActividad) {
        this.disableModificarSubActividad = disableModificarSubActividad;
    }

    public Boolean getDisableAuditoriaSubActividad() {
        return disableAuditoriaSubActividad;
    }

    public void setDisableAuditoriaSubActividad(Boolean disableAuditoriaSubActividad) {
        this.disableAuditoriaSubActividad = disableAuditoriaSubActividad;
    }

    public Boolean getEnGastos() {
        return enGastos;
    }

    public void setEnGastos(Boolean enGastos) {
        this.enGastos = enGastos;
    }

    public Boolean getEnNuevoGasto() {
        return enNuevoGasto;
    }

    public void setEnNuevoGasto(Boolean enNuevoGasto) {
        this.enNuevoGasto = enNuevoGasto;
    }

    public Boolean getEnEdicionGasto() {
        return enEdicionGasto;
    }

    public void setEnEdicionGasto(Boolean enEdicionGasto) {
        this.enEdicionGasto = enEdicionGasto;
    }

    public Boolean getEnAuditoriaGasto() {
        return enAuditoriaGasto;
    }

    public void setEnAuditoriaGasto(Boolean enAuditoriaGasto) {
        this.enAuditoriaGasto = enAuditoriaGasto;
    }

    public Boolean getDisableBotonesGastos() {
        return disableBotonesGastos;
    }

    public void setDisableBotonesGastos(Boolean disableBotonesGastos) {
        this.disableBotonesGastos = disableBotonesGastos;
    }
}
