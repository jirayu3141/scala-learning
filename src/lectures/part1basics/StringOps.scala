package lectures.part1basics

object StringOps extends App {
  val str: String =  "Hello, I am learning Scala"
  println(str.charAt(2))
  println(str.substring(0, 3))
  println(str.split(" ").toList)  //.splt returns an array
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)
  println(str.reverse)
  println(str.take(2))

  println("------")
  val aNumberString = "2"
  val anNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println("------")

  // String interpolators (Scala-specific)
  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  println(greeting)
  val anotherGreeting = s"Hello, my name is $name and I am ${age + 1} years old"
  println(anotherGreeting)

  // F-interpolators
  val speed = 1f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)




}
