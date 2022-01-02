package lectures.part3fp

object MapFlapMapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  //map
  val test = list.map(x => x + 1)
  println(list.map(x => x + 1))
  println(list.map(_ + 1))
  println(list.map(_ + " is a member"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap - for each element, generate a list
  val toPair: Int => List[Int] = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))
  // for each of element x in the list, convert it to a list of its own with element of x and x+1
  println(list.flatMap(x => List(x, x + 1)))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List(1, 2, 3, 4)
  val colors = List("black", "white")
  // print List("a1", "a2",..."d4")

  // for each of the element x,
  val plusA: Int => List[String] = (num: Int) => List(num + "a")
  // for each of the element x, return a new list with element of x"a"
  val result = numbers.flatMap(plusA)
  println(result)
  // for each of the element n, convert that element to list of something where in the list, we have the element of n and
  // each of the character c
  val result2 = numbers.flatMap(n => chars.map(c => c + n.toString))
  println(result2)

  val result3 = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + color)))
  println(result3)

  // for each
  list.foreach(println)

  // for-comprehension
  val forCombinations = for {
    n <- numbers
    c <- chars
  } yield c + n

  println(forCombinations)

  // same as list.foreach(println)
  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map { x =>
    x * 2
  })

  /*
    A small collection of at most ONE element - Maybe[+T]
    - map, flatMap, filter
   */


}
