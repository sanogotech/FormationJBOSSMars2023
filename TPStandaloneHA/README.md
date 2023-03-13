standalone.bat -Djboss.socket.binding.port-offset=1000  

standalone.bat -Djboss.http.port=8090

Mode haute disponibilité:

bin/standalone.bat -c standalone-full-ha.xml -Djboss.node.name=nodeA
bin/standalone.bat  -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=500 -Djboss.node.name=nodeB

Dans l'exemple ci-dessus on a décalé les ports du second noeud de 500, ainsi le port http 8080 se retrouve en 8580