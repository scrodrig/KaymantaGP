/*
 * KAYMANTA CIA. LTDA.
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2013-2014 Kaymanta Todos los derechos reservados.
 */
package com.persist.common.dao;

/**
 * Clase de utilidad que define el ordenamiento de consultas.
 * 
 * @author Gestorinc S.A.
 * @version $Revision:$
 * 
 */
public final class Order {

	/**
	 * Constante utilizada para definir ordenamiento ascendente.
	 */
	public static final String ASC = "A,";
	/**
	 * Constante utilizada para definir ordenamiento descendente.
	 */
	public static final String DESC = "D,";
	
	/**
	 * Constructor por defecto.
	 */
	private Order() {
		
	}
	
	/**
	 * Retorna un objeto que especifica que la propiedad recibida como 
	 * parámetro será tomada para ordenamiento ascendente.
	 * 
	 * @param propiedad Propiedad de la entidad que será utilizada para el ordenamiento.
	 * @return Cadena que especifica el ordenamiento.
	 */
	public static String ascendente(String propiedad) {
		return ASC+propiedad;
	}
	
	/**
	 * Retorna un objeto que especifica que la propiedad recibida como 
	 * parámetro será tomada para ordenamiento descendente.
	 * 
	 * @param propiedad Propiedad de la entidad que será utilizada para el ordenamiento.
	 * @return Cadena que especifica el ordenamiento.
	 */
	public static String descendente(String propiedad) {
		return DESC+propiedad;
	}
}
