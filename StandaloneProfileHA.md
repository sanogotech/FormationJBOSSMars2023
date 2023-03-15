## Docs

- http://docs.wildfly.org/12/High_Availability_Guide.html

##   Standalone Profiles

What is the difference between the four standalone-*.xml files provided in JBoss EAP ?
What is the difference between the four profiles "default", "full", "ha" and "full-ha" in domain mode in JBoss EAP ?
Which profile supports clustering support for JMS?
What is the difference between the standalone-full-ha.xml, standalone-full.xml,standalone.xml files in JBoss EAP ?

* standalone.xml: Support of Java EE Web-Profile plus some extensions like RESTFul Web Services and support for EJB3 remote invocations
* standalone-full.xml: Support of Java EE Full-Profile and all server capabilities without clustering
* standalone-ha.xml: Default profile with clustering capabilities
* standalone-full-ha.xml: Full profile with clustering capabilities


## What are High Availability services?
WildFly’s High Availability services are used to guarantee availability of a deployed Java EE application.

Deploying critical applications on a single node suffers from two potential problems:

loss of application availability when the node hosting the application crashes (single point of failure)

loss of application availability in the form of extreme delays in response time during high volumes of requests (overwhelmed server)

WildFly supports two features which ensure high availability of critical Java EE applications:

fail-over: allows a client interacting with a Java EE application to have uninterrupted access to that application, even in the presence of node failures

load balancing: allows a client to have timely responses from the application, even in the presence of high-volumes of requests


# Mode standard

standalone.bat   ou  /standalone.bat -c standalone.xml 


$ standalone.sh -c standalone-full.xml

standalone.bat -Djboss.socket.binding.port-offset=1000  

# Mode haute disponibilité: HA


bin/standalone.bat -c standalone-full-ha.xml -Djboss.node.name=nodeA


bin/standalone.bat  -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=500 -Djboss.node.name=nodeB

Dans l'exemple ci-dessus on a décalé les ports du second noeud de 500, ainsi le port http 8080 se retrouve en 8580


# Mode stantard vs full vs ha vs full-ha
***Mode haute disponibilité
Il existe deux exemples de configuration en mode haute disponibilité (ou plus simplement en mode maître - esclave) qui permet à une instance de reprendre là où s'est arrêtée l'autre. Il faudra par conte lancer les deux instances avec un décalage de ports afin d'éviter les conflits ainsi que le nom du noeud.

bin/standalone.sh -c standalone-full-ha.xml -Djboss.node.name=nodeA
bin/standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=500 -Djboss.node.name=nodeB
