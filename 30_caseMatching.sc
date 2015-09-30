//
//
//
//
import scala.util._
import java.util.UUID

object caseMatching {
  println("Welcome to the Scala Case Matching")   //> Welcome to the Scala Case Matching

  val npe: String = null                          //> npe  : String = null
  val better: Option[String] = Option(npe)        //> better  : Option[String] = None
  val some = Option("foo")                        //> some  : Option[String] = Some(foo)

  val happyPath = Try("Foo")                      //> happyPath  : scala.util.Try[String] = Success(Foo)
  val exCaught = Try(npe.toString)                //> exCaught  : scala.util.Try[String] = Failure(java.lang.NullPointerException)
                                                  //| 

  Seq(better, some).foreach(println)              //> None
                                                  //| Some(foo)

  Seq(better, some).foreach { o =>
    o match {
      case Some(s) => println(s" Value is ${s}")
      case None    => println("No Value")
    }
  }                                               //> No Value
                                                  //|  Value is foo
  Seq(happyPath, exCaught).foreach { t =>
    t match {
      case Failure(ex) => println(ex.getClass.getName)
      case Success(s)  => println(s.getClass.getName)
    }
  }                                               //> java.lang.String
                                                  //| java.lang.NullPointerException

  println(UUID.randomUUID())                      //> 22d336bf-ff91-4dc4-9736-7e338580c15f
}

//
//
//
//