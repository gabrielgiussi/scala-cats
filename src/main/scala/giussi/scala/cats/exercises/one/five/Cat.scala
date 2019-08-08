package giussi.scala.cats.exercises.one.five

import cats.Eq
import cats.instances.option._
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._

object Cat {

  implicit val catEq = Eq.instance[Cat]{ (cat1, cat2) =>
    (cat1.name  === cat2.name ) &&
    (cat1.age   === cat2.age  ) &&
    (cat1.color === cat2.color)
  }

}

final case class Cat(name: String, age: Int, color: String)


object CatEqApp extends App {
  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(optionCat1 === optionCat2)
  println(optionCat1 =!= optionCat2)
  println(optionCat1 === optionCat1)
}