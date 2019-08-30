package giussi.scala.cats.exercises.two.five

import cats.instances.int._
import cats.instances.option._
import cats.instances.double._
import cats.syntax.semigroup._

object SuperAdderApp extends App {

  def add[A : cats.Monoid](items: List[A]): A = items.foldLeft(cats.Monoid[A].empty)(_ |+| _)

  println(add(List(1,2,3)))

  println(add(List(Option(1),Option(2),None)))

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid = new cats.Monoid[Order]{
    override def empty: Order = Order(0,0)

    override def combine(x: Order, y: Order): Order = {
      val quantity = x.quantity |+| y.quantity
      val cost = x.totalCost |+| y.totalCost
      Order(cost,quantity)
    }
  }

  println(add(List(
    Order(1,3),
    Order(5.4,3)
  )))
}
