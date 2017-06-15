// case classes
import play.api.libs.json.Json

case class MyData(name: String, value: Int)

  println("Welcome to the Scala Case classes")
  val c1 = MyData("DaVe.", 123)
  val c2 = MyData("DaVe.", 234)
  val c3 = c2

  c2.name
  c2.value

  c1 == c2
  c2 == c3

  val c4 = c2.copy(value = 123)
  c4 == c2
  c4 == c1

  val set = Set(c2, c1)
  val set2 = set + c4
  implicit val _format = Json.format[MyData]

  val json = Json.prettyPrint(Json.toJson(set))


//
//
//
//