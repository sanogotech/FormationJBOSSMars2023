##  TOP 10  JBBOSC CLI

https://access.redhat.com/documentation/fr-fr/red_hat_jboss_enterprise_application_platform/7.2/html/management_cli_guide/getting_started_management_cli

jboss-cli.sh -c 


jboss-cli.bat  --connect

help
- deploy  my.ear
- undeploy my.ear
- :reload
- :shutdown
- ls
- cd
help --commands
deployment-info
deploy --help


EAP_HOME/bin/jboss-cli.sh --connect --file=/path/to/cli_commands.txt

[[jboss-cli]]
== Management using Command Line Interface (jboss-cli)

*Purpose*: This section explains how to manage WildFly using jboss-cli.

WildFly comes with a Command Line Interface management tool for a standalone server or a managed domain. This script is in the `bin` directory of WildFly distrbution and can be invoked by calling `jboss-cli.sh` for Mac/Linux based systems or `jboss-cli.bat` for Windows. It allows a user to connect to a standalone server or domain controller and execute management operations available through the management model.

=== Connecting to a server

. Start a WildFly standalone server, if not already running, using the following command:
+
[source]
----
standalone.sh
----
+
. Use `jboss-cli' to connect with this instance by giving the following command:
+
[source]
----
jboss-cli.sh -c
----
+
The `-c` switch connects using the default host (`localhost') and management port (`9990'). These values are specified in `bin/jboss-cli.xml' and can be updated.
+
This opens up the `jboss-cli' interactive console and shows the following prompt:
+
[source]
----
[standalone@localhost:9990 /]
----
+
The prompt indicates that `jboss-cli' is connected to a standalone instance's default management port.
+
. If WildFly instance is running on a different host and/or port, then `--controller` switch can be used to specify that information.
+
.. In another shell, start another WildFly instance on a different port using the following command:
+
[source]
----
./bin/standalone.sh -Djboss.socket.binding.port-offset=10000
----
+
This will start another WildFly standalone instance on application port 18080 and management port 19090.
+
.. Use `jboss-cli' to connect with this instance by giving the following command:
+
[source]
----
jboss-cli.sh -c --controller=localhost:19990
----
+
This opens up the `jboss-cli' interactive console and shows the following prompt:
+
[source]
----
[standalone@localhost:19990 /]
----
+
The prompt indicates that `jboss-cli' is connected to a standalone instance on port `19990'.
+
.. Type `exit' or `quit' in the console to exit.

=== Resources, Operations, and Commands

WildFly internal management model consists of _management resources_ that that are added, removed, or modified by using _operations_ and _commands_. Operations are low level but a comprehensive way to manage the server. Commands are more user-friendly, although most of them still translate into operation requests and some of them even into a few composite operation requests.

All resources are organized in a tree. The path to the node in the tree for a particular resource is its _address_ and is identified by `/`.

Each resource expose information about their state as _attributes_.

Each resource may support child resources.

All resources expose metadata that describes their attributes, operations, and child types. This metadata can be queried by invoking one or more of the _global operations_ supported by the resource.

Command and operation request history is enabled by default. While in the command line session, you can use the arrow keys to go back and forth in the history of commands and operations.

==== Operations

