package net.ensode.javaee8book.jsfcustomval;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@FacesValidator(value = "emailValidator")
// POJO (Plain Old Java Object) class
public class MyEmailValidator implements Validator<String> {

  @Override
  public void validate(FacesContext facesContext, UIComponent uiComponent,String value) throws ValidatorException {
    EmailValidator emailValidator = EmailValidator.getInstance();
    HtmlInputText htmlInputText = (HtmlInputText) uiComponent;

    if (!StringUtils.isEmpty(value)) {
      if (!emailValidator.isValid(value)) {
        FacesMessage facesMessage = new FacesMessage(htmlInputText.
                getLabel() + ": email format is not valid xxx");
        throw new ValidatorException(facesMessage);
      }
    }
  }
}
