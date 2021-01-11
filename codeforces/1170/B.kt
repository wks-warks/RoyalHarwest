fun main(args: Array<String>) {
  readLine() // Ignore value of days
  val visits = readLine()!!.split(" ").map{it.toInt()}
  val badDays = getBadCount(visits)
  println(badDays)
}

fun getBadCount(visits: List<Int>): Int {
  var max = Integer.MIN_VALUE
  var secondMax = Integer.MIN_VALUE
  var badDays = 0

  for (dayVisit in visits) {
    if (secondMax > dayVisit) {
      badDays = badDays.plus(1)
    } else if (dayVisit > max) {
      secondMax = max
      max = dayVisit
    } else {
      secondMax = dayVisit
    }
  }
  
  return badDays
}