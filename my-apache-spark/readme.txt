$ cd target
$ unzip spark-example-2.0-SNAPSHOT.jar 
Archive:  spark-example-2.0-SNAPSHOT.jar
  inflating: META-INF/MANIFEST.MF    
   creating: com/
   creating: com/geekcap/
   creating: com/geekcap/javaworld/
   creating: com/geekcap/javaworld/sparkexample/
   creating: META-INF/maven/
   creating: META-INF/maven/com.geekcap.javaworld/
   creating: META-INF/maven/com.geekcap.javaworld/spark-example/
  inflating: com/geekcap/javaworld/sparkexample/WordCount$2.class  
  inflating: com/geekcap/javaworld/sparkexample/WordCount$3.class  
  inflating: com/geekcap/javaworld/sparkexample/WordCount$1.class  
  inflating: META-INF/maven/com.geekcap.javaworld/spark-example/pom.xml  
  inflating: com/geekcap/javaworld/sparkexample/WordCount.class  
  inflating: META-INF/maven/com.geekcap.javaworld/spark-example/pom.properties  


1. LOCAL java (output generated locally)

SG0212148@DCNU251B78K /cygdrive/c/dev/my-projects/my-apache-spark
$ java -jar target/spark-example-0.3-SNAPSHOT.jar pom.xml local
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
16/07/11 16:32:44 INFO SparkContext: Running Spark version 1.6.2
16/07/11 16:32:44 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
16/07/11 16:32:44 INFO SecurityManager: Changing view acls to: SG0212148
16/07/11 16:32:44 INFO SecurityManager: Changing modify acls to: SG0212148
16/07/11 16:32:44 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(SG0212148); users with modify permissions: Set(SG0212148)
16/07/11 16:32:45 INFO Utils: Successfully started service 'sparkDriver' on port 58540.
16/07/11 16:32:46 INFO Slf4jLogger: Slf4jLogger started
16/07/11 16:32:46 INFO Remoting: Starting remoting
16/07/11 16:32:46 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://sparkDriverActorSystem@10.162.247.72:58553]
16/07/11 16:32:46 INFO Utils: Successfully started service 'sparkDriverActorSystem' on port 58553.
16/07/11 16:32:46 INFO SparkEnv: Registering MapOutputTracker
16/07/11 16:32:46 INFO SparkEnv: Registering BlockManagerMaster
16/07/11 16:32:46 INFO DiskBlockManager: Created local directory at C:\dev\env\cygwin64\tmp\blockmgr-6a816324-e1be-4329-b6d3-60168ab927e0
16/07/11 16:32:46 INFO MemoryStore: MemoryStore started with capacity 1129.9 MB
16/07/11 16:32:46 INFO SparkEnv: Registering OutputCommitCoordinator
16/07/11 16:32:46 INFO Utils: Successfully started service 'SparkUI' on port 4040.
16/07/11 16:32:46 INFO SparkUI: Started SparkUI at http://10.162.247.72:4040
16/07/11 16:32:46 INFO Executor: Starting executor ID driver on host localhost
16/07/11 16:32:46 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 58560.
16/07/11 16:32:46 INFO NettyBlockTransferService: Server created on 58560
16/07/11 16:32:46 INFO BlockManagerMaster: Trying to register BlockManager
16/07/11 16:32:46 INFO BlockManagerMasterEndpoint: Registering block manager localhost:58560 with 1129.9 MB RAM, BlockManagerId(driver, localhost, 58560)
16/07/11 16:32:46 INFO BlockManagerMaster: Registered BlockManager
16/07/11 16:32:48 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 107.7 KB, free 107.7 KB)
16/07/11 16:32:48 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 9.8 KB, free 117.5 KB)
16/07/11 16:32:48 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on localhost:58560 (size: 9.8 KB, free: 1129.9 MB)
16/07/11 16:32:48 INFO SparkContext: Created broadcast 0 from textFile at WordCount.java:89
16/07/11 16:32:52 INFO FileInputFormat: Total input paths to process : 1
16/07/11 16:32:52 INFO deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
16/07/11 16:32:52 INFO deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
16/07/11 16:32:52 INFO deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
16/07/11 16:32:52 INFO deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
16/07/11 16:32:52 INFO deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
16/07/11 16:32:52 INFO SparkContext: Starting job: saveAsTextFile at WordCount.java:98
16/07/11 16:32:53 INFO DAGScheduler: Registering RDD 3 (mapToPair at WordCount.java:95)
16/07/11 16:32:53 INFO DAGScheduler: Got job 0 (saveAsTextFile at WordCount.java:98) with 1 output partitions
16/07/11 16:32:53 INFO DAGScheduler: Final stage: ResultStage 1 (saveAsTextFile at WordCount.java:98)
16/07/11 16:32:53 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
16/07/11 16:32:53 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 0)
16/07/11 16:32:53 INFO DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:95), which has no missing parents
16/07/11 16:32:53 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 5.5 KB, free 123.0 KB)
16/07/11 16:32:53 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 3.0 KB, free 126.0 KB)
16/07/11 16:32:53 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on localhost:58560 (size: 3.0 KB, free: 1129.9 MB)
16/07/11 16:32:53 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:1006
16/07/11 16:32:53 INFO DAGScheduler: Submitting 1 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:95)
16/07/11 16:32:53 INFO TaskSchedulerImpl: Adding task set 0.0 with 1 tasks
16/07/11 16:32:53 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, partition 0,PROCESS_LOCAL, 2058 bytes)
16/07/11 16:32:53 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
16/07/11 16:32:53 INFO HadoopRDD: Input split: file:/C:/dev/my-projects/my-apache-spark/pom.xml:0+2488
16/07/11 16:32:53 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 2253 bytes result sent to driver
16/07/11 16:32:53 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 197 ms on localhost (1/1)
16/07/11 16:32:53 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool
16/07/11 16:32:53 INFO DAGScheduler: ShuffleMapStage 0 (mapToPair at WordCount.java:95) finished in 0.219 s
16/07/11 16:32:53 INFO DAGScheduler: looking for newly runnable stages
16/07/11 16:32:53 INFO DAGScheduler: running: Set()
16/07/11 16:32:53 INFO DAGScheduler: waiting: Set(ResultStage 1)
16/07/11 16:32:53 INFO DAGScheduler: failed: Set()
16/07/11 16:32:53 INFO DAGScheduler: Submitting ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:98), which has no missing parents
16/07/11 16:32:53 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 49.5 KB, free 175.5 KB)
16/07/11 16:32:53 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 17.4 KB, free 192.9 KB)
16/07/11 16:32:53 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on localhost:58560 (size: 17.4 KB, free: 1129.8 MB)
16/07/11 16:32:53 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:1006
16/07/11 16:32:53 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:98)
16/07/11 16:32:53 INFO TaskSchedulerImpl: Adding task set 1.0 with 1 tasks
16/07/11 16:32:53 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1, localhost, partition 0,NODE_LOCAL, 1813 bytes)
16/07/11 16:32:53 INFO Executor: Running task 0.0 in stage 1.0 (TID 1)
16/07/11 16:32:53 INFO deprecation: mapred.output.dir is deprecated. Instead, use mapreduce.output.fileoutputformat.outputdir
16/07/11 16:32:53 INFO deprecation: mapred.output.key.class is deprecated. Instead, use mapreduce.job.output.key.class
16/07/11 16:32:53 INFO deprecation: mapred.output.value.class is deprecated. Instead, use mapreduce.job.output.value.class
16/07/11 16:32:53 INFO deprecation: mapred.working.dir is deprecated. Instead, use mapreduce.job.working.dir
16/07/11 16:32:53 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks out of 1 blocks
16/07/11 16:32:53 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 7 ms
16/07/11 16:32:53 INFO FileOutputCommitter: Saved output of task 'attempt_201607111632_0001_m_000000_1' to file:/C:/dev/my-projects/my-apache-spark/output/_temporary/0/task_201607111632_0001_m_000000
16/07/11 16:32:53 INFO SparkHadoopMapRedUtil: attempt_201607111632_0001_m_000000_1: Committed
16/07/11 16:32:53 INFO Executor: Finished task 0.0 in stage 1.0 (TID 1). 1165 bytes result sent to driver
16/07/11 16:32:53 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 178 ms on localhost (1/1)
16/07/11 16:32:53 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool
16/07/11 16:32:53 INFO DAGScheduler: ResultStage 1 (saveAsTextFile at WordCount.java:98) finished in 0.178 s
16/07/11 16:32:53 INFO DAGScheduler: Job 0 finished: saveAsTextFile at WordCount.java:98, took 0.572391 s
16/07/11 16:32:53 INFO SparkContext: Invoking stop() from shutdown hook
16/07/11 16:32:53 INFO SparkUI: Stopped Spark web UI at http://10.162.247.72:4040
16/07/11 16:32:53 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
16/07/11 16:32:53 INFO MemoryStore: MemoryStore cleared
16/07/11 16:32:53 INFO BlockManager: BlockManager stopped
16/07/11 16:32:53 INFO BlockManagerMaster: BlockManagerMaster stopped
16/07/11 16:32:53 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
16/07/11 16:32:53 INFO RemoteActorRefProvider$RemotingTerminator: Shutting down remote daemon.
16/07/11 16:32:53 INFO RemoteActorRefProvider$RemotingTerminator: Remote daemon shut down; proceeding with flushing remote transports.
16/07/11 16:32:53 INFO SparkContext: Successfully stopped SparkContext
16/07/11 16:32:53 INFO ShutdownHookManager: Shutdown hook called
16/07/11 16:32:53 INFO ShutdownHookManager: Deleting directory C:\dev\env\cygwin64\tmp\spark-9b2c799e-952c-4382-8318-de236609bca8



