## how to add glassfish as application server in intellij idea

### Install the GlassFish Plugin

1.  Open IntelliJ IDEA settings (Ctrl+Alt+S)
2.  Go to Plugins
3.  Switch to the Marketplace tab
4.  Search for "GlassFish"
5.  Find the "GlassFish" plugin and click Install
6.  Restart IntelliJ IDEA when prompted [more...](https://www.jetbrains.com/help/idea/run-debug-configuration-glassfish-server.html)

### Create a Run/Debug Configuration in any project (this can be done by project basis)

To run your application on GlassFish:

1.  Go to Run > Edit Configurations
2.  Click the "+" button and select "GlassFish Server"
3.  Configure the server settings like host, port, etc.
4.  Add your application sources to deploy
5.  Click OK to save [more...](https://www.jetbrains.com/help/idea/run-debug-configuration-glassfish-server.html)

### Troubleshooting

- Make sure GlassFish is installed on your system before configuring it in IntelliJ IDEA
- You may need to add GlassFish libraries as project dependencies if IntelliJ can't find them automatically
- For remote GlassFish servers, ensure you have the correct connection details configured
