
object functional1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def fun(a: Int) = {
    a + 1
    a - 2
    a * 3
  }                                               //> fun: (a: Int)Int

  println(fun(10))                                //> 30

  def identity(x: Int): Int = x                   //> identity: (x: Int)Int
  def square(x: Int): Int = x * x                 //> square: (x: Int)Int
  def cube(x: Int): Int = x * x * x               //> cube: (x: Int)Int

  // function as a parameter, sum the results of a fumction applied to the range a to b
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    f(a) + ((a == b) match {
      case true  => 0
      case false => sum(f, a + 1, b)
    })
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int

  Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).sum          //> res0: Int = 55
  
  Seq(identity _, square _, cube _).map { f => sum(f, 1, 10) }
                                                  //> res1: Seq[Int] = List(55, 385, 3025)

  sum(x => x * 3, 1, 10)                          //> res2: Int = 165

}