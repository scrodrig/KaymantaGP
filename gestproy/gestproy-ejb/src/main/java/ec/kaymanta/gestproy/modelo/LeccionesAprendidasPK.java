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
 * La Clase LeccionesAprendidasPK representa a la clave primaria de la tabla gpk_lecciones_aprendidas.
 * 
 * @author JPA Generator 
 * @version 1.0
 */
@Embeddable
public class LeccionesAprendidasPK implements Serializable {


	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = -1647429433352407806L;
	

	/** Llave foránea que referencia a un proyecto.. 
	 * Representa a la columna PROYECTO. 	 */
   	@Column(name = "PROYECTO", nullable = false )
 	private Long proyecto;
 
	/** Código de las lecciones aprendidas que alimentan la base del conocimiento.. 
	 * Representa a la columna COD_LECCIONES_APRENDIDAS. 	 */
   	@Column(name = "COD_LECCIONES_APRENDIDAS", nullable = false )
 	private Long codigoLeccionesAprendidas;
 
	
	/**
	 * Crea una nueva instancia de LeccionesAprendidasPK.
	 */
	public LeccionesAprendidasPK() {
	}
	
	/**
	 * Crea una nueva instancia de LeccionesAprendidasPK.
	 * 
	 * @param proyecto valor a ser asignado a la propiedad proyecto
	 * @param codigoLeccionesAprendidas valor a ser asignado a la propiedad codigoLeccionesAprendidas
	 */
	public LeccionesAprendidasPK(Long proyecto, Long codigoLeccionesAprendidas) {
		this.proyecto=proyecto;
		this.codigoLeccionesAprendidas=codigoLeccionesAprendidas;
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
	 * Obtiene el valor de la propiedad codigoLeccionesAprendidas relacionado con la columna COD_LECCIONES_APRENDIDAS.
	 * 
	 * @return el valor asignado a la propiedad codigoLeccionesAprendidas
	 */
	public Long getCodigoLeccionesAprendidas() {
		return codigoLeccionesAprendidas;
	}
	
	/**
	 * Asigna un valor a la propiedad codigoLeccionesAprendidas, relacionado con la columna COD_LECCIONES_APRENDIDAS.
	 * 
	 * @param codigoLeccionesAprendidas el valor a ser asignado a la propiedad codigoLeccionesAprendidas
	 */
	public void setCodigoLeccionesAprendidas(Long codigoLeccionesAprendidas) {
		this.codigoLeccionesAprendidas = codigoLeccionesAprendidas;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += proyecto != null ? proyecto.hashCode() : 0;
		hash += codigoLeccionesAprendidas != null ? codigoLeccionesAprendidas.hashCode() : 0;
		return hash;
	}

		/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "LeccionesAprendidasPK[" +
				" proyecto=" + proyecto +
				",codigoLeccionesAprendidas=" + codigoLeccionesAprendidas +
				"]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof LeccionesAprendidasPK)) {
			igual = false;
		}
		LeccionesAprendidasPK other = (LeccionesAprendidasPK) object;
		if ((this.proyecto == null && other.proyecto != null)
				|| (this.proyecto != null && !this.proyecto
						.equals(other.proyecto))) {
			igual = false;
		}
		if ((this.codigoLeccionesAprendidas == null && other.codigoLeccionesAprendidas != null)
				|| (this.codigoLeccionesAprendidas != null && !this.codigoLeccionesAprendidas
						.equals(other.codigoLeccionesAprendidas))) {
			igual = false;
		}
		return igual;
	}

}
