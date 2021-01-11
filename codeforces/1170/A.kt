fun main(args : Array<String>) {
    val queries = readLine()!!.toInt()
    repeat(queries) {
        val (firstSum, secondSum) = readLine()!!.split(" ").map{it.toInt()}
        val num1 = 1
        val num2 = Math.min(firstSum, secondSum).minus(1)
        val num3 = Math.max(firstSum, secondSum).minus(num2)
        println("$num1 $num2 $num3")
    }
}