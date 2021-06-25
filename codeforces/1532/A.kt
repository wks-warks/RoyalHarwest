import java.util.Scanner

fun main() {
  repeat(readLine()!!.toInt()) {
    val (first, second) = readLine()!!.split(" ").map{it.toInt()}
    val output = sum(first, second)
    println(output)
  }
}

fun sum(a: Int, b: Int): Int {
  return a + b;
}