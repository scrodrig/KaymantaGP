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
 * La Clase ExpectativaPK representa a la clave primaria de la tabla gpk_expectativa.
 * 
 * @author JPA Generator 
 * @version 1.0
 */
@Embeddable
public class ExpectativaPK implements Serializable {


	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = -8000996657464793109L;
	

	/** Llave foránea que referencia a un proyecto.. 
	 * Representa a la columna PROYECTO. 	 */
   	@Column(name = "PROYECTO", nullable = false )
 	private Long proyecto;
 
	/** Código que identifica a una expectativa de un proyecto.. 
	 * Representa a la columna COD_EXPECTATIVA. 	 */
   	@Column(name = "COD_EXPECTATIVA", nullable = false )
 	private Long codigoExpectativa;
 
	
	/**
	 * Crea una nueva instancia de ExpectativaPK.
	 */
	public ExpectativaPK() {
	}
	
	/**
	 * Crea una nueva instancia de ExpectativaPK.
	 * 
	 * @param proyecto valor a ser asignado a la propiedad proyecto
	 * @param codigoExpectativa valor a ser asignado a la propiedad codigoExpectativa
	 */
	public ExpectativaPK(Long proyecto, Long codigoExpectativa) {
		this.proyecto=proyecto;
		this.codigoExpectativa=codigoExpectativa;
	}

	/**
	 * Obtiene el valor de la propiedad proyecto relacionado con la columna PROYECTO.
	 * 
	 * @return el valor asignado a la propiedad proyecto
	 */
	public Long getProyecto() {
		return proyecto;
	}
	
	/**
	 * Asigna un valor a la propiedad proyecto, relacionado con la columna PROYECTO.
	 * 
	 * @param proyecto el valor a ser asignado a la propiedad proyecto
	 */
	public void setProyecto(Long proyecto) {
		this.proyecto = proyecto;
	}

	/**
	 * Obtiene el valor de la propiedad codigoExpectativa relacionado con la columna COD_EXPECTATIVA.
	 * 
	 * @return el valor asignado a la propiedad codigoExpectativa
	 */
	public Long getCodigoExpectativa() {
		return codigoExpectativa;
	}
	
	/**
	 * Asigna un valor a la propiedad codigoExpectativa, relacionado con la columna COD_EXPECTATIVA.
	 * 
	 * @param codigoExpectativa el valor a ser asignado a la propiedad codigoExpectativa
	 */
	public void setCodigoExpectativa(Long codigoExpectativa) {
		this.codigoExpectativa = codigoExpectativa;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += proyecto != null ? proyecto.hashCode() : 0;
		hash += codigoExpectativa != null ? codigoExpectativa.hashCode() : 0;
		return hash;
	}

		/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ExpectativaPK[" +
				" proyecto=" + proyecto +
				",codigoExpectativa=" + codigoExpectativa +
				"]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof ExpectativaPK)) {
			igual = false;
		}
		ExpectativaPK other = (ExpectativaPK) object;
		if ((this.proyecto == null && other.proyecto != null)
				|| (this.proyecto != null && !this.proyecto
						.equals(other.proyecto))) {
			igual = false;
		}
		if ((this.codigoExpectativa == null && other.codigoExpectativa != null)
				|| (this.codigoExpectativa != null && !this.codigoExpectativa
						.equals(other.codigoExpectativa))) {
			igual = false;
		}
		return igual;
	}

}
