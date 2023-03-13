https://access.redhat.com/documentation/en-us/jboss_enterprise_application_platform/6/html/administration_and_configuration_guide/configure_a_file_log_handler_in_the_cli1

https://examples.javacodegeeks.com/enterprise-java/jboss-wildfly/jboss-wildffly-logging-configuration-example/

https://examples.javacodegeeks.com/enterprise-java/jboss-wildfly/jboss-wildffly-logging-configuration-example/

1.  Creer un  FileHandler  qui pointe vers fichier et preciser le level

2.  Creer un logger qui pointe vers le fileHandler  et  logger category="com.jcg"   

Standalone-full.xml


<periodic-rotating-file-handler name="MY_HANDLER" autoflush="true">
  <formatter>
    <named-formatter name="PATTERN"/>
  </formatter>
  <file relative-to="jboss.server.log.dir" path="jboss-wildfly-netbeans-example.log"/>
  <suffix value=".yyyy-MM-dd"/>
  <append value="true"/>
</periodic-rotating-file-handler>
<logger category="com.jcg" use-parent-handlers="false">
  <level name="INFO"/>
  <handlers>
    <handler name="MY_HANDLER"/>
  </handlers>
</logger>
--------------------------------
import org.jboss.logging.Logger;

private static Logger log = Logger.getLogger(GreetingsBean.class.getName());

log.info("Created GreetingsBean instance...");