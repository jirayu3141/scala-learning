package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age : Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging with ${person.name}"
    def unary_! : String = s"$name, what the heck!"
    def isAlive: Boolean = true
    def  apply(): String = s"Hi, my name is $name and I like $favoriteMovie"


    // exercises
    // (1)
    def +(nickName : String) : Person = new Person(s"$name ($nickName)", favoriteMovie)
    // (2)
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    // (3)
    def learns(subject : String = "Scala") : String = s"$name learns $subject"
    def learnsScala : String = this learns "Scala"
    // (4)
    def apply(num : Int) = s"$name watch $favoriteMovie $num times"

  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?
  println(1+2)
  println(1.+(2))

  // prefix notation
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  //postfix
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  /*
    1. Overload the + operator
      mary  + "the rockstar" => person "Mary (the rockstar)"

    2. Add an age to the person class with default 0 value
       Add a unary + operator => new person with the age +1
       +mary => mary with the age incrementer

    3. Add a "learns" method in the Person class  => "Mary learns Scala"
       Add a learnsScala method, cals learns method with "Scala"
       Use it in postfix notion.

    4. Overload apply method
       mary.apply(2) => "Marry watched Inception 2 times"
   */


  // 1.
  println(mary + "the rockstar")
  println((mary + "the rockstar")())

  println(mary.+("the rockstar"))


  // 2.
  println((+mary).age)

  // 3.
  println(mary learns "Neuroscience")
  println(mary learnsScala)

  // 4
  println(mary.apply(2))
  println(mary(2))


}
