/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.validators;

import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author schubert_david
 */
@FacesValidator("validator.dateRangeValidator")
public class DateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || component.getAttributes().get("fromDate") == null) return;

        Date toDate   = (Date) value; 
        Date fromDate = (Date) component.getAttributes().get("fromDate");
        System.out.println(value);
        if (toDate.after(fromDate)) {
            FacesMessage message = new FacesMessage("Invalid dates submitted.");
            MensajesGenericos.infoError("Invalid dates submitted.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
