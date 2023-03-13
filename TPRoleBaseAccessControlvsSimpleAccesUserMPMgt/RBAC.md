#RBAC

Pour se connecter à un autre serveur WildFly :

$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=myserver:10099
Pour démarrer en mode non-interactif, et exécuter un script :

$JBOSS_HOME/bin/jboss-cli.sh --connect --file=myscript.cli


Vocabulaire
Avec cli, on travaille avec des commandes, des ressources (ou noeuds), des attributs et opérations sur ces ressources.

Aide sur une commande
Pour obtenir de l'aide sur une commande, il faut taper la commande puis --help. Par exemple :

* deploy --help
Attention, ça marche bien pour les commandes, mais pas les opérations.

Aide sur une opération
Pour avoir de l'aide sur une opération, il faut utiliser la commande read-operation, en précisant le noeud et l'opération.

* read-operation --node=/core-service=vault add
Commandes en standalone (management)
Cette partie concerne la configuration des accès aux outils de gestions (jboss-cli, web console, APIs).

Accès local
L'accès en localhost avec jboss-cli peut se faire sans authentification. Si cette possibilité vous gène, vous pouvez la désactiver :

/core-service=management/security-realm=ManagementRealm/authentication=local:remove
reload
Après ça, même en localhost, jboss-cli demandera une authentification.

Gestion des droits
Pour activer le système d'autorisations RBAC :

/core-service=management/access=authorization:write-attribute(name=provider,value=rbac)
Pour revenir au fonctionnement par défaut :

/core-service=management/access=authorization:write-attribute(name=provider,value=simple)
Dans ce fonctionnement, pour qu'un utilisateur ait le droit d'accéder aux outils de management, il faut lui associer un de rôles prédéfinis.

Il est possible d'associer un rôle à tous les utilisateurs.

/core-service=management/access=authorization/role-mapping=Monitor:add(include-all=true)
On peut aussi associer explicitement des utilisateurs à un rôle.

/core-service=management/access=authorization/role-mapping=Administrator/include=alexis:add(type=USER, name=alexis)
Enfin, on peut associer des groupes d'utilisateurs à un rôle.

/core-service=management/access=authorization/role-mapping=Administrator/include=app-admin:add(type=USER, name=app-admin)
Dans la configuration initiale, les groupes d'utilisateurs sont définis dans le fichier standalone/configuration/mgmt-groups.properties.
