package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild creature"
    def eat = println("nomnomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println(dog.creatureType)
    }
  }
//  class Dog(dogType: String) extends Animal {
//    override val creatureType: String = dogType
//  }
  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphysim)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  // prevent overrides
  // 1. - use final on member
  // 2. - use finial in class
  // 3. - seal the class = can extend class in this file only, but prevent extension to other file



}
