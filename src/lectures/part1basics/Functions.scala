package lectures.part1basics

import scala.annotation.tailrec

// extends app to be runnable by intellij
object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("String", 4))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
    Exercises:
    1. A greeting function for kids (name, age) => "Hi, my name is $name and I am $age years old"
    2. Factorial function => 1 * 2 * 3 * .. * n
    3. A Fibonacci function =>
      f(1) = 1
      f(2) = 1
      f(n) = f(n-1) + f(n-2)
    4. Tests if a number is prime.
   */

  // 1. A greeting function
  def greeting(name: String, age: Int) =
    "Hi, my name is " + name + " and I am " + age + " years old"
  println(greeting("Peter", 22))

  // 2. Factorial Function
  def factorial(n: Int): Int =
    if (n == 1) n
    else factorial(n - 1) * n

  println(factorial(5))

  // 3. Fibonacci
  def fibonacci(n: Int): Int = {
    if (n <= 1) n
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  println(fibonacci(9))

  // Test if the number is prime
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(15))


}


