package fr.upem.partiel.part1

import java.time.Instant
import java.time.temporal.ChronoUnit.YEARS

import scala.util.Try


// Part 1 (10pts)
object Part1 {

  // 1.1 Apply 'mul2' using pattern matching to the given integer (.5pts)
  def mul2(i: Int): Int = i * 2
  def applyMul2WithPatternMatching(i: Option[Int]): Option[Int] =
    i match {
      case Some(x) => Some(x*2)
      case None => None
    }

  // 1.2 Apply 'mul2' WITHOUT using pattern matching to the given integer (.5pts)
  def applyMul2WithoutPatternMatching(i: Option[Int]): Option[Int] = {
    if (i.isEmpty)
      None
    else
      Option(i.get * 2)
  }




  // 1.3 Refactor the following code using pattern matching (1pts)
  sealed trait Animal
  case object Cat extends Animal
  case object Bird extends Animal
  case class Dog(age: Int) extends Animal

  /*
  def formatAnimal(animal: Animal): String =
    if (animal == Cat)
      "It's a cat"
    else if (animal == Bird)
      "It's a bird"
    else if (animal.isInstanceOf[Dog])
      s"It's a ${animal.asInstanceOf[Dog].age} year old dog"
    else
      throw new RuntimeException("This should not happen but I'm a Java developer !")

  */
  def formatAnimal(animal: Animal): String = animal match {
    case Cat => "It's a cat"
    case Bird => "It's a bird"
    case Dog(age) => s"It's a ${age} year old dog"
  }

  // 1.4 Find the index of the given element if any, use recursivity (1pts)
  def indexOf[A](l: List[A], a: A): Option[Int] = l match {
    case Nil => None
    case x :: Nil if x != a => None
    case x :: _ if x == a => Option(0)
    case _ :: xs => indexOf(xs, a) match {
      case Some(value) => Option(value + 1)
      case None => None
    }
  }

  // 1.5 Throw away all errors (.5pts)
  case class Error(message: String)
  def keepValid[A](l: List[Either[Error, A]]): List[A] = l.collect { case Right(r) => r}


  // 1.6 Aggregate values (.5pts)
  def aggregate[A](l: List[A], combine: (A, A) => A, empty: A): A = ???

  // 1.7 Aggregate valid values (.5pts)
  def aggregateValid[A](l: List[Either[Error, A]], combine: (A, A) => A, empty: A): A = ???


  // 1.8 Create the Monoid typeclass and rewrite the above "aggregateValid" (.5pts)
  trait Monoid[A] {
    def empty: A
    def combine(x: A, y: A): A
  }

  def aggregateValidM = ???

  // 1.9 Implement the Monoid typeclass for Strings and give an example usage with aggregateValidM (.5pts)




  // 1.10 Refactor the following object oriented hierarchy with an ADT (1.5pts)

  /*
  sealed trait FinancialAsset {
    def computeEarnings: Double
  }


  case class FlatRateAsset(rate : Double, amount : Double) extends FinancialAsset {
    override def computeEarnings: Double = amount + (amount * rate)
   }

  case object LivretA {
    private val Rate: Double = 0.75
  }
  case class LivretA() extends FlatRateAsset {

  }

  object Pel {
    private val Rate: Double = 1.5
    private val GovernmentGrant: Int = 1525
  }

  case class Pel(amount: Double, creation: Instant) extends FlatRateAsset {
    override protected val rate: Double = Pel.Rate
    override def computeEarnings: Double =
      if (Instant.now().minus(4, YEARS).isAfter(creation))
        super.computeEarnings + Pel.GovernmentGrant
      else
        super.computeEarnings
  }

  case object CarSale {
    private val StateHorsePowerTaxation: Int = 500
  }
  case class CarSale(amount: Int, horsePower: Int) extends FinancialAsset {
    override def computeEarnings: Double = amount - (CarSale.StateHorsePowerTaxation * horsePower)
  }
  */





  abstract class FinancialAsset {
    def computeEarnings: Double
  }

  abstract class FlatRateAsset extends FinancialAsset {
    protected val rate: Double
    protected val amount: Double

    override def computeEarnings: Double = amount + (amount * rate)
  }

  object LivretA {
    private val Rate: Double = 0.75
  }

  case class LivretA(override val amount: Double) extends FlatRateAsset {
    override protected val rate: Double = LivretA.Rate
  }

  object Pel {
    private val Rate: Double = 1.5
    private val GovernmentGrant: Int = 1525
  }

  case class Pel(amount: Double, creation: Instant) extends FlatRateAsset {
    override protected val rate: Double = Pel.Rate
    override def computeEarnings: Double =
      if (Instant.now().minus(4, YEARS).isAfter(creation))
        super.computeEarnings + Pel.GovernmentGrant
      else
        super.computeEarnings
  }

  object CarSale {
    private val StateHorsePowerTaxation: Int = 500
  }
  class CarSale(amount: Int, horsePower: Int) extends FinancialAsset {
    override def computeEarnings: Double = amount - (CarSale.StateHorsePowerTaxation * horsePower)
  }


  // 1.11 Extract the "computeEarnings" logic of the above hierarchy
  // into an "Earnings" typeclass and create the adequate instances (1.5pts)



  // 1.12 Rewrite the following function with your typeclass (.5pts)
  def computeTotalEarnings(assets: List[FinancialAsset]): Double =
    assets.map(_.computeEarnings).sum


  // 1.13 Enrich the "String" type with an "atoi" extension method that parses the
  // given String to an Int IF possible (1pts)

  object Implicits {
    implicit class StringAtoi(val s: String) extends AnyVal {
      def atoi: Int = s.toInt
    }
  }


}
