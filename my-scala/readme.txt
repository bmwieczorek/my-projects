SG0212148@DCNU251B78K /cygdrive/c/dev/my-projects/my-scala
$ java -cp target/my-scala-1.0-SNAPSHOT.jar HelloWorld
Hello, world!


C:\dev\my-projects\my-scala\src\main\scala>scalac HelloWorld.scala
C:\dev\my-projects\my-scala\src\main\scala>scala HelloWorld
Hello, world!
C:\dev\my-projects\my-scala\src\main\scala>dir
 Directory of C:\dev\my-projects\my-scala\src\main\scala
08/05/2016  16:24    <DIR>          .
08/05/2016  16:24    <DIR>          ..
08/05/2016  16:24               637 HelloWorld$.class
08/05/2016  16:24               586 HelloWorld.class
08/05/2016  16:07               104 HelloWorld.scala
               3 File(s)          1,327 bytes
               2 Dir(s)  84,023,054,336 bytes free


C:\dev\my-projects\my-scala>scala
Welcome to Scala 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_102).
Type in expressions for evaluation. Or try :help.
scala>
scala>
scala> object HelloWorld {
     |     def main(args: Array[String]): Unit = {
     |         println("Hello, world!")
     |     }
     | }
defined object HelloWorld
scala> HelloWorld.main(Array())
Hello, world!
scala> :q
C:\dev\my-projects\my-scala>
