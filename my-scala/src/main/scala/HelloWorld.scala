object HelloWorld {
    def main(args: Array[String]): Unit = {
        println("Hello, world!")
        val ints = Array(1, 2 ,3)
        val reduce = ints.reduce(_ + _)
        println(reduce)
    }
}