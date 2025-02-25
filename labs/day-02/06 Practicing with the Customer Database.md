### Hands-on Lab: Practicing with the Customer Database

The create_populate_tables.sql script creates a database, adds some tables to it and prepopulates some of the tables.

#### To run the script:

#### From the command line:

1. Change directory to [glassfish installation directory]\glassfish\bin
2. run .\asadmin start-database
3. Change directory to [glassfish installation directory]\javadb\bin.
4. Open the file called setNetworkClientCP.bat in your favorite text editor.
5. Look for a line that looks like this:
   rem set DERBY_INSTALL=
6. Uncomment the above line and add the javadb installation directory to the
   right of the equal sign (the value should be [c:\glassfish5\javadb]).
7. From the command line and still in the directory from step 3, execute the above script:
   .\setNetworkClientCP.bat
8. Execute the following command:

   ```bash
   ij.bat
   ```

   You should now be executing ij, a command line sql interpreter included
   with JavaDB.

9. Type the following in ij:

   ```bash
   run '<path to your book repo>\Chapter03\dbscript\create_populate_tables.sql';
   ```

   The script should now execute, it should create the database, create the
   tables and prepopulate some of the tables.

10. Check that the tables were created by executing the following command:

    **Note:** _do not forget the semicolon at the end of the commands. In case you miss it, you will see a continuation prompt (>) the sql interpreter waiting your continuation, so just continue: type the missing semicolon and enter_

    ```bash
    select * from customers;
    ```

    You should see 0 rows, but the output should clearly indicate that table exists

    ```bash
    select * from us_states;
    ```

    You should see 51 rows, one for each state in the US.

11. Type `quit;` at the ij prompt.
    You should now be at the command line prompt.

The database and tables should have been created.

_Note: Before creating the connection pool and datasource the domain should be
running. This can be accomplished by entering the following command:
"asadmin start-domain domain1" at the [glassfish installation
directory]\glassfish\bin directory (assuming we are using the default domain
called domain1)._

### To create the connection pool:

The connection pool can be created either graphically as it was demonstrated in the presentation _or_ by executing the following steps:

1. Change directory to [glassfish installation directory]\glassfish\bin.
2. Execute the following command:

   ```bash
   asadmin create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource --restype javax.sql.DataSource --property DatabaseName=customerdb:User=dev:password=dev CustomerDBPool
   ```

3. Check what you have done :-). Using GlassFish Admin Console (localhost:4848), navigate to Resources > JDBC > JDBC Connection Pools.

### To create the datasource:

The datasource can be created either graphically as it was demonstrated in the presentation _or_ by executing the following steps:

1. Change directory to [glassfish installation directory]\glassfish\bin.
2. Execute the following command (without the quotes):

   ```bash
   asadmin create-jdbc-resource --connectionpoolid CustomerDBPool jdbc/__CustomerDBPool
   ```

3. Check what you have done :-). Using GlassFish Admin Console (localhost:4848), navigate to Resources > JDBC > JDBC Resources.

After following all of the above steps. Examples using database connectivity should deploy and execute properly.

### View the Database in ItelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select **View > Tool Windows > Database**.
3. Click the **+** icon and select **Data Source > Apache Derby (Remote)**.
4. In the **Data Sources and Drivers** dialog, set the following:

- **Name**: CustomerDB
- **Host**: localhost
- **Port**: 1527
- **Authentication**: User and Password
- **User**: dev
- **Password**: dev
- **Database**: customerdb
- **URL**: jdbc:derby://localhost:1527/customerdb
- Click on Download link in case the driver files are missing.
- **Test Connection**: Click the button to test the connection.
- **OK**: Click the button to save the connection.

5. The database should now be visible in the **Database** tool window.
6. Expand the database to view the tables and data.
7. Note that not all schemas are displayed by default. You may need to expand the **Schemas** node to see the tables and check or uncheck the schemas you want to view. (click on the 1 of 12 just to right of the datasource name)
8. Try: Right-click on the database and select **Refresh** to refresh the database.

### Update some data in the database:

1. Open the DEV schema node and it tables node
2. Right-click on the table you want to update.
3. Select **Edit Data**.
4. Right-click on the row you want to update.
5. Select **Edit**.
6. Just for the sake of curiosity, click on Preview Pending Changes to see the SQL that will be executed. (on the toolbar the eye icon with little green arrow in it)
7. Update the data by clicking the Submit button on the toolbar (green arrow).

### **Optionally install DataGrip and see your database there too**

1. Install DataGrip from JetBrains, by downloading it from the JetBrains website. [download here](https://download-cdn.jetbrains.com/datagrip/datagrip-2024.3.exe)
2. In the install you can leave the default options, just click next until the end.
3. Start DataGrip.
4. Create a new Project, name it customerdb.
5. Menu: File > New > Data Source > Apache Derby > Apache Derby (Remote)
6. Go on in very similar way as you did in IntelliJ IDEA :-)
