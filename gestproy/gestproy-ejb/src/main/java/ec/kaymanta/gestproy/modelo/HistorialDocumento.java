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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase HistorialDocumento representa a la tabla gpk_historial_documento.
 * Entidad que almacena las diferentes versiones de los documentos existentes.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_HISTORIAL_DOCUMENTO", catalog = "kaymantaGP", schema = "")
public class HistorialDocumento implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -2663882809314844369L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * HistorialDocumento.
     */
    @EmbeddedId
    private HistorialDocumentoPK pk = new HistorialDocumentoPK();
    /**
     * Nombre del documento.. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    /**
     * Archivo digital que evidencia la existencia de un cambio.. Representa a
     * la columna RESPALDO_DOCUMENTO.
     */
    @Lob
    @Column(name = "RESPALDO_DOCUMENTO", nullable = false, length = 65535)
    private Byte[] respaldoDocumento;
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
     * Propiedad documento representa una relaci�n de muchos a uno con la
     * Entidad Documento.
     */
    @JoinColumn(name = "DOCUMENTO", referencedColumnName = "COD_DOCUMENTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Documento documento;

    /**
     * Crea una nueva instancia de la entidad HistorialDocumento.
     */
    public HistorialDocumento() {
    }

    /**
     * Crea una nueva instancia de la entidad HistorialDocumento de acuerdo a su
     * clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad HistorialDocumento.
     */
    public HistorialDocumento(HistorialDocumentoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad HistorialDocumento.
     *
     * @return la clave primaria de la entidad HistorialDocumento.
     */
    public HistorialDocumentoPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad HistorialDocumento.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * HistorialDocumento
     */
    public void setPk(HistorialDocumentoPK pk) {
        this.pk = pk;
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
     * Obtiene el valor de la propiedad respaldoDocumento relacionado con la
     * columna RESPALDO_DOCUMENTO.
     *
     * @return el valor asignado a la propiedad respaldoDocumento
     */
    public Byte[] getRespaldoDocumento() {
        return respaldoDocumento;
    }

    /**
     * Asigna un valor a la propiedad respaldoDocumento, relacionado con la
     * columna RESPALDO_DOCUMENTO.
     *
     * @param respaldoDocumento el valor a ser asignado a la propiedad
     * respaldoDocumento
     */
    public void setRespaldoDocumento(Byte[] respaldoDocumento) {
        this.respaldoDocumento = respaldoDocumento;
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
     * Obtiene el valor de la propiedad documento relacionado con la entidad
     * Documento.
     *
     * @return el valor asignado a la propiedad documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Asigna un valor a la propiedad documento, relacionado con la entidad
     * Documento.
     *
     * @param documento el valor a ser asignado a la propiedad documento
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        return "HistorialDocumento[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof HistorialDocumento)) {
            igual = false;
        }
        HistorialDocumento other = (HistorialDocumento) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
