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
 * La Clase ParroquiaPK representa a la clave primaria de la tabla
 * gpk_parroquia.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class ParroquiaPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 5638792578674120415L;
    /**
     * Código que identifica a una parroquia.. Representa a la columna
     * COD_PARROQUIA.
     */
    @Column(name = "COD_PARROQUIA", nullable = false)
    private Long codigoParroquia;
    /**
     * Llave foránea al cantón al que pertenece la parroquia.. Representa a la
     * columna CANTON.
     */
    @Column(name = "CANTON", nullable = false)
    private Long canton;
    /**
     * Llave foránea a la provincia a la cual pertenece la parroquia..
     * Representa a la columna PROVINCIA.
     */
    @Column(name = "PROVINCIA", nullable = false)
    private Long provincia;

    /**
     * Crea una nueva instancia de ParroquiaPK.
     */
    public ParroquiaPK() {
    }

    /**
     * Crea una nueva instancia de ParroquiaPK.
     *
     * @param codigoParroquia valor a ser asignado a la propiedad
     * codigoParroquia
     * @param canton valor a ser asignado a la propiedad canton
     * @param provincia valor a ser asignado a la propiedad provincia
     */
    public ParroquiaPK(Long codigoParroquia, Long canton, Long provincia) {
        this.codigoParroquia = codigoParroquia;
        this.canton = canton;
        this.provincia = provincia;
    }

    /**
     * Obtiene el valor de la propiedad codigoParroquia relacionado con la
     * columna COD_PARROQUIA.
     *
     * @return el valor asignado a la propiedad codigoParroquia
     */
    public Long getCodigoParroquia() {
        return codigoParroquia;
    }

    /**
     * Asigna un valor a la propiedad codigoParroquia, relacionado con la
     * columna COD_PARROQUIA.
     *
     * @param codigoParroquia el valor a ser asignado a la propiedad
     * codigoParroquia
     */
    public void setCodigoParroquia(Long codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    /**
     * Obtiene el valor de la propiedad canton relacionado con la columna
     * CANTON.
     *
     * @return el valor asignado a la propiedad canton
     */
    public Long getCanton() {
        return canton;
    }

    /**
     * Asigna un valor a la propiedad canton, relacionado con la columna CANTON.
     *
     * @param canton el valor a ser asignado a la propiedad canton
     */
    public void setCanton(Long canton) {
        this.canton = canton;
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
        hash += codigoParroquia != null ? codigoParroquia.hashCode() : 0;
        hash += canton != null ? canton.hashCode() : 0;
        hash += provincia != null ? provincia.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ParroquiaPK["
                + " codigoParroquia=" + codigoParroquia
                + ",canton=" + canton
                + ",provincia=" + provincia
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ParroquiaPK)) {
            igual = false;
        }
        ParroquiaPK other = (ParroquiaPK) object;
        if ((this.codigoParroquia == null && other.codigoParroquia != null)
                || (this.codigoParroquia != null && !this.codigoParroquia
                .equals(other.codigoParroquia))) {
            igual = false;
        }
        if ((this.canton == null && other.canton != null)
                || (this.canton != null && !this.canton
                .equals(other.canton))) {
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
