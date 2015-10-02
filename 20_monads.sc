//
//
//
//
import scala.util.Try

object monads {
  println("Welcome to the Scala Monads")          //> Welcome to the Scala Monads

  //  A monad is defined in the wikibook, and in Wikipedia, as having three parts:
  //   1. A type constructor
  //   2. A unit function, commonly called return in Haskell [apply in Scala]
  //   3. A binding operation, commonly called >>= in Haskell [map or flatMap in Scala]

  val npe: String = null                          //> npe  : String = null
  val better: Option[String] = Option(npe)        //> better  : Option[String] = None
  val some = Option("foo")                        //> some  : Option[String] = Some(foo)

  //
  // OPTION
  // //////////////
  //  Option.apply("string").map { s => println(s) }
  //  Option("String") map (println)
  //  def printString(in: String): String = { println(in); in }
  //  Option("String") map printString

  //
  // handling null
  // ////////////////
  //  var foo = npe
  //  if (foo == null) {
  //    foo = "default"
  //  }
  //  println(foo)

  //  better.getOrElse("default")
  //  Option("foo").getOrElse("default")

  //
  // TRY
  // //////////
  //  val happyPath = Try("Foo")
  //  happyPath.isSuccess
  //  val result = happyPath.get
  //  val exCaught = Try(npe.toString)
  //  exCaught.isFailure
  //  exCaught.failed
  //  val myEx = exCaught.failed.get
}

//
//
//
//