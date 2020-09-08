#!/bin/bash

echo "\n"
echo "Iniciando o deploy automático"
echo "\n"

echo "1. Atualização da branch (pull)"
echo "\n"
git pull
echo "\n"

echo "2. Limpando e gerando package"
echo "\n"
mvn clean package
echo "\n"

echo "3. Movendo package.war para a pasta de deploy do tomcat"
echo "\n"
mv target/teachermatch-0.0.1-SNAPSHOT.war /home/felipys/apps/apache-tomcat-8.5.56/webapps/ROOT.war
echo "\n"

echo "4. Deploy finalizado com sucesso!!"
echo "\n"