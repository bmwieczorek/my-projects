#!/bin/bash
if ! [ $# -eq 3 ]; then
    echo "$0: Usage $0 <company> <username> <domain>"
    exit 1
fi

grep -rl mycompany * | grep -v replace | xargs sed -i "s/mycompany/$1/g"
grep -rl myusername * | grep -v replace |  xargs sed -i "s/myusername/$2/g"
grep -rl mydomain * | grep -v replace |  xargs sed -i "s/mydomain/$3/g"

