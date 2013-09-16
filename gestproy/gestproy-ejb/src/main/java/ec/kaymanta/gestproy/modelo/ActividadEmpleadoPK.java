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
 * La Clase ActividadEmpleadoPK representa a la clave primaria de la tabla
 * gpk_actividad_empleado.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class ActividadEmpleadoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -2995599745428045137L;
    /**
     * Llave foránea que referencia a un empleado como responsable de una
     * actividad o subactividad.. Representa a la columna RESPONSABLE.
     */
    @Column(name = "RESPONSABLE", nullable = false, length = 10)
    private String responsable;
    /**
     * Llave foránea que referencia a una actividad o subactividad.. Representa
     * a la columna ACTIVIDAD.
     */
    @Column(name = "ACTIVIDAD", nullable = false)
    private Long actividad;

    /**
     * Crea una nueva instancia de ActividadEmpleadoPK.
     */
    public ActividadEmpleadoPK() {
    }

    /**
     * Crea una nueva instancia de ActividadEmpleadoPK.
     *
     * @param responsable valor a ser asignado a la propiedad responsable
     * @param actividad valor a ser asignado a la propiedad actividad
     */
    public ActividadEmpleadoPK(String responsable, Long actividad) {
        this.responsable = responsable;
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad responsable relacionado con la columna
     * RESPONSABLE.
     *
     * @return el valor asignado a la propiedad responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Asigna un valor a la propiedad responsable, relacionado con la columna
     * RESPONSABLE.
     *
     * @param responsable el valor a ser asignado a la propiedad responsable
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
        hash += responsable != null ? responsable.hashCode() : 0;
        hash += actividad != null ? actividad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ActividadEmpleadoPK["
                + " responsable=" + responsable
                + ",actividad=" + actividad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadEmpleadoPK)) {
            igual = false;
        }
        ActividadEmpleadoPK other = (ActividadEmpleadoPK) object;
        if ((this.responsable == null && other.responsable != null)
                || (this.responsable != null && !this.responsable
                .equals(other.responsable))) {
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
