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
 * La Clase RiesgoPK representa a la clave primaria de la tabla gpk_riesgo.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class RiesgoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 6346880334067379922L;
    /**
     * Llave foránea que referencia a un proyecto.. Representa a la columna
     * PROYECTO.
     */
    @Column(name = "PROYECTO", nullable = false)
    private Long proyecto;
    /**
     * .
     * Representa a la columna COD_RIESGO.
     */
    @Column(name = "COD_RIESGO", nullable = false)
    private Long codigoRiesgo;

    /**
     * Crea una nueva instancia de RiesgoPK.
     */
    public RiesgoPK() {
    }

    /**
     * Crea una nueva instancia de RiesgoPK.
     *
     * @param proyecto valor a ser asignado a la propiedad proyecto
     * @param codigoRiesgo valor a ser asignado a la propiedad codigoRiesgo
     */
    public RiesgoPK(Long proyecto, Long codigoRiesgo) {
        this.proyecto = proyecto;
        this.codigoRiesgo = codigoRiesgo;
    }

    /**
     * Obtiene el valor de la propiedad proyecto relacionado con la columna
     * PROYECTO.
     *
     * @return el valor asignado a la propiedad proyecto
     */
    public Long getProyecto() {
        return proyecto;
    }

    /**
     * Asigna un valor a la propiedad proyecto, relacionado con la columna
     * PROYECTO.
     *
     * @param proyecto el valor a ser asignado a la propiedad proyecto
     */
    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * Obtiene el valor de la propiedad codigoRiesgo relacionado con la columna
     * COD_RIESGO.
     *
     * @return el valor asignado a la propiedad codigoRiesgo
     */
    public Long getCodigoRiesgo() {
        return codigoRiesgo;
    }

    /**
     * Asigna un valor a la propiedad codigoRiesgo, relacionado con la columna
     * COD_RIESGO.
     *
     * @param codigoRiesgo el valor a ser asignado a la propiedad codigoRiesgo
     */
    public void setCodigoRiesgo(Long codigoRiesgo) {
        this.codigoRiesgo = codigoRiesgo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += proyecto != null ? proyecto.hashCode() : 0;
        hash += codigoRiesgo != null ? codigoRiesgo.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RiesgoPK["
                + " proyecto=" + proyecto
                + ",codigoRiesgo=" + codigoRiesgo
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof RiesgoPK)) {
            igual = false;
        }
        RiesgoPK other = (RiesgoPK) object;
        if ((this.proyecto == null && other.proyecto != null)
                || (this.proyecto != null && !this.proyecto
                .equals(other.proyecto))) {
            igual = false;
        }
        if ((this.codigoRiesgo == null && other.codigoRiesgo != null)
                || (this.codigoRiesgo != null && !this.codigoRiesgo
                .equals(other.codigoRiesgo))) {
            igual = false;
        }
        return igual;
    }
}
