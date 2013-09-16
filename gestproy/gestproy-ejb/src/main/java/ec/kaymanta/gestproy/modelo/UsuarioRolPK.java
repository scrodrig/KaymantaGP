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
 * La Clase UsuarioRolPK representa a la clave primaria de la tabla
 * gpk_usuario_rol.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Embeddable
public class UsuarioRolPK implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 98117707387229627L;
    /**
     * Llave foránea que referencia al usuario que recibirá un rol.. Representa
     * a la columna USUARIO.
     */
    @Column(name = "USUARIO", nullable = false, length = 10)
    private String usuario;
    /**
     * Llave foránea al rol que va a ser asignado.. Representa a la columna ROL.
     */
    @Column(name = "ROL", nullable = false)
    private Long rol;

    /**
     * Crea una nueva instancia de UsuarioRolPK.
     */
    public UsuarioRolPK() {
    }

    /**
     * Crea una nueva instancia de UsuarioRolPK.
     *
     * @param usuario valor a ser asignado a la propiedad usuario
     * @param rol valor a ser asignado a la propiedad rol
     */
    public UsuarioRolPK(String usuario, Long rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    /**
     * Obtiene el valor de la propiedad usuario relacionado con la columna
     * USUARIO.
     *
     * @return el valor asignado a la propiedad usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Asigna un valor a la propiedad usuario, relacionado con la columna
     * USUARIO.
     *
     * @param usuario el valor a ser asignado a la propiedad usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += usuario != null ? usuario.hashCode() : 0;
        hash += rol != null ? rol.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UsuarioRolPK["
                + " usuario=" + usuario
                + ",rol=" + rol
                + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof UsuarioRolPK)) {
            igual = false;
        }
        UsuarioRolPK other = (UsuarioRolPK) object;
        if ((this.usuario == null && other.usuario != null)
                || (this.usuario != null && !this.usuario
                .equals(other.usuario))) {
            igual = false;
        }
        if ((this.rol == null && other.rol != null)
                || (this.rol != null && !this.rol
                .equals(other.rol))) {
            igual = false;
        }
        return igual;
    }
}
