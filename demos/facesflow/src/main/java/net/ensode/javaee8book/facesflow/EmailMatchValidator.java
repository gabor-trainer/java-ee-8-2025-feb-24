package net.ensode.javaee8book.facesflow;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailMatchValidator")
public class EmailMatchValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null) {
            return; // No need to validate if input is null
        }

        // Retrieve the first email component ID from the attribute
        String emailComponentId = (String) component.getAttributes().get("emailComponent");

        if (emailComponentId == null) {
            return; // If no attribute is set, skip validation
        }

        // Find the actual component using the ID
        UIInput emailInput = (UIInput) component.findComponent(emailComponentId);

        if (emailInput == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email component not found", null));
        }

        // Get the value of the first email field
        Object emailValue = emailInput.getValue();

        // Compare emails
        if (emailValue == null || !emailValue.equals(value)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Emails do not match", null));
        }
    }
}
