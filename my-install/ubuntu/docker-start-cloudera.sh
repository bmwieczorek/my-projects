#/bin/bash
docker run --hostname=quickstart.cloudera --privileged=true -t -i --hostname=quickstart.cloudera --privileged=true -t -i -p 8888:8888 -p80:80  -d $(docker images | grep cloudera | awk '{print $3}') /usr/bin/docker-quickstart

