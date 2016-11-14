#!/bin/bash
echo "Disabling proxy ..."

HOME_DIR=/home/bartek
sudo cp $HOME_DIR/dev/env/install/environment.orig /etc/environment
sudo cp $HOME_DIR/dev/env/install/resolv.conf.orig /etc/resolv.conf
prefsjs=$(find $HOME_DIR/.mozilla/firefox/ -name prefs.js)
cp $HOME_DIR/dev/env/install/prefs.js.orig $prefsjs
cp $HOME_DIR/.gitconfig.orig $HOME_DIR/.gitconfig
sudo rm -f /etc/apt/apt.conf.d/95proxies
sudo cp /etc/default/docker.orig /etc/default/docker
sudo service docker restart

#gsettings set org.gnome.system.proxy mode 'none'
#gsettings reset org.gnome.system.proxy use-same-proxy
#gsettings reset org.gnome.system.proxy.http host
#gsettings reset org.gnome.system.proxy.http port
#gsettings reset org.gnome.system.proxy.https host
#gsettings reset org.gnome.system.proxy.https port
#gsettings reset org.gnome.system.proxy.ftp host
#gsettings reset org.gnome.system.proxy.ftp port
