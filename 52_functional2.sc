object functional2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def identity(x: Int): Int = x                   //> identity: (x: Int)Int
  def square(x: Int): Int = x * x                 //> square: (x: Int)Int
  def cube(x: Int): Int = x * x * x               //> cube: (x: Int)Int

  def minus(x: Int): Int = x - 1                  //> minus: (x: Int)Int

  // function composition
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
                                                  //> compose: (f: Int => Int, g: Int => Int)Int => Int

  Seq(1, 2, 3, 4).map(compose(square, minus))     //> res0: Seq[Int] = List(0, 1, 4, 9)
  Seq(1, 2, 3, 4).map(minus).map(square)          //> res1: Seq[Int] = List(0, 1, 4, 9)
  Seq(1, 2, 3, 4).map(square).map(cube)           //> res2: Seq[Int] = List(1, 64, 729, 4096)

}