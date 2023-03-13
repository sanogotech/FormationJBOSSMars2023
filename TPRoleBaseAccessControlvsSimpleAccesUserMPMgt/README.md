

- Simple Acess managemnet = unimaquement user management avec user/password

- Role base access controle =  Users/ Groupes/  Role / Right
******
0.  Regarder standalone.xml  et mgmt-users.properties /mgmt-groups.properties

1.  Passer de tester et crere les deux groupes

	<access-control provider="rbac">
            <role-mapping>
                <role name="SuperUser">
                    <include>
                        <user name="$local"/>
                        <user realm="ManagementRealm" name="toto"/>
                    </include>
                </role>
                
            </role-mapping>
        </access-control>
		
		Avant ******:
		
		 <management-interfaces>
            <http-interface security-realm="ManagementRealm">
                <http-upgrade enabled="true"/>
                <socket-binding http="management-http"/>
            </http-interface>
        </management-interfaces>
        <access-control provider="simple">
            <role-mapping>
                <role name="SuperUser">
                    <include>
                        <user name="$local"/>
                    </include>
                </role>
            </role-mapping>
        </access-control>
    

2.  Create  avec  :

- add-user.bat -u  tata  -p  tata  -g  developers// groupe avec le role de Monitor   / groupe developers
- add-user.bat -u  titi  -p  titi  -g   lead-developers // groupe avec le role de Deployer  // groupe  lead-developers
- add-user.bat -u  toto  -p  toto  // role supperuser  sans groupe 
- add-user.bat -u  wildfly  -p  wildfly # pas de groupe

2. Verifier dans mgmt-users.properties

3. Connaitre les Roles:

Four roles are not given permissions for "security sensitive" items:

Monitor – a read-only role. Cannot modify any resource.
Operator – Monitor permissions, plus can modify runtime state, but cannot modify anything that ends up in the persistent configuration. Could, for example, restart a server.
Maintainer – Operator permissions, plus can modify the persistent configuration.
Deployer – like a Maintainer, but with permission to modify persistent configuration constrained to resources that are considered to be "application resources". A deployment is an application resource. The messaging server is not. Items like datasources and JMS destinations are not considered to be application resources by default, but this is configurable.

Three roles are granted permissions for security sensitive items:

SuperUser – has all permissions. Equivalent to a JBoss AS 7 administrator.
Administrator – has all permissions except cannot read or write resources related to the administrative audit logging system.
Auditor – can read anything. Can only modify the resources related to the administrative audit logging system.

4. Passer de 


6.  Creer les groupes 
*  / role de Monitor   / groupe developers
* role de Deployer  // groupe  lead-developers

add-user.bat -u  tete -p tete -g developers

5. 

	<access-control provider="rbac">
        <role-mapping>
            <role name="SuperUser">
                <include>
                    <user name="$local"/>
                    <user realm="ManagementRealm" name="toto"/>
				   
                </include>
            </role>
            <role name="Deployer">
                <include>
                    <group alias="group-lead-devs" name="lead-developers"/>
                </include>
            </role>
            <role name="Monitor">
                <include>
                    <group alias="group-standard-devs" name="developers"/>
                </include>
            </role>
        </role-mapping>
    </access-control>

******
https://blog.soat.fr/2016/03/les-bases-de-la-securite-en-developpement-java-ee/
*** Apres
<access-control provider="rbac">
            <role-mapping>
                <role name="SuperUser">
                    <include>
                        <user name="$local"/>
                        <user realm="ManagementRealm" name="toto"/>
                    </include>
                </role>
            </role-mapping>
</access-control>

-------------------

/core-service=management/management-interface=http-interface:write-attribute(name=allowed-origins,value=["http://localhost:9990"])

*********************

he HTTP API Endpoint serves two different contexts. One for executing management operations and another one that allows you to access the web interface:

Domain API: http://<host>:9990/management
Web Console: http://<host>:9990/console

http://hpehl.info/standalone-management-console.html

Console Configuration
When you open the console it detects whether it is part of a WildFly instance or launched independently. In the latter case you need to specify a management interface you like to connect to. You can manage a list of different interfaces running on different WildFly instances. The configuration is stored in the browser’s local storage, so it’s available the next time you open the console.

Say you want access your local WildFly instance using the HAL management console 2.6.5.Final served from the build proxy. In order to do so, follow these steps:

Point your browser to http://access-halproject.rhcloud.com/release/2.6.5.Final

Click ‘Add’ to configure a management endpoint.

- JBOSS CLI  Command to  pass to Simple acces control to role base acces control
https://access.redhat.com/documentation/en-us/jboss_enterprise_application_platform/6.2/html/security_guide/enabling_role-based_access_control

/core-service=management/access=authorization:write-attribute(name=provider, value=rbac)