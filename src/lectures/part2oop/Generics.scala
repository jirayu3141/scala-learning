package lectures.part2oop

object Generics extends App {

  /** right now, MyList is a CovariantList, which means that it can be of any type and we can create a new class that accepts the
    * subclasses of whatever A is.
    * @tparam A - A generic type
    */
  class MyList[+A] {

    /** If to a list of A, we apple this add method and provide an element with a type of B, which is a parent of A, then
      * we turn ths class (list) into a list of B
      */
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A - Cat
      B - Dog <: Animal

      If we add a B (of type Dog) to a list of A (of type Cat), then this list will turn into a list of Animal
      because Dog is an animal.
     */
  }

  val newAnimalList = new MyList[Cat]
  newAnimalList.add(new Cat)
  newAnimalList.add(new Dog)

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods - if we want to return a generic class
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem - can they extend?
  class Animal
  class Cat extends Animal
  class Dog extends Animal

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
  val trainer: Trainer[Cat] =
    new Trainer[Animal] // animal trainer can train cat!

  // bounded types - only allow certain types
  // class A only accepts types that are subclasses of Animal
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

}
