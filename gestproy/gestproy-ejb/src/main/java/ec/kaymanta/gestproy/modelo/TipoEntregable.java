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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La Clase TipoEntregable representa a la tabla gpk_tipo_entregable. Temática
 * de un entregable.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_tipo_entregable", catalog = "kaymantaGP", schema = "")
public class TipoEntregable implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 7923070916463868289L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * TipoEntregable, se relaciona con la columna COD_TIPO_DOCUMENTO.
     */
    @Id
    @Column(name = "COD_TIPO_DOCUMENTO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Nombre del tipo de entregable.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    /**
     * Descripción del tipo de entregable.. Representa a la columna DESCRIPCION.
     */
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;

    /**
     * Crea una nueva instancia de la entidad TipoEntregable.
     */
    public TipoEntregable() {
    }

    /**
     * Crea una nueva instancia de la entidad TipoEntregable de acuerdo a su
     * clave primaria.
     *
     * @param codigo valor para la clave primaria de la entidad TipoEntregable.
     */
    public TipoEntregable(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el c�digo de la entidad TipoEntregable, se relaciona con la
     * columna COD_TIPO_DOCUMENTO.
     *
     * @return el c�digo de la entidad TipoEntregable
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el c�digo de la entidad TipoEntregable, se relaciona con la
     * columna COD_TIPO_DOCUMENTO.
     *
     * @param codigo el valor a ser asignado al c�digo de la entidad
     * TipoEntregable
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
     * Obtiene el valor de la propiedad descripcion relacionado con la columna
     * DESCRIPCION.
     *
     * @return el valor asignado a la propiedad descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna un valor a la propiedad descripcion, relacionado con la columna
     * DESCRIPCION.
     *
     * @param descripcion el valor a ser asignado a la propiedad descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null) ? codigo.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TipoEntregable[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof TipoEntregable)) {
            igual = false;
        }
        TipoEntregable other = (TipoEntregable) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
