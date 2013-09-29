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

public class BotonesBean {

    private Boolean disabledNuevo;
    private Boolean disabledModificar;
    private Boolean disabledEliminar;
    private Boolean uno;
    private Boolean varios;
    private Boolean noSeleccionados;
    private Boolean enEdicion;
    private Boolean enRegistro;
    private Boolean soloLectura;

    @PostConstruct
    public void postConstructor() {
        this.sinSeleccion();
    }

    public void sinSeleccion() {
        this.reset();
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.noSeleccionados = Boolean.TRUE;
    }

    public void seleccionadoUno() {
        this.reset();
        this.uno = Boolean.TRUE;
    }

    public void seleccionadoVarios() {
        this.reset();
        this.disabledModificar = Boolean.TRUE;
        this.varios = Boolean.TRUE;
    }

    public void crear() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.enRegistro = Boolean.TRUE;
    }

    public void modificar() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.enEdicion = Boolean.TRUE;
    }
    
    public void cancelar()
    {
        this.reset();   
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        
    }

    public void verDetalles() {
        this.reset();
        this.disabledNuevo = Boolean.TRUE;
        this.disabledModificar = Boolean.TRUE;
        this.disabledEliminar = Boolean.TRUE;
        this.soloLectura = Boolean.TRUE;
    }

    private void reset() {
        this.disabledNuevo = Boolean.FALSE;
        this.disabledModificar = Boolean.FALSE;
        this.disabledEliminar = Boolean.FALSE;
        this.uno = Boolean.FALSE;
        this.varios = Boolean.FALSE;
        this.noSeleccionados = Boolean.FALSE;
        this.enEdicion = Boolean.FALSE;
        this.enRegistro = Boolean.FALSE;
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
}
