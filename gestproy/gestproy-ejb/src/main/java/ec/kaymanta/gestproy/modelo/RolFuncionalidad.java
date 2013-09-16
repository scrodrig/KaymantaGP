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
 * La Clase RolFuncionalidad representa a la tabla gpk_rol_funcionalidad.
 * Entidad que almacena las funcionalidades que son designados a los diferentes
 * roles.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_rol_funcionalidad", catalog = "kaymantaGP", schema = "")
public class RolFuncionalidad implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -1500335737989230957L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * RolFuncionalidad.
     */
    @EmbeddedId
    private RolFuncionalidadPK pk = new RolFuncionalidadPK();
    /**
     * Fecha en la que se realiza la asignación de la funcionalidad a un rol..
     * Representa a la columna FECHA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    /**
     * Propiedad rol representa una relación de muchos a uno con la Entidad Rol.
     */
    @JoinColumn(name = "ROL", referencedColumnName = "COD_ROL", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Rol rol;
    /**
     * Propiedad funcionalidad representa una relación de muchos a uno con la
     * Entidad Funcionalidad.
     */
    @JoinColumn(name = "FUNCIONALIDAD", referencedColumnName = "COD_FUNCIONALIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionalidad funcionalidad;

    /**
     * Crea una nueva instancia de la entidad RolFuncionalidad.
     */
    public RolFuncionalidad() {
    }

    /**
     * Crea una nueva instancia de la entidad RolFuncionalidad de acuerdo a su
     * clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad RolFuncionalidad.
     */
    public RolFuncionalidad(RolFuncionalidadPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad RolFuncionalidad.
     *
     * @return la clave primaria de la entidad RolFuncionalidad.
     */
    public RolFuncionalidadPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad RolFuncionalidad.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * RolFuncionalidad
     */
    public void setPk(RolFuncionalidadPK pk) {
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
     * Obtiene el valor de la propiedad funcionalidad relacionado con la entidad
     * Funcionalidad.
     *
     * @return el valor asignado a la propiedad funcionalidad
     */
    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    /**
     * Asigna un valor a la propiedad funcionalidad, relacionado con la entidad
     * Funcionalidad.
     *
     * @param funcionalidad el valor a ser asignado a la propiedad funcionalidad
     */
    public void setFuncionalidad(Funcionalidad funcionalidad) {
        this.funcionalidad = funcionalidad;
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
        return "RolFuncionalidad[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof RolFuncionalidad)) {
            igual = false;
        }
        RolFuncionalidad other = (RolFuncionalidad) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
