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
 * La Clase CantonPK representa a la clave primaria de la tabla gpk_canton.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class CantonPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -5680080305772174911L;
    /**
     * Código que identifica a un cantón. Representa a la columna COD_CANTON.
     */
    @Column(name = "COD_CANTON", nullable = false)
    private Long codigoCanton;
    /**
     * Llave foránea a la provincia a la cual pertenece un cantón.. Representa a
     * la columna PROVINCIA.
     */
    @Column(name = "PROVINCIA", nullable = false)
    private Long provincia;

    /**
     * Crea una nueva instancia de CantonPK.
     */
    public CantonPK() {
    }

    /**
     * Crea una nueva instancia de CantonPK.
     *
     * @param codigoCanton valor a ser asignado a la propiedad codigoCanton
     * @param provincia valor a ser asignado a la propiedad provincia
     */
    public CantonPK(Long codigoCanton, Long provincia) {
        this.codigoCanton = codigoCanton;
        this.provincia = provincia;
    }

    /**
     * Obtiene el valor de la propiedad codigoCanton relacionado con la columna
     * COD_CANTON.
     *
     * @return el valor asignado a la propiedad codigoCanton
     */
    public Long getCodigoCanton() {
        return codigoCanton;
    }

    /**
     * Asigna un valor a la propiedad codigoCanton, relacionado con la columna
     * COD_CANTON.
     *
     * @param codigoCanton el valor a ser asignado a la propiedad codigoCanton
     */
    public void setCodigoCanton(Long codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    /**
     * Obtiene el valor de la propiedad provincia relacionado con la columna
     * PROVINCIA.
     *
     * @return el valor asignado a la propiedad provincia
     */
    public Long getProvincia() {
        return provincia;
    }

    /**
     * Asigna un valor a la propiedad provincia, relacionado con la columna
     * PROVINCIA.
     *
     * @param provincia el valor a ser asignado a la propiedad provincia
     */
    public void setProvincia(Long provincia) {
        this.provincia = provincia;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += codigoCanton != null ? codigoCanton.hashCode() : 0;
        hash += provincia != null ? provincia.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CantonPK["
                + " codigoCanton=" + codigoCanton
                + ",provincia=" + provincia
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof CantonPK)) {
            igual = false;
        }
        CantonPK other = (CantonPK) object;
        if ((this.codigoCanton == null && other.codigoCanton != null)
                || (this.codigoCanton != null && !this.codigoCanton
                .equals(other.codigoCanton))) {
            igual = false;
        }
        if ((this.provincia == null && other.provincia != null)
                || (this.provincia != null && !this.provincia
                .equals(other.provincia))) {
            igual = false;
        }
        return igual;
    }
}
