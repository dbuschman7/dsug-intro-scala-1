//
//
//
// Scala has traits - Java interfaces
trait IJavaInterface {
  def getXPlus1
  val x = 1
}

// but they are true mixins
trait SecondTrait {
  val y = 2
  def getY = y
}

// Multiple inheritance ?
class MyClass extends IJavaInterface with SecondTrait {
  def getX = x
  def getXPlus1 = x + 1
  def getYPlusX = getY + getX
}

// object are static singletons
object classes {
  val obj = new MyClass                           //> obj  : MyClass = MyClass@504bae78

  val results = Seq(obj.getX, obj.getY, obj.getYPlusX)
                                                  //> results  : Seq[Int] = List(1, 2, 3)
  println(s"Total = ${results.sum} for ${results}")
                                                  //> Total = 6 for List(1, 2, 3)
}


//
//
//
//