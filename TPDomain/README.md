
## Sanmple Config  Domain
git clone  https://github.com/sanogotech/jboss-eap7-domains-labs


- https://developers.redhat.com/blog/2016/07/28/jboss-eap-7-domain-deployments-part-1-setup-a-simple-eap-domain#install_jboss_eap

# TP  Domain:

Domaine Jboss/Wildfly  = Instances Wildfly   + Controleur de domaine'master' + Controleur d’hote'slave' .//Serveur Group profile standalone, full, ha

* Creer une nouvelle installation/zip de Wildfly pour le TP

*  dans HOMEWILDFLY/bin :

-  add-user.bat  -u  admin -p  admin123

- lancer  domain.bat 

-  Verififier la création du repertoire ‘servers’ dans le répertoire ‘domain’

- Compter les process java sur la machine /

* https://developers.redhat.com/blog/2016/07/28/jboss-eap-7-domain-deployments-part-1-setup-a-simple-eap-domain/

* https://developers.redhat.com/blog/2016/08/05/jboss-eap-7-domain-deployments-part-2-domain-deployments-through-the-eap-7-0-management-console/

## CLI

jboss-cli.bat --connect command=/host=master:shutdown
