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
 * La Clase ActividadEntregablePK representa a la clave primaria de la tabla
 * gpk_actividad_entregable.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class ActividadEntregablePK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 5505235612365452029L;
    /**
     * Código que identifica a cada entregable.. Representa a la columna
     * COD_ACTIVIDAD_ENTREGABLE.
     */
    @Column(name = "COD_ACTIVIDAD_ENTREGABLE", nullable = false)
    private Long codigoActividadEntregable;
    /**
     * Llave foránea que referencia a una actividad o subactividad del
     * desarrollo de un proyecto.. Representa a la columna ACTIVIDAD.
     */
    @Column(name = "ACTIVIDAD", nullable = false)
    private Long actividad;

    /**
     * Crea una nueva instancia de ActividadEntregablePK.
     */
    public ActividadEntregablePK() {
    }

    /**
     * Crea una nueva instancia de ActividadEntregablePK.
     *
     * @param codigoActividadEntregable valor a ser asignado a la propiedad
     * codigoActividadEntregable
     * @param actividad valor a ser asignado a la propiedad actividad
     */
    public ActividadEntregablePK(Long codigoActividadEntregable, Long actividad) {
        this.codigoActividadEntregable = codigoActividadEntregable;
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad codigoActividadEntregable relacionado
     * con la columna COD_ACTIVIDAD_ENTREGABLE.
     *
     * @return el valor asignado a la propiedad codigoActividadEntregable
     */
    public Long getCodigoActividadEntregable() {
        return codigoActividadEntregable;
    }

    /**
     * Asigna un valor a la propiedad codigoActividadEntregable, relacionado con
     * la columna COD_ACTIVIDAD_ENTREGABLE.
     *
     * @param codigoActividadEntregable el valor a ser asignado a la propiedad
     * codigoActividadEntregable
     */
    public void setCodigoActividadEntregable(Long codigoActividadEntregable) {
        this.codigoActividadEntregable = codigoActividadEntregable;
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
        hash += codigoActividadEntregable != null ? codigoActividadEntregable.hashCode() : 0;
        hash += actividad != null ? actividad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ActividadEntregablePK["
                + " codigoActividadEntregable=" + codigoActividadEntregable
                + ",actividad=" + actividad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadEntregablePK)) {
            igual = false;
        }
        ActividadEntregablePK other = (ActividadEntregablePK) object;
        if ((this.codigoActividadEntregable == null && other.codigoActividadEntregable != null)
                || (this.codigoActividadEntregable != null && !this.codigoActividadEntregable
                .equals(other.codigoActividadEntregable))) {
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
