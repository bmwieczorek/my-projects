#!/bin/bash
if [ $# -lt 1 ]; then
    echo "$0: Usage $0 <company> <domain> [<user>] [<mypassnt>] [<mypasslm>]"
    exit 1
fi

myuser=${3:-${USER}}
mypassnt=${4:-$(cat /usr/local/etc/cntlm.conf | grep ^PassNT | awk '{ print $2 }')} 
mypasslm=${5:-$(cat /usr/local/etc/cntlm.conf | grep ^PassLM | awk '{ print $2 }')} 
echo "myuser=$myuser"
echo "mypassnt=$mypassnt"
echo "mypasslm=$mypasslm"

grep -rl mycompany * | grep -v replace | xargs sed -i "" "s/mycompany/$1/g"
grep -rl mydomain *  | grep -v replace | xargs sed -i "" "s/mydomain/$2/g"
grep -rl myuser *    | grep -v replace | xargs sed -i "" "s/myuser/$myuser/g"
grep -rl mypassnt *  | grep -v replace | xargs sed -i "" "s/mypassnt/$mypassnt/g"
grep -rl mypasslm *  | grep -v replace | xargs sed -i "" "s/mypasslm/$mypasslm/g"

