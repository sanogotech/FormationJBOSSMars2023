
Avec CLI, on travaille avec des commandes, des ressources (ou noeuds), des attributs et opérations sur ces ressources.

Aide sur une commande
Pour obtenir de l’aide sur une commande, il faut taper la commande puis --help. Par exemple :

[host:9990 /] deploy --help
Attention, ça marche bien pour les commandes, mais pas les opérations.

Aide sur une opération
Pour avoir de l’aide sur une opération, il faut utiliser la commande read-operation, en précisant le noeud et l’opération.

[host:9990 /] read-operation --node=/core-service=vault add


##  JBOSS CLI VS  XML CONFIG: standalone.xml

    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
        <socket-binding name="ajp" port="${jboss.ajp.port:8009}"/>
        <socket-binding name="http" port="${jboss.http.port:8080}"/>
        <socket-binding name="https" port="${jboss.https.port:8443}"/>
        <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:9990}"/>
        <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:9993}"/>
        <socket-binding name="txn-recovery-environment" port="4712"/>
        <socket-binding name="txn-status-manager" port="4713"/>
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="${jboss.mail.server.host:localhost}" port="${jboss.mail.server.port:25}"/>
        </outbound-socket-binding>
    </socket-binding-group>
	
	```
## One line 

/socket-binding-group=standard-sockets/socket-binding=http:write-attribute(name="port", value="8080")  
  

##  Full
You can do this using CLI. This example changes the port from 8081 to 8080:

Start CLI (in .../bin/):

$ ./jboss-cli.sh   
You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.  
Connect

[disconnected /] connect     
Change into the target area

[standalone@localhost:9999 /] cd /socket-binding-group=standard-sockets/socket-binding=http     
Show the current state:

[standalone@localhost:9999 socket-binding=http] ls -l  
ATTRIBUTE         VALUE     TYPE      
bound             true      BOOLEAN   
bound-address     127.0.0.1 STRING    
bound-port        8081      INT       
client-mappings   undefined LIST      
fixed-port        false     BOOLEAN   
interface         undefined STRING    
multicast-address undefined STRING    
multicast-port    undefined INT       
name              http      STRING    
port              8081      INT   
Change the port attribute:

[standalone@localhost:9999 socket-binding=http] :write-attribute(name="port", value="8080")  
{  
    "outcome" => "success",  
    "response-headers" => {  
        "operation-requires-reload" => true,  
        "process-state" => "reload-required"  
    }  
}  
Note that the process-state is "reload-required"

Look again:

[standalone@localhost:9999 socket-binding=http] ls -l                                        
ATTRIBUTE         VALUE     TYPE      
bound             true      BOOLEAN   
bound-address     127.0.0.1 STRING    
bound-port        8081      INT       
client-mappings   undefined LIST      
fixed-port        false     BOOLEAN   
interface         undefined STRING    
multicast-address undefined STRING    
multicast-port    undefined INT       
name              http      STRING    
port              8080      INT       
Note that here as well the bound-port is still at the old value.

So go back to the root directory

[standalone@localhost:9999 subsystem=web] cd /  
Reload

[standalone@localhost:9999 /] :reload  
{  
    "outcome" => "success",  
    "response-headers" => {"process-state" => "reload-required"}  
}  
That means that the reload is still in progress, again

[standalone@localhost:9999 /] :reload  
{"outcome" => "success"}  
Now the HTTP connector should listen on the new port.

Update

The question asks for changing the port dynamically (JBoss is up and running). The other option is to write the port into the configuration file (standalone.xml). This is statically, but it will probably work as well as for installation purposes.