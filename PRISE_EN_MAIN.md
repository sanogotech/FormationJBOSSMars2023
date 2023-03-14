
## 0. Download JBOSS

- JBOSS/Wildfly : https://www.wildfly.org/downloads/
- Version : 21.0.2.Final  Jakarta EE Full & Web Distribution	LGPL	zip


## 1. Test Rapide du Zip JBOSS WildFly

- Unzip   C:/tools/wildfly-21.0.2.Final
- Lancer le standalone.bat  avec  C:/tools/wildfly-21.0.2.Final/bin/standalone.bat

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

- Maven
- mvn  clean package
- lien vers /target/*.war
- deposer /sampleswarear

#  Manual  Deploy
- starter.war
- hello-ear

# Projet avec datasource
- projet datasource h2
- projet avec mysql
- download  driver mysql
- lancer  wampserver
- créer la base "books"

## TOP 10  JBOSS-CLI
- Top
- jboss-cli.bat --file





