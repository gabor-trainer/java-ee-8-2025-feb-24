### Hands-On Lab: Working with the Custom Validator Project in Java EE 8

### **Time Estimate**

**Total Time**: **30-45 minutes**

---

### Prerequisites

To complete this lab, ensure the following prerequisites are met:

- **Connect** to your lab machine using RDP, with the user name and password provided by instructor or lab manager.
- **IntelliJ IDEA** is installed and configured. GlassFish support is installed in IntelliJ IDEA.
- **GlassFish 5** application server is installed and configured.
- **JDK 8** is installed.
- **Maven** is installed and properly configured.
- **The repository Java-EE-8-Application-Development** is cloned on your lab machine.

In case of any of this are not met, please refer to the **Prerequisites**, and **Step 0** in [day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.

---

### Lab Objective

**Note:** This lab provides some source code examples and instructions to guide you through the process of exploring and modifying a Java EE 8 JSF project. Always try to understand the code and experiment with it to get a better grasp of the concepts.
**When the lab asks you to modify the code, always try to do it first on your own. Using, and copy and pasting the solution code provided here is always the last resort. :-)**

In this lab, you will:

1.  Clone an existing Maven project from a public repository.
2.  Import the project into IntelliJ IDEA.
3.  Configure the project to run with GlassFish 5.
4.  Deploy the project to GlassFish 5 and verify it is working.
5.  Debug the application in IntelliJ.
6.  Modify the application by adding a second custom validator to validate email format and then test the modification.

### **Step 1: Import the Project into IntelliJ IDEA**

1.  Open **IntelliJ IDEA**.
2.  Select **File > New > Project from Existing Sources**.
3.  Navigate to the `Chapter02/jsfcustomval` folder inside the cloned repository.
4.  Choose the folder itself or the inside **pom.xml** file and click **OK**.
5.  In case youo selected the project folder there will be a next step: Select **Import project from external model > Maven**, then click **Next**.
6.  Leave default settings for Maven configuration and click **Finish**.
7.  For the question: Trust and Open Project ...? click **Trust Project**. You can optioanlly check the box to always trust the project, so you do not get asked again.
8.  For the question: Where would you like to open the project ...? click **This Window** in case you have no other specific preference.
9.  Wait for IntelliJ to download dependencies and index the project.

---

### **Step 2: Configure GlassFish in IntelliJ**

1.  **Set Up the Run Configuration**:

    - Go to **Run > Edit Configurations**.
    - Add a new **GlassFish Local** configuration using the + button top left
    - Choose GlassFish **Local** as the server type.
    - There are Server, Deployment, Logs, etc tabs.
    - Specify `domain1` (in the Server tab, specify `domain1` as the domain. Make sure you understand what is a domain in GlassFish.)
    - Specify Java 8. (in the Server tab in the **JRE** dropdown, specify `C:\Program Files\Java\jdk-1.8\jre`)
    - Set the artifact to deploy as `jsfcustomval:war`. (in the Deployment tab, use the + button and add the artifact to deploy as `jsfcustomval:war`)
    - Close the dialog with OK.

---

### **Step 3: Test the Application**

1.  Open a browser and go to `http://localhost:8080/jsfcustomval`.
2.  Test the form and ensure the custom validator functions correctly by entering invalid inputs.

---

### **Step 4: Debug the Application**

1.  Set a breakpoint in the `validate` method of your validator class:
2.  Start debugging
3.  Trigger the breakpoint by entering an invalid input in the browser.
4.  Observe the values of variables in the IntelliJ debugger.

---

### **Step 5: Modify the Application**

#### **Modification Task: Add a Custom Email Validator**

You will enhance the project by adding a new custom validator that ensures the provided input is a valid email address.

#### **Steps to Implement the Modification**

1.  **Create the New Validator Class**:

    - Navigate to the appropriate package in the `src/main/java` directory.
    - Right-click and select **New > Java Class**.
    - Name the class `MyEmailValidator`.
    - Implement the `javax.faces.validator.Validator` and `javax.faces.validator.FacesValidator` interfaces:

      ```Java
      // ... package and imports ... goes here, if needed

      @FacesValidator("emailValidator")
      public class MyEmailValidator implements Validator {
          @Override
          public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
              String email = value.toString();
              if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                  throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email format", null));
              }
          }
      }

      ```

2.  **Update the JSF Page**:

    - Open `src/main/webapp/index.xhtml`.
    - Add a new input field for email with the `EmailValidator`:

3.  **Update the Named Bean**:

    - Open `customer.java`.
    - Add a property for `email` in case it does not exist:

      ```Java
      private String email;

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      ```

4.  **Redeploy the Application**:

    - Redeploy by clicking **Run > Run 'Custom Validator'**.

5.  **Test the Modification**:

    - Open `http://localhost:8080/jsfcustomval`.
    - Enter valid and invalid email addresses in the new field to test the validator.

---

### **Completion**

Congratulations! You successfully cloned, ran, debugged, modified, and tested the `jsfcustomval` project. The application now includes a new custom email validator.
