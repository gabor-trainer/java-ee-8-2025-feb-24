### **Hands-On Lab: Working with a Simple JSF form Project.md**

---

### **Objective**

**Note:** This lab provides some source code examples and instructions to guide you through the process of exploring and modifying a Java EE 8 JSF project. Always try to understand the code and experiment with it to get a better grasp of the concepts.
**When the lab asks you to modify the code, always try to do it first on your own. Using, and copy and pasting the solution code provided here is always the last resort. :-)**

By the end of this lab, you will:

1.  Clone and import the `jsf_intro` project.
2.  Review the source code structure and understand its components.
3.  Deploy and run the project on Glassfish using a debug configuration.
4.  Modify the project by adding a new property (`address`) to the `Customer` class and updating the JSF view accordingly.
5.  Test the modified project.

---

### **Prerequisites**

**Connect** to your lab machine using RDP, with the user name and password provided by instructor or lab manager.

Ensure the following are already set up:

1.  **Git** is installed and configured. You can test it by running `git --version`. Any recent version should be fine.
2.  **IntelliJ IDEA** is installed and configured with Java EE support and Glassfish support. You can test it by creating a new Java EE project and selecting GlassFish as the application server. To install GlassFish support, go to **File > Settings > Plugins > Marketplace tab at the top** and search for **GlassFish**. Install the plugin and restart IntelliJ IDEA. For further details see in this repoistory's additional-material folder. [how to add glassfish as application server in intellij idea](../../additional-material/how%20to%20add%20glassfish%20as%20application%20server%20in%20intellij%20idea.md)
3.  **Maven** is installed and integrated with IntelliJ. You can test it by running `mvn -v`. Any recent version should be fine.
4.  **Java 8** is installed. Test it by running `java -version`. You should see a version number starting with `1.8`. and greater than `1.8.0_211`. Java 9, 11 17, 21 is also NOT supported when using GlassFish and working with Java EE 8, it will cause issues.
5.  **GlassFish 5** is installed and configured. You can test it by running `asadmin version` in your remote lab machine's c:\glassfish5\bin folder. You should see a version number starting with `5`.

---

### **Time Estimate**

**Total Time**: **30-45 minutes**

---

### **Step 0: Clone the Repository in case you have not cloned it yet**

1.  Open a terminal or Git-enabled environment.
2.  Clone the repository in case you have not cloned it yet:

    ```bash
    git clone https://github.com/PacktPublishing/Java-EE-8-Application-Development.git

    ```

3.  Optionally navigate to the project directory, view and check the source code is available

    ```bash
    cd Java-EE-8-Application-Development/Chapter02/jsf_intro

    ```

---

### **Step 1: Import the Project into IntelliJ IDEA**

1.  Open **IntelliJ IDEA**.
2.  Select **File > New > Project from Existing Sources**.
3.  Navigate to the `jsf_intro` folder inside the cloned repository.
4.  Choose the folder itself or the inside **pom.xml** file and click **OK**.
5.  In case youo selected the project folder there will be a next step: Select **Import project from external model > Maven**, then click **Next**.
6.  Leave default settings for Maven configuration and click **Finish**.
7.  For the question: Trust and Open Project ...? click **Trust Project**. You can optioanlly check the box to always trust the project, so you do not get asked again.
8.  For the question: Where would you like to open the project ...? click **This Window** in case you have no other specific preference.
9.  Wait for IntelliJ to download dependencies and index the project.

---

### **Step 2: Review the Source Files**

