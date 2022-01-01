package lectures.part2oop

object Generics extends App {
  class MyList[A] {
    // use the type A
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem - can they extend?
  class Animal
  class Cat extends Animal
  class Dg extends Animal

  // 1.  Yes, Covariance - List[Cat] extends List[Animal]
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // cannot do animalList.add(new Dog)

  // 2. NO  = Invariance
  class InvariantList[A]
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell no, Contravariance
  class Contravariance[-A]
  val contravariance: Contravariance[Cat] = new Contravariance[Animal]
  // seems counter intuitive but watch this
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // animal trainer can train cat!

  // bounded types - only allow certain types



}
