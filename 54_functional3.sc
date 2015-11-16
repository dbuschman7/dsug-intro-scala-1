object functional3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // currying
  def addA(x: Int, y: Int) = x + y                //> addA: (x: Int, y: Int)Int

  def addB(x: Int): Int => Int = y => x + y       //> addB: (x: Int)Int => Int

  addA(3, 4)                                      //> res0: Int = 7
  addB(3)(4)                                      //> res1: Int = 7

  def curryMe(x: Int)(y: Int) = x + y             //> curryMe: (x: Int)(y: Int)Int

  val f = curryMe(4) _                            //> f  : Int => Int = <function1>
  f(3)                                            //> res2: Int = 7

}