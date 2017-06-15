// Scala has traits - more than Java interfaces

trait IJavaInterface {
	def getXPlus1

	val x = 1
}

// but they are true mixins
trait ScalaTrait {
	val y = 2

	def getY = y
}

// Multiple inheritance ?
class MyClass(arg1: String) extends IJavaInterface with ScalaTrait {
	def getX = x

	def getXPlus1 = x + 1

	def getYPlusX = getY + getX
}

// object are static singletons
object MyClass {

}

val obj = new MyClass("foo)")

val results = Seq(obj.getX, obj.getY, obj.getYPlusX)
println(s"Total = ${results.sum} for ${results}")


//
//
//
//