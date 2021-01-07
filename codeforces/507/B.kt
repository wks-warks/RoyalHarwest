import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val (radius, x0, y0, x1, y1) = readLine()!!.split(" ").map(String::toInt)
    println(computeMoves(radius, getDistance(x0, y0, x1, y1)))
}

fun getDistance(x0: Int, y0: Int, x1: Int, y1: Int): Double {
    val xDiff = abs(x0.minus(x1)).toDouble()
    val yDiff = abs(y0.minus(y1)).toDouble()
    return sqrt(xDiff.pow(2.0) + yDiff.pow(2.0))
}

fun computeMoves(radius: Int, distance: Double): Int {
    val baseAnswer = distance.div(radius.times(2)).toInt()
    return if (distance.rem(radius.times(2)).equals(0.0)) baseAnswer else baseAnswer + 1
}