. The operations require one of the following prefixes:
+
.. `:` to execute against the current node
+
Type `:` at the prompt and press tab key to see a complete list of operations. This will show the following output:
+
[source]
----
[standalone@localhost:9990 /] :
add-namespace                read-operation-names         take-snapshot                
add-schema-location          read-resource                undefine-attribute           
delete-snapshot              read-resource-description    upload-deployment-bytes      
full-replace-deployment      reload                       upload-deployment-stream     
list-snapshots               remove-namespace             upload-deployment-url        
read-attribute               remove-schema-location       validate-address             
read-children-names          replace-deployment           validate-operation           
read-children-resources      resolve-expression           whoami                       
read-children-types          resolve-internet-address     write-attribute              
read-config-as-xml           server-set-restart-required  
read-operation-description   shutdown 
----
+
TIP: Operations can be auto completed by using the tab key. For example, type `:r` at the prompt and press tab key to see the list of operations beginning with that letter.
+
Read a simple attribute using `read-attribute` operation as shown:
+
[source]
----
[standalone@localhost:9990 /] :read-attribute(name=release-version)
----
+
This will show the output as:
+
[source]
----
{
    "outcome" => "success",
    "result" => "8.0.0.Final-SNAPSHOT"
}
----
+
.. `./` to execute against a child node of the current path
+
[source]
----
[standalone@localhost:9990 deployment] ./javaee7-1.0-SNAPSHOT.war:read-resource
----
+
This command reads resource's attribute, the deployed war file in this case, values along with either basic (default) or complete information about any child resources. Detailed information about the resource can be obtained by adding `recursive=true` parameters as:
+
[source]
----
[standalone@localhost:9990 /deployment] ./javaee7-1.0-SNAPSHOT.war:read-resource(recursive=true)
----
+
.. `/` to execute an operation against the root node
+
[source]
----
[standalone@localhost:9990 /] /socket-binding-group=standard-sockets/socket-binding=http:read-attribute(name=bound-port)
----
+
This command traverses a path and reads an attribute on the leaf node. In this case it reads the port and displays the output as:
+
[source]
----
{
    "outcome" => "success",
    "result" => 8080
}
----
+
. Read the complete list of operations supported by a resource by typing `read-operation-names` at the prompt and see the output as:
+
[source]
----
[standalone@localhost:9990 /] :read-operation-names
{
    "outcome" => "success",
    "result" => [
        "add-namespace",
        "add-schema-location",
        "delete-snapshot",
        "full-replace-deployment",
        "list-snapshots",
        "read-attribute",
        "read-children-names",
        "read-children-resources",
        "read-children-types",
        "read-config-as-xml",
        "read-operation-description",
        "read-operation-names",
        "read-resource",
        "read-resource-description",
        "reload",
        "remove-namespace",
        "remove-schema-location",
        "replace-deployment",
        "resolve-expression",
        "resolve-internet-address",
        "server-set-restart-required",
        "shutdown",
        "take-snapshot",
        "undefine-attribute",
        "upload-deployment-bytes",
        "upload-deployment-stream",
        "upload-deployment-url",
        "validate-address",
        "validate-operation",
        "whoami",
        "write-attribute"
    ]
}
----
+
. A number of operations can be applied to every resource. Such operations are called _global operations_. `read-operation-names` is one such global operation. Another commonly used global operation is `read-resource` that reads resource's attribute values along with either basic or complete information about any child resources.
+
. Read complete detail about `read-operation-description` operation by giving the command:
+
[source]
----
[standalone@localhost:9990 /] :read-operation-description(name=read-operation-names)
----
+
and see the output as:
+
[source]
----
{
    "outcome" => "success",
    "result" => {
        "operation-name" => "read-operation-names",
        "description" => "Gets the names of all the operations for the given resource",
        "request-properties" => {"access-control" => {
            "type" => BOOLEAN,
            "description" => "If 'true' only operations the user is allowed to see are returned, and filtered operations are listed in the 'access-control' response header.",
            "expressions-allowed" => false,
            "required" => false,
            "nillable" => true,
            "default" => false
        }},
        "reply-properties" => {
            "type" => LIST,
            "value-type" => STRING,
            "description" => "The operation names"
        },
        "read-only" => true
    }
}
----
+
Try this operation for some other operations and read the output.

==== Commands

. Type `help --commands` at the jboss-cli prompt to see a complete list of commands available in current context. This will show the following output:
+
[source]
----
[standalone@localhost:9990 /] help --commands
alias               deploy              if                  read-attribute      undeploy            
batch               deployment-info     jdbc-driver-info    read-operation      unset               
cd                  deployment-overlay  ls                  reload              version             
clear               echo                module              run-batch           xa-data-source      
command             echo-dmr            patch               set                 :                   
connect             help                pwd                 shutdown            
data-source         history             quit                try  
----
+
This can also be achieved by pressing the tab key at the prompt. The list of commands depends upon the current context, i.e. it may change based upon the node address in the domain management model.
+
TIP: Commands can be auto completed by using the tab key. For example, type letter `d` at the prompt and press tab key to see the list of commands beginning with that letter. Enter space after choosing the command and press tab key again to see the list of arguments to the command.
+
. Help for any command is available by typing the command name and using `--help` option. For example:
+
[source]
----
[standalone@localhost:9990 /] deploy --help
----


