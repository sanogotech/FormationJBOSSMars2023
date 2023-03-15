# FormationJBOSSMars2023
Formation JBOSS  Mars 2023

##  Docs

- https://github.com/sanogotech/wildfly-lab

- https://www.jtips.info/WildFly/GettingStarted

- https://access.redhat.com/documentation/fr-fr/red_hat_jboss_enterprise_application_platform/7.3/pdf/configuration_guide/red_hat_jboss_enterprise_application_platform-7.3-configuration_guide-en-us.pdf

- http://www.mastertheboss.com/jboss-frameworks/jboss-maven/configuring-maven-wildfly-plugin/

- https://access.redhat.com/documentation/fr-fr/jboss_enterprise_application_platform/6.3/html/installation_guide/installation_structure

- Full vs Default : https://docs.wildfly.org/21/Getting_Started_Guide.html 

##  9h30 - 12h30 /  13h30 - 17h/16h30

- Pause : 10h45
- Pause : 15h

## INTRO

-   JAVA vs JEE
-   JAVA  EE :  https://docs.oracle.com/javaee/7/tutorial/overview007.htm
-   JBOSS/Wildfly  :  https://www.wildfly.org/downloads/
```
The technology behind WildFly is also available in JBoss Enterprise Application Platform 7. JBoss EAP is a hardened enterprise subscription with Red Hat’s world-class support, long multi-year maintenance cycles, and exclusive content.

```

##  Jour 1 TP

- Download  jboss wildfly :  https://www.wildfly.org/downloads/ 
- 21.0.2.Final  Jakarta EE Full & Web Distribution

```
$JBOSS_HOME/bin/standalone.bat  ou   /bin/standalone.sh  sur Linux
$JBOSS_HOME/bin/add-user.bat -u admin -p admin123 

```
## TP Jour 2

```
$ bin/standalone.bat  -Djboss.node.name=instance1
Pour le second serveur, il faut en plus faire un décalage de ports pour éviter les conflits.

$ bin/standalone.bat -Djboss.node.name=instance2 -Djboss.socket.binding.port-offset=100
```

Pour changer de profil, il y a plusieurs possibilités :

$ bin/standalone.bat -c standalone-full.xml

Mode haute disponibilité
Il existe deux exemples de configuration en mode haute disponibilité (ou plus simplement en mode maître - esclave) qui permet à une instance de reprendre là où s’est arrêtée l’autre. Il faudra par conte lancer les deux instances avec un décalage de ports afin d’éviter les conflits ainsi que le nom du noeud.

```
$ bin/standalone.sh -c standalone-full-ha.xml -Djboss.node.name=nodeA
$ bin/standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=500 -Djboss.node.name=nodeB
```
Dans l’exemple ci-dessus on a décalé les ports du second noeud de 500, ainsi le port http 8080 se retrouve en 8580.

** Datasource H2 DB 
git clone  https://github.com/sanogotech/JBOSSWildflyQuickstart.git
cd JBOSSWildflyQuickstart
cd kitchensink-ear

### Architecture NTIER

### Architecture JAVA JEE

### Serveur  d'Application vs Serveur Web

## Installation

https://access.redhat.com/documentation/fr-fr/red_hat_jboss_enterprise_application_platform/7.0/html-single/installation_guide/index#choosing_a_jboss_eap_installation_method

#Méthodes d'installation

* Installation ZIP

L'archive ZIP convient aux installations sur n'importe quel système d'exploitation pris en charge. Cette méthode devra être utilisée si vous souhaitez extraire l'instance manuellement.


* Installateur JAR

Le programme d'installation JAR peut être exécuté dans une console, ou en tant que qu'assistant graphique. Ces deux options fournissent des instructions étape par étape pour installer et configurer l'instance du serveur. Cette méthode d'installation est la méthode privilégiée pour installer JBoss EAP sur toutes les plateformes prises en charge.

*  Installation RPM

JBoss EAP peut être installé en utilisant des paquets RPM sur des installations prises en charge de Red Hat Enterprise Linux 6 et Red Hat Enterprise Linux 7.

* Docker

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


