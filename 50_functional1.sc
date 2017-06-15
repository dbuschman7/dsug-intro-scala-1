
  println("Welcome to the Scala worksheet")

  def fun(a: Int) = {
    a + 1
    a - 2
    a * 3
  }

  println(fun(10))

  def identity(x: Int): Int = x
  def square(x: Int): Int = x * x
  def cube(x: Int): Int = x * x * x

  // function as a parameter, sum the results of a fumction applied to the range a to b
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    f(a) + ((a == b) match {
      case true  => 0
      case false => sum(f, a + 1, b)
    })
  }

  Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).sum

  Seq(identity _, square _, cube _).map { f => sum(f, 1, 10) }


  sum(x => x * 3, 1, 10)

