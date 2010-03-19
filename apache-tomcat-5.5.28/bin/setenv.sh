#!/bin/sh


##############
#ssi only file
##############


CATALINA_BASE=/home/sg0920709/dev/workspace/apache-tomcat-5.5.28
EMAIL='Bartosz.Wieczorek.ctr@sabre.com'
HOST="$HOSTNAME"".dev.sabre.com"


SSI_OPTS="-Dssi.service.port=8080 \
          -Dssi.service.host=$HOST \
          -Dssi.community.portal.security.enable=false \
          -Dssi.service.authorization.flight.enable=false \
          -Dflex.port=8080 \
          -Dssi.community.portal.spml.user=sgssigui \
          -Dssi.community.portal.spml.password=Abcd123456 \
          -Dssi.community.portal.spml.url=http://olive.dev.sabre.com:20011/emergo/services/spmlServices/v2008_0?wsdl \
          -Dssi.community.portal.spml.environment=DEV \
          -Dssi.service.momServiceName=devService \
          -Dice.config.dir=$CATALINA_BASE/conf/ice-config \
	  -Dlog4j.configuration=log4j/log4j-local.xml \
          -Dcom.sun.management.jmxremote.port=8082 \
          -Dcom.sun.management.jmxremote.ssl=false \
          -Dcom.sun.management.jmxremote.authenticate=false \
	  -Dcom.sun.management.jmxremote \
          -Dssi.service.email.to=$EMAIL"

CATALINA_OPTS="$CATALINA_OPTS $SSI_OPTS"

JAVA_OPTS='-Xmx2048m -XX:MaxPermSize=512m '$TPTP_OPTS

export CATALINA_PID CATALINA_OPTS JAVA_OPTS CLASSPATH 
