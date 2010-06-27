#!/bin/bash

usage="Usage: $0 option [ option2 [ option3 ] ... ]]] 

This script can hot redeploy static file (jsp, js, html, htm) without rebuilding the complete application
restarting jboss. It reads in the jboss home directory (<jboss.home> tag), artifactId and version from the 
pom.xml. It start jboss if not running and explodes the war if necesssary on the jboss server. If there are
any local changes on the static files, then it redeploys them. 

Options:
        --help          print help message 
        html            redeploy html files
        htm             redeploy htm files
        jsp             redeploy jsp files
        js              redeploy js files
        static          redeploy all static files (listed above)
        all             rebuilds the complete application using mvn clean install -Dtest=false -DfailIfNoTests=false"

#functions
function parseValueFromPom {
    pattern=$1
    echo $(grep $pattern pom.xml | head -n 1 | sed s/.*\<$pattern\>//g | sed s/\<\\/$pattern\>//g)
}

function startJbossIfNotRunning {
    jboss_pid=$(ps aux | grep `whoami` | grep jboss | grep Main | awk ' {print $2}' | xargs echo)
    if ! [ -n "$jboss_pid" ]; then
        green "JBoss not running ($JBOSS_HOME), starting ..."
        $JBOSS_HOME/bin/run.sh 
    else
        green "JBoss already running (pid: $jboss_pid)"
    fi
}

function explodeWarOnServer {
    green "Exploding the package in the constainer"
    unzip $package -d $package.tmp
    rm $package
    mv $package.tmp $package
}

function deploy {
    if ! [ -d $package]; then
        explodeWarOnServer
    fi

    filetype=$1
    green "Redeploying $filetype file(s) ..."
    i=0
    for filepath in $(find src/ -type f -name \*$filetype ); do 
        file=$(echo $filepath | sed 's/.*webapp\///g' );  
        webappfile=$JBOSS_HOME/server/default/deploy/$ARTIFACTID-$VERSION.war/$file; 
        if ! diff $filepath $webappfile > /dev/null 2>/dev/null; then
            cp $filepath $webappfile 
            orange "Copying: $filepath to $webappfile"
            i=$[i+1]
            TOTAL_FILES_REDEPLOYED=$[TOTAL_FILES_REDEPLOYED+1]
        fi
    done
    green "Modified and redeployed $i $filetype file(s)" 
}

function deployAllExploded {
    green "Deploy complete application ..."
    mvn clean install
    package=$JBOSS_HOME/server/default/deploy/$ARTIFACTID-$VERSION.war

    if [ -e $package ]; then
        rm -rf $package
        green "Removing $package"
    fi

    cp -r target/$ARTIFACTID-$VERSION $package
    green "Copied $ARTIFACTID-$VERSION to $package"
    startJbossIfNotRunning 
}

#color logging
green(){ echo -ne "\033[32m$1\033[0m\n"; }
red(){ echo -ne "\033[31m$1\033[0m\n"; }
orange(){ echo -ne "\033[33m$1\033[0m\n"; }


#main program
if [ $# -eq 0 ];then
    green "$usage"
    exit 0
fi

#global variables also used in functions
JBOSS_HOME=$(parseValueFromPom jboss.home)
ARTIFACTID=$(parseValueFromPom artifactId)
VERSION=$(parseValueFromPom version)
TOTAL_FILES_REDEPLOYED=0


if ! [ -e $package ]; then
    green "Package $package has not been deployed yet on JBoss"
    deployAllExploded
    exit 0
fi


while [ $# -gt 0 ]; do
    case $1 in
        --help)
        green "$usage"
        exit 0
        ;;
        all)
        deployAllExploded
        exit 0
        ;;
        jsp)
        deploy jsp
        shift
        ;;
        htm)
        deploy htm
        shift
        ;;
        html)
        deploy html
        shift
        ;;
        js)
        deploy js
        shift
        ;;
        static)
        green "Deploying all static files ..."
        deploy jsp
        deploy html
        deploy htm
        deploy js
        break
        ;;
        *)
        red "Internal Error: option processing error: $1" 1>&2
        exit 1
        ;;
    esac
done

green
green "Total files redeployed: $TOTAL_FILES_REDEPLOYED"
startJbossIfNotRunning
