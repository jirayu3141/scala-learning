package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // Expression (1+2)
  print(x)

  println(2 + 3 * 4)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))

  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3
  println(if(aCondition) 5 else 3)


}
