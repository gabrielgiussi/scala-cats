package giussi.scala.cats.exercises.one.four

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._


object Cat {
  implicit val showCat = Show.show[Cat]{ case Cat(name, age, color) =>
    val n  = name.show
    val a   = age.show
    val c = color.show
    s"$n is a $a year-old $c cat."
  }
}

final case class Cat(name: String, age: Int, color: String)

object CatApp extends App {

  val cat = Cat("gabi",2,"black")

  println(cat.show)

}