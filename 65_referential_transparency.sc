object referential_transparency {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // If an expression can be replaced by its value without changing the behavior of the
  // program, it is said to be referentially transparent.

  // "referentially transparent" means that the value of expression can depend only
  // on the values of its parts, and not on any other facts about them.

  var value = 1000                                //> value  : Int = 1000

  def subtract(number: Int): Int = {
    value = value - number
    value
  }                                               //> subtract: (number: Int)Int

  // side effects
  subtract(100)                                   //> res0: Int = 900
  subtract(100)                                   //> res1: Int = 800

  case class Value(val total: Int) {
    def subtract(number: Int) = copy(total = total - number)
  }

  val f1 = Value(1000).subtract(100)              //> f1  : referential_transparency.Value = Value(900)
  val f2 = f1.subtract(100)                       //> f2  : referential_transparency.Value = Value(800)
  val f3 = f1.subtract(100)                       //> f3  : referential_transparency.Value = Value(800)

}