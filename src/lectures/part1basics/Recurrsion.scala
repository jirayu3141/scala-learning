package lectures.part1basics

import scala.annotation.tailrec

object Recurrsion extends App {

  def factorial(n: Int) : BigInt = {
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " * factorial(" + (n-1) + ")")
      val result = n * factorial(n-1)
      println("Computed factorial of " + n + " * factorial(" + (n-1) + ")")
      result

    }
  }
  println(factorial(10))

  def anotherFac(n: Int) : BigInt = {
    @tailrec
    def facHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else facHelper(x-1, x * accumulator)
    }
    facHelper(n, 1)
  }
  println(anotherFac(1000))

  //Exercises
  // 1. Concatenate a string n times
  def concatString(aString: String, n: Int) : String =
    if (n <= 1) aString
    else aString + concatString(aString, n-1)
  println(concatString("cat", 3))

  def concatString2(aString : String, n: Int) : String = {
    @tailrec
    def concatHelper(x: Int, accumulated: String): String = {
      if(x <= 1) accumulated
      else concatHelper(x-1, aString + accumulated)
    }
    concatHelper(n, aString)
  }
  println(concatString2("cat", 3))

  @tailrec
  def concatString3(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatString3(aString, n-1, accumulator + aString)

  println(concatString3("cat", 3, ""))


  // 2. reverse a string
  def reverseString(aString: String): String = {
    @tailrec
    def strHelper(x: Int, accumulated: String): String = {
      if (x <= 0) accumulated
      else strHelper(x-1, accumulated + aString.charAt(x-1))
    }
    strHelper(aString.length, "")
  }
  println(reverseString("cat"))
  /*
   reverseString("cat") = strHelper(3, "")
   strHelper(3, "") = strHelper(2, "" + "t")
   strHelper(2, "" + "t") = strHelper(1, "" + "t" + "a")
   strHelper(1, "" + "t" + "a") = strHelper(0, "" + "t" + "a" + "c")
   */


  //3. IsPrime
  def isPrime(n: Int) : Boolean = {
    @tailrec
    def helper(x: Int, accumulated: Boolean): Boolean = {
      if (x <= 1) accumulated
      else if (x == 2) accumulated
      else helper(x-1, accumulated && (n % x != 0))
    }
    helper(n/2, accumulated = true)
  }
  println(isPrime(2))
  println(isPrime(35))
  println(isPrime(37))
  println(isPrime(105) + "\n------------\n")


  @tailrec
  def isPrime2(n: Int, buffer: Int, accumulator: Boolean): Boolean =
    if (buffer <= 3) accumulator
    else isPrime2(n, buffer-1, accumulator && (n%(buffer-1) != 0))

  println(isPrime2(2, 2, accumulator = true))
  println(isPrime2(35, 35, accumulator = true))
  println(isPrime2(37, 37, accumulator = true))
  println(isPrime2(2009, 2009, accumulator = true) + "\n-----\n")

  def isPrime3(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailRec(n/2, isStillPrime = true)
  }
  println(isPrime3(2))

  //4. Fibonacci
  def fib(n: Int) : Int = {
    @tailrec
    def helper(x: Int, furPrevSum: Int, prevSum: Int): Int = {
      if (x == 0) 0
      else if(x >= n) prevSum + furPrevSum
      else helper(x+1, prevSum, furPrevSum + prevSum)
    }
    helper(if(n == 0) 0 else 2, 0, 1)
  }
  println(fib(18))

  def printFib(n: Int) : Unit = {
    if (n <= 0) print(fib(0) + " ")
    else {
      printFib(n-1)
      print(fib(n) + " ")
    }
  }
  printFib(18)

}
