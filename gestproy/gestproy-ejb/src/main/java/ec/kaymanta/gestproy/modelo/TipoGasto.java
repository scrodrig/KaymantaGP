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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La Clase TipoGasto representa a la tabla gpk_tipo_gasto. Tipo de gasto que se
 * define durante el desarrollo de uns actividad o subactividad de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_TIPO_GASTO", catalog = "kaymantaGP", schema = "")
public class TipoGasto implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -244694692272021114L;
    /**
     * Propiedad que representa a la clave primaria de la entidad TipoGasto, se
     * relaciona con la columna COD_TIPO_GASTO.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TIPO_GASTO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Nombre del tipo de gasto.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 40)
    private String nombre;
    /**
     * Descripción del tipo de gasto.. Representa a la columna DESCRIPCION.
     */
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;

    /**
     * Crea una nueva instancia de la entidad TipoGasto.
     */
    public TipoGasto() {
    }

    /**
     * Crea una nueva instancia de la entidad TipoGasto de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad TipoGasto.
     */
    public TipoGasto(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad TipoGasto, se relaciona con la columna
     * COD_TIPO_GASTO.
     *
     * @return el código de la entidad TipoGasto
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad TipoGasto, se relaciona con la columna
     * COD_TIPO_GASTO.
     *
     * @param codigo el valor a ser asignado al código de la entidad TipoGasto
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
        return "TipoGasto[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof TipoGasto)) {
            igual = false;
        }
        TipoGasto other = (TipoGasto) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