2. LOCAL java (output generated locally)

$ java -jar target/spark-example-0.3-SNAPSHOT.jar hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/bartek/pom.xml local
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
16/07/11 16:30:05 INFO SparkContext: Running Spark version 1.6.2
16/07/11 16:30:06 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
16/07/11 16:30:06 INFO SecurityManager: Changing view acls to: SG0212148
16/07/11 16:30:06 INFO SecurityManager: Changing modify acls to: SG0212148
16/07/11 16:30:06 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(SG0212148); users with modify permissions: Set(SG0212148)
16/07/11 16:30:08 INFO Utils: Successfully started service 'sparkDriver' on port 62692.
16/07/11 16:30:08 INFO Slf4jLogger: Slf4jLogger started
16/07/11 16:30:09 INFO Remoting: Starting remoting
16/07/11 16:30:09 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://sparkDriverActorSystem@10.162.247.72:54264]
16/07/11 16:30:09 INFO Utils: Successfully started service 'sparkDriverActorSystem' on port 54264.
16/07/11 16:30:09 INFO SparkEnv: Registering MapOutputTracker
16/07/11 16:30:09 INFO SparkEnv: Registering BlockManagerMaster
16/07/11 16:30:09 INFO DiskBlockManager: Created local directory at C:\dev\env\cygwin64\tmp\blockmgr-6f3011fa-49f5-4867-a45e-3abb2f3dd1ea
16/07/11 16:30:09 INFO MemoryStore: MemoryStore started with capacity 1129.9 MB
16/07/11 16:30:09 INFO SparkEnv: Registering OutputCommitCoordinator
16/07/11 16:30:09 INFO Utils: Successfully started service 'SparkUI' on port 4040.
16/07/11 16:30:09 INFO SparkUI: Started SparkUI at http://10.162.247.72:4040
16/07/11 16:30:09 INFO Executor: Starting executor ID driver on host localhost
16/07/11 16:30:09 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 57249.
16/07/11 16:30:09 INFO NettyBlockTransferService: Server created on 57249
16/07/11 16:30:09 INFO BlockManagerMaster: Trying to register BlockManager
16/07/11 16:30:09 INFO BlockManagerMasterEndpoint: Registering block manager localhost:57249 with 1129.9 MB RAM, BlockManagerId(driver, localhost, 57249)
16/07/11 16:30:09 INFO BlockManagerMaster: Registered BlockManager
16/07/11 16:30:11 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 107.7 KB, free 107.7 KB)
16/07/11 16:30:11 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 9.8 KB, free 117.5 KB)
16/07/11 16:30:11 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on localhost:57249 (size: 9.8 KB, free: 1129.9 MB)
16/07/11 16:30:11 INFO SparkContext: Created broadcast 0 from textFile at WordCount.java:89
16/07/11 16:30:17 INFO FileInputFormat: Total input paths to process : 1
16/07/11 16:30:17 INFO deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
16/07/11 16:30:17 INFO deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
16/07/11 16:30:17 INFO deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
16/07/11 16:30:17 INFO deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
16/07/11 16:30:17 INFO deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
16/07/11 16:30:17 INFO SparkContext: Starting job: saveAsTextFile at WordCount.java:98
16/07/11 16:30:17 INFO DAGScheduler: Registering RDD 3 (mapToPair at WordCount.java:95)
16/07/11 16:30:17 INFO DAGScheduler: Got job 0 (saveAsTextFile at WordCount.java:98) with 1 output partitions
16/07/11 16:30:17 INFO DAGScheduler: Final stage: ResultStage 1 (saveAsTextFile at WordCount.java:98)
16/07/11 16:30:17 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
16/07/11 16:30:17 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 0)
16/07/11 16:30:17 INFO DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:95), which has no missing parents
16/07/11 16:30:17 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 5.6 KB, free 123.1 KB)
16/07/11 16:30:17 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 3.1 KB, free 126.1 KB)
16/07/11 16:30:17 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on localhost:57249 (size: 3.1 KB, free: 1129.9 MB)
16/07/11 16:30:17 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:1006
16/07/11 16:30:17 INFO DAGScheduler: Submitting 1 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:95)
16/07/11 16:30:17 INFO TaskSchedulerImpl: Adding task set 0.0 with 1 tasks
16/07/11 16:30:17 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, partition 0,ANY, 2076 bytes)
16/07/11 16:30:18 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
16/07/11 16:30:18 INFO HadoopRDD: Input split: hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/bartek/pom.xml:0+2488
16/07/11 16:30:19 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 2253 bytes result sent to driver
16/07/11 16:30:19 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 1225 ms on localhost (1/1)
16/07/11 16:30:19 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool
16/07/11 16:30:19 INFO DAGScheduler: ShuffleMapStage 0 (mapToPair at WordCount.java:95) finished in 1.265 s
16/07/11 16:30:19 INFO DAGScheduler: looking for newly runnable stages
16/07/11 16:30:19 INFO DAGScheduler: running: Set()
16/07/11 16:30:19 INFO DAGScheduler: waiting: Set(ResultStage 1)
16/07/11 16:30:19 INFO DAGScheduler: failed: Set()
16/07/11 16:30:19 INFO DAGScheduler: Submitting ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:98), which has no missing parents
16/07/11 16:30:19 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 49.5 KB, free 175.7 KB)
16/07/11 16:30:19 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 17.4 KB, free 193.0 KB)
16/07/11 16:30:19 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on localhost:57249 (size: 17.4 KB, free: 1129.8 MB)
16/07/11 16:30:19 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:1006
16/07/11 16:30:19 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:98)
16/07/11 16:30:19 INFO TaskSchedulerImpl: Adding task set 1.0 with 1 tasks
16/07/11 16:30:19 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1, localhost, partition 0,NODE_LOCAL, 1813 bytes)
16/07/11 16:30:19 INFO Executor: Running task 0.0 in stage 1.0 (TID 1)
16/07/11 16:30:19 INFO deprecation: mapred.output.dir is deprecated. Instead, use mapreduce.output.fileoutputformat.outputdir
16/07/11 16:30:19 INFO deprecation: mapred.output.key.class is deprecated. Instead, use mapreduce.job.output.key.class
16/07/11 16:30:19 INFO deprecation: mapred.output.value.class is deprecated. Instead, use mapreduce.job.output.value.class
16/07/11 16:30:19 INFO deprecation: mapred.working.dir is deprecated. Instead, use mapreduce.job.working.dir
16/07/11 16:30:19 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks out of 1 blocks
16/07/11 16:30:19 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 3 ms
16/07/11 16:30:19 INFO FileOutputCommitter: Saved output of task 'attempt_201607111630_0001_m_000000_1' to file:/C:/dev/my-projects/my-apache-spark/output/_temporary/0/task_201607111630_0001_m_000000
16/07/11 16:30:19 INFO SparkHadoopMapRedUtil: attempt_201607111630_0001_m_000000_1: Committed
16/07/11 16:30:19 INFO Executor: Finished task 0.0 in stage 1.0 (TID 1). 1165 bytes result sent to driver
16/07/11 16:30:19 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 367 ms on localhost (1/1)
16/07/11 16:30:19 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool
16/07/11 16:30:19 INFO DAGScheduler: ResultStage 1 (saveAsTextFile at WordCount.java:98) finished in 0.368 s
16/07/11 16:30:19 INFO DAGScheduler: Job 0 finished: saveAsTextFile at WordCount.java:98, took 1.802596 s
16/07/11 16:30:19 INFO SparkContext: Invoking stop() from shutdown hook
16/07/11 16:30:19 INFO SparkUI: Stopped Spark web UI at http://10.162.247.72:4040
16/07/11 16:30:19 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
16/07/11 16:30:19 INFO MemoryStore: MemoryStore cleared
16/07/11 16:30:19 INFO BlockManager: BlockManager stopped
16/07/11 16:30:19 INFO BlockManagerMaster: BlockManagerMaster stopped
16/07/11 16:30:19 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
16/07/11 16:30:19 INFO SparkContext: Successfully stopped SparkContext
16/07/11 16:30:19 INFO ShutdownHookManager: Shutdown hook called
16/07/11 16:30:19 INFO ShutdownHookManager: Deleting directory C:\dev\env\cygwin64\tmp\spark-6920df3e-81f5-4073-b02e-aef646d2ecf8
16/07/11 16:30:19 INFO RemoteActorRefProvider$RemotingTerminator: Shutting down remote daemon.
16/07/11 16:30:19 INFO RemoteActorRefProvider$RemotingTerminator: Remote daemon shut down; proceeding with flushing remote transports.
16/07/11 16:30:19 INFO RemoteActorRefProvider$RemotingTerminator: Remoting shut down.

