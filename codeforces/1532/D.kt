fun main() {
  val students = readLine()!!.toInt()
  val skills = readLine()!!.split(" ").map{it.toInt()}.toIntArray().sorted()
  val problemsToSolve = findProblemsSolved(students, skills)
  println(problemsToSolve)
}

fun findProblemsSolved(students: Int, skills: List<Int>): Int {
  var problemsToSolve = 0
  for (i in 1..students step 2) {
    problemsToSolve += skills[i]-skills[i-1]
  }
  return problemsToSolve
}