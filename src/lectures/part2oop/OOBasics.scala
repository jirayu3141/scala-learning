package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet2()
}

class Person (name: String, val age: Int) {
  val x = 2

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  def greet2(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
// class parameters are not fields (only accessible if there's val before it)

/*
  Novel and a write
  writer: first name, surname, year
  - method fullname

  Novel: name, year of release, author
  - method authorAge (age of the author at the time of the release)
  - isWrittenBy (author)
  - copy (new year of release) = new instance of Nobel with new year of release
 */

class Writer(firstname: String, lastname: String, val year: Int)  {
  def fullName() = s"$firstname $lastname"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

object BookDriver extends App {
  // create a new author
  println("===creating an author===")
  val author = new Writer("Mark", "Twain", 1874)
  println(author.fullName())
  println("===finished creating author, writing a new book...===")

  //Crete a new book
  val book1 = new Novel("Tom and Jerry", 1890, author)
  println(book1.authorAge())
  println(book1.isWrittenBy(author))
  println("===writing a copy...===")

  val book2 = book1.copy(1892)
  println(book2.authorAge())
  println(book2.isWrittenBy(author))

}


/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new counter
    - overload inc/dec to receive an amount => new counter
 */

class Counter(val count: Int = 0) {
  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1)
  }
  def dec: Counter = {
    println("decrementing")
    new Counter(count-1)
  }

  def inc(n: Int): Counter = {
    if(n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if(n <= 0) this
    else dec.dec(n-1)
  }

  def print(): Unit = println(s"current count is: $count")
}

object CounterDriver extends App {
  // create new counter
  var counter = new Counter
  counter.inc.print()
  counter.inc.inc.inc.print()
  counter.inc(10).print()




}


