/*
 * KAYMANTA CIA. LTDA.
 * Sistema: GESTPROY 1.0 - KAYMANTA
 * Creado: 15-sep-2013 - 15:12:12
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2013-2014 Kaymanta Todos los derechos reservados.
 */
package ec.kaymanta.gestproy.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * La Clase ActividadSegumientoPK representa a la clave primaria de la tabla
 * gpk_actividad_segumiento.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class ActividadSegumientoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 3525206269893976723L;
    /**
     * Código que identifica al segumiento de un proyecto.. Representa a la
     * columna COD_ACTIVIDAD_SEGUIMIENTO.
     */
    @Column(name = "COD_ACTIVIDAD_SEGUIMIENTO", nullable = false)
    private Long codigoActividadSeguimiento;
    /**
     * Llave foránea que referencia a una actividad o subactividad del
     * desarrollo de un proyecto.. Representa a la columna ACTIVIDAD.
     */
    @Column(name = "ACTIVIDAD", nullable = false)
    private Long actividad;

    /**
     * Crea una nueva instancia de ActividadSegumientoPK.
     */
    public ActividadSegumientoPK() {
    }

    /**
     * Crea una nueva instancia de ActividadSegumientoPK.
     *
     * @param codigoActividadSeguimiento valor a ser asignado a la propiedad
     * codigoActividadSeguimiento
     * @param actividad valor a ser asignado a la propiedad actividad
     */
    public ActividadSegumientoPK(Long codigoActividadSeguimiento, Long actividad) {
        this.codigoActividadSeguimiento = codigoActividadSeguimiento;
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad codigoActividadSeguimiento relacionado
     * con la columna COD_ACTIVIDAD_SEGUIMIENTO.
     *
     * @return el valor asignado a la propiedad codigoActividadSeguimiento
     */
    public Long getCodigoActividadSeguimiento() {
        return codigoActividadSeguimiento;
    }

    /**
     * Asigna un valor a la propiedad codigoActividadSeguimiento, relacionado
     * con la columna COD_ACTIVIDAD_SEGUIMIENTO.
     *
     * @param codigoActividadSeguimiento el valor a ser asignado a la propiedad
     * codigoActividadSeguimiento
     */
    public void setCodigoActividadSeguimiento(Long codigoActividadSeguimiento) {
        this.codigoActividadSeguimiento = codigoActividadSeguimiento;
    }

    /**
     * Obtiene el valor de la propiedad actividad relacionado con la columna
     * ACTIVIDAD.
     *
     * @return el valor asignado a la propiedad actividad
     */
    public Long getActividad() {
        return actividad;
    }

    /**
     * Asigna un valor a la propiedad actividad, relacionado con la columna
     * ACTIVIDAD.
     *
     * @param actividad el valor a ser asignado a la propiedad actividad
     */
    public void setActividad(Long actividad) {
        this.actividad = actividad;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += codigoActividadSeguimiento != null ? codigoActividadSeguimiento.hashCode() : 0;
        hash += actividad != null ? actividad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ActividadSegumientoPK["
                + " codigoActividadSeguimiento=" + codigoActividadSeguimiento
                + ",actividad=" + actividad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadSegumientoPK)) {
            igual = false;
        }
        ActividadSegumientoPK other = (ActividadSegumientoPK) object;
        if ((this.codigoActividadSeguimiento == null && other.codigoActividadSeguimiento != null)
                || (this.codigoActividadSeguimiento != null && !this.codigoActividadSeguimiento
                .equals(other.codigoActividadSeguimiento))) {
            igual = false;
        }
        if ((this.actividad == null && other.actividad != null)
                || (this.actividad != null && !this.actividad
                .equals(other.actividad))) {
            igual = false;
        }
        return igual;
    }
}