3. LOCAL cloudera & spark submit

# SparkConf conf = new SparkConf().setMaster("local").setAppName("Work Count App");

[cloudera@quickstart Downloads]$ spark-submit --class com.geekcap.javaworld.sparkexample.WordCount --master local[1] /home/cloudera/dev/my-projects/my-apache-spark/target/spark-example-1.0-SNAPSHOT.jar hdfs://quickstart.cloudera:8020/user/cloudera/pom.xml
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/usr/lib/zookeeper/lib/slf4j-log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/usr/lib/flume-ng/lib/slf4j-log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
16/07/11 04:25:30 INFO spark.SparkContext: Running Spark version 1.6.0
16/07/11 04:25:30 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
16/07/11 04:25:31 INFO spark.SecurityManager: Changing view acls to: cloudera
16/07/11 04:25:31 INFO spark.SecurityManager: Changing modify acls to: cloudera
16/07/11 04:25:31 INFO spark.SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(cloudera); users with modify permissions: Set(cloudera)
16/07/11 04:25:31 INFO util.Utils: Successfully started service 'sparkDriver' on port 45593.
16/07/11 04:25:32 INFO slf4j.Slf4jLogger: Slf4jLogger started
16/07/11 04:25:32 INFO Remoting: Starting remoting
16/07/11 04:25:32 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://sparkDriverActorSystem@10.0.2.15:41751]
16/07/11 04:25:32 INFO Remoting: Remoting now listens on addresses: [akka.tcp://sparkDriverActorSystem@10.0.2.15:41751]
16/07/11 04:25:32 INFO util.Utils: Successfully started service 'sparkDriverActorSystem' on port 41751.
16/07/11 04:25:32 INFO spark.SparkEnv: Registering MapOutputTracker
16/07/11 04:25:32 INFO spark.SparkEnv: Registering BlockManagerMaster
16/07/11 04:25:32 INFO storage.DiskBlockManager: Created local directory at /tmp/blockmgr-c25ea1f5-213a-44dc-b394-ec959f0a6df9
16/07/11 04:25:32 INFO storage.MemoryStore: MemoryStore started with capacity 530.0 MB
16/07/11 04:25:33 INFO spark.SparkEnv: Registering OutputCommitCoordinator
16/07/11 04:25:33 INFO server.Server: jetty-8.y.z-SNAPSHOT
16/07/11 04:25:33 INFO server.AbstractConnector: Started SelectChannelConnector@0.0.0.0:4040
16/07/11 04:25:33 INFO util.Utils: Successfully started service 'SparkUI' on port 4040.
16/07/11 04:25:33 INFO ui.SparkUI: Started SparkUI at http://10.0.2.15:4040
16/07/11 04:25:33 INFO spark.SparkContext: Added JAR file:/home/cloudera/dev/my-projects/my-apache-spark/target/spark-example-1.0-SNAPSHOT.jar at spark://10.0.2.15:45593/jars/spark-example-1.0-SNAPSHOT.jar with timestamp 1468236333601
16/07/11 04:25:33 INFO executor.Executor: Starting executor ID driver on host localhost
16/07/11 04:25:33 INFO util.Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 36301.
16/07/11 04:25:33 INFO netty.NettyBlockTransferService: Server created on 36301
16/07/11 04:25:33 INFO storage.BlockManagerMaster: Trying to register BlockManager
16/07/11 04:25:33 INFO storage.BlockManagerMasterEndpoint: Registering block manager localhost:36301 with 530.0 MB RAM, BlockManagerId(driver, localhost, 36301)
16/07/11 04:25:33 INFO storage.BlockManagerMaster: Registered BlockManager
16/07/11 04:25:35 INFO storage.MemoryStore: Block broadcast_0 stored as values in memory (estimated size 138.8 KB, free 138.8 KB)
16/07/11 04:25:35 INFO storage.MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 15.3 KB, free 154.1 KB)
16/07/11 04:25:35 INFO storage.BlockManagerInfo: Added broadcast_0_piece0 in memory on localhost:36301 (size: 15.3 KB, free: 530.0 MB)
16/07/11 04:25:35 INFO spark.SparkContext: Created broadcast 0 from textFile at WordCount.java:79
16/07/11 04:25:37 WARN shortcircuit.DomainSocketFactory: The short-circuit local reads feature cannot be used because libhadoop cannot be loaded.
16/07/11 04:25:37 INFO mapred.FileInputFormat: Total input paths to process : 1
16/07/11 04:25:38 INFO Configuration.deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
16/07/11 04:25:38 INFO Configuration.deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
16/07/11 04:25:38 INFO Configuration.deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
16/07/11 04:25:38 INFO Configuration.deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
16/07/11 04:25:38 INFO Configuration.deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
16/07/11 04:25:38 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
16/07/11 04:25:38 INFO spark.SparkContext: Starting job: saveAsTextFile at WordCount.java:88
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Registering RDD 3 (mapToPair at WordCount.java:85)
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Got job 0 (saveAsTextFile at WordCount.java:88) with 1 output partitions
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Final stage: ResultStage 1 (saveAsTextFile at WordCount.java:88)
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Missing parents: List(ShuffleMapStage 0)
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:85), which has no missing parents
16/07/11 04:25:38 INFO storage.MemoryStore: Block broadcast_1 stored as values in memory (estimated size 5.7 KB, free 159.8 KB)
16/07/11 04:25:38 INFO storage.MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 3.1 KB, free 162.9 KB)
16/07/11 04:25:38 INFO storage.BlockManagerInfo: Added broadcast_1_piece0 in memory on localhost:36301 (size: 3.1 KB, free: 530.0 MB)
16/07/11 04:25:38 INFO spark.SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:1006
16/07/11 04:25:38 INFO scheduler.DAGScheduler: Submitting 1 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[3] at mapToPair at WordCount.java:85)
16/07/11 04:25:38 INFO scheduler.TaskSchedulerImpl: Adding task set 0.0 with 1 tasks
16/07/11 04:25:38 INFO scheduler.TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, partition 0,ANY, 2213 bytes)
16/07/11 04:25:38 INFO executor.Executor: Running task 0.0 in stage 0.0 (TID 0)
16/07/11 04:25:38 INFO executor.Executor: Fetching spark://10.0.2.15:45593/jars/spark-example-1.0-SNAPSHOT.jar with timestamp 1468236333601
16/07/11 04:25:39 INFO util.Utils: Fetching spark://10.0.2.15:45593/jars/spark-example-1.0-SNAPSHOT.jar to /tmp/spark-5babeaba-4b54-4f71-a857-848aed00e926/userFiles-bdc2e675-6e66-406d-a8c3-5e8267a0df84/fetchFileTemp4190695363468335040.tmp
16/07/11 04:25:40 INFO executor.Executor: Adding file:/tmp/spark-5babeaba-4b54-4f71-a857-848aed00e926/userFiles-bdc2e675-6e66-406d-a8c3-5e8267a0df84/spark-example-1.0-SNAPSHOT.jar to class loader
16/07/11 04:25:40 INFO rdd.HadoopRDD: Input split: hdfs://quickstart.cloudera:8020/user/cloudera/pom.xml:0+2488
16/07/11 04:25:40 INFO executor.Executor: Finished task 0.0 in stage 0.0 (TID 0). 2253 bytes result sent to driver
16/07/11 04:25:40 INFO scheduler.TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 2080 ms on localhost (1/1)
16/07/11 04:25:40 INFO scheduler.TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
16/07/11 04:25:40 INFO scheduler.DAGScheduler: ShuffleMapStage 0 (mapToPair at WordCount.java:85) finished in 2.224 s
16/07/11 04:25:41 INFO scheduler.DAGScheduler: looking for newly runnable stages
16/07/11 04:25:41 INFO scheduler.DAGScheduler: running: Set()
16/07/11 04:25:41 INFO scheduler.DAGScheduler: waiting: Set(ResultStage 1)
16/07/11 04:25:41 INFO scheduler.DAGScheduler: failed: Set()
16/07/11 04:25:41 INFO scheduler.DAGScheduler: Submitting ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:88), which has no missing parents
16/07/11 04:25:41 INFO storage.MemoryStore: Block broadcast_2 stored as values in memory (estimated size 70.6 KB, free 233.5 KB)
16/07/11 04:25:41 INFO storage.MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 25.2 KB, free 258.7 KB)
16/07/11 04:25:41 INFO storage.BlockManagerInfo: Added broadcast_2_piece0 in memory on localhost:36301 (size: 25.2 KB, free: 530.0 MB)
16/07/11 04:25:41 INFO spark.SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:1006
16/07/11 04:25:41 INFO scheduler.DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (MapPartitionsRDD[5] at saveAsTextFile at WordCount.java:88)
16/07/11 04:25:41 INFO scheduler.TaskSchedulerImpl: Adding task set 1.0 with 1 tasks
16/07/11 04:25:41 INFO scheduler.TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1, localhost, partition 0,NODE_LOCAL, 1963 bytes)
16/07/11 04:25:41 INFO executor.Executor: Running task 0.0 in stage 1.0 (TID 1)
16/07/11 04:25:41 INFO storage.ShuffleBlockFetcherIterator: Getting 1 non-empty blocks out of 1 blocks
16/07/11 04:25:41 INFO storage.ShuffleBlockFetcherIterator: Started 0 remote fetches in 8 ms
16/07/11 04:25:41 INFO output.FileOutputCommitter: File Output Committer Algorithm version is 1
16/07/11 04:25:42 INFO output.FileOutputCommitter: Saved output of task 'attempt_201607110425_0001_m_000000_1' to hdfs://quickstart.cloudera:8020/user/cloudera/output/_temporary/0/task_201607110425_0001_m_000000
16/07/11 04:25:42 INFO mapred.SparkHadoopMapRedUtil: attempt_201607110425_0001_m_000000_1: Committed
16/07/11 04:25:42 INFO executor.Executor: Finished task 0.0 in stage 1.0 (TID 1). 2080 bytes result sent to driver
16/07/11 04:25:42 INFO scheduler.DAGScheduler: ResultStage 1 (saveAsTextFile at WordCount.java:88) finished in 1.025 s
16/07/11 04:25:42 INFO scheduler.TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 1030 ms on localhost (1/1)
16/07/11 04:25:42 INFO scheduler.TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool 
16/07/11 04:25:42 INFO scheduler.DAGScheduler: Job 0 finished: saveAsTextFile at WordCount.java:88, took 3.911618 s
16/07/11 04:25:42 INFO spark.SparkContext: Invoking stop() from shutdown hook
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/metrics/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/stage/kill,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/api,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/static,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/executors/threadDump/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/executors/threadDump,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/executors/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/executors,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/environment/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/environment,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/storage/rdd/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/storage/rdd,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/storage/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/storage,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/pool/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/pool,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/stage/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/stage,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/stages,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/jobs/job/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/jobs/job,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/jobs/json,null}
16/07/11 04:25:42 INFO handler.ContextHandler: stopped o.s.j.s.ServletContextHandler{/jobs,null}
16/07/11 04:25:42 INFO ui.SparkUI: Stopped Spark web UI at http://10.0.2.15:4040
16/07/11 04:25:43 INFO spark.MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
16/07/11 04:25:43 INFO storage.MemoryStore: MemoryStore cleared
16/07/11 04:25:43 INFO storage.BlockManager: BlockManager stopped
16/07/11 04:25:43 INFO storage.BlockManagerMaster: BlockManagerMaster stopped
16/07/11 04:25:43 INFO scheduler.OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
16/07/11 04:25:43 INFO spark.SparkContext: Successfully stopped SparkContext
16/07/11 04:25:43 INFO util.ShutdownHookManager: Shutdown hook called
16/07/11 04:25:43 INFO util.ShutdownHookManager: Deleting directory /tmp/spark-5babeaba-4b54-4f71-a857-848aed00e926
16/07/11 04:25:43 INFO remote.RemoteActorRefProvider$RemotingTerminator: Shutting down remote daemon.
16/07/11 04:25:43 INFO remote.RemoteActorRefProvider$RemotingTerminator: Remote daemon shut down; proceeding with flushing remote transports.

4. LOCAL & spark submit yarn (output on hdfs hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/output)

# // SparkConf conf = new SparkConf().setMaster("local").setAppName("Work Count App");
# SparkConf conf = new SparkConf().setAppName("Work Count App");

#failed
[cloudera@quickstart my-apache-spark]$ spark-submit --class com.geekcap.javaworld.sparkexample.WordCount --master yarn --deploy-mode cluster /home/cloudera/dev/my-projects/my-apache-spark/target/spark-example-0.3-SNAPSHOT.jar hdfs://quickstart.cloudera:8020/user/cloudera/pom.xml

[bdaldr@hadoop01 bartek]$ spark-submit --class com.geekcap.javaworld.sparkexample.WordCount --master yarn --deploy-mode cluster /login/bdaldr/bartek/spark-example-2.0-SNAPSHOT.jar hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/bartek/pom.xml
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/opt/cloudera/parcels/CDH-5.5.1-1.cdh5.5.1.p0.11/jars/avro-tools-1.7.6-cdh5.5.1.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/opt/cloudera/parcels/CDH-5.5.1-1.cdh5.5.1.p0.11/jars/slf4j-log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
16/07/11 07:16:40 INFO RMProxy: Connecting to ResourceManager at hadoop01.sgdcelab.sabre.com/10.14.236.209:8032
16/07/11 07:16:41 INFO Client: Requesting a new application from cluster with 4 NodeManagers
16/07/11 07:16:41 INFO Client: Verifying our application has not requested more than the maximum memory capability of the cluster (8192 MB per container)
16/07/11 07:16:41 INFO Client: Will allocate AM container, with 1408 MB memory including 384 MB overhead
16/07/11 07:16:41 INFO Client: Setting up container launch context for our AM
16/07/11 07:16:41 INFO Client: Setting up the launch environment for our AM container
16/07/11 07:16:41 INFO Client: Preparing resources for our AM container
16/07/11 07:16:41 INFO Client: Uploading resource file:/login/bdaldr/bartek/spark-example-2.0-SNAPSHOT.jar -> hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/.sparkStaging/application_1466685646767_2623/spark-example-2.0-SNAPSHOT.jar
16/07/11 07:16:42 INFO Client: Uploading resource file:/tmp/spark-f6c74fe3-85fb-401e-b1f6-e9cb05d63a8e/__spark_conf__3753405504865792218.zip -> hdfs://hadoop01.sgdcelab.sabre.com:8020/user/bdaldr/.sparkStaging/application_1466685646767_2623/__spark_conf__3753405504865792218.zip
16/07/11 07:16:42 INFO SecurityManager: Changing view acls to: bdaldr
16/07/11 07:16:42 INFO SecurityManager: Changing modify acls to: bdaldr
16/07/11 07:16:42 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(bdaldr); users with modify permissions: Set(bdaldr)
16/07/11 07:16:42 INFO Client: Submitting application 2623 to ResourceManager
16/07/11 07:16:42 INFO YarnClientImpl: Submitted application application_1466685646767_2623
16/07/11 07:16:43 INFO Client: Application report for application_1466685646767_2623 (state: ACCEPTED)
16/07/11 07:16:43 INFO Client:
         client token: N/A
         diagnostics: N/A
         ApplicationMaster host: N/A
         ApplicationMaster RPC port: -1
         queue: root.bdaldr
         start time: 1468239402583
         final status: UNDEFINED
         tracking URL: http://hadoop01.sgdcelab.sabre.com:8088/proxy/application_1466685646767_2623/
         user: bdaldr
