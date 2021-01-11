fun main(args: Array<String>) {
  val tests = readLine()!!.toInt()
  repeat(tests) {
    val originalString = readLine()!!
    val conversionDesired = readLine()!!
    println(if (conversionPossible(originalString, conversionDesired)) "YES" else "NO")
  }
}

fun conversionPossible(originalString: String, conversionDesired: String): Boolean {
  var ptr1 = 0
  var ptr2 = 0
  while (ptr1 < originalString.length && ptr2 < conversionDesired.length) {
    val originalCharacter = getChar(ptr1, originalString)
    val convertedCharacter = getChar(ptr2, conversionDesired)
    if (originalCharacter!!.equals(convertedCharacter!!)) {
      ptr1 = ptr1.plus(1)
      ptr2 = ptr2.plus(1)
    } else {
      if (originalCharacter.equals('+') && convertedCharacter.equals('-')) return false
      if (getChar(ptr1+1, originalString)?.equals('-') ?: false) {
        ptr1 = ptr1.plus(2)
        ptr2 = ptr2.plus(1)
      } else {
        return false
      }
    }
  }

  return ptr1.equals(originalString.length) && ptr2.equals(conversionDesired.length)
}

fun getChar(idx: Int, str: String): Char? = if (str?.length>idx) str?.get(idx) else null 