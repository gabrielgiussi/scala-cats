package giussi.scala.cats.exercises.two.three


trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
}

object SemigroupApp extends App {

  val and = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  val or = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  val xor = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }



  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]) = m.combine(x,m.combine(y,z)) == m.combine(m.combine(x,y),z)

  def identityLaw[A](x: A)(implicit m: Monoid[A]) = (m.combine(x,m.empty) == x) && (m.combine(m.empty,x)  == x)

  List(
    identityLaw(true)(or),
    identityLaw(false)(or),
    identityLaw(true)(and),
    identityLaw(false)(and)
  ).foreach(println)
}
