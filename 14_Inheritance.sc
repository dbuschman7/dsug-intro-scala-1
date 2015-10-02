// inheritance and recursion

object caseClasses17 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Expr
  case class Number(n: Int) extends Expr
  case class Plus(l: Expr, r: Expr) extends Expr
  case class Minus(i: Expr, r: Expr) extends Expr

  def eval(e: Expr): Int = e match {
    case Number(v)   => v
    case Plus(l, r)  => eval(l) + eval(r)
    case Minus(l, r) => eval(l) - eval(r)
  }                                               //> eval: (e: caseClasses17.Expr)Int

  // 1 + (2 + 3)
  //eval(Plus(Number(1), Plus(Number(2), Number(3))))

  // 1 + (2 - 3)
  //eval(Plus(Number(1), Minus(Number(2), Number(3))))

}

//
//
//
//