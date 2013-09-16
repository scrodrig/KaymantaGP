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
 * La Clase RolFuncionalidadPK representa a la clave primaria de la tabla
 * gpk_rol_funcionalidad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class RolFuncionalidadPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -4655767105167555417L;
    /**
     * Llave foránea que referencia al rol que recibirá las funcionalidades..
     * Representa a la columna ROL.
     */
    @Column(name = "ROL", nullable = false)
    private Long rol;
    /**
     * Llave foránea que referencia a una funcionalidad que se le da a un rol..
     * Representa a la columna FUNCIONALIDAD.
     */
    @Column(name = "FUNCIONALIDAD", nullable = false)
    private Long funcionalidad;

    /**
     * Crea una nueva instancia de RolFuncionalidadPK.
     */
    public RolFuncionalidadPK() {
    }

    /**
     * Crea una nueva instancia de RolFuncionalidadPK.
     *
     * @param rol valor a ser asignado a la propiedad rol
     * @param funcionalidad valor a ser asignado a la propiedad funcionalidad
     */
    public RolFuncionalidadPK(Long rol, Long funcionalidad) {
        this.rol = rol;
        this.funcionalidad = funcionalidad;
    }

    /**
     * Obtiene el valor de la propiedad rol relacionado con la columna ROL.
     *
     * @return el valor asignado a la propiedad rol
     */
    public Long getRol() {
        return rol;
    }

    /**
     * Asigna un valor a la propiedad rol, relacionado con la columna ROL.
     *
     * @param rol el valor a ser asignado a la propiedad rol
     */
    public void setRol(Long rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el valor de la propiedad funcionalidad relacionado con la columna
     * FUNCIONALIDAD.
     *
     * @return el valor asignado a la propiedad funcionalidad
     */
    public Long getFuncionalidad() {
        return funcionalidad;
    }

    /**
     * Asigna un valor a la propiedad funcionalidad, relacionado con la columna
     * FUNCIONALIDAD.
     *
     * @param funcionalidad el valor a ser asignado a la propiedad funcionalidad
     */
    public void setFuncionalidad(Long funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += rol != null ? rol.hashCode() : 0;
        hash += funcionalidad != null ? funcionalidad.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RolFuncionalidadPK["
                + " rol=" + rol
                + ",funcionalidad=" + funcionalidad
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof RolFuncionalidadPK)) {
            igual = false;
        }
        RolFuncionalidadPK other = (RolFuncionalidadPK) object;
        if ((this.rol == null && other.rol != null)
                || (this.rol != null && !this.rol
                .equals(other.rol))) {
            igual = false;
        }
        if ((this.funcionalidad == null && other.funcionalidad != null)
                || (this.funcionalidad != null && !this.funcionalidad
                .equals(other.funcionalidad))) {
            igual = false;
        }
        return igual;
    }
}
