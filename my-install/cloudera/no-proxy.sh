#!/bin/bash
echo "Disabling proxy ..."

HOME_DIR=/home/cloudera
sudo cp $HOME_DIR/dev/env/cloudera/environment.orig /etc/environment
sudo cp $HOME_DIR/dev/env/cloudera/resolv.conf.orig /etc/resolv.conf
prefsjs=$(find $HOME_DIR/.mozilla/firefox/ -name prefs.js)
cp $HOME_DIR/dev/env/cloudera/prefs.js.orig $prefsjs
cp $HOME_DIR/dev/env/common/.gitconfig.orig $HOME_DIR/.gitconfig
sudo service cntlmd stop
