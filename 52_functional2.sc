  println("Welcome to the Scala worksheet")

  def identity(x: Int): Int = x
  def square(x: Int): Int = x * x
  def cube(x: Int): Int = x * x * x

  def minus(x: Int): Int = x - 1

  // function composition
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))


  Seq(1, 2, 3, 4).map(compose(square, minus))
  Seq(1, 2, 3, 4).map(minus).map(square)
  Seq(1, 2, 3, 4).map(square).map(minus)

