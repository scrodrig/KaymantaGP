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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase InstitucionControl representa a la tabla gpk_institucion_control.
 * Institución que rige las normas y controles estándar de los diferentes
 * proyectos.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_INSTITUCION_CONTROL", catalog = "kaymantaGP", schema = "")
public class InstitucionControl implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 7289683786842321968L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * InstitucionControl, se relaciona con la columna COD_INSTITUCION_CONTROL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_INSTITUCION_CONTROL", nullable = false, length = 10)
    private Long codigo;
    /**
     * Nombre de la institución de control.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    /**
     * Dirección de la institución de control.. Representa a la columna
     * DIRECCION.
     */
    @Column(name = "DIRECCION", nullable = false, length = 100)
    private String direccion;
    /**
     * Teléfono de la institución de control.. Representa a la columna TELEFONO.
     */
    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;
    /**
     * Persona que servirá de contacto en la institución de control.. Representa
     * a la columna CONTACTO.
     */
    @Column(name = "CONTACTO", nullable = false, length = 100)
    private String contacto;
    /**
     * Email de la institución de control.. Representa a la columna EMAIL.
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
     * Crea una nueva instancia de la entidad InstitucionControl.
     */
    public InstitucionControl() {
    }

    /**
     * Crea una nueva instancia de la entidad InstitucionControl de acuerdo a su
     * clave primaria.
     *
     * @param codigo valor para la clave primaria de la entidad
     * InstitucionControl.
     */
    public InstitucionControl(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el ccódigodigo de la entidad InstitucionControl, se relaciona con
     * la columna COD_INSTITUCION_CONTROL.
     *
     * @return el ccódigodigo de la entidad InstitucionControl
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el ccódigodigo de la entidad InstitucionControl, se relaciona con
     * la columna COD_INSTITUCION_CONTROL.
     *
     * @param codigo el valor a ser asignado al ccódigodigo de la entidad
     * InstitucionControl
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
     * Obtiene el valor de la propiedad direccion relacionado con la columna
     * DIRECCION.
     *
     * @return el valor asignado a la propiedad direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna un valor a la propiedad direccion, relacionado con la columna
     * DIRECCION.
     *
     * @param direccion el valor a ser asignado a la propiedad direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
     * Obtiene el valor de la propiedad contacto relacionado con la columna
     * CONTACTO.
     *
     * @return el valor asignado a la propiedad contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Asigna un valor a la propiedad contacto, relacionado con la columna
     * CONTACTO.
     *
     * @param contacto el valor a ser asignado a la propiedad contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
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
        return "InstitucionControl[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof InstitucionControl)) {
            igual = false;
        }
        InstitucionControl other = (InstitucionControl) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
