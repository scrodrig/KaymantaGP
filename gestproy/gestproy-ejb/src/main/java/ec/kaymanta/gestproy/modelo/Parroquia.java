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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 

/**
 * La Clase Parroquia representa a la tabla gpk_parroquia.
 * Parroquia en la que tiene lugar el desarrollo de un proyecto. 
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_PARROQUIA", catalog = "kaymantaGP", schema = "")
public class Parroquia implements Serializable {

	/** Constante autogenerada serialVersionUID. */
	private static final long serialVersionUID = -4655343559584810903L;
	
	/** Propiedad que representa a la clave primaria de la entidad Parroquia. */
	@EmbeddedId
	private ParroquiaPK pk = new ParroquiaPK();


	/** Nombre de la parroquia.. 
	 * Representa a la columna NOMBRE. 	 */
   	@Column(name = "NOMBRE", nullable = false , length = 20)
 	private String nombre;
 
	/** Propiedad  canton representa una relación de muchos a uno con la Entidad Canton.*/
	@JoinColumns({
	@JoinColumn(name = "CANTON", referencedColumnName = "COD_CANTON", insertable = false, updatable = false) ,
	@JoinColumn(name = "PROVINCIA", referencedColumnName = "PROVINCIA", insertable = false, updatable = false) 
	})
	@ManyToOne(fetch = FetchType.EAGER)
	private Canton canton;


		
	/**
	 * Crea una nueva instancia de la entidad Parroquia.
	 */
	public Parroquia() {
	}
	
	/**
	 * Crea una nueva instancia de la entidad Parroquia de acuerdo a su clave primaria.
	 * 
	 * @param pk valor para la clave primaria de la entidad Parroquia.
	 */
	public Parroquia(ParroquiaPK pk) {
		this.pk = pk;
	}

	/**
	 * Obtiene la clave primaria de la entidad Parroquia.
	 * 
	 * @return la clave primaria de la entidad Parroquia.
	 */
	public ParroquiaPK getPk() {
		return pk;
	}
	
	/**
	 * Asigna la clave primaria de la entidad Parroquia.
	 * 
	 * @param pk el valor a ser asignado a la clave primaria de la entidad Parroquia
	 */
	public void setPk(ParroquiaPK pk) {
		this.pk = pk;
	}
	
	/**
	 * Obtiene el valor de la propiedad nombre relacionado con la columna NOMBRE.
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
	 * Obtiene el valor de la propiedad canton relacionado con la entidad Canton.
	 * 
	 * @return el valor asignado a la propiedad canton
	 */
	public Canton getCanton() {
		return canton;
	}
	
	/**
	 * Asigna un valor a la propiedad canton, relacionado con la entidad Canton.
	 * 
	 * @param canton el valor a ser asignado a la propiedad canton
	 */
	public void setCanton(Canton canton) {
		this.canton = canton;
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
		return "Parroquia[pk=" + pk + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		boolean igual = true;
		if (!(object instanceof Parroquia)) {
			igual = false;
		}
		Parroquia other = (Parroquia) object;
		if ((this.pk == null && other.pk != null)
				|| (this.pk != null && !this.pk
						.equals(other.pk))) {
			igual = false;
		}
		return igual;
	}

}
