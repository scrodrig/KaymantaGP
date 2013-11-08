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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase Documento representa a la tabla gpk_documento. Escrito que sirve
 * para ilustrar o dejar constancia de algún hecho particular del proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_DOCUMENTO", catalog = "kaymantaGP", schema = "")
public class Documento implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 5591409946963600175L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Documento, se
     * relaciona con la columna COD_DOCUMENTO.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DOCUMENTO", nullable = false, length = 10)
    private Long codigo;
    /**
     * Llave foránea que referencia a la temática de un documento.. Representa a
     * la columna TIPO_DOCUMENTO.
     */
    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private Long codTipoDocumento;
    /**
     * Llave foránea que referencia a la institución de control del documento..
     * Representa a la columna INSTITUCION_CONTROL.
     */
    @Column(name = "INSTITUCION_CONTROL", nullable = false)
    private Long codInstitucionControl;
    /**
     * Nombre del documento.. Representa a la columna NOMBRE_DOCUMENTO.
     */
    @Column(name = "NOMBRE_DOCUMENTO", nullable = false, length = 100)
    private String nombreDocumento;
    /**
     * Respaldo digital del documento.. Representa a la columna DOCUMENTO.
     */
    @Lob
    @Column(name = "DOCUMENTO", nullable = false, length = 65535)
    private Byte[] documento;
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
     * Propiedad tipoDocumento representa una relación de muchos a uno con la
     * Entidad TipoDocumento.
     */
    @JoinColumn(name = "TIPO_DOCUMENTO", referencedColumnName = "COD_TIPO_DOCUMENTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoDocumento tipoDocumento;
    /**
     * Propiedad institucionControl representa una relación de muchos a uno con
     * la Entidad InstitucionControl.
     */
    @JoinColumn(name = "INSTITUCION_CONTROL", referencedColumnName = "COD_INSTITUCION_CONTROL", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private InstitucionControl institucionControl;

    /**
     * Crea una nueva instancia de la entidad Documento.
     */
    public Documento() {
    }

    /**
     * Crea una nueva instancia de la entidad Documento de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Documento.
     */
    public Documento(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Documento, se relaciona con la columna
     * COD_DOCUMENTO.
     *
     * @return el código de la entidad Documento
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Documento, se relaciona con la columna
     * COD_DOCUMENTO.
     *
     * @param codigo el valor a ser asignado al código de la entidad Documento
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocumento relacionado con la columna
     * TIPO_DOCUMENTO.
     *
     * @return el valor asignado a la propiedad tipoDocumento
     */
    public Long getCodTipoDocumento() {
        return codTipoDocumento;
    }

    /**
     * Asigna un valor a la propiedad tipoDocumento, relacionado con la columna
     * TIPO_DOCUMENTO.
     *
     * @param tipoDocumento el valor a ser asignado a la propiedad tipoDocumento
     */
    public void setCodTipoDocumento(Long codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

    /**
     * Obtiene el valor de la propiedad institucionControl relacionado con la
     * columna INSTITUCION_CONTROL.
     *
     * @return el valor asignado a la propiedad institucionControl
     */
    public Long getCodInstitucionControl() {
        return codInstitucionControl;
    }

    /**
     * Asigna un valor a la propiedad institucionControl, relacionado con la
     * columna INSTITUCION_CONTROL.
     *
     * @param institucionControl el valor a ser asignado a la propiedad
     * institucionControl
     */
    public void setCodInstitucionControl(Long codInstitucionControl) {
        this.codInstitucionControl = codInstitucionControl;
    }

    /**
     * Obtiene el valor de la propiedad nombreDocumento relacionado con la
     * columna NOMBRE_DOCUMENTO.
     *
     * @return el valor asignado a la propiedad nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Asigna un valor a la propiedad nombreDocumento, relacionado con la
     * columna NOMBRE_DOCUMENTO.
     *
     * @param nombreDocumento el valor a ser asignado a la propiedad
     * nombreDocumento
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * Obtiene el valor de la propiedad documento relacionado con la columna
     * DOCUMENTO.
     *
     * @return el valor asignado a la propiedad documento
     */
    public Byte[] getDocumento() {
        return documento;
    }

    /**
     * Asigna un valor a la propiedad documento, relacionado con la columna
     * DOCUMENTO.
     *
     * @param documento el valor a ser asignado a la propiedad documento
     */
    public void setDocumento(Byte[] documento) {
        this.documento = documento;
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
     * Obtiene el valor de la propiedad tipoDocumento relacionado con la entidad
     * TipoDocumento.
     *
     * @return el valor asignado a la propiedad tipoDocumento
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Asigna un valor a la propiedad tipoDocumento, relacionado con la entidad
     * TipoDocumento.
     *
     * @param tipoDocumento el valor a ser asignado a la propiedad tipoDocumento
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el valor de la propiedad institucionControl relacionado con la
     * entidad InstitucionControl.
     *
     * @return el valor asignado a la propiedad institucionControl
     */
    public InstitucionControl getInstitucionControl() {
        return institucionControl;
    }

    /**
     * Asigna un valor a la propiedad institucionControl, relacionado con la
     * entidad InstitucionControl.
     *
     * @param institucionControl el valor a ser asignado a la propiedad
     * institucionControl
     */
    public void setInstitucionControl(InstitucionControl institucionControl) {
        this.institucionControl = institucionControl;
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
        return "Documento[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Documento)) {
            igual = false;
        }
        Documento other = (Documento) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
