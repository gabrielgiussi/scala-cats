package giussi.scala.cats.exercises.two.four

import giussi.scala.cats.exercises.two.three.{Monoid, Semigroup}

object SemigroupSetApp extends App {

  implicit def set[A] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }

  implicit val intSetMonoid = Monoid[Set[Int]]

  println(intSetMonoid.empty)


  implicit def intersection[A] = new Semigroup[Set[A]]{
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }
}
