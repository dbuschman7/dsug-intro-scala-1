// case classes
import play.api.libs.json.Json

case class MyData(name: String, value: Int)

object caseClasses {
  println("Welcome to the Scala Case classes")    //> Welcome to the Scala Case classes
  val c1 = MyData("DaVe.", 123)                   //> c1  : MyData = MyData(DaVe.,123)
  val c2 = MyData("DaVe.", 234)                   //> c2  : MyData = MyData(DaVe.,234)
  val c3 = c2                                     //> c3  : MyData = MyData(DaVe.,234)

  c2.name                                         //> res0: String = DaVe.
  c2.value                                        //> res1: Int = 234

  c1 == c2                                        //> res2: Boolean = false
  c2 == c3                                        //> res3: Boolean = true

  val c4 = c2.copy(value = 123)                   //> c4  : MyData = MyData(DaVe.,123)
  c4 == c2                                        //> res4: Boolean = false
  c4 == c1                                        //> res5: Boolean = true

  val set = Set(c2, c1)                           //> set  : scala.collection.immutable.Set[MyData] = Set(MyData(DaVe.,234), MyDat
                                                  //| a(DaVe.,123))
  val set2 = set + c4                             //> set2  : scala.collection.immutable.Set[MyData] = Set(MyData(DaVe.,234), MyDa
                                                  //| ta(DaVe.,123))
  implicit val _format = Json.format[MyData]      //> _format  : play.api.libs.json.OFormat[MyData] = play.api.libs.json.OFormat$$
                                                  //| anon$1@7113b13f

  val json = Json.prettyPrint(Json.toJson(set))   //> json  : String = [ {
                                                  //|   "name" : "DaVe.",
                                                  //|   "value" : 234
                                                  //| }, {
                                                  //|   "name" : "DaVe.",
                                                  //|   "value" : 123
                                                  //| } ]

}

//
//
//
//