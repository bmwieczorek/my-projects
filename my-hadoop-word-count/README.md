1. Steps executed from Windows/Linux: 
- install vbox (add bios vitrualization support)
- download cloudera quickstart vm zip for vbox: e.g. cloudera-quickstart-vm-5.5.0-0-virtualbox
- unzip, import and start cloudera vm using box


2. Steps executed from cloudera vm (note cloudera vm comes with mvn and eclipse with m2eclipse and git plugin):
- open cloudera's eclipse and git clone this project
- run mvn clean install from m2eclipse or bash terminal
- run from bash terminal:
hadoop fs -copyFromLocal input.txt hdfs://quickstart.cloudera:8020/user/cloudera/input.txt
hadoop jar target/my-hadoop-word-count-0.0.1-SNAPSHOT.jar input.txt wcount

hadoop fs -ls /user/cloudera
hadoop fs -ls wcount
hadoop fs -cat wcount/part-r-00000
hadoop fs -rm -r wcount
