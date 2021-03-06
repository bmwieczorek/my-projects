#!/bin/bash
#import vmdk and enable bidirectional clipboard and shared folders for vbox-shared and .m2
#Devices/Insert Guest Additions CD Image
# sudo usermod -a -G vboxsf $USER
#
jdkVersion=152
intellijVersion=2017.2.5-no-jdk
sbtVersion=1.0.2
mavenVersion=3.5.0
nodejsVersion=6.11.3
cdapVersion=4.3.1

gsettings set org.gnome.desktop.input-sources xkb-options "['altwin:ctrl_win']"
gsettings set org.gnome.Terminal.Legacy.Keybindings:/org/gnome/terminal/legacy/keybindings/ paste '<Ctrl>v'
gsettings set com.canonical.Unity always-show-menus true

sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install vim git subversion cntlm -y
sudo apt-get remove gnome-mines gnome-mahjongg aisleriot gnome-sudoku -y
sudo apt-get remove linux-generic linux-headers-generic linux-image-generic -y

gsettings set com.canonical.desktop.interface scrollbar-mode normal
gsettings set org.gnome.nautilus.desktop home-icon-visible true
dconf write /org/compiz/profiles/unity/plugins/unityshell/show-launcher '""'

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

echo "Installing chrome..."
wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo bash -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
sudo apt-get update
sudo apt-get install google-chrome-stable -y

echo "Installing sublime..."
wget -qO - https://download.sublimetext.com/sublimehq-pub.gpg | sudo apt-key add -
echo "deb https://download.sublimetext.com/ apt/stable/" | sudo tee /etc/apt/sources.list.d/sublime-text.list
sudo apt-get update
sudo apt-get install sublime-text

#echo "Installing docker..."
#sudo apt-get install apt-transport-https ca-certificates
#sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
#sudo apt-add-repository 'deb https://apt.dockerproject.org/repo ubuntu-xenial main'
#sudo apt-get update
#apt-cache policy docker-engine
#sudo apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual -y
#sudo apt-get update
#sudo apt-get install docker-engine -y
#sudo service docker start
#sudo groupadd docker
#sudo usermod -aG docker $USER
#docker run hello-world

#docker pull wnameless/oracle-xe-11g
#docker run -d -p 2222:22 -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true --name dev-db  wnameless/oracle-xe-11g
#docker ps

#echo "Start docker via: 'sudo service docker start' and run it via 'docker run hello-world'"

echo "Cleaning up packages..."
sudo apt-get clean -y && sudo apt-get autoremove -y && sudo apt-get autoclean -y && sudo apt clean -y && sudo apt autoremove -y && sudo apt autoclean -y

#Disabling password for sudo:
#sudo visudo and add as LAST LINE bartek ALL=(ALL) NOPASSWD: ALL and delete password for user sudo passwd bartek -d

echo "Installing Java, Maven, Intellij, Scala, SBT, Kafka and Apache Spark ..."

mkdir -p ~/dev/env
cd ~/dev/env
tar xzf /media/sf_vbox-shared/ideaIU-${intellijVersion}.tar.gz
tar xzf /media/sf_vbox-shared/apache-maven-${mavenVersion}-bin.tar.gz
tar xzf /media/sf_vbox-shared/jdk-8u${jdkVersion}-linux-x64.tar.gz
tar xzf /media/sf_vbox-shared/scala-2.11.11.tgz
tar xzf /media/sf_vbox-shared/sbt-${sbtVersion}.tgz
tar xzf /media/sf_vbox-shared/kafka_2.11-0.11.0.0.tgz
tar xzf /media/sf_vbox-shared/spark-1.6.3-bin-hadoop2.6.tgz
tar xzf /media/sf_vbox-shared/db-derby-10.13.1.1-bin.tar.gz
tar xf /media/sf_vbox-shared/node-v${nodejsVersion}-linux-x64.tar.xz
unzip /media/sf_vbox-shared/cdap-sandbox-${cdapVersion}.zip

ln -s ~/dev/env/jdk1.8.0_${jdkVersion} ~/dev/env/jdk1.8
ln -s ~/dev/env/apache-maven-${mavenVersion} ~/dev/env/apache-maven
ln -s ~/dev/env/scala-2.11.11 ~/dev/env/scala
mv sbt sbt-${sbtVersion}
ln -s ~/dev/env/sbt-${sbtVersion} ~/dev/env/sbt
ln -s /media/sf_.m2/ ~/.m2
ln -s ~/dev/env/node-v${nodejsVersion}-linux-x64 ~/dev/env/node
ln -s ~/dev/env/cdap-sandbox-${cdapVersion} ~/dev/env/cdap


mkdir ~/dev/env/install
cp -r /media/sf_my-install/common/. ~/dev/env/install
cp -r /media/sf_my-install/ubuntu/. ~/dev/env/install

sudo chown -R $USER:$USER ~/dev/env/install

cp ~/dev/env/install/.gitconfig* ~
cp ~/dev/env/install/setenv.sh ~/dev/env/
cp ~/dev/env/install/proxy.sh ~
cp ~/dev/env/install/no-proxy.sh ~

chmod 644 ~/.gitconfig*

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
ideaIU=$(find ~/dev/env/ -name idea-IU-*)

#tar xzf ~/dev/env/install/.IntelliJIdea2016.2.orig-no-scala.tgz
#mv .IntelliJIdea2016.2.orig-no-scala .IntelliJIdea2016.2

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


