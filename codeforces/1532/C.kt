fun main() {
  repeat(readLine()!!.toInt()) {
    val (stringLength, alphabets) = readLine()!!.split(" ").map{it.toInt()}
    val output = getString(stringLength, alphabets)
    println(output)
  }
}

fun getString(stringLength: Int, alphabets: Int): String {
  val sb = StringBuilder()
  while (sb.length < stringLength) {
    val ch: Char = (sb.length % alphabets + 'a'.toInt()).toChar()
    sb.append(ch)
  }
  return sb.toString()
}