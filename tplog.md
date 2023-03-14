

0. Lancer  Jboss CLI
```
jboss-cli.bat  -c
```

1. Creer un  Handler de Type  Fichier: File

```
/subsystem=logging/periodic-rotating-file-handler=my-apphandler:add(append=true, autoflush=true, suffix=".yyyy-MM-dd", file={"relative-to"=>"jboss.server.log.dir", "path"=>"myapp.log"})
```

2. Creer une categorie pour l'application

```
/subsystem=logging/logger=com.jcg:add(use-parent-handlers=false, handlers=["my-apphandler"])

```

##  Sample Code

```java
package com.jcg.wildflyexample;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.jboss.logging.Logger;

/**
 *
 * @author Satya Choudhury
 */
@Named(value = "greetingsBean")
@RequestScoped
public class GreetingsBean {

    private String userName = "";
    private static Logger log = Logger.getLogger(GreetingsBean.class.getName());
    
    /**
     * Creates a new instance of GreetingsBean
     */
    public GreetingsBean() {
        //System.out.println("Created GreetingsBean instance...");
        log.info("Created GreetingsBean instance...");
    }
    
    public String getUserName() {
        return this.userName.trim();
    }
    
    public void setUserName(String userName) {
        this.userName = userName.trim();
    }
    
    public String greetUser() {
        return "greeting";
    }
    
}
```
##  Framework de log
- org.jboss.logging.Logger;
- log4j
- logback.xml
