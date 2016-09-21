#!/bin/bash
echo "Enabling proxy ..."

HOME_DIR=/home/cloudera
sudo cp $HOME_DIR/dev/env/cloudera/environment.proxy /etc/environment
sudo cp $HOME_DIR/dev/env/cloudera/resolv.conf.proxy /etc/resolv.conf
prefsjs=$(find $HOME_DIR/.mozilla/firefox/ -name prefs.js)
cp $HOME_DIR/dev/env/cloudera/prefs.js.proxy $prefsjs
cp $HOME_DIR/dev/env/common/.gitconfig.proxy $HOME_DIR/.gitconfig
sudo service cntlmd start
