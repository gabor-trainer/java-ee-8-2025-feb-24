package net.ensode.javaee8book.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
// A named bean could be in the role of a model in the MVC (Model-View-Controller) design pattern or even a controller.
// Customer is a named bean, which means that it is a managed bean that can be accessed from the JSF pages.
public class Customer {

  private String firstName;
  private String lastName;
  private String email;
  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
      this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
