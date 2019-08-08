package giussi.scala.cats.exercises.one.three

import giussi.scala.cats.exercises.one.three.PrintableApp.cat

trait Printable[A] {
  def format(value: A): String
}

object Printable {
  def format[A : Printable](value: A): String = implicitly[Printable[A]].format(value)

  def print[A : Printable](value: A): Unit = println(format(value))
}

object PrintableInstances {
  implicit val printableInt = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val printableString = new Printable[String] {
    override def format(value: String): String = value
  }
}

final case class Cat(name: String, age: Int, color: String)

object Cat {
  import PrintableInstances._
  implicit val printableCat = new Printable[Cat] {
    override def format(value: Cat): String = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}

object PrintableApp extends App {
  import PrintableInstances._
  import PrintableSyntax._

  val cat = Cat("tom",1,"white")

  Printable.print(1)

  Printable.print(cat)

  1.print
  cat.print


}


object PrintableSyntax {
  implicit class PrintableOps[A](val a: A) extends AnyVal {
    def format(implicit evidence: Printable[A]): String = evidence.format(a)

    def print(implicit evidence: Printable[A]): Unit = println(format)
  }
}