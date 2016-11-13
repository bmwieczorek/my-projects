#!/bin/bash
#import vmdk and enable bidirectional clipboard and shared folers for vbox-shared and .m2
#Devices/Insert Guest Additions CD Image
# sudo usermod -a -G vboxsf bartek
#
jdkVersion=112
intellijVersion=2016.2.5-no-jdk
 

sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install vim git subversion cntlm -y
sudo apt-get remove gnome-mines gnome-mahjongg aisleriot gnome-sudoku -y
sudo apt-get remove linux-generic linux-headers-generic linux-image-generic -y

gsettings set com.canonical.desktop.interface scrollbar-mode normal
gsettings set org.gnome.nautilus.desktop home-icon-visible true

#Install Silverlight (with closed firefox during installation) for Ipla:
sudo add-apt-repository ppa:pipelight/stable -y
sudo apt-get update -y
sudo apt-get install --install-recommends pipelight-multi -y
sudo pipelight-plugin --enable silverlight
sudo pipelight-plugin --update

echo "##############################"
echo "!!! start and stop forefox !!!"
echo "##############################"

firefox
sudo pipelight-plugin --create-mozilla-plugins -y

sudo apt-get clean -y && sudo apt-get autoremove -y && sudo apt-get autoclean -y && sudo apt clean -y && sudo apt autoremove -y && sudo apt autoclean -y

#Disabling password for sudo:
#sudo visudo and add as LAST LINE bartek ALL=(ALL) NOPASSWD: ALL and delete password for user sudo passwd bartek -d

echo "Installing Java, Maven, Intellij, Scala and Apache Spark ..."

mkdir -p ~/dev/env
cd ~/dev/env
tar xzf /media/sf_vbox-shared/ideaIU-${intellijVersion}.tar.gz
tar xzf /media/sf_vbox-shared/apache-maven-3.3.9-bin.tar.gz
tar xzf /media/sf_vbox-shared/jdk-8u${jdkVersion}-linux-x64.tar.gz
tar xzf /media/sf_vbox-shared/scala-2.11.8.tgz
tar xzf /media/sf_vbox-shared/spark-1.6.2-bin-hadoop2.6.tgz

ln -s ~/dev/env/jdk1.8.0_${jdkVersion} ~/dev/env/jdk1.8
ln -s ~/dev/env/apache-maven-3.3.9 ~/dev/env/apache-maven
ln -s ~/dev/env/scala-2.11.8 ~/dev/env/scala
ln -s /media/sf_.m2/ ~/.m2

mkdir ~/dev/env/install
cp -r /media/sf_my-install/common/. ~/dev/env/install
cp -r /media/sf_my-install/ubuntu/. ~/dev/env/install

sudo chown -R bartek:bartek ~/dev/env/install

cp ~/dev/env/install/.gitconfig* ~
cp ~/dev/env/install/setenv.sh ~/dev/env/
cp ~/dev/env/install/proxy.sh ~
cp ~/dev/env/install/no-proxy.sh ~

sudo cp ~/dev/env/install/cntlm.conf.proxy /etc/cntlm.conf
sudo cp ~/dev/env/install/detect-proxy /etc/network/if-up.d/
sudo chmod 755 /etc/network/if-up.d/detect-proxy
sudo cp /etc/cntlm.conf /etc/cntlm.conf.orig
sudo cp /etc/environment /etc/environment.orig
prefsjs=$(find ~/.mozilla/firefox/ -name prefs.js)
cp $prefsjs $prefsjs.orig

echo ". ~/dev/env/setenv.sh" >> ~/.profile

. ~/.profile

cd ~
ideaIU=$(find /home/bartek/dev/env/ -name idea-IU-*)
cp $ideaIU/bin/idea64.vmoptions $ideaIU/bin/idea64.vmoptions.orig
echo "-Duser.name=myusername" >> $ideaIU/bin/idea64.vmoptions

tar xzf ~/dev/env/install/.IntelliJIdea2016.2.orig-no-scala.tgz
mv .IntelliJIdea2016.2.orig-no-scala .IntelliJIdea2016.2

#tar xzf /media/sf_vbox-shared/.IntelliJIdea2016.2.orig-scala.tgz
#mv .IntelliJIdea2016.2.orig-scala .IntelliJIdea2016.2

echo "##############################################################################"
echo "!!! First connect VPN, then run ~/proxy.sh and eventually accept Intellij policy agreement !!!"
echo "##############################################################################"

echo "##############################################################################"
echo "!!! Then create Intellij desktop entry and ctrl+shift+alt+S to add SDK-> ~/dev/env/jdk8, update Maven and lock it to laucher together with terminal !!!"
echo "##############################################################################"

echo "##############################################################################"
echo "!!! Restart VBOX to make proxy working !!!"
echo "##############################################################################"


$ideaIU/bin/idea.sh


#Install chrome - Import all bookmarks from Chrome: Ctrl+Shift+O
#start intellij -> create desktop entry and go to centos Applications/Programming and right click Add this launcher to desktop
