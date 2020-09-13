#!/bin/bash

echo -e "\n"
echo "Iniciando o deploy automático"
echo -e "\n"

echo "1. Atualização da branch (pull)"
echo -e "\n"
sudo git pull
echo -e "\n"

echo "2. Limpando e gerando o package"
echo -e "\n"
mvn clean package
echo -e "\n"

echo "3. Movendo package.war para a pasta de deploy do tomcat"
echo -e "\n"
mv target/teachermatch-0.0.1-SNAPSHOT.war ~/apps/apache-tomcat-8.5.57/webapps/ROOT.war
echo -e "\n"

echo "4. Deploy finalizado com sucesso!!"
echo -e "\n"
