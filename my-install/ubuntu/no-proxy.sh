#!/bin/bash
echo "Disabling proxy ..."

sudo cp ~/dev/env/install/environment.orig /etc/environment
sudo cp ~/dev/env/install/resolv.conf.orig /etc/resolv.conf
prefsjs=$(find ~/.mozilla/firefox/ -name prefs.js)
cp ~/dev/env/install/prefs.js.orig $prefsjs
cp ~/.gitconfig.orig ~/.gitconfig
sudo rm -f /etc/apt/apt.conf.d/95proxies
sudo /etc/init.d/cntlm stop

# Docker
#sudo rm -rf /etc/systemd/system/docker.service.d/http-proxy.conf
#sudo systemctl daemon-reload
#sudo systemctl show --property Environment docker
#sudo systemctl restart docker

gsettings set org.gnome.system.proxy mode 'none'
gsettings reset org.gnome.system.proxy use-same-proxy
gsettings reset org.gnome.system.proxy.http host
gsettings reset org.gnome.system.proxy.http port
gsettings reset org.gnome.system.proxy.https host
gsettings reset org.gnome.system.proxy.https port
gsettings reset org.gnome.system.proxy.ftp host
gsettings reset org.gnome.system.proxy.ftp port

