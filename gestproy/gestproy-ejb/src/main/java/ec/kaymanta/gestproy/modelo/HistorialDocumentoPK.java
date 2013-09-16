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
 * La Clase HistorialDocumentoPK representa a la clave primaria de la tabla gpk_historial_documento.
 * 
 * @author JPA Generator 
 * @version 1.0
 */
@Embeddable
public class HistorialDocumentoPK implements Serializable {


	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = 311577193117708376L;
	

	/** Llave foránea que referencia a un documento.. 
	 * Representa a la columna DOCUMENTO. 	 */
   	@Column(name = "DOCUMENTO", nullable = false )
 	private Long documento;
 
	/** Código del historial de un documento.. 
	 * Representa a la columna COD_HISTORIAL_DOCUMENTO. 	 */
   	@Column(name = "COD_HISTORIAL_DOCUMENTO", nullable = false )
 	private Long codigoHistorialDocumento;
 
	
	/**
	 * Crea una nueva instancia de HistorialDocumentoPK.
	 */
	public HistorialDocumentoPK() {
	}
	
	/**
	 * Crea una nueva instancia de HistorialDocumentoPK.
	 * 
	 * @param documento valor a ser asignado a la propiedad documento
	 * @param codigoHistorialDocumento valor a ser asignado a la propiedad codigoHistorialDocumento
	 */
	public HistorialDocumentoPK(Long documento, Long codigoHistorialDocumento) {
		this.documento=documento;
		this.codigoHistorialDocumento=codigoHistorialDocumento;
	}

	/**
	 * Obtiene el valor de la propiedad documento relacionado con la columna DOCUMENTO.
	 * 
	 * @return el valor asignado a la propiedad documento
	 */
	public Long getDocumento() {
		return documento;
	}
	
	/**
	 * Asigna un valor a la propiedad documento, relacionado con la columna DOCUMENTO.
	 * 
	 * @param documento el valor a ser asignado a la propiedad documento
	 */
	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	/**
	 * Obtiene el valor de la propiedad codigoHistorialDocumento relacionado con la columna COD_HISTORIAL_DOCUMENTO.
	 * 
	 * @return el valor asignado a la propiedad codigoHistorialDocumento
	 */
	public Long getCodigoHistorialDocumento() {
		return codigoHistorialDocumento;
	}
	
	/**
	 * Asigna un valor a la propiedad codigoHistorialDocumento, relacionado con la columna COD_HISTORIAL_DOCUMENTO.
	 * 
	 * @param codigoHistorialDocumento el valor a ser asignado a la propiedad codigoHistorialDocumento
	 */
	public void setCodigoHistorialDocumento(Long codigoHistorialDocumento) {
		this.codigoHistorialDocumento = codigoHistorialDocumento;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += documento != null ? documento.hashCode() : 0;
		hash += codigoHistorialDocumento != null ? codigoHistorialDocumento.hashCode() : 0;
		return hash;
	}

		/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "HistorialDocumentoPK[" +
				" documento=" + documento +
				",codigoHistorialDocumento=" + codigoHistorialDocumento +
				"]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof HistorialDocumentoPK)) {
			igual = false;
		}
		HistorialDocumentoPK other = (HistorialDocumentoPK) object;
		if ((this.documento == null && other.documento != null)
				|| (this.documento != null && !this.documento
						.equals(other.documento))) {
			igual = false;
		}
		if ((this.codigoHistorialDocumento == null && other.codigoHistorialDocumento != null)
				|| (this.codigoHistorialDocumento != null && !this.codigoHistorialDocumento
						.equals(other.codigoHistorialDocumento))) {
			igual = false;
		}
		return igual;
	}

}
