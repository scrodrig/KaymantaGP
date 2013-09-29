/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.util;

import java.text.MessageFormat;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author  schubert_david
 */
public class FacesUtil {

    /**
     * Constructor por defecto.
     */
    private FacesUtil() {
    }

    /**
     * Obtiene la referencia al FacesContext.
     *
     * @return FacesContext.
     */
    public static FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Obtiene un componente del árbol de componentes.
     *
     * @param id Id del componente en la interfaz de usuario.
     * @return UIComponent
     */
    public static UIComponent findComponent(String id) {
        return facesContext().getViewRoot().findComponent(id);
    }

    /**
     * Obtiene la referencia a cualquier tipo de Bean (request, session o
     * application); los beans de scope 'request' deben estar ejecutándose para
     * poder acceder a ellos, en cambio que los Beans de scope 'session' pueden
     * ser accedidos incluso si aun no han sido cargados en session.
     *
     * @param <T> Cualquier tipo de dato.
     * @param nombreBean Nombre del Bean, este nombre es el definido en el
     * faces-config.xml
     * @return La referencia al Bean
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static <T> T instanciaBean(String nombreBean) {
        return (T) facesContext().getApplication().getVariableResolver().resolveVariable(
                FacesContext.getCurrentInstance(), nombreBean);
    }

    /**
     * Permite crear una expresión de valor para asociarlo a los atributos de
     * los componentes de JSF.
     *
     * @param expresion EL Expresión que representa el valor que se desea
     * asociar.
     * @return Objeto que representa el valor asociado.
     */
    public static ValueExpression crearValueExpression(String expresion) {
        FacesContext facesCtx = facesContext();
        ExpressionFactory elFactory = facesCtx.getApplication().getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        return elFactory.createValueExpression(elContext, expresion, Object.class);
    }

    /**
     * Permite asociar un método a un atributo de los componentes de JSF.
     *
     * @param expresion EL Expresión que representa la referencia al método .
     * @param argtypes Lista de argumentos del método.
     * @return Objeto que representa el método asociado.
     */
    @SuppressWarnings("rawtypes")
    public static MethodExpression crearMethodExpression(String expresion, Class[] argtypes) {
        FacesContext facesCtx = facesContext();
        ExpressionFactory elFactory = facesCtx.getApplication().getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        return elFactory.createMethodExpression(elContext, expresion, null, argtypes);
    }

    /**
     * Método que permite agregar un mensaje global informativo.
     *
     * @param mensaje Mensaje informativo.
     */
    public static void addMessageInfo(String mensaje) {
        facesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", mensaje));
    }

    /**
     * Agrega un mensaje de advertencia.
     *
     * @param clientId ClientId del componente asociado al mensaje.
     * @param mensaje Mensaje de advertencia.
     */
    public static void addMessageWarn(String clientId, String mensaje) {
        facesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    /**
     * Agrega un mensaje de error.
     *
     * @param clientId ClientId del componente asociado al mensaje.
     * @param mensaje Mensaje de error.
     */
    public static void addMessageError(String clientId, String mensaje) {
        facesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }
}
