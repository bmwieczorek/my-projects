#!/bin/bash

TOMCAT_VERSION=6.0.26
CATALINA_HOME="$(pwd)"/apache-tomcat-${TOMCAT_VERSION}
CATALINA_BASE="$(pwd)"/DEV-catalina-base
CATALINA_PID="${CATALINA_BASE}"/catalina.pid

# CATALINA_HOME is a directory created from expanded apache-tomcat-${TOMCAT_VERSION}.tar.gz, shared across tomcat instances
echo "CATALINA_HOME=${CATALINA_HOME}"

# CATALINA_BASE is a directory of one of tomcat instances
echo "CATALINA_BASE=${CATALINA_BASE}"

echo "CATALINA_PID=${CATALINA_PID}"

#echo "Downloading tomcat ${TOMCAT_VERSION} ..."
#wget http://archive.apache.org/dist/tomcat/tomcat-6/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz

echo "Expanding tomcat to directory ${CURRENT_DIR}"
#tar xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz
unzip -q apache-tomcat-${TOMCAT_VERSION}.zip

echo "Setting up catalina base ... "
mkdir -p "${CATALINA_BASE}"/{bin,conf,logs,temp,webapps,work}

# create your own if you want to override some settings (e.g. ports)
cp "${CATALINA_HOME}"/conf/server.xml "${CATALINA_BASE}"/conf
cp "${CATALINA_HOME}"/conf/web.xml "${CATALINA_BASE}"/conf
cp -r "${CATALINA_HOME}"/webapps/ROOT "${CATALINA_BASE}"/webapps

# tomcat manager - redirect folder location
mkdir -p "${CATALINA_BASE}"/conf/Catalina/localhost
echo '<?xml version="1.0" encoding="UTF-8"?>' >> "${CATALINA_BASE}"/conf/Catalina/localhost/manager.xml
echo '<Context docBase="${catalina.home}/webapps/manager" privileged="true" antiResourceLocking="false" />' >> "${CATALINA_BASE}"/conf/Catalina/localhost/manager.xml

# tomcat manager - login credentials
echo "<?xml version='1.0' encoding='utf-8'?>" >> "${CATALINA_BASE}"/conf/tomcat-users.xml 
echo '<tomcat-users>' >> "${CATALINA_BASE}"/conf/tomcat-users.xml 
echo '<role rolename="manager"/>' >> "${CATALINA_BASE}"/conf/tomcat-users.xml 
echo '<user username="admin" password="" roles="manager"/>' >> "${CATALINA_BASE}"/conf/tomcat-users.xml 
echo '</tomcat-users>' >> "${CATALINA_BASE}"/conf/tomcat-users.xml 


echo '#!/bin/bash' >> "${CATALINA_BASE}"/bin/startup.sh
echo "export CATALINA_BASE=\"${CATALINA_BASE}\"" >> "${CATALINA_BASE}"/bin/startup.sh
echo "export CATALINA_HOME=\"${CATALINA_HOME}\"" >> "${CATALINA_BASE}"/bin/startup.sh
echo "export CATALINA_PID=\"${CATALINA_PID}\"" >> "${CATALINA_BASE}"/bin/startup.sh
echo '"${CATALINA_HOME}"'/bin/startup.sh  >> "${CATALINA_BASE}"/bin/startup.sh

echo '#!/bin/bash' >> "${CATALINA_BASE}"/bin/shutdown.sh
echo "export CATALINA_BASE=\"${CATALINA_BASE}\"" >> "${CATALINA_BASE}"/bin/shutdown.sh
echo "export CATALINA_HOME=\"${CATALINA_HOME}\"" >> "${CATALINA_BASE}"/bin/shutdown.sh
echo "export CATALINA_PID=\"${CATALINA_PID}\"" >> "${CATALINA_BASE}"/bin/shutdown.sh
echo '"${CATALINA_HOME}"'/bin/shutdown.sh  >> "${CATALINA_BASE}"/bin/shutdown.sh

cd "${CATALINA_HOME}" && find -name \*sh -exec chmod +x {} \; 
cd "${CATALINA_BASE}" && find -name \*sh -exec chmod +x {} \; 
