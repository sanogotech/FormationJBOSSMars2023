
## LOG LEVEL

There are six basic log levels in log4j: TRACE, DEBUG, INFO, WARN, ERROR and FATAL. 

The logging threshold on the console is INFO, so that any informational messages, warning messages and error messages are returned to the console, while general debug and trace messages are only available in the log file. If no logging level is set for the server.log file, it defaults to DEBUG.

* https://kb.novaordis.com/index.php/WildFly_Logging_Subsystem_Concepts

## Configuration des logs avec   Jboss CLI
```
jboss-cli.bat  -c
```

1. Creer un  Handler de Type  Fichier: File

```
/subsystem=logging/periodic-rotating-file-handler=my-apphandler:add(append=true, autoflush=true, suffix=".yyyy-MM-dd", file={"relative-to"=>"jboss.server.log.dir", "path"=>"myapp.log"})
```

2. Creer une categorie pour l'application

```
/subsystem=logging/logger=it.fixx:add(use-parent-handlers=false, handlers=["my-apphandler"])

```
3. Set log level

```
/subsystem=logging/file-handler=my-apphandler:change-log-level(level="INFO")
```


4. Reload

:reload

##  Sample Code
* https://github.com/sanogotech/helloear/blob/master/HelloWorld-web/src/main/java/it/fixx/helloworld/controller/MemberController.java

```java
import it.fixx.helloworld.model.Member;
import it.fixx.helloworld.service.MemberRegistration;

import org.jboss.logging.Logger;


//@SessionScoped
//public class MemberController  implements Serializable
public class MemberController {
	
	private static Logger log = Logger.getLogger(MemberController.class.getName());

    @Inject
    private FacesContext facesContext;

    @Inject
    private MemberRegistration memberRegistration;

    private Member newMember;

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() throws Exception {
        try {
			log.info("Start ... Registration ***");
            memberRegistration.register(newMember);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
            initNewMember();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
            facesContext.addMessage(null, m);
        }
    }


    
}
```
##  Test du Code 

git clone  https://github.com/sanogotech/helloear.git
mvn clean package
deployer ear pour voir les logs.

http://127.0.0.1:8090/HelloWorld-web/index.jsf

##  Framework de log
- org.jboss.logging.Logger;
- log4j
- logback.xml

## Divers  PATH Defaut ?  "jboss.server.log.dir"
- Voir dans la console admin web
