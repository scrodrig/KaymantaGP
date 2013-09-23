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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase UsuarioRol representa a la tabla gpk_usuario_rol. Entidad que
 * almacena que roles son asignados a un usuario.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_USUARIO_ROL", catalog = "kaymantaGP", schema = "")
public class UsuarioRol implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -3058309829142751754L;
    /**
     * Propiedad que representa a la clave primaria de la entidad UsuarioRol.
     */
    @EmbeddedId
    private UsuarioRolPK pk = new UsuarioRolPK();
    /**
     * Fecha en la que se realiza la asignación el rol al usuario.. Representa a
     * la columna FECHA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    /**
     * Propiedad usuario representa una relación de muchos a uno con la Entidad Usuario.
     */
    @JoinColumn(name = "USUARIO", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    /**
     * Propiedad rol representa una relación de muchos a uno con la Entidad Rol.
     */
    @JoinColumn(name = "ROL", referencedColumnName = "COD_ROL", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Rol rol;

    /**
     * Crea una nueva instancia de la entidad UsuarioRol.
     */
    public UsuarioRol() {
    }

    /**
     * Crea una nueva instancia de la entidad UsuarioRol de acuerdo a su clave
     * primaria.
     *
     * @param pk valor para la clave primaria de la entidad UsuarioRol.
     */
    public UsuarioRol(UsuarioRolPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad UsuarioRol.
     *
     * @return la clave primaria de la entidad UsuarioRol.
     */
    public UsuarioRolPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad UsuarioRol.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * UsuarioRol
     */
    public void setPk(UsuarioRolPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad fecha relacionado con la columna FECHA.
     *
     * @return el valor asignado a la propiedad fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Asigna un valor a la propiedad fecha, relacionado con la columna FECHA.
     *
     * @param fecha el valor a ser asignado a la propiedad fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el valor de la propiedad usuario relacionado con la entidad
     * Usuario.
     *
     * @return el valor asignado a la propiedad usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Asigna un valor a la propiedad usuario, relacionado con la entidad
     * Usuario.
     *
     * @param usuario el valor a ser asignado a la propiedad usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el valor de la propiedad rol relacionado con la entidad Rol.
     *
     * @return el valor asignado a la propiedad rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Asigna un valor a la propiedad rol, relacionado con la entidad Rol.
     *
     * @param rol el valor a ser asignado a la propiedad rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
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
        return "UsuarioRol[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof UsuarioRol)) {
            igual = false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
