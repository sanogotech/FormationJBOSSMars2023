
## Manually Creating Data Source

1. Download MySQL connector from Maven central

You can download with this link :

Download : mysql-connector-java-8.0.17.jar

https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.17/mysql-connector-java-8.0.17.jar


And then put the file under WILDFLY_HOME\modules\system\layers\base\com\mysql\main

2. Create a file  module.xml

Using any kind of text editor, create file inside your Wildfly path, WILDFLY_HOME\modules\system\layers\base\com\mysql\main, and this is the XML file contents of it:

```
<module name="com.mysql" xmlns="urn:jboss:module:1.5">
    <resources>
        <resource-root path="mysql-connector-java-8.0.17.jar">
    </resource-root></resources>
    <dependencies>
        <module name="javax.api">
        <module name="javax.transaction.api">
    </module></module></dependencies>
</module>
```

If the folders didn't exist, create it by yourself.

3. Add MySQL connector to the driver list

Open WILDFLY_HOME\standalone\configuration\standalone.xml, and then find <drivers> tag, inside that tag, put these lines to add MySQL driver:


    
    **  Datasource
    
        ```
               <datasource jndi-name="java:/jboss/datasources/books-database" pool-name="books-datasource">
                    <connection-url>jdbc:mysql://127.0.0.1:3306/books?useSSL=false&amp;serverTimezone=UTC&amp;useLegacyDatetimeCode=false</connection-url>
                    <driver>mysql-connector-java-8.0.26.jar</driver>
                    <security>
                        <user-name>root</user-name>
                         <password></password>
                    </security>
                </datasource>
    ```

Now you can restart Wildfly and expect that new driver will be inside the available list driver.
## Sample code
- https://github.com/sanogotech/demojbossdatasourcemysql
