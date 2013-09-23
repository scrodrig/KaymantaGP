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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * La Clase Canton representa a la tabla gpk_canton. Cantón en el que tiene
 * lugar el desarrollo de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_CANTON", catalog = "kaymantaGP", schema = "")
public class Canton implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 552948779292258245L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Canton.
     */
    @EmbeddedId
    private CantonPK pk = new CantonPK();
    /**
     * Nombre del cantón.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    /**
     * Propiedad provincia representa una relación de muchos a uno con la
     * Entidad Provincia.
     */
    @JoinColumn(name = "PROVINCIA", referencedColumnName = "COD_PROVINCIA", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Provincia provincia;

    /**
     * Crea una nueva instancia de la entidad Canton.
     */
    public Canton() {
    }

    /**
     * Crea una nueva instancia de la entidad Canton de acuerdo a su clave
     * primaria.
     *
     * @param pk valor para la clave primaria de la entidad Canton.
     */
    public Canton(CantonPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad Canton.
     *
     * @return la clave primaria de la entidad Canton.
     */
    public CantonPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad Canton.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * Canton
     */
    public void setPk(CantonPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad nombre relacionado con la columna
     * NOMBRE.
     *
     * @return el valor asignado a la propiedad nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un valor a la propiedad nombre, relacionado con la columna NOMBRE.
     *
     * @param nombre el valor a ser asignado a la propiedad nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor de la propiedad provincia relacionado con la entidad
     * Provincia.
     *
     * @return el valor asignado a la propiedad provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Asigna un valor a la propiedad provincia, relacionado con la entidad
     * Provincia.
     *
     * @param provincia el valor a ser asignado a la propiedad provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null) ? pk.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Canton[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Canton)) {
            igual = false;
        }
        Canton other = (Canton) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
