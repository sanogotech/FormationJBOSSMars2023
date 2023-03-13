# FormationJBOSSMars2023
Formation JBOSS  Mars 2023

##  Docs

- https://access.redhat.com/documentation/fr-fr/red_hat_jboss_enterprise_application_platform/7.3/pdf/configuration_guide/red_hat_jboss_enterprise_application_platform-7.3-configuration_guide-en-us.pdf

- http://www.mastertheboss.com/jboss-frameworks/jboss-maven/configuring-maven-wildfly-plugin/

- https://access.redhat.com/documentation/fr-fr/jboss_enterprise_application_platform/6.3/html/installation_guide/installation_structure

##  9h30 - 12h30 /  13h30 - 17h/16h30

- Pause : 10h45
- Pause : 15h

##  Jour 1 TP

- Download  jboss wildfly :  https://www.wildfly.org/downloads/ 
- 21.0.2.Final  Jakarta EE Full & Web Distribution

```
$JBOSS_HOME/bin/standalone.bat  ou   /bin/standalone.sh  sur Linux
$JBOSS_HOME/bin/add-user.bat -u admin -p admin123 

```


## INTRO

-   JAVA vs JEE
-   JAVA  EE :  https://docs.oracle.com/javaee/7/tutorial/overview007.htm
-   JBOSS/Wildfly  :  https://www.wildfly.org/downloads/
```
The technology behind WildFly is also available in JBoss Enterprise Application Platform 7. JBoss EAP is a hardened enterprise subscription with Red Hat’s world-class support, long multi-year maintenance cycles, and exclusive content.

```

### Architecture NTIER

### Architecture JAVA JEE

### Serveur  d'Application vs Serveur Web

## JBOSS Overview

Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

```
        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat
		
		```
 **STARTER JBOSS +++++

** standalone.bat 

```
standalone.bat  --server-config=standalone-full.xml
```
- Port admin 9990
- Port App: 8080

** Create admin user: 
```
$JBOSS_HOME/bin/add-user.bat -u admin -p admin123 --silent
$JBOSS_HOME/bin/add-user.sh -u admin -p admin123 --silent

$JBOSS_HOME/bin/add-user.bat -u admin -p admin123
Updated user 'admin' to file 'D:\JBOSS\wildfly-21.0.2.Final\standalone\configuration\mgmt-users.properties'
Updated user 'admin' to file 'D:\JBOSS\wildfly-21.0.2.Final\domain\configuration\mgmt-users.properties'
Appuyez sur une touche pour continuer...


http://localhost:9990/console/index.html
user = admin
password =  admin123

### Presentation de l'Arborescence  Folders/Files Wildfly

##  Sample code
- git clone https://github.com/sanogotech/helloear.git

* https://hantsy.github.io/jakartaee8-starter-boilerplate/03run-wildfly-mvn.html

mvn clean  package 
```
mvn clean  package  wildfly:deploy
```
mvn clean  package   wildfly:undeploy


