#!/bin/bash

if [ "$1" == "" ]; then 
    echo "$0 missing user@host"
    exit 1
fi

userAndHost=$1

sshKeyFile=~/.ssh/id_rsa.pub

if [ -e $sshKeyFile ]; then 
    echo "Copying file $sshKeyFile to $userAndHost"
else
    echo "file $sshKeyFile does not exist"
    exit 1
fi

scp $sshKeyFile $userAndHost: 
ssh $userAndHost "cat ~/id_rsa.pub >> ~/.ssh/authorized_keys && rm ~/id_rsa.pub"