1.  Open the project structure in IntelliJ:

    - Navigate to **`src/main/java`** and locate the `net.ensode.javaee8book.jsf` class.
    - Review the properties and methods in the `Customer` class.
      - Discuss its role as a managed bean in the JSF framework. (It is used to store the user's input and display the greeting message.) Make sure you understand the `@Named` and `@RequestScoped` annotations. Make sure you understand the concept of a managed bean in JSF.

2.  Navigate to **`src/main/webapp`**:

    - Open `index.xhtml` and review how it uses JSF tags to bind the UI components to the `Customer` bean.
    - Review and understand the binding expressions like `#{customer.propertyName}`.
    - Open `confirmation.xhtml` and review how it uses JSF tags to bind the UI components

3.  Review **web.xml**:

    - Open **`src/main/webapp/WEB-INF/web.xml`**.
    - Review its role in defining the welcome page and application configurations.

4.  Review the Maven Project Structure in project view:

    - Open the **Project View** in IntelliJ.
    - Review the Maven project structure and the dependencies in the **pom.xml** file.
    - Understand the role of the **Maven** project structure in managing dependencies and building the project, for example `javax.faces` and `javax.enterprise` dependencies.
    - Review the build elements in the **pom.xml** file, such as the **maven-compiler-plugin** and **maven-war-plugin**. Note the Java version is set to 1.8.

### **Step 3: Run the Project with a Debug Configuration**

1.  **Set Up the Run Configuration**:

    - Go to **Run > Edit Configurations**.
    - Add a new **GlassFish Local** configuration using the + button top left
    - Choose GlassFish **Local** as the server type.
    - There are Server, Deployment, Logs, etc tabs.
    - Specify `domain1` (in the Server tab, specify `domain1` as the domain. Make sure you understand what is a domain in GlassFish.)
    - Specify Java 8. (in the Server tab in the **JRE** dropdown, specify `C:\Program Files\Java\jdk-1.8\jre`)
    - Set the artifact to deploy as `jsf_intro:war`. (in the Deployment tab, use the + button and add the artifact to deploy as `jsf_intro:war`)
    - Close the dialog with OK.

2.  **Set a Breakpoint**:

    - Open the `Customer` class.
    - Set a breakpoint on the getter and setter method for the `lastName` (or any other) property.

3.  **Run the Application in Debug Mode**:

    - Click the **Debug** button (top rightish in the IDE) or use the main menu **Run/...** or press the keyboard shortcut according your keyboard settings (default is `Shift+F9`) to start the server in debug mode.
    - Wait for the server to start and deploy the application.
    - The GlassFish server log will show the deployment status.
      you may see a log in your IDE something like this:

      ```bash
        "C:\Program Files\Java\jdk-1.8\jre\bin\java.exe" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.3\lib\idea_rt.jar" com.intellij.rt.execution.CommandLineWrapper N:\ZTEMP\idea_classpath610605675 com.intellij.javaee.oss.process.JavaeeProcess 53422 com.intellij.javaee.oss.glassfish.agent.Glassfish51Agent
        C:\glassfish5\glassfish\bin\asadmin.bat start-domain --debug domain1
        [2024-11-18 07:23:11,647] Artifact jsf_intro:war: Waiting for server connection to start artifact deployment...
        Detected server admin port: 4848
        Detected server http port: 8080
        Attempting to start domain1.... Please look at the server log for more details.....
        Connected to the target VM, address: '127.0.0.1:9009', transport: 'socket'
        Connected to server
        [2024-11-18 07:23:48,967] Artifact jsf_intro:war: Artifact is being deployed, please wait...
        [2024-11-18 07:23:53,720] Artifact jsf_intro:war: Artifact is deployed successfully
        [2024-11-18 07:23:53,720] Artifact jsf_intro:war: Deploy took 4,753 milliseconds
      ```

    - The browser will open automatically with the application URL.
    - The application will be running in debug mode.

4.  **Test the Application**:

    - IntelliJ will launch the browser, in case if not, open the application in a browser at `http://localhost:8080/jsf`.
    - Enter a value in the input fields
    - Observe the breakpoint hit in IntelliJ.
    - Use the Debugger panel to inspect the variable's value and step through the execution.
    - Continue the execution with **Resume** button, and see the results in the browser.

---

### **Step 4: Modify the Project**

1.  **Add a New Property to the `Customer` Class**:

    - Open the `Customer` class.
    - Add a new property `address`:

      ```java
      private String address;

      public String getAddress() {
          return address;
      }

      public void setAddress(String address) {
          this.address = address;
      }

      ```

2.  **Update the JSF Views `index.xhtml` and `index.xhtml` **:

    - Add a new input field for the `address`:

      ```html
      <h:outputLabel value="Enter your address:" for="address" />
      <h:inputText id="address" value="#{customer.address}" />
      ```

    - Add a new output text to display the address on the confirmation page:

      ```html
      <h:outputText value="#{customer.address}"></h:outputText>
      ```

3.  **Rebuild the Project**:

    - Run the Maven build command in your ide. (right toolbar -> maven button -> lifecycle -> clean, then `Run Maven Build`)
    - ... or in the terminal in your project folder, where pom.xml is located:

      ```bash
      mvn clean package

      ```

---

### **Step 5: Test the Modified Project**

1.  Redeploy the application to GlassFish.
2.  Open the application in a browser at `http://localhost:8080/jsf`.
3.  Test the new functionality:
    - Enter values for the name and address fields.
    - Verify the greeting message displays both the name and address.

---

### **Summary**

In this lab, you:

1.  Cloned the `jsf_intro` project.
2.  Imported it into IntelliJ IDEA.
3.  Reviewed the source code structure and functionality.
4.  Ran the project with a debug configuration and explored how breakpoints work.
5.  Enhanced the project by adding a new property (`address`) to the `Customer` class and updated the JSF view to use it.
6.  You've tested, and more: even modified project :-) congrats!

This exercise reinforced your understanding of JSF, managed beans, and debugging Java EE projects at least on a basic level. You also learned how to deploy and run a Java EE project on GlassFish using IntelliJ IDEA.
