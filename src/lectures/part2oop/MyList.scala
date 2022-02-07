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
  /*
    add[type description of input](input): type of output
   */
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

}


object Empty extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[Nothing][] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements

  /** receive a trait MyTransformer, which within this trait, has the ability to transform element from one type to a
    * different type
    */
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    // for each of the element, apply the transform
    new Cons(transformer.transform(h), t.map(transformer))
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = ???
}

// 1. Generic trait MyPredicate[-T] with a method test(T) => Boolean
trait MyPredicate[-T] {
  def test(element: T): Boolean
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(element: Int): Boolean = if (element % 2 == 0) true else false
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

class StringToIntTransformer extends MyTransformer[Int, String] {
  override def transform(element: Int): String = "" + element + ": now string yo "
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

  val listOfInteger2: MyList[Int] =
    new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, new Cons(6, Empty))))))
  val listOfString2 = new Cons("Hello", new Cons("Scala", new Cons("Hohoho", Empty)))
  println(listOfInteger2.toString())
  println(listOfString2.toString())
  println(listOfString2.add(3).toString())

  println(listOfInteger2.filter(new EvenPredicate))
  println(listOfInteger2.map(new StringToIntTransformer))
  println(listOfInteger2.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }))

}
