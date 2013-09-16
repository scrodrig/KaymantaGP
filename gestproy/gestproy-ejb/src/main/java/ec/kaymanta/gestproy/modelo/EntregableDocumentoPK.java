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
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * La Clase EntregableDocumentoPK representa a la clave primaria de la tabla
 * gpk_entregable_documento.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class EntregableDocumentoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 7892961065135841067L;
    /**
     * Llave foránea que refiere al archivo que se enlaza a los diferentes
     * proyectos.. Representa a la columna DOCUMENTO.
     */
    @Column(name = "DOCUMENTO", nullable = false)
    private Long documento;
    /**
     * Llave foránea que refiere al entregable de la actividad.. Representa a la
     * columna ENTREGABLE.
     */
    @Column(name = "ENTREGABLE", nullable = false)
    private Long entregable;
    /**
     * Llave foránea que referencia a una actividad o subactividad del
     * desarrollo de un proyecto.. Representa a la columna ACTIVIDAD.
     */
    @Column(name = "ACTIVIDAD", nullable = false)
    private Long actividad;

    /**
     * Crea una nueva instancia de EntregableDocumentoPK.
     */
    public EntregableDocumentoPK() {
    }

    /**
     * Crea una nueva instancia de EntregableDocumentoPK.
     *
     * @param documento valor a ser asignado a la propiedad documento
     * @param entregable valor a ser asignado a la propiedad entregable
     * @param actividad valor a ser asignado a la propiedad actividad
     */
    public EntregableDocumentoPK(Long documento, Long entregable, Long actividad) {
        this.documento = documento;
        this.entregable = entregable;
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad documento relacionado con la columna
     * DOCUMENTO.
     *
     * @return el valor asignado a la propiedad documento
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * Asigna un valor a la propiedad documento, relacionado con la columna
     * DOCUMENTO.
     *
     * @param documento el valor a ser asignado a la propiedad documento
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el valor de la propiedad entregable relacionado con la columna
     * ENTREGABLE.
     *
     * @return el valor asignado a la propiedad entregable
     */
    public Long getEntregable() {
        return entregable;
    }

    /**
     * Asigna un valor a la propiedad entregable, relacionado con la columna
     * ENTREGABLE.
     *
     * @param entregable el valor a ser asignado a la propiedad entregable
     */
    public void setEntregable(Long entregable) {
        this.entregable = entregable;
    }

    /**
     * Obtiene el valor de la propiedad actividad relacionado con la columna
     * ACTIVIDAD.
     *
     * @return el valor asignado a la propiedad actividad
     */
    public Long getActividad() {
        return actividad;
    }

    /**
     * Asigna un valor a la propiedad actividad, relacionado con la columna
     * ACTIVIDAD.
     *
     * @param actividad el valor a ser asignado a la propiedad actividad
     */
    public void setActividad(Long actividad) {
        this.actividad = actividad;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += documento != null ? documento.hashCode() : 0;
        hash += entregable != null ? entregable.hashCode() : 0;
        hash += actividad != null ? actividad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "EntregableDocumentoPK["
                + " documento=" + documento
                + ",entregable=" + entregable
                + ",actividad=" + actividad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof EntregableDocumentoPK)) {
            igual = false;
        }
        EntregableDocumentoPK other = (EntregableDocumentoPK) object;
        if ((this.documento == null && other.documento != null)
                || (this.documento != null && !this.documento
                .equals(other.documento))) {
            igual = false;
        }
        if ((this.entregable == null && other.entregable != null)
                || (this.entregable != null && !this.entregable
                .equals(other.entregable))) {
            igual = false;
        }
        if ((this.actividad == null && other.actividad != null)
                || (this.actividad != null && !this.actividad
                .equals(other.actividad))) {
            igual = false;
        }
        return igual;
    }
}
