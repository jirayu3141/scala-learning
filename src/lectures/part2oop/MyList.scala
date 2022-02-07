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
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (tail.isEmpty) "" + head
    else head + " " + tail.printElements

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  /*
    [1, 2].flatMap(n => [n, n+1])
    = [1, 2] ++ 2.flatMap(n => [n+1])
    = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n+1])
    = [1, 2 ,2 , 3]

   */
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    // transform the head and then concat it with the flatMapped tail, which returns a concatenated list
    transformer.transform(h) ++ t.flatMap(transformer)
  }


  // concatenation - take a list and return another list that is concatenated
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(elem: A): B
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

  println(listOfInteger2.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)


  println(listOfInteger2.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)


}
