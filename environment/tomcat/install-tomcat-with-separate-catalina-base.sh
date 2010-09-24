#!/bin/bash

#go to directory where you want to install tomcat

TOMCAT_VERSION=6.0.26
BASE_DIR=$(pwd)
CURRENT_DIR=${BASE_DIR}

echo "Current directory is ${CURRENT_DIR}"

echo "Downloading tomcat ${TOMCAT_VERSION} ..."
wget http://archive.apache.org/dist/tomcat/tomcat-6/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz

echo "Expanding tomcat to directory ${CURRENT_DIR}"
tar xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz

cd ${CURRENT_DIR}/apache-tomcat-${TOMCAT_VERSION}

CURRENT_DIR=$(pwd)
echo "Current directory is ${CURRENT_DIR}"

# CATALINA_BASE is a directory of one of tomcat instances
CATALINA_BASE=${BASE_DIR}/DEV-catalina-base
echo "CATALINA_BASE=${CATALINA_BASE}"

# CATALINA_HOME is a directory created from expanded apache-tomcat-${TOMCAT_VERSION}.tar.gz, shared across tomcat instances
CATALINA_HOME=${BASE_DIR}/apache-tomcat-${TOMCAT_VERSION}
echo "CATALINA_HOME=${CATALINA_HOME}"

echo "Setting up catalina base ... "
mkdir -p ${CATALINA_BASE}/{bin,conf,logs,temp,webapps,work}

# create your own if you want to override some settings (e.g. ports)
cp ${CATALINA_HOME}/conf/server.xml ${CATALINA_BASE}/conf
cp ${CATALINA_HOME}/conf/web.xml ${CATALINA_BASE}/conf

cp ${CATALINA_HOME}/bin/catalina.sh ${CATALINA_BASE}/bin

# specify your env variables in setenv.sh


echo '#!/bin/bash' >> ${CATALINA_BASE}/bin/startup.sh
echo "export CATALINA_BASE=${CATALINA_BASE}" >> ${CATALINA_BASE}/bin/startup.sh
echo "export CATALINA_HOME=${CATALINA_HOME}" >> ${CATALINA_BASE}/bin/startup.sh
echo 'exec "$CATALINA_HOME"/bin/startup.sh "$@"' >> ${CATALINA_BASE}/bin/startup.sh
chmod +x ${CATALINA_BASE}/bin/startup.sh


