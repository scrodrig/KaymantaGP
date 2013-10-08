/*
 * Gestorinc S.A.
 * Sistema: Gestor G5
 * Creado: 13-oct-2008 - 12:17:08
 * 
 * Los contenidos de este archivo son propiedad intelectual de Gestorinc S.A.
 * y se encuentran protegidos por la licencia: "GESTOR FIDUCIA/FONDOS G5".
 * 
 * Usted puede encontrar una copia de esta licencia en: 
 *   http://www.gestorinc.com
 * 
 * Copyright 2008-2010  Todos los derechos reservados.
 */
package com.persist.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene el número de registros totales de una búsqueda y una lista
 * con los registros de la búsqueda. Se debe tener en cuenta que no siempre el
 * número de registros totales va a ser igual al número de registros que se
 * encuentran en la lista, ya que en la primera ocasión que se use el objeto el
 * número de registros de la lista será máximo el número parametrizado en el
 * sistema.
 *
 * @param <T> Clase correspondiente al Modelo (Clases Hibernate) que especifica
 * el tipo de objetos que retorna la búsqueda.
 *
 * @author Gestorinc S.A.
 * @version $Revision: 4475 $
 *
 */
public class ResultadoBusqueda<T> implements Serializable {

    /**
     * Valor auto generado para la constante serialVersionUID.
     */
    private static final long serialVersionUID = 2785241156862627237L;
    /**
     * Propiedad que representa al número de registros totales de una búsqueda.
     */
    private Long registros;
    /**
     * Propiedad que representa a los registros retornados por una búsqueda.
     */
    private List<T> resultado;
    /**
     * Especifica si es la primera ves que se utiliza el objeto para almacenar
     * el resultado de la búsqueda.
     */
    private Boolean inicial = Boolean.TRUE;

    /**
     * Constructor por Defecto.
     */
    public ResultadoBusqueda() {
        super();
        this.resultado = new ArrayList<T>();
    }

    /**
     * Constructor que modifica el estado inicial.
     *
     * @param esInicial Estado de la entidad de búsqueda
     */
    public ResultadoBusqueda(Boolean esInicial) {
        this();
        this.inicial = esInicial;
    }

    /**
     * Retorna el número de registros totales de una búsqueda.
     *
     * @return Número de registros totales de la búsqueda.
     */
    public Long getRegistros() {
        return registros;
    }

    /**
     * Asigna el número total de registros de una búsqueda.
     *
     * @param registros Número de registros totales de la búsqueda.
     */
    public void setRegistros(Long registros) {
        this.registros = registros;
    }

    /**
     * Retorna una lista de registros de acuerdo a la búsqueda ejecutada.
     *
     * @return Lista de registros de la búsqueda.
     */
    public List<T> getResultado() {
        return resultado;
    }

    /**
     * Asigna los registros obtenidos al ejecutar la búsqueda.
     *
     * @param resultado Lista de registros obtenidos al ejecutar la búsqueda.
     */
    public void setResultado(List<T> resultado) {
        this.resultado = resultado;
    }

    /**
     * Obtiene el estado del objeto. TRUE si es la primera ves que es utilizado
     * para retornar el resultado de una búsqueda. FALSE si el objeto ya fue
     * utilizado anteriormente.
     *
     * @return El estado del objeto de resultado.
     */
    public Boolean isInicial() {
        return inicial;
    }

    /**
     * Asigna el estado inicial del objeto de resultado.
     *
     * @param inicial Por defecto el estado TRUE es asignado al crear una nueva
     * instancia del objeto, y FALSE es asignado al momento de asignar el
     * resultado de una búsqueda
     */
    public void setInicial(Boolean inicial) {
        this.inicial = inicial;
    }
}
