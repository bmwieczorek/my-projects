#!/bin/sh


JAVA_OPTS='-Xmx1024m -XX:MaxPermSize=512m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8082 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false'

export JAVA_OPTS 
