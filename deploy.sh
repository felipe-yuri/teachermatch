#!/bin/bash

echo "Iniciando o deploy automático"

echo "1. Limpando e gerando package"
mvn clean package

echo "Movendo package.war para de deploy do tomcat"
mv target/teachermatch-0.0.1-SNAPSHOT.war /home/felipys/apps/apache-tomcat-8.5.56/webapps/ROOT.war

echo "Deploy finalizado com sucesso!!"