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
 * La Clase Modulo representa a la tabla gpk_modulo. Principales divisiones del
 * sistema.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_modulo", catalog = "kaymantaGP", schema = "")
public class Modulo implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 6600108489896703301L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Modulo, se
     * relaciona con la columna COD_MODULO.
     */
    @Id
    @Column(name = "COD_MODULO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Nombre del módulo.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    /**
     * Crea una nueva instancia de la entidad Modulo.
     */
    public Modulo() {
    }

    /**
     * Crea una nueva instancia de la entidad Modulo de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Modulo.
     */
    public Modulo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el c�digo de la entidad Modulo, se relaciona con la columna
     * COD_MODULO.
     *
     * @return el c�digo de la entidad Modulo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el c�digo de la entidad Modulo, se relaciona con la columna
     * COD_MODULO.
     *
     * @param codigo el valor a ser asignado al c�digo de la entidad Modulo
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
        return "Modulo[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Modulo)) {
            igual = false;
        }
        Modulo other = (Modulo) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
