#!/bin/bash

grep -rl mycompany * | grep -v replace | xargs sed -i "s/mycompany/$1/g"
grep -rl myusername * | grep -v replace |  xargs sed -i "s/myusername/$2/g"

