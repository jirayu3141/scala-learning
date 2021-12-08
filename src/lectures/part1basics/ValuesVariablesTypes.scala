package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42
  println(x)

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = true
  val aChar: Char = 'c'
  val aShort: Short = 4613
  val aLong: Long = 123213132122L // L at the end indicates Long
  val aFloat: Float = 2.0f  // f at the end represents float
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4

  aVariable = 5 // this can change because it's a 'var' not a 'val'

}
