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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase Proyecto representa a la tabla gpk_proyecto. Conjunto de actividades
 * para conseguir un objetivo alineado con los requerimientos de la empresa.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_PROYECTO", catalog = "kaymantaGP", schema = "")
public class Proyecto implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 4955446856113747763L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Proyecto, se
     * relaciona con la columna COD_PROYECTO.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PROYECTO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Llave foránea que referencia a un empleado responsable del proyecto..
     * Representa a la columna RESPONSABLE.
     */
    @Column(name = "RESPONSABLE", nullable = false, length = 10)
    private String responsable;
    /**
     * Llave foránea que referencia a la empresa contratante del proyecto..
     * Representa a la columna EMPRESA.
     */
    @Column(name = "EMPRESA", nullable = false, length = 13)
    private String codEmpresa;
    /**
     * Llave foránea que referencia a una provincia.. Representa a la columna
     * PROVINCIA.
     */
    @Column(name = "PROVINCIA", nullable = false)
    private Long codProvincia;
    /**
     * Llave foránea que referencia a un cantón.. Representa a la columna
     * CANTON.
     */
    @Column(name = "CANTON", nullable = false)
    private Long codCanton;
    /**
     * llave foránea que referencia a una parroquia. Representa a la columna
     * PARROQUIA.
     */
    @Column(name = "PARROQUIA", nullable = false)
    private Long codParroquia;
    /**
     * Nombre del proyecto.. Representa a la columna NOMBRE_PROYECTO.
     */
    @Column(name = "NOMBRE_PROYECTO", nullable = false, length = 100)
    private String nombreProyecto;
    /**
     * Fecha inicial del proyecto.. Representa a la columna FINICIO.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FINICIO", nullable = false)
    private Date finicio;
    /**
     * Fecha estimada para la finalización del proyecto.. Representa a la
     * columna FESTIMADA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FESTIMADA", nullable = false)
    private Date festimada;
    /**
     * Fecha real de finalización del proyecto.. Representa a la columna FFIN.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FFIN", nullable = false)
    private Date ffin;
    /**
     * Avance porcentual del avance del proyecto.. Representa a la columna
     * AVANCE.
     */
    @Column(name = "AVANCE", nullable = false)
    private BigDecimal avance;
    /**
     * Horas Diarias Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_DIA_EST", nullable = false)
    private Long hDiaEst;
    
    /**
     * Horas Diarias Reales.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_DIA_REAL", nullable = false)
    private Long hDiaReal;
    
    /**
     * Horas Trabajadas Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_TRAB_EST", nullable = false)
    private BigDecimal hTrabEst;
    
    /**
     * Horas Trabajadas Real.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_TRAB_REAL", nullable = false)
    private BigDecimal hTrabReal;
    
    /**
     * Trabajo total Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "TRABAJO_TOTAL_EST", nullable = false)
    private Long tTotalEst;
    
    /**
     * Trabajo Total Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "TRABAJO_TOTAL_REAL", nullable = false)
    private Long tTotalReal;
    
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
     * Propiedad empresa representa una relación de muchos a uno con la Entidad
     * Empresa.
     */
    @JoinColumn(name = "EMPRESA", referencedColumnName = "RUC", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;
    /**
     * Propiedad empleado representa una relación de muchos a uno con la Entidad
     * Empleado.
     */
    @JoinColumn(name = "RESPONSABLE", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Empleado empleado;
    /**
     * Propiedad parroquia representa una relación de muchos a uno con la
     * Entidad Parroquia.
     */
    @JoinColumns({
        @JoinColumn(name = "PARROQUIA", referencedColumnName = "COD_PARROQUIA", insertable = false, updatable = false),
        @JoinColumn(name = "CANTON", referencedColumnName = "CANTON", insertable = false, updatable = false),
        @JoinColumn(name = "PROVINCIA", referencedColumnName = "PROVINCIA", insertable = false, updatable = false)
    })
    @ManyToOne(fetch = FetchType.EAGER)
    private Parroquia parroquia;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpkProyecto")
//    private List<Expectativa> gpkExpectativaList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpkProyecto")
//    private List<LeccionesAprendidas> gpkLeccionesAprendidasList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpkProyecto")
//    private List<DocumentosProyecto> gpkDocumentosProyectoList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gpkProyecto")
//    private List<Riesgo> gpkRiesgoList;
    /**
     * Crea una nueva instancia de la entidad Proyecto.
     */
    public Proyecto() {
    }

    /**
     * Crea una nueva instancia de la entidad Proyecto de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Proyecto.
     */
    public Proyecto(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Proyecto, se relaciona con la columna
     * COD_PROYECTO.
     *
     * @return el código de la entidad Proyecto
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Proyecto, se relaciona con la columna
     * COD_PROYECTO.
     *
     * @param codigo el valor a ser asignado al código de la entidad Proyecto
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad responsable relacionado con la columna
     * RESPONSABLE.
     *
     * @return el valor asignado a la propiedad responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Asigna un valor a la propiedad responsable, relacionado con la columna
     * RESPONSABLE.
     *
     * @param responsable el valor a ser asignado a la propiedad responsable
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * Obtiene el valor de la propiedad empresa relacionado con la columna
     * EMPRESA.
     *
     * @return el valor asignado a la propiedad empresa
     */
    public String getCodEmpresa() {
        return codEmpresa;
    }

    /**
     * Asigna un valor a la propiedad empresa, relacionado con la columna
     * EMPRESA.
     *
     * @param empresa el valor a ser asignado a la propiedad empresa
     */
    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    /**
     * Obtiene el valor de la propiedad provincia relacionado con la columna
     * PROVINCIA.
     *
     * @return el valor asignado a la propiedad provincia
     */
    public Long getCodProvincia() {
        return codProvincia;
    }

    /**
     * Asigna un valor a la propiedad provincia, relacionado con la columna
     * PROVINCIA.
     *
     * @param provincia el valor a ser asignado a la propiedad provincia
     */
    public void setCodProvincia(Long codProvincia) {
        this.codProvincia = codProvincia;
    }

    /**
     * Obtiene el valor de la propiedad canton relacionado con la columna
     * CANTON.
     *
     * @return el valor asignado a la propiedad canton
     */
    public Long getCodCanton() {
        return codCanton;
    }

    /**
     * Asigna un valor a la propiedad canton, relacionado con la columna CANTON.
     *
     * @param canton el valor a ser asignado a la propiedad canton
     */
    public void setCodCanton(Long codCanton) {
        this.codCanton = codCanton;
    }

    /**
     * Obtiene el valor de la propiedad parroquia relacionado con la columna
     * PARROQUIA.
     *
     * @return el valor asignado a la propiedad parroquia
     */
    public Long getCodParroquia() {
        return codParroquia;
    }

    /**
     * Asigna un valor a la propiedad parroquia, relacionado con la columna
     * PARROQUIA.
     *
     * @param parroquia el valor a ser asignado a la propiedad parroquia
     */
    public void setCodParroquia(Long codParroquia) {
        this.codParroquia = codParroquia;
    }

    /**
     * Obtiene el valor de la propiedad nombreProyecto relacionado con la
     * columna NOMBRE_PROYECTO.
     *
     * @return el valor asignado a la propiedad nombreProyecto
     */
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    /**
     * Asigna un valor a la propiedad nombreProyecto, relacionado con la columna
     * NOMBRE_PROYECTO.
     *
     * @param nombreProyecto el valor a ser asignado a la propiedad
     * nombreProyecto
     */
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    /**
     * Obtiene el valor de la propiedad finicio relacionado con la columna
     * FINICIO.
     *
     * @return el valor asignado a la propiedad finicio
     */
    public Date getFinicio() {
        return finicio;
    }

    /**
     * Asigna un valor a la propiedad finicio, relacionado con la columna
     * FINICIO.
     *
     * @param finicio el valor a ser asignado a la propiedad finicio
     */
    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    /**
     * Obtiene el valor de la propiedad festimada relacionado con la columna
     * FESTIMADA.
     *
     * @return el valor asignado a la propiedad festimada
     */
    public Date getFestimada() {
        return festimada;
    }

    /**
     * Asigna un valor a la propiedad festimada, relacionado con la columna
     * FESTIMADA.
     *
     * @param festimada el valor a ser asignado a la propiedad festimada
     */
    public void setFestimada(Date festimada) {
        this.festimada = festimada;
    }

    /**
     * Obtiene el valor de la propiedad ffin relacionado con la columna FFIN.
     *
     * @return el valor asignado a la propiedad ffin
     */
    public Date getFfin() {
        return ffin;
    }

    /**
     * Asigna un valor a la propiedad ffin, relacionado con la columna FFIN.
     *
     * @param ffin el valor a ser asignado a la propiedad ffin
     */
    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    /**
     * Obtiene el valor de la propiedad avance relacionado con la columna
     * AVANCE.
     *
     * @return el valor asignado a la propiedad avance
     */
    public BigDecimal getAvance() {
        return avance;
    }

    /**
     * Asigna un valor a la propiedad avance, relacionado con la columna AVANCE.
     *
     * @param avance el valor a ser asignado a la propiedad avance
     */
    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public Long gethDiaEst() {
        return hDiaEst;
    }

    public void sethDiaEst(Long hDiaEst) {
        this.hDiaEst = hDiaEst;
    }

    public Long gethDiaReal() {
        return hDiaReal;
    }

    public void sethDiaReal(Long hDiaReal) {
        this.hDiaReal = hDiaReal;
    }

    public BigDecimal gethTrabEst() {
        return hTrabEst;
    }

    public void sethTrabEst(BigDecimal hTrabEst) {
        this.hTrabEst = hTrabEst;
    }

    public BigDecimal gethTrabReal() {
        return hTrabReal;
    }

    public void sethTrabReal(BigDecimal hTrabReal) {
        this.hTrabReal = hTrabReal;
    }

    public Long gettTotalEst() {
        return tTotalEst;
    }

    public void settTotalEst(Long tTotalEst) {
        this.tTotalEst = tTotalEst;
    }

    public Long gettTotalReal() {
        return tTotalReal;
    }

    public void settTotalReal(Long tTotalReal) {
        this.tTotalReal = tTotalReal;
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
     * Obtiene el valor de la propiedad empleado relacionado con la entidad
     * Empleado.
     *
     * @return el valor asignado a la propiedad empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Asigna un valor a la propiedad empleado, relacionado con la entidad
     * Empleado.
     *
     * @param empleado el valor a ser asignado a la propiedad empleado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene el valor de la propiedad parroquia relacionado con la entidad
     * Parroquia.
     *
     * @return el valor asignado a la propiedad parroquia
     */
    public Parroquia getParroquia() {
        return parroquia;
    }

    /**
     * Asigna un valor a la propiedad parroquia, relacionado con la entidad
     * Parroquia.
     *
     * @param parroquia el valor a ser asignado a la propiedad parroquia
     */
    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
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
        return "Proyecto[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Proyecto)) {
            igual = false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
