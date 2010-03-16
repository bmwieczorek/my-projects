#!/bin/sh
##############
#ssi only file
##############


CATALINA_BASE=/home/sg0920709/dev/workspace/apache-tomcat-5.5.28
EMAIL='Adam.Czepil@sabre-holdings.com,Arkadiusz.Milczarek@sabre-holdings.com,Bartosz.Bankowski@sabre-holdings.com,Bartosz.Wieczorek.ctr@sabre.com,Grzegorz.Pietrzyk@sabre.com,Krzysztof.Karczmarczyk@sabre.com,Pawel.Trendota@sabre-holdings.com,Piotr.Jagielski@sabre-holdings.com,Rafal.Sontowski@sabre-holdings.com,Tomasz.Bak@sabre-holdings.com'

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
	  -Dssi.log4j.env=dev \
          -Dssi.service.email.to=$EMAIL"

CATALINA_OPTS="$CATALINA_OPTS $SSI_OPTS"

#TPTP_OPTS='-agentlib:JPIBootLoader=JPIAgent;CGProf'
#TPTP_OPTS=

JAVA_OPTS='-Xmx2048m -XX:MaxPermSize=512m '$TPTP_OPTS

export CATALINA_PID CATALINA_OPTS JAVA_OPTS CLASSPATH 
