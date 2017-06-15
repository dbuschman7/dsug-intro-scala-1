  println("Welcome to the Scala worksheet")

  // currying
  def addA(x: Int, y: Int) = x + y

  def addB(x: Int): Int => Int = y => x + y

  addA(3, 4)

  val f1 = addB(3)
  f1(4)
  addB(3)(4)

  def curryMe(x: Int)(y: Int) = x + y

  val f2 = curryMe(4) _
  f2(3)

