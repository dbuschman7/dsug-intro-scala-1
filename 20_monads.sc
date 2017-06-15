//
//
//
//
import scala.util._

println("Welcome to the Scala Monads")

//  A monad is defined in the wikibook, and in Wikipedia, as having three parts:
//   1. A type constructor
//   2. A unit function, commonly called return in Haskell [apply(...) in Scala]
//   3. A binding operation, commonly called >>= in Haskell [map(...) or flatMap(...) in Scala]

//
// OPTION
// //////////////
Option.apply("string").map { s => println(s) }

Option("String") map println
Option[String](null).map { s => s.length }

def printString(in: String): String = {
	println(in);
	in
}

Option("String") map printString

//
// handling null
// ////////////////
val npe: String = null
val better: Option[String] = Option(npe)
val some = Option("foo")

var foo = npe
if (foo == null) {
	foo = "default"
}
println(foo)

better.getOrElse("default")
Option("foo").getOrElse("default")

//
// TRY
// //////////
val happyPath = Try[String]("Foo")
happyPath.isSuccess

val result = happyPath.get
val exCaught = Try(npe.toString)

exCaught.map { f => println("") }

exCaught.isFailure
exCaught.failed
val myEx = exCaught.failed.get



//
//
//
//