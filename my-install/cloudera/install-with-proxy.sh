#!/bin/bash
#Devices/Insert Guest Additions CD Image
# sudo usermod -a -G vboxsf cloudera

sudo rpm -ivh /media/sf_vbox-shared/cntlm-0.92.3-1.x86_64.rpm

echo "Installing Java, maven and Intellij ..."

mkdir -p ~/dev/env
cd ~/dev/env
tar xzf /media/sf_vbox-shared/ideaIU-2016.2.4.tar.gz
tar xzf /media/sf_vbox-shared/apache-maven-3.3.9-bin.tar.gz
tar xzf /media/sf_vbox-shared/jdk-8u102-linux-x64.tar.gz

ln -s ~/dev/env/jdk1.8.0_102 ~/dev/env/jdk1.8
ln -s ~/dev/env/apache-maven-3.3.9 ~/dev/env/apache-maven
ln -s /media/sf_.m2/ ~/.m2

cp -r /media/sf_vbox-shared/install/common ~/dev/env/
cp -r /media/sf_vbox-shared/install/cloudera ~/dev/env/


cd ~
ideaIU=$(find /home/cloudera/dev/env/ -name idea-IU-*)
cp $ideaIU/bin/idea64.vmoptions $ideaIU/bin/idea64.vmoptions.orig
echo "-Duser.name=myusername" >> $ideaIU/bin/idea64.vmoptions

tar xzf /media/sf_vbox-shared/install/common/.IntelliJIdea2016.2.orig-no-scala.tgz
mv .IntelliJIdea2016.2.orig-no-scala .IntelliJIdea2016.2


#tar xzf /media/sf_vbox-shared/.IntelliJIdea2016.2.orig-scala.tgz
#mv .IntelliJIdea2016.2.orig-scala .IntelliJIdea2016.2

sudo cp /etc/cntlm.conf /etc/cntlm.conf.orig
sudo cp /etc/environment /etc/environment.orig
prefsjs=$(find ~/.mozilla/firefox/ -name prefs.js)
cp $prefsjs $prefsjs.orig

cp -r ~/dev/env/cloudera/*proxy*.sh ~/
sudo cp ~/dev/env/common/cntlm.conf.proxy /etc/cntlm.conf

echo ". ~/dev/env/cloudera/setenv.sh" >> ~/.profile

. ~/.profile

echo "Installing git 2.9.2"
sudo yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel -y
sudo yum install gcc perl-ExtUtils-MakeMaker -y
cd /usr/src
sudo tar xzf /media/sf_vbox-shared/git-2.9.2.tar.gz 
cd git-2.9.2
sudo make prefix=/usr/local/git all
sudo make prefix=/usr/local/git install
export PATH=/usr/local/git/bin:$PATH
git --version

ln -s /usr/local/git ~/dev/env/git

echo ". ~/dev/env/cloudera/setenv.sh" >> ~/.bashrc
. ~/.bashrc

sudo cp /etc/spark/conf/spark-defaults.conf /etc/spark/conf/spark-defaults.conf.orig
sudo cp /etc/hadoop/conf/yarn-site.xml /etc/hadoop/conf/yarn-site.xml.orig

sudo cp /media/sf_vbox-shared/install/cloudera/etc/spark/conf/spark-defaults.conf.event-history /etc/spark/conf/spark-defaults.conf

sudo cp /media/sf_vbox-shared/install/cloudera/etc/hadoop/conf/yarn-site.xml.yarn-log-server-url /etc/hadoop/conf/yarn-site.xml

#lrwxrwxrwx 1 root root 39 Apr  5 23:55 /usr/bin/java -> /usr/java/jdk1.7.0_67-cloudera/bin/java
#sudo rm /usr/bin/java
#sudo ln -s ~/dev/env/jdk1.8 /usr/bin/java

sudo rm -rf /usr/java/jdk1.7.0_67-cloudera
sudo ln -s ~/dev/env/jdk1.8 /usr/java/jdk1.7.0_67-cloudera

sudo service spark-history-server stop
sudo service spark-history-server start

sudo /etc/init.d/hadoop-yarn-nodemanager restart 
sudo /etc/init.d/hadoop-yarn-resourcemanager restart

echo "##############################################################################"
echo "!!! First connect VPN, then run ~/proxy.sh and eventually accept Intellij policy agreement !!!"
echo "##############################################################################"

echo "##############################################################################"
echo "!!! Then create Intellij desktop entry and ctrl+shift+alt+S to add SDK-> ~/dev/env/jdk8, change git executable to /home/cloudera/dev/env/git/bin/git and lock it to laucher together with terminal !!!"
echo "##############################################################################"

echo "##############################################################################"
echo "!!! Restart VBOX to make proxy working !!!"
echo "##############################################################################"


$ideaIU/bin/idea.sh

# create desktop entry and applications/programming/right click add laucher to desktop
# git clone https://bmwieczorek@github.com/bmwieczorek/my-projects.git
# git clone https://github.com/bmwieczorek/my-projects.git
