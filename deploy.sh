#!/bin/bash

echo "Iniciando o deploy automático"

echo "1. Atualização da branch (pull)"
git pull

echo "2. Limpando e gerando package"
mvn clean package

echo "3. Movendo package.war para de deploy do tomcat"
mv target/teachermatch-0.0.1-SNAPSHOT.war /home/felipys/apps/apache-tomcat-8.5.56/webapps/ROOT.war

echo "4. Deploy finalizado com sucesso!!"