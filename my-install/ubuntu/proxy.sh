#!/bin/bash
echo "Enabling proxy ..."

HOME_DIR=/home/bartek
sudo cp $HOME_DIR/dev/env/install/environment.proxy /etc/environment
sudo cp $HOME_DIR/dev/env/install/resolv.conf.proxy /etc/resolv.conf
prefsjs=$(find $HOME_DIR/.mozilla/firefox/ -name prefs.js)
cp $HOME_DIR/dev/env/install/prefs.js.proxy $prefsjs
cp $HOME_DIR/.gitconfig.proxy $HOME_DIR/.gitconfig
sudo cp $HOME_DIR/dev/env/install/95proxies /etc/apt/apt.conf.d/
sudo chmod 644 /etc/apt/apt.conf.d/95proxies
sudo cp $HOME_DIR/dev/env/install/docker.proxy /etc/default/docker
sudo service docker restart

#gsettings set org.gnome.system.proxy mode 'manual'
#gsettings set org.gnome.system.proxy use-same-proxy false
#gsettings set org.gnome.system.proxy.http host 'localhost'
#gsettings set org.gnome.system.proxy.http port 3128
#gsettings set org.gnome.system.proxy.https host 'localhost'
#gsettings set org.gnome.system.proxy.https port 3128
#gsettings set org.gnome.system.proxy.ftp host 'localhost'
#gsettings set org.gnome.system.proxy.ftp port 3128
