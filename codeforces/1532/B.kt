fun main() {
  repeat(readLine()!!.toInt()) {
    val (rightJump, leftJump, jumps) = readLine()!!.split(" ").map{it.toInt()}
    val output = finalPos(rightJump, leftJump, jumps)
    println(output)
  }
}

fun finalPos(rightJump: Int, leftJump: Int, jumps: Int): Long {
  val rightCount = (jumps + 1).toLong() shr 1
  val leftCount = jumps.toLong() shr 1
  return rightCount * rightJump - leftCount * leftJump
}