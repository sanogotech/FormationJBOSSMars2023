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
