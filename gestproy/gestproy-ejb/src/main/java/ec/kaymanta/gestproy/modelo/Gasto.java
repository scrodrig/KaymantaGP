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
import java.math.BigDecimal;
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
 * La Clase Gasto representa a la tabla gpk_gasto. Gasto planificado
 * correspondiente a una actividad o subactividad de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_gasto", catalog = "kaymantaGP", schema = "")
public class Gasto implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 7514489263696216302L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Gasto.
     */
    @EmbeddedId
    private GastoPK pk = new GastoPK();
    /**
     * Llave foránea que referencia a que categoría de gasto corresponde..
     * Representa a la columna TIPO_GASTO.
     */
    @Column(name = "TIPO_GASTO", nullable = false)
    private Long codTipoGasto;
    /**
     * Valor monetario del gasto.. Representa a la columna VALOR_PLAN.
     */
    @Column(name = "VALOR_PLAN", nullable = false)
    private BigDecimal valorPlan;
    /**
     * Valor monetario real del gasto.. Representa a la columna VALOR_REAL.
     */
    @Column(name = "VALOR_REAL", nullable = false)
    private BigDecimal valorReal;
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
     * Propiedad tipoGasto representa una relaci�n de muchos a uno con la
     * Entidad TipoGasto.
     */
    @JoinColumn(name = "TIPO_GASTO", referencedColumnName = "COD_TIPO_GASTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoGasto tipoGasto;
    /**
     * Propiedad actividad representa una relaci�n de muchos a uno con la
     * Entidad Actividad.
     */
    @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actividad;

    /**
     * Crea una nueva instancia de la entidad Gasto.
     */
    public Gasto() {
    }

    /**
     * Crea una nueva instancia de la entidad Gasto de acuerdo a su clave
     * primaria.
     *
     * @param pk valor para la clave primaria de la entidad Gasto.
     */
    public Gasto(GastoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad Gasto.
     *
     * @return la clave primaria de la entidad Gasto.
     */
    public GastoPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad Gasto.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad Gasto
     */
    public void setPk(GastoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad tipoGasto relacionado con la columna
     * TIPO_GASTO.
     *
     * @return el valor asignado a la propiedad tipoGasto
     */
    public Long getCodTipoGasto() {
        return codTipoGasto;
    }

    /**
     * Asigna un valor a la propiedad tipoGasto, relacionado con la columna
     * TIPO_GASTO.
     *
     * @param tipoGasto el valor a ser asignado a la propiedad tipoGasto
     */
    public void setCodTipoGasto(Long codTipoGasto) {
        this.codTipoGasto = codTipoGasto;
    }

    /**
     * Obtiene el valor de la propiedad valorPlan relacionado con la columna
     * VALOR_PLAN.
     *
     * @return el valor asignado a la propiedad valorPlan
     */
    public BigDecimal getValorPlan() {
        return valorPlan;
    }

    /**
     * Asigna un valor a la propiedad valorPlan, relacionado con la columna
     * VALOR_PLAN.
     *
     * @param valorPlan el valor a ser asignado a la propiedad valorPlan
     */
    public void setValorPlan(BigDecimal valorPlan) {
        this.valorPlan = valorPlan;
    }

    /**
     * Obtiene el valor de la propiedad valorReal relacionado con la columna
     * VALOR_REAL.
     *
     * @return el valor asignado a la propiedad valorReal
     */
    public BigDecimal getValorReal() {
        return valorReal;
    }

    /**
     * Asigna un valor a la propiedad valorReal, relacionado con la columna
     * VALOR_REAL.
     *
     * @param valorReal el valor a ser asignado a la propiedad valorReal
     */
    public void setValorReal(BigDecimal valorReal) {
        this.valorReal = valorReal;
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
     * Obtiene el valor de la propiedad tipoGasto relacionado con la entidad
     * TipoGasto.
     *
     * @return el valor asignado a la propiedad tipoGasto
     */
    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    /**
     * Asigna un valor a la propiedad tipoGasto, relacionado con la entidad
     * TipoGasto.
     *
     * @param tipoGasto el valor a ser asignado a la propiedad tipoGasto
     */
    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    /**
     * Obtiene el valor de la propiedad actividad relacionado con la entidad
     * Actividad.
     *
     * @return el valor asignado a la propiedad actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Asigna un valor a la propiedad actividad, relacionado con la entidad
     * Actividad.
     *
     * @param actividad el valor a ser asignado a la propiedad actividad
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
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
        return "Gasto[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Gasto)) {
            igual = false;
        }
        Gasto other = (Gasto) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