16/07/11 07:16:44 INFO Client: Application report for application_1466685646767_2623 (state: ACCEPTED)
16/07/11 07:16:45 INFO Client: Application report for application_1466685646767_2623 (state: ACCEPTED)
16/07/11 07:16:46 INFO Client: Application report for application_1466685646767_2623 (state: ACCEPTED)
16/07/11 07:16:47 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:47 INFO Client:
         client token: N/A
         diagnostics: N/A
         ApplicationMaster host: 10.14.236.212
         ApplicationMaster RPC port: 0
         queue: root.bdaldr
         start time: 1468239402583
         final status: UNDEFINED
         tracking URL: http://hadoop01.sgdcelab.sabre.com:8088/proxy/application_1466685646767_2623/
         user: bdaldr
16/07/11 07:16:48 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:49 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:50 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:51 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:52 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:53 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:54 INFO Client: Application report for application_1466685646767_2623 (state: RUNNING)
16/07/11 07:16:55 INFO Client: Application report for application_1466685646767_2623 (state: FINISHED)
16/07/11 07:16:55 INFO Client:
         client token: N/A
         diagnostics: N/A
         ApplicationMaster host: 10.14.236.212
         ApplicationMaster RPC port: 0
         queue: root.bdaldr
         start time: 1468239402583
         final status: SUCCEEDED
         tracking URL: http://hadoop01.sgdcelab.sabre.com:8088/proxy/application_1466685646767_2623/history/application_1466685646767_2623/1
         user: bdaldr
16/07/11 07:16:55 INFO ShutdownHookManager: Shutdown hook called
16/07/11 07:16:55 INFO ShutdownHookManager: Deleting directory /tmp/spark-f6c74fe3-85fb-401e-b1f6-e9cb05d63a8e


