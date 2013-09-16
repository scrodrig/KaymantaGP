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
 * La Clase Expectativa representa a la tabla gpk_expectativa. Expectativas
 * preliminares que pueden recolectar un proyecto durante su desarrollo.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_expectativa", catalog = "kaymantaGP", schema = "")
public class Expectativa implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -1106812253372726490L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Expectativa.
     */
    @EmbeddedId
    private ExpectativaPK pk = new ExpectativaPK();
    /**
     * Explicación del requerimiento acerca del proyecto.. Representa a la
     * columna REQUERIMIENTO.
     */
    @Column(name = "REQUERIMIENTO", nullable = false, length = 65535)
    private String requerimiento;
    /**
     * La decisión que se toma sobre dicho requerimiento.. Representa a la
     * columna ACCION.
     */
    @Column(name = "ACCION", nullable = false, length = 65535)
    private String accion;
    /**
     * Estado de la expectativa.. Representa a la columna ESTADO.
     */
    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;
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
     * Propiedad proyecto representa una relaci�n de muchos a uno con la Entidad Proyecto.
     */
    @JoinColumn(name = "PROYECTO", referencedColumnName = "COD_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto proyecto;

    /**
     * Crea una nueva instancia de la entidad Expectativa.
     */
    public Expectativa() {
    }

    /**
     * Crea una nueva instancia de la entidad Expectativa de acuerdo a su clave
     * primaria.
     *
     * @param pk valor para la clave primaria de la entidad Expectativa.
     */
    public Expectativa(ExpectativaPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad Expectativa.
     *
     * @return la clave primaria de la entidad Expectativa.
     */
    public ExpectativaPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad Expectativa.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * Expectativa
     */
    public void setPk(ExpectativaPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad requerimiento relacionado con la columna
     * REQUERIMIENTO.
     *
     * @return el valor asignado a la propiedad requerimiento
     */
    public String getRequerimiento() {
        return requerimiento;
    }

    /**
     * Asigna un valor a la propiedad requerimiento, relacionado con la columna
     * REQUERIMIENTO.
     *
     * @param requerimiento el valor a ser asignado a la propiedad requerimiento
     */
    public void setRequerimiento(String requerimiento) {
        this.requerimiento = requerimiento;
    }

    /**
     * Obtiene el valor de la propiedad accion relacionado con la columna
     * ACCION.
     *
     * @return el valor asignado a la propiedad accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Asigna un valor a la propiedad accion, relacionado con la columna ACCION.
     *
     * @param accion el valor a ser asignado a la propiedad accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Obtiene el valor de la propiedad estado relacionado con la columna
     * ESTADO.
     *
     * @return el valor asignado a la propiedad estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Asigna un valor a la propiedad estado, relacionado con la columna ESTADO.
     *
     * @param estado el valor a ser asignado a la propiedad estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
     * Obtiene el valor de la propiedad proyecto relacionado con la entidad
     * Proyecto.
     *
     * @return el valor asignado a la propiedad proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * Asigna un valor a la propiedad proyecto, relacionado con la entidad
     * Proyecto.
     *
     * @param proyecto el valor a ser asignado a la propiedad proyecto
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        return "Expectativa[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Expectativa)) {
            igual = false;
        }
        Expectativa other = (Expectativa) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
