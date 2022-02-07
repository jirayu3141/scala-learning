package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  /** create a new class and then assign it to funnyAnimal (Anonymous class) * */
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahaha")
  }

  print(funnyAnimal.getClass)

  // the above is equivalent to:
  /*
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("hahahahaha")
    val funnyAnimal = new AnonymousClasses$$anon$1
  }
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of your service")
  }

  jim.sayHi

  /*

   */

}