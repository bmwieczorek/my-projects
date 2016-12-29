#!/bin/bash
if ! [ $# -eq 5 ]; then
    echo "$0: Usage $0 <company> <username> <domain> <mypassnt> <mypasslm>"
    exit 1
fi

grep -rl mycompany * | grep -v replace | xargs sed -i "" "s/mycompany/$1/g"
grep -rl myuser *    | grep -v replace | xargs sed -i "" "s/myuser/$2/g"
grep -rl mydomain *  | grep -v replace | xargs sed -i "" "s/mydomain/$3/g"
grep -rl mypassnt *  | grep -v replace | xargs sed -i "" "s/mypassnt/$4/g"
grep -rl mypasslm *  | grep -v replace | xargs sed -i "" "s/mypasslm/$5/g"

