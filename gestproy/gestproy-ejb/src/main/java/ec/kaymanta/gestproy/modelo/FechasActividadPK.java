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
 * La Clase FechasActividadPK representa a la clave primaria de la tabla gpk_fechas_actividad.
 * 
 * @author JPA Generator 
 * @version 1.0
 */
@Embeddable
public class FechasActividadPK implements Serializable {


	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = 1425977136618230822L;
	

	/** Código que identifica a un set de fechas.. 
	 * Representa a la columna COD_FECHAS_ACTIVIDAD. 	 */
   	@Column(name = "COD_FECHAS_ACTIVIDAD", nullable = false )
 	private Long codigoFechasActividad;
 
	/** Actividad o subactividad a la que están relacionadas las fechas.. 
	 * Representa a la columna ACTIVIDAD. 	 */
   	@Column(name = "ACTIVIDAD", nullable = false )
 	private Long actividad;
 
	
	/**
	 * Crea una nueva instancia de FechasActividadPK.
	 */
	public FechasActividadPK() {
	}
	
	/**
	 * Crea una nueva instancia de FechasActividadPK.
	 * 
	 * @param codigoFechasActividad valor a ser asignado a la propiedad codigoFechasActividad
	 * @param actividad valor a ser asignado a la propiedad actividad
	 */
	public FechasActividadPK(Long codigoFechasActividad, Long actividad) {
		this.codigoFechasActividad=codigoFechasActividad;
		this.actividad=actividad;
	}

	/**
	 * Obtiene el valor de la propiedad codigoFechasActividad relacionado con la columna COD_FECHAS_ACTIVIDAD.
	 * 
	 * @return el valor asignado a la propiedad codigoFechasActividad
	 */
	public Long getCodigoFechasActividad() {
		return codigoFechasActividad;
	}
	
	/**
	 * Asigna un valor a la propiedad codigoFechasActividad, relacionado con la columna COD_FECHAS_ACTIVIDAD.
	 * 
	 * @param codigoFechasActividad el valor a ser asignado a la propiedad codigoFechasActividad
	 */
	public void setCodigoFechasActividad(Long codigoFechasActividad) {
		this.codigoFechasActividad = codigoFechasActividad;
	}

	/**
	 * Obtiene el valor de la propiedad actividad relacionado con la columna ACTIVIDAD.
	 * 
	 * @return el valor asignado a la propiedad actividad
	 */
	public Long getActividad() {
		return actividad;
	}
	
	/**
	 * Asigna un valor a la propiedad actividad, relacionado con la columna ACTIVIDAD.
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
		hash += codigoFechasActividad != null ? codigoFechasActividad.hashCode() : 0;
		hash += actividad != null ? actividad.hashCode() : 0;
		return hash;
	}

		/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "FechasActividadPK[" +
				" codigoFechasActividad=" + codigoFechasActividad +
				",actividad=" + actividad +
				"]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof FechasActividadPK)) {
			igual = false;
		}
		FechasActividadPK other = (FechasActividadPK) object;
		if ((this.codigoFechasActividad == null && other.codigoFechasActividad != null)
				|| (this.codigoFechasActividad != null && !this.codigoFechasActividad
						.equals(other.codigoFechasActividad))) {
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
