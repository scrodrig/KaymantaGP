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
 * La Clase DocumentosProyectoPK representa a la clave primaria de la tabla
 * gpk_documentos_proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class DocumentosProyectoPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 7459250185645591694L;
    /**
     * Llave foránea que referencia al proyecto al cuál se le adjunta un
     * documento.. Representa a la columna PROYECTO.
     */
    @Column(name = "PROYECTO", nullable = false)
    private Long proyecto;
    /**
     * Llave foránea que refiere al archivo que se enlaza a los diferentes
     * proyectos.. Representa a la columna DOCUMENTO.
     */
    @Column(name = "DOCUMENTO", nullable = false)
    private Long documento;

    /**
     * Crea una nueva instancia de DocumentosProyectoPK.
     */
    public DocumentosProyectoPK() {
    }

    /**
     * Crea una nueva instancia de DocumentosProyectoPK.
     *
     * @param proyecto valor a ser asignado a la propiedad proyecto
     * @param documento valor a ser asignado a la propiedad documento
     */
    public DocumentosProyectoPK(Long proyecto, Long documento) {
        this.proyecto = proyecto;
        this.documento = documento;
    }

    /**
     * Obtiene el valor de la propiedad proyecto relacionado con la columna
     * PROYECTO.
     *
     * @return el valor asignado a la propiedad proyecto
     */
    public Long getProyecto() {
        return proyecto;
    }

    /**
     * Asigna un valor a la propiedad proyecto, relacionado con la columna
     * PROYECTO.
     *
     * @param proyecto el valor a ser asignado a la propiedad proyecto
     */
    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += proyecto != null ? proyecto.hashCode() : 0;
        hash += documento != null ? documento.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DocumentosProyectoPK["
                + " proyecto=" + proyecto
                + ",documento=" + documento
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof DocumentosProyectoPK)) {
            igual = false;
        }
        DocumentosProyectoPK other = (DocumentosProyectoPK) object;
        if ((this.proyecto == null && other.proyecto != null)
                || (this.proyecto != null && !this.proyecto
                .equals(other.proyecto))) {
            igual = false;
        }
        if ((this.documento == null && other.documento != null)
                || (this.documento != null && !this.documento
                .equals(other.documento))) {
            igual = false;
        }
        return igual;
    }
}
