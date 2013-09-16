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

import java.io.Serializable;
import java.util.List;

/**
 * Define los métodos principales de acceso a datos, los cuales deben ser
 * implementados por la Clase DAO Genérica.
 *
 * @param <T> Clase correspondiente al modelo (Clases Hibernate) que especifica
 * el tipo de objeto con el cual se van a realizar las operaciones de acceso a
 * datos.
 * @param <ID> Tipo de la Clave Primaria de la clase modelo, esta clase debe
 * extender de java.io.Serializable
 *
 * @author Kaymanta
 * @version $Revision $
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Busca un objeto de acuerdo a su clave primaria.
     *
     * @param id Objeto que contiene el valor de la clave primaria para realizar
     * la búsqueda.
     * @param lock TRUE si desea bloquear el objeto encontrado.
     * @return Objeto encontrado correspondiente con la clave primaria enviada
     * como parámetro, nulo en caso de que no se haya encontrado ningún objeto.
     */
    T findById(ID id, boolean lock);

    /**
     * Ejecuta una búsqueda retornando todos los registros de una tabla. SOLO se
     * puede utilizar para las entidades cuyo clave primaria sea compuesta y la
     * misma contenga el atributo 'numLicencia'. Es recomendable que en las
     * Clases DAO que heredan de esta se sobreescriba el método para las
     * entidades en las cuales se quiere prevenir la ejecución del mismo, la
     * sobreescritura deberá lanzar por defecto UnsoportedOperationException.
     * @return Retorna el listado de todos los registros de una tabla.
     */
    List<T> findAll();

    /**
     * Ejecuta una búsqueda en base a los criterios especificados por los
     * valores asignados a las propiedades de entityExample. Este método tiene
     * dos funcionamientos de acuerdo al estado del parámetro resultado.
     *
     * @param entityExample Entidad que contiene los valores para la aplicación
     * de los criterios de búsqueda.
     * 
     * @param orden el orden (opcional).
     *
     * @return Retorna el resultado de la búsqueda ejecutada.
     *
     * @see com.gestorinc.gestor.common.beans.ResultadoBusqueda
     */
    List<T> find(T entityExample, String... orden);

    
    /**
     * Retorna el número total de registros que retornará la ejecución de una
     * búsqueda en base a los criterios especificados.
     *
     * @param entityExample Entidad que contiene los valores para la aplicación
     * de los criterios de búsqueda.
     * @return Número total de registros que devolverá la ejecución de la
     * búsqueda.
     */
    Integer count(T entityExample);

/**
     * Ejecuta la operación INSERT en el repositorio de datos.
     *
     * @param entity Objeto que contiene los valores con los cuales se va a
     * crear el nuevo registro en el repositorio de datos.
     * 
     */
    void insert(T entity);

    /**
     * Ejecuta la operación UPDATE en el repositorio de datos.
     *
     * @param entity Objeto que contiene los valores con los cuales se va a
     * actualizar el registro que se encuentra en el repositorio de datos.
     * 
     * @return Objeto con los nuevos valores que constan en el repositorio de
     * datos.
     */
    T update(T entity);

    /**
     * Ejecuta la operación DELETE en el repositorio de datos.
     *
     * @param entity Objeto que contiene la clave primaria del registro que va a
     * ser eliminado del repositorio de datos.
     */
    void makeTransient(T entity);

    /**
     * Ejecuta la operación DELETE en el repositorio de datos.
     *
     * @param entity Objeto que contiene la clave primaria del registro que va a
     * ser eliminado del repositorio de datos.
     */
    void remove(T entity);

    /**
     * Ejecutar sentencia nativa.
     *
     * @param sentencia la sentencia
     */
    void ejecutarNativo(String sentencia);

    

    /**
     * Bloquear un objeto para escritura.
     *
     * @param entidad el objeto a bloquear
     *
     * @return la entidad actualizada
     */
    T bloquearEscritura(T entidad);

    /**
     * Refresca el estado de la entidad con su contenido en la base de datos.
     *
     * @param entidad el objeto a refrescar
     */
    void refresh(T entidad);

    /**
     * Refresca el estado de la entidad en la base de datos.
     */
    void flush();
    
}
