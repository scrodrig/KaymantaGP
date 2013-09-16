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
 * La Clase ReunionPK representa a la clave primaria de la tabla gpk_reunion.
 * 
 * @author JPA Generator 
 * @version 1.0
 */
@Embeddable
public class ReunionPK implements Serializable {


	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = 1633118482877850194L;
	

	/** Llave foránea que referencia a un proyecto.. 
	 * Representa a la columna PROYECTO. 	 */
   	@Column(name = "PROYECTO", nullable = false )
 	private Long proyecto;
 
	/** Código que identifica a una reunión.. 
	 * Representa a la columna COD_REUNION. 	 */
   	@Column(name = "COD_REUNION", nullable = false )
 	private Long codigoReunion;
 
	
	/**
	 * Crea una nueva instancia de ReunionPK.
	 */
	public ReunionPK() {
	}
	
	/**
	 * Crea una nueva instancia de ReunionPK.
	 * 
	 * @param proyecto valor a ser asignado a la propiedad proyecto
	 * @param codigoReunion valor a ser asignado a la propiedad codigoReunion
	 */
	public ReunionPK(Long proyecto, Long codigoReunion) {
		this.proyecto=proyecto;
		this.codigoReunion=codigoReunion;
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
	 * Obtiene el valor de la propiedad codigoReunion relacionado con la columna COD_REUNION.
	 * 
	 * @return el valor asignado a la propiedad codigoReunion
	 */
	public Long getCodigoReunion() {
		return codigoReunion;
	}
	
	/**
	 * Asigna un valor a la propiedad codigoReunion, relacionado con la columna COD_REUNION.
	 * 
	 * @param codigoReunion el valor a ser asignado a la propiedad codigoReunion
	 */
	public void setCodigoReunion(Long codigoReunion) {
		this.codigoReunion = codigoReunion;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += proyecto != null ? proyecto.hashCode() : 0;
		hash += codigoReunion != null ? codigoReunion.hashCode() : 0;
		return hash;
	}

		/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ReunionPK[" +
				" proyecto=" + proyecto +
				",codigoReunion=" + codigoReunion +
				"]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof ReunionPK)) {
			igual = false;
		}
		ReunionPK other = (ReunionPK) object;
		if ((this.proyecto == null && other.proyecto != null)
				|| (this.proyecto != null && !this.proyecto
						.equals(other.proyecto))) {
			igual = false;
		}
		if ((this.codigoReunion == null && other.codigoReunion != null)
				|| (this.codigoReunion != null && !this.codigoReunion
						.equals(other.codigoReunion))) {
			igual = false;
		}
		return igual;
	}

}
