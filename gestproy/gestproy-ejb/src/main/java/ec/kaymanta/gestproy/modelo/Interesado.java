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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase Interesado representa a la tabla gpk_interesado. Persona
 * perteneciente a la empresa contratante de servicios.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_interesado", catalog = "kaymantaGP", schema = "")
public class Interesado implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -802614739615822362L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Interesado, se
     * relaciona con la columna COD_INTERESADO.
     */
    @Id
    @Column(name = "COD_INTERESADO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Empresa que realiza la contratación del proyecto.. Representa a la
     * columna EMPRESA.
     */
//    @Column(name = "EMPRESA", nullable = false, length = 13)
//    private String empresa;
    /**
     * Nombre de la persona de interés para un proyecto.. Representa a la
     * columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    /**
     * Teléfono del interesado.. Representa a la columna TELEFONO.
     */
    @Column(name = "TELEFONO", nullable = false, length = 10)
    private String telefono;
    /**
     * Email del interesado.. Representa a la columna EMAIL.
     */
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
    /**
     * Notas acerca del interesado.. Representa a la columna NOTAS.
     */
    @Column(name = "NOTAS", nullable = false, length = 65535)
    private String notas;
    /**
     * Usuario que crea el registro.. Representa a la columna USR_CREACION.
     */
    @Column(name = "USR_CREACION", nullable = false, length = 20)
    private String usrCreacion;
    /**
     * Fecha en la que el registro es creado.. Representa a la columna
     * FCREACION.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FCREACION", nullable = false)
    private Date fcreacion;
    /**
     * Último usuario que modifica el registro.. Representa a la columna
     * USR_MODIFICACION.
     */
    @Column(name = "USR_MODIFICACION", nullable = false, length = 20)
    private String usrModificacion;
    /**
     * Fecha en la que se modifica el registro.. Representa a la columna
     * FMODIFICACION.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FMODIFICACION", nullable = false)
    private Date fmodificacion;
    /**
     * Propiedad empresa representa una relación de muchos a uno con la Entidad Empresa.
     */
    @JoinColumn(name = "EMPRESA", referencedColumnName = "RUC", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;

    /**
     * Crea una nueva instancia de la entidad Interesado.
     */
    public Interesado() {
    }

    /**
     * Crea una nueva instancia de la entidad Interesado de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Interesado.
     */
    public Interesado(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Interesado, se relaciona con la columna
     * COD_INTERESADO.
     *
     * @return el código de la entidad Interesado
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Interesado, se relaciona con la columna
     * COD_INTERESADO.
     *
     * @param codigo el valor a ser asignado al código de la entidad Interesado
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad empresa relacionado con la columna
     * EMPRESA.
     *
     * @return el valor asignado a la propiedad empresa
     */
//    public String getEmpresa() {
//        return empresa;
//    }

    /**
     * Asigna un valor a la propiedad empresa, relacionado con la columna
     * EMPRESA.
     *
     * @param empresa el valor a ser asignado a la propiedad empresa
     */
//    public void setEmpresa(String empresa) {
//        this.empresa = empresa;
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
     * Obtiene el valor de la propiedad telefono relacionado con la columna
     * TELEFONO.
     *
     * @return el valor asignado a la propiedad telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna un valor a la propiedad telefono, relacionado con la columna
     * TELEFONO.
     *
     * @param telefono el valor a ser asignado a la propiedad telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el valor de la propiedad email relacionado con la columna EMAIL.
     *
     * @return el valor asignado a la propiedad email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna un valor a la propiedad email, relacionado con la columna EMAIL.
     *
     * @param email el valor a ser asignado a la propiedad email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el valor de la propiedad notas relacionado con la columna NOTAS.
     *
     * @return el valor asignado a la propiedad notas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Asigna un valor a la propiedad notas, relacionado con la columna NOTAS.
     *
     * @param notas el valor a ser asignado a la propiedad notas
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene el valor de la propiedad usrCreacion relacionado con la columna
     * USR_CREACION.
     *
     * @return el valor asignado a la propiedad usrCreacion
     */
    public String getUsrCreacion() {
        return usrCreacion;
    }

    /**
     * Asigna un valor a la propiedad usrCreacion, relacionado con la columna
     * USR_CREACION.
     *
     * @param usrCreacion el valor a ser asignado a la propiedad usrCreacion
     */
    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    /**
     * Obtiene el valor de la propiedad fcreacion relacionado con la columna
     * FCREACION.
     *
     * @return el valor asignado a la propiedad fcreacion
     */
    public Date getFcreacion() {
        return fcreacion;
    }

    /**
     * Asigna un valor a la propiedad fcreacion, relacionado con la columna
     * FCREACION.
     *
     * @param fcreacion el valor a ser asignado a la propiedad fcreacion
     */
    public void setFcreacion(Date fcreacion) {
        this.fcreacion = fcreacion;
    }

    /**
     * Obtiene el valor de la propiedad usrModificacion relacionado con la
     * columna USR_MODIFICACION.
     *
     * @return el valor asignado a la propiedad usrModificacion
     */
    public String getUsrModificacion() {
        return usrModificacion;
    }

    /**
     * Asigna un valor a la propiedad usrModificacion, relacionado con la
     * columna USR_MODIFICACION.
     *
     * @param usrModificacion el valor a ser asignado a la propiedad
     * usrModificacion
     */
    public void setUsrModificacion(String usrModificacion) {
        this.usrModificacion = usrModificacion;
    }

    /**
     * Obtiene el valor de la propiedad fmodificacion relacionado con la columna
     * FMODIFICACION.
     *
     * @return el valor asignado a la propiedad fmodificacion
     */
    public Date getFmodificacion() {
        return fmodificacion;
    }

    /**
     * Asigna un valor a la propiedad fmodificacion, relacionado con la columna
     * FMODIFICACION.
     *
     * @param fmodificacion el valor a ser asignado a la propiedad fmodificacion
     */
    public void setFmodificacion(Date fmodificacion) {
        this.fmodificacion = fmodificacion;
    }

    /**
     * Obtiene el valor de la propiedad empresa relacionado con la entidad
     * Empresa.
     *
     * @return el valor asignado a la propiedad empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Asigna un valor a la propiedad empresa, relacionado con la entidad
     * Empresa.
     *
     * @param empresa el valor a ser asignado a la propiedad empresa
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        return "Interesado[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Interesado)) {
            igual = false;
        }
        Interesado other = (Interesado) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
