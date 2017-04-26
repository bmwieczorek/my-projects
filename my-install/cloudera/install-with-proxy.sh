#!/bin/bash
#Devices/Insert Guest Additions CD Image
# sudo usermod -a -G vboxsf cloudera

jdkVersion=131
intellijVersion=2017.1.2-no-jdk
gitVersion=2.9.3

sudo rpm -ivh /media/sf_vbox-shared/cntlm-0.92.3-1.x86_64.rpm

echo "Installing Java, maven and Intellij ..."

mkdir -p ~/dev/env
cd ~/dev/env
tar xzf /media/sf_vbox-shared/ideaIU-${intellijVersion}.tar.gz

cd /usr/local/apache-maven/
sudo tar xzf /media/sf_vbox-shared/apache-maven-3.3.9-bin.tar.gz

cd /usr/java/
sudo tar xzf /media/sf_vbox-shared/jdk-8u${jdkVersion}-linux-x64.tar.gz

ln -s /usr/java/jdk1.8.0_${jdkVersion} ~/dev/env/jdk1.8
ln -s /usr/local/apache-maven/apache-maven-3.3.9 ~/dev/env/apache-maven
ln -s /media/sf_.m2/ ~/.m2

cp -r /media/sf_my-install/common ~/dev/env/
cp -r /media/sf_my-install/cloudera ~/dev/env/

sudo mv /etc/profile /etc/profile.orig
sudo cp /media/sf_my-install/cloudera/profile /etc
sudo chmod 644 /etc/profile

cd ~
ideaIU=$(find /home/cloudera/dev/env/ -name idea-IU-*)
cp $ideaIU/bin/idea64.vmoptions $ideaIU/bin/idea64.vmoptions.orig
echo "-Duser.name=myuser" >> $ideaIU/bin/idea64.vmoptions

#tar xzf /media/sf_my-install/common/.IntelliJIdea2016.2.orig-no-scala.tgz
#mv .IntelliJIdea2016.2.orig-no-scala .IntelliJIdea2016.2


#tar xzf /media/sf_vbox-shared/.IntelliJIdea2016.2.orig-scala.tgz
#mv .IntelliJIdea2016.2.orig-scala .IntelliJIdea2016.2

sudo cp /etc/cntlm.conf /etc/cntlm.conf.orig
sudo cp /etc/environment /etc/environment.orig
prefsjs=$(find ~/.mozilla/firefox/ -name prefs.js)
cp $prefsjs $prefsjs.orig

cp -r ~/dev/env/cloudera/*proxy*.sh ~/
sudo cp ~/dev/env/common/cntlm.conf.proxy /etc/cntlm.conf

#echo ". ~/dev/env/cloudera/setenv.sh" >> ~/.profile

#. ~/.profile

echo "Installing git ${gitVersion}"
sudo yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel -y
sudo yum install gcc perl-ExtUtils-MakeMaker -y
cd /usr/src
sudo tar xzf /media/sf_vbox-shared/git-${gitVersion}.tar.gz 
cd git-${gitVersion}
sudo make prefix=/usr/local/git all
sudo make prefix=/usr/local/git install
export PATH=/usr/local/git/bin:$PATH
git --version

ln -s /usr/local/git ~/dev/env/git

#echo ". ~/dev/env/cloudera/setenv.sh" >> ~/.bashrc
#. ~/.bashrc

#sudo cp /etc/spark/conf/spark-defaults.conf /etc/spark/conf/spark-defaults.conf.orig
#sudo cp /etc/hadoop/conf/yarn-site.xml /etc/hadoop/conf/yarn-site.xml.orig

#sudo cp /media/sf_my-install/cloudera/etc/spark/conf/spark-defaults.conf.event-history /etc/spark/conf/spark-defaults.conf

#sudo cp /media/sf_my-install/cloudera/etc/hadoop/conf/yarn-site.xml.yarn-log-server-url /etc/hadoop/conf/yarn-site.xml

sudo mv /usr/java/jdk1.7.0_67-cloudera /usr/java/jdk1.7.0_67-cloudera.orig
sudo ln -s ~/dev/env/jdk1.8 /usr/java/jdk1.7.0_67-cloudera

#sudo service spark-history-server stop
#sudo service spark-history-server start

#sudo /etc/init.d/hadoop-yarn-nodemanager restart 
#sudo /etc/init.d/hadoop-yarn-resourcemanager restart

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
