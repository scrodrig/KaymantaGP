/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.util;

import java.text.MessageFormat;

/**
 *
 * @author schubert_david
 */
public class MensajesGenericos {

    private static final String CREAR_FEMENINO = "Se registró la {0}: {1}";
    private static final String MODIFICAR_FEMENINO = "Se modificó la información de la {0}: {1}";
    private static final String ELIMINAR_FEMENINO = "Se eliminó la {0}: {1}";
    private static final String CREAR_MASCULINO = "Se registró el {0}: {1}";
    private static final String MODIFICAR_MASCULINO = "Se modificó la información del {0}: {1}";
    private static final String ELIMINAR_MASCULINO = "Se eliminó el {0}: {1}";

    /**
     * Constructor por defecto.
     */
    private MensajesGenericos() {
    }

    /**
     * Permite agregar un msg de creación de entidades. Ejemplo: Se registró el
     * Usuario: 01 - Luis Perez.
     *
     * @param entidad Nombre de la entidad. Ejemplo: "Usuario"
     * @param identificador Identificador de la instacia. Ejemplo: 01 - Luis
     * Perez (CODIGO - NOMBRE); ROCHE0001 (CODIGO);
     * @param masculino Verdadero si la entidad es femenina (ejemplo: el
     * Usuario) caso contrario falso/null;
     */
    public static void infoCrear(String entidad, String identificador, Boolean masculino) {
        FacesUtil.addMessageInfo(MessageFormat.format(Boolean.TRUE.equals(masculino) ? CREAR_MASCULINO : CREAR_FEMENINO,
                entidad, identificador == null ? "" : identificador));
    }

    /**
     * Permite agregar un msg de modificación de la información de entidades.
     * Ejemplo: Se modificó la información del Usuario: 01 - Luis Perez.
     *
     * @param entidad Nombre de la entidad. Ejemplo: "Usuario"
     * @param identificador Identificador de la instacia. Ejemplo: 01 - Luis
     * Perez (CODIGO - NOMBRE); ROCHE0001 (CODIGO);
     * @param masculino Verdadero si la entidad es femenina (ejemplo: el
     * Usuario) caso contrario falso/null;
     */
    public static void infoModificar(String entidad, String identificador, Boolean masculino) {
        FacesUtil.addMessageInfo(MessageFormat.format(Boolean.TRUE.equals(masculino) ? MODIFICAR_MASCULINO : MODIFICAR_FEMENINO,
                entidad, identificador == null ? "" : identificador));
    }

    /**
     * Permite agregar un msg de eliminación de instancias de entidades.
     * Ejemplo: Se eliminó el Usuario: 01 - Luis Perez.
     *
     * @param entidad Nombre de la entidad. Ejemplo: "Usuario"
     * @param identificador Identificador de la instacia. Ejemplo: 01 - Luis
     * Perez (CODIGO - NOMBRE); ROCHE0001 (CODIGO);
     * @param masculino Verdadero si la entidad es femenina (ejemplo: el
     * Usuario) caso contrario falso/null;
     */
    public static void infoEliminar(String entidad, String identificador, Boolean masculino) {
        FacesUtil.addMessageInfo(MessageFormat.format(Boolean.TRUE.equals(masculino) ? ELIMINAR_MASCULINO : ELIMINAR_FEMENINO,
                entidad, identificador == null ? "" : identificador));
    }

    /**
     * Se agrega el mensaje informativo al dar clic en el botón cancelar: 'Se
     * cancelo los cambios.'
     */
    public static void infoCancelar() {
        FacesUtil.addMessageInfo("Se cancelo los cambios.");
    }

    /**
     * Agrega un mensaje de información.
     *
     * @param msg Mensaje a ser agregado.
     */
    public static void info(String message) {
        FacesUtil.addMessageInfo(message);
    }
    
     /**
     * Agrega un mensaje de información.
     *
     * @param msg Mensaje a ser agregado.
     */
    public static void infoCargado(String message) {
        FacesUtil.addMessageInfo(message);
    }

    /**
     * Agrega un mesaje de error.
     *
     * @param msg Mensaje a ser agregado.
     */
    public static void infoError(String msg) {
        FacesUtil.addMessageError(null, msg);
    }

    /**
     * Se agrega un mensaje de error cuando se produce un error al copiar los
     * valores de las propiedades entre instancia.
     */
    public static void errorCopyProperties() {
        FacesUtil.addMessageError(null, "Se produjo un error al copiar los valores de las propiedades entre instancias.");
    }

    /**
     * Se agrega un mensaje de error cuando se produce un error al guardar la
     * informacion de registro o modificacion.
     */
    public static void errorGuardar() {
        FacesUtil.addMessageError(null, "Se produjo un error al guardar la información. Verifique el log de errores.");
    }
}
