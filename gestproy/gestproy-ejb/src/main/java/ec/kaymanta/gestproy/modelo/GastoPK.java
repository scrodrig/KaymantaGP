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
 * La Clase GastoPK representa a la clave primaria de la tabla gpk_gasto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class GastoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 5309293194874315521L;
    /**
     * Código que identifica un gasto.. Representa a la columna COD_GASTO.
     */
    @Column(name = "COD_GASTO", nullable = false)
    private Long codigoGasto;
    /**
     * Llave foránea que referencia a una actividad o subactividad.. Representa
     * a la columna ACTIVIDAD.
     */
    @Column(name = "ACTIVIDAD", nullable = false)
    private Long actividad;

    /**
     * Crea una nueva instancia de GastoPK.
     */
    public GastoPK() {
    }

    /**
     * Crea una nueva instancia de GastoPK.
     *
     * @param codigoGasto valor a ser asignado a la propiedad codigoGasto
     * @param actividad valor a ser asignado a la propiedad actividad
     */
    public GastoPK(Long codigoGasto, Long actividad) {
        this.codigoGasto = codigoGasto;
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad codigoGasto relacionado con la columna
     * COD_GASTO.
     *
     * @return el valor asignado a la propiedad codigoGasto
     */
    public Long getCodigoGasto() {
        return codigoGasto;
    }

    /**
     * Asigna un valor a la propiedad codigoGasto, relacionado con la columna
     * COD_GASTO.
     *
     * @param codigoGasto el valor a ser asignado a la propiedad codigoGasto
     */
    public void setCodigoGasto(Long codigoGasto) {
        this.codigoGasto = codigoGasto;
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
        hash += codigoGasto != null ? codigoGasto.hashCode() : 0;
        hash += actividad != null ? actividad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GastoPK["
                + " codigoGasto=" + codigoGasto
                + ",actividad=" + actividad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof GastoPK)) {
            igual = false;
        }
        GastoPK other = (GastoPK) object;
        if ((this.codigoGasto == null && other.codigoGasto != null)
                || (this.codigoGasto != null && !this.codigoGasto
                .equals(other.codigoGasto))) {
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