+
will show the following output:
+
[source]
----
SYNOPSIS

    deploy ((file_path | --url=deployment_url)
               [--script=script_name] [--name=deployment_name]
               [--runtime-name=deployment_runtime_name]
               [--force | --disabled] [--unmanaged])
           | --name=deployment_name
           [--server-groups=group_name (,group_name)* | --all-server-groups]
           [--headers={operation_header (;operation_header)*}]

DESCRIPTION

  Deploys the application designated by the file_path or enables an already
  existing but disabled in the repository deployment designated by the name
  . . .
----
+
. `ls` command list the contents of a node path including node types and attributes. Giving this command on the root node shows the following output:
+
[source]
----
[standalone@localhost:9990 /] ls
core-service                          management-minor-version=0            
deployment                            name=aruns-macbook-pro                
deployment-overlay                    namespaces=[]                         
extension                             process-type=Server                   
interface                             product-name=undefined                
path                                  product-version=undefined             
socket-binding-group                  profile-name=undefined                
subsystem                             release-codename=WildFly              
system-property                       release-version=8.0.0.Final-SNAPSHOT  
launch-type=STANDALONE                running-mode=NORMAL                   
management-major-version=2            schema-locations=[]                   
management-micro-version=0            server-state=running
----
+
All entries with name/value pairs are attributes and every thing else is a node.
+
. `cd` command changes the current node path to the specified argument.
+
Change the path to `management' node by typing the command:
+
[source]
----
[standalone@localhost:9990 /] cd core-service=management
[standalone@localhost:9990 core-service=management]
----
+
The command line prompt in the first line shows that the command was issued from the root node. The prompt in the second line shows the updated node name.
+
. Deploy an application and check its status by typing the following commands:
+
[source]
----
[standalone@localhost:9990 /] deploy ~/workspaces/wildfly-lab/samples/javaee7/target/javaee7-1.0-SNAPSHOT.war --force
[standalone@localhost:9990 /] deployment-info 
NAME                     RUNTIME-NAME             PERSISTENT ENABLED STATUS 
javaee7-1.0-SNAPSHOT.war javaee7-1.0-SNAPSHOT.war true       true    OK
----
+
. Change the HTTP application port from a default value of 8080 to 8090 by giving the following command:
+
[source]
----
[standalone@localhost:9990 /] /socket-binding-group=standard-sockets/socket-binding=http:write-attribute(name=port,value=8090)
----
+
and see the output as:
+
[source]
----
{
    "outcome" => "success",
    "response-headers" => {
        "operation-requires-reload" => true,
        "process-state" => "reload-required"
    }
}
----
+
The command output indicates that the server should be reloaded. This can be achieved by typing `reload` command at the prompt.
Now the application is accessible at http://8090/javaee7-1.0-SNAPSHOT/EmployeeList instead of the port 8080.
+
Any change to the management model is persisted to the configuration file. Lets change the port back to 8080 by giving the following command:
+
[source]
----
[standalone@localhost:9990 /] /socket-binding-group=standard-sockets/socket-binding=http:write-attribute(name=port,value=8080)
----

=== Batch

The batch mode allows one to group commands and operations and execute them together as an atomic unit, i.e., if at least one of the commands or operations fails, all the other successfully executed commands and operations in the batch are rolled back.

Only the commands that translate into operation requests are allowed in the batch. The batch, actually, translates into a 'composite' operation request.

Batch mode can be composed interactively using jboss-cli prompt or non-interactively where the set of commands and operations are saved in a file and loaded at the prompt.

==== Interactive Batch

. Start batch mode by typing the `batch` command:
+
[source]
----
[standalone@localhost:9990 /] batch
[standalone@localhost:9990 / #]
----
+
The prompt changes to `#` indicating that the CLI is in batch mode.
+
. Enter the operations and commands that need to be included in batch:
+
[source]
----
[standalone@localhost:9990 / #] data-source add --name=myDataSource --jndi-name=java:jboss/datasources/MyDataSource --user-name=sa --password=sa --driver-name=h2 --connection-url=jdbc:h2:mem:myData
[standalone@localhost:9990 / #] deploy ~/workspaces/wildfly-lab/samples/javaee7/javaee7-1.0-SNAPSHOT.war
----
+
This command is creating a JDBC resource and deploys an application that uses it.
+
. Finally run the commands entered in the batch by giving the following command:
+
[source]
----
[standalone@localhost:9990 / #] run-batch
----
+
If the command is executed successfully then it is discarded and the CLI leaves the batch mode. If any of the command or operation in the batch fails then the CLI gives an error and all steps executed so far are rolled back.

==== Non-interactive Batch

Non-interactive batch is useful for set of commands and operations that are executed frequently. Such commands and operations can be saved to a file and later used as argument to `batch' command.

. Save the following commands in a text file:
+
[source]
----
/subsystem=datasources/data-source="java:jboss/datasources/MyDataSource":add(jndi-name="java:jboss/datasources/MyDataSource", driver-name="h2", connection-url="jdbc:h2:mem:myData", user-name="sa", password="sa")
deploy ~/workspaces/wildfly-lab/samples/javaee7/target/javaee7-1.0-SNAPSHOT.war
----
+
and save the file as `myScript.txt'.
+
. Run the interactive CLI as:
+
[source]
----
standalone.sh
----
+
. Load the script file using `batch` command:
+
[source]
----
[standalone@localhost:9990 /] batch --file=myScript.txt
----
+
+ Run the batch using the `run-job` command.
+
Alternatively, the file may be loaded and executed in one command:
+
[source]
----
[standalone@localhost:9990 /] run-batch --file=myScript.txt
----

=== Environment variables

CLI supports variables and are resolved during command line parsing phase. They are useful to store frequently used nodepaths, complex commands or operations, or any other text that needs a shorter and easy to use name.

Variables set during a CLI session are not persisted when the session is terminated. The variables may be stored in `.jbossclirc` in which case they are persisted across different sessions.

==== Non-persistent Variables

. Set a new variable as:
+
[source]
----
[standalone@localhost:9990 /] set default_port=/socket-binding-group=standard-sockets/socket-binding=http:read-attribute(name=bound-port)
----
+
This code defines a new variable `default_port` and sets its value to the defined operation. Variable names are expected to follow Java identifier format.
+
. Use this variable in CLI to execute the command:
+
[source]
----
[standalone@localhost:9990 /] $default_port
----
+
to see the output as:
+
[source]
----
{
    "outcome" => "success",
    "result" => 8090,
    "response-headers" => {"process-state" => "reload-required"}
}
----

==== Persistent Variables

Persistent variables are stored in `.jbossclirc` file. The location of this file is checked in the following order:

. value of system property `jboss.cli.rc`
. user's working directory (as defined by `user.dir` system property)
. `bin` directory

A default `.jbossclirc` is already included in the `bin` directory and can be used as a template for user-specific environment setup.

The file contains `set' commands to define the variables, such as:
[source]
----
set default_port=/socket-binding-group=standard-sockets/socket-binding=http:read-attribute(name=bound-port)
----

=== GUI

CLI can be started with a GUI instead of a command line. It allows you to browse through different nodes and commands and operations supported on a node. Commands are automatically created and can be submitted to the server. Applications can be deployed and undeployed as well.

. Type the following command to start CLI with GUI:
+
[source]
----
standalone.sh --gui
----
+
The complete domain model is shown in a separate window as:
+
image::images/jboss-cli-gui.png[title="jboss-cli GUI"]
+
. Right-click on any node to see the list of supported operations as shown:
+
image::images/jboss-cli-gui-operations.png[title="Operations on nodes"]
+
The command is dynamically created and populated in the `cmd>' text box.
+
. This command can be submitted to the server by clicking on `Submit' button. Command output is shown:
+
image::images/jboss-cli-gui-output.png[title="jboss-cli GUI output"]
+
. Type `serv' in `Filter' box to search for any nodes and attributes that contains this phrase. The output is shown as:
+
image::images/jboss-cli-gui-filter.png[title="Filter on GUI"]

