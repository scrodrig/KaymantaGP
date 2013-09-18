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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase Usuario representa a la tabla gpk_usuario. Persona permitida a usar
 * el sistema.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_usuario", catalog = "kaymantaGP", schema ="")
public class Usuario implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -5092714760176836317L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Usuario, se
     * relaciona con la columna CEDULA.
     */
    @Id
    @Column(name = "CEDULA", nullable = false, length = 10)
    private String codigo;
    /**
     * Es el usuario como con el que se identifica cada usuario.. Representa a
     * la columna USUARIO.
     */
    @Column(name = "USUARIO", nullable = false, length = 20)
    private String usuario;
    /**
     * Es la clave de acceso al sistema y debe ser personal.. Representa a la
     * columna CLAVE.
     */
    @Column(name = "CLAVE", nullable = false, length = 20)
    private String clave;
    /**
     * Fecha de último acceso al sistema.. Representa a la columna
     * FECHA_ULT_ACCESO.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ULT_ACCESO", nullable = false)
    private Date fechaUltAcceso;
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
     * Crea una nueva instancia de la entidad Usuario.
     */
    public Usuario() {
    }

    /**
     * Crea una nueva instancia de la entidad Usuario de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Usuario.
     */
    public Usuario(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Usuario, se relaciona con la columna
     * CEDULA.
     *
     * @return el código de la entidad Usuario
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Usuario, se relaciona con la columna
     * CEDULA.
     *
     * @param codigo el valor a ser asignado al código de la entidad Usuario
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad usuario relacionado con la columna
     * USUARIO.
     *
     * @return el valor asignado a la propiedad usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Asigna un valor a la propiedad usuario, relacionado con la columna
     * USUARIO.
     *
     * @param usuario el valor a ser asignado a la propiedad usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el valor de la propiedad clave relacionado con la columna CLAVE.
     *
     * @return el valor asignado a la propiedad clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Asigna un valor a la propiedad clave, relacionado con la columna CLAVE.
     *
     * @param clave el valor a ser asignado a la propiedad clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el valor de la propiedad fechaUltAcceso relacionado con la
     * columna FECHA_ULT_ACCESO.
     *
     * @return el valor asignado a la propiedad fechaUltAcceso
     */
    public Date getFechaUltAcceso() {
        return fechaUltAcceso;
    }

    /**
     * Asigna un valor a la propiedad fechaUltAcceso, relacionado con la columna
     * FECHA_ULT_ACCESO.
     *
     * @param fechaUltAcceso el valor a ser asignado a la propiedad
     * fechaUltAcceso
     */
    public void setFechaUltAcceso(Date fechaUltAcceso) {
        this.fechaUltAcceso = fechaUltAcceso;
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
        return "Usuario[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Usuario)) {
            igual = false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
