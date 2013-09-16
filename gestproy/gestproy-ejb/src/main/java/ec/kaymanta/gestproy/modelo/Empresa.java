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
 * La Clase Empresa representa a la tabla gpk_empresa. Organización contratante
 * de servicios.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_empresa", catalog = "kaymantaGP", schema = "")
public class Empresa implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -2796149886897701144L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Empresa, se
     * relaciona con la columna RUC.
     */
    @Id
    @Column(name = "RUC", nullable = false, length = 13)
    private String codigo;
    /**
     * Razón social de la empresa.. Representa a la columna RAZON_SOCIAL.
     */
    @Column(name = "RAZON_SOCIAL", nullable = false, length = 100)
    private String razonSocial;
    /**
     * Teléfono de la empresa.. Representa a la columna TELEFONO.
     */
    @Column(name = "TELEFONO", nullable = false, length = 10)
    private String telefono;
    /**
     * Website de la empresa.. Representa a la columna WEBSITE.
     */
    @Column(name = "WEBSITE", nullable = false, length = 100)
    private String website;
    /**
     * Email de la empresa.. Representa a la columna EMAIL.
     */
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
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
     * Crea una nueva instancia de la entidad Empresa.
     */
    public Empresa() {
    }

    /**
     * Crea una nueva instancia de la entidad Empresa de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Empresa.
     */
    public Empresa(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el c�digo de la entidad Empresa, se relaciona con la columna RUC.
     *
     * @return el c�digo de la entidad Empresa
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Asigna el c�digo de la entidad Empresa, se relaciona con la columna RUC.
     *
     * @param codigo el valor a ser asignado al c�digo de la entidad Empresa
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial relacionado con la columna
     * RAZON_SOCIAL.
     *
     * @return el valor asignado a la propiedad razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Asigna un valor a la propiedad razonSocial, relacionado con la columna
     * RAZON_SOCIAL.
     *
     * @param razonSocial el valor a ser asignado a la propiedad razonSocial
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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
     * Obtiene el valor de la propiedad website relacionado con la columna
     * WEBSITE.
     *
     * @return el valor asignado a la propiedad website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Asigna un valor a la propiedad website, relacionado con la columna
     * WEBSITE.
     *
     * @param website el valor a ser asignado a la propiedad website
     */
    public void setWebsite(String website) {
        this.website = website;
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
        return "Empresa[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Empresa)) {
            igual = false;
        }
        Empresa other = (Empresa) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
