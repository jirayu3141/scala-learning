package lectures.part2oop

abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) -> new list with this element added
    override toString -> a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)

  // polymorphic call
  println(list.toString)

  val listOfIntegers: MyList[Int] = Empty
  val listOfStrings: MyList[String] = Empty

  println(listOfIntegers.add(99))
  listOfIntegers.add(100)
  listOfIntegers.add(100).printElements

  val listOfInteger2: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString2 = new Cons("Hello", new Cons("Scala", new Cons("Hohoho", Empty)))
  println(listOfInteger2.toString())
  println(listOfString2.toString())
  println(listOfString2.add(3).toString())
}
