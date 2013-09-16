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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.persistence.Query;


/**
 * Implementa las operaciones básicas de acceso a datos DAO utilizando el API de
 * JPA. <p> Para escribir una subclase DAO, la cual implemente sus propios
 * métodos debe asociarse el tipo de Entidad. Recuerde que deberá existir una
 * clase DAO por cada clase de Entidad.
 *
 * Este proyecto utiliza la implementación de Hibernate para JPA.
 *
 * @param <T> Clase correspondiente al modelo (Clases Hibernate) que especifica
 * el tipo de objeto con el cual se van a realizar las operaciones de acceso a
 * datos.
 * @param <ID> Tipo de la Clave Primaria de la clase modelo, esta clase debe
 * extender de java.io.Serializable
 *
 * @author Kaymanta S.A.
 * @version $Rev$
 */
public class DefaultGenericDAOImple<T, ID extends Serializable> implements
        GenericDAO<T, ID> { 

    /**
     * Creación del log de auditor�a.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultGenericDAOImple.class);
    
    /**
     * La constante MENSAJE_ERROR_AUDITORIA con el mensaje de error para el
     * LOGGER cuando no se puede asignar la información de auditor�a.
     */
    public static final String MENSAJE_ERROR_AUDITORIA =
            "Error al asignar el objeto de auditoria: ";
    
    /**
     * Objeto que representa a la clase de modelo a la cual pertenece el DAO.
     */
    private Class<T> entityBeanType;
    
    private QueryBuilder<T> qryBuilder;
    
    /**
     * Objeto que maneja las operaciones de persistencia.
     */
    @PersistenceContext(name = "punit")
    private EntityManager em;

    /**
     * Constructor por defecto.
     */
    public DefaultGenericDAOImple() {
        //this.entityBeanType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	/**
     * Constructor por defecto.
     */
    @SuppressWarnings("unchecked")
    public DefaultGenericDAOImple(Class<T> clase) {
		this.entityBeanType = clase;
    }
    
    @PostConstruct
    public void init() {
        this.qryBuilder = new QueryBuilder<T>(this.em);
    }

    /**
     * Retorna una referencia al objeto que maneja las operaciones de
     * persistencia definidas por JPA.
     *
     * @return Referencia al objeto que maneja las operaciones de persistencia.
     * En caso de que el objeto no este inicializado lanza la excepción
     * @see java.lang.IllegalStateException
     */
    public EntityManager getEntityManager() {
        if (em == null) {
            throw new IllegalStateException(
                    "EntityManager has not been set on DAO before usage");
        } else {
            return em;
        }
    }

    /**
     * Retorna el tipo de Entidad a la que pertenece el DAO.
     *
     * @return Tipo de Entidad a la que pertenece el DAO.
     */
    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = getEntityManager().find(getEntityBeanType(), id);
            em.lock(entity, javax.persistence.LockModeType.WRITE);
        } else {
            entity = getEntityManager().find(getEntityBeanType(), id);
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        try {
            Query query = this.qryBuilder.buildQuery(0, this.entityBeanType.newInstance());
            return query.getResultList();
        } catch (Exception ex) {
            LOGGER.error("Error al ejecutar sentencia all.",ex);
            return new ArrayList<T>();
        } 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(T entity) { 
        getEntityManager().persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void makeTransient(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    /**
     * Ejecuta la operaci�n flush definida en JPA.
     */
    @Override
    public void flush() {
        getEntityManager().flush();
    }
    
    /**
     * Ejecuta la operaci�n clear definida en JPA.
     */
    public void clear() {
        getEntityManager().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> find(T entityExample, String... orden) {
        Query query = this.qryBuilder.buildQuery(0, entityExample, orden);
        return query.getResultList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer count(T entityExample) {
        Query query = this.qryBuilder.buildQuery(1, entityExample);
        return ((Long)query.getSingleResult()).intValue();
    }
 
    
    /** 
    * {@inheritDoc}
    */
    @Override
    public void ejecutarNativo(String sentencia){
        try {
            Connection connection = em.unwrap(java.sql.Connection.class);

            PreparedStatement ps;
                    ps = connection.prepareStatement(sentencia);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
                LOGGER.error("Error al ejecutar sentencia",e);
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public T bloquearEscritura(T entidad) {
        return getEntityManager().merge(entidad);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void refresh(T entidad) {
        this.getEntityManager().refresh(entidad);
    }
        
    /**
     * Agrega comillas a una cadena: 'ejemplo'.
     *
     * @param cadena Cadena sin comillas: ejemplo.
     * @return cadena con comillas
     */
    protected String comillar(String cadena) {
        return StringUtils.isBlank(cadena) ? "''" : "'" + cadena + "'";
    }
}
 