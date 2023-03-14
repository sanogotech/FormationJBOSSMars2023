
## 0. Download JBOSS

- JBOSS/Wildfly : https://www.wildfly.org/downloads/
- Version : 21.0.2.Final  Jakarta EE Full & Web Distribution	LGPL	zip


## 1. Test Rapide du Zip JBOSS WildFly

- Unzip   C:/tools/wildfly-21.0.2.Final

```
- Lancer le standalone.bat  avec  C:/tools/wildfly-21.0.2.Final/bin/standalone.bat

```

Vérifier: RAS sur la console
Vérifier dans les logs:  C:/tools/wildfly-21.0.2.Final/standalone/log

- Depuis chrome aller sur:  localhost:8080/  (application)

- Depuis chrome aller sur: localhost:9990/ ( admin)

##  Ajouter dans les variables Env  windows le chemin vers C:/tools/wildfly-21.0.2.Final/bin/

- Depuis le menu démarrer taper :"Modier variables"
- TODO : Capture

- Tester avec le lancement de cmd:  et directement standalone.bat 

##  Admin User


## JAVA VS  JAVA JEE

##  Arborescence JBOSS

/bin
/deployment
/modules

## Git Clone ou Download  War ou code source

- Download :  https://github.com/sanogotech/FormationJBOSSMars2023/tree/main/sample/starter.war
- git clone git clone https://github.com/sanogotech/helloear.git
- git clone https://github.com/sanogotech/JBOSSWildflyQuickstart/tree/master/kitchensink-ear

- Maven

```
mvn  clean package

```
- lien vers /target/*.war
- deposer /sampleswarear

##  Manual  Deploy
- starter.war
- hello-ear

## Maven  Plugin Wildfly:deploy

git clone https://github.com/sanogotech/helloear.git

Doc: https://hantsy.github.io/jakartaee8-starter-boilerplate/03run-wildfly-mvn.html

```
mvn clean  package
mvn clean  package  wildfly:deploy
mvn clean  package   wildfly:undeploy

```

## Projet avec datasource
- projet datasource h2
- projet avec mysql
- download  driver mysql
- lancer  wampserver
- créer la base "books"


## Manually Creating Data Source

0. git clone https://github.com/sanogotech/JBOSSWildflyQuickstart/tree/master/kitchensink-ear

2. Download MySQL connector from Maven central

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
jboss-cli.bat  -c
deploy  mysql-connector-java-8.0.26.jar

4. Open WILDFLY_HOME\standalone\configuration\standalone.xml, and then find <drivers> tag, inside that tag, put these lines to add MySQL driver:


    
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



## TOP 10  JBOSS-CLI


jboss-cli.sh -c

jboss-cli.bat --connect

help

deploy my.ear
undeploy my.ear
:reload
:shutdown
ls
cd help --commands deployment-info deploy --help
EAP_HOME/bin/jboss-cli.sh --connect --file=/path/to/cli_commands.txt




