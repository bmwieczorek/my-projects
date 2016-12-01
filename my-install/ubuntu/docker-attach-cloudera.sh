#/bin/bash
docker attach $(docker ps | grep $(docker images | grep cloudera | awk '{print $3}') | awk '{print $1}')

