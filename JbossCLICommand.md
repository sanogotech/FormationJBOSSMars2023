L'outil CLI permet d'effectuer toutes les tâches d'administration de WildFly en ligne de commande.
Attention : certaines commandes sont trop longues pour être affichées sur une seule ligne 
 dans ce cas, je les ai affichées sur plusieurs lignes, avec '\' comme signe de continuation.
 
 **** http://www.jtips.info/WildFly/cli   ***
 http://www.jtips.info/WildFly/cli#commandes-en-domaine
--------------------------------
* jboss-cli.bat
You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.
[disconnected /]

--------
* exit
---------
* help

Here are some of the most basic supported commands:

 cn (or cd)             - change the current node path to the argument;
 connect                - connect to the server or domain controller;
 deploy                 - deploy an application;
 help (or h)            - print this message;
 history                - print or disable/enable/clear the history expansion;
 ls                     - list the contents of the node path;
 pwn (or pwd)           - prints the current working node;
 quit (or q)            - quit the command line interface;
 undeploy               - undeploy an application;
 version                - prints the version and environment information.
---------------
Avec cli, on travaille avec des commandes, des ressources (ou noeuds), des attributs et opérations sur ces ressources.

* connect --help

 To connect automatically after the launch, use '--connect' switch. E.g.

  jboss-cli.sh --connect
  jboss-cli.sh --connect controller=controller-host.net
  jboss-cli.sh --connect controller=controller-host.net:1234
  jboss-cli.sh --connect controller=remote://controller-host.net:1234
  jboss-cli.sh --connect controller=http-remoting://controller-host.net:1234

  The host may be any of these formats:
  - a host name, e.g. localhost
  - an IPv4 address, e.g. 127.0.0.1
  - an IPv6 address, e.g. [::1]

  Controller addresses can be aliased and stored in the jboss-cli.xml config
  file. Aliases can later be used as arguments for the connect command.


ARGUMENTS

  protocol  - optional, default value is http.

  host      - optional, default value is localhost.

  port      - optional, default value is 9990.
  
------------------------------------------------
Pour se connecter au serveur WildFly local, démarré dans port-offset :

$JBOSS_HOME/bin/jboss-cli.sh --connect
En réalité, cette commande connecte jboss-cli au
 serveur définit comme serveur par défaut dans $JBOSS_HOME/bin/jboss-cli.xml
 
 ----------------
 
 Pour se connecter à un autre serveur WildFly :

$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=myserver:10099
Pour démarrer en mode non-interactif, et exécuter un script :

$JBOSS_HOME/bin/jboss-cli.sh --connect --file=myscript.cli

--------------------------
Mode stantard vs full vs ha vs full-ha
***Mode haute disponibilité
Il existe deux exemples de configuration en mode haute disponibilité (ou plus simplement en mode maître - esclave) qui permet à une instance de reprendre là où s'est arrêtée l'autre. Il faudra par conte lancer les deux instances avec un décalage de ports afin d'éviter les conflits ainsi que le nom du noeud.

bin/standalone.sh -c standalone-full-ha.xml -Djboss.node.name=nodeA
bin/standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=500 -Djboss.node.name=nodeB