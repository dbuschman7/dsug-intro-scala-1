//
//
//
//
import scala.util.Try

object monads {
  println("Welcome to the Scala Monads")          //> Welcome to the Scala Monads

  //  A monad is defined in the wikibook, and in Wikipedia, as having three parts:
  //   1. A type constructor
  //   2. A unit function, commonly called return in Haskell[, apply in Scala]
  //   3. A binding operation, commonly called >>= in Haskell[, map or flatMap in Scala]

  val npe: String = null                          //> npe  : String = null
  val better: Option[String] = Option(npe)        //> better  : Option[String] = None
  val some = Option("foo")                        //> some  : Option[String] = Some(foo)

  // OPTION
  // //////////////
  //

  Option.apply("string").map { s => println(s) }  //> string
                                                  //| res0: Option[Unit] = Some(())
  Option("String") map (println)                  //> String
                                                  //| res1: Option[Unit] = Some(())
  def printString(in: String): String = { println(in); in }
                                                  //> printString: (in: String)String
  Option("String") map printString                //> String
                                                  //| res2: Option[String] = Some(String)
  // handling null
  var foo = npe                                   //> foo  : String = null
  if (foo == null) {
    foo = "default"
  }
  println(foo)                                    //> default

  better.getOrElse("default")                     //> res3: String = default
  Option("foo").getOrElse("default")              //> res4: String = foo

  // TRY
  val happyPath = Try("Foo")                      //> happyPath  : scala.util.Try[String] = Success(Foo)
  happyPath.isSuccess                             //> res5: Boolean = true
  val result = happyPath.get                      //> result  : String = Foo
  val exCaught = Try(npe.toString)                //> exCaught  : scala.util.Try[String] = Failure(java.lang.NullPointerException)
                                                  //| 
  exCaught.isFailure                              //> res6: Boolean = true
  exCaught.failed                                 //> res7: scala.util.Try[Throwable] = Success(java.lang.NullPointerException)
  val myEx = exCaught.failed.get                  //> myEx  : Throwable = java.lang.NullPointerException
}


//
//
//
//