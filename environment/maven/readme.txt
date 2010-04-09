1) javax.management:jmxri:jar:1.2.1

Try downloading the file manually from:
http://java.sun.com/products/JavaManagement/download.html

Then, install it using the command:
mvn install:install-file -DgroupId=javax.management -DartifactId=jmxri -Dversion=1.2.1 -Dpackaging=jar -Dfile=/path/to/file


mvn install:install-file -DgroupId=com.sun.jmx -DartifactId=jmxri -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxri.jar

M2_HOME=$HOME/dev/environment/apache-maven-2.2.1 && PATH=$M2_HOME/bin:$PATH && mvn install:install-file -DgroupId=com.sun.jmx -DartifactId=jmxri -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxri.jar



2) com.sun.jdmk:jmxtools:jar:1.2.1

Try downloading the file manually from: http://java.sun.com/products/JavaManagement/download.html

Then, install it using the command: 
mvn install:install-file -DgroupId=com.sun.jdmk -DartifactId=jmxtools -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxtools.jar

M2_HOME=$HOME/dev/environment/apache-maven-2.2.1 && PATH=$M2_HOME/bin:$PATH && mvn install:install-file -DgroupId=com.sun.jdmk -DartifactId=jmxtools  -Dversion=1.2.1 -Dpackaging=jar -Dfile=jmxtools.jar

3)
M2_HOME=$HOME/dev/environment/apache-maven-2.2.1 && PATH=$M2_HOME/bin:$PATH && mvn install:install-file -DpomFile=jaxb-commons-lang-plugin-2.1.3.pom -Dfile=jaxb-commons-lang-plugin-2.1.3.jar -DlocalRepositoryPath=$HOME/.m2/repository
