

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
