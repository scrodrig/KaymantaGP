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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * La Clase Funcionalidad representa a la tabla gpk_funcionalidad. FUNCIONALIDAD
 * DEL SISTEMA
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_funcionalidad", catalog = "kaymantaGP", schema = "")
public class Funcionalidad implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 6196318989406266750L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Funcionalidad,
     * se relaciona con la columna COD_FUNCIONALIDAD.
     */
    @Id
    @Column(name = "COD_FUNCIONALIDAD", nullable = false, length = 10)
    private Long codigo;
    /**
     * Llave foránea que referencia al modulo sobre el cual existe la
     * funcionalidad.. Representa a la columna MODULO.
     */
//    @Column(name = "MODULO", nullable = false)
//    private Long modulo;
    /**
     * Nombre de la funcionalidad.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    /**
     * Descripción de la funcionalidad. Representa a la columna DESCRIPCION.
     */
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    /**
     * Propiedad modulo representa una relación de muchos a uno con la Entidad Modulo.
     */
    @JoinColumn(name = "MODULO", referencedColumnName = "COD_MODULO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Modulo modulo;

    /**
     * Crea una nueva instancia de la entidad Funcionalidad.
     */
    public Funcionalidad() {
    }

    /**
     * Crea una nueva instancia de la entidad Funcionalidad de acuerdo a su
     * clave primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Funcionalidad.
     */
    public Funcionalidad(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Funcionalidad, se relaciona con la
     * columna COD_FUNCIONALIDAD.
     *
     * @return el código de la entidad Funcionalidad
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Funcionalidad, se relaciona con la columna
     * COD_FUNCIONALIDAD.
     *
     * @param codigo el valor a ser asignado al código de la entidad
     * Funcionalidad
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad modulo relacionado con la columna
     * MODULO.
     *
     * @return el valor asignado a la propiedad modulo
     */
//    public Long getModulo() {
//        return modulo;
//    }

    /**
     * Asigna un valor a la propiedad modulo, relacionado con la columna MODULO.
     *
     * @param modulo el valor a ser asignado a la propiedad modulo
     */
//    public void setModulo(Long modulo) {
//        this.modulo = modulo;
//    }

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
     * Obtiene el valor de la propiedad modulo relacionado con la entidad
     * Modulo.
     *
     * @return el valor asignado a la propiedad modulo
     */
    public Modulo getModulo() {
        return modulo;
    }

    /**
     * Asigna un valor a la propiedad modulo, relacionado con la entidad Modulo.
     *
     * @param modulo el valor a ser asignado a la propiedad modulo
     */
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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
        return "Funcionalidad[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Funcionalidad)) {
            igual = false;
        }
        Funcionalidad other = (Funcionalidad) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
