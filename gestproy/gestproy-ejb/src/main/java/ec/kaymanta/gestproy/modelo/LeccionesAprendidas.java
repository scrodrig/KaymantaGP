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
 * La Clase LeccionesAprendidas representa a la tabla gpk_lecciones_aprendidas.
 * Entidad que almacena las lecciones aprendidas del proyecto, amanera de base
 * de conocimiento.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_lecciones_aprendidas", catalog = "kaymantaGP", schema = "")
public class LeccionesAprendidas implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 4783563448565883481L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * LeccionesAprendidas.
     */
    @EmbeddedId
    private LeccionesAprendidasPK pk = new LeccionesAprendidasPK();
    /**
     * Situación que se presenta a lo largo del proyecto.. Representa a la
     * columna PROBLEMA.
     */
    @Column(name = "PROBLEMA", nullable = false, length = 500)
    private String problema;
    /**
     * Acción correctiva que se toma para mitigar el impacto.. Representa a la
     * columna SOLUCION.
     */
    @Column(name = "SOLUCION", nullable = false, length = 500)
    private String solucion;
    /**
     * Fecha en la que se presenta el inconveniente.. Representa a la columna
     * FPROBLEMA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FPROBLEMA", nullable = false)
    private Date fproblema;
    /**
     * Fecha en la que se soluciona el inconveniente.. Representa a la columna
     * FSOLUCION.
     */
    @Column(name = "FSOLUCION", nullable = false)
    private Long fsolucion;
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
     * Crea una nueva instancia de la entidad LeccionesAprendidas.
     */
    public LeccionesAprendidas() {
    }

    /**
     * Crea una nueva instancia de la entidad LeccionesAprendidas de acuerdo a
     * su clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad LeccionesAprendidas.
     */
    public LeccionesAprendidas(LeccionesAprendidasPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad LeccionesAprendidas.
     *
     * @return la clave primaria de la entidad LeccionesAprendidas.
     */
    public LeccionesAprendidasPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad LeccionesAprendidas.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * LeccionesAprendidas
     */
    public void setPk(LeccionesAprendidasPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad problema relacionado con la columna
     * PROBLEMA.
     *
     * @return el valor asignado a la propiedad problema
     */
    public String getProblema() {
        return problema;
    }

    /**
     * Asigna un valor a la propiedad problema, relacionado con la columna
     * PROBLEMA.
     *
     * @param problema el valor a ser asignado a la propiedad problema
     */
    public void setProblema(String problema) {
        this.problema = problema;
    }

    /**
     * Obtiene el valor de la propiedad solucion relacionado con la columna
     * SOLUCION.
     *
     * @return el valor asignado a la propiedad solucion
     */
    public String getSolucion() {
        return solucion;
    }

    /**
     * Asigna un valor a la propiedad solucion, relacionado con la columna
     * SOLUCION.
     *
     * @param solucion el valor a ser asignado a la propiedad solucion
     */
    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    /**
     * Obtiene el valor de la propiedad fproblema relacionado con la columna
     * FPROBLEMA.
     *
     * @return el valor asignado a la propiedad fproblema
     */
    public Date getFproblema() {
        return fproblema;
    }

    /**
     * Asigna un valor a la propiedad fproblema, relacionado con la columna
     * FPROBLEMA.
     *
     * @param fproblema el valor a ser asignado a la propiedad fproblema
     */
    public void setFproblema(Date fproblema) {
        this.fproblema = fproblema;
    }

    /**
     * Obtiene el valor de la propiedad fsolucion relacionado con la columna
     * FSOLUCION.
     *
     * @return el valor asignado a la propiedad fsolucion
     */
    public Long getFsolucion() {
        return fsolucion;
    }

    /**
     * Asigna un valor a la propiedad fsolucion, relacionado con la columna
     * FSOLUCION.
     *
     * @param fsolucion el valor a ser asignado a la propiedad fsolucion
     */
    public void setFsolucion(Long fsolucion) {
        this.fsolucion = fsolucion;
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
        return "LeccionesAprendidas[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof LeccionesAprendidas)) {
            igual = false;
        }
        LeccionesAprendidas other = (LeccionesAprendidas) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